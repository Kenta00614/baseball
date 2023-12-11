<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <h1>大会情報変更</h1>
    <form action="TournamentUpdate" method="post">
        <%-- セッションから大会情報を取得 --%>
        <c:set var="tournament" value="${sessionScope.tournament}" />

        <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />
        <label for="year">年:</label>
        <input type="text" id="year" name="year" value="${tournament.year}" required />
        <label for="ordinalNum">第何回:</label>
        <input type="text" id="ordinalNum" name="ordinalNum" value="${tournament.ordinalNum}" required />
        <label for="name">大会名:</label>
        <input type="text" id="name" name="name" value="${tournament.name}" required />
        <label for="name">季節:</label>
        <input type="text" id="season" name="season" value="${tournament.season}" required />

        <button type="submit">変更</button>
    </form>
</body>
</html>
