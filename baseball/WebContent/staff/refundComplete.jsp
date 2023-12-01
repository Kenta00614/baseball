<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ticketId = (String)request.getAttribute("ticketsId"); %>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="Refund" method="get">
        	<h1>払い戻し完了</h1>
      		<div class="confirmation-results">
	            <p>チケット番号: <%=ticketId %></p>
	            <!-- 他の入力フィールドの内容も同様に表示できます。 -->
        	</div>
            <p>チケット価格:「」　円</p>
            <p>払い戻し対応を行いました。</p>

    　　　　<button type="submit">次へ</button>
　　　　</form>
    </div>

</body>
</html>
