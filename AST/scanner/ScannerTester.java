package scanner;

import java.io.*;
/**
 * The ScannerTester class is designed to test the 
 * Scanner class.
 * 
 * Instructions to test in BlueJ:
 * Control-click(right-click on PCs) on the ScannerTester 
 * class and create a new instance of the ScannerTester class.
 * Control-click on the image representing
 * the ScannerTester Object and select the method to execute.
 * An image representing a Scanner Object will appear. 
 * Control-click on the image representing
 * the Scanner Object and select the printTokens() method.
 * The results will appear in the BlueJ Terminal window.
 * 
 * Troubleshooting:
 * If the results do not appear, try closing BlueJ and opening
 * it again.
 * 
 * @author Arun Sundaresan
 * @version September 9, 2019
 */
public class ScannerTester
{
    /**
     * Constructor for objects of class ScannerTester
     */
    public ScannerTester()
    {
        
    }
    
    /**
     * This method creates a new File object from the file
     * scannerTestAdvanced.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and the tokens are printed. In the event of 
     * an IOException, the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void readAdvanced() throws ScanErrorException 
    {
        try 
        {
            File f = new File("parsertest0.txt");
            FileInputStream inStream = new FileInputStream(f);
        
            Scanner scan = new Scanner(inStream);
        
            while(scan.hasNext())
            {
                System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }
    
    /**
     * This method creates a new File object from the file
     * scannerTest.txt and creates a FileInputStream
     * from the file. Then, a Scanner with the FileInputStream
     * is created and the tokens are printed. In the event of 
     * an IOException, the program is aborted.
     *          
     * @precondition the file scannerTestAdvanced.txt is in
     *          the same working directory as the ScannerTester
     *          class
     */
    public static void readBasic() throws ScanErrorException 
    {
        try 
        {
            File f = new File("ScannerTest.txt");
            FileInputStream inStream = new FileInputStream(f);
        
            Scanner scan = new Scanner(inStream);
        
            while(scan.hasNext())
            {
                System.out.println(scan.nextToken());
            }
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }
}
