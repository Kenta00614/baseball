<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<script language="JavaScript" type="text/javascript">

  function CheckPassword_1() {
    //IE対応の為変更
    //var mail = email_1.value; //メールフォームの値を取得
    //var mailConfirm = emailConfirm_1.value; //メール確認用フォームの値を取得
    var password = document.getElementById("password").value; //メールフォームの値を取得
    var passwordConfirm = document.getElementById("confirm_password").value; //メール確認用フォームの値を取得
    // パスワードの一致確認
    if (mail != mailConfirm){
      alert("パスワードと確認用パスワードが一致しません"); // 一致していなかったら、エラーメッセージを表示する
      return false;
    }else{
      return true;
    }
  };
</script>
<body>
    <div class="login-box">
    <h1 class="login-title">パスワード変更</h1>
    　　<form action="PasswordChange" method="post">
	        <div class="login-input">
	            <label for="password">パスワード</label>
	            <input name="newPassword" type="password" id="password" placeholder="🔒Password" class="login-pass" required>
	            <label for="password"  >パスワード確認</label>
	            <input name="newPassword" type="password" id="confirm_password" placeholder="🔒Password" class="login-pass" required>
	            <button type="submit" class="form-btn">パスワード変更</button>
	        </div>
	    	<input type="hidden" name="uuid" value="${uuid}">
	    </form>

	</div>

</body>
</html>
