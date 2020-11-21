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
<title>Edit Loan Application</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
<%!String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
				%> 
				<%
				ConnectionDao con = new ConnectionDao("jdbc:mysql://127.0.0.1:3306/eloan", "root", "root");
				LoanInfo ln  = con.getApplicationvaluesfromdb(AdminController.loanappnum);
				%>
<form action="admin?action=updatestatus" method="post">
			<table style="with: 50%">
				
				<%if(con.getApplicationvaluesfromdb(AdminController.loanappnum)==null){%>
<tr><td>No application exist to track</tr></td> 
<%}else { %>
				<tr>
					<td>Loan Name</td>
					<td><input type="text" name="lname" value= "Mortgage" /></td>
				</tr>
				
				<tr>
					<td>Loan Application Number</td>
					<td><%=ln.getApplno()%></td>
				</tr>
				<tr>
					<td>Loan Purpose</td>
					<td><input type="text" name="lpurpose" value =<%=ln.getPurpose()%>></td>
				</tr>
				<tr>
					<td>Business Structure</td>
					<td><input type="text" name="lbstruct" value =<%=ln.getBstructure()%>></td>
				</tr>
				<tr>
					<td>Loan Date</td>
					<td><%=ln.getDoa()%></td>
				</tr>
				<tr>
					<td>Loan Amount</td>
					<td><input type="text" name="lramt" value =<%=ln.getAmtrequest()%>></td>
				</tr>
				<tr>
					<td>Status</td>
					<td>
					<select name="lstatus">
						  <option>Select</option>
						   <option>Approve</option>
						  <option>Decline</option>
						   <option>Pending</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>PAN</td>
					<td><input type="text" name="pan" value =<%=ln.getPan()%>></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><input type="text" name="phone" value =<%=ln.getMobile()%>></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email" value =<%=ln.getEmail()%> ></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" value =<%=ln.getAddress()%>></td>
				</tr>
				
				<tr>
					<td>Employment Status</td>
					<td>
					<%String value =ln.getBindicator();
					System.out.print(value);%>
					<select name="empst">
						  
						  <%if(value.equals("Salaried")) {%>
						  <option>Select</option>
						   <option selected="selected">Salaried</option>
						  <option>Non-Salaried</option><%} %>
						  <%if(value.equals("Non-Salaried")) { %>
						  <option>Select</option>
						   <option >Salaried</option>
						  <option selected="selected">Non-Salaried</option><%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Update"> </div></td>
					
				</tr>
</table>
</form>
><%} %>
<hr/>
<jsp:include page="footer.jsp"/>

</body>
</html>