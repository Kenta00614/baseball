<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }

        form {
            max-width: 400px;
            margin: 50px auto 0;
            background-color: #fff;
            padding: 50px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 18px;
            color: #333;
        }

        button {
            background-color:#007BFF;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <form action="MatchInformation" method="post">
        <p>試合情報の変更が完了しました。</p>
        <input type="hidden" value="${tournamentId}" name="tournamentId">
        <button type="submit">OK</button>
    </form>
</body>
</html>
