<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<body>
	<form action="MatchInformation" method="post">
		<p>試合情報の変更が完了しました。</p>
		<input type="hidden" value="${tournamentId }" name="tournamentId">
		<button type="submit">OK</button>
	</form>
</body>
</html>
