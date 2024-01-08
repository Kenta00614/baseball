<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<script language="JavaScript" type="text/javascript">

  function CheckEmail_1() {
    //IE対応の為変更
    //var mail = email_1.value; //メールフォームの値を取得
    //var mailConfirm = emailConfirm_1.value; //メール確認用フォームの値を取得
    var mail = document.getElementById("email_1").value; //メールフォームの値を取得
    var mailConfirm = document.getElementById("emailConfirm_1").value; //メール確認用フォームの値を取得
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
    <h1 class="login-title">メールアドレス変更</h1>
    　　<form action="EmailChange" method="post">
	        <div class="login-input">
	            <label for="user-id">ID(email)</label>
	            <input name="newMail" type="email" id="email_1" placeholder="✉Mail" class="login-email" required>
	            <label for="user-id">確認用ID(email)</label>
	            <input name="newMail" type="email" id="emailConfirm_1" placeholder="✉Mail" class="login-email" required>

	            <button type="submit" class="form-btn">メールアドレス変更</button>
	        </div>


　　　　</form>

	</div>
</body>
</html>
