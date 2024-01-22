<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>大会情報登録</title>
    <meta charset="UTF-8">
</head>
<body>
	<div class="header">
	    <h2>大会情報登録　入力画面</h2>
	    <div class="form-container"> <!-- form-container を追加 -->
	        <form action="TournamentRegistrationInput" method="post">
	            <div class="control">
	                <label for="year">開催年:</label>
	                <p id="year" class=""form-input">${requestScope.year}</p>
	            </div>

	            <div class="form-cfm">
		            <label for="name" class="form-element">氏　名　</label>
		            <p id="name" class="form-input">${requestScope.name}</p>
		        </div>




	            <div class="control">
	                <label for="ordinalNum">第何回:</label>
	                <p id="ordinalNum" class="form-input">${requestScope.name}</p>
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