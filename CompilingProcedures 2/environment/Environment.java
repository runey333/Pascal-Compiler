package environment;

import ast.Expression;
import ast.ProcedureDeclaration;
import java.util.*;

/**
 * This class stores a HashMap which contains the names and values
 * of variables and procedure declarations specified in the code.
 *
 * @author Arun Sundaresan
 * @version October 10, 2019
 */
public class Environment
{
    private HashMap<String, Integer> vars = new HashMap<String, Integer>();
    private HashMap<String, ProcedureDeclaration> procs = 
        new HashMap<String, ProcedureDeclaration>();
    private Environment parent;

    /**
     * Constructor for objects of class Environment
     * 
     * @param parent this Environment's parent Environment
     */
    public Environment(Environment parent)
    {
        this.parent = parent;
    }

    /**
     * Uses the HashMap class' put method to either replace the value 
     * of a variable or add a new variable-value pair to the 
     * stored HashMap in the local Environment.
     * 
     * @param id the name of the variable
     * @param value the value of the variable
     */
    public void declareVariable(String id, int value)
    {     
        vars.put(id, value);
    }
    
    /**
     * Sets the value of the variable with the specified id to
     * the specified value. If the variable is declared in this
     * Environment, the value is set in this Environment. Otherwise,
     * it is set in the global Environment.
     * 
     * @param id the variable name
     * @param value the value to assign
     */
    public void setVariable(String id, int value)
    {     
        if (vars.containsKey(id))
        { // if variable already declared, put in this environment
            vars.put(id, value);         
        }
        else //set variable in global environment
        {
            Environment global = this.parent;

            if (global != null)
            {    
                while (global != null)
                {
                    if (global.getVars().containsKey(id))
                    { 
                        global.getVars().put(id, value);  
                        return;    
                    }
                    global = global.getParent();
                }             
            }        

            vars.put(id, value);   
        }
    }

    /**
     * Returns the value of a variable using the HashMap class' get method.
     * If this object's HashMap contains the desired key, it is obtained. If
     * not this method recursively calls itself on the parent Environment.
     * 
     * @param id the name of the variable whose value is to be obtained
     * @return the value of the variable whose name corresponds to the
     *              inputted parameter
     */
    public int getVariable(String id)
    {       
        if (vars.containsKey(id))
        {
            return vars.get(id);
        }
        else
        {
            return parent.getVariable(id);
        }
    }

    /**
     * Returns the value of a variable using the HashMap class' get method.
     * If this object's HashMap contains the desired key, it is obtained. If
     * not this method recursively calls itself on the parent Environment.
     * 
     * @param id the name of the procedure whose declaration is to 
     *              be obtained
     * @return the ProcedureDeclaration corresponding to the
     *              inputted parameter
     */
    public ProcedureDeclaration getProcedure(String id)
    {
        if (procs.containsKey(id))
        {
            return procs.get(id);
        }
        else
        {
            return parent.getProcedure(id);
        }
    }

    /**
     * Adds a procedure with a given id and ProcedureDeclaration to
     * the HashMap of the global environment.
     * 
     * @param id the procedure's name
     * @param proc the ProcedureDeclaration of the procedure
     */
    public void setProcedure(String id, ProcedureDeclaration proc)
    {   
        // if (parent == null)
        // {
        // procs.put(id, proc);
        // }
        // else
        // {
        // parent.setProcedure(id, proc);
        // }
        Environment global = parent;
        if (global == null)
        {
            procs.put(id, proc);
            //return;
        }
        else 
        {    
            while (global != null)
            {
                global = global.getParent();
            }         
            procs.put(id, proc);
            //return;
        }   
        //procs.put(id, proc);
    }

    /**
     * Returns the parent Environment
     * 
     * @return the parent Environment
     */
    public Environment getParent()
    {
        return parent;
    }
    
    /**
     * Returns the HashMap containing variables
     * 
     * @return the HashMap containing variables
     */
    public HashMap<String, Integer> getVars()
    {
        return vars;
    }
    
    /**
     * Returns the HashMap containing ProcedureDeclaration objects
     * 
     * @return the HashMap containing ProcedureDeclaration objects
     */
    public HashMap<String, ProcedureDeclaration> getProcs()
    {
        return procs;
    }
}
