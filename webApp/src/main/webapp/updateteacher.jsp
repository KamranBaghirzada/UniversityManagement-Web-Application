<%@ page import="Repository.TeacherRepository" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %><%--
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
    <link rel="stylesheet" href="/webApp_war_exploded/style.css">
    <script src="app.js"></script>
    <script>
        function foo() {
            var element = document.getElementById("exampleModal")
            element.style = "display: block; opacity:1";
        }
    </script>

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Dashboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item" onclick="foo()">
                    <a class="nav-link" href="#">Teachers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Students</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<h1>Teacher Update</h1>
<%
    TeacherRepository teacherRepository = new TeacherRepository();
    Integer id = Integer.parseInt(request.getParameter("id"));
    Teacher teacher = teacherRepository.getByID(id);
%>
<form id="form" class="col-3 m-lg-3" method="POST" action="/webApp_war_exploded/">
    <div class="mb-3">
        <label class="form-label" for="name">
            name
        </label>
        <input class="form-control" id="name" type="text" name="name" value="<%=teacher.getName()%>"/>
    </div>

    <div>
        <label class="form-label" for="surname">
            surname
        </label>
        <input class="form-control" id="surname" type="text" name="surname" value="<%=teacher.getSurname()%>"/>
    </div>
    <div>
        <label class="form-label" for="salary">
            salary
        </label>
        <input class="form-control" id="salary" type="text" name="salary" value="<%=teacher.getSalary()%>"/>
    </div>
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="<%=id%>"/>
    <input type="hidden" name="jspname" value="updateteacher"/>
    <button type="submit" class="btn btn-primary" style="margin-top: 5px"> UPDATE</button>
</form>
<br/>
<br/>
<br/>
</body>
</html>

