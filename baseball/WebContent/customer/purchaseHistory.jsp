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
	<h1>購入履歴</h1>
	<c:choose>
		<c:when test="${fn:length(ticketNumList)<1 }">
			<p>購入情報はありません</p>
		</c:when>
		<c:otherwise>
			<table class="history-list">
			<tr>
				<th style="width:120em;">大会名</th>
				<th style="width:70em;">開催日</th>
				<th style="width:70em;">購入日時</th>
				<th style="width:3px;">枚数</th>
				<th style="width:30em;">価格</th>
			</tr>
			<c:forEach begin="0" end="${fn:length(ticketNumList)-1 }" step="1" var="i">
				<tr>
					<td>第${purchaseList[i].ordinalNum }回<br>${purchaseList[i].tournamentName }</td>
					<td>　${purchaseList[i].dateStr }</td><td>${purchaseList[i].purchaseStr }</td>
					<td>${ticketNumList[i] }枚</td>
					<td><fmt:formatNumber value="${ticketPrice[i] }" maxFractionDigits="0" groupingUsed="true" />円</td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
