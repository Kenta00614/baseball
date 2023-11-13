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
    	<p>${mat.saleStartAt}～</p>
    	<p>${mat.eventDate}</p>
   		<form action="TicketApplication" method="get">
    		<button type="submit" value="${mat.matchId }">申し込む</button>
		</form>
    	<hr>
    </c:forEach>
   </div>
</body>
</html>
