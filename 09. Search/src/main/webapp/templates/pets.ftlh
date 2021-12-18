<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pets</title>
</head>
<body>
<script>
    function addPet(kind, name) {
        let request = new XMLHttpRequest();
        let body = {
            "kind": kind,
            "name": name
        };

        request.open('POST', '/pets', false);
        request.setRequestHeader('Content-Type', 'application/json');

        request.send(JSON.stringify(body));

        if(request.status !== 200) {
            alert('Ошибка');
        } else {
            let html =
                '<tr>' +
                '<th>Id</th>' +
                '<th>Kind</th>' +
                '<th>Name</th>' +
                '</tr>'

            let response = JSON.parse(request.response);

            for(let i = 0; i < response.length; i++) {
                html += '<tr>';
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['kind'] + '</td>';
                html += '<td>' + response[i]['name'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('pets_table').innerHTML = html;
        }
    }
</script>

<table id="pets_table">
    <tr>
        <th>Id</th>
        <th>kind</th>
        <th>name</th>
    </tr>
    <#list pets as pet>
        <tr>
            <td>${pet.id}</td>
            <td>${pet.kind}</td>
            <td>${pet.name}</td>
        </tr>
    </#list>
</table>

<div>
    <input id="kindInput" placeholder="Kind">
    <input id="nameInput" placeholder="Name">
    <button onclick="addPet(
        document.getElementById('kindInput').value,
        document.getElementById('nameInput').value
    )">
        Add
    </button>
</div>


</body>
</html>