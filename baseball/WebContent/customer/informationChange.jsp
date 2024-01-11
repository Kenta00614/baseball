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
        <h1>会員情報変更</h1>
        <form id="memberChangeForm" action="InformationChangeConfirm" method="post">
            <label for="name">氏名</label>
            <input type="text" id="name" name="name" value="${param.name}"  class="field" required>

            <label for="tel">電話番号</label>
            <input type="tel" id="tel" name="tel" value="${param.tel}" pattern="\d{10}|\d{11}" class="field" required>

            <input type="submit" value="確認" class="submit-button">
        </form>
    </div>
</body>
</html>