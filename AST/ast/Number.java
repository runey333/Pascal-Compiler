package ast;

import environment.Environment;
/**
 * This class defines the behavior of numbers in the code. 
 * Each object of this class stores an int representing the
 * value of the number represented.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Number extends Expression
{   
    private int value;
    
    /**
     * Constructor for objects of clas Number
     * 
     * @param value the value of the number represented
     */
    public Number(int value)
    {
        this.value = value;
    }
    
    /**
     * Returns the value stored by this Number.
     * 
     * @param env the Environment in which to evaluate the Expression
     * @return the value stored by this Number.
     */
    public int eval(Environment env)
    {
        return value;
    }
}
