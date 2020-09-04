package ast;

import environment.Environment;
/**
 * This class defines WHILE loops. Each object of this class
 * contains uses the value of a Condition to determine whether
 * to execute a Statement while the COndition evaluates to true.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class While extends Statement
{
    private Condition cond;
    private Statement next;
    
    /**
     * Constructor for objects of class While
     * 
     * @param cond the Condition to consider
     * @param next the Statement to execute while cond
     *          evaluates to true
     */
    public While(Condition cond, Statement next)
    {
        this.cond = cond;
        this.next = next;
    }
    
    /**
     * Uses the Condition class' eval method to determine 
     * whether the proposition represented by the condition is
     * satisfied, and executes this class' stored Statement while
     * it is.
     * 
     * @param env the Environment in which to execute the Statement
     */
    public void exec(Environment env)
    {
        //System.out.println("while");
        while (cond.eval(env) == 1)
        {           
            next.exec(env);
        }
    }
    
    
}
