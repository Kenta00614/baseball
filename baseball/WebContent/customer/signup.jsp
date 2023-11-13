<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員情報変更</title>
<style>
  body {
    font-family: 'Arial', sans-serif;
  }
  .container {
    width: 600px;
    margin: 30px auto;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0px 0px 10px #ccc;
  }
  h1 {
    text-align: center;
  }
  form {
    display: flex;
    flex-direction: column;
  }
  label {
    margin-top: 10px;
  }
  input[type="text"],
  input[type="email"],
  input[type="tel"],
  input[type="password"] {
    padding: 10px;
    margin-top: 5px;
  }
  .submit-button {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #000;
    color: white;
    border: none;
    cursor: pointer;
  }
</style>
<script>
  function validatePassword() {
    var password = document.getElementById('password');
    var confirmPassword = document.getElementById('confirmPassword');

    if (password.value !== confirmPassword.value) {
      confirmPassword.setCustomValidity('パスワードが一致しません。');
    } else {
      confirmPassword.setCustomValidity('');
    }
  }

  window.onload = function() {
    document.getElementById('password').onchange = validatePassword;
    document.getElementById('confirmPassword').onkeyup = validatePassword;
  }
</script>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
<div class="container">
  <h1>新規会員登録</h1>
  <form id="registrationForm" action="ProvisionalSignup" method="post">
    <label for="name">氏名</label>
    <input type="text" id="name" name="name" required>

    <label for="mail">メールアドレス</label>
    <input type="mail" id="mail" name="mail" placeholder="xxxxxx@stu.o-hara.ac.jp" required>

    <label for="tel">電話番号</label>
    <input type="tel" id="tel" name="tel" pattern="\d{10}|\d{11}" placeholder="0332378711" required>

    <label for="password">パスワード</label>
    <input type="password" id="password" name="password" required>

    <label for="confirmPassword">パスワード確認</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>

    <input type="submit" value="確認" class="submit-button">
  </form>
</div>
</body>
</html>
