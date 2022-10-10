<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Module Management </title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> Module Management </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Module</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">Menu Module</h3>
        <hr>
        <div class="container text-left">

            <a href="/Module?action=newMD" class="btn btn-success">Them Moi Module</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>STT</th>
                <th>TEN Module</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="md" items="${listModule}">

                <tr>
                    <td><c:out value="${md.id}"/></td>
                    <td><c:out value="${md.name}"/></td>
                    <td><a href="/Module?action=editMD&id=${md.id}">Sua</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="/Module?action=deleteMD&id=<c:out value='${md.id}' />">Xoa</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>
        </table>
    </div>
</div>
</body>
</html>