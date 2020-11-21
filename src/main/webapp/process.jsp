<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get EMI</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<hr/>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
<div align="right"><a href="index.jsp">Logout</a></div>
	
	<form action="admin?action=callemi" method ="post">
	<table style="with: 50%">
	<tr><td>Application Number</td><td><input type ="text" name ="loanappnum"><td><input type ="Submit" value ="Calculate EMI"></td></tr>
	</table>
	</form>

<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>