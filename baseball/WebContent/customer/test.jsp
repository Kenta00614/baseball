<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>第103回全国高等学校野球選手権大会</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        line-height: 1.6;
    }
    .title {
            text-align: center;
            font-size: 24px;
            margin: 20px 0;
        }
    .container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px;
    }

    .date-info, .registration-info {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-right: 20px;
    }

    .registration {
        display: flex;
        align-items: center;
    }

    .button {
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: white;
        margin-left: 20px;
    }

    .button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="title">第103回全国高等学校野球選手権大会</div>
<div class="container">
    <div class="date-info">
        <div>2023/08/1(木)</div>
        <div>第一回戦</div>
        <div>試合開始: 8:00~</div>
    </div>

    <div class="registration-info">
        <div>受付期間 7/1(月)~7/31(水)</div>
        <div>受付中 or 受付前</div>
    </div>

    <div class="registration">
        <button class="button" onclick="location.href='#'">申し込む</button>
    </div>
</div>

</body>
</html>
