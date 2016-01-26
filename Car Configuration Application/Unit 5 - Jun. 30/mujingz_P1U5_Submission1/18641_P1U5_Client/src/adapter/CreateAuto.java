package adapter;

import java.util.Properties;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * CreateAuto -- interface which will be implemented by the BuildAuto class.
 * Details of the methods are given when implemented in the ProxyAutomobile 
 * abstract class.
 */

public interface CreateAuto {
    public void buildAuto(String fileType,Properties props);
    
    public void printAuto(String modelName);
    
}
