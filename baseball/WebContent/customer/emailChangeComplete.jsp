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
		<c:when test="${comfilmFlg == 0 }">
		<%-- 変更が正常に行われたとき --%>
			<div class="email-change">
				<h2 class="poster">変更が完了しました</h2>
	  			<button onclick="location.href='Main'" >メイン画面へ</button>
			</div>
		</c:when>
		<c:when test="${comfilmFlg == 1 }">
		<%-- uuidがそんざいしないとき --%>
			<h2 class="error" >変更に失敗しました</h2>
			<p>すでに変更されているか、URLの有効期限が切れています。確認してください。</p>
		</c:when>
		<%-- UUIDの長さが36ないとき --%>
		<c:when test="${comfilmFlg == 2 }">
		<h2 class="error" >変更に失敗しました</h2>
			<p>URLが正しいか確認してください。</p>
		</c:when>
	</c:choose>
</body>
</html>
