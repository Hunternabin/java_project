package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

//import com.mysql.jdbc.PreparedStatement;

public class StudentDbUtil {
	
	private DataSource datasource;
	/**
	 * DataSource and the DriverManager are the two basic ways to connect to a database. 
	 * The DriverManager is older facility, DataSource is newer. 
	 *  Datasource ->improves application performance as connections are not created/closed within a class, they are managed by the application server and can be fetched while at runtime. 
it provides a facility creating a pool of connections 
helpful for enterprise applications
	 * @param theDatasource
	 */
	
	public StudentDbUtil(DataSource theDatasource) {
		datasource = theDatasource;
		
		
	}
	
	public List<Student> getStudent() {
		
		List<Student> students = new ArrayList<Student>();
		Connection myconn = null;
		Statement stm = null;
		ResultSet rslt = null;
		
		
		try {
			
		
		//step 1 : getconnection
//			Class.forName("com.jdbc.mysql.Driver");
		myconn = datasource.getConnection();
//			myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web-student-trakar","root","");
		//step2 :  create sql stmt
		String sql = "select * from student";
		//step 3: process result
		stm = myconn.createStatement();
		//step 4: executeQuery()
		rslt = stm.executeQuery(sql);
		while(rslt.next()) {
			
			//retrive data from the resultset
			int id = rslt.getInt("id");
			String firstname= rslt.getString("firstname");
			String lastname = rslt.getString("lastname");
			String email = rslt.getString("email");
			//create the new student object
			Student tempstudent = new Student(id,firstname, lastname, email);
			
			//add it to the list of student
			students.add(tempstudent);
		}
		
		myconn.close();
		rslt.close();
		stm.close();
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return students;
		
		
	}

	public void addStudent(Student thestudent) throws IOException {
		//auto generated
		
		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;
		
		
		try {
			
		//get db connection 
			myConn = datasource.getConnection();
		//create sql for insert
		String sql = "insert into student"
				+ "(firstname,lastname,email)"
				+ "values(?,?,?)";
		
		myStmt = myConn.prepareStatement(sql);
		//set parameter value for the student
		myStmt.setString(1,thestudent.getFirstName() );
		myStmt.setString(2,thestudent.getLastName());
		myStmt.setString(3,thestudent.getEmail());
		
		//execute the sql insert
		
		myStmt.execute();
		
		JOptionPane.showMessageDialog(null, "datainserted into database succcefully");
		//clean up the JDBC objects
		myConn.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
			}

	public Student getStudent(String theStudentId)throws Exception {

		Student theStudent = null;
		
		Connection 	myConn = null;
		java.sql.PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			
			
			//convert student id to int
			
			studentId = Integer.parseInt( theStudentId);
			//get connection to database
			myConn = datasource.getConnection();
			//create sql to get selected student
			
			//create prepare statement
			String sql = "select * from studnent whereid = ?";
			
			//set params
			myStmt = myConn.prepareStatement(sql);
			//execute statement
			myStmt.setInt(1,studentId);
			//execute statement
			myRs  = myStmt.executeQuery();
			//retrieve data from resultset
			
			if(myRs.next()) {
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String email = myRs.getString("email");
			
				//use the studentId during construction 
				theStudent = new Student(firstName, lastName, email);
			
			}
			else {
				throw new Exception("didnot find student id : "+studentId);
			}
			
			return theStudent;
		}
		
		finally {
			//clean up 	the connection
			myConn.close();
			
		}
		
		
	}

	
	public void updateStudent(Student theStudent)throws Exception {

		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;
		
		try {
			//get the db connection
			myConn = datasource.getConnection();
			//create the sql statement
			String sql = "update student "+"set firstname = ?, lastname = ?,email = ? "+ "where id = ?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			//set param
			myStmt.setString(1, theStudent.getFirstName()); //getter() of student
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			//execute the sql  statement
			myStmt.execute();
		}
		finally {
			
			//close the connection
			myConn.close();
		}
		
		
		
		
	}

	public void deleteStudent(String theStudentId)throws Exception {
		
		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;
		
		try {
			//convert student id to int
			int studentId = Integer.parseInt("theStudentId");
			//get connection to the database
			myConn = datasource.getConnection();
			//create sql to delete student
			String sql = "delete from student where id = ?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			//set param
			myStmt.setInt(1,studentId);
			//execute sql statement
			myStmt.execute();
			
			
		}
		
		finally {
			
			myConn.close();
			
		}
	}
}
