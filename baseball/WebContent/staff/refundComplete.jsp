<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="Refund" method="get">
        	<h1>払い戻し完了</h1>
      		<div class="confirmation-results">
	            <p>チケット番号: <%= request.getParameter("ticketnumber") %></p>
	            <!-- 他の入力フィールドの内容も同様に表示できます。 -->
        	</div>
            <p>チケット価格:「」　円</p>
            <p>払い戻し対応を行いました。</p>

    　　　　<button type="submit">次へ</button>
　　　　</form>
    </div>

</body>
</html>
