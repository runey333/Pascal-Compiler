package ast;

import environment.Environment;
import emitter.Emitter;
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
    
    /**
     * This method generates MIPS code for numbers included in a
     * simplified Pascal language by storing the value of the number
     * in the $v0 register.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value);
        //e.close();
    }
}
