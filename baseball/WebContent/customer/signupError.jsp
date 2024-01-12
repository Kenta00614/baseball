<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
<h2>アカウントの登録に失敗しました</h2>
	<c:choose>
		<c:when test="${situFlg == 0 }">
			<p>認証期限が過ぎています、もう一度登録しなおしてください。</p>
		</c:when>
		<c:when test="${situFlg == 1 }">
			<p>URLが正しいか確認してください。</p>
		</c:when>
		<c:when test="${situFlg == 2 }">
			<p>電話番号・もしくはメールアドレスがすてに使用されています。</p>
		</c:when>
	</c:choose>
</body>
</html>
