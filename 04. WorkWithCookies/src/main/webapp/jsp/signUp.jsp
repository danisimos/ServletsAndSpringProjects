<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 15.10.2021
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signUp</title>
</head>
<body>
<form method="post">
    <label>First name
        <input name="firstName" type="text">
    </label>
    <label>Last name
        <input name="lastName" type="text">
    </label>
    <label>Age
    <input name="age" type="number" min="3" max="100">
    </label>
    <label>Login
        <input name="login" type="text">
    </label>
    <label>Password
        <input name="password" type="password">
    </label>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
