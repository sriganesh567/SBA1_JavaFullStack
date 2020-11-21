<%@page import="com.iiht.evaluation.eloan.controller.UserController"%>
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
<title>Loan Application Form</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/><hr/>

<div align="right"><a href="index.jsp">Logout</a></div>
<form action="placeloan" method="post">

<%!String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
					
					%> 
				<%
				ConnectionDao con = new ConnectionDao("jdbc:mysql://127.0.0.1:3306/eloan", "root", "root");
				int appnum  = con.maxapplications();
					
				%>
<%if(!(con.getApplicationvaluesfromdb(UserController.loginId)==null)){%>
<tr><td>application already exist</tr></td> 
<%}%>
			<table style="with: 50%">
				<tr>
					<td>Loan Name</td>
					<td><input type="text" name="lname" value= "Mortgage" /></td>
				</tr>
				
				<tr>
					<td>Loan Application Number</td>
					<td><input type="text" name="lnum" value =<%=appnum%>></td>
				</tr>
				<tr>
					<td>Loan Purpose</td>
					<td><input type="text" name="lpurpose" /></td>
				</tr>
				<tr>
					<td>Business Structure</td>
					<td><input type="text" name="lbstruct" /></td>
				</tr>
				<tr>
					<td>Loan Date</td>
					<td><input type="text" name="ldate" value =<%=date%>/></td>
				</tr>
				<tr>
					<td>Loan Amount</td>
					<td><input type="text" name="lramt" /></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><input type="text" name="phone" /></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" /></td>
				</tr>
				
				<tr>
					<td>Employment Status</td>
					<td>
					<select name="empst">
						  <option>Select</option>
						   <option>Salaried</option>
						  <option>Non- Salaried</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>PAN</td>
					<td><input type="text" name="pan" /></td>
				</tr>
				<%if(con.getApplicationvaluesfromdb(UserController.loginId)==null){%>
<tr>
					<td><input type="submit" value="Register"> </div></td>
					
				</tr>
<%}%>
				
</table>
</form>
<hr/>
<jsp:include page="footer.jsp"/>
</body>	
</html>