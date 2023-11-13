<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
    <div class="header">
        <form action="TicketSelectSeat" method="get">
    　　　　<button type="submit">席種・枚数選択に戻る</button>
　　　　</form>
    </div>
    <div class="header">
        <form action="TicketComplete" method="get">
    　　　　<button type="submit">購入</button>
　　　　</form>
    </div>
</body>
</html>
