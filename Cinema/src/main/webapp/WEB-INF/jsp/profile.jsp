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
<img src="/FWA/images${sessionScope.avatar}" alt="Trulli" width="500" height="333">
<form method="post" enctype="multipart/form-data">
    <div>
        <label for="file">Choose file to upload</label>
        <input type="file" id="file" name="file" accept="image/*" multiple>
    </div>
    <div>
        <button>Submit</button>
    </div>
</form>

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

<table>
    <tr>
        <th>File name</th>
        <th>Size</th>
        <th>MIME</th>
    </tr>
    <jsp:useBean id="files" scope="request" type="java.util.List"/>
    <c:forEach items="${files}" var="file">
        <tr>
            <td><p><a href="/FWA/images/${file.get(0)}">${file.get(0)}</a></p></td>
            <td>${file.get(1)}</td>
            <td>${file.get(2)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
