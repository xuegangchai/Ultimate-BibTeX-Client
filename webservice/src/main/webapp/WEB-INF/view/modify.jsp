<%-- 
    Document   : muokkaus
    Created on : 1.4.2012, 19:48:31
    Author     : ewk
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/custom.tl" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
        <form:form commandName="reference" action="${pageContext.request.contextPath}/${action}" method="POST">
            <table>
                <tbody>
                    <tr>
                        <th>Viitteen tyyppi</th>
                        <td>
                            Article: <form:radiobutton path="type" value="article" /><br />
                            Book: <form:radiobutton path="type" value="book" /><br/>
                            Inproceedings: <form:radiobutton path="type" value="inproceedings" />
                        </td>
                    </tr>
                    
                    <tr>
                        <th>Viiteavain</th>
                        <td><form:input path="refkey" required="required"/>
                            <form:errors path="refkey" /></td>
                        <td> - LaTeXissa käytettävä viiteavain</td>
                    </tr>
                    <tr>
                        <th>Tekijät</th>
                        <td><form:input path="author" required="required"/>
                            <form:errors path="author" /></td>
                        <td>- Tekijöiden nimet </td>
                    </tr>
                    <tr>
                        <th>Toimittajat</th>
                        <td><form:input path="editor"/>
                            <form:errors path="editor" /></td>
                        <td> - Toimittajien nimet</td>
                    </tr>
                    <tr>
                        <th>Artikkelin nimi</th>
                        <td><form:input path="title" required="required"/>
                            <form:errors path="title" /></td>
                        <td>- Siteerattavan työn nimi</td>
                    </tr>
                    <tr>
                        <th>Kirjan nimi</th>
                        <td><form:input path="booktitle" required="required"/>
                            <form:errors path="booktitle" /></td>
                        <td>- Kirjan nimi, jos vain osaa siitä siteerataan</td>
                    </tr>
                    <tr>
                        <th>Sivut</th>
                        <td><form:input path="pages" />
                            <form:errors path="pages" /></td>
                        <td>- Sivun numerot joko pilkuilla eroitettuina tai kantama väliviivoilla eroitettuina esim. "7,41,73--97"  </td>
                    </tr>
                    <tr>
                        <th>Osa</th>
                        <td><form:input path="volume"/>
                            <form:errors path="volume" /></td>
                        <td>- Mikä osa jos moniosainen kirja</td>
                    </tr>
                    <tr>
                        <th>Numero</th>
                        <td><form:input path="number"/>
                            <form:errors path="number" /></td>
                        <td>- Julkaisun numero jos sellaista on</td>
                    </tr>
                    <tr>
                        <th>Sarja</th>
                        <td><form:input path="series"/>
                            <form:errors path="series" /></td>
                        <td>- Sarjan nimi jos kirja on osa kirjasarjaa</td>
                    </tr>
                    <tr>
                        <th>Julkaisija</th>
                        <td><form:input path="publisher"/>
                            <form:errors path="publisher" /></td>
                        <td>- Julkaisijan nimi </td>
                    </tr>
                    <tr>
                        <th>Julkaisijan osoite</th>
                        <td><form:input path="address"/>
                            <form:errors path="address" /></td>
                        <td>- Julkaisijan osoite tai kaupunki</td>
                    </tr>
                    <tr>
                        <th>Vuosi</th>
                        <td><form:input path="year" required="required"/>
                            <form:errors path="year" /></td>
                        <td>- Julkaisuvuosi tai julkaisettomalle kirjoitusvuosi</td>
                    </tr>
                    <tr>
                        <th>Kuukausi</th>
                        <td><form:input path="month" type="number" min="1" max="12" />
                            <form:errors path="month" /></td>
                        <td>- Julkaisukuukausi tai julkaisemattomalle kirjoituskuukausi</td>
                    </tr>
                    <tr>
                        <th>Organisaatio</th>
                        <td><form:input path="organization"/>
                            <form:errors path="organization" /></td>
                        <td>- Konfrenssia sponsoroiva organisaatio </td>
                    </tr>
                    <tr>
                        <th>Lisätietoja</th>
                        <td><form:input path="note"/>
                            <form:errors path="note" /></td>
                        <td>- Ylimääräistä lukijaa auttavaa informaatiota</td>
                    </tr>
                    <tr>
                        <th>Järjestämisavain</th>
                        <td><form:input path="key"/>
                            <form:errors path="key" /></td>
                        <td>- Aakkoselliseen järjestämiseen käytettävä avain kuin kun tekijä- ja editori-kentät puuttuvat</td>
                    </tr>
                      <th>Journal</th>
                        <td><form:input path="journal"/>
                            <form:errors path="journal" /></td>
                        <td>- Journalin tai lehden nimi jos artikkeli </td>
                    </tr>
                    <tr>
                        <th>Tags</th>
                        <td><form:input path="tags"/>
                            <form:errors path="tags" /></td>
                        <td>- Asiaan liittyvät tagit</td>
                    </tr>
                    
                </tbody>
            </table>
            <input type="submit" id="submit" value="${button}">
        </form:form>
    </body>
</html>