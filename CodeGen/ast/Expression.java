package ast;

import environment.Environment;
import emitter.Emitter;
/**
 * The abstract class Expression defines the eval method and is
 * extended by the Number, BinOp, Variable, and Condition classes.
 *
 * @author Arun Sundaresan
 * @version October 10, 2109
 */
public abstract class Expression
{
    /**
     * Evaluates an Expression using an Environment and 
     * returns an integer representing the value of the evaluated 
     * Expression.
     * 
     * @param env the Environment in which to evaluate the Expression
     * @return an integer representing the value of the evaluated Expression
     */
    public abstract int eval(Environment env);
    
    /**
     * This method uses an Emitter to generate MIPS code for
     * AST components extending the Expression class.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        
    }
}
