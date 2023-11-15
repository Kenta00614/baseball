<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<body>

	<h2>ログイン</h2>
    <div class="login-box">
    　　<form action="Login" method="post">

        <div class="input">
            <label for="user-id">ID</label>
            <input name="id" type="text" id="user-id" required>
        </div>
        <div class="input">
            <label for="password">Password</label>
            <input name="password" type="password" id="password" placeholder="Password" required>
        </div>
        <p><input type="submit" value="Login"></p>
        	<br><p>初めてご利用の方はこちら<br><a href="ProvisionalSignupDisplay" class="btn">→新規会員登録へ</a></p>
　　　　</form>



	</div>
</body>
</html>
