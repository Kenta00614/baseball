<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">

</head>
<body>
	<c:choose>
		<c:when test="${sucssesFlg == 0 }">
			<h2 class="poster" >パスワードの再設定が完了しました</h2>
		</c:when>
		<c:when test="${sucssesFlg == 1 }">
			<h2 class="error" >パスワードの再設定に失敗しました</h2>
			<p>もう一度再設定してください。</p>
		</c:when>
	</c:choose>
</body>
</html>