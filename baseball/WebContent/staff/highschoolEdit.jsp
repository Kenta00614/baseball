<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>高校情報編集</title>
</head>
<body>
    <h2>高校情報編集</h2>
    <form action="HighschoolUpdate" method="post">
        <input type="hidden" name="schoolId" value="${schoolId}" />
        <div>
            <label for="schoolName">高校名:</label>
            <input type="text" name="schoolName" id="schoolName" value="${schoolName}" />
        </div>
        <div>
            <input type="submit" value="保存">
        </div>
    </form>
</body>
</html>
