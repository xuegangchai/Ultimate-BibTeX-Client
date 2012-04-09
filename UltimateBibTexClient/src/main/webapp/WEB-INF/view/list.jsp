<%-- 
    Document   : listaus
    Created on : 1.4.2012, 19:48:03
    Author     : ewk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Viitteet</title>
    </head>
    <body>
		<h1>Viitteet</h1>
		<table>
			<thead>
				<tr>
					<th>Nimi<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ref" items="${references}">
					<tr>
						<td>${ref.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </body>
</html>
