package environment;

import ast.Expression;
import java.util.*;
/**
 * This class stores a HashMap which contains the names and values
 * of variables specified in the code.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Environment
{
    private HashMap<String, Integer> vars = new HashMap<String, Integer>();

    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {

    }
    
    /**
     * Uses the HashMap class' put method to either replace the value 
     * of a variable or add a new variable-value pair to the 
     * stored HashMap.
     * 
     * @param id the name of the variable
     * @param value the value of the variable
     */
    public void setVariable(String id, int value)
    {     
        vars.put(id, value);
    }
    
    /**
     * Returns the value of a variable using the HashMap class' get method.
     * 
     * @param id the name of the variable whose value is to be obtained
     * @return the value of the variable whose name corresponds to the
     *              inputted parameter
     */
    public int getVariable(String id)
    {       
        return vars.get(id);
    }
    
    
}
