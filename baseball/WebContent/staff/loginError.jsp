<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Error</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #000; /* Black color */
        }

        p {
            color: #000; /* Black color */
            margin-bottom: 20px;
        }

        .home-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #000; /* Black color */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .home-btn:hover {
            background-color: #333; /* Darker black on hover */
        }
    </style>
</head>

<body>

    <div class="container">
        <h2>ログインできませんでした。</h2>
        <p>ID、パスワードを再確認してください。</p>
        <a href="LoginDisplay" class="home-btn">ログイン画面に戻る</a>
    </div>

</body>

</html>
