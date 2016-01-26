package model;

import java.util.LinkedHashMap;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * Fleet -- This class has a field of fleet which is a LinkedHashMap whose values are 
 * Automobile objects and keys are the model name of the Automobile.
 *  
 */

public class Fleet {
    private LinkedHashMap<String, Automobile> fleet;
    
    /*
     * Fleet -- default constructor of Fleet class with no argument.
     */   
    public Fleet(){
        fleet =new LinkedHashMap<>();
    }
    
    public int getSize(){
        return fleet.size();
    }
    
    public String[] getAllModelName()
    {
        String [] modelName=new String[getSize()];
        int i=0;
        for(String key:fleet.keySet()){
            modelName[i]=fleet.get(key).getName();
            i++;
        }
        return modelName;
    }
    
    public String[] getAllMakeName()
    {
        String [] makeName=new String[getSize()];
        int i=0;
        for(String key:fleet.keySet()){
            makeName[i]=fleet.get(key).getMake();
            i++;
        }
        return makeName;
    }
    /*
     * addAuto -- add an Automobile object together with its model name
     * to the fleet HashMap.
     */
    public void addAuto(String name, Automobile a1)
    {
        fleet.put(name, a1);
    }
    
    /*
     * searchAuto -- given the model name, find the corresponding Automobile
     * object.
     */
    public Automobile searchAuto(String name)
    {
        if (fleet.containsKey(name))
        {
            return fleet.get(name);
        }
        else {
            System.out.println("can not find the car with name "+ name +" in the fleet");
            return null;
        }
    }
}
