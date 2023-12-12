<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5F5F5;
        }

        .header {
            background-color: #232733	;
            color: white;
            padding: 0.1px;
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

        .button-container {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .custom-btn {
            overflow: hidden;
            position: relative;
        }

        .custom-btn button {
            background-color: #ff6347;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
        }

        .custom-btn button:hover {
            background-color: #ff6347;
            transform: scale(1.1);
        }

        .custom-btn button:before {
            transition: transform 0.3s ease-in-out;
        }

        .custom-btn button:hover:before {
            transform: translateY(0);
        }
    </style>
</head>

<body>
    <div class="header">
        <h1>入退場画面</h1>
    </div>
    <div class="form-container">
        <div class="button-container">
            <div class="custom-btn">
                <form action="Entry" method="get">
                    <button type="submit">入場</button>
                </form>
            </div>
            <div class="custom-btn">
                <form action="Exit" method="get">
                    <button type="submit">退場</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>
