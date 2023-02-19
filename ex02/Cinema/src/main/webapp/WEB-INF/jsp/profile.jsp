<%--
  Created by IntelliJ IDEA.
  User: aeldridg
  Date: 18.02.2023
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<c>${user.firstName} ${user.lastName},${user.email}</c>
<table>
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>Ip</th>
    </tr>
    <c:forEach items="${user.logs}" var="log">
        <tr>
            <td>${log.date}</td>
            <td>${log.time}</td>
            <td>${log.ip}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
