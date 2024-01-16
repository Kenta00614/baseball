<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<style>
	/*--- 張り紙スタイル ---*/
	.error{
		border:5px double #fb8883;
	 	background-color:#fee7e6;
	 	padding:16px;
	 	margin:20px;
	 	margin-top:100px;
	}
	</style>
</head>
<body>

<h2 class="error">ログインできませんでした。</h2>
<p>ID(メールアドレス)、パスワードを再確認してください。</p>
  <button onclick="location.href='LoginDisplay'" class="home-btn">ログイン画面に戻る</button>
</body>
</html>
