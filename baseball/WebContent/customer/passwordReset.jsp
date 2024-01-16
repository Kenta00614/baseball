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
		<c:when test="${sucssesFlg == 0 }">
			<h1 class="poster" >パスワードの再設定が完了しました</h1>
		</c:when>
		<c:when test="${sucssesFlg == 1 }">
			<h1 class="error" >パスワードの再設定に失敗しました</h1>
			<p>もう一度再設定してください。</p>
		</c:when>
	</c:choose>
</body>
</html>