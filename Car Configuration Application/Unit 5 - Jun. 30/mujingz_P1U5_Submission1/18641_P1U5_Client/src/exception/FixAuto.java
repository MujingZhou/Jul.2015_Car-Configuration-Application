package exception;

import model.Automobile;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * CreateAuto-- interface which will be implemented by the BuildAuto class.
 * Details of the methods are given when implemented in the ProxyAutomobile 
 * abstract class.
 */

public interface FixAuto {
    public void fix(int errno, Automobile a1);
    
    public void fix(int errno) ;
}
