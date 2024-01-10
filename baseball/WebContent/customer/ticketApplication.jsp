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

<%-- 前へ戻るボタン --%>
	<br><a href="TicketPurchase" type="submit" class="return-btn">戻る</a>
<%-- 大会名 --%>
    <h1>第${tour.ordinalNum }回　${tour.name }</h1>

	<div class="ticket-summary">
	<table>
	<%-- 日付 --%>
		<tr><th>公演名</th><td><b>第${tour.ordinalNum }回${tour.name }</b></td></tr>
	    <tr><th>開催日</th><td>${match.eventDate }(${match.eventDayOfWeek })</td></tr>
	    <tr><th>会場</th><td>甲子園球場</td></tr>
	    <tr><th>開始時刻</th><td>${match.eventDate }(${match.eventDayOfWeek })　8:00~(開場7:00)</td></tr>
	    <tr><th>注意事項</th><td>一回のお申し込みで6枚まで指定できます</td></tr>
	    <tr><th>座種・料金</th>
	    	<td>
	    		　　中央指定席　1・3塁指定席　外野指定席(レフト・ライト)<br>
	    		大人　&yen;4,200　　　　&yen;3,700　　　　　　&yen;700<br>
	    		子供　&yen;4,200　　　　&yen;1,200　　　　　　&yen;200<br>
	    	</td></tr>
	    	<%--<td>中央指定席: 大人・子供 &yen;4,200<br>
	    		1・3塁指定席: 大人 &yen;3,700 子供 &yen;1,200<br>
	    		 アルプス席: &yen;1,400<br>  ←必要ないかも
	    		外野指定席(レフト・ライト): 大人 &yen;700 子供 &yen;200</td></tr>--%>
	    <tr><th>支払方法</th><td>paypal</td></tr>
	    <tr><th>受け取り方法</th><td>QRチケット</td></tr>
	</table></div><br>


	<div class="seat-selection">
		<h2 class="content-selection">申し込み内容選択</h2>
	<%-- 座席選択 --%>
	    <form action="TicketSelectAll" method="post">
	<%-- 残数があれば表示 --%>
		<c:choose>
			<c:when test="${remaining == 0}">
				<b class="tiket-soldout">※完売しています。</b>
			</c:when>
			<c:when test="${remaining > 0}">
	      		<p class="tiket-rest">残数：${remaining }枚</p>
	      	</c:when>
		</c:choose>

		<p class="ticket-element">座種：
			<select name="seat">
			<c:forEach begin="0" end="${fn:length(seatType)-1}" step="1" var="i">
				<option value="${seatOrder[i] }">${seatType[i] }</option>
			</c:forEach>
			</select>
		</p>
		<p class="ticket-element">枚数：
			<select name="count">
				<c:forEach begin="1" end="6" step="1" var="i">
				<option value="${i }">${i }</option>
				</c:forEach>
			</select>
		</p>
		   	<button type="submit" class="choice-button">座席選択へ進む</button>
		</form>
	</div>
</body>
</html>