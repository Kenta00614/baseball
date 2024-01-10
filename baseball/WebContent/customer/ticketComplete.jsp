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
	<%-- 大会名 --%>
    <h1>第${tour.ordinalNum }回　${tour.name }</h1>

	<c:choose>
	    <%-- 決済行われなかったとき --%>
	    <c:when test="${fn:length(selTicketsData) == 0}">
	    	<p style="font-size:20px;">決済が正常に行われませんでした<p>
	    </c:when>
		<c:when test="${selTicketsData != null}">
			<%-- 決済行われたとき --%>
			<p style="font-size:20px;">決済が完了しました。ご購入ありがとうございます。</p>
			<table>
			<tr><th>申込内容</th></tr>
			<c:forEach var="data" items="${selTicketsData }">

			<tr><td style="text-align:center;">${data.seat.typeStr }　${data.seat.step }段　${data.seat.number }番　${data.seat.gate}ゲート　${data.seat.passage}通路</td></tr>

			</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<form action="Main" method="get">
		<button type="submit" class="home-btn">メインメニューに戻る</button>
	</form>
</body>
</html>
