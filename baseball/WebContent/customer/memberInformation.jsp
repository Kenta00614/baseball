<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
	<title>会員情報</title>
</head>
<body>
	<h1>会員情報</h1>

    <div class="member-info">
		<table>
        <c:choose>
            <c:when test="${not empty spectator}">
                <tr><th>ID</th><td> ${spectator.spectatorId}</td></tr>
                <tr><th>名前</th><td> ${spectator.name}</td></tr>
                <tr><th>メールアドレス</th><td>${spectator.mail}</td></tr>
                <tr><th>電話番号</th><td>${spectator.tel}</td></tr>
                <tr><th>ポイント</th><td>${spectator.point}</td></tr>
            </c:when>
            <c:otherwise>
                <p class="memberinfo-else">会員情報が利用できません。 ログインしてください。</p>
            </c:otherwise>
        </c:choose>
        </table>
    </div>

	<div class="member-selection">
	    <form action="EmailChangeDisplay" method="get">
            <button type="submit">メールアドレス変更</button>
        </form>

        <form action="MemberWithdraw" method="get">
            <button type="submit">会員退会</button>
        </form>

        <form action="InformationChange" method="get">
            <button type="submit">会員情報変更</button>
        </form>
    </div>


</body>
</html>
