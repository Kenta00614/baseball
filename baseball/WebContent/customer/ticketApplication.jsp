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
<%-- 日付 --%>
    <p>${match.eventDate }(${match.eventDayOfWeek })</p>
    <p>会場：甲子園球場</p>
    <p>開始時刻：${match.eventDate }(${match.eventDayOfWeek })　8:00~(開場7:00)</p>
    <p>注意事項：一回のお申し込みで6枚まで指定できます</p>
    <p>座種・料金：中央指定席:　&yen;4,200</p>
    <p>1・3塁指定席:　&yen;3,700</p>
    <p>アルプス席:　&yen;1,400</p>
    <p>外野指定席(レフト・ライト):　&yen;700</p>
    <p>支払方法：paypal</p>
    <p>受け取り方法：QRチケット</p>

<%-- 前へ戻るボタン --%>
	<form action="TicketPurchase" method="get">
   		<button type="submit">前へ戻る</button>
	</form>

<%-- 座席選択 --%>
    <form action="TicketSelectAll" method="post">
<%-- 残数があれば表示 --%>
	<c:choose>
		<c:when test="${remaining == 0}">
			<p>完売しています。</p>
		</c:when>
		<c:when test="${remaining > 0}">
      		<p>残数：${remaining }枚</p>
      	</c:when>
	</c:choose>

	<p>座種：
		<select name="seat">
		<c:forEach items="${seatType }" var="seat">
			<option value="${seat.key }">${seat.value }</option>
		</c:forEach>
		</select>
	</p>
	<p>枚数：
		<select name="count">
			<c:forEach begin="1" end="6" step="1" var="i">
			<option value="${i }">${i }</option>
			</c:forEach>
		</select>
	</p>
	   	<button type="submit">座席選択へ進む</button>
	   	<input type="hidden" value="${match.matchId }" name="matchId">
	</form>
</body>
</html>