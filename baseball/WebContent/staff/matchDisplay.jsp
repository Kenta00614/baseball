<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff;
        }

        .header {
            background-color: #DCDCDC;
            color: #555555;
            padding: 10px; /* Adjust padding */
            text-align: center;
            margin: 10px; /* Add margin for spacing */
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        button {
            background-color: #ff6347;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
    </style>
</head>

<body>
    <h1>大会情報一覧</h1>
    <c:forEach var="tournament" items="${tournamentList}">
        <div class="header">
            <!-- 大会名とIDを含むボタン -->
            <form action="MatchInformation" method="post">
                <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />
                <button type="submit">${tournament.year }年 第${tournament.ordinalNum }回 ${tournament.season } ${tournament.name}</button>
            </form>
        </div>
    </c:forEach>
</body>

</html>
