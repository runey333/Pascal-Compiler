package ast;

import environment.Environment;
/**
 * This class defines the behavior of mathematical operators(+, -, *, /)
 * Each object of this class stores a String representing the operator
 * and two Expressions representing the operands.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class BinOp
     * 
     * @param op a String representing the operator
     * @param exp1 an operand of the operation
     * @param exp2 an operand of the operation
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    
    /**
     * Evaluates the values of both expressions and returns
     * and int as the result of an operation based on the 
     * String stored in the instance variable op.
     * 
     * @param env the Environment in which to evaluate the Expression
     * @return the result of the appropriate operation on 
     *              the two Expressions
     */
    public int eval(Environment env)
    {
        if(op.equals("*"))
        {
            return exp1.eval(env) * exp2.eval(env);
        }       
        else if(op.equals("/"))
        {
            return exp1.eval(env) / exp2.eval(env);
        }
        else if(op.equals("+"))
        {
            return exp1.eval(env) + exp2.eval(env);
        }
        else 
        {
            return exp1.eval(env) - exp2.eval(env);
        }
        
    }
}
