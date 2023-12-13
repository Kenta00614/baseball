<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp"%>
<html>

<head>
	<style>
	    /* 既存のスタイル */
	    body {
	        font-family: 'Arial', sans-serif;
	        margin: 0;
	        padding: 0;
	        background-color: #F5F5F5;
	    }

	    .header {
	        background-color: #232733;
	        color: white;
	        padding: 6px;
	        text-align: center;
	    }

	    .date-input-container {
	        display: flex;
	        align-items: center;
	    }

	    .date-input-container input {
	        margin-right: 5px;
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

	    /* 変更点: ボタンを同じ列に表示するスタイル */
	    button {
	        background-color: #ff6347;
	        color: white;
	        padding: 8px; /* 調整可能なパディング */
	        border: none;
	        border-radius: 5px;
	        cursor: pointer;
	        width: 100%; /* 100%にして横幅いっぱいに */
	        font-size: 14px; /* 調整可能なフォントサイズ */
	        margin-bottom: 10px; /* 下マージンを追加してボタンを離す */
	    }
	</style>


</head>

<body>
    <c:choose>
        <c:when test="${tournament == null }">
            <!-- 大会情報がないとき -->
            <div class="header">
                <h2>大会情報は登録されていません</h2>
            </div>
            <div class="form-container">
                <form action="TournamentRegistrationInputDisplay" method="get">
                    <button type="submit">大会情報登録</button>
                </form>
            </div>
        </c:when>
        <c:when test="${tournament != null }">
            <!-- 大会情報があるとき -->
            <div class="header">
                <h2>第${tournament.ordinalNum }回　${tournament.name }</h2>
            </div>
            <div class="form-container">
                <form action="MatchRegistrationCompletion" method="post">
					<div class="control">
					    <label for="eventDateMonth">開催日</label>
					    <div class="date-input-container">
					        <input type="text" maxlength="2" name="eventDateMonth" pattern="[0-9]|1[0-2]" title="1から12の半角数字を入力してください" required>月
					        <input type="text" maxlength="2" name="eventDateDate" pattern="^(0?[1-9]|[1-2][0-9]|3[0-1])$" title="1から31の半角数字を入力してください" required>日
					    </div>
					</div>
                    <div class="control">
                        <label for="saleAtMonth">チケット販売開始日</label>
                        <div class="date-input-container">
					        <input type="text" maxlength="2" name="saleAtMonth" pattern="[0-9]|1[0-2]" title="1から12の半角数字を入力してください" required>月
					        <input type="text" maxlength="2" name="saleAtDate" pattern="^(0?[1-9]|[1-2][0-9]|3[0-1])$" title="1から31の半角数字を入力してください" required>日
					    </div>
                    </div>
                    <table>
                        <tr>
                            <th></th>
                            <th>高校名</th>
                            <th>高校名</th>
                            <th>第何回戦</th>
                        </tr>
                        <c:forEach var="duel" begin="1" end="4">
                            <tr>
                                <td>第${duel}試合</td>
                                <td><select name="duel${duel}School1">
                                        <c:forEach var="school" items="${schoolList }">
                                            <option value="${school.schoolId }">${school.name }</option>
                                        </c:forEach>
                                    </select></td>
                                <td><select name="duel${duel}School2">
                                        <c:forEach var="school" items="${schoolList }">
                                            <option value="${school.schoolId }">${school.name }</option>
                                        </c:forEach>
                                    </select></td>
                                <td><select name="duel${duel}Round">
                                        <c:forEach var="round" begin="1" end="6">
                                            <option value="${round}">第${round}回戦</option>
                                        </c:forEach>
                                    </select></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${insertNum == 0 }">
                        <div class="error-message">同じ日付が登録されています</div>
                    </c:if>
                    <input type="hidden" name="tournamentId" value="${tournament.tournamentId }">
                    <button type="submit">試合情報登録</button>
                </form>
                <form action="TournamentUpdateInput" method="get" value="${tournament.tournamentId}">
                    <button type="submit">大会情報変更</button>
                </form>
            </div>
        </c:when>
    </c:choose>
</body>

</html>
