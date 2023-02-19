<%--
  Created by IntelliJ IDEA.
  User: aeldridg
  Date: 18.02.2023
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/signIn">
    <label for="email">Email
        <input type="text" id="email" name="email">
    </label>

    <label for="password">password
        <input type="password" id="password" name="password">
    </label>

    <input type="submit" value="Sign in!">
</form>
</body>
</html>
