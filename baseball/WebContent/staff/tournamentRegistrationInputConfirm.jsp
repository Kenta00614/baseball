<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>大会情報登録確認</title>
    <meta charset="UTF-8">
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
            max-width: 400px;
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
            display: inline-block;
            margin-bottom: 5px;
            font-weight: bold;
            width: 100px;
        }

        .form-input {
            display: inline-block;
            width: calc(100% - 120px);
            padding: 10px;
            box-sizing: border-box;
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
    <h2>大会情報登録　確認画面</h2>
    <div class="form-container">
        <form action="TournamentRegistrationInput" method="post">
            <div class="control">
                <label for="year">開催年:</label>
                <p class="form-input">${requestScope.year}</p>
            </div>
            <div class="control">
                <label for="ordinalNum">第何回:</label>
                <p class="form-input">${requestScope.ordinalNum}</p>
            </div>

            <div class="control">
                <label for="season">大会の時期:</label>
                <p class="form-input">${requestScope.season}</p>
            </div>

            <div class="control">
                <label for="name">大会名:</label>
                <p class="form-input">${requestScope.name}</p>
            </div>

            <input type="hidden" name="year" value="${requestScope.year}">
            <input type="hidden" name="ordinalNum" value="${requestScope.ordinalNum}">
            <input type="hidden" name="season" value="${requestScope.season}">
            <input type="hidden" name="name" value="${requestScope.name}">

            <button type="submit">登録</button>
        </form>
    </div>
</div>
</body>
</html>
