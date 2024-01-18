<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員情報変更確認</title>
    <link rel="stylesheet" type="text/css" href="/baseball/css/Customer.css">
</head>
<body>
    <div class="container">
        <h1>会員情報変更確認</h1>
        <form id="memberChangeConfirmForm" action="InformationChange" method="post">
            <p class="signup-label"><label for="name">氏名</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("name") %></span>

            <p class="signup-label"><label for="tel">電話番号</label></p>
            <!-- サーバーサイドで受け取った値を表示 -->
            <span><%= request.getParameter("tel") %></span>




            <!-- 確定ボタン -->
            <input type="submit" value="確定" class="submit-button">
            <!-- 戻るボタン -->
            <input type="button" value="戻る" onclick="history.back();" class="submit-button">
        </form>
    </div>
</body>
</html>
