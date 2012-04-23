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
        <style type="text/css">
            h1 a {
                color:#336699;
                text-decoration:none;
                font-weight:normal;
                padding-left:15px;
            }
            table {
                font-size:0.9em;
                font-family:Arial, Helvetica, verdana sans-serif;
                width:100%;
            }

            thead th {
                border-right:1px solid #fff;
                color:#fff;
                text-align:center;
                text-transform:uppercase;
                height:25px;
                background-color:#336699;
                font-weight:400;
                padding:2px;
            }

            tbody tr#list {
                background-color:#F0F0F0 ;
                border-bottom:1px solid #D8D8D8;
            }

            tbody td {
                color:#414141;
                text-align:left;
                padding:5px;
            }
        </style>
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
                <tr id="list">
                    <th>Viitetyyppi</th>
                    <th>Tekijä</th>
                    <th>Artikkelin nimi</th>
                    <th>Kirjan nimi</th>
                    <th>Vuosi</th>
                    <th>Sivut</th>
                    <th>Julkaisija</th>
                    <th>Tagit</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ref" items="${references}">
                    <tr>
                        <td>${ref.type}</td>
                        <td>${ref.author}</td>
                        <td>${ref.title}</td>
                        <td>${ref.booktitle}</td>
                        <td>${ref.year}</td>
                        <td>${ref.pages}</td>
                        <td>${ref.publisher}</td>
                        <td>${ref.tags}</td>
                        <td><a href="${pageContext.request.contextPath}/reference/${ref.id}" name="${ref.title}">Muokkaa</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>