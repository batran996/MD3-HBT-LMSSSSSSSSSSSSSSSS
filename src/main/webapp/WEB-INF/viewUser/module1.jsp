<%--
  Created by IntelliJ IDEA.
  User: TungDao
  Date: 10/6/2022
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table align="center" border="1" , style="width: 50%; text-align: center" class="table table-striped">
        <tr class="table-warning">
            <th scope="col">ID</th>
            <th scope="col">ID Lộ Trình</th>
            <th scope="col">Tên Module</th>
        </tr>

            <c:forEach var="lt1" items='${requestScope["moduleList1"]}'>
                <tr>
                    <td>${lt1.id}</td>
                    <td>${lt1.id_lotrinh}</td>
                    <td>${lt1.name_module}</td>
                </tr>
            </c:forEach>

    </table>





</body>
</html>
