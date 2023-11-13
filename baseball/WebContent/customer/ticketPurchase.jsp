<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
	<div class="header">

	<%-- 第何回　～大会のヘッダー --%>
    <h2>第${lastTour.ordinalNum }回${lastTour.name }</h2>
    <c:forEach var="mat" items="${match}">
    <c:choose>

    <%-- 販売日の7日以前は表示しない --%>
    	<c:when test="${!mat.dispFlg }">
    	</c:when>
    	<c:otherwise>
    		<p>受付期間${mat.saleStartAt}(${mat.saleDayOfWeek})～${mat.eventDate }(${mat.eventDayOfWeek})</p>
    	<p>開催日:${mat.eventDate}(${mat.eventDayOfWeek})</p>

    	<%-- 10時になったら受付中にしてボタン表示する --%>
    	<c:choose>
    		<c:when test="${!mat.saleFlg }">受付前</c:when>
    		<c:otherwise>受付中

    		<%-- POSTでmatchIdを渡す --%>
	    		<form action="TicketApplication" method="post">
		   			<input type="hidden" name="matchId" value="${mat.matchId }">
		    		<button type="submit">申し込む</button>
				</form>
    		</c:otherwise>
    	</c:choose>

    	<%-- 一区切りの線 --%>
    	<hr>
    	</c:otherwise>
    </c:choose>
    </c:forEach>
   </div>
</body>
</html>