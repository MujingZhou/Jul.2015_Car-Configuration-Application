package exception;

import util.Util;
import model.Automobile;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * AutoException -- This class implements the FixAuto interface and has two fields of 
 * the error number and the error message. The fix method is used to do some fix operations.
 *  
 */

public class AutoException extends Exception implements FixAuto{
    
    private static final long serialVersionUID = 1L;
    private int errorNo;
    private String errorMsg;
    
    /*
     * AutoException -- default constructor of AutoException class with no argument.
     */
    public AutoException() {
        super();
    }
    
    /*
     * AutoException -- constructor of AutoException class with one argument
     * which initialize the errorMsg field.
     */
    public AutoException(String errorMsg) {
        super();
        setErrorMsg(errorMsg);
    }
    
    /*
     * AutoException -- constructor of AutoException class with one argument
     * which initialize the errorNo field.
     */
    public AutoException(int errorNo){
        super();
        setErrorNo(errorNo);     
    }
    
    /*
     * AutoException -- constructor of AutoException class with two arguments
     * which initialize the errorNo and errorMsg fields.
     */
    public AutoException(int errorNo, String errorMsg){
        super();
        setErrorNo(errorNo);
        setErrorMsg(errorMsg);
    }
    
    /*
     * getErrorNo -- get the ErrorNo field of AutoException class.
     */
    public int getErrorNo() {
        return errorNo;
    }
    
    /*
     * setErrorNo -- set the ErrorNo field of AutoException class.
     */
    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }
    
    /*
     * getErrorMsg -- get the ErrorMsg field of AutoException class.
     */
    public String getErrorMsg() {
        return errorMsg;
    }
    
    /*
     * setErrorMsg -- set the ErrorMsg field of AutoException class.
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
     
    /*
     * printMyProblem -- print out the information about the exception.
     */
    public void printMyProblem() {
        System.out.println("Exception [errorno=" + errorNo + ", errormsg=\"" + errorMsg+"]\"");
        Util util1=new Util();
        util1.writeLogFile(errorNo, errorMsg);
    }
    
    /*
     * fix -- fix the exception numbered from 1 to 4.
     */
    public void fix(int errno, Automobile a1)
    {
        Fix1to5 f1=new Fix1to5();
        
        switch (errno)
        {
        case 1: printMyProblem();f1.fixFileNotFound(errno,a1); break; 
        
        case 2: printMyProblem();f1.fixMissingBasePrice(errno,a1); break;
        
        case 3: printMyProblem();f1.fixMissingModelName(errno,a1); break;
        
        case 4: printMyProblem();f1.fixMissingOptionPrice(errno, a1); break;
        }
        
    }
    
    /*
     * fix -- fix the exception numbered 5.
     */
    public void fix(int errno)
    {
        Fix1to5 f1=new Fix1to5();
        setErrorMsg("Index out of bound");
        printMyProblem();
        switch (errno)
        {
        case 5: f1.fixIndexOutOfBound(errno); break; 
        }
        
    }
}
