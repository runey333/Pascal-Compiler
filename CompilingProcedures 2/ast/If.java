package ast;

import environment.Environment;
import emitter.Emitter;
/**
 * This class defines the behavior of IF statements. This class
 * extends the Statement class and uses the value of a Condition 
 * to decide whether to execute a Statement if the Condition
 * evaluates to true.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class If extends Statement
{
    private Condition cond;
    private Statement next;

    /**
     * Constructor for objects of class If
     * 
     * @param cond the Condition to consider
     * @param next the Statement to execute if the proposition 
     *          represented by the condition is satisfied.
     */
    public If(Condition cond, Statement next)
    {
        this.cond = cond;
        this.next = next;
    }

    /**
     * Uses the Condition class' eval method to determine 
     * whether the proposition represented by the condition is
     * satisfied, and executes this class' stored Statement if
     * it is.
     * 
     * @param env the Environment in which to execute the Statement
     */
    public void exec(Environment env)
    {         
        if (cond.eval(env) == 1)
        {
            next.exec(env);
        }
    }
    
    /**
     * This method generates MIPS code for IF statements included in  
     * a simplified Pascal language by generating an ID for a
     * target label, compiling the stored Condition and Statement,
     * and emitting the target label.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        String label = e.nextLabelID();        
        cond.compile(e, label);        
        next.compile(e);
        e.emit(label + ":");
    }
}
