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
	<div class="header">

	<%-- 第何回　～大会のヘッダー --%>
	<c:if test="${fn:length(match) > 0}"><h1>第${tour.ordinalNum }回${tour.name }</h1></c:if>

    <%-- 試合日情報がないとき --%>
    <c:if test="${fn:length(match) <= 0}"><h1>販売中のチケットはありません</h1></c:if>
    <c:forEach var="mat" items="${match}">
	    <c:choose>

	    <%-- 販売日の7日以前は表示しない --%>
	    <c:when test="${!mat.dispFlg }">
	    </c:when>

	    <c:otherwise>
	    	<div class="pre-order">
	    		<p>受付期間${mat.saleStartAt}(${mat.saleDayOfWeek})～${mat.eventDate }(${mat.eventDayOfWeek})</p>
		    	<p>開催日:${mat.eventDate}(${mat.eventDayOfWeek})</p>

		    	<%-- 10時になったら受付中にしてボタン表示する --%>
		    	<div class="apply">
		    	<c:choose>
		    		<c:when test="${!mat.saleFlg }"><p class="before-reception" >受付前</p></c:when>
		    		<c:otherwise><p class="now-reception">受付中</p>

		    		<%-- POSTでmatchIdを渡す --%>
			    	<form action="TicketApplication" method="post">
				   		<input type="hidden" name="matchId" value="${mat.matchId }">
				   		<input type="hidden" name="remaining" value=-1>
				   		<button type="submit" class="apply-btn">申し込む</button>
					</form>
		    		</c:otherwise>
		    	</c:choose>
	    		</div>

	    	<%-- 一区切りの線 --%>
	    	<hr>
	    	</div>
	    </c:otherwise>
	    </c:choose>
    </c:forEach>
   </div>
   <c:if test="${canselPurchase == 1 }">
	   <script>
	      	alert('セッションが切れています、もう一度最初から購入してください。');
	   </script>
   </c:if>
</body>
</html>