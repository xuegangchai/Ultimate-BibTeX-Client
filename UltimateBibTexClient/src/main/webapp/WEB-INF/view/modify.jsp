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
			th
			{
				text-align: right;
			}
		</style>
    </head>
    <body>
		<h1>${title}</h1>
		<form action="${action}" method="POST">
			<table>
				<tbody>
					<tr>
						<th>Tekijä</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>Artikkelin nimi</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>Kirjan nimi</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>Julkaisuvuosi</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>Sivunumerot</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>Julkaisija</th>
						<td><input type="text"></td>
					</tr>
				</tbody>
			</table>
			<button type="submit">Lähetä</button>
		</form>
    </body>
</html>
