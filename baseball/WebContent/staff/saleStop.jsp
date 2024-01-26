<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>当日券・リセール席の販売停止</title>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5F5F5;
        }

        .header {
            background-color: #F5F5F5;
            color: #555555;
            padding: 10px;
            text-align: center;
        }

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .control {
            margin-bottom: 15px;
            display: flex;
            text-align: center;
			margin-left: 50px;
        }

        label {
            display: inline-block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555555;
        }

        button {
            background-color: #0066FF;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 18%;
            display: block;
            margin: auto;
        }
    </style>
</head>

<body>

    <div class="header">
        <h1>当日券・リセール席の販売停止</h1>
    </div>

    <div class="form-container">

        <form action="SaleOptionsRedirect" method="post" onsubmit="return validateSelection();">
            <div class="control">

                <label for="sale1"><input type="radio" id="sale1" name="sale" value="cancel" style="margin-right: 10px;">雨天時などの試合中止(払い戻し可)</label>
            </div>

            <div class="control">

                <label for="sale2"><input type="radio" id="sale2" name="sale" value="end" style="margin-right: 10px;">当日券・リセール席の販売停止</label>
            </div>

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
