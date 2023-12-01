<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
            text-align: center;
            padding-top: 20px;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .content {
            text-align: center;
        }

        .header {
            margin: 20px;
            text-align: center;
        }

        .button-container {
            display: flex;
            justify-content: space-around;
        }

        .custom-btn {
            overflow: hidden;
            position: relative;
            margin: 20px;
        }

        .custom-btn button {
        background-color: #000;
        color: #fff;
        padding: 15px 30px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        outline: none;
        overflow: hidden;
        position: relative;
        z-index: 1;
        transition: color 0.3s ease-in-out, transform 0.3s ease-in-out; /* アニメーションの追加 */
    }

    .custom-btn button:hover {
        color: #000;
        transform: scale(1.1); /* ホバー時のアニメーション */
    }

    .custom-btn button:before {
        /* 既存のスタイルは省略 */
        transition: transform 0.3s ease-in-out; /* アニメーションの追加 */
    }

    .custom-btn button:hover:before {
        transform: translateY(0);
    }
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="content">
            <h1>入退場画面</h1>
            <div class="button-container">
                <div class="header custom-btn">
                    <form action="Entry" method="get">
                        <button type="submit">入場</button>
                    </form>
                </div>
                <div class="header custom-btn">
                    <form action="Exit" method="get">
                        <button type="submit">退場</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
