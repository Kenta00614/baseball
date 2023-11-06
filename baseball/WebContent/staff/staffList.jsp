<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>職員情報</h1>
        <form action="StaffRegister" method="get">
    　　　　<button type="submit">新規職員登録</button>
　　　　</form>
    </div>
    <div class="header">
        <form action="StaffDelete" method="get">
    　　　　<button type="submit">職員情報の削除</button>
　　　　</form>
    </div>
        <div class="header">
        <form action="StaffPass" method="get">
    　　　　<button type="submit">パスワード再設定</button>
　　　　</form>
    </div>
</body>
</html>
