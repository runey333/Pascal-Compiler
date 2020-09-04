package parser;

import scanner.Scanner;
import scanner.ScanErrorException;
import environment.Environment;
import ast.Statement;
import ast.Block;
import ast.Writeln;
import ast.Assignment;
import ast.Number;
import ast.BinOp;
import ast.Variable;
import ast.If;
import ast.While;
import ast.Program;
import emitter.Emitter;

import java.io.*;

/**
 * Tester for the Parser class. This class contains
 * 4 methods, each corresponding to a test for the
 * Parser class.
 *
 * @author Arun Sundaresan
 * @version October 1, 2019
 */
public class ParserTester
{
    /**
     * This method creates a new File object from the file
     * parserTest1.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test1() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest1.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            while(scan.hasNext())
            {
                //Statement s = p.parseStatement();
                //s.exec(env);

                Program pr = p.parseProgram(env);
                Statement s = pr.getStatement();
                s.exec(env);

                //System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This method creates a new File object from the file
     * parserTest2.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test2() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest2.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            while(scan.hasNext())
            {
                //Statement s = p.parseStatement();
                //s.exec(env);

                Program pr = p.parseProgram(env);
                Statement s = pr.getStatement();
                s.exec(env);

                //System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }

    /**
     * This method creates a new File object from the file
     * parserTest3.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test3() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest3.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            while(scan.hasNext())
            {
                //Statement s = p.parseStatement();
                //s.exec(env);

                Program pr = p.parseProgram(env);
                Statement s = pr.getStatement();
                s.exec(env);

                //System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }

    /**
     * This method creates a new File object from the file
     * parserTest4.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test4() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest4.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            while(scan.hasNext())
            {
                //Statement s = p.parseStatement();
                //s.exec(env);

                Program pr = p.parseProgram(env);
                Statement s = pr.getStatement();
                s.exec(env);

                //System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }

    /**
     * This method creates a new File object from the file
     * parserTest6.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test6() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest6.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            while(scan.hasNext())
            {
                //Statement s = p.parseStatement();
                //s.exec(env);

                Program pr = p.parseProgram(env);
                Statement s = pr.getStatement();
                s.exec(env);

                //System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }

    /**
     * This method creates a new File object from the file
     * parserTest7.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test7() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest7.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            
            //Statement s = p.parseStatement();
            //s.exec(env);

            Program pr = p.parseProgram(env);
            Statement s = pr.getStatement();
            s.exec(env);

            //System.out.println(scan.nextToken());
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }
    
    /**
     * This method creates a new File object from the file
     * parserTest8.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and a parser is created with the Scanner as
     * input to the constructor. The statements in the text
     * file are parsed. In the event of an IOException, 
     * the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void test8() throws ScanErrorException 
    {

        try 
        {
            File f = new File("./parser/parserTest8.txt");
            FileInputStream inStream = new FileInputStream(f);

            Scanner scan = new Scanner(inStream);
            Parser p = new Parser(scan);
            Environment env = new Environment(null);

            
            //Statement s = p.parseStatement();
            //s.exec(env);
            
            Program pr = p.parseProgram(env);
            
            
            //Emitter e = new Emitter();
            pr.compile("receptacle.asm");
            //System.out.println(scan.nextToken());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
