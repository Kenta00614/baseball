<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<h1>購入済みチケット一覧</h1>

	<%-- 購入チケットがないとき --%>
	<c:if test="${fn:length(tickets) == 0 && fn:length(refundTickets) == 0}">購入済みのチケットはありません</c:if>



	<c:forEach var="refundTicket" items="${refundTickets }">
	<div class=ticket-list>
		<%--購入チケットの情報 --%>
		<h3>第${refundTicket.ordinalNum }回${refundTicket.tournamentName }</h3>
		<div class="ticket-show">
			<div class="ticket-item">
			<p>${refundTicket.dateStr }(${refundTicket.eventDayOfWeek })<br>　${refundTicket.typeStr }　<c:choose><c:when test="${refundTicket.child }">こども券</c:when><c:when test="${!refundTicket.child }">おとな券</c:when></c:choose>　<fmt:formatNumber value="${refundTicket.price }" type="CURRENCY" currencySymbol="¥" maxFractionDigits="0" groupingUsed="true" />円　${refundTicket.step }段　${refundTicket.number }番<br>
			　チケットID：${refundTicket.ticketsId }</p>

			<%-- チケット共有がされているとき表示 --%>
			<c:if test="${refundTicket.shared }"><p style="color:#FF6666;font-weight: bold;">※共有済みチケットです</p></c:if>
			</div>
			</div>
		</div>
	</div>
	</c:forEach>


	<c:forEach var="ticket" items="${tickets }">
	<div class=ticket-list>
		<%--購入チケットの情報 --%>
		<h3>第${ticket.ordinalNum }回${ticket.tournamentName }</h3>
		<div class="ticket-show">
			<div class="ticket-item">
			<p>${ticket.dateStr }(${ticket.eventDayOfWeek })<br>　${ticket.typeStr }　<c:choose><c:when test="${ticket.child }">こども券</c:when><c:when test="${!ticket.child }">おとな券</c:when></c:choose>　<fmt:formatNumber value="${ticket.price }" type="CURRENCY" currencySymbol="¥" maxFractionDigits="0" groupingUsed="true" />円　${ticket.step }段　${ticket.number }番</p>

			<%-- チケット共有がされているとき表示 --%>
			<c:if test="${ticket.shared }"><p style="color:#FF6666;font-weight: bold;">※共有済みチケットです</p></c:if>
			</div>


			<div class="ticket-btn">
			<%-- 共有ボタン --%>
			<form action="TicketShare" method="post">
				<input type="hidden" value="${ticket.ticketsId }" name="ticketsId">
				<input type="hidden" value="${ticket.ordinalNum }" name="ordinalNum">
				<input type="hidden" value="${ticket.tournamentName }" name="tournamentName">
				<input type="hidden" value="${ticket.dateStr }" name="dateStr">
				<input type="hidden" value="${ticket.eventDayOfWeek }" name="eventDayOfWeek">
				<input type="hidden" value="${ticket.typeStr }" name="typeStr">
				<input type="hidden" value="${ticket.child }" name="child">
				<input type="hidden" value="${ticket.price }" name="price">
				<input type="hidden" value="${ticket.step }" name="step">
				<input type="hidden" value="${ticket.number }" name="number">
				<button type="submit" class="ticketElement-btn">共有</button>
			</form>

			<%-- QRコードボタン --%>
			<form action="TicketQr" method="post">
				<input type="hidden" value="${ticket.ticketsId }" name="ticketsId">
				<input type="hidden" value="${ticket.ordinalNum }" name="ordinalNum">
				<input type="hidden" value="${ticket.tournamentName }" name="tournamentName">
				<input type="hidden" value="${ticket.dateStr }" name="dateStr">
				<input type="hidden" value="${ticket.eventDayOfWeek }" name="eventDayOfWeek">
				<input type="hidden" value="${ticket.eventDate }" name="eventDate">
				<button type="submit" class="ticketElement-btn">QRコード</button>
			</form>
			</div>
		</div>
	</div>
	</c:forEach>
</body>
</html>
