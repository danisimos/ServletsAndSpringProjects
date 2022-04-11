<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
    <style>
        .avatar {
            vertical-align: middle;
            width: 150px;
            height: 150px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<h1>Your account: ${account.firstName} ${account.lastName}</h1>
<br>
<h1>email: ${account.email}</h1>
<br>
<#if account.state == "NOT_CONFIRMED">
    <h1>Your account not confirmed, please check your email</h1>
</#if>
<br>
<#if account.avatarLink.avatarLink??>
    <img src="http://localhost:8080/${account.avatarLink.avatarLink}" class="avatar">
</#if>
<br>
<h1>Upload avatar:</h1>
<br>
<form action="/files/upload/${account.login}" method="post" enctype="multipart/form-data">
    <label for="description">Description</label>
    <input type="text" id="description" name="description" placeholder="Enter description...">
    <br>
    <input type="file" name="file">
    <input type="submit" value="File Upload">
</form>
</body>
</html>