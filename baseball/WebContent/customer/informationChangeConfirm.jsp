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
            <p id="name" class="field">${requestScope.name}</p>
            <label for="tel">電話番号</label>
            <p id="tel" class="field">${requestScope.tel}</p>

			<%-- 送る値 --%>
			<input type="hidden" name="name" value="${requestScope.name}">
			<input type="hidden" name="tel" value="${requestScope.tel}">

            <!-- 戻るボタン -->
            <input type="button" value="戻る" onclick="history.back();" class="submit-button">

            <!-- 確定ボタン -->
            <input type="submit" value="確定" class="submit-button">
        </form>
    </div>
</body>
</html>
