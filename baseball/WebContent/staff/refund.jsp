<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background-color: #4CAF50;
            padding: 20px;
            color: white;
            text-align: center;
        }

        h1 {
            margin: 0;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }

        .control {
            margin-bottom: 15px;
            text-align: left;
            width: 300px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }

        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .note {
            font-size: 12px;
            margin: 5px 0 0;
            color: #666;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
    <div class="header">
        <h1>払い戻し確認</h1>
        <form action="RefundHandle" method="get">
            <div class="control">
                <label for="ticketnumber"> チケット番号</label>
                <input id="ticketnumber" type="text" name="ticketnumber" required>
                <p class="note">※半角数字で入力してください。</p>
            </div>
            <button type="submit">確認</button>
        </form>
    </div>
</body>

</html>
