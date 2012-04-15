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
            h1 a {
                color:#336699;
                text-decoration:none;
                font-weight:normal;
                padding-left:15px;
            }
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
        <h1><a href="/vihaiset">Etusivu</a></h1>
        <form action="${pageContext.request.contextPath}/${action}" method="POST">
            <table>
                <tbody>
                    <tr>
                        <th>Tekijät</th>
                        <td><input type="text" name="author" value="${ref.author}"></td>
                    </tr>
                    <tr>
                        <th>Toimittajat</th>
                        <td><input type="text" name="editor" value="${ref.editor}"></td>
                    </tr>
                    <tr>
                        <th>Artikkelin nimi</th>
                        <td><input type="text" name="title" value="${ref.title}"></td>
                    </tr>
                    <tr>
                        <th>Kirjan nimi</th>
                        <td><input type="text" name="booktitle" value="${ref.booktitle}"></td>
                    </tr>
                    <tr>
                        <th>Sivut</th>
                        <td><input type="text" name="pages" value="${ref.pages}"></td>
                    </tr>
                    <tr>
                        <th>Osa</th>
                        <td><input type="text" name="volume" value="${ref.volume}"></td>
                    </tr>
                    <tr>
                        <th>Numero</th>
                        <td><input type="text" name="number" value="${ref.number}"></td>
                    </tr>
                    <tr>
                        <th>Sarja</th>
                        <td><input type="text" name="series" value="${ref.series}"></td>
                    </tr>
                    <tr>
                        <th>Julkaisija</th>
                        <td><input type="text" name="publisher" value="${ref.publisher}"></td>
                    </tr>
                    <tr>
                        <th>Julkaisijan osoite</th>
                        <td><input type="text" name="address" value="${ref.address}"></td>
                    </tr>
                    <tr>
                        <th>Vuosi</th>
                        <td><input type="text" name="year" value="${ref.year}"></td>
                    </tr>
                    <tr>
                        <th>Kuukausi</th>
                        <td><input type="text" name="month" value="${ref.month}"></td>
                    </tr>
                    <tr>
                        <th>Organisaatio</th>
                        <td><input type="text" name="organization" value="${ref.organization}"></td>
                    </tr>
                    <tr>
                        <th>Lisätietoja</th>
                        <td><input type="text" name="note" value="${ref.note}"></td>
                    </tr>
                    <tr>
                        <th>Järjestämisavain</th>
                        <td><input type="text" name="key" value="${ref.key}"></td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">Lähetä</button>
        </form>
    </body>
</html>