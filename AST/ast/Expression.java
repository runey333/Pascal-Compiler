package ast;

import environment.Environment;
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
}
