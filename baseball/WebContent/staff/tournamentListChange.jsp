<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <h1>大会名の編集</h1>
    <form action="TournamentChange" method="post">
        <input type="hidden" name="tournamentId" value="${param.tournamentId}" />
        <input type="text" name="newName" placeholder="新しい大会名" />
        <button type="submit">更新</button>
    </form>
</body>
</html>
