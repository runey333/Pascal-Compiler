package ast;

import environment.Environment;
/**
 * This class defines the behavior of boolean expressions. 
 * Each object of this class contains a String representing
 * the relative operator in the condition, and two Expressions
 * representing the operands. The operands are evaluated and
 * compared to deliver a verdict on whether the proposition
 * of the Expression is satsified.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Condition extends Expression
{
    private String relOp;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class Condition
     * 
     * @param relOp the relative operator of the boolean Expression
     * @param exp1 an operand of the boolean Expression
     * @param exp2 an operand of the boolean Expression
     */
    public Condition(String relOp, Expression exp1, Expression exp2)
    {
        this.relOp = relOp;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * Evaluates the stored operand Expressions and compares
     * them based on the value of the stored relative operator.
     * This method returns 1 if the proposition of the boolean
     * Expression is satisfied.
     * 
     * @param env the Environment in which to evaluate the Expression
     * @return 1 is the proposition of the boolean Expression 
     *              is satisfied; otherwise, this method returns 0
     */
    public int eval(Environment env)
    {
        int e1 = exp1.eval(env);
        int e2 = exp2.eval(env);
        
        if (relOp.equals("="))
        {
            if (e1 == e2)
            {
                return 1;
            }
        }
        else if (relOp.equals("<>"))
        {
            if (e1 != e2)
            {
                return 1;
            }
        }
        else if (relOp.equals("<"))
        {
            //System.out.println("1");
            if (e1 < e2)
            {
                return 1;
            }
        }
        else if (relOp.equals(">"))
        {
            if (e1 > e2)
            {
                return 1;
            }
        }
        else if (relOp.equals("<="))
        {
            if (e1 <= e2)
            {
                return 1;
            }
        }
        else //case for if(relOp.equals(">="))
        {
            if (e1 >= e2)
            {
                return 1;
            }
        }
        
        return 0;
    }
}
