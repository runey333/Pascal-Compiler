package ast;

import java.util.*;
import environment.Environment;
import emitter.Emitter;
/**
 * This class represents the declarations of procedures in the PASCAL
 * languages, accounting for procedures with multiple parameters.
 *
 * @author Arun Sundaresan
 * @version October 14, 2019
 */
public class ProcedureDeclaration extends Statement
{
    // instance variables - replace the example below with your own
    private String id;
    private Statement stmt;
    private List<String> paramNames = new ArrayList<String>();

    /**
     * Constructor for objects of class ProcedureDeclaration
     * 
     * @param id the name of the procedure associated with this
     *          procedureDeclaration
     * @param stmt this procedureDeclaration's executable Statement
     * @param paramNames a List of names of the parameters associated
     *          with this procedureDeclaration
     */
    public ProcedureDeclaration(String id, Statement stmt, 
    List<String> paramNames)
    {
        this.id = id;
        this.stmt = stmt;
        this.paramNames = paramNames;
    }

    /**
     * Returns the Statement associated with this procedureDeclaration
     * 
     * @return the Statement associated with this procedureDeclaration
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * Returns the name of the procedure associated with this 
     * procedureDeclaration
     * 
     * @return the name of the procedure associated with this 
     *              procedureDeclaration
     */
    public String getId()
    {
        return id;
    }

    /**
     * Returns the list of parameter names for the procedure associated
     * with this procedureDeclaration
     * 
     * @return the list of parameter names for the procedure associated
     *              with this procedureDeclaration
     */
    public List<String> getParamNames()
    {
        return paramNames;
    }

    /**
     * Executes this procedureDeclaration by storing it in an
     * Environment
     * 
     * @param env the Environment in which to store this ProcedureDeclaration
     */
    public void exec(Environment env)
    {
        env.setProcedure(id, this);
    }
    
}
