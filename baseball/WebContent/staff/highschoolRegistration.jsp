<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>高校登録</title>
</head>
<body>
    <h2>高校情報登録</h2>
    <form action="HighschoolRegistration" method="post">
        <!-- 大会IDの隠しフィールド -->
        <input type="hidden" name="tournamentId" value="${tournamentId}">
        <% for (int i = 1; i <= 49; i++) { %>
            <div>
                <label for="schoolName<%=i%>">高校名<%=i%>:</label>
                <input type="text" name="schoolName<%=i%>" id="schoolName<%=i%>">
            </div>
        <% } %>
        <div>
            <input type="submit" value="登録">
        </div>
    </form>
</body>
</html>
