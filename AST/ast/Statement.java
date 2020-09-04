package ast;

import environment.Environment;
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
}
