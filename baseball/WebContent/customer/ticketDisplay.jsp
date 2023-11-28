<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<h2>購入済みチケット一覧</h2>
	<c:forEach var="ticket" items="${tickets }">
		<p>第${ticket.ordinalNum }回${ticket.tournamentName }</p>
		<p>${ticket.eventDate }(${ticket.eventDayOfWeek })　${ticket.step }段　${ticket.number }番</p>
		<form action="TicketShare" method="get">
			<button type="submit">共有</button>
		</form>
		<form action="TicketQr" method="get">
			<button type="submit">QRコード</button>
		</form>
	</c:forEach>
	<hr>
</body>
</html>
