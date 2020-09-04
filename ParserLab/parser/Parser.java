package parser;

import scanner.Scanner;
import scanner.ScanErrorException;


import java.util.*;
/**
* The Parser class defines a recursive Top-Down Parser.
* It handles WRITELN statements, arithmetic expressions,
* and variables.
*
* @author Arun Sundaresan
* @version 20 September 2019
*/
public class Parser
{
    private static Scanner scan;
    private static String currToken;
    private HashMap<String, Integer> vars = new HashMap<String, Integer>();
    
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
     * @return the value of the parsed integer
     * @throws ScanErrorException if an no valid lexeme is found
     */
    private int parseNumber() throws ScanErrorException
    {   
        int result = 0;
        String currVal = currToken;
        
        for(int i = 0; i < currVal.length(); i++)
        {
            result *= 10;
            result += Integer.parseInt(currVal.substring(i, i + 1));
        }
        
        eat(currToken);
        return result;
    }
    
    /**
     * Parses statements, with cases included for WRITELN 
     * statements containing an expression, blocks bounded
     * by BEGIN and END, and variables. The grammar is as follows:
     * stmt → WRITELN ( expr ) ; | BEGIN stmts END ; | id := expr ;
     * stmts → stmts stmt | ε
     * modified to prevent left-recursion.
     *
     * @precondition the current token is a String
     * @postcondition String token has been eaten
     */
    public void parseStatement() throws ScanErrorException
    {
        //eats WRITELN
        if (currToken.equals("WRITELN"))
        {
            eat(currToken);
            
            eat("(");
        
            System.out.println(parseExpression());
        
            eat(")");
        
            eat(";");
        }        
        else if (currToken.equals("BEGIN"))
        {
            eat(currToken);
            
            while (!currToken.equals("END"))
            {
                parseStatement();
            }
            eat("END");
            eat(";");
        }
        else if (scan.isLetter(currToken.charAt(0)))
        {
            String temp = currToken;
            eat(currToken);
            
            eat(":=");
            
            int val = parseExpression();
            
            vars.put(temp, val);
            
            eat(";");
            
        }
        return;
    }
    
    
    /**
     * Parses factors with parentheses and negative signs.
     * The grammar is as follows: factor → ( expr ) | - factor | num | id
     * This method includes cases for parentheses, negative signs,
     * numbers, and variables and uses the parseNumber method to
     * return numerical values.
     * 
     * @return the numerical value of the factor found
     * @throws ScanErrorException if an invalid factor is found
     */
    public int parseFactor() throws ScanErrorException
    {
        //if open parenthesis, eat, parse factor, eat close parenthesis
        if (currToken.equals("("))
        {
            eat(currToken);
            int temp = parseExpression();
            eat(")");
            return temp;
        }     
        //if negative sign, eat it and parse a factor
        else if (currToken.equals("-"))
        {
            eat(currToken);
            return -1 * parseFactor();
        }
        //id case
        else if (vars.containsKey(currToken))
        {
            int temp = vars.get(currToken);
            eat(currToken);
            return temp;
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
     * parseFactor method to obtain numerical values.
     * 
     * @return the numerical value of the term found
     * @throws ScanErrorException if an invalid factor is found
     */
    public int parseTerm() throws ScanErrorException
    {
        int val = parseFactor();
        
        while (currToken.equals("*") || currToken.equals("/"))
        {
            if (currToken.equals("*"))
            {
                eat(currToken);
                val = val * parseFactor();
            }
            else if (currToken.equals("/"))
            {
                eat(currToken);
                val = val / parseFactor();
            }            
        }
        
        return val;
    }
    
    /**
     * Parses expressions with addition and subtraction 
     * according to the grammar: expr → expr + term | expr - term | term
     * modified to prevent left-recursion. The parseTerm method is
     * used to obtain numerical values and enable the parser to
     * handle multiplication and division as well as addition
     * and subtraction.
     * 
     * @return the numerical value of the expression found
     * @throws ScanErrorException if an invalid term is found
     */
    public int parseExpression() throws ScanErrorException
    {
        int val = parseTerm();
        
        while (currToken.equals("+") || currToken.equals("-"))
        {
            if (currToken.equals("+"))
            {
                eat(currToken);
                val = val + parseTerm();
            }
            if (currToken.equals("-"))
            {
                eat(currToken);
                val = val - parseTerm();
            }            
        }
        
        return val;
    }
}