<#ftl encoding='UTF-8'>
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
    <label>Email
        <input type="email" name="email">
    </label>
    <input type="submit">
</form>

<br>

<#list users as profile>
    ${profile.firstName}
    ${profile.lastName}
    age
    ${profile.age}
    <br>
</#list>
</body>
</html>