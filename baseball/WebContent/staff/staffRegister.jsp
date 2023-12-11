<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規職員登録</title>

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

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .control {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            cursor: pointer;
        }

	    button {
	        background-color: #3498db; /* 青に変更 */
	        color: white;
	        padding: 10px;
	        border: none;
	        border-radius: 5px;
	        cursor: pointer;
	        width: 100%;
	    }

	    button:hover {
	        background-color: #2980b9; /* ホバー時の色を調整 */
	    }
    </style>
</head>

<body>
    <div class="header">
        <h1>新規職員登録</h1>
    </div>

    <div class="form-container">
        <form action="StaffRegisterComplete" method="get">
            <div class="control">
                <label for="id">ID</label>
                <input id="id" type="text" name="id" required>
            </div>

            <div class="control">
                <label for="name">氏名</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="control">
                <label for="birth">生年月日(整数８桁)</label>
                <input type="text" id="birth" name="BIRTH" pattern="[12][0-9]{3}[01][0-9][0-3][0-9]" required>
            </div>

            <div class="control">
                <label for="position">職員ステータス:</label>
                <select name="position">
                    <option value="1">スタッフ</option>
                    <option value="2">管理者</option>
                </select>
            </div>

            <button type="submit">登録</button>
        </form>
    </div>
</body>

</html>
