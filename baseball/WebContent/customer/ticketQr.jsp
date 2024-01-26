<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.awt.image.BufferedImage,utils.BufferedImageUtil" %>
<% BufferedImage  bImage=(BufferedImage )request.getAttribute("bImage"); %>
<%@include file="header.jsp"%>


<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<%-- 戻るボタン --%>
	<form action="TicketDisplay" method="get">
		<button type="submit" class="return-btn">戻る</button>
	</form>
	<div class="ticket-qr">
	<%-- QRコード --%>
	<c:if test="${DispQrFlg == 0 }">
		<img src="<%= BufferedImageUtil.convert2DataURI(bImage, "png") %>" class="qr-img"/>
	</c:if>
	<c:if test="${DispQrFlg == 1 }">
		このチケットは使用期限が過ぎています。
	</c:if>
	<%-- 情報 --%>
	<p class="qr-memo">第${ordinalNum }回${tournamentName }</p>
	<p class="qr-memo">${dateStr }(${eventDayOfWeek }) 08:00</p>
	</div>
</body>
</html>
