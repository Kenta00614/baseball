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
	<form action="MemberInformation" method="get">
		<button type="submit">戻る</button>
	</form>
	<h2>購入履歴</h2>
	<c:if test="${fn:length(ticketNumList)<1 }">
		<p>購入情報はありません</p>
	</c:if>
	<p>大会名　開催日　購入日時　枚数　価格</p>
	<c:forEach begin="0" end="${fn:length(ticketNumList)-1 }" step="1" var="i">
		<p>	第${purchaseList[i].ordinalNum }回${purchaseList[i].tournamentName }　${purchaseList[i].dateStr }　${purchaseList[i].purchaseStr }　${ticketNumList[i] }　${ticketPrice[i] }円</p>
	</c:forEach>
</body>
</html>
