<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <h1>払い戻し確認</h1>
        <form action="RefundHandle" method="get">
            <div class="control">
                <label for="ticketnumber"> チケット番号</label>
                <input id="ticketnumber" type="text" name="ticketnumber" pattern="[0-9]+" required>
                <p>※半角数字で入力してください。</p>
            </div>
            <button type="submit">確認</button>
        </form>


    </div>
</body>
</html>
