<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<form action="TicketDisplay" method="get">
		<button type="submit" class="return-btn">戻る</button>
	</form>
	<h1 class="select-title">第${ordinalNum }回${tournamentName }</h1>
		<table  class="ticket-shard">
			<tr><th class="share-th">チケット内容</th></tr>
			<tr><td class="share-td" style="text-align:center;   display: block;margin-left: auto;margin-right: auto ">${dateStr }(${eventDayOfWeek })　${typeStr }　<c:choose><c:when test="${child == 'true'}">こども券</c:when><c:when test="${child == 'false'}">おとな券</c:when></c:choose>　<fmt:formatNumber value="${price }" type="CURRENCY" currencySymbol="¥" maxFractionDigits="0" groupingUsed="true" />円　${step }段　${number }番</td></tr>
			<tr><td class="share-td" ><input type="text" value="${url }" id="copyUrl" readonly class="shared-link"><button onclick="copyUrl()" class="copy-btn">Copy</button></td></tr>
		</table>

	<%-- リンクをコピーするjs --%>
	<script>
		function copyUrl() {
			var copyUrl = document.getElementById("copyUrl");
            copyUrl.select();
            document.execCommand("Copy");
		}
	</script>
</body>
</html>
