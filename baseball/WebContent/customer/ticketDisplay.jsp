<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<h2>購入済みチケット一覧</h2>
	<c:if test="${fn:length(tickets) == 0 }">購入済みのチケットはありません</c:if>
	<c:forEach var="ticket" items="${tickets }">
		<p>第${ticket.ordinalNum }回${ticket.tournamentName }</p>
		<p>${ticket.dateStr }(${ticket.eventDayOfWeek })　${ticket.typeStr }　<c:choose><c:when test="${ticket.child }">こども券</c:when><c:when test="${!ticket.child }">おとな券</c:when></c:choose>　${ticket.price }円　${ticket.step }段　${ticket.number }番</p>
		<form action="TicketShare" method="get">
			<button type="submit">共有</button>
		</form>
		<form action="TicketQr" method="post">
			<input type="hidden" value="${ticket.ticketsId }" name="ticketsId">
			<input type="hidden" value="${ticket.ordinalNum }" name="ordinalNum">
			<input type="hidden" value="${ticket.tournamentName }" name="tournamentName">
			<input type="hidden" value="${ticket.dateStr }" name="dateStr">
			<input type="hidden" value="${ticket.eventDayOfWeek }" name="eventDayOfWeek">
			<input type="hidden" value="${ticket.eventDate }" name="eventDate">
			<button type="submit">QRコード</button>
		</form>
	</c:forEach>
</body>
</html>
