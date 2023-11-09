<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
試合情報変更　入力画面
<body>
    <div class="header">
        <form action="MatchUpdateCompletion" method="get">
    　　　　<button type="submit">試合変更</button>
　　　　</form>
    </div>
    <div class="header">
        <form action="MatchUpdateDelete" method="get">
    　　　　<button type="submit">試合削除</button>
　　　　</form>
    </div>
</body>
</html>
