<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="RefundHandle" method="get">
        	<h1>払い戻し確認</h1>
        	<p><label for="ticketnumber"> チケット番号 </label>
            <input type="number" id="ticketnumber" name="ticketnumber"></p>

    　　　　<button type="submit">確認</button>
　　　　</form>
    </div>

</body>
</html>
