<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>パスワード再設定</h1>
        <form action="StaffPassComplete" method="get" onsubmit="return validatePassword()">
        	<div class="control">
	            <label for="mymail"> ID(メールアドレス)</label>
	            <input id="mymail" type="email" name="mymail" required>
			</div>
			<div class="control">
	            <label for="passcode"> パスワード</label>
	            <input id="passcode" type="password" name="passcode"pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$"required>
        	</div>
			<div class="control">
	            <label for="passcode2"> パスワード(確認用)</label>
	            <input id="passcode2" type="password" name="passcode2"required>
	            <p class="note">※パスワードは8文字以上で、数字と英字が必要です。</p>
        	</div>
        	<div id="passwordError" style="color: red;"></div><br>
    　　　　<button type="submit">再設定</button>
　　　　</form>
    </div>

    <script>
        function validatePassword() {
            var password1 = document.getElementById("passcode").value;
            var password2 = document.getElementById("passcode2").value;
            var passwordError = document.getElementById("passwordError");

            if (password1 !== password2) {
                passwordError.innerHTML = "パスワードが一致しません。";
                return false; // フォームが送信されない
            } else {
                passwordError.innerHTML = "";
                return true; // フォームを送信
            }
        }
    </script>
</body>
</html>