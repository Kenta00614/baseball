<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">
        <h1>当日券・リセール席の販売停止</h1>
        <form action="SaleOptionsRedirect" method="post" onsubmit="return validateSelection();">
            <p>
                <input type="radio" id="sale1" name="sale" value="cancel">
                <label for="sale1">雨天時などの試合中止(払い戻し可)</label>
                <br>
                <input type="radio" id="sale2" name="sale" value="end">
                <label for="sale2">当日券・リセール席の販売停止</label>
            </p>
            <button type="submit">確認</button>
        </form>
    </div>

    <script>
        function validateSelection() {
            var selectedOption = document.querySelector('input[name="sale"]:checked');
            if (!selectedOption) {
                alert("ラジオボタンを選択してください。");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>