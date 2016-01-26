package servlet_client;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 30 2015
 * 
 * ServletInterface -- This is an interface of four methods which will be used by the two 
 * client defined Servlet. These methods defined in this interface are some common methods
 * which could also be implemented by similar Servlet programs.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletInterface {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	public void closeSession();
	
	public void establishSession() throws UnknownHostException, IOException;
	
	public PrintWriter servletHeader(HttpServletResponse response) throws IOException;
	
	
}
