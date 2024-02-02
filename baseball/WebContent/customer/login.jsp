<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<style>
		placeholder{
			font-size: 30px;
		}
	</style>
</head>
<body>


    <div class="login-box">
    <h1 class="login-title">ログイン</h1>
    　　<form action="Login" method="post" class="login-form">
	        <div class="login-input">
	            <label for="user-id" class="login-label" >ID(email)</label>
	            <input name="mail" type="email" id="user-id" placeholder="✉Mail" class="login-input-element" required>
	        </div>
	        <div class="login-input">
	            <label for="password" class="login-label">Password</label>
	            <input name="password" type="password" id="password" placeholder="🔒Password" class="login-input-element" required>
	        </div>
	        <p class="login-btn"><input type="submit" value="Login" class="login-input-btn"></p>
	        <br><p>初めてご利用の方はこちら<br><a href="ProvisionalSignupDisplay" class="btn">→新規会員登録へ</a></p>
	        <br><p>パスワードを忘れた方はこちら<br><a href="PasswordChangeFormDisplay" class="btn">→パスワード再設定へ</a></p>
　　　　</form>

	</div>
</body>
</html>
