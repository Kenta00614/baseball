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
<%-- 戻るボタン --%>
	<form action="TicketApplication" method="post">
    	<button type="submit" class="return-btn">戻る</button>
	</form>
<%-- 大会名 --%>
    <h1>第${tour.ordinalNum }回　${tour.name }</h1>

<%-- 指定された座種の画像。クソデカなのでclass="seat-type-img"で指定してます --%>
<c:choose>
	<c:when test="${seat == '0B' }">
		<img src="${pageContext.request.contextPath}/customer/image/toshiba_green_page.jpg" alt="バックネット裏" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0F' }">
		<img src="${pageContext.request.contextPath}/customer/image/infield1.jpg" alt="内野一塁側" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0T' }">
		<img src="${pageContext.request.contextPath}/customer/image/infield3.jpg" alt="内野三塁側" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0R' }">
		<img src="${pageContext.request.contextPath}/customer/image/right.jpg" alt="外野ライト" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0L' }">
		<img src="${pageContext.request.contextPath}/customer/image/left.jpg" alt="外野レフト" class="seat-type-img" />
	</c:when>
</c:choose>
    <hr>

    <c:if test="${remain <= count && remain != -1}">
    	購入枚数分用意できません
    </c:if>



<%-- ブロック選択 --%>
	<form action="TicketSelectSeat" method="post">
	<p>ブロック選択：
<%-- ドロップダウン --%>
		<select name="block">
			<c:forEach begin="0" end="${fn:length(blocks)-1 }" step="1" var="i">
				<option value="${blocks[i] }">${ fn:substring(blocks[i], 2, fn:length(blocks[i]))} </option>
			</c:forEach>
		</select>
	</p>
		<button type="submit" class="home-btn">次へ</button>
	</form>
</body>
</html>