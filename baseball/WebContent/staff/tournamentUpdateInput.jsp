<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ja">

<head>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
            padding: 0.1px;
            text-align: center;
        }

        .form-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .control {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #000;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            cursor: pointer;
        }

        button {
            background-color: #0066FF;
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
    <div class="header">
        <h1>大会情報変更</h1>
    </div>

    <div class="form-container">
        <form action="TournamentUpdate" method="post">
            <%-- セッションから大会情報を取得 --%>
            <c:set var="tournament" value="${sessionScope.tournament}" />

            <input type="hidden" name="tournamentId" value="${tournament.tournamentId}" />

            <div class="control">
                <label for="year">開催年</label>
                <input type="text" class="form-control" id="year" name="year" value="${tournament.year}" required />
            </div>

            <div class="control">
                <label for="ordinalNum">第何回</label>
                <input type="text" class="form-control" id="ordinalNum" name="ordinalNum" value="${tournament.ordinalNum}" required />
            </div>

            <div class="control">
                <label for="name">大会名</label>
                <input type="text" class="form-control" id="name" name="name" value="${tournament.name}" required />
            </div>

            <div class="control">
                <label for="season">開催時期</label>
                <input type="text" class="form-control" id="season" name="season" value="${tournament.season}" required />
            </div>

            <button type="submit" class="btn btn-primary">変更</button>
        </form>
    </div>
</body>

</html>
