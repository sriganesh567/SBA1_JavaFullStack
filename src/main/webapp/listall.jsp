<%@page import="com.iiht.evaluation.eloan.controller.AdminController"%>
<%@page import="com.iiht.evaluation.eloan.dao.ConnectionDao"%>

<%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<hr/>

<%
int itemsnumber = AdminController.aln.size();

%>

<div align="right"><a href="index.jsp">Logout</a></div>>			
<form>
<table style="with: 50%" border =1>
	
	<tr>
		<td>Employment Indicator</td>
		<td>Email</td>
		<td>Mobile</td>
		<td>Application Number</td>
		<td>Name</td>
		<td>Requested Amount</td>
		<td>Date of Application</td>
		<td>Business Structure</td>
		<td>Purpose</td>
		<td>Status</td>
		<td>Address</td>
		<td>Uid</td>
		<td>PAN</td>
	</tr>
	
<%for(int i=0; i<itemsnumber;i++){%>
<tr>
		<td><%=AdminController.aln.get(i).getBindicator() %></td>
		<td><%=AdminController.aln.get(i).getEmail() %></td>
		<td><%=AdminController.aln.get(i).getMobile() %></td>
		<td><%=AdminController.aln.get(i).getApplno() %></td>
		<td><%=AdminController.aln.get(i).getName() %></td>
		<td><%=AdminController.aln.get(i).getAmtrequest()%></td>
		<td><%=AdminController.aln.get(i).getDoa() %></td>
		<td><%=AdminController.aln.get(i).getBstructure() %></td>
		<td><%=AdminController.aln.get(i).getPurpose() %></td>
		<td><%=AdminController.aln.get(i).getStatus() %></td>
		<td><%=AdminController.aln.get(i).getAddress()%></td>
		<td><%=AdminController.aln.get(i).getUid()%></td>
		<td><%=AdminController.aln.get(i).getPan() %></td>
		
</tr>
<%} %>	
</table>
</form>
<hr/>

<jsp:include page="footer.jsp"/>
</body>
</html>