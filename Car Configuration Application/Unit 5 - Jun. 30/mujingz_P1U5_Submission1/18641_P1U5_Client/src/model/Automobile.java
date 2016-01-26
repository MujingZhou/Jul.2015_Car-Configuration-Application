package model;
import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import model.OptionSet;
import model.OptionSet.Option;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * Automotive -- This class is the main class for creating an Auto object.
 * An Auto object has the model name, base price and an array of type OptionSet which
 * stores available options(like color, Transmission and so on). A couple of methods are
 * created for basic operations of the Auto object.
 * This class implements the Serializable Interface for serialization in the Driver class.
 */

public class Automobile implements Serializable {

   /**
	 * 
	 */
//	private static final long serialVersionUID = -3422888057734946220L;
	private static final long serialVersionUID = 1L;
    private String name;
    private String make;
    private ArrayList<OptionSet> optionSet;
    private ArrayList<Option> choice;
    private float basePrice;
    
    /*
     * Automotive -- default constructor of Automotive class with no argument.
     */
    public Automobile() {
        optionSet=new ArrayList<OptionSet>();
        choice=new ArrayList<Option>();
    }

    /*
     * Automotive -- constructor of Automotive class with three arguments which
     * initialize the name and basePrice fields as well as initialize the optSet
     * field with the optionSetSize.
     */
    public Automobile(String name, float basePrice, int optionSetSize) {
        setName(name);
        setBasePrice(basePrice);
        optionSet=new ArrayList<OptionSet>();
        choice=new ArrayList<Option>();


        for (int i = 0; i < optionSetSize; i++) {
            optionSet.add(new OptionSet());
            choice.add(optionSet.get(0).new Option());
        }        
    }

    /*
     * initAutoe -- initialize the two ArrayLists fields.
     */
    public void initAuto(int optionSetSize)
    {
        for (int i = 0; i < optionSetSize; i++) {
            optionSet.add(new OptionSet());
            choice.add(optionSet.get(0).new Option());
        }
    }
    
    /*
     * setName -- set the name field of the Automotive class.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * setBasePrice -- set the name field of the Automotive class.
     */
    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    /*
     * setMake -- set the make field of the Automotive class.
     */
    public void setMake(String make)
    {
        this.make=make;
    }
    
    /*
     * setOptionSet -- set the optSet field of the Automotive class by
     * given array of options names.
     */
    public void setOptionSet(String[] options) {
        for (int i = 0; i < options.length; i++) {
            optionSet.set(i, new OptionSet(options[i]));            
        }
       
    }

    /*
     * setOption -- set values of Option(in context of OptionSet).
     * optionSetName is used to determine which OptionSet will be used to set
     * the following option values. Arrays of option names and prices will be 
     * used to set the option values.
     */
    public void setOption(String optionSetName, String[] optName,
            float[] optPrice) {

        int index = findOptionSet(optionSetName);
        if (index >= 0) {
            optionSet.set(index, new OptionSet(optionSetName, optName.length));

            for (int i = 0; i < optName.length; i++) {
                optionSet.get(index).setArrList(i,optName[i],optPrice[i]);
            }
        }        
    }

    /*
     * getName -- get the value of the name field of the Automotive class.
     */
    public String getName() {
        return name;
    }

    /*
     * getMake -- get the value of the make field of the Automotive class.
     */
    public String getMake()
    {
        return make;
    }
    
    
    /*
     * getBasePrice -- get the value of basePrice field of the Automotive class.
     */
    public float getBasePrice() {
        return basePrice;
    }

    /*
     * getOptionSet -- get the OptionSet object via the index of the optSet array.
     */
    public OptionSet getOptionSet(int OptionSetIndex){
        try{
        if (OptionSetIndex>optionSet.size()-1||OptionSetIndex<0)
        {
            throw new AutoException(5);
        }
        
        }
        catch (AutoException ae)
        {
            AutoException ae1 = new AutoException(5);
            OptionSetIndex=0;
            ae.setErrorMsg("Option Set Index Out of Bound");
            ae1.fix(5);
        }
        return optionSet.get(OptionSetIndex);
    }

