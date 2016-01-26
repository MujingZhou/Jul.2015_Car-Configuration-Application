package defaultsocket;

import java.io.*;
import java.net.*;
import java.util.Properties;

import adapter.BuildAuto;
import client.SelectCarOption;
import model.Automobile;
import model.Fleet;
import util.Util;

/* Name: Mujing Zhou
* Andrew ID: mujingz
* Date: Jun. 24 2015
* 
* DefaultSocketClient -- this class is a revised version of the default socket class given by 
* the professor. This class deals with the main operations from the user and will send those requests
* of the user to the server.
*/

public class DefaultSocketClient extends Thread implements
        SocketClientInterface, SocketClientConstants {
    private BufferedReader reader;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private InputStreamReader isr;
    private Socket sock;
    private String strHost;
    private int iPort;
    public Fleet fleet;
    
    /*
     * DefaultSocketClient -- default constructor of DefaultSocketClient class with two
     * arguments which initialize the port number and host address.
     */
    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }

    /*
     * run -- this starts the thread and implements the following three methods.
     */
    public void run() {
        if (openConnection()) {
            sendOutput();
            closeSession();
        }
    }

    /*
     * openConnection -- this opens the connection and initialize those Object 
     * streams.
     */
    public boolean openConnection() {

        try {
            sock = new Socket(strHost, iPort);
        } catch (IOException socketError) {
            socketError.printStackTrace();
            if (DEBUG)
                System.err.println("Unable to connect to " + strHost);
            return false;
        }

        try {
            out = new ObjectOutputStream(sock.getOutputStream());
            out.flush();
            in = new ObjectInputStream(sock.getInputStream());
            isr = new InputStreamReader(System.in);
            reader = new BufferedReader(isr);

        } catch (Exception e) {
            if (DEBUG)
                System.err
                        .println("Unable to obtain stream to/from " + strHost);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
     * handleUpload -- this method handles the case when the client chooses to upload a
     * file.
     */
    public void handleUpload() {
        System.out.println("Client: You have choose to upload a file.");
        try {
            out.writeObject(1);
            out.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        System.out.println("Client: Please enter the path of the file");

        String fileName = "";
        boolean flag=true;
        while(flag){
        try {
            fileName = reader.readLine();

            System.out.println("Client: FileName you have entered is : " + fileName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (fileName.contains("properties")) {
            flag=false;
            System.out.println("Client: You have chosen to upload properties format");
            try {
                
                Properties props = new Properties();
                FileInputStream in = new FileInputStream(fileName);
                props.load(in);
                Util util1 = new Util();
                Automobile a1 = util1.parseProperty(props);
                BuildAuto bu1=new BuildAuto();
                bu1.addAutomobile(a1);
                out.writeObject("Correct fileName");
                out.flush();
                System.out.println("Client: The properties file you have entered is correct, uploading......");
                out.writeObject("properties");
                out.flush();
                out.writeObject(props);
                out.flush();
            } catch (FileNotFoundException e) {
                flag=true;
                System.out.println("The properties file you have just enter can not be found, enter an existing file name");
                try {
                    
                    out.writeObject("Wrong fileName");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                continue;
                
            } catch (IOException e) {
                if (DEBUG)
                    System.out.println("Error writing to " + strHost);
            }

        }

        else if (fileName.contains("txt")) {
            flag=false;
            
            System.out.println("Client: You have chosen to upload txt format");
            try {
                out.writeObject("Correct fileName");
                out.flush();
                out.writeObject("txt");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            System.out
                    .println("Client: Waring! Only .properties or .txt file can be uploaded, try again!");
        }
        }
    }
    
    /*
     * handleConfigure -- this methods handles the case that the client choose to
     * configure a car.
     */
    public void handleConfigure(){
        System.out.println("Client: You have chosen to configure");
        try {
            out.writeObject(2);
            out.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        SelectCarOption sc1=new SelectCarOption();
        
        try {
            String[] modelNameServer = (String[]) in.readObject();
            
            sc1.printModel(modelNameServer);
            sc1.selectModelAndOption(reader, modelNameServer, out);
            Automobile a1 = (Automobile) in.readObject();
            
            sc1.printOptionAndPrice(a1, reader);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /*
     * handleQuit -- this methods handles the case that the client choose to
     * quit.
     */
    public void handleQuit(){
        System.out.println("Client: choose to exit");
        try {
            out.writeObject(3);
            out.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    
    /*
     * sendOutput -- this methods sends out some reminding information and reads the
     * choice from the user.
     */
    public boolean sendOutput() {
        while (true) {
            System.out.println("Client: Please enter the number for choice");
            System.out.println("1. Upload a file.");
            System.out.println("2. Configure a car. ");
            System.out.println("3. Quit");
            String choice = "";
            
            try {
                choice = reader.readLine();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (choice.equals("1")) {
                handleUpload();
            }

            if (choice.equals("2")) {
                handleConfigure();
            }

            if (choice.equals("3")) {
            	handleQuit();
                break;
                
            }           
        }
        return true;
    }
    
    /*
     * closeSession -- this methods closes all the streams and the socket.
     */   
    public void closeSession() {
        try {
            in=null;
            out = null;
            reader = null;
            sock.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    /*
     * setHost -- set the strHost field of this class.
     */
    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    /*
     * setPort -- set the iPort field of this class.
     */
    public void setPort(int iPort) {
        this.iPort = iPort;
    }

    /*
     * getHost -- get the value of strHost field of this class.
     */
    public String getHost() {
        return strHost;
    }

    /*
     * getPort -- get the value of iPort field of this class.
     */
    public int getPort() {
        return iPort;
    }

}
