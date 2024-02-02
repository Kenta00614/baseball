<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<script language="JavaScript" type="text/javascript">

function CheckEmail_1() {
	  var mail = document.getElementById("email_1").value;
	  var mailConfirm = document.getElementById("emailConfirm_1").value;

	  if (mail != mailConfirm) {
	    alert("メールアドレスと確認用メールアドレスが一致しません");
	    return false; // フォームの送信をキャンセル
	  } else {
	    return true; // フォームの送信を許可
	  }
	};

</script>
<body>
    <div class="login-box">
    <h1 class="login-title">メールアドレス変更</h1>
    　　<form action="EmailChange" method="post" onsubmit="return CheckEmail_1();">
	        <div class="login-input">
	            <label for="user-id" >ID(email)　</label>
	            <input name="newMail" type="email" id="email_1" placeholder="✉Mail" class="login-input-element" required>
	        </div>
	        <div class="login-input">
	            <label for="user-id" >確認用ID　</label>
	            <input name="newMail" type="email" id="emailConfirm_1" placeholder="✉Mail" class="login-input-element" required>
			</div>
	            <button type="submit" class="form-change-btn">変更</button>



　　　　</form>

	</div>
</body>
</html>
