<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 09.10.2021
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>wordsTable</title>
</head>
<body>
<table border = 1>
    <tr>
        <th>File Name</th>
        <th>1</th>
        <th>2</th>
        <th>3</th>
        <th>4</th>
        <th>5</th>
        <th>6</th>
        <th>7</th>
        <th>8</th>
        <th>9</th>
        <th>10</th>
    </tr>
    <c:forEach items="${files}" var="file">
        <tr>
            <th><c:out value="${file.key}"/></th>
        <c:forEach items="${file.value}" var="map">
            <th><c:out value="${map.key}"/>: <c:out value="${map.value}"/></th>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
