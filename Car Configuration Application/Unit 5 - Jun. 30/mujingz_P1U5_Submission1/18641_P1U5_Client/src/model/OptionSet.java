package model;

import java.io.Serializable;
import java.util.ArrayList;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * OptionSet class -- This class records the available optionSets in which the optionSet
 * name and all the options will be included. In this class, the Option class will be used 
 * as an inner class so that it will not be accessed outside the OptionSet class. Protected modifier
 * is used to limit access.
 */

public class OptionSet implements Serializable{

    private static final long serialVersionUID = 1L;
    private ArrayList<Option> option;
    private String name;   

    
    protected class Option implements Serializable{
        private static final long serialVersionUID = 1L;
        private String name;
        private float price;
        
        /*
         * Option -- default constructor of Option class with no argument.
         */
        protected Option(){
        }
        
        /*
         * Option -- constructor of Option class with one argument which
         * initialize the name field of an Option object.
         */
        protected Option(String name)
        {
            setName(name);
        }
        
        /*
         * Option -- constructor of Option class with two arguments which
         * initialize the name and price fields of an Option object.
         */
        protected Option(String name, float price)
        {
            setName(name);
            setPrice(price);
        }
        
        /*
         * setName -- set the name field of the Option class.
         */
        protected void setName(String name)
        {
            this.name=name;
        }
        
        /*
         * setPrice -- set the price field of the Option class.
         */
        protected void setPrice(float price)
        {
            this.price=price;
        }
        
        /*
         * getName -- get the value of the name field of the Option class.
         */
        protected String getName()
        {
            return name;
        }
        
        /*
         * getPrice -- get the value of the price field of the Option class.
         */
        protected float getPrice()
        {
            return price;
        }
       
    }

    /*
     * OptionSet -- default constructor of OptionSet class with no argument.
     */
    protected OptionSet(){}
    
    /*
     * OptionSet -- constructor of OptionSet class with one argument which
     * initializes the name field of OptionSet class.
     */
    protected OptionSet(String name)
    {
        setName(name);
    }
    
    /*
     * OptionSet -- constructor of OptionSet class with two arguments which
     * initialize the name field of OptionSet class as well as initialize the 
     * option array using the size parameter.
     */
    protected OptionSet(String name, int size)
    {
        option=new ArrayList<Option>(size);
        for (int i=0;i<size;i++)
        {
            option.add(new Option());
        }
        setName(name);
    }
    
    /*
     * setName -- set the name field of the OptionSet class.
     */
    protected void setName(String name)
    {
       
        this.name=name;
    }
    
    /*
     * getName -- get the value of the name field of the OptionSet class.
     */
    protected String getName()
    {
        return name;
    }
    
    /*
     * getOpt -- get an Option using the index of the Option array.
     */
    protected Option getOpt(int index)
    {
        
        return option.get(index);
    }
    
    /*
     * setOptionName - set certain Option by index.
     */
    protected void setOptionName(int index, String name)
    {
        option.set(index, new Option(name));
    }
    
    /*
     * getArrList - get the option ArrayList.
     */
    protected ArrayList<Option> getArrList()
    {
        return option;
    }
    
    /*
     * setArrList - set certain element in the option ArrayList
     * by index.
     */
    protected void setArrList(int index,String name, float price)
    {
        option.set(index, new Option(name,price));
    }
    
    /*
     * getOptLen -- get the length of the Option ArrayList.
     */
    protected int getOptLen()
    {
        return option.size();
    }
    
    /*
     * findOptionInSet -- given a name of an Option, determined whether such 
     * optionSet can be found in the Option array.
     * If found, the index of the Option array which matches the given string name
     * will be returned.
     * If not found, -1 will be returned.
     * This method will be called by findOptionInAuto in Automotive class.
     */
    protected int findOptionInSet(String optName)
    {
        for (int i = 0; i < option.size(); i++)
        {
            if (option.get(i).getName().equals(optName))
            {
                return i;
            }
        }
        
        return -1;
          
    }
    
    /*
     * deleteOption -- set the Option referenced by index parameter to
     * be null for deleting.
     * This method will be called by deleteOption method in Automotive class.
     */
    protected void deleteOption(int index)
    {
        option.remove(index);
    }
    
    /*
     * printOptSet -- print out the information of the OptionSet.
     */
    
    protected StringBuffer printOptSet()
    {
        StringBuffer str=new StringBuffer();
        

        
        for (int i=0;i<getOptLen()-1;i++)
        {
            str.append(getOpt(i).getName());
            str.append("($");
            str.append(getOpt(i).getPrice());
            str.append(")");
            str.append("\n");
        }
        
        str.append(getOpt(getOptLen()-1).getName());
        str.append("($");
        str.append(getOpt(getOptLen()-1).getPrice());
        str.append(")\n");
        str.append("\n");
        
        return str;
    }  
}
