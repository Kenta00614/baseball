<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>

<body>
    <div class="login-box">
    <h1 class="login-title">メールアドレス入力</h1>
    　　<form action="PasswordChangeMail" method="post">
	        <div class="login-input">
	            <label for="user-id">ID(email)</label>
	            <input name="mail" type="email" id="email" placeholder="✉Mail" class="login-email" required>

	            <button type="submit">送信</button>
	        </div>


　　　　</form>

	</div>
</body>
</html>
