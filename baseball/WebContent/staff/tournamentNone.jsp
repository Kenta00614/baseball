<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp"%>
<html>

<head>
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
		    max-width: 500px;
		    margin: 20px auto;
		    padding: 15px;
		    background-color: white;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		    border-radius: 5px;
}

	    button {
	        background-color: #0066FF;
	        color: white;
	        padding: 8px;
	        border: none;
	        border-radius: 5px;
	        cursor: pointer;
	        width: 100%;
	        font-size: 15px;
	        margin-bottom: 10px;
	    }
	</style>
</head>

<body>
	<!-- 大会情報がないとき -->
	<div class="header">
	    <h2>大会情報が登録されていません</h2>
	</div>
	<div class="form-container">
	    <form action="TournamentRegistrationInputDisplay" method="get">
	        <button type="submit">大会情報登録</button>
	    </form>
	</div>
</body>
</html>