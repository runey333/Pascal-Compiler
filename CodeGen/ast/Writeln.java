package ast;

import environment.Environment;
import emitter.Emitter;
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
    
    /**
     * This method generates MIPS code for WRITELN statements of 
     * a simplified Pascal language. The stored Expression is compiled
     * and its value is printed out, followed by a new line.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
        
        e.emit("li $v0 4");
        e.emit("la $a0 newln");
        e.emit("syscall");
        //e.emit(".asciiz \"\n\"");
        //e.close();
    }
}
