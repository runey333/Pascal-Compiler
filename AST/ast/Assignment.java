package ast;

import environment.Environment;
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
}
