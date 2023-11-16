<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>大会情報登録</title>
    <meta charset="UTF-8">
</head>
<body>
    <div class="header">
        <h2>大会情報登録　入力画面</h2>
        <form action="TournamentRegistrationInput" method="post">
            <div>
                <label for="year">開催年:</label>
                <input type="text" id="year" name="year" required>
            </div>
            <div>
                <label for="ordinalNum">第何回:</label>
                <input type="text" id="ordinalNum" name="ordinalNum" required>
            </div>
            <div>
                <label for="season">大会の時期:</label>
                <input type="text" id="season" name="season" required>
            </div>
            <div>
                <label for="name">大会名:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <button type="submit">登録</button>
        </form>
    </div>
</body>
</html>
