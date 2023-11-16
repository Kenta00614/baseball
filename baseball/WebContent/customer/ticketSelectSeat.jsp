<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<form action="TicketSelectAll" method="post">
		<input type="hidden" name="matchId" value="${matchId }">
		<input type="hidden" name="count" value="${count }">
		<input type="hidden" name="seat" value="${seat }">
		<button type="submit">戻る</button>
	</form>

	<form action="TicketConfirm" method="get">
		<button type="submit">次へ</button>
	</form>
</body>
</html>
