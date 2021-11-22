<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<form action="/users" method="post">
    <label>FirstName
        <input type="text" name="firstName">
    </label>
    <label>LastName
        <input type="text" name="lastName">
    </label>
    <label>Age
        <input type="number" name="age">
    </label>
    <input type="submit">
</form>

<br>

<#list users as user>
    ${user.firstName}
    ${user.lastName}
    age
    ${user.age}
    <br>
</#list>
</body>
</html>