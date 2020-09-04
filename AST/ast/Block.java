package ast;

import java.util.*;
import environment.Environment;
/**
 * This class defines the behavior of blocks starting with
 * BEGIN and ending with END. Each object of this class stores
 * a List of Statements to be executed.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Block extends Statement
{
    private List<Statement> stmts;

    /**
     * Constructor for objects of class Block
     * 
     * @param stmts the List of Statements to be executed in this Block
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }
    
    /**
     * Uses polymorphism to execute each statement in the stored List.
     * 
     * @param env the Environment in which to execute the Statements
     */
    public void exec(Environment env)
    {
        for(int i = 0; i < stmts.size(); i++)
        {
            stmts.get(i).exec(env);
        }
    }
}
