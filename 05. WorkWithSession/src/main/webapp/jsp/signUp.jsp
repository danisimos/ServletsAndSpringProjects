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
    <br>
    <label>Last name
        <input name="lastName" type="text">
    </label>
    <br>
    <label>Age
    <input name="age" type="number" min="3" max="100">
    </label>
    <br>
    <label>Login
        <input name="login" type="text">
    </label>
    <br>
    <label>Password
        <input name="password" type="password">
    </label>
    <br>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
