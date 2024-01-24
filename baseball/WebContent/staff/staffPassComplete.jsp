<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <title>パスワード再設定完了</title>
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

        /* Style for the confirmation button */
        input[type="submit"] {
            background-color: #007BFF;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <div class="header">
        <form action="Login" method="post">
            <h1>パスワード再設定</h1>
            <p>パスワードの再設定が完了しました。</p>
            <input type="hidden" name="id" value="${id }">
            <input type="hidden" name="password" value="${password }">
            <input type="submit" value="確認">
        </form>
    </div>
</body>

</html>
