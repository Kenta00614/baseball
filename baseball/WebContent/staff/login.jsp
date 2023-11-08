<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>ログイン</h1>
        <form action="<%-- ログイン先のリンク --%>" method="get">
        	<div class="control">
            <label for="mymail"> メールアドレス</label>
            <input id="mymail" type="email" name="mymail" required>
			</div>
			<div class="control">
			    <label for="passcode"> パスワード</label>
			    <input id="passcode" type="password" name="passcode" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$" required>
			    <p class="note">※パスワードは8文字以上で、数字と英字が必要です。</p>
			</div>

    　　　　<button type="submit">ログイン</button>
            <a href="StaffPass" class="btn">パスワード再設定</a>
　　　　</form>
    </div>

</body>
</html>