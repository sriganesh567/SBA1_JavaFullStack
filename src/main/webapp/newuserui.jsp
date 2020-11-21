<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->
	<jsp:include page="header.jsp"/>
<hr/>
	
<div align="right"><a href="index.jsp">Logout</a></div>
	<form action="registernewuser" method="post">
			<table style="with: 50%">
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>User ID</td>
					<td><input type="text" name="uid" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="pwd" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Register"> </div></td>
					
				</tr>
</table>
</form>
<hr/>
<jsp:include page="footer.jsp"/>


</body>
</html>