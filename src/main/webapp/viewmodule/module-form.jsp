<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ADMIN Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    <%--          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"--%>
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> ADMIN Management </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">ADMIN MODULE</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${Module != null}">
            <form action="/Module?action=updateMD" method="post">
                </c:if>
                <c:if test="${Module == null}">
                <form action="/Module?action=insertMD" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${Module != null}">
                                Sua Module
                            </c:if>
                            <c:if test="${Module == null}">
                                Them Module
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${Module != null}">
                        <input type="hidden" name="id" value="<c:out value='${Module.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label> Ten Module</label> <input type="text"
                                                           value="<c:out value='${Module.name}'/>" class="form-control"
                                                           name="name_module" required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>