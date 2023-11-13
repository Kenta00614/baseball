<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<hr>
	<hr>
	<div class="header">
    <h2>第${lastTour.ordinalNum }回${lastTour.name }</h2>
    <c:forEach var="mat" items="${match}">
    	<p>販売期間${mat.saleStartAt}(${mat.saleDayOfWeek})～${mat.eventDate }(${mat.eventDayOfWeek})</p>
    	<p>${mat.eventDate}(${mat.eventDayOfWeek})</p>
   		<form action="TicketApplication" method="post">
   			<input type="hidden" name="matchId" value="${mat.matchId }">
    		<button type="submit">申し込む</button>
		</form>
    	<hr>
    </c:forEach>
   </div>
</body>
</html>
