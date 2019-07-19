<!DOCTYPE html>
<html>

<head>

	<link type = "text/css" rel ="stylesheet" href= "css/add-student-style.css">
<!--  <link type = "text/css" rel ="stylesheet" href= "css/style.css"> -->

</head>

<body>
<div id = "wrapper">
<div id = "header">
<h2> Tribhuban University</h2>


</div>
</div>

	<div id = "container">
	<h3>Add Student</h3>
	</div>

	<form action="StudentControllerServlet" method="get">
	
	<input type = "hidden" name = "command" value = "ADD">
	<table>
	<tbody>
	
	<tr>
	<td>FirstName:</td>
	<td><input type = "text" name = "firstname"></td> 
	</tr>
	<tr>
	<td>LastName:</td> 
	<td> <input type = "text" name="lastname"></td>
	</tr>
	
	<tr>
	<td>Email:</td>
	<td><input type = "text" name = "email"></td> 
	</tr>
	
	<tr>
	<td></td>
	<td><input type = "submit" value="Save" class = "save"></td>
	</tr>
	</tbody>
	
	
	</table>
	
	
	</form>

	 <div style = "clear:both;">
	<p>
	<a href = "StudentControllerServlet">Back To List</a>
	</p>
	</div>
</body>
</html>