<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<script>
	  function validatePassword() {
	    var password = document.getElementById('password');
	    var confirmPassword = document.getElementById('confirm_password');

	    if (password.value !== confirmPassword.value) {
	      confirmPassword.setCustomValidity('パスワードが一致しません。');
	    } else {
	      confirmPassword.setCustomValidity('');
	    }
	  }

	  window.onload = function() {
	    document.getElementById('password').onchange = validatePassword;
	    document.getElementById('confirm_password').onkeyup = validatePassword;
	  }
	</script>
<body>
    <div class="login-box">
    <h1 class="login-title">パスワード変更</h1>
    　　<form action="PasswordChange" method="post">
	        <div class="login-input">
	            <label for="password">パスワード</label>
	            <input name="newPassword" type="password" id="password" placeholder="🔒Password" class="login-input-element" pattern="[!-~]{8,}" required>
	            </div>
	            <div class="login-input">
	            <label for="password"  >パスワード確認</label>
	            <input name="newPassword" type="password" id="confirm_password" placeholder="🔒Password" class="login-input-element" pattern="[!-~]{8,}" required>
	            <button type="submit" class="form-change-btn">変更</button>
	        </div>
	    	<input type="hidden" name="uuid" value="${uuid}">
	    </form>

	</div>

</body>
</html>
