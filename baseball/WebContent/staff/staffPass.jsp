<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>パスワード再設定</h1>
        <form action="StaffPassComplete" method="get">
			<div class="control">
	            <label for="passcode"> パスワード</label><br>
	            <input id="passcode" type="password" name="passcode">
        	</div>
			<div class="control">
	            <label for="passcode2"> パスワード(確認用)</label><br>
	            <input id="passcode2" type="password" name="passcode2">
        	</div><br>
    　　　　<button type="submit">再設定</button>
　　　　</form>
    </div>

</body>
</html>
