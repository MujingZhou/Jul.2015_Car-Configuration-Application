package servlet_client;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 30 2015
 * 
 * ServletOption -- This servlet will get the model name from previous menu, and ouput
 * this model name to the connected server. The server will return an Automobile object
 * based on this model name. After getting this Automobile object, this servlet will
 * prompt the drop-down menu for client to configure the car options. After the client
 * configure the car successfully, this servlet will again jump back to itself and try
 * to set all the choice fields of the car object. When all the choices are set, the 
 * final car object will be sent to the JSP named "PrintAuto.jsp" for display.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;

@WebServlet("/ServletOption")
public class ServletOption extends HttpServlet implements ServletInterface{
	private static final long serialVersionUID = 1L;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sock;
    private static Automobile a2;
	
    /*
     * doGet -- entrance of the Servlet program. In this doGet method, our Servlet will 
     * first connect to the server and write the model name get from previous drop-down
     * list back to the server. The server will send to our Servlet the corresponding 
     * Automobile object. A drop-down list of option set name and option name will be printed
     * out. To avoid to connect to the server again, our Servlet will jump to itself again
     * to get the choices made by the client and finaaly send the configured car to the jsp.
 
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String choice = request.getParameter("Model");
		
		if (choice!=null){		
		establishSession();
        System.out.println("connected to Servlet Option!");
				
		out.writeObject(5);
		out.flush();
		
		out.writeObject(choice);
		out.flush();
		Automobile a1=new Automobile();
			
		try {
			a1=(Automobile)in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		a2=a1;
		
		PrintWriter out1=servletHeader(response);
		
		out1.println("<HTML>\n" +
                "<HEAD><TITLE>HelloOption</TITLE></HEAD>\n");
		
		out1.println(
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + "Basic Car Choice" + "</H1>\n" +
                "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                "<TR>\n"); 
		out1.println("<TH>"+"Make/Model:" +
                "  <TH>"+a1.getName()+"<TR>\n");
		for (int i=0;i<a1.getOptionSetLength();i++){
		out1.println("<TH>"+a1.getOptionSetName(i) +
                "<TH>");
		out1.println("<form action=\"ServletOption\" method=\"GET\">"
				+ "<select name=\""+a1.getOptionSetName(i)+"\">");
		
			for (int j=0;j<a1.getOptionLength(i);j++){
				out1.println("<option value=\""+a1.getOptionName(a1.getOptionSetName(i), j)+"\">"+a1.getOptionName(a1.getOptionSetName(i), j)+"</option>");
			}
			out1.println("<TR>\n");
		}
		out1.println("</select>"
		        +"</TABLE>");
		out1.println("<H1 ALIGN=\"CENTER\">"+"<input type=\"submit\" value=\"Done\">");
		out1.println("</form></BODY></HTML>");
		}
		
		else {
			response.setContentType("text/html");
			for (int i=0;i<a2.getOptionSetLength();i++){
				String optionSetName=a2.getOptionSetName(i);
				String optionName=request.getParameter(optionSetName);
				a2.setOptionChoice(optionSetName ,optionName);
			}
			request.setAttribute("Automobile",a2);//getSession().
            String url = "/PrintAuto.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request,response);
		}
	}
	
	/*
     * servletHeader -- basic header information before outputting other information.
     */
	public void establishSession() throws UnknownHostException, IOException {
		sock = new Socket(InetAddress.getLocalHost().getHostAddress(), 4300);
		out = new ObjectOutputStream(sock.getOutputStream());
		out.flush();
		in = new ObjectInputStream(sock.getInputStream());
	}
	
	/*
     * establishSession -- establish the connection with the server and initialize 
     * the I/O streams with the socket.
     */
	public PrintWriter servletHeader(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out1;
		out1 = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		out1.println(docType);
		return out1;
	}
	
	/*
     * closeSession -- close the I/O streams and the socket.
     */
	public void closeSession() {
		
		in = null;
		out = null;
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}
}
