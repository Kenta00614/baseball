<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <title>職員情報の削除完了</title>
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
        }

        h1 {
            color: #333333;
            margin-top: 0;
        }

        p {
            color: #666666;
            margin-bottom: 20px;
        }

        button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <div class="header">
        <form action="StaffDelete" method="get">
            <h1>職員情報の削除</h1>
            <p>職員情報の削除が完了しました。</p>
            <button type="submit">続けて職員情報を削除する</button>
        </form>
    </div>
</body>

</html>
