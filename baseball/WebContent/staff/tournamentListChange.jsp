<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <h1>大会名の編集</h1>
    <c:forEach var="tournament" items="${tournamentList}">
        <form action="TournamentListChange" method="post">
            <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />
            <label for="name${tournament.tournamentId}">大会名:</label>
            <input type="text" name="name" id="name${tournament.tournamentId}" value="${tournament.name}" />
            <button type="submit">更新</button>
        </form>
    </c:forEach>
</body>
</html>
