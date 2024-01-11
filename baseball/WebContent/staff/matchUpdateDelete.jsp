<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*,java.util.*,bean.Tournament" %>
<% String date = (String)request.getAttribute("eventDate"); %>
<% Tournament t = (Tournament)request.getAttribute("tournament"); %>
<%@include file="header.jsp"%>
<html>
<body>

	<h2><%=t.getOrdinalNum() %>回　<%=t.getName() %></h2>
	<p><%=date.substring(0,4) %>年<%=date.substring(5,7) %>月<%=date.substring(8,10) %>日の試合情報を削除します。</p>
    <form action="MatchUpdateInput" method="post">
		<input type="hidden" value="${eventDate }" name="date">
		<button type="submit">戻る</button>
	</form>

	<form action="MatchInformation" method="post">
		<input type="hidden" value="${tournamentId }" name="tournamentId">
		<input type="hidden" value="${eventDate }" name="eventDate">
		<button type="submit">確認</button>
	</form>
</body>
</html>
