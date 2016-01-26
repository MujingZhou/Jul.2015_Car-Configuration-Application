package adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import scale.EditOptions;
import util.Util;
import model.Automobile;
import model.Fleet;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * ProxyAutomobile -- This class is an abstract class which is extended by the 
 * BuildAuto class. This class has a static field of a Fleet object and some 
 * methods from the CreateAuto and UpdateAuto interface to operate on the Automobile
 * elements in the fleet field.
 *  
 */

public abstract class ProxyAutomobile {
    private static Fleet fleet;

    /*
     * ProxyAutomobile -- default constructor of ProxyAutomobile class with no
     * argument.
     */
    public ProxyAutomobile() {
        fleet = new Fleet();
    }

    /*
     * getFleet -- get the fleet field of the ProxyAutomobile class.
     */
    public Fleet getFleet() {
        return fleet;
    }

    public int getFleetLength(){
        return fleet.getSize();
    }
    
    public String [] getAutoModelName(){
        return fleet.getAllModelName(); 
    }
    
    public String [] getAutoMakeName(){
        return fleet.getAllMakeName(); 
    }
    
    public void addAutomobile(Automobile a1){
        fleet.addAuto(a1.getName(), a1);
        System.out.println(a1.getName());
    }
    
    /*
     * getAuto -- given the model name, search for the Automobile object and
     * return it.
     */
    public Automobile getAuto(String modelName) {
        return fleet.searchAuto(modelName);
    }

    /*
     * buildAuto -- build an Automobile object from the filename given from the
     * user. buildAutoObject method in the util class will be called for the
     * building. In this method, the "Text file not found" exception is handled.
     */
    public void buildAuto() {
        Automobile a1 = new Automobile();
        Util util1 = new Util();
        try {
            System.out.println("Please enter the filename:");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();
            util1.buildAutoObject(s, a1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fleet.addAuto(a1.getName(), a1);
    }
    
    
    
    /*
     * printAuto -- given the model name, if the name can be found print out the
     * related information of the corresponding Automobile.
     */
    public void printAuto(String modelName) {
        Automobile a1 = fleet.searchAuto(modelName);
        if (a1 != null) {
            a1.printAuto();
        } else {
            System.out.println("Model Name not found");
        }
    }

    /*
     * updateOptionSetName -- given the model name and the old name of the
     * OptionSet, if such model can be found, set the name of this OptionSet to
     * be the new name given by newName parameter. If not found, just prompt a
     * reminder message. Synchronized threads are used in this method.
     */
    public void updateOptionSetName(String modelName, String optionSetName,
            String newName) {

        Automobile a1 = fleet.searchAuto(modelName);

        System.out.println("after searching: " + optionSetName);
        if (a1 != null) {
            synchronized (a1) {
                String operation[] = new String[2];
                operation[0] = optionSetName;
                operation[1] = newName;
                System.out.println("it is now " + optionSetName);

                EditOptions ed1 = new EditOptions(a1, operation, 1);
                ed1.start();
                try {
                    a1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out
                    .println("Model Name not found, can not update the option set name");
        }

    }

    /*
     * updateOptionName -- given the name of the model name and the name of
     * OptionSet and name of the option, if such Option in such OptionSet can be
     * found, set the option name of this Option to be the new name given by newOptionName
     * parameter. If not found, just prompt a reminder message.
     * Synchronized threads are used in this method.
     */
    public void updateOptionName(String modelName, String optionSetName,
            String oldOptionName, String newOptionName) {

        Automobile a1 = fleet.searchAuto(modelName);

        if (a1 != null) {
            synchronized (a1) {
                String operation[] = new String[3];
                operation[0] = optionSetName;
                operation[1] = oldOptionName;
                operation[2] = newOptionName;
                System.out.println("it is now " + oldOptionName);

                EditOptions ed1 = new EditOptions(a1, operation, 2);
                ed1.start();
                try {
                    a1.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            System.out
                    .println("Model Name not found, can not update the option name");
        }

    }

    /*
     * updateOptionPrice -- given the name of the model name and the name of
     * OptionSet and name of the option, if such Option in such OptionSet can be
     * found, set the price of this Option to be the new price given by newPrice
     * parameter. If not found, just prompt a reminder message. 
     * Synchronized threads are used in this method.
     */
    public void updateOptionPrice(String modelName, String optionSetName,
            String option, float newPrice) {
        Automobile a1 = fleet.searchAuto(modelName);

        if (a1 != null) {
            synchronized (a1) {
                String operation[] = new String[3];
                operation[0] = optionSetName;
                operation[1] = option;
                operation[2] = String.valueOf(newPrice);

                EditOptions ed1 = new EditOptions(a1, operation, 3);
                ed1.start();
                try {
                    a1.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            System.out
                    .println("Model Name not found, can not update the option set name");
        }
    }

    /*
     * deleteOptionSet -- given the name of the model name and the name of
     * OptionSet, try to delete such optionSet. If such optionSet can not be deleted
     * (may be deleted already or does not exist), a reminder message will be prompted.
     * Return true only when such optionSet can be deleted successfully.
     * Synchronized threads are used in this method.
     */
    public boolean deleteOptionSet(String modelName, String optionSetName) {
        Automobile a1 = fleet.searchAuto(modelName);

        if (a1 != null) {
            synchronized (a1) {
                String operation[] = new String[1];
                operation[0] = optionSetName;

                if (a1.findOptionSet(optionSetName) >= 0) {

                    EditOptions ed1 = new EditOptions(a1, operation, 4);
                    ed1.start();
                    try {
                        a1.wait();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    return true;
                } else {
                    System.out.println("option set: " + optionSetName
                            + " does not exist. Can not remove.");
                    return false;
                }

            }
        } else {
            System.out
                    .println("Model Name not found, can not update the option set name");
            return false;
        }
    }

    /*
     * deleteOption -- given the name of the model name and the name of
     * OptionSet, try to delete a given option. If such option can not be deleted
     * (may be deleted already or does not exist), a reminder message will be prompted.
     * Return true only when such option can be deleted successfully.
     * Synchronized threads are used in this method.
     */
    public boolean deleteOption(String modelName, String optionSetName,
            String optionName) {
        Automobile a1 = fleet.searchAuto(modelName);

        if (a1 != null) {
            synchronized (a1) {
                String operation[] = new String[2];
                operation[0] = optionSetName;
                operation[1] = optionName;
                if (a1.findOptionInAuto(optionSetName, optionName) >= 0) {

                    EditOptions ed1 = new EditOptions(a1, operation, 5);
                    ed1.start();
                    try {
                        a1.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return true;
                } else {
                    System.out.println("option : " + optionName
                            + " in option set: " + optionSetName
                            + " does not exist. Can not remove.");
                    return false;
                }
            }
        } else {
            System.out
                    .println("Model Name not found, can not update the option set name");
            return false;
        }
    }
}
