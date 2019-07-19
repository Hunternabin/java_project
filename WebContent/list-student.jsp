<!--  
<%@ page import = "java.util.*,com.luv2code.web.jdbc.*"%>
-->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<html>

<head>
	<title>Student Tracker app</title>
	
	<link type = "text/css" rel = "stylesheet" href = "css/style.css">


</head>


<body>




<div id = "wrapper">
	<div id = "header">
	
	<h1>Tribhuban University</h1>
	</div>
	</div>
	<div id = "container">
	<div id = "content">
	
	<!-- new button -->
	
	<input type = "button" value = "Add Student"
	onclick = "window.location.href = 'add-student-form.jsp'; return false;"
	class = "add-student-button">
	
	
	
	
	<table>
	<tr>
	<th>FirstName</th>
	<th>LastName</th>
	<th>Email</th>
	<th>Action</th>
	
	</tr>
<c:forEach var = "tempstudent" items = "${STUDENT_LIST}">	


	<!-- set up the link for each student -->
	
	<c:url var = "templink" value = "StudentControllerServlet">
	<c:param name="command" value = "LOAD"></c:param>
	<c:param name="studentId" value = "${tempStudent.id}"></c:param>
	</c:url>
	
	<!-- set up the link to delete the student -->
	
	<c:url var = "deletelink" value = "StudentControllerServlet">
	<c:param name="command" value = "DELETE"></c:param>
	<c:param name="studentId" value = "${tempStudent.id}"></c:param>
	</c:url>
	
		<tr>
		<td> ${tempstudent.firstName} </td>
		
		<td> ${tempstudent.lastName} </td> <!-- calling gettermethod -->
		<td> ${tempstudent.getemail} </td>
		<td><a href = "${tempLink}">Update</a>
		<a href = "${deletelink }"
		onclick ="if(!(confirm('Are you sure want to delete this student ?'))) return false"
		>Delete</a>
		
		
		</td>
		
		</tr>
	
	</c:forEach>
	 
	
	
	</table>
	
	</div>
	</div>


	
	</div>


</div>

</body>
</html>



