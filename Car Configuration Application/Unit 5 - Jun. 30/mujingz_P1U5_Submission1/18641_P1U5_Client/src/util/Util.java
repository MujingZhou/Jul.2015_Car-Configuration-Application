package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import exception.AutoException;
import model.Automobile;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * Util class -- This class has some common-used methods such as read and parse the text file,
 * serialization and deserialzation. Some exceotions are dealt with in splitAndSetOpt and 
 * buildAutoObject methods.
 */

public class Util {

    /*
     * splitAndSetOpt -- based on the keywords array, split the line read from
     * text file and set the relevant Options using these parsed strings. In
     * this method, customer defined exception - "Missing price for option" is
     * dealt with.
     */
    public void splitAndSetOpt(String[] keywords, String line, Automobile a1) {
        for (int i = 0; i < keywords.length; i++) {
            if (line.contains(keywords[i])) {
                String option[] = (line.substring(keywords[i].length()
                        + " - ".length())).split(", ");
                float price[] = new float[option.length];
                for (int j = 0; j < option.length; j++) {
                    int index = option[j].indexOf("(");

                    try {
                        String tmp = option[j].substring(index + "($".length(),
                                option[j].length() - 1);
                        if (tmp != null && !tmp.isEmpty()) {
                            price[j] = Float.valueOf(option[j].substring(index
                                    + "($".length(), option[j].length() - 1));

                        } else {
                            throw new AutoException(4);
                        }
                    } catch (AutoException ae) {
                        AutoException ae1 = new AutoException(4);
                        ae1.setErrorMsg("Missing price for option");
                        ae1.fix(4, a1);
                        price[j] = 100;
                    }

                    option[j] = option[j].substring(0, index);
                }
                a1.setOption(keywords[i], option, price);
            }
        }
    }

    /*
     * buildAutoObject -- read text file from the file of filename and construct
     * the Automotive object. Inner Options will be set calling the
     * splitAndSetOpt method. In this method, customer defined exception -
     * "Missing model name", "Missing base price" and "Text file not found" are
     * dealt with.
     */
    public void buildAutoObject(String filename, Automobile a1) {
        try {
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            String line;
            String modelName = new String();
            float basePrice = 0;
            int numOptionSets = 0;
            String optionSet[];
            line = buff.readLine();
            optionSet = (line.substring("Available Options: ".length()))
                    .split(", ");
            numOptionSets = optionSet.length;
            a1.initAuto(numOptionSets);
            a1.setOptionSet(optionSet);
            while ((line = buff.readLine()) != null) {

                if (line.contains("Model Name: ")) {
                    try {
                        String tmp = line.substring("Model Name: ".length());
                        if (tmp != null && !tmp.isEmpty()) {
                            modelName = line.substring("Model Name: ".length());
                            a1.setName(modelName);
                        } else {
                            throw new AutoException(3);
                        }
                    } catch (AutoException ae) {
                        AutoException ae1 = new AutoException(3);
                        ae1.setErrorMsg("Missing model name");
                        ae1.fix(3, a1);
                    }

                }

                else if (line.contains("Base Price: $")) {
                    try {
                        String tmp = line.substring("Base Price: $".length());
                        if (tmp != null && !tmp.isEmpty()) {
                            basePrice = Float.valueOf(line
                                    .substring("Base Price: $".length()));
                            a1.setBasePrice(basePrice);
                        } else {
                            throw new AutoException(2);
                        }
                    } catch (AutoException ae) {
                        AutoException ae1 = new AutoException(2);
                        ae1.setErrorMsg("Missing base price");
                        ae1.fix(2, a1);
                    }
                }

                else {
                    splitAndSetOpt(optionSet, line, a1);
                }
            }
            buff.close();
            file.close();
        }

        catch (FileNotFoundException foe) {
            AutoException ae1 = new AutoException(1);
            ae1.setErrorMsg("Text file not found");
            ae1.fix(1, a1);
        }

        catch (IOException e) {
            System.out.println();
        }

    }

    /*
     * serializeAuto -- serialize the object and write it to the path given by
     * filename parameter.
     */
    public void serializeAuto(Automobile a1, String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            out.writeObject(a1);
            out.close();
        } catch (Exception e) {
            System.out.print("Error: " + e);
            System.exit(1);
        }

    }

    /*
     * deserializeAuto -- deserialize the object from the path given by
     * filename.
     */
    public Automobile deserializeAuto(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    filename));
            Automobile a1 = (Automobile) in.readObject();
            in.close();
            return a1;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            System.exit(1);
        }

        return null;
    }

    /*
     * writeLogFile -- this method will be called to write information related
     * to the exception to a default file named "log.txt". Following information
     * will be appended after the previous ones.
     */
    public void writeLogFile(int errorNo, String errorMsg) {
        String writeStr = "Exception [errorno=" + errorNo + ", errormsg=\""
                + errorMsg + "]\"\n";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss -- ");
        String fileName = "log.txt";
        try {

            FileWriter out = new FileWriter(fileName, true);
            out.write(df.format(new Date()));
            out.write(writeStr);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public Automobile parseProperty(Properties props)
    {
        Automobile a1=new Automobile();
        String CarMake = props.getProperty("CarMake");
        if (!CarMake.equals(null))
        {
            a1.setMake(CarMake);
            String CarModel=props.getProperty("CarModel");
            a1.setName(CarModel);
            
            String Option1 =props.getProperty("Option1");
            String OptionValue1a=props.getProperty("OptionValue1a");
            String OptionValue1b=props.getProperty("OptionValue1b");
            
            String [] OptionValue1={OptionValue1a,OptionValue1b};
            float [] OptionPrice1={0,0};
            String [] OptionSet={Option1};
            a1.initAuto(1);
            a1.setOptionSet(OptionSet);
            a1.setOption(Option1, OptionValue1,OptionPrice1 );
                    
            
        }
        return a1;
    }
    
    
}
