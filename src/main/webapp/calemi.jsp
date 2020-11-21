<%@page import="com.iiht.evaluation.eloan.controller.AdminController"%>
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
<title>EMI for Application</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<hr/><div align=center>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
<%LoanInfo ln = AdminController.wln; %>

<div align="right"><a href="index.jsp">Logout</a></div>>			
<form action = "admin?action=process" align="center" method="post">
			<table style="with: 50%">
				
				
				<tr>
					<td>Loan Name</td>
					<td>"Mortgage"</td>
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
					<td>EMI</td>
					<td><%=AdminController.emi%></td>
				</tr>
				<tr>
					<td><input type="submit" value="OK"> </div></td>
				</tr>
</table>
</form>
<form action = "loanDetails.jsp"><tr>
					<td><input type="submit" value="Update Status"> </div></td>
</tr></form>
</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>