<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
</head>
<body>

    <div class="header">
        <form action="MemberWithdraw" method="get">
            <button type="submit">会員退会</button>
        </form>
    </div>

    <div class="header">
        <form action="InformationChange" method="get">
            <button type="submit">会員情報変更</button>
        </form>
    </div>

    <div class="member-info">
        <h1>会員情報</h1>
        <c:choose>
            <c:when test="${not empty spectator}">
                <p>ID: ${spectator.spectatorId}</p>
                <p>名前: ${spectator.name}</p>
                <p>メールアドレス: ${spectator.mail}</p>
                <p>電話番号: ${spectator.tel}</p>
                <p>ポイント: ${spectator.point}</p>
            </c:when>
            <c:otherwise>
                <p>会員情報が利用できません。ログインしてください。</p>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
