<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員情報変更</title>
    <link rel="stylesheet" type="text/css" href="/baseball/css/Customer.css">

</head>
<body>
    <div class="container">
        <h1 class="login-title">会員情報変更</h1>
        <form id="memberChangeForm" action="InformationChangeConfirm" method="post">
        <div class="login-input">
            <label for="name" class="infochange-input-element">氏名</label>
            <input type="text" id="name" name="name" value="${param.name}"  class="login-input-element" required>
		</div>

		<div class="login-input">
            <label for="tel" class="infochange-input-element">電話番号</label>
            <input type="tel" id="tel" name="tel" value="${param.tel}" pattern="\d{10}|\d{11}" class="login-input-element" required>
		</div>
            <input type="submit" value="確認" class="submit-button">
        </form>
    </div>
</body>
</html>