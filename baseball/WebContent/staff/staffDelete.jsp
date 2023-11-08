<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>職員情報の削除</h1>
        <form action="StaffDeleteCheck" method="get">
        	<div class="control">
	            <label for="mymail"> ID(メールアドレス)</label>
	            <input id="mymail" type="email" name="mymail" required>
			</div>
            <p><label for="name">氏名</label>
			<input type="text" id="name" name="NAME" required></p>
    　　　　<button type="submit">削除</button>
　　　　</form>
    </div>

</body>
</html>
