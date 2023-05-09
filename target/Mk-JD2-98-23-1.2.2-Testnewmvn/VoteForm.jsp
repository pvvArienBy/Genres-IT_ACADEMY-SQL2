<%@ page import="by.it_academy.jd2.Mk_JD2_98_23.web.TestServ" %><%--
  Created by IntelliJ IDEA.
  User: LIR0821
  Date: 09.04.2023
  Time: 04:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Voting Form</title>
</head>
<body>
<h1>Voting Form</h1>
<form action="vote" method="POST">
    <label for="performer">Best Performer:</label>
    <select name="performer" id="performer" required>
        <%@  page import="by.it_academy.jd2.Mk_JD2_98_23.web.VotingData" %>
        <%@ page import="by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO" %>
        <%VotingData votingData = new VotingData();%>
        <%for (ArtistDTO artist : votingData.getArtists()) { %>
        <option value=<%=artist.getId()%>><%=artist.getName()%></option>
        <%}%>
    </select>
    <br><br>

    <label>Your Favorite Genres (Select 3-5 options):</label>

        <%@  page import="by.it_academy.jd2.Mk_JD2_98_23.web.VotingData" %>
        <%@ page import="by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO" %>

        <%for (GenreDTO genre : votingData.getGenres()) { %>
    <br><input type="checkbox" name="genres" id="genre1" value=<%=genre.getId()%>>
    <label for="genre1"><%=genre.getName()%></label>
        <%}%>
    <br><br>

    <label for="text">Short Text About You:</label>
    <br>
    <textarea name="text" id="text" rows="4" cols="50" required></textarea>
    <br><br>

    <input type="submit" value="Submit Vote">
</form>
</body>
</html>