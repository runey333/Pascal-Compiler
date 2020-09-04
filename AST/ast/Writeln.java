package ast;

import environment.Environment;
/**
 * This class defines the behavior of WRITLELN statements. 
 * Each object of this class stores an Expression which 
 * is evaluated before execution.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of class Writeln
     * 
     * @param exp the Expression stored by each object 
     *          of the Writeln class
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Evaluates and prints the value of the stored Expression
     * 
     * @param env the Environment in which to execute the Statement
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
