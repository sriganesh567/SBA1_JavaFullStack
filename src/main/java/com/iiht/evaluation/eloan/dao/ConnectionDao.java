package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.controller.UserController;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.Registration;
import com.iiht.evaluation.eloan.model.User;

public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private static Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	public int loginVerification(String username, String Password) throws SQLException {
		jdbcConnection = connect();
		String stmtstr = "SELECT count(*) FROM registrations where uid = '"+username+ "'and pwd = '"+Password+"'";
		
		Statement st = jdbcConnection.createStatement();
		ResultSet rs = st.executeQuery(stmtstr); // execute the query, and get a java resultset

		int result = 0;
		
		if(rs.next()) {
			if(!(rs.getInt(1) == 0))
				{result =1;}}
		jdbcConnection = null;
		disconnect();
		return result;
   }
	public int maxapplications() throws SQLException {
		jdbcConnection = connect();
		String maxapp = "SELECT MAX(APPLICATIONID) FROM applications";
		int result= 100000;
		Statement st = jdbcConnection.createStatement();
		ResultSet rs = st.executeQuery(maxapp); // execute the query, and get a java resultset
		if(rs.next()) {
			if(rs.getInt(1) == 0)
				{result = result;}
			else {
			result = rs.getInt(1)+1;}
		}
		jdbcConnection = null;
		disconnect();
		return result;
   }

	public void setApplicationvaluestodb(LoanInfo ln) throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "INSERT INTO applications(Bindicator ,email ,mobile ,APPLICATIONID , name , Amount , DOA, BSTRUCT, PURPOSE , STATUS , ADDRESS, UID, PAN) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_LN_QRY);){
		pst.setString(1, ln.getBindicator());
		pst.setString(2, ln.getEmail());
		pst.setString(3, ln.getMobile());
		pst.setInt(4, Integer.parseInt(ln.getApplno()));
		pst.setString(5, ln.getName());
		pst.setString(6,((Integer)ln.getAmtrequest()).toString());
		pst.setString(7, ln.getDoa());
		pst.setString(8, ln.getBstructure());
		pst.setString(9, ln.getPurpose());
		pst.setString(10, ln.getStatus());
		pst.setString(11, ln.getAddress());
		pst.setString(12,ln.getUid());
		pst.setString(13, ln.getPan());

		pst.executeUpdate();}
		catch (SQLException exp) {
		}
		finally {
			jdbcConnection = null;
			disconnect();
		}
	}
	public void updateApplicationvaluestodb(LoanInfo ln) throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "Update applications SET Bindicator =?,email =? ,mobile =?, name =?, Amount=? , DOA=?, BSTRUCT=?, PURPOSE=? , STATUS=? , ADDRESS=?, PAN=? where APPLICATIONID=?";
		try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_LN_QRY);){
		pst.setString(1, ln.getBindicator());
		pst.setString(2, ln.getEmail());
		pst.setString(3, ln.getMobile());
		pst.setString(4, ln.getName());
		pst.setString(5,((Integer)ln.getAmtrequest()).toString());
		pst.setString(6, ln.getDoa());
		pst.setString(7, ln.getBstructure());
		pst.setString(8, ln.getPurpose());
		pst.setString(9, ln.getStatus());
		pst.setString(10, ln.getAddress());
		pst.setString(11, ln.getPan());
		pst.setString(12,ln.getApplno());
		
		pst.executeUpdate();}
		catch (SQLException exp) {
		}
		finally {
			jdbcConnection = null;
			disconnect();
		}
	}
	public void updatestatus(String status, int loannumber) throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "Update applications SET STATUS=? where APPLICATIONID=?";
		try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_LN_QRY);){
		pst.setString(1, status);
		pst.setInt(2, loannumber);
		
		
		pst.executeUpdate();}
		catch (SQLException exp) {
		}
		finally {
			jdbcConnection = null;
			disconnect();
		}
	}
	public LoanInfo getApplicationvaluesfromdb(String loginid) throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "select Bindicator ,email ,mobile ,APPLICATIONID , name , Amount , DOA, BSTRUCT, PURPOSE , STATUS , ADDRESS, UID, PAN from applications where uid ='"+UserController.loginId+"'";
		LoanInfo result,ln;
			Statement st = jdbcConnection.createStatement();
			ResultSet rs = st.executeQuery(INS_LN_QRY); // execute the query, and get a java resultset
			if(rs.next()) {
				ln = new LoanInfo();
				ln.setBindicator(rs.getString(1));
				ln.setEmail(rs.getString(2));
				ln.setMobile(rs.getString(3));
				ln.setApplno(((Integer)rs.getInt(4)).toString());
				ln.setName(rs.getString(5));
				ln.setAmtrequest(Integer.parseInt(rs.getString(6)));
				ln.setDoa(rs.getString(7));
				ln.setBstructure(rs.getString(8));
				ln.setPurpose(rs.getString(9));
				ln.setStatus(rs.getString(10));
				ln.setAddress(rs.getString(11));
				ln.setUid(rs.getString(12));
				ln.setPan(rs.getString(13));

				result = ln;
				}
				else {
				result = null;}

			jdbcConnection = null;
				disconnect();
				return result;
		
	}
	public LoanInfo getApplicationvaluesfromdb(int loanappnum) throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "select Bindicator ,email ,mobile ,APPLICATIONID , name , Amount , DOA, BSTRUCT, PURPOSE , STATUS , ADDRESS, UID, PAN from applications where APPLICATIONID ='"+loanappnum+"'";
		LoanInfo result,ln;
			Statement st = jdbcConnection.createStatement();
			ResultSet rs = st.executeQuery(INS_LN_QRY); // execute the query, and get a java resultset
			if(rs.next()) {
				ln = new LoanInfo();
				ln.setBindicator(rs.getString(1));
				ln.setEmail(rs.getString(2));
				ln.setMobile(rs.getString(3));
				ln.setApplno(((Integer)rs.getInt(4)).toString());
				ln.setName(rs.getString(5));
				ln.setAmtrequest(Integer.parseInt(rs.getString(6)));
				ln.setDoa(rs.getString(7));
				ln.setBstructure(rs.getString(8));
				ln.setPurpose(rs.getString(9));
				ln.setStatus(rs.getString(10));
				ln.setAddress(rs.getString(11));
				ln.setUid(rs.getString(12));
				ln.setPan(rs.getString(13));

				result = ln;
				}
				else {
				result = null;}

			jdbcConnection = null;
				disconnect();
				return result;
		
	}
	public void setRegistrationvaluestodb(Registration Rn) throws SQLException {
		jdbcConnection = connect();
		
		final String INS_LN_QRY = "INSERT INTO registrations(name ,uid ,pwd) VALUES(?,?,?)";
		try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_LN_QRY);){
		pst.setString(1, Rn.getName());
		pst.setString(2, Rn.getUsername());
		pst.setString(3, Rn.getPassword());	
		pst.executeUpdate();}
		catch (SQLException exp) {
		}

		finally {
			jdbcConnection = null;
					disconnect();
		}
	}

	public List<LoanInfo> getAllLoansfromdb() throws SQLException {
		jdbcConnection = connect();
		final String INS_LN_QRY = "select Bindicator ,email ,mobile ,APPLICATIONID , name , Amount , DOA, BSTRUCT, PURPOSE , STATUS , ADDRESS, UID, PAN from applications where STATUS = 'Pending'";
		List<LoanInfo> aln = new ArrayList<LoanInfo>();		
		LoanInfo ln;
		
			Statement st = jdbcConnection.createStatement();
			ResultSet rs = st.executeQuery(INS_LN_QRY); // execute the query, and get a java resultset
			int i=0;
			while(rs.next()) {
				ln = new LoanInfo();
				ln.setBindicator(rs.getString(1));
				ln.setEmail(rs.getString(2));
				ln.setMobile(rs.getString(3));
				ln.setApplno(((Integer)rs.getInt(4)).toString());
				ln.setName(rs.getString(5));
				ln.setAmtrequest(Integer.parseInt(rs.getString(6)));
				ln.setDoa(rs.getString(7));
				ln.setBstructure(rs.getString(8));
				ln.setPurpose(rs.getString(9));
				ln.setStatus(rs.getString(10));
				ln.setAddress(rs.getString(11));
				ln.setUid(rs.getString(12));
				ln.setPan(rs.getString(13));
				aln.add(ln);
				}
			

			jdbcConnection = null;
				disconnect();
				return aln;
		
	}	
	// put the relevant DAO methods here..
}
