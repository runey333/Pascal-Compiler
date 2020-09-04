package ast;

import environment.Environment;
import emitter.Emitter;
/**
 * This class defines the behavior of assignment statements. Each object
 * of this class stores a String representing the variable in the 
 * assignment statement and an Expression representing the value 
 * being assigned to the variable.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructor for objects of class Assignment
     * 
     * @param var a String representing the name of the
     *          variable in the assignment statement
     * @param exp the Expression stored by the variable
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    /**
     * Uses the Environment class' setVariable method to either
     * replace or put the value of a variable into the HashMap
     * stored in the Environment class.
     * 
     * @param env the Environment in which to execute the Statement
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }

    /**
     * This method generates MIPS code for assignment statements
     * in a simplified Pascal language, by first compiling this class' 
     * stored expression and storing it in a declared variable in the
     * .data section of the memory.
     * 
     * @param e the Emitter to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        //value of expression is stored in $v0
        
        //store in appropriate stack position if variable is local
        if (e.isLocalVariable(this.var))
        {
            int offset = e.getOffset(this.var);
            //System.out.println("offset of " + this.var + ": " + offset);
            e.emit("addu $t0 $sp " + offset);
            e.emit("sw $v0 ($t0)");
        }
        else 
        {
            e.emit("la $t0, var" + var);        
            e.emit("sw $v0 ($t0)"); 
        }
        
        return;
    }
}
