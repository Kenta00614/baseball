<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
    <h1>当日券・リセール席の販売停止</h1>

			<p>
				<input type="radio" id ="sale1" name="sale" value="cancel">
				<label for = "sale1">雨天時などの試合中止(払い戻し可)</label>
				<%-- SaleCancelCheck への仮置きリンク--%><a href="saleCancelCheck.jsp" class="btn">中止</a>


				<input type="radio" id ="sale2" name="sale" value="end">
				<label for = "sale2">当日券・リセール席の販売停止</label>
				<%-- SaleStopCheck への仮置きリンク --%><a href="saleStopCheck.jsp" class="btn">停止</a>
			</p>
    　　　　<button type="submit">確認</button>

    </div>
</body>
</html>
