<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>高校情報表示</title>
</head>
<body>
    <h2>登録済み高校情報</h2>
    <c:if test="${not empty schools}">
        <ul>
            <c:forEach var="school" items="${schools}">
                <li>${school.name}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
