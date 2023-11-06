<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>職員情報の削除</h1>
        <form action="StaffDeleteCheck" method="get">
        	<p><label for="id"> ID</label>
            <input id="text" type="id" name="ID"></p>
            <p><label for="name">氏名</label>
			<input type="text" id="name" name="NAME" ></p>
    　　　　<button type="submit">削除</button>
　　　　</form>
    </div>

</body>
</html>
