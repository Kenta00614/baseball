<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
    <div class="header">
        <form action="TicketShare" method="get">
    　　　　<button type="submit">共有</button>
　　　　</form>
    </div>
    <div class="header">
        <form action="TicketQr" method="get">
    　　　　<button type="submit">QRコード</button>
　　　　</form>
    </div>
</body>
</html>
