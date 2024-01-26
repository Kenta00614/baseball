<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    .header {
        background-color: #f4f4f4;
        color: #555555;
        padding: 20px;
        text-align: center;
    }

    form {
        max-width: 300px;
        margin: 20px auto;
        background-color: #ffffff;
        padding: 20px 50px 10px 50px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

	.control {
	    margin-bottom: 15px;
	    display: flex;
	    align-items: center;
	    text-align: center;
	}

	label {
	    display: inline-block;
	    font-weight: bold;
	    margin-bottom: 5px;
	    margin-right: 10px;
	    min-width: 100px;
	}

	.form-input {
	    flex: 1;
	    padding: 8px;
	    box-sizing: border-box;
	    border: none;
	    border-radius: 4px;
	    margin-top: 5px;
	    min-width: 200px;
	}



	button {
	    background-color: #007bff;
	    color: #ffffff;
	    padding: 10px 15px;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	    display: block;
	    margin: 0 auto;
	}

	button:hover {
	    background-color: #0056b3;
	}


</style>

</head>

<body>
    <div class="header">
        <h2>大会情報変更　確認画面</h2>
    </div>
    <form action="TournamentUpdateCompletion" method="get">
        <div class="control">
            <label for="year">開催年:</label>
            <p id="year" class="form-input">${requestScope.year}</p>
        </div>
        <div class="control">
            <label for="ordinalNum">第何回:</label>
            <p id="ordinalNum" class="form-input">${requestScope.ordinalNum}</p>
        </div>

        <div class="control">
            <label for="name">大会名:</label>
            <p id="name" class="form-input">${requestScope.name}</p>
        </div>

        <div class="control">
            <label for="season">大会の時期:</label>
            <p id="season" class="form-input">${requestScope.season}</p>
        </div>

        <input type="hidden" name="year" value="${requestScope.year}">
        <input type="hidden" name="ordinalNum" value="${requestScope.ordinalNum}">
        <input type="hidden" name="season" value="${requestScope.season}">
        <input type="hidden" name="name" value="${requestScope.name}">
        <button type="submit">変更</button>
    </form>
</body>

</html>
