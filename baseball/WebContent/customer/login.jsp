<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>


    <div class="login-box">
    <h1 class="login-title">ログイン</h1>
    　　<form action="Login" method="post">
	        <div class="login-input">
	            <label for="user-id">ID(email)</label>
	            <input name="mail" type="email" id="user-id" placeholder="✉Mail" class="login-email" required>
	        </div>
	        <div class="login-input">
	            <label for="password">Password</label>
	            <input name="password" type="password" id="password" placeholder="🔒Password" class="login-pass" required>
	        </div>
	        <p class="login-btn"><input type="submit" value="Login"></p>
　　　　</form>

	</div>
</body>
</html>
