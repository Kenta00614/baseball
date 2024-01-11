<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<body>
    <form action="MatchUpdateInput" method="post">
		<input type="hidden" value="${eventDate }" name="date">
		<button type="submit">戻る</button>
	</form>

	<form action="MatchInformation" method="post">
		<input type="hidden" value="${tournamentId }" name="tournamentId">
		<input type="hidden" value="${eventDate }" name="delEventDate">
		<button type="submit">確認</button>
	</form>
</body>
</html>
