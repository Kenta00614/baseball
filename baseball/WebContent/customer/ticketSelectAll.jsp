<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<%@include file="/js/ticketSelectAll.js" %>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
<%-- 戻るボタン --%>
	<form action="TicketApplication" method="post">
		<input type="hidden" name="matchId" value="${matchId }">
        <input type="hidden" name="seat" value="${seat }">
        <input type="hidden" name="count" value="${count }">
    	<button type="submit">戻る</button>
	</form>

	<form action="TicketSelectBlock" method="post">
	<script type="text/javascript" src="../js/dialog.js" charset="UTF-8">
</script>
	ctx.strokeRect(0,0,80,80)
		<button type="submit">次へ</button>
	</form>
</body>
</html>
