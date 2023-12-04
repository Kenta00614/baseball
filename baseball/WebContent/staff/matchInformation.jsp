<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<c:choose>
<c:when test="${matchList == null }">
	試合情報が登録されていません
</c:when>
<c:when test="${matchList != null }">
	<h2>試合情報</h2>
    <c:forEach var="matchList" items="${matchList}">

    </c:forEach>
    </c:when>
</c:choose>
</body>
</html>