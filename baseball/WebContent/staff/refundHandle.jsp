<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ticketId = (String)request.getAttribute("ticketId"); %>
<% String state = (String)request.getAttribute("state"); %>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="RefundComplete" method="get">
        	<h1>払い戻し対応</h1>
        	<div class="confirmation-results">
	            <p>チケット番号: <%=ticketId %></p>
        	</div>

		<% if(state.equals("6")){ %>
			<p>払い戻し可能です。</p>
			<button type="submit">払い戻し</button>
		<% }else if(state.equals("5")){ %>
			<p>払い戻し済みです。</p>
		<% }else{ %>
			<p>払い戻し対応が不要なチケットです。</p>
		<% } %>
　　　　</form>
    </div>

</body>
</html>
