<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>新規職員登録</h1>
        <form action="StaffRegisterComplete" method="get">
            <div class="control">
        		<p><label for="ID">ID</label><br>
				<input type="text" id="id" name="ID" ></p>
			</div>
        	<div class="control">
        		<p><label for="name">氏名</label><br>
				<input type="text" id="name" name="name" ></p>
			</div>
			<div class="control">
        		<p><label for="name">生年月日(整数８桁)</label><br>
				<input type="number" id="birth" name="BIRTH"></p>
			</div>
			<p>職員ステータス:
			<select class="control">
        		<option value="1">スタッフ</option><br>
				<option value="2">役職</option><br>
			</select></p>

    　　　　<button type="submit">登録</button>
　　　　</form>
    </div>

</body>
</html>
