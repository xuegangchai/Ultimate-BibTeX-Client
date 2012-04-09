<%-- 
    Document   : muokkaus
    Created on : 1.4.2012, 19:48:31
    Author     : ewk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
		<style type="text/css">
			tbody th
			{
				text-align: right;
			}
			
			
			input[type=text]
			{
				width: 20em;
			}
		</style>
    </head>
    <body>
		<h1>${title}</h1>
		<form action="${action}" method="POST">
			<table>
				<tbody>
					<tr>
						<th>Tekijät</th>
						<td><input type="text">${ref.author}</td>
					</tr>
					<tr>
						<th>Toimittajat</th>
						<td><input type="text">${ref.editor}</td>
					</tr>
					<tr>
						<th>Artikkelin nimi</th>
						<td><input type="text">${ref.title}</td>
					</tr>
					<tr>
						<th>Kirjan nimi</th>
						<td><input type="text">${ref.booktitle}</td>
					</tr>
					<tr>
						<th>Sivut</th>
						<td><input type="text">${ref.pages}</td>
					</tr>
					<tr>
						<th>Osa</th>
						<td><input type="text">${ref.volume}</td>
					</tr>
					<tr>
						<th>Numero</th>
						<td><input type="text">${ref.number}</td>
					</tr>
					<tr>
						<th>Sarja</th>
						<td><input type="text">${ref.series}</td>
					</tr>
					<tr>
						<th>Julkaisija</th>
						<td><input type="text">${ref.publisher}</td>
					</tr>
					<tr>
						<th>Julkaisijan osoite</th>
						<td><input type="text">${ref.address}</td>
					</tr>
					<tr>
						<th>Vuosi</th>
						<td><input type="text">${ref.year}</td>
					</tr>
					<tr>
						<th>Kuukausi</th>
						<td><input type="text">${ref.month}</td>
					</tr>
					<tr>
						<th>Organisaatio</th>
						<td><input type="text">${ref.organization}</td>
					</tr>
					<tr>
						<th>Lisätietoja</th>
						<td><input type="text">${ref.note}</td>
					</tr>
					<tr>
						<th>Järjestämisavain</th>
						<td><input type="text">${ref.key}</td>
					</tr>
				</tbody>
			</table>
			<button type="submit">Lähetä</button>
		</form>
    </body>
</html>
