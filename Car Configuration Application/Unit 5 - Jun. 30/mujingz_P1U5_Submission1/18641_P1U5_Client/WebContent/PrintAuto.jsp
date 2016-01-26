<% 
/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 30 2015
 * 
 * PrintAuto -- This .jsp file will get the finally configured Automobile object and 
 * display information about the choices made by the client.
 */%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor= "#FDF5E6">
	<center><H1>Here is what you selected: </H1></center>
	<%
		Automobile a1 = (Automobile) request.getAttribute("Automobile");
	%>
	<table border="1" align = "center">
		<tr>
			<td><%=a1.getName()%></td>
			<td>Base Price</td>
			<td><%=a1.getBasePrice()%></td>
		</tr>
		<%
			for (int i = 0; i < a1.getOptionSetLength(); i++) {
				String optionSetName = a1.getOptionSetName(i);
				String optionName = a1.getOptionChoice(optionSetName);
				float optionPrice = a1.getOptionChoicePrice(optionSetName);
		%>
		<tr>
			<td><%=optionSetName%></td>
			<td><%=optionName%></td>
			<td><%=optionPrice%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td>Total Cost</td>
			<td></td>
			<td>$<%=a1.getTotalPrice()%></td>
		</tr>
	</table>
	
</body>
</html>