package scale;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * EditAuto -- interface which will be implemented by the BuildAuto class.
 * Details of the methods are given when implemented in the ProxyAutomobile 
 * abstract class.
 */

public interface EditAuto {
    public void updateOptionSetName(String modelName, String optionSetName,
            String newName);
    
    public void updateOptionName(String modelName, String optionSetName,
            String oldOptionName, String newOptionName);
    
    public void updateOptionPrice(String modelName, String optionSetName,
            String option, float newPrice);
    
    public boolean deleteOptionSet(String modelName, String optionSetName);
    
    public boolean deleteOption(String modelName, String optionSetName,
            String optionName);
}
