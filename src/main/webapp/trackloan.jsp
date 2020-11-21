<%@page import="com.iiht.evaluation.eloan.controller.UserController"%>
<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@page import="com.iiht.evaluation.eloan.dao.ConnectionDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->
	<jsp:include page="header.jsp"/>
<hr/>
	
<%!String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
				%> 
				<%
				ConnectionDao con = new ConnectionDao("jdbc:mysql://127.0.0.1:3306/eloan", "root", "root");
				LoanInfo ln  = con.getApplicationvaluesfromdb(UserController.loginId);
				%>
<form action="displaystatus" method="post">
			<table style="with: 50%">
			<%if(con.getApplicationvaluesfromdb(UserController.loginId)==null){%>
<tr><td>No application exist to track</tr></td> 
<%}else { %>

				<tr>
					<td>Loan Name</td>
					<td>Mortgage</td>
				</tr>
				
				<tr>
					<td>Loan Application Number</td>
					<td><%=ln.getApplno()%></td>
				</tr>
				<tr>
					<td>Loan Purpose</td>
					<td><%=ln.getPurpose()%></td>
				</tr>
				<tr>
					<td>Business Structure</td>
					<td><%=ln.getBstructure()%></td>
				</tr>
				<tr>
					<td>Loan Date</td>
					<td><%=ln.getDoa()%></td>
				</tr>
				<tr>
					<td>Loan Amount</td>
					<td><%=ln.getAmtrequest()%></td>
				</tr>
				<tr>
					<td>Loan Status</td>
					<td><%=ln.getStatus()%></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><%=ln.getMobile()%></td>
				</tr>
				<tr>
					<td>email</td>
					<td><%=ln.getEmail()%></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><%=ln.getAddress()%></td>
				</tr>
				
				<tr>
					<td>PAN</td>
					<td><%=ln.getPan()%></td>
				</tr>
				<tr>
					<td><input type="submit" value="OK"> </div></td>
					
				</tr>
				</table></form><%} %>

<hr/><jsp:include page="header.jsp"/>
				
</body>
</html>