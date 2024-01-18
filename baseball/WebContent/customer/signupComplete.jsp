<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<style>
	/*--- 張り紙スタイル ---*/
	.poster{
	 	border:5px double #a4c3b2;
	 	background-color:#F3F7F5;
	 	padding:16px;
	 	margin:20px;
	 	margin-top:100px;
	}

	.error{
		border:5px double #fb8883;
	 	background-color:#fee7e6;
	 	padding:16px;
	 	margin:20px;
	 	margin-top:100px;
	}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${situFlg == 0 }">
			<h2 class="error" >アカウントの登録に失敗しました</h2>
			<p >認証期限が過ぎています。もう一度登録し直してください。</p>
		</c:when>
		<c:when test="${situFlg == 1 }">
			<h2 class="error" >アカウントの登録に失敗しました</h2>
			<p>URLが正しいか確認してください。</p>
		</c:when>
		<c:when test="${situFlg == 2 }">
			<h2 class="poster" >アカウントを登録しました</h2>
		</c:when>
	</c:choose>
</body>
</html>
