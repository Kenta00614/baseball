<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5F5F5;
        }

        .header {
            background-color: #F5F5F5;
            color: #555555;
            padding: 0.1px 0;
            text-align: center;
        }

        .tournament-btn-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .tournament-btn {
            overflow: hidden;
            position: relative;
            margin: 10px;
        }

        .tournament-btn button {
            background-color: #0066FF;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
        }

    </style>
</head>

<body>
    <div class="header">
        <h1>大会情報一覧</h1>
    </div>
    <div class="tournament-btn-container">
        <c:forEach var="tournament" items="${tournamentList}">
            <div class="tournament-btn">
                <form action="MatchInformation" method="post">
                    <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />
                    <button type="submit">${tournament.year}年 第${tournament.ordinalNum}回 ${tournament.season} ${tournament.name}</button>
                </form>
            </div>
        </c:forEach>
    </div>
    <div class="aaa">
        <form action="TournamentListChangeDisplay" method="post">
            <button type="submit">変更</button>
        </form>
    </div>
</body>

</html>
