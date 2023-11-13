<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
    <div class="header">
        <form action="TicketPurchase" method="get">
    　　　　<button type="submit">前へ戻る</button>
　　　　</form>
    </div>
    <div class="header">
        <form action="TicketSelectAll" method="get">
    　　　　<button type="submit">座席選択へ進む</button>
　　　　</form>
    </div>
</body>
</html>
