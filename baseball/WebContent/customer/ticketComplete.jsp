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

    <%-- 決済行われなかったとき --%>
    <c:if test="${selTicketsData == null}">
    	<p>決済が正常に行われませんでした</p>
    </c:if>

	<%-- 決済行われたとき --%>
	<c:forEach var="data" items="${selTicketsData }">
		<p>決済が完了しました</p>
		<p>申込内容</p>
		<p>${data.seat.typeStr }　${data.seat.step }段　${data.seat.number }番　${data.seat.gate}ゲート　${data.seat.passage}通路</p>
	</c:forEach>
	<form action="Main" method="get">
		<button type="submit">メインメニューに戻る</button>
	</form>
</body>
</html>
