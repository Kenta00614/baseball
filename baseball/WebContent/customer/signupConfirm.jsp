<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規会員登録確認</title>
    <link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
</head>
<body>
    <div class="container">
        <h1 >新規会員登録確認</h1>
        <form id="registrationForm" action="ProvisionalSignup" method="post">
        <p class="form-comment">以下の内容で登録してよろしいですか？</p>
        <div class="form-inline">
        	<hr class="inline">
        	<div class="form-cfm">
            	<p class="signup-label"><label for="name" class="form-element"> 　氏　　名　　</label></p>
            	<!-- サーバーサイドで受け取った値を表示 -->
            	<span class="form-input"><%= request.getParameter("name") %></span>
			</div>
			<hr class="inline">
			<div class="form-cfm">
	            <p class="signup-label"><label for="mail" class="form-element">メールアドレス</label></p>
	            <!-- サーバーサイドで受け取った値を表示 -->
	            <span class="form-input"><%= request.getParameter("mail") %></span>
			</div>
			<hr class="inline">
			<div class="form-cfm">
	            <p class="signup-label"><label for="tel" class="form-element">電　話　番　号</label></p>
	            <!-- サーバーサイドで受け取った値を表示 -->
	            <span class="form-input"><%= request.getParameter("tel") %></span>
	        </div>
            <hr class="inline">
        </div>

		<div class="infor-change">
			<input type="hidden" name="mail" value="<%= request.getParameter("mail") %>">
			<input type="hidden" name="name" value="<%= request.getParameter("name") %>">
			<input type="hidden" name="password" value="<%= request.getParameter("password") %>">
			<input type="hidden" name="tel" value="<%= request.getParameter("tel") %>">
            <input type="button" value="戻る" onclick="history.back();" class="infor-change-btn">
            <input type="submit" value="登録" class="infor-change-btn">
        </div>
        </form>
    </div>
</body>
</html>
