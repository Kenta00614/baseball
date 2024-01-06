<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<body>
	<p>試合情報登録　完了</p>
	<form action="MatchRegistrationInput" method="get">
		<button type="submit">続けて登録</button>
	</form>
	<form action="MatchInformation" method="post">
		<input type="hidden" value="${tournamentId }" name="tournamentId">
		<button type="submit">OK</button>
	</form>
</body>
</html>
