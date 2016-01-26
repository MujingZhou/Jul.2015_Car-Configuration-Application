package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import defaultsocket.DefaultSocketClient;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 24 2015
 * 
 * CarModelOptionsIO -- this class initialize a DefaultSocketClient in a given IP address
 * on a given port number and start the thread.
 */

public class CarModelOptionsIO {
    DefaultSocketClient clientSocketClient;
    
    /*
     * CarModelOptionsIO -- default constructor of CarModelOptionsIO class with no
     * argument.
     */
    public CarModelOptionsIO()   
    {
        try {
			clientSocketClient=new DefaultSocketClient(InetAddress.getLocalHost().getHostAddress(),4300);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
    }
    
    /*
     * begin -- this method starts the thread.
     */
    public void begin(){
        clientSocketClient.start();
    }
}
