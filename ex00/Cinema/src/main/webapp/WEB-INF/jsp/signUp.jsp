<%@ page import="edu.school21.cinema.models.User" %><%--
  Created by IntelliJ IDEA.
  User: aeldridg
  Date: 01.02.2023
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sign up + all users</title>
</head>
<body>
    <form method="post" action="/signUp">
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
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
        </tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phoneNumber}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
