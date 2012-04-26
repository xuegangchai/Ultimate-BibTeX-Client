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
        <link href="${pageContext.request.contextPath}/resources/vihaiset.css" rel="stylesheet" type="text/css">

    </head>
    <body>
        <!-- This is the front page. -->
        <h1><a href="${pageContext.request.contextPath}/">Ultimate Bibtex Client</a></h1>
        <p>
            <a href="${pageContext.request.contextPath}/create.html">Luo uusi viite</a><br>
            <a href="${pageContext.request.contextPath}/bibtex">Lataa viitteet BibTeX-muodossa</a>
        </p>
        <form action="${pageContext.request.contextPath}/search" method="POST">
            <table width="400" cellpadding="5">
                <tr>
                    <td>
                        Etsi: <input type="text" name="keywords" size="30" maxlength="50" value="${searchword}"><button type="submit">Search</button>
                    </td>
                </tr>
            </table>
        </form>
        <table>
            <thead>
                <tr>
                    <th>Viitetyyppi</th>
                    <th>Tekijä</th>
                    <th>Nimi</th>
                    <th>Kirjan nimi</th>
                    <th>Vuosi</th>
                    <th>Sivut</th>
                    <th>Julkaisija</th>
                    <th>Tagit</th>
                    <th>Journal</th>               
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ref" items="${references}">
                    <tr id="list">
                        <td id="${ref.id}.type" class="type" >${ref.type}</td>
                        <td id="${ref.id}.author" class="author" >${ref.author}</td>
                        <td id="${ref.id}.title" class="title" >${ref.title}</td>
                        <td id="${ref.id}.booktitle" class="booktitle" >${ref.booktitle}</td>
                        <td id="${ref.id}.year" class="year" >${ref.year}</td>
                        <td id="${ref.id}.pages" class="pages" >${ref.pages}</td>
                        <td id="${ref.id}.publisher" class="publisher" >${ref.publisher}</td>
                        <td id="${ref.id}.tags" class="tags" >${ref.tags}</td>
                        <td id="${ref.id}.journal" class="journal" >${ref.journal}</td>
                        <td class="modify">
                            <a href="${pageContext.request.contextPath}/reference/${ref.id}" id="${ref.refkey}modify">Muokkaa</a> / <a href="${pageContext.request.contextPath}/delete/${ref.id}" id="${ref.id}.delete">Poista</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>