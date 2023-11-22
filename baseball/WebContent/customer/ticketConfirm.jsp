<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
<%-- 大会名 --%>
	<h3>第${tour.ordinalNum }回　${tour.name }</h3>
	<hr>
	<p>申込内容</p>
	<p>開催日:${match.eventDate }(${match.eventDayOfWeek })</p>
	<p>座種:${selTicketsData[0].seat.typeStr }</p>
	<c:forEach var="ticket" items="${selTicketsData }">
		<p>${ticket.seat.step }段　${ticket.seat.number }番　${ticket.seat.gate }番ゲート　${ticket.seat.passage }通路　値段:&yen;${price }</p>
	</c:forEach>
	<p>	支払い方法/受け取り方法　QRコード/Paypal</p>
	<c:if test="${spectatorIds[0].point }>0">
		<p>ポイント:${spectatorIds[0].point }(&yen;${spectatorIds[0].point }相当)利用可能</p>
		<input type="number" name="usePoint">
		<button type="button" name="pointBtn">適用</button>
	</c:if>
	<p>ご請求:&yen;<c:if test="${spectatorIds[0].point }>0">(内ポイント利用&yen;0)</c:if></p>

<%-- 戻るボタン --%>
    <div class="header">
        <form action="TicketApplication" method="post">
			<button type="submit">席種・枚数選択に戻る</button>
		</form>
    </div>
    <%-- 購入ボタン --%>
    <div class="header">
        <form action="TicketComplete" method="get">
			<button type="submit">購入</button>
		</form>
    </div>
</body>
</html>
