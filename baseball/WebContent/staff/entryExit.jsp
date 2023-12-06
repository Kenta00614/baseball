<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f4f4f4;
	        margin: 0;
	        padding: 0;
	    }

	    h1 {
	        text-align: center;
	        color: #333;
	        margin-bottom: 20px;
	        margin-top: 80px; /* Add margin to the top */
	    }

	    .header {
	        display: flex;
	        justify-content: space-around;
	        width: 60%;
	        margin: 20px auto; /* Reduce margin from 50px to 20px */
	        text-align: center;
	    }

	    button {
	        background-color: #28a745;
	        color: #fff;
	        padding: 15px 30px;
	        border: none;
	        border-radius: 4px;
	        cursor: pointer;
	        font-size: 18px;
	        transition: background-color 0.3s;
	    }

	    button:hover {
	        background-color: #218838;
	    }
</style>
    <title>入退場画面</title>
</head>

<body>
    <h1>入場・退場</h1>
    <div class="header">
        <form action="Entry" method="get">
            <button type="submit">入場</button>
        </form>
        <form action="Exit" method="get">
            <button type="submit">退場</button>
        </form>
    </div>
</body>

</html>
