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

        <link href="resources/vihaiset.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="resources/required.js"></script>

    </head>
    <body>
        <h1><a href="${pageContext.request.contextPath}/">Etusivu</a></h1>
        <form:form commandName="reference" action="${pageContext.request.contextPath}/${action}" method="POST">
            <table>
                <tbody>
                    <tr>
                        <th>Viitteen tyyppi</th>
                        <td>
                            Article: <form:radiobutton id="articleButton" path="type" value="article" /><br />
                            Book: <form:radiobutton id="bookButton" path="type" value="book" /><br/>
                            Inproceedings: <form:radiobutton id="inproceedingsButton" path="type" value="inproceedings" />
                        </td>
                    </tr>

                    <tr>
                        <th>Viiteavain</th>
                        <td><form:input id="refkey" path="refkey" />
                            <form:errors path="refkey" /></td>
                        <td> - LaTeXissa käytettävä viiteavain</td>
                    </tr>           
                    <tr>
                        <th>Tekijät</th>
                        <td><form:input id="author" oninput="OnInputAuthor (event)"  path="author" />
                            <form:errors path="author" /></td>
                        <td>- Tekijöiden nimet </td>
                    </tr>
                    <tr>
                        <th>Toimittajat</th>
                        <td><form:input id="editor" oninput="OnInputEditor (event)" path="editor"/>
                            <form:errors path="editor" /></td>
                        <td> - Toimittajien nimet</td>
                    </tr>
                    <tr>
                        <th>Nimi</th>
                        <td><form:input id="title" path="title" />
                            <form:errors path="title" /></td>
                        <td>- Siteerattavan työn nimi</td>
                    </tr>
                    <tr>
                        <th>Kirjan nimi</th>
                        <td><form:input id="booktitle" path="booktitle" />
                            <form:errors path="booktitle" /></td>
                        <td>- Kirjan nimi, jos vain osaa siitä siteerataan</td>
                    </tr>
                    <tr>
                        <th>Sivut</th>
                        <td><form:input id="pages" path="pages" />
                            <form:errors path="pages" /></td>
                        <td>- Sivun numerot joko pilkuilla eroitettuina tai kantama väliviivoilla eroitettuina esim. "7,41,73--97"  </td>
                    </tr>
                    <tr>
                        <th>Osa</th>
                        <td><form:input id="volume" path="volume"/>
                            <form:errors path="volume" /></td>
                        <td>- Mikä osa jos moniosainen kirja</td>
                    </tr>
                    <tr>
                        <th>Numero</th>
                        <td><form:input id="number" path="number"/>
                            <form:errors path="number" /></td>
                        <td>- Julkaisun numero jos sellaista on</td>
                    </tr>
                    <tr>
                        <th>Sarja</th>
                        <td><form:input id="series" path="series"/>
                            <form:errors path="series" /></td>
                        <td>- Sarjan nimi jos kirja on osa kirjasarjaa</td>
                    </tr>
                    <tr>
                        <th>Julkaisija</th>
                        <td><form:input id="publisher" path="publisher"/>
                            <form:errors path="publisher" /></td>
                        <td>- Julkaisijan nimi </td>
                    </tr>
                    <tr>
                        <th>Julkaisijan osoite</th>
                        <td><form:input id="adress" path="address"/>
                            <form:errors path="address" /></td>
                        <td>- Julkaisijan osoite tai kaupunki</td>
                    </tr>
                    <tr>
                        <th>Vuosi</th>
                        <td><form:input id="year" path="year" />
                            <form:errors path="year" /></td>
                        <td>- Julkaisuvuosi tai julkaisettomalle kirjoitusvuosi</td>
                    </tr>
                    <tr>
                        <th>Kuukausi</th>
                        <td><form:input id="month" path="month" type="number" min="1" max="12" />
                            <form:errors path="month" /></td>
                        <td>- Julkaisukuukausi tai julkaisemattomalle kirjoituskuukausi</td>
                    </tr>
                    <tr>
                        <th>Organisaatio</th>
                        <td><form:input id="organization" path="organization"/>
                            <form:errors path="organization" /></td>
                        <td>- Konfrenssia sponsoroiva organisaatio </td>
                    </tr>
                    <tr>
                        <th>Lisätietoja</th>
                        <td><form:input id="note" path="note"/>
                            <form:errors path="note" /></td>
                        <td>- Ylimääräistä lukijaa auttavaa informaatiota</td>
                    </tr>
                    <tr>
                        <th>Järjestämisavain</th>
                        <td><form:input id="key"path="key"/>
                            <form:errors path="key" /></td>
                        <td>- Aakkoselliseen järjestämiseen käytettävä avain kuin kun tekijä- ja editori-kentät puuttuvat</td>
                    </tr>
                <th>Journal</th>
                <td><form:input id="journal" path="journal"/>
                    <form:errors path="journal" /></td>
                <td>- Journalin tai lehden nimi jos artikkeli </td>
            </tr>
            <tr>
                <th>Tags</th>
                <td><form:input id="tags" path="tags"/>
                    <form:errors path="tags" /></td>
                <td>- Asiaan liittyvät tagit</td>
            </tr>

        </tbody>
    </table>
    <input type="submit" id="submit" value="${button}">
</form:form>
    <script>
        //jos tyyppi on book joko author TAI editor ovat pakollisia kenttia
        function OnInputAuthor(event) {
            if (document.getElementById("bookButton").checked) {

                if (event.target.value == "") {


                    document.getElementById("editor").placeholder = "required";

                    document.getElementById("editor").required = "required";
                } else {
                    document.getElementById("editor").placeholder = "";

                    document.getElementById("editor").required = "";
                }
            }

        };

        //jos tyyppi on book joko author TAI editor ovat pakollisia kenttia    
        function OnInputEditor(event) {
            if (document.getElementById("bookButton").checked) {

                if (event.target.value == "") {


                    document.getElementById("author").placeholder = "required";

                    document.getElementById("author").required = "required";
                } else {
                    document.getElementById("author").placeholder = "";

                    document.getElementById("author").required = "";
                }
            }
        };
    </script>
</body>
</html>