<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 15.10.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<form method="post">
    <label>Login
        <input name="login" type="text">
    </label>
    <label>Password
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>

<%=request.getAttribute("message")%>

<form method="get" action="/signUp">
    <label>Create Account
    <input type="submit" value="Sign Up">
    </label>
</form>
</body>
</html>
