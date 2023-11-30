<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<script type="text/javascript" charset="UTF-8" src="../js/hoge.js"></script>
</head>
<body>
	<p>第${ordinalNum }回${tournamentName }</p>
		<p>${dateStr }(${eventDayOfWeek })　${typeStr }　<c:choose><c:when test="${child == 'true'}">こども券</c:when><c:when test="${child == 'false'}">おとな券</c:when></c:choose>　${price }円　${step }段　${number }番</p>
		<input type="text" value="${url }" id="copyUrl" readonly>
		<button onclick="copyUrl()">リンクをコピー</button>
	<form action="TicketDisplay" method="get">
		<button type="submit">戻る</button>
	</form>
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
