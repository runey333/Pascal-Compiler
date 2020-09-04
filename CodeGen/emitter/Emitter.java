package emitter;

import java.io.*;

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
        this.emit("lw $t0 ($sp)");
        this.emit("addu $sp $sp 4");        
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
     * Generates a label for next loop.
     * 
     * @return a label for the next loop
     */
    public String nextLoopID()
    {
        loopNum++;
        return "loop" + loopNum;
    }
}