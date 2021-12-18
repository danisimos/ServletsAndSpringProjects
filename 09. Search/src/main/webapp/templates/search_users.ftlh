<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <script>
        function searchUsers(email) {
            let request = new XMLHttpRequest();
            request.open('GET', '/search/users/byEmail?email=' + email, false);
            request.send();
            if(request.status !== 200) {
                alert('Ошибка');
            } else {
                let html =
                    '<tr>' +
                        '<th>ID</th>' +
                        '<th>First Name</th>' +
                        '<th>Last Name</th>' +
                        '<th>Email</th>' +
                    '</tr>';
                let response = JSON.parse(request.response);

                for(let i = 0; i < response.length; i++) {
                    html += '<tr>';
                    html += '<td>' + response[i]['id'] + '</td>';
                    html += '<td>' + response[i]['firstName'] + '</td>';
                    html += '<td>' + response[i]['lastName'] + '</td>';
                    html += '<td>' + response[i]['email'] + '</td>';
                    html += '</tr>';
                }

                document.getElementById("users_table").innerHTML = html;
            }
        }
    </script>
</head>
<body>
<label>Введите email
    <input name="email" id="email" placeholder="email" onkeyup="searchUsers(document.getElementById('email').value)">
</label>

<table id="users_table">
</table>

</body>
</html>