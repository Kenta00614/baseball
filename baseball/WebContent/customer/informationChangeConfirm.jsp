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
            <label for="name">氏名</label>
            <input type="text" id="name" name="name" value="${requestScope.name}" class="field" required readonly>

            <label for="tel">電話番号</label>
            <input type="tel" id="tel" name="tel" value="${requestScope.tel}" pattern="\d{10}|\d{11}" class="field" required readonly>

            <!-- 戻るボタン -->
            <input type="button" value="戻る" onclick="history.back();" class="submit-button">

            <!-- 確定ボタン -->
            <input type="submit" value="確定" class="submit-button">
        </form>
    </div>
</body>
</html>
