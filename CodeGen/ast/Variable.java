package ast;

import environment.Environment;
import emitter.Emitter;
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
    
    /**
     * This method generates MIPS code for Variable objects by loading
     * the value of the variable defined in the .data section into the
     * register $v0.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        e.emit("la $t0 var" + this.name);
        e.emit("lw $v0 ($t0)");
        
        //e.close();
    }
}
