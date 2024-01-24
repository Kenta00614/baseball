<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>大会情報登録</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5F5F5;
        }

        .header {
            background-color: #F5F5F5;
            color: #555555;
            padding: 6px;
            text-align: center;
        }

        .form-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
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
            background-color: #0066FF;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
	<div class="header">
	    <h2>大会情報登録　入力画面</h2>
	    <div class="form-container"> <!-- form-container を追加 -->
	        <form action="TournamentRegistrationInputConfirm" method="post">
	            <div class="control">
	                <label for="year">開催年:</label>
	                <input type="text" id="year" name="year" required>
	            </div>
	            <div class="control">
	                <label for="ordinalNum">第何回:</label>
	                <input type="text" id="ordinalNum" name="ordinalNum" required>
	            </div>
	            <div class="control">
	                <label for="season">大会の時期:</label>
	                <select id="season" name="season">
	                	<option value="春">春</option>
	                	<option value="夏">夏</option>
	                </select>
	            </div>
	            <div class="control">
	                <label for="name">大会名:</label>
	                <input type="text" id="name" name="name" required>
	            </div>
	            <button type="submit">登録</button>
	        </form>
	    </div> <!-- form-container を追加 -->
	</div>
</body>
</html>