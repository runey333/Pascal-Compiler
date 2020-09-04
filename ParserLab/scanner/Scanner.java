package scanner;

import java.io.*;
import java.util.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) 
 * lab exercise 1
 * @author Arun Sundaresan
 * @version September 8, 2019
 *  
 * Usage: An instance of the Scanner class can be constructed
 * with either an InputStream object or a String as input to
 * the constructor. The Scanner class keeps track of a field 
 * currentChar representing the current character. The scanner
 * uses the nextToken method to recognize the next token
 * (special characters, operands, identifiers, numbers).
 * The ScannerTester class is used to test the Scanner class, and
 * instructions for testing can be found in the ScannerTester
 * class.
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;

    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    
    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string. It sets the end-of-file flag and 
     * then reads the first character of the input string into 
     * the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }
    
    /**
     * Uses the BufferedReader read method to get the next character in the 
     * input stream. If the read method returns a value of -1, indicating that
     * the end of the file is reached, the eof field is set to true. Otherwise, 
     * the currentChar field is set to the value returned by the read method 
     * casted to a char. if  an IOErrorException is caught, the program is 
     * aborted.
     */
    private void getNextChar()
    {
        try 
        {
            int next = in.read();
        
            if (next == -1)
            {
                eof = true;
            }
            else
            {
                currentChar = (char)(next);
            }
        }
        catch (IOException e) 
        {
            System.exit(1);
        }

    }
    
    /**
     * Takes a parameter representing the expected value of the currentChar 
     * field and compares it to the expected value of the currentChar field. 
     * If the two match,the input stream is advanced by one character using 
     * the getNextChar method. Otherwise, this method throws a 
     * ScanErrorException with a message to the user.
     *
     * @param expected the expected value of the currentChar field
     * @throws ScanErrorException if the value of the currentChar 
     *                              field is not equal to the expected value
     */  
    private void eat(char expected) throws ScanErrorException
    {
        if (expected == currentChar)
        {
            getNextChar();
        }
        else
        {
            String exc = "Illegal Character: Expected " + currentChar;
            exc += " and found " + expected;
            throw new ScanErrorException(exc);
        }
    }
    
    /**
     * This method determines whether or not the end of file has been reached.
     *
     * @return true if  the end of file has not been reached; otherwise
     *      false
     */
    public boolean hasNext()
    {
        return !eof;    
    }
    
    /**
     * This method returns the next token found by the Scanner.
     * @return a String representing the token found
     * @throws ScanErrorException if the scanOperand, scanNumber, 
     *                              or scanIdentifier methods cannot find
     *                              a valid lexeme or an unrecognizable
     *                              character is found.
     */
    public String nextToken() throws ScanErrorException
    {       
        
        ArrayList<String> l = new ArrayList<String>();
        if(hasNext())
        {
            try
            {
                while (isWhitespace(currentChar) && hasNext())
                {
                    eat(currentChar);
                }
            
                if (isOperand(currentChar) && hasNext())
                {
                    return scanOperand();
                }
                else if (isDigit(currentChar) && hasNext())
                {
                    return scanNumber();
                }
                else if (isLetter(currentChar) && hasNext())
                {
                    return scanIdentifier();
                }   
                else if (isSpecial(currentChar) && hasNext())
                {
                    return scanSpecial();
                }
                else
                {
                    throw new ScanErrorException("char not found");               
                } 
            }
            catch (ScanErrorException e)
            {
                return scanGeneral();
            }
        }
        
        for(int i = 0; i < l.size(); i++)
        {
            throw new ScanErrorException("no char found");
        }
        return "END";
    }    
    
    /**
     * Determines if  a character is a digit.
     *
     * @param test the character to check against the regular expression
     * @return true if the inputted parameter matches the regular expression 
     *          for digits; otherwise false
     */
    public static boolean isDigit(char test)
    {
        return "01234567890".indexOf(test) >= 0;
    }
    
    /**
     * Determines if  a character is a letter.
     *
     * @param test the character to check against the regular expression
     * @return true if  the inputted parameter matches the regular expression 
     *          for letters; otherwise false
     */
    public static boolean isLetter(char test)
    {
        String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        return letters.indexOf(test) >= 0;
    }
    
    /**
     * Determines if  a character is whitespace.
     *
     * @param test the character to check against the regular expression
     * @return true if  the inputted parameter matches the regular expression 
     *          for whitespace; otherwise false
     */
    public static boolean isWhitespace(char test)
    {
        return (test == ' ' || test == '\t' || test == '\n' || test == '\r');
    }
    
    /**
     * Determines if  a character is an acceptable 
     * special character.
     *
     * @param test the character to check
     * @return true if the inputted parameter is an
     *          acceptable special character; otherwise false
     */
    public static boolean isSpecial(char test)
    {
        return ";".indexOf(test) >= 0;
    }
    
    /**
     * Determines if  a character is an operand.
     *
     * @param test the character to check against the regular expression
     * @return true if  the inputted parameter matches the regular expression 
     *          for operands; otherwise false
     */
    public static boolean isOperand(char test)
    {
        return "+=-*/%()<>:".indexOf(test) >= 0;
    }
    
    
    /**
     * This method searches for the next lexeme of a special character
     * by examining the value of the currentChar field.
     * 
     * @return a String representing the lexeme found
     * @throws ScanErrorException if no lexeme is found 
     */
    private String scanSpecial() throws ScanErrorException
    {    
        String res = "";

        if (isSpecial(currentChar) && hasNext())
        {
            res += currentChar;
            eat(currentChar);
        }      
        
        if (res.equals(""))
        {
            throw new ScanErrorException("No lexeme found!");
        }
        else
        {
            return res;
        }
    } 
    
    /**
     * This method searches for the next lexeme
     * by examining the value of the currentChar field.
     * 
     * @return a String representing the lexeme found
     * @throws ScanErrorException if no lexeme is found 
     */
    private String scanGeneral() throws ScanErrorException
    {    
        String res = "";

        if (!isWhitespace(currentChar) && hasNext())
        {
            res += currentChar;
            eat(currentChar);
        }      
        

        return res;
        
    } 
    
    /**
     * This method searches for the next lexeme of a number by 
     * examining the value of the currentChar field.
     * 
     * @return a String representing the lexeme found
     * @throws ScanErrorException if no lexeme is found 
     */
    private String scanNumber() throws ScanErrorException
    {    
        String res = "";

        while (isDigit(currentChar)  && hasNext())
        {
            res += currentChar;
            eat(currentChar);
        }      
        
        if (res.equals(""))
        {
            throw new ScanErrorException("No lexeme found!");
        }
        else
        {
            return res;
        }
        
    }
    
    /**
     * This method searches for the next lexeme of an identifier 
     * by examining the value of the currentChar field.
     * 
     * @return a String representing the lexeme found
     * @throws ScanErrorException if no lexeme is found 
     */
    private String scanIdentifier() throws ScanErrorException
    {    
        String res = "";

        if (isLetter(currentChar))
        {
            while ( (isDigit(currentChar) || 
                isLetter(currentChar)) && hasNext())
            {       
                res += currentChar;
                eat(currentChar);
            }   
        }
        
        if (res.equals(""))
        {
            throw new ScanErrorException("No lexeme found!");
        }
        else
        {
            return res;
        }
        
    }
    
    /**
     * This method searches for the next lexeme of an operand 
     * by examining the value of the currentChar field.
     * 
     * @return a String representing the lexeme found
     * @throws ScanErrorException if no lexeme is found 
     */
    private String scanOperand() throws ScanErrorException
    {    
        String res = "";

        if (isOperand(currentChar)  && hasNext())
        {
            if (currentChar == '<')
            {
                res += currentChar;
                eat(currentChar);
                if (currentChar == '=')
                {
                    res += currentChar;
                    eat(currentChar);
                    return res;
                }
            }           
            else if (currentChar == '>')
            {
                res += currentChar;
                eat(currentChar);
                if (currentChar == '=')
                {
                    res += currentChar;
                    eat(currentChar);
                    return res;
                }
            }
            else if (currentChar == ':')
            {
                res += currentChar;
                eat(currentChar);
                if (currentChar == '=')
                {
                    res += currentChar;
                    eat(currentChar);
                    return res;
                }
            }
            else
            {
                res += currentChar;
                eat(currentChar);
            }
        }      
        
        if (res.equals(""))
        {
            throw new ScanErrorException("No lexeme found!");
        }
        else
        {
            return res;
        }        
    }
    
    /**
     * This method prints all of the tokens this 
     * Scanner can find for a particular input.
     * 
     * @throws ScanErrorException if no lexeme is found for a token
     */
    public void printTokens() throws ScanErrorException
    {
        while (hasNext())
        {
            System.out.println(nextToken());
        }
        //System.out.println(nextToken());
    }
}
