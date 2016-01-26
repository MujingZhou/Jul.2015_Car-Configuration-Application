package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Automobile;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 24 2015
 * 
 * SelectCarOption -- this class deals with the methods which are called when
 * the user selects to configure the car.
 */

public class SelectCarOption {
    
    /*
     * printModel -- given an array of the model name, print all these names on the client
     * side.
     */
    public void printModel(String modelNameServer[]) {
        System.out.println("Client: Car info from server is listed as : ");
        for (int i = 0; i < modelNameServer.length; i++) {
            System.out.println("Model " + (i + 1) + " : " + modelNameServer[i]);
        }
        System.out.println();
    }

    /*
     * printOptionAndPrice -- given an object of the Automobile, allows the user 
     * to select a model.
     */
    public void printOptionAndPrice(Automobile a1, BufferedReader reader){
        for (int i = 0; i < a1.getOptionSetLength(); i++) {
            a1.printOptionSetForChoice(i);
            int oriOptionNum=a1.getOptionLength(i);
            System.out
                    .println("Please enter the number for selection");
            boolean flag=false;
            int optionNum=0;
            while(flag==false){
            
            try {
                optionNum = Integer.valueOf(reader.readLine());
                if (optionNum>=1&&optionNum<=oriOptionNum){
                    flag=true;
                }
                else {
                    System.out.println("The option index you have entered is out of bound, please enter again");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            }
            a1.setOptionChoice(a1.getOptionSetName(i), a1
                    .getOptionName(a1.getOptionSetName(i),
                            optionNum - 1));
        }
        System.out.println();
        System.out.println();
        System.out.println("Information after selection is: ");
        System.out.println("Model Name: "+a1.getName());
        System.out.println("Make Name: "+a1.getMake());
        for(int i=0;i<a1.getOptionSetLength();i++)
        {
            a1.printOptionSetForChoice(i);
        }
        System.out.println("Total price is " + a1.getTotalPrice());
        System.out.println();
        System.out.println();
    }
    
    /*
     * selectModelAndOption -- allows the user to enter its respective options 
     * and display the selected options together with the total price after choosing.
     */
    public void selectModelAndOption(BufferedReader reader, String []modelNameServer,ObjectOutputStream out) {
        System.out.println("Please enter the model name you wish to configure");
        String modelNameClient="";
        try {
            modelNameClient = reader.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        boolean flag = false;
        
        
        
        for (int i = 0; i < modelNameServer.length; i++) {
            if (modelNameClient.equals(modelNameServer[i])) {
                flag = true;
                try {
                    out.writeObject(modelNameClient);
                    out.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        while (flag == false) {
            System.out
                    .println("The model name you entered does not exist, please enter again");
            try {
                modelNameClient = reader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i < modelNameServer.length; i++) {
                if (modelNameClient.equals(modelNameServer[i])) {
                    flag = true;
                    try {
                        out.writeObject(modelNameClient);
                        out.flush();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
