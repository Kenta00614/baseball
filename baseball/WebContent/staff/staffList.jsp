<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>職員情報</title>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        button {
            background-color: #007BFF; /* 青のカラーコードに変更 */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3; /* ホバー時の青のカラーコードに変更 */
        }
    </style>
</head>

<body>
    <div class="header">
        <h1>職員情報</h1>
    </div>

    <div class="header">
        <form action="StaffRegister" method="get">
            <button type="submit">新規職員登録</button>
        </form>
    </div>

    <div class="header">
        <form action="StaffDelete" method="get">
            <button type="submit">職員情報の削除</button>
        </form>
    </div>

    <div class="header">
        <form action="StaffPassDisplay" method="get">
            <button type="submit">パスワード再設定</button>
        </form>
    </div>

    <!-- ここにページのその他のコンテンツを追加 -->

</body>

</html>
