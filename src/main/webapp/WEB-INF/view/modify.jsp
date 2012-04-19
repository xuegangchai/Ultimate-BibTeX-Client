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
        <h1><a href="${pageContext.request.contextPath}/">Etusivu</a></h1>
        <form action="${pageContext.request.contextPath}/${action}" method="POST">
            <table>
                <tbody>
                    <tr>
                        <th>Viiteavain</th>
                        <td><input type="text" name="refkey" value="${ref.refkey}" required</td>
                        <td>${errorFormatter.formatErrors("refkey")}- LaTeXissa käytettävä viiteavain</td>
                    </tr>
                    <tr>
                        <th>Tekijät</th>
                        <td><input type="text" name="author" value="${ref.author}" required></td>
                        <td>${errorFormatter.formatErrors("author")} - Tekijöiden nimet </td>
                    </tr>
                    <tr>
                        <th>Toimittajat</th>
                        <td><input type="text" name="editor" value="${ref.editor}"></td>
                        <td>${errorFormatter.formatErrors("editor")} - Toimittajien nimet</td>
                    </tr>
                    <tr>
                        <th>Artikkelin nimi</th>
                        <td><input type="text" name="title" value="${ref.title}" required></td>
                        <td>${errorFormatter.formatErrors("title")} - Siteerattavan työn nimi</td>
                    </tr>
                    <tr>
                        <th>Kirjan nimi</th>
                        <td><input type="text" name="booktitle" value="${ref.booktitle}" required></td>
                        <td>${errorFormatter.formatErrors("booktitle")} - Kirjan nimi, jos vain osaa siitä siteerataan</td>
                    </tr>
                    <tr>
                        <th>Sivut</th>
                        <td><input type="text" name="pages" value="${ref.pages}"></td>
                        <td>${errorFormatter.formatErrors("pages")} - Sivun numerot joko pilkuilla eroitettuina tai kantama väliviivoilla eroitettuina esim. "7,41,73--97"  </td>
                    </tr>
                    <tr>
                        <th>Osa</th>
                        <td><input type="text" name="volume" value="${ref.volume}"></td>
                        <td>${errorFormatter.formatErrors("volume")} - Mikä osa jos moniosainen kirja</td>
                    </tr>
                    <tr>
                        <th>Numero</th>
                        <td><input type="text" name="number" value="${ref.number}"></td>
                        <td>${errorFormatter.formatErrors("number")} - Julkaisun numero jos sellaista on</td>
                    </tr>
                    <tr>
                        <th>Sarja</th>
                        <td><input type="text" name="series" value="${ref.series}"></td>
                        <td>${errorFormatter.formatErrors("series")} - Sarjan nimi jos kirja on osa kirjasarjaa</td>
                    </tr>
                    <tr>
                        <th>Julkaisija</th>
                        <td><input type="text" name="publisher" value="${ref.publisher}"></td>
                        <td>${errorFormatter.formatErrors("publisher")} - Julkaisijan nimi </td>
                    </tr>
                    <tr>
                        <th>Julkaisijan osoite</th>
                        <td><input type="text" name="address" value="${ref.address}"></td>
                        <td>${errorFormatter.formatErrors("address")} - Julkaisijan osoite tai kaupunki</td>
                    </tr>
                    <tr>
                        <th>Vuosi</th>
                        <td><input type="year" name="year" value="${ref.year}" required></td>
                        <td>${errorFormatter.formatErrors("year")} - Julkaisuvuosi tai julkaisettomalle kirjoitusvuosi</td>
                    </tr>
                    <tr>
                        <th>Kuukausi</th>
                        <td><input type="number" name="month" value="${ref.month}"></td>
                        <td>${errorFormatter.formatErrors("month")} - Julkaisukuukausi tai julkaisemattomalle kirjoituskuukausi</td>
                    </tr>
                    <tr>
                        <th>Organisaatio</th>
                        <td><input type="text" name="organization" value="${ref.organization}"></td>
                        <td>${errorFormatter.formatErrors("organization")} - Konfrenssia sponsoroiva organisaatio </td>
                    </tr>
                    <tr>
                        <th>Lisätietoja</th>
                        <td><input type="text" name="note" value="${ref.note}"></td>
                        <td>${errorFormatter.formatErrors("note")} - Ylimääräistä lukijaa auttavaa informaatiota</td>
                    </tr>
                    <tr>
                        <th>Järjestämisavain</th>
                        <td><input type="text" name="key" value="${ref.key}"></td>
                        <td>${errorFormatter.formatErrors("key")} - Aakkoselliseen järjestämiseen käytettävä avain kuin kun tekijä- ja editori-kentät puuttuvat</td>
                    </tr>
                    <tr>
                        <th>Tags</th>
                        <td><input type="text" name="tags" value="${ref.tags}"></td>
                        <td>${errorFormatter.formatErrors("tags")} - Asiaan liittyvät tagit</td>
                    </tr>
                    
                </tbody>
            </table>
            <button type="submit" id="submit">Lähetä</button>
        </form>
    </body>
</html>