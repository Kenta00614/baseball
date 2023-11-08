<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>新規職員登録</h1>
        <form action="StaffRegisterComplete" method="get" onsubmit="return validatePasswords()">
        	<div class="control">
	            <label for="mymail"> ID(メールアドレス)</label>
	            <input id="mymail" type="email" name="mymail" required>
			</div>
 			<div class="control">
			    <label for="passcode"> パスワード</label>
			    <input id="passcode" type="password" name="passcode" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$" required>
			</div>
			<div class="control">
	            <label for="passcode2"> パスワード(確認用)</label>
	            <input id="passcode2" type="password" name="passcode2"required>
			</div>
			<div id="passwordError" style="color: red;"></div>
        	<p class="note">※パスワードは8文字以上で、数字と英字が必要です。</p>
        	<div class="control">
        		<p><label for="name">氏名</label><br>
				<input type="text" id="name" name="name" required></p>
			</div>
			<div class="control">
			    <p><label for="name">生年月日(整数８桁)</label><br>
			    <input type="text" id="birth" name="BIRTH" pattern="[12][0-9]{3}[01][0-9][0-3][0-9]" required></p>
			</div>
			<p>職員ステータス:
			<select class="control">
        		<option value="1">スタッフ</option>
				<option value="2">役職</option>
			</select></p>

    　　　　<button type="submit">登録</button>
　　　　</form>
    </div>
    <script>
    	function validatePasswords() {
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