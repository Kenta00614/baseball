<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>パスワード再設定</h1>
        <form action="StaffPass" method="post">
        	<div class="control">
	            <label for="id"> ID</label>
	            <input id="id" type="id" name="id" required>
			</div>
			<div class="control">
	            <label for="password"> パスワード</label>
	            <input id="password" type="password" name="password"required>
        	</div>
    　　　　<p><input type="submit" value="Reset"></p>
　　　　</form>
    </div>

    <script>
        function validatePassword() {
            var password1 = document.getElementById("password").value;
            var password2 = document.getElementById("password2").value;
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