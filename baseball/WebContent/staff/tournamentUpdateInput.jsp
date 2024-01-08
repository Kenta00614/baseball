<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* カスタムスタイル */
        body {
            padding: 50px;
        }
        form {
            max-width: 500px;
            margin: auto;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>大会情報変更</h1>
        <form action="TournamentUpdate" method="post">
            <%-- セッションから大会情報を取得 --%>
            <c:set var="tournament" value="${sessionScope.tournament}" />

            <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />

            <div class="form-group">
                <label for="year">年:</label>
                <input type="text" class="form-control" id="year" name="year" value="${tournament.year}" required />
            </div>

            <div class="form-group">
                <label for="ordinalNum">第何回:</label>
                <input type="text" class="form-control" id="ordinalNum" name="ordinalNum" value="${tournament.ordinalNum}" required />
            </div>

            <div class="form-group">
                <label for="name">大会名:</label>
                <input type="text" class="form-control" id="name" name="name" value="${tournament.name}" required />
            </div>

            <div class="form-group">
                <label for="season">季節:</label>
                <input type="text" class="form-control" id="season" name="season" value="${tournament.season}" required />
            </div>

            <button type="submit" class="btn btn-primary">変更</button>
        </form>
    </div>
</body>
</html>
