package emitter;

import java.io.*;
import java.util.*;
import ast.ProcedureDeclaration;
/**
 * This class creates Objects that write MIPS code to 
 * a specified text file.
 * 
 * @author Anu Datar with additional methods added by Arun Sundaresan
 * @version November 14, 2019
 */
public class Emitter
{
    private PrintWriter out;
    private int labelNum = 0;
    private int loopNum = 0;
    private int bypassNum = 0;
    ProcedureDeclaration currProc = null;
    int excessStackHeight = 4;
    //private String file;

    /**
     * Constructor for Objects of class Emitter. Creates an emitter 
     * for writing to a new file with given name.
     * 
     * @param outputFileName the name of the file to write to
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
            //file = outputFileName;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes one line of code to the text file, with non-labels
     * indented.
     * 
     * @param code the MIPS code to put in the file
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    /**
     * Emits MIPS code to push onto the stack of a specified 
     * register. 
     * 
     * @param reg the register to push onto
     */
    public void emitPush(String reg)
    {
        this.emit("#push " + reg);
        this.emit("subu $sp $sp 4");
        this.emit("sw " + reg + " ($sp)");
        excessStackHeight += 4;
    }

    /**
     * Emits MIPS code to pop from the stack of a specified 
     * register. 
     * 
     * @param reg the register to pop from
     */
    public void emitPop(String reg)
    {   
        this.emit("#pop " + reg);
        this.emit("lw " + reg + " ($sp)");
        this.emit("addu $sp $sp 4"); 
        excessStackHeight -= 4;
    }

    //closes the file.  should be called after all calls to emit.
    /**
     * This method closes the file being written to.
     */
    public void close()
    {
        out.close();
    }

    /**
     * Generates a label for the next if statement.
     * 
     * @return a label for the next if statement
     */
    public String nextLabelID()
    {
        labelNum++;
        return "endif" + labelNum;
    }

    /**
     * Generates a label for the next loop.
     * 
     * @return a label for the next loop
     */
    public String nextLoopID()
    {
        loopNum++;
        return "loop" + loopNum;
    }

    //remember proc as current procedure context
    /**
     * Sets a specified procedure as the context.
     * 
     * @param proc the procedure to set as the context
     */
    public void setProcedureContext(ProcedureDeclaration proc)
    {
        this.currProc = proc;
        excessStackHeight = 4;
    }

    //clear current procedure context (remember null)
    /**
     * Sets the procedure context to null
     */
    public void clearProcedureContext()
    {
        this.currProc = null;
    }
    
    /**
     * Checks if the variable corresponding to a specified name
     * is local; i.e. if it is a parameter, a local variable declared
     * in a procedure, or the name of the procedure stored as the
     * context.
     * 
     * @param varName the name of the variable to search for
     * @return true if the variable is local, otherwise
     *      false
     */
    public boolean isLocalVariable(String varName)
    {
        if (currProc == null)
        {
            //System.out.println("no proc");
            return false;
        }
        //System.out.println(currProc.getId());

        if(varName.equals(currProc.getId()))
        {
            return true;
        }

        List<String> paramNames = currProc.getParamNames();
        List<String> localNames = currProc.getLocalNames();

        for (String s:localNames)
        {
            if (varName.equals(s))
            {
                //System.out.println("true");
                return true;
            }
        }

        for (String s:paramNames)
        {
            if (varName.equals(s))
            {
                //System.out.println("true");
                return true;
            }
        }

        //System.out.println("fell");
        return false;
    }
    
    /**
     * Gets the offset (distance from the top of the stack) of
     * a variable, given the variable's name.
     * 
     * @param localVarName the name of the variable to search for
     * @return the distance from the top of the stack of the 
     *          specified variable
     */
    public int getOffset(String localVarName)
    {
        List<String> paramNames = currProc.getParamNames();
        List<String> localNames = currProc.getLocalNames();
        //System.out.println(localNames.size());

        if (localNames.size() == 0)
        {
            //this part is in question
            if (localVarName.equals(currProc.getId()))
            {
                //System.out.println(localVarName);
                return localNames.size()*4;
            }
            //to here
            if (localNames.contains(localVarName))
            {
                int lastindex = localNames.size()-1;
                //System.out.println(localNames.size());
                int index = -1;
                for (int i = lastindex; i > -1; i--)
                {
                    index ++;
                    if (localNames.get(i).equals(localVarName))
                    {
                        break;
                    }
                }
                return (index*4)+excessStackHeight;
                //return (index*4);
            }
            int lastindex = paramNames.size()-1;
            int index = -1;
            for (int i = lastindex; i > -1; i--)
            {
                index ++;
                if (paramNames.get(i).equals(localVarName))
                {
                    break;
                }
            }
            return (index*4)+excessStackHeight+(localNames.size()*4);
        }
        else
        {
            if (localVarName.equals(currProc.getId()))
            {
                return 4 * localNames.size();
            }

            int locOffset = 0;
            for (int i = localNames.size() - 1; i >= 0; i--)
            {
                String currName = localNames.get(i);
                if (localVarName.equals(currName))
                {
                    return locOffset;
                }
                else
                {
                    locOffset += 4;
                }
            }

            int offset = 4 + (4 * localNames.size());
            for (int j = paramNames.size() - 1; j >= 0; j--)
            {
                String currName = paramNames.get(j);
                if (localVarName.equals(currName))
                {
                    break;
                }
                else
                {
                    offset += 4;
                }
            }

            return offset;
        }
    }

}