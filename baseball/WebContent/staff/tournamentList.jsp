<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <h1>大会情報一覧</h1>
    <c:forEach var="tournament" items="${tournamentList}">
        <div class="header">
            <form action="HighschoolRegistration" method="get">
                <!-- 大会名の表示 -->
                <button type="submit">${tournament.name}</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
