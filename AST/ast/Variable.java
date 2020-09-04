package ast;

import environment.Environment;
/**
 * This class defines the behavior of variables in the code.
 * Each object of this class stores a String representing the
 * name of the variable represented.
 * 
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Variable extends Expression
{
    
    private String name;

    /**
     * Constructor for objects of class Variable
     * 
     * @param name the name of this Variable
     */
    public Variable(String name)
    {
        this.name = name;
    }
    
    /**
     * Uses Environment's getVariable method to return
     * the value associated with the variable name
     * 
     * @param env the Environment in which to evaluate this Expression
     * @return the value that this Variable represents
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
