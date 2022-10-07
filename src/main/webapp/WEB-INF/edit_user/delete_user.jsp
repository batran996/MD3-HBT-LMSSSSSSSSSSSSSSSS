<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<form method="post" action="/users?action=delete_user">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>USER_NAME</th>
            <th>EMAIL</th>
            <th>PASS</th>
            <th>ROLE</th>
        </tr>
        <tr>
            <td><input value="${delete_user.id}" name="id"></td>
            <td> ${delete_user.name}</td>
            <td>${delete_user.username}</td>
            <td>${delete_user.email}</td>
            <td>${delete_user.password}</td>

            <c:forEach var="role" items="${delete_user.roleList}">
                <td>
                        ${role.name}
                </td>
            </c:forEach>
        </tr>
    </table>
    <button type="submit"  > delete</button>
</form>
</body>
</html>
