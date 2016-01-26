package servlet_client;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 30 2015
 * 
 * ServletModel -- After connecting to the server, this servlet will prompt out 
 * all the available car model names on the server side which are created after the client
 * succeeds to upload the properties file. A drop-down list will be displayed on this 
 * servlet and client can choose their desired car model. After the client clicked on the
 * "Done" button, the servlet will direct to another servlet named "ServletOption" which
 * corresponds to the ServletOption.java file.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletModel11")
public class ServletModel extends HttpServlet implements ServletInterface {

	private static final long serialVersionUID = 1L;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket sock;

	
	/*
     * doGet -- entrance of the Servlet program. In this doGet method, our Servlet will 
     * first connect to the server and then get all the available model names from the 
     * server. Then, drop-down list are created for user customization.
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		establishSession();

		System.out.println("connected to Servlet Model!");

		out.writeObject(4);
		out.flush();

		String[] modelName = null;

		
		try {
			modelName = (String[]) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintWriter out1 = servletHeader(response);

		closeSession();

		out1.println("<HTML>\n" + "<HEAD><TITLE>Welcome!</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<H1 ALIGN=\"CENTER\">" + "Please choose your model" + "</H1>"
				+ "<form ALIGN=\"CENTER\" name=\"info\"  action=\"ServletOption\" method=\"GET\"><br>"
				+ "<select name=\"Model\"></H1>");

		for (int i = 0; i < modelName.length; i++) {
			out1.println("<option value=" + modelName[i] + ">" + modelName[i] + "</option>");
		}

		out1.println("</select>" + "<input type=\"submit\" value=\"Done\">" + "</form>" + "</BODY></HTML>");

	}

	/*
     * servletHeader -- basic header information before outputting other information.
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
     * establishSession -- establish the connection with the server and initialize 
     * the I/O streams with the socket.
     */
	public void establishSession() throws UnknownHostException, IOException {
		sock = new Socket(InetAddress.getLocalHost().getHostAddress(), 4300);
		out = new ObjectOutputStream(sock.getOutputStream());
		out.flush();
		in = new ObjectInputStream(sock.getInputStream());
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
