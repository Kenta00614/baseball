<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <title>販売停止確認</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 50px auto;
            text-align: center;
        }

        h1 {
            color: #333333;
            margin-top: 0;
        }

        p {
            color: #666666;
            margin-bottom: 20px;
        }

        .link-btn {
            color: #007bff;
            text-decoration: underline;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .link-btn:hover {
            color: #0056b3;
        }

        .btn, .button-container {
            display: inline-block;
            margin-bottom: 20px;
        }

        .btn {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        button {
            background-color: #d9534f;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #c9302c;
        }

        .button-container {
            text-align: left;
            display: block;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
    <div class="header">
        <div class="button-container">
            <a href="SaleStop" class="link-btn">戻る</a>
        </div>
        <form action="SaleStopComplete" method="post">
            <h1>雨天時などの試合中止</h1>
            <p>当日券・リセール席の販売を停止します。</p>
            <p>よろしいですか？</p>
            <button type="submit">停止</button>
        </form>
    </div>
</body>

</html>
