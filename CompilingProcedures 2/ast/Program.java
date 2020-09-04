package ast;

import java.util.*;
import emitter.Emitter;
/**
 * This class represents Pascal programs. Each object of this class contains
 * a List of ProcedureDeclaration objects representing the procedures
 * defined at the beginning at the program and a Statement object.
 *
 * @author Arun Sundaresan with assistance from Srivishnu Pyda
 * @version October 14, 2019
 */
public class Program
{
    // instance variables - replace the example below with your own
    private List<String> vars = 
        new ArrayList<String>();
    private List<ProcedureDeclaration> procs = 
        new ArrayList<ProcedureDeclaration>();
    private static Statement stmt;

    /**
     * Constructor for objects of class Program
     * 
     * @param vars the List of variables declared at the beginning
     *                  of the program
     * @param procs the List of ProcedureDeclaration objects defining
     *                  the behavior of procedures
     * @param stmt the statement to execute
     */
    public Program(List<String> vars, 
        List<ProcedureDeclaration> procs, Statement stmt)
    {
        this.vars = vars;
        this.procs = procs;
        this.stmt = stmt;
    }

    /**
     * Returns this Program's Statement
     * 
     * @return this Program's Statement
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * This is a helper method that returns the name of a variable
     * in the .data section of MIPS code given its name in a segment of 
     * code in a simplified Pascal language.
     * 
     * @param name the name of the variable in a segment of code in a 
     *          simplified Pascal language
     * @return the variable's name in the MIPS .data section
     */
    public String makeVariableName(String name)
    {
        return "var" + name;
    }
    
    /**
     * This method generates MIPS code for programs in a simplified
     * Pascal language. It first creates an Emitter and assigns it 
     * to a text file. It then creates a .data section and adds a 
     * variable representing a new line to it. Variables declared at 
     * the beginning of the Pascal program are then added to the
     * .data section and the .text section and main: label are added.
     * Finally, this Program's statement is compiled.
     * 
     * @param filename the file to emit MIPS code to
     */
    public void compile(String filename)
    {
        Emitter e = new Emitter(filename);

        e.emit(".data");

        e.emit("newln:");
        e.emit(".asciiz \"" + "\\" + "n" + "\"");

        for(String v: vars)
        {
            e.emit(makeVariableName(v) + ":");
            e.emit(".word 0");
        }

        e.emit(".text");
        
        e.emit("main:");
        stmt.compile(e);
        e.emit("li $v0 10");
        e.emit("syscall");

        for(ProcedureDeclaration p: procs)
        {
            p.compile(e);
        }
        
        e.close();
    }
}