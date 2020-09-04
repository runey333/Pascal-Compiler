package ast;

import environment.Environment;
import emitter.Emitter;
/**
 * The abstract class Statement defines the eval method and is
 * extended by the Writeln, If, While, Block,and Assignment classes.
 *
 * @author Arun Sundaresan
 * @version October 10, 2109
 */
public abstract class Statement
{
    /**
     * Executes a Statement using an Environment.
     * 
     * @param env the Environment in which to execute the Statement
     */
    public abstract void exec(Environment env);
    
    /**
     * This method uses an Emitter to generate MIPS code for
     * AST components extending the Statement class.
     * 
     * @param e the Emitter used to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        
    }
}
