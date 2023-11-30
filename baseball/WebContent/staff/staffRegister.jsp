<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>新規職員登録</h1>
        <form action="StaffRegisterComplete" method="get">
        	<div class="control">
	            <label for="mymail"> ID</label>
	            <input id="id" type="text" name="id" required>
			</div>

        	<div class="control">
        		<p><label for="name">氏名</label><br>
				<input type="text" id="name" name="name" required></p>
			</div>
			<div class="control">
			    <p><label for="name">生年月日(整数８桁)</label><br>
			    <input type="text" id="birth" name="BIRTH" pattern="[12][0-9]{3}[01][0-9][0-3][0-9]" required></p>
			</div>
			<p>職員ステータス:
			<select name="position" class="control">
        		<option value="1">スタッフ</option>
				<option value="2">管理者</option>
			</select></p>

    　　　　<button type="submit">登録</button>
　　　　</form>
    </div>
</body>
</html>