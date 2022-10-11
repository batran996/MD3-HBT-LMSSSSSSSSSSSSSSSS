<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Lo Trinh Management </title>
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
            <a class="navbar-brand"> Lo Trinh Management </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="/HomeServlet?action=goHome"
                   class="nav-link">Trang Chu</a></li>
        </ul>

    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">Menu Lo Trinh</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Them Moi Lo Trinh</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>STT</th>
                <th>TEN Lo Trinh</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="baiDoc" items="${listAdmin}">

                <tr>
                    <td>
                        <c:out value="${baiDoc.id}" />
                    </td>
                    <td>
                        <a href=""><c:out value="${baiDoc.name}" /></a>
                    </td>
                    <td><a href="<%=request.getContextPath()%>/edit?id=${baiDoc.id}">Sua</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/delete?id=<c:out value='${baiDoc.id}' />">Xoa</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>