    public int getOptionSetLength(){
        return optionSet.size();
    }
    
    public String getOptionName(String optionSetName, int index){
        OptionSet os1=optionSet.get(findOptionSet(optionSetName));
        return os1.getOpt(index).getName();
    }
    
    public String getOptionSetName(int index)
    {
        return optionSet.get(index).getName();
    }
    /*
     * findOptionSet -- given a name of an OptionSet, determined whether such 
     * optionSet can be found in optSet array.
     * If found, the index of the optSet array which matches the given string name
     * will be returned.
     * If not found, -1 will be returned.
     */
    public int findOptionSet(String optSetName) {
        for (int i = 0; i < optionSet.size(); i++) {
            OptionSet ops1=optionSet.get(i);
            if (ops1.getName().equals(optSetName)) {
                return i;
            }
        }
        return -1;
    }
    
   /*
    * findOptionInAuto -- given the name of an OptionSet and the name of an option, 
    * determined whether such option can be found in the given OptionSet.
    * If found, the index of the option array which matches the given string name
    * will be returned.
    * If not found, -1 will be returned.
    */
    public int findOptionInAuto(String optSetName, String optName) {
        int optSetIndex = findOptionSet(optSetName);
        if (optSetIndex >= 0) {
            OptionSet ops1=optionSet.get(optSetIndex);
            return ops1.findOptionInSet(optName);
        }
        else
            return -1;
    }

    /*
     * updateOptionSet -- given the old name of the OptionSet,
     * if such OptionSet can be found, set the name of this OptionSet
     * to be the new name given by newName parameter.
     * If not found, just ignore it.
     */
    public void updateOptionSet(String oldName, String newName) {
        int index = findOptionSet(oldName);
        if (index >= 0) {
            synchronized (this) {
 
            System.out.println("succeed to find optionSet: "+oldName);
            OptionSet ops1=optionSet.get(index);           
            ops1.setName(newName);
            }
        }
        else 
        {
            System.out.println("can not find optionSet: "+oldName);
        }
    }

    /*
     * updateOption -- given the name of the OptionSet and the old name of the option, 
     * if such Option in such OptionSet can be found, set the name of this Option
     * to be the new name given by optnewName parameter.
     * If not found, just ignore it.
     */
    public void updateOption(String optSetName, String optOldName,
            String optNewName) {
        int optIndex = findOptionInAuto(optSetName, optOldName);
        if (optIndex >= 0) {
            int optSetIndex = findOptionSet(optSetName);
            OptionSet ops1=optionSet.get(optSetIndex);
            ops1.getOpt(optIndex).setName(optNewName);
        }
        else {
            System.out.println("can not find option: "+optOldName);
        }
    }

    /*
     * updateOption -- given the name of the OptionSet and the old name of the option, 
     * if such Option in such OptionSet can be found, set the price of this Option
     * to be the new price given by newPrice parameter.
     * If not found, just ignore it.
     */
    public void updateOption(String optSetName, String optOldName,
            float newPrice) {
        int optIndex = findOptionInAuto(optSetName, optOldName);
        if (optIndex >= 0) {
            OptionSet ops1=optionSet.get(findOptionSet(optSetName));
            float oldPrice=ops1.getOpt(optIndex).getPrice();
            ops1.getOpt(optIndex).setPrice(
                    oldPrice+newPrice);
        }
        else {
            System.out.println("can not find option: "+optOldName);
        }
    }

    /*
     * updateOption -- given the name of the OptionSet and the old name of the option, 
     * if such Option in such OptionSet can be found, set both the name and price of
     * this Option to be the new name and new price given by optNewName and newPrice parameters.
     * If not found, just ignore it.
     */
    public void updateOption(String optSetName, String optOldName,
            String optNewName, float newPrice) {
        int optIndex = findOptionInAuto(optSetName, optOldName);
        if (optIndex >= 0) {
            OptionSet ops1=optionSet.get(findOptionSet(optSetName));
            ops1.getOpt(optIndex).setName(
                    optNewName);
            float oldPrice=ops1.getOpt(optIndex).getPrice();
            ops1.getOpt(optIndex).setPrice(
                    oldPrice+newPrice);
        }
        else {
            System.out.println("can not find option: "+optOldName);
        }
    }

