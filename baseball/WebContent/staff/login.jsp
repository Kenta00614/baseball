<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>ログイン</h1>
        <form action="" method="get">
        	<div class="control">
        		<p><label for="ID">ID(メールアドレス)</label>
				<input type="text" id="id" name="ID" ></p>
			</div>
			<div class="control">
	            <label for="passcode"> パスワード</label>
	            <input id="passcode" type="password" name="passcode">
        	</div>

    　　　　<button type="submit">ログイン</button>
            <a href="StaffPass" class="btn">パスワード再設定</a>
　　　　</form>
    </div>

</body>
</html>
