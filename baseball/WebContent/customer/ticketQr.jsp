<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<button type="submit">戻る</button>
	</form>
	<%-- QRコード --%>
	<img src="<%= BufferedImageUtil.convert2DataURI(bImage, "png") %>"/>
	<%-- 情報 --%>
	<p>第${ordinalNum }回${tournamentName }</p>
	<p>${dateStr }(${eventDayOfWeek }) 08:00</p>
</body>
</html>
