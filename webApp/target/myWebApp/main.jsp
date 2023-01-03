<%@ page import="Repository.TeacherRepository" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kamran
  Date: 11/18/2022
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Teachers</title>
    <link rel="stylesheet" href="style.css">
    <script src="app.js"></script>
    <script>

    </script>

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="d-flex" action="/webApp_war_exploded/logout.jsp">
                <button class="btn btn-outline-success" type="submit" style="margin-left: 1400px">LOGOUT</button>
            </form>
        </div>
    </div>
</nav>


<h1>With which entity would you like to deal with?
</h1>

<a href="/webApp_war_exploded/mainteacher.jsp">  <button class="btn btn-dark" style="margin-left: 20px" >TEACHERS</button></a>

<a href="/webApp_war_exploded/mainstudent.jsp">  <button class="btn btn-dark" style="margin-left: 20px" > STUDENTS</button></a>

<div>
    <br/>
    <br/>
    <br/>
</div>



</body>

</html>


