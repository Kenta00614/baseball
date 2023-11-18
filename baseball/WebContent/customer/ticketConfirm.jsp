<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
    <div class="header">
        <form action="TicketApprication" method="post">
        	<input type="hidden" name="tour" value="">
        	<input type="hidden" name="match" value="">
        	<input type="hidden" name="remaining" value=-1>
        	<input type="hidden" name="seatType" value="">
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
