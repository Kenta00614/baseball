<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
大会情報変更　確認画面
<body>
    <div class="header">
        <form action="TournamentUpdateCompletion" method="get">
	            <div class="control">
	                <label for="year">開催年:</label>
	                <p id="year" class=""form-input">${requestScope.year}</p>
	            </div>
	            <div class="control">
	                <label for="ordinalNum">第何回:</label>
	                <p id="ordinalNum" class="form-input">${requestScope.ordinalNum}</p>
	            </div>

	            <div class="control">
		            <label for="name">大会の時期</label>
		            <p id="season" class="form-input">${requestScope.season}</p>
		        </div>

	            <div class="control">
	                <label for="name">大会名:</label>
	                <p id="name" class="form-input">${requestScope.name}</p>
	            </div>

	        <input type="hidden" name="year" value="${requestScope.year}">
			<input type="hidden" name="ordinalNum" value="${requestScope.ordinalNum}">
			<input type="hidden" name="season" value="${requestScope.season}">
			<input type="hidden" name="name" value="${requestScope.name}">
	            <button type="submit">変更</button>
	        </form>
    </div>
</body>
</html>
