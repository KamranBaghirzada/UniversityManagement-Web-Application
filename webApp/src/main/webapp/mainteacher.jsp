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
    <link rel="stylesheet" href="style.css">
    <script src="app.js"></script>
    <script>

    </script>

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
            <form class="d-flex" action="/webApp_war_exploded/logout.jsp">
                <button class="btn btn-outline-success" type="submit" style = margin-left:1400px>LOGOUT</button>
            </form>
        </div>
    </div>
</nav>


<h1>Teachers  <a href="/webApp_war_exploded/teachers/insert">  <button class="btn btn-dark" style="margin-left: 20px" > INSERT</button></a> </h1>
<form id="form" class="col-3 m-lg-3" method="GET">
    <div class="mb-3">
        <label class="form-label" for="name">
            name
        </label>
        <input class="form-control" id="name" name="name" type="text"/>
    </div>

    <div>
        <label class="form-label" for="surname">
            surname
        </label>
        <input class="form-control" id="surname" name="surname" type="text"/>
    </div>
    <div>
        <label class="form-label" for="salary">
            salary
        </label>
        <input class="form-control" id="salary" name="salary" type="text"/>
    </div>
    <button class="btn btn-primary" style="margin-top: 5px"> SEARCH</button>

</form>

<div>
    <br/>
    <br/>
    <br/>

    <table class="table">

        <thead>
        <tr>
            <th>name</th>
            <th>surname</th>
            <th>salary</th>
            <th>University</th>
            <th>actions</th>
        </tr>
        </thead>

        <tbody>
        <%
            TeacherRepository teacherRepository = new TeacherRepository();
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String salaryStr = request.getParameter("salary");
            BigDecimal salary = salaryStr != null && salaryStr.trim().length() > 0 ? new BigDecimal(salaryStr.trim()) : null;
            List<Teacher> list = teacherRepository.getList(name, surname, salary);
            for (Teacher teacher : list) {
        %>
        <tr>
            <td>
                <%=teacher.getName()%>
            </td>
            <td>
                <%=teacher.getSurname()%>
            </td>
            <td>
                <%=teacher.getSalary()%>
            </td>
            <td>
                <%=teacher.getUniversity_id()%>
            </td>
            <td>
                <a href="teachers/delete?id=<%=teacher.getId()%>" class="btn btn-danger">delete</a>
            </td>
            <td>
                <a href="teachers/update?<%="id="+teacher.getId()%>">
                    <button class="btn btn-warning">update</button>
                </a>
            </td>

        </tr>

        <%}%>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure to delete?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger">Delete</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>

