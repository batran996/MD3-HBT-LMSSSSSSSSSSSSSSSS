<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page='../boostrap/boostrapUser.jsp'>
    <jsp:param name="articleId" value=""/>
</jsp:include>
<%--
  Created by IntelliJ IDEA.
  User: TungDao
  Date: 10/7/2022
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello World!!!!</h1>
<table align="center" border="1" , style="width: 50%; text-align: center" class="table table-striped">
    <tr class="table-warning">
        <th scope="col">ID</th>
        <th scope="col">ID_Lộ Trình</th>
        <th scope="col">ID Module</th>
        <th scope="col">Tên Bài Đọc</th>
    </tr>

        <c:forEach var="bd" items='${requestScope["listBaiDoc"]}'>
            <tr class="table-danger">
                <td>${bd.id}</td>
                <td>${bd.id_lotrinh}</td>
                <td>${bd.id_module}</td>
                <td>${bd.name_baidoc}</td>
            </tr>

        </c:forEach>
</table>
</body>
</html
<jsp:include page='../boostrap/footer.jsp'>
    <jsp:param name="articleId" value=""/>
</jsp:include>
