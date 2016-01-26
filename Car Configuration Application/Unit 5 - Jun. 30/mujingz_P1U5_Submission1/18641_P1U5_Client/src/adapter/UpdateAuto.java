package adapter;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * UpdateAuto-- interface which will be implemented by the BuildAuto class.
 * Details of the methods are given when implemented in the ProxyAutomobile 
 * abstract class.
 */

public interface UpdateAuto {
    public void updateOptionSetName(String modelName, String optionSetName, String newName);
    
    public void updateOptionPrice(String modelName, String optionName, String option, float newprice);
}
