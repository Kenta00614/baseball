<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ja">
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/baseball/css/Customer.css">
    <title>会員情報</title>
</head>
<body>
    <h1>会員情報</h1>

    <div class="member-info">
        <table class="memberinfo-table">
            <c:choose>
                <c:when test="${not empty spectator}">
                    <tr><th class="memberinfo-th">名前</th><td class="memberinfo-td"> ${spectator.name}</td></tr>
                    <tr><th class="memberinfo-th">メールアドレス</th><td class="memberinfo-td">${spectator.mail}</td></tr>
                    <tr><th class="memberinfo-th">電話番号</th><td class="memberinfo-td">${spectator.tel}</td></tr>
                    <tr><th class="memberinfo-th">ポイント</th><td class="memberinfo-td">${spectator.point}</td></tr>
                </c:when>
                <c:otherwise>
                    <p class="memberinfo-else">会員情報が利用できません。ログインしてください。</p>
                </c:otherwise>
            </c:choose>
        </table>
    </div>

    <div class="member-selection">
        <form action="InformationChangeDisplay" method="post">
            <input type="hidden" name="name" value="${spectator.name}">
            <input type="hidden" name="tel" value="${spectator.tel}">
            <button type="submit" class="memberinfo-btn">会員情報変更</button>
        </form>
        <form action="EmailChangeDisplay" method="Get">
            <input type="hidden" name="mail" value="${spectator.mail}">
            <button type="submit" class="memberinfo-btn">メールアドレス変更</button>
        </form>

    </div>
</body>
</html>
