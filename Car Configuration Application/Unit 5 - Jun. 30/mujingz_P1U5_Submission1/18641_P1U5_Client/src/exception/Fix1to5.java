package exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Automobile;
import util.Util;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * Fix1to5 -- methods in this class are called by the fix method in AutoExcetption
 * class to fix 5 customer defined exceptions.
 */

public class Fix1to5 {

    /*
     * fixFileNotFound -- fix the exception that the text file can not found.
     */
    public void fixFileNotFound(int errno,Automobile a1){
        try {
            System.out.println("Please enter the correct filename again:");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();
            Util util1 = new Util();
            
            util1.buildAutoObject(s, a1);

        } catch (FileNotFoundException f) {
            
            AutoException ae1=new AutoException(1);
            ae1.fix(1,a1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /*
     * fixMissingBasePrice -- fix the exception that the base price of a model
     * is missing. 
     */
    public void fixMissingBasePrice(int errno,Automobile a1)
    {
        String s="s";
        while (isNumeric(s)==false)
        {
            System.out.println("Enter base price:");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            try {
                s= br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        a1.setBasePrice(Float.valueOf(s));
    }
    
    /*
     * fixMissingModelName -- fix the exception that the model name
     * is missing. 
     */
    public void fixMissingModelName(int errno,Automobile a1)
    {
        String s="";
        while (s.isEmpty())
        {
            System.out.println("Enter model name:");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            try {
                s= br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        a1.setName(s);
    }
    
    /*
     * fixMissingOptionPrice -- prompts out the information when the 
     * exception of option price missing takes place. 
     */
    public void fixMissingOptionPrice(int errno, Automobile a1)
    {
        System.out.println("Missing option price has been set to default($100)");
    }
    
    /*
     * fixIndexOutofBound -- prompts out the information when the 
     * exception of index out of bound takes place. 
     */   
    public void fixIndexOutOfBound(int errno)
    {
        System.out.println("Invalid index has been set to default(Index:0)");
    }
    
    /*
     * isNumeric -- helper function to determine whether a given string is a number. 
     */
    public boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
           int chr=str.charAt(i);
           if(chr<48 || chr>57)
               if (chr!=46)
              return false;
        }
        return true;
     }
}
