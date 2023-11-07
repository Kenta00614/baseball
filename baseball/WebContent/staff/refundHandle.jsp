<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <form action="RefundComplete" method="get">
        	<h1>払い戻し対応</h1>
        	<p><label for="ticketnumber"> チケット番号 </label>
            <input type="number" id="ticketnumber" name="ticketnumber"></p>

            <p>払い戻し未対応</p>

    　　　　<button type="submit">払い戻し</button>
　　　　</form>
    </div>

</body>
</html>
