package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name = "web-student-trakar")
	
	private DataSource datasource;
	
	
	
	@Override
	public void init() throws ServletException {
	
		super.init();
	
	
		//create our student dbutil  and pass the connection pool/datasource
		
		
		try {
			
			studentDbUtil = new StudentDbUtil(datasource);
			
			
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
		
		
	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	try {	
	
		//read the command parameter
		String thecommand = request.getParameter("command");
		
		//if the command is missing,then default the listing students
		if(thecommand==null) {
			thecommand = "LIST";
		}
		
		//route the  appropriate method
		
		switch(thecommand) {
		
		case "LIST":
		//list the student in mvc fashion
		listStudent(request,response);
		break;
		
		case "ADD":
		addstudent(request,response);
		break;
		case "LOAD":
			loadStudent(request,response);
			
		case "UPDATE":
			updateStudent(request,response);
		case "DELETE":
			deleteStudent(request,response);
		default:
			listStudent(request,response);
		
		}
		
		
	}
	
	catch(Exception e) {
		throw new  ServletException(e);
	}
	}




	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		//read the student id from the form data
		String theStudentId = request.getParameter("studentId");
		//delete student fromt the  database
		
		studentDbUtil.deleteStudent("theStudent");
		//send them to back to "list" students page
	}




	private void updateStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//read student info from the form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		//create a new student object
		Student theStudent = new Student(firstName, lastName, email);
		//perform object on database
		studentDbUtil.updateStudent(theStudent);
		//send back to the 'list' student
		listStudent(request,response);
		
	}








	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		//get student from the database(dbutil)
		
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		//place student in the request attribute
		request.setAttribute("THE-STUDENT", theStudent);
		
		//send to jsp page: update-student-form.jsp
	RequestDispatcher dispatcher =request.getRequestDispatcher("/update-student-form.jsp");
	
	}
	




	private void addstudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//read student info from form data
		
		String firstName = request.getParameter("firstname"); //uta add-student-jsp ko form ma bhako name line ho 
		String lastName = request.getParameter("email");
		String email = request.getParameter("email");
		
		
		//create the student object
		
		Student thestudent = new Student(firstName, lastName, email);
		//add the student to the database
		
		studentDbUtil.addStudent(thestudent);
		
		//sent back to the main page(the student list)
		
		listStudent(request, response);
		
	}


	//upto this ==================for add-student-form.jsp====================
	

	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get student from the list
		List<Student> student = studentDbUtil.getStudent();
	
		//add student to the request
		request.setAttribute("STUDNET_LIST", student);
		
		
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-student.jsp");
		dispatcher.forward(request, response);
	
	
		
	}
	
	

}
