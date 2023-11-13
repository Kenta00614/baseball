<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
    <div class="login-box">
    　　<form action="Login" method="post">
        <div class="input">
            <label for="user-id">ID(メールアドレス)</label>
            <input type="mail" id="user-id" placeholder="メールアドレスを入力">
        </div>
        <div class="input">
            <label for="password">パスワード</label>
            <input type="password" id="password" placeholder="パスワードを入力">
        </div>
        <button type="submit">ログイン</button>
　　　　</form>


    </div>
</body>
</html>
