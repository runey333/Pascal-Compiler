package ast;

import environment.Environment;
import emitter.Emitter;
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

    /**
     * This method generates MIPS code for expressions involving
     * binary operators. The two expressions representing operands
     * are compiled and their values are stored in registers. Then, 
     * depending on the operator, a MIPS statement is generated and 
     * its output value is stored in $v0.
     * 
     * @param e the Emitter to write MIPS code to a text file
     */
    public void compile(Emitter e)
    {
        exp1.compile(e);

        e.emitPush("$v0");

        exp2.compile(e);

        e.emitPop("$t0");

        if(op.equals("+"))
        {
            e.emit("addu $v0 $t0 $v0");
            //e.emitPush("$v0");
        }       
        else if(op.equals("-"))
        {
            e.emit("subu $v0 $t0 $v0");
            //e.emitPush("$v0");
        }
        else if(op.equals("*"))
        {
            e.emit("mult $t0 $v0");
            e.emit("mflo $v0");
            //e.emitPush("$v0");
        }
        else 
        {
            e.emit("div $t0 $v0");
            e.emit("mflo $v0");
            //e.emitPush("$v0");
        }
    }
}