    /*
     * deleteOptionSet -- given the name of an OptionSet, if it can be 
     * found in optSet, delete it.
     */
    public void deleteOptionSet(String optSetName) {
        int index = findOptionSet(optSetName);
        if (index >= 0) {
            optionSet.remove(index);
        }
        else {
            System.out.println("option set: "+optSetName+" does not exist. Can not remove.");
        }
    }

    /*
     * deleteOption -- given the name of an OptionSet and name of an Option, 
     * if it can be found, delete it.
     */
    public void deleteOption(String optSetName, String optName) {
        int optIndex = findOptionInAuto(optSetName, optName);
        if (optIndex >= 0) {
            OptionSet ops1=optionSet.get(findOptionSet(optSetName));
            ops1.deleteOption(optIndex);
        }
    }

    /*
     * getOptionChoice -- given the name of an OptionSet, try to get
     * the choice in this optionSet the user has made.
     */
    public String getOptionChoice(String setName)
    {
        int indexOptionSet=findOptionSet(setName);
        if (indexOptionSet>=0)
        {
            if (choice.get(indexOptionSet)!=null)
            return choice.get(indexOptionSet).getName();
            else return null;
        }
        
        return null;       
    }
    
    /*
     * getOptionChoicePrice -- given the name of an OptionSet, try to get
     * the price of the choice in this optionSet the user has made.
     */
    public float getOptionChoicePrice(String setName)
    {   
       float price=0;
       
       int indexOptionSet=findOptionSet(setName);
       if (indexOptionSet>=0)
       {
           if (choice.get(indexOptionSet)!=null){
               price=choice.get(indexOptionSet).getPrice();
           }
       }      
       return price; 
    }
    
    /*
     * setOptionChoice -- given the name of an OptionSet, set the 
     * choice of the user made to be the option given by optionName parameter.
     */
    public void setOptionChoice(String setName, String optionName)
    {
        int indexOptionSet=findOptionSet(setName);
        if (indexOptionSet>=0)
        {
            int indexOption=findOptionInAuto(setName, optionName);
            if (indexOption>=0)
            {         
                choice.set(indexOptionSet, optionSet.get(indexOptionSet).getOpt(indexOption));
            }
        }
    }
    
    /*
     * getTotalPrice -- Based on the choices the user made on each of the 
     * optionSet, calculate the total price if the user purchases the auto.
     */
    public float getTotalPrice()
    {
        float totalPrice=basePrice;
        for (int i=0;i<optionSet.size();i++)
        {
           
            totalPrice+=choice.get(i).getPrice();
        }
        
        return totalPrice;
    }
    
    /*
     * printAuto -- append information for output.
     */
    public void printAuto() {
        StringBuffer str = new StringBuffer();
        str.append("Model Name: ");
        str.append(getName());
        str.append("\nBase Price: $");
        str.append(getBasePrice());
        str.append("\nAvailable Options: ");
        
        
        for (int i = 0; i < optionSet.size() - 1; i++) {
            str.append(optionSet.get(i).getName());
            str.append(", ");
        }
        str.append(optionSet.get(optionSet.size() - 1).getName());
        str.append("\n\n");
        str.append("Detailed Option Information: ");
        str.append("\n");

        for (int i = 0; i < optionSet.size(); i++) {
            str.append(optionSet.get(i).getName());
            str.append(":\n");
            str.append(optionSet.get(i).printOptSet());
        }
        
        System.out.println(str);
    }
    
    public void printOptionSetForChoice(int index)
    {
        System.out.println("OptionSetName: "+getOptionSet(index).getName());
        for (int i=0;i<getOptionSet(index).getOptLen();i++)
        {
            System.out.println("Option "+(i+1)+" : "+getOptionSet(index).getOpt(i).getName());
        }
        System.out.println();
    }
    
    public int getOptionLength(int indexOptionSet)
    {
        OptionSet ops1=optionSet.get(indexOptionSet);
        return ops1.getOptLen();
    }
}
