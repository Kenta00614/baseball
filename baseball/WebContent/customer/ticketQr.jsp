<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<form action="TicketDisplay" method="get">
		<button type="submit">戻る</button>
	</form>
	<p>第${ordinalNum }回${tournamentName }</p>
	<p>${dateStr }(${eventDayOfWeek }) 08:00</p>
</body>
</html>
