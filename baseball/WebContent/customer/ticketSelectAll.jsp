<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
<style type="text/css">
#seat-map {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(30px, 1fr));
  gap: 5px;
}

.seat {
  width: 30px;
  height: 30px;
  border: 1px solid #000;
  text-align: center;
  line-height: 30px;
}

.seat.available {
  background-color: #ccc;
}

.seat.reserved {
  background-color: #f00;
}

.seat.selected {
  background-color: #0f0;
}
</style>
</head>
<body>
<%-- 戻るボタン --%>
	<form action="TicketApplication" method="post">
		<input type="hidden" name="matchId" value="${matchId }">
        <input type="hidden" name="seat" value="${seat }">
        <input type="hidden" name="count" value="${count }">
    	<button type="submit">戻る</button>
	</form>

	<form action="TicketSelectBlock" method="post">
		<button type="submit">次へ</button>
	</form>
	<script src="../js/ticketSelectAll.js"></script>
</body>
</html>