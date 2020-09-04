package ast;

import environment.Environment;
import java.util.*;
import emitter.Emitter;
/**
 * This class represents calls to procedures from the body of Pascal
 * programs. Each class contains a List of Expressions representing the
 * values of the parameters of the procedure.
 *
 * @author Arun Sundaresan
 * @version October 14, 2019
 */
public class ProcedureCall extends Expression
{
    // instance variables - replace the example below with your own
    private String id;
    private List<Expression> params = new ArrayList<Expression>();

    /**
     * Constructor for objects of class ProcedureCall
     * 
     * @param id the name of the procedure being called
     * @param params the Expressions representing the 
     *      values of the parameters
     */
    public ProcedureCall(String id, List<Expression> params)

    {
        this.id = id;
        this.params = params;
    }    

    /**
     * Evaluates the procedure call. This method creates a local environment
     * and evaluates the parameters in it. The statement of the procedure is
     * then executed and an integer is returned representing the return
     * value of the procedure.
     * 
     * @param env the Environment in which to evaluate the procedure call
     * @return an integer is returned representing the return
     *          value of the procedure.
     */
    public int eval(Environment env)
    {
        ProcedureDeclaration p = env.getProcedure(id);
        List<String> paramNames = p.getParamNames();
        String procId = p.getId();

        Environment local = new Environment(env);
        env.declareVariable(procId, 0);

        for(int i = 0; i < params.size(); i++)
        {
            //local.declareVariable(paramNames.get(i), 
            //    params.get(i).eval(env));
            //System.out.println(paramNames.get(i));
            local.declareVariable(paramNames.get(i), params.get(i).eval(local));
        }
        //System.out.println("p");
        //if (p != null) {System.out.println("p");}
        Statement stmt = p.getStatement();
        stmt.exec(local);
        return env.getVariable(procId);
    }

    /**
     * Converts procedure calls in a simplified Pascal language to 
     * MIPS by pushing the return address, pushing all parameters,
     * emitting a jump-and-link instruction, popping all parameters, 
     * and popping the return address.
     * 
     * @param e the Emitter that writes MIPS code to a file
     */
    public void compile(Emitter e)
    {
        e.emitPush("$ra");

        for (Expression ex: params)
        {
            ex.compile(e); //return value in $v0

            //changed to $a0 from $ra
            e.emit("move $a0 $v0");
            e.emitPush("$a0");
        }

        String procName = "proc" + id;
        e.emit("jal " + procName);

        for (Expression ex: params)
        {
            e.emitPop("$a0");
        }
        e.emitPop("$ra");

    }
}
