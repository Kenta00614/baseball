<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>スケジュール</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .title {
            text-align: center;
            font-size: 24px;
            margin: 20px 0;
        }
        .schedule {
    		display: flex;
    		justify-content: center;  /* 中央寄せに変更 */
    		margin-bottom: 10px;
    		flex-wrap: wrap;  /* 必要に応じて折り返し */
		}

		.schedule > div {
    		margin: 0 10px;  /* 各項目の左右のマージンを設定 */
		}

		.date-center {
    		text-align: center;
    		font-size: 18px;
    		margin: 20px 0;
		}


    </style>
</head>
<body>

<h1 class="title">第102回全国高等学校野球選手権大会</h1>

<p class="date-center">8月1日 (木曜日)</p>


<div class="schedule">
    <div>第一試合 (第1回戦)</div>
    <div>未定</div>
    <div>8:00 試合開始</div>
    <div>未定</div>
</div>

<div class="schedule">
    <div>第二試合 (第1回戦)</div>
    <div>未定</div>
    <div>11:00 試合開始</div>
    <div>未定</div>
</div>

<div class="schedule">
    <div>第三試合 (第1回戦)</div>
    <div>未定</div>
    <div>14:00 試合開始</div>
    <div>未定</div>
</div>

</body>
</html>


