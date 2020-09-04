package parser;

import scanner.Scanner;
import scanner.ScanErrorException;

import ast.Expression;
import ast.Statement;
import ast.Number;
import ast.BinOp;
import ast.Variable;
import ast.Writeln;
import ast.Block;
import ast.Assignment;
import ast.If;
import ast.While;
import ast.Condition;
import ast.Program;
import ast.ProcedureDeclaration;
import ast.ProcedureCall;

import environment.Environment;

import java.util.*;
/**
 * The Parser class defines a recursive Top-Down Parser.
 * It handles WRITELN statements, IF statements, WHILE loops
 * blocks, arithmetic expressions, and variables.
 *
 * @author Arun Sundaresan
 * @version 20 September 2019
 */
public class Parser
{
    private static Scanner scan;
    private static String currToken;
    //private HashMap<String, Integer> vars = new HashMap<String, Integer>();

    /**
     * Constructor for objects of class Parser
     * 
     * @param scan the Scanner that this Parser will use to
     *               obtain a stream of tokens
     * @throws ScanErrorException if no token is found
     */
    public Parser(Scanner scan) throws ScanErrorException
    {
        this.scan = scan;
        this.currToken = scan.nextToken();
    }

    /**
     * This method parses programs in two steps. The first step parses
     * any number of procedures as long as the current token is PROCEDURE.
     * Once the name of the procedure is parsed, the parameters are parsed 
     * and their names are recorded. ProcedureDeclaration objects are 
     * created with the List of parameters and added to a an ArrayList.
     * Then, a single Statement is parsed and a Program object is created
     * with the List of ProcedureDeclaration objects and the Statement.
     * 
     * @param env the Environment in which to parse the program
     * @throws ScanErrorException if no lexeme is found
     * @return a Program object with a list of procedures and 
     *          a Statement to execute
     */
    public Program parseProgram(Environment env) throws ScanErrorException
    {      
        ArrayList<String> vars = new ArrayList<String>();

        ArrayList<ProcedureDeclaration> procs = 
            new ArrayList<ProcedureDeclaration>();
            
        ArrayList<String> paramNames = 
            new ArrayList<String>();
            
        //changed to while-- was if as of 11/14
        while (currToken.equals("VAR"))
        {
            eat("VAR");
            while (scan.isLetter(currToken.charAt(0)))
            {
                vars.add(currToken);
                eat(currToken);
                if (!currToken.equals(","))
                {
                    break;
                }
                else
                {
                    eat(",");
                }
            }
            eat(";");
        }

        while (currToken.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String id = currToken;
            eat(currToken);
            eat("(");
            paramNames = new ArrayList<String>();
            while (scan.isLetter(currToken.charAt(0)))
            {

                paramNames.add(currToken);
                eat(currToken);
                if (!currToken.equals(","))
                {
                    break;
                }
                else
                {
                    eat(",");
                }
            }
            eat(")");
            eat(";");
            Statement stmt = parseStatement();
            //System.out.println(id);
            ProcedureDeclaration procDec = 
                new ProcedureDeclaration(id, stmt, paramNames);
            procs.add(procDec);            
            //env.setProcedure(id, procDec);
            procDec.exec(env);
        }

        Statement temp = parseStatement();

        return new Program(vars, procs, temp);
    }

    /**
     * This method takes a String representing the expected value
     * of the current token. If the expected value is equal to the
     * true value, the Scanner’s nextToken() method is called to go 
     * to the next token. Otherwise, this method throws an 
     * IllegalArgumentException.
     * 
     * @param expected the expected value of the token
     * @throws IllegalArgumentException if the value of currToken
     *           is not equal to the expected value
     * @throws ScanErrorException if no lexeme is found
     */
    private void eat(String expected) throws ScanErrorException
    {
        if(expected.equals(currToken))
        {
            currToken = scan.nextToken();
        }
        else
        {
            String msg = "Expected " + expected + " but found " + currToken;               
            throw new IllegalArgumentException(msg);
        }

    }

    /**
     * Parses numbers using the Integer class' parseInt method
     * and the Parser's eat method.
     * 
     * @precondition the current token is a String representing
     *                  an integer
     * @postcondition number token has been eaten
     * @return a Number object with the value of the parsed integer
     * @throws ScanErrorException if an no valid lexeme is found
     */
    private Expression parseNumber() throws ScanErrorException
    {   
        String currVal = currToken;

        int result = Integer.parseInt(currVal);

        eat(currToken);
        return new Number(result);
    }

