<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>高校情報表示</title>
    <meta charset="UTF-8">
</head>
<body>
    <h2>登録済み高校情報</h2>
    <form action="HighschoolEdit" method="get">
        <c:if test="${not empty schools}">
            <ul>
                <c:forEach var="school" items="${schools}">
                    <li>
                        <input type="radio" name="schoolId" value="${school.schoolId}" /> ${school.name}
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <input type="submit" value="変更" />
    </form>
</body>
</html>
