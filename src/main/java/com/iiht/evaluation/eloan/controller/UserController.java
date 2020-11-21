package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.Registration;
import com.iiht.evaluation.eloan.model.User;



@WebServlet({"/validate","/registeruser","/registernewuser","/placeloan","/application1","/editLoanProcess","/register","/application","/trackloan","/editloan","/displaystatus"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String loginId = "";
	ConnectionDao con = new ConnectionDao("jdbc:mysql://127.0.0.1:3306/eloan", "root", "root");
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String action = request.getParameter("action");
		String viewName = "";
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		loginId = request.getParameter("loginid").toString();
		String password = request.getParameter("password").toString();
		User user = new User();
		user.setUsername(loginId);
		user.setPassword(password);
		
			if((con.loginVerification(loginId, password)) == 1) 
				{ view =  "userhome.jsp";}
			else if(loginId.equals("admin") && password.equals("admin")) {
				 view =  "adminhome1.jsp";}		
		return view;
		
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		String view = "";
		if(con.getApplicationvaluesfromdb(loginId) == null){
			 
		  LoanInfo ln = new LoanInfo();
		  ln.setApplno(request.getParameter("lnum").toString());		  
		  ln.setAmtrequest(Integer.parseInt(request.getParameter("lramt")));
		  ln.setDoa(request.getParameter("ldate").toString());
		  ln.setBstructure(request.getParameter("lbstruct").toString());
		  ln.setPurpose(request.getParameter("lpurpose").toString());
		  ln.setBindicator(request.getParameter("empst").toString());
		  ln.setEmail(request.getParameter("email").toString());
		  ln.setMobile(request.getParameter("phone").toString());
		  ln.setStatus("Pending");
		  ln.setName(request.getParameter("lname").toString());
		  ln.setAddress(request.getParameter("address"));
		  ln.setUid(loginId);
		  ln.setPan(request.getParameter("pan"));
		  con.setApplicationvaluestodb(ln);
			if(ln.getBindicator().equals("")||ln.getEmail().equals("")||ln.getMobile().equals("")||ln.getApplno().equals("")||ln.getAmtrequest() == 0||ln.getDoa().equals("")||
					ln.getBstructure().equals("")||ln.getPurpose().equals("") ||
					ln.getStatus().equals("")||ln.getAddress().equals("") || ln.getUid().equals("")||ln.getPan().equals("")) 
				{ view =  "errorPage.jsp";}
			else {
				 view =  "userhome.jsp";}	}
		else {
			
			view = "application.jsp";
		}
		return view;
			
	
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return "application.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */

		String view = "";		  
		  LoanInfo ln = new LoanInfo();
		  ln.setApplno(con.getApplicationvaluesfromdb(loginId).getApplno());		  
		  ln.setAmtrequest(Integer.parseInt(request.getParameter("lramt")));
		  ln.setDoa(con.getApplicationvaluesfromdb(loginId).getDoa());
		  ln.setBstructure(request.getParameter("lbstruct").toString());
		  ln.setPurpose(request.getParameter("lpurpose").toString());
		  ln.setBindicator(request.getParameter("empst").toString());
		  ln.setEmail(request.getParameter("email").toString());
		  ln.setMobile(request.getParameter("phone").toString());
		  ln.setStatus("Pending");
		  ln.setName(request.getParameter("lname").toString());
		  ln.setAddress(request.getParameter("address"));
		  ln.setUid(loginId);
		  ln.setPan(request.getParameter("pan"));
		  con.updateApplicationvaluestodb(ln);
			if(ln.getBindicator().equals("")||ln.getEmail().equals("")||ln.getMobile().equals("")||ln.getAmtrequest() == 0||
					ln.getBstructure().equals("")||ln.getPurpose().equals("") || 
			ln.getStatus().equals("")||ln.getAddress().equals("") || ln.getUid().equals("")||ln.getPan().equals("")) 
				{ view =  "errorPage.jsp";}
			else {
				 view =  "userhome.jsp";}
		return view;

	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		
		  return "newuserui.jsp";
			
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Registration ln = new Registration();
		ln.setUsername(request.getParameter("uid").toString());		  
		  ln.setPassword(request.getParameter("pwd"));
		  ln.setName(request.getParameter("name").toString());
		  con.setRegistrationvaluestodb(ln);
		  return "index.jsp"; 
		}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return "register.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return "userhome.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		return "editloan.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		
		return "traackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */

		return "traackloan.jsp";
	}
}
