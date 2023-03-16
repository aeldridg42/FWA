<%--
  Created by IntelliJ IDEA.
  User: aeldridg
  Date: 01.02.2023
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Sign up + all users</title>
</head>

<%
    String errorMsg = "";
    if (session.getAttribute("ERROR") != null)
        errorMsg = (String)session.getAttribute("ERROR");
    session.setAttribute("ERROR", null);
%>


<body>
    <form method="post" action="${pageContext.request.contextPath}/signUp">
        <label for="email">Email
            <input type="text" id="email" name="email">
        </label>

        <label for="first_name">First name
            <input type="text" id="first_name" name="first_name">
        </label>

        <label for="last_name">Last name
            <input type="text" id="last_name" name="last_name">
        </label>

        <label for="phone_number">Phone number
            <input type="text" id="phone_number" name="phone_number">
        </label>

        <label for="password">Password
            <input type="password" id="password" name="password">
        </label>
        <input type="submit" value="sign up!">
    </form>
        <%= errorMsg %>
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>Email</th>
        </tr>
        <jsp:useBean id="usersFromServer" scope="request" type="java.util.List"/>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
