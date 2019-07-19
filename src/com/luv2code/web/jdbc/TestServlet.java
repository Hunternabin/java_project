package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	//define datasource/connection pool for injection
	@Resource(name = "web-student-trakar")  //must be exactly the same name as in context.xml
	
	private DataSource datasource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//step1: set up the print writer
	PrintWriter out = response.getWriter();
	response.setContentType("text/plain");
	
	//step2 : get the connection to the database
		Connection myconn = null;
		Statement mystm = null;
		ResultSet myRs = null;

	
		
		try {
			
//			myconn = dasource.getConnection();
			myconn = datasource.getConnection(); 
			//step3 : create the sql statement
			String sql = "select * from student";
			mystm = myconn.createStatement();
			
		
		
		
		
	//step 4: execute the query
	myRs= mystm.executeQuery(sql);
		
	//step5 : process the result
		while(myRs.next()) {
			
			String email = myRs.getString("email");
			out.println(email); //show data back to the browser
			
			
		}
		
		}
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	
	
	
	
	
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
