<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規会員登録確認</title>
    <link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
</head>
<body>
    <div class="container">
        <h1>新規会員登録確認</h1>
        <form id="registrationForm" action="ProvisionalSignup" method="post">
            <p class="signup-label"><label for="name">氏名</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("name") %></span>

            <p class="signup-label"><label for="mail">メールアドレス</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("mail") %></span>

            <p class="signup-label"><label for="tel">電話番号</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("tel") %></span>

            <p class="signup-label"><label for="password">パスワード</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("password") %></span>

            <input type="button" value="戻る" onclick="history.back();" class="submit-button">
            <input type="submit" value="登録" class="submit-button">
        </form>
    </div>
</body>
</html>