    /**
     * Parses statements, with cases included for WRITELN 
     * statements containing an expression, blocks bounded
     * by BEGIN and END, WHILE loops, IF statements, and 
     * variables. The grammar is as follows:
     * stmt → WRITELN (expr); | BEGIN stmts END; | id := expr; |
     * IF cond THEN stmt | WHILE cond DO stmt
     * stmts → stmts stmt | ε
     * modified to prevent left-recursion.
     *
     * @precondition the current token is a String
     * @postcondition String token has been eaten
     * @return an object of a class extending the Statement class
     *              representing the parsed statement
     */
    public Statement parseStatement() throws ScanErrorException
    {
        //eats WRITELN
        if (currToken.equals("WRITELN"))
        {
            eat(currToken);

            eat("(");

            Expression exp = parseExpression();

            eat(")");

            eat(";");

            //For testing purposes only
            //System.out.println("Writeln made");

            return new Writeln(exp);
        }        
        else if (currToken.equals("BEGIN"))
        {
            eat(currToken);
            List<Statement> stmts = new ArrayList<Statement>();

            while (!currToken.equals("END"))
            {
                stmts.add(parseStatement());
            }

            eat("END");
            eat(";");

            //For testing purposes only
            //System.out.println("Block made");

            return new Block(stmts);
        }
        else if (currToken.equals("IF"))
        {
            eat("IF");

            Expression exp1 = parseExpression(); 

            String tempRelOp = currToken;
            eat(currToken);

            Expression exp2 = parseExpression(); 

            eat("THEN");

            Statement next = parseStatement();

            Condition cond = new Condition(tempRelOp, exp1, exp2); 
            return new If(cond, next);
        }
        else if (currToken.equals("WHILE"))
        {
            eat("WHILE");

            Expression exp1 = parseExpression(); 

            String tempRelOp = currToken;
            eat(currToken);

            Expression exp2 = parseExpression(); 

            eat("DO");

            Statement next = parseStatement();

            Condition cond = new Condition(tempRelOp, exp1, exp2); 
            return new While(cond, next);
        }
        else if (scan.isLetter(currToken.charAt(0)))
        {
            String temp = currToken;
            eat(currToken);

            eat(":=");

            Expression val = parseExpression();           

            eat(";");

            //For testing purposes only
            //System.out.println("Assignment " + temp + ":=" + val);            
            return new Assignment(temp, val);
        }
        return null;
    }

    /**
     * Parses factors with parentheses and negative signs.
     * The grammar is as follows: factor → ( expr ) | - factor | num | id
     * This method includes cases for parentheses, negative signs,
     * numbers, and variables and uses the parseNumber method to
     * return Number Objects.
     * 
     * @return an object of a class extending the Expression class
     *              representing the factor found
     * @throws ScanErrorException if an invalid factor is found
     */
    public Expression parseFactor() throws ScanErrorException
    {
        //if open parenthesis, eat, parse factor, eat close parenthesis
        if (currToken.equals("("))
        {
            eat(currToken);
            Expression temp = parseExpression();
            eat(")");
            return temp;
        }     
        //if negative sign, eat it and parse a factor
        else if (currToken.equals("-"))
        {
            eat(currToken);
            return new BinOp("*", new Number(-1), parseFactor());
        }
        //id case
        else if (scan.isLetter(currToken.charAt(0)))
        {
            String temp = currToken;
            eat(currToken);
            ArrayList<Expression> params = new ArrayList<Expression>();

            if (currToken.equals("("))
            {
                eat("(");
                while (!currToken.equals(")"))
                {
                    params.add(parseExpression());
                    if (currToken.equals(","))
                    {
                        eat(",");
                    }
                }
                eat(")");
                //System.out.println("Call found " + temp);
                return new ProcedureCall(temp, params);
            }

            return new Variable(temp);
        }
        else
        {
            return parseNumber();
        }

    }

    /**
     * Parses terms with multiplication and division. The grammar 
     * is as follows: term → term * factor | term / factor | factor
     * modified to prevent left-recursion. This method uses the
     * parseFactor method to obtain Number objects.
     * 
     * @return an object of a class extending the Expression class
     *              representing the term found
     * @throws ScanErrorException if an invalid factor is found
     */
    public Expression parseTerm() throws ScanErrorException
    {
        Expression val = parseFactor();

        while (currToken.equals("*") || currToken.equals("/"))
        {
            if (currToken.equals("*"))
            {
                eat(currToken);
                val = new BinOp("*", val, parseFactor());
            }
            else if (currToken.equals("/"))
            {
                eat(currToken);
                val = new BinOp("/", val, parseFactor());
            }            
        }

        return val;
    }

    /**
     * Parses expressions with addition and subtraction 
     * according to the grammar: expr → expr + term | expr - term | term
     * modified to prevent left-recursion. The parseTerm method is
     * used to obtain Number objects and enable the parser to
     * handle multiplication and division as well as addition
     * and subtraction.
     * 
     * @return an object of a class extending the Expression class
     *              representing the factor found
     * @throws ScanErrorException if an invalid term is found
     */
    public Expression parseExpression() throws ScanErrorException
    {
        Expression val = parseTerm();

        while (currToken.equals("+") || currToken.equals("-"))
        {
            if (currToken.equals("+"))
            {
                eat(currToken);
                val = new BinOp("+", val, parseTerm());
            }
            if (currToken.equals("-"))
            {
                eat(currToken);
                val = new BinOp("-", val, parseTerm());
            }            
        }

        return val;
    }

}