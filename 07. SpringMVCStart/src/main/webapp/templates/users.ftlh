<html>
<head>
    <title>Users</title>
</head>
<body>
    <#list users as user>
        ${user.firstName}
        ${user.lastName}
        age
        ${user.age}
        <br>
    </#list>
</body>
</html>