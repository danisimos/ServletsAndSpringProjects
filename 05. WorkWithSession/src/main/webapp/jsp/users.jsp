<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${users}" var="user">
<c:out value="${user.firstName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${user.age}"/> <br>
</c:forEach>
</body>
</html>
