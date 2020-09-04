package ast;

import java.util.*;
import environment.Environment;
import emitter.Emitter;
/**
 * This class represents the declarations of procedures in the PASCAL
 * languages, accounting for procedures with multiple parameters.
 *
 * @author Arun Sundaresan
 * @version October 14, 2019
 */
public class ProcedureDeclaration extends Statement
{
    // instance variables - replace the example below with your own
    private String id;
    private Statement stmt;
    private List<String> paramNames = new ArrayList<String>();
    private List<String> localNames = new ArrayList<String>();

    /**
     * Constructor for objects of class ProcedureDeclaration
     * 
     * @param id the name of the procedure associated with this
     *          procedureDeclaration
     * @param stmt this procedureDeclaration's executable Statement
     * @param paramNames a List of names of the parameters associated
     *          with this procedureDeclaration
     * @param localNames a List of local variables declared in this
     *          procedure
     */
    public ProcedureDeclaration(String id, Statement stmt, 
        List<String> paramNames, List<String> localNames)
    {
        this.id = id;
        this.stmt = stmt;
        this.paramNames = paramNames;
        this.localNames = localNames;
    }

    /**
     * Returns the Statement associated with this procedureDeclaration
     * 
     * @return the Statement associated with this procedureDeclaration
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * Returns the name of the procedure associated with this 
     * procedureDeclaration
     * 
     * @return the name of the procedure associated with this 
     *              procedureDeclaration
     */
    public String getId()
    {
        return id;
    }

    /**
     * Returns the list of parameter names for the procedure associated
     * with this procedureDeclaration
     * 
     * @return the list of parameter names for the procedure associated
     *              with this procedureDeclaration
     */
    public List<String> getParamNames()
    {
        return paramNames;
    }
    
    /**
     * Returns the list of parameter names for the procedure associated
     * with this procedureDeclaration
     * 
     * @return the list of parameter names for the procedure associated
     *              with this procedureDeclaration
     */
    public List<String> getLocalNames()
    {
        return localNames;
    }

    /**
     * Executes this procedureDeclaration by storing it in an
     * Environment
     * 
     * @param env the Environment in which to store this ProcedureDeclaration
     */
    public void exec(Environment env)
    {
        env.setProcedure(id, this);
    }
    
    /**
     * Converts procedure declarations in a simplified Pascal 
     * language to MIPS code. This method handles pushing and
     * popping local variables from the stack, setting and 
     * clearing procedure context, and emitting a jr $ra 
     * instruction.
     * 
     * @param e the Emitter that writes MIPS code to a file 
     */
    public void compile(Emitter e)
    {        
        String procName = "proc" + id + ":";
        e.emit(procName);
        
        e.emit("move $a0 $zero");
        e.emitPush("$a0");
        
        e.setProcedureContext(this);
        
        for (String s:localNames)
        {
            e.emit("move $a0 $zero");
            e.emitPush("$a0");
        }
        
        stmt.compile(e);
        
        for (String s:localNames)
        {
            e.emitPop("$a0");
        }
        
        e.emitPop("$a0");
        e.emit("move $v0 $a0");

        e.emit("jr $ra");
        e.clearProcedureContext();
        
        
    }
}
