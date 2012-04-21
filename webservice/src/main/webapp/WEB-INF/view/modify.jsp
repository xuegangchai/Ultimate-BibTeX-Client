<%-- 
    Document   : muokkaus
    Created on : 1.4.2012, 19:48:31
    Author     : ewk
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/custom.tl" prefix="c"%>

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
                        <td>${c:formatErrors(errorFormatter, "refkey")}- LaTeXissa käytettävä viiteavain</td>
                    </tr>
                    <tr>
                        <th>Tekijät</th>
                        <td><input type="text" name="author" value="${ref.author}" required></td>
                        <td>${c:formatErrors(errorFormatter, "author")} - Tekijöiden nimet </td>
                    </tr>
                    <tr>
                        <th>Toimittajat</th>
                        <td><input type="text" name="editor" value="${ref.editor}"></td>
                        <td>${c:formatErrors(errorFormatter, "editor")} - Toimittajien nimet</td>
                    </tr>
                    <tr>
                        <th>Artikkelin nimi</th>
                        <td><input type="text" name="title" value="${ref.title}" required></td>
                        <td>${c:formatErrors(errorFormatter, "title")} - Siteerattavan työn nimi</td>
                    </tr>
                    <tr>
                        <th>Kirjan nimi</th>
                        <td><input type="text" name="booktitle" value="${ref.booktitle}" required></td>
                        <td>${c:formatErrors(errorFormatter, "booktitle")} - Kirjan nimi, jos vain osaa siitä siteerataan</td>
                    </tr>
                    <tr>
                        <th>Sivut</th>
                        <td><input type="text" name="pages" value="${ref.pages}"></td>
                        <td>${c:formatErrors(errorFormatter, "pages")} - Sivun numerot joko pilkuilla eroitettuina tai kantama väliviivoilla eroitettuina esim. "7,41,73--97"  </td>
                    </tr>
                    <tr>
                        <th>Osa</th>
                        <td><input type="text" name="volume" value="${ref.volume}"></td>
                        <td>${c:formatErrors(errorFormatter, "volume")} - Mikä osa jos moniosainen kirja</td>
                    </tr>
                    <tr>
                        <th>Numero</th>
                        <td><input type="text" name="number" value="${ref.number}"></td>
                        <td>${c:formatErrors(errorFormatter, "number")} - Julkaisun numero jos sellaista on</td>
                    </tr>
                    <tr>
                        <th>Sarja</th>
                        <td><input type="text" name="series" value="${ref.series}"></td>
                        <td>${c:formatErrors(errorFormatter, "series")} - Sarjan nimi jos kirja on osa kirjasarjaa</td>
                    </tr>
                    <tr>
                        <th>Julkaisija</th>
                        <td><input type="text" name="publisher" value="${ref.publisher}"></td>
                        <td>${c:formatErrors(errorFormatter, "publisher")} - Julkaisijan nimi </td>
                    </tr>
                    <tr>
                        <th>Julkaisijan osoite</th>
                        <td><input type="text" name="address" value="${ref.address}"></td>
                        <td>${c:formatErrors(errorFormatter, "address")} - Julkaisijan osoite tai kaupunki</td>
                    </tr>
                    <tr>
                        <th>Vuosi</th>
                        <td><input type="year" name="year" value="${ref.year}" required></td>
                        <td>${c:formatErrors(errorFormatter, "year")} - Julkaisuvuosi tai julkaisettomalle kirjoitusvuosi</td>
                    </tr>
                    <tr>
                        <th>Kuukausi</th>
                        <td><input type="number" name="month" value="${ref.month}"></td>
                        <td>${c:formatErrors(errorFormatter, "month")} - Julkaisukuukausi tai julkaisemattomalle kirjoituskuukausi</td>
                    </tr>
                    <tr>
                        <th>Organisaatio</th>
                        <td><input type="text" name="organization" value="${ref.organization}"></td>
                        <td>${c:formatErrors(errorFormatter, "organization")} - Konfrenssia sponsoroiva organisaatio </td>
                    </tr>
                    <tr>
                        <th>Lisätietoja</th>
                        <td><input type="text" name="note" value="${ref.note}"></td>
                        <td>${c:formatErrors(errorFormatter, "note")} - Ylimääräistä lukijaa auttavaa informaatiota</td>
                    </tr>
                    <tr>
                        <th>Järjestämisavain</th>
                        <td><input type="text" name="key" value="${ref.key}"></td>
                        <td>${c:formatErrors(errorFormatter, "key")} - Aakkoselliseen järjestämiseen käytettävä avain kuin kun tekijä- ja editori-kentät puuttuvat</td>
                    </tr>
                    <tr>
                        <th>Tags</th>
                        <td><input type="text" name="tags" value="${ref.tags}"></td>
                        <td>${c:formatErrors(errorFormatter, "tags")} - Asiaan liittyvät tagit</td>
                    </tr>
                    
                </tbody>
            </table>
            <button type="submit" id="submit">Lähetä</button>
        </form>
    </body>
</html>