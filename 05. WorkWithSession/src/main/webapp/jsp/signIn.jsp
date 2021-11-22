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
    <br>
    <label>Password
        <input name="password" type="password">
    </label>
    <br>
    <input type="submit">
</form>

<br>
<br>
<form method="get" action="/signUp">
    <label>Create Account
    <input type="submit" value="Sign Up">
    </label>
</form>
</body>
</html>
