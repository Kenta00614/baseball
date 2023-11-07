<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header"><%-- saleCancelCompleteとsaleStopCompleteは内容同じ --%>
        <form action="SaleCancelComplete" method="get">
            <br><a href="SaleStop" class="btn">戻る</a>
        	<h1>雨天時などの販売中止</h1>
        	<p>当日券・リセール席の販売を停止します。</p>
        	<p>チケットステータスを払い戻し未対応に変更します。</p><br>
        	<p>よろしいですか？</p>
    　　　　<button type="submit">停止</button>
　　　　</form>
    </div>

</body>
</html>
