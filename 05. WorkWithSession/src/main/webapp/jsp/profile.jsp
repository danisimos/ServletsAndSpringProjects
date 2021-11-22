<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Your account:
<br>
<c:out value="${user.firstName}"/>
<c:out value="${user.lastName}"/>
<br>

<form method="post" action="/signOut">
    <input type="submit" value="Sign Out">
</form>

<br>
<br>
<form method="get" action="/users">
    <label>All users
        <input type="submit" value="Users">
    </label>
</form>
</body>
</html>
