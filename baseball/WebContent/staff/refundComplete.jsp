<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.Tickets,java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% List<Tickets> list = (List<Tickets>)request.getAttribute("list"); %>
<% List<Integer> price = (List<Integer>)request.getAttribute("price"); %>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="Refund" method="get">
        	<% for(Tickets t : list){ %>
	        	<h1>払い戻し完了</h1>
	      		<div class="confirmation-results">
		            <p>チケット番号: <%= t.getTicketsId()%></p>
		            <!-- 他の入力フィールドの内容も同様に表示できます。 -->
	        	</div>
	            <p>チケット価格:<%= price.get(0) %>円</p>
	            <p>払い戻し対応を行いました。</p>
    		<% } %>
    		<button type="submit">次へ</button>

　　　　</form>
    </div>

</body>
</html>
