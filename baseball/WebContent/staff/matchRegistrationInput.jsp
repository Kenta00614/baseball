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

	    .date-input-container {
	        display: flex;
	        align-items: center;
	    }

	    .date-input-container input {
	        margin-right: 5px;
	    }

		.form-container {
		    max-width: 500px;
		    margin: 20px auto;
		    padding: 15px;
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
	        padding: 8px;
	        box-sizing: border-box;
	        border: 1px solid #ccc;
	        border-radius: 4px;
	    }

	    select {
	        cursor: pointer;
	        text-align: center;
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

	    table {
	        width: 100%;
	        border-collapse: collapse;
	        margin-top: 15px;
	    }

	    table, th, td {
	        border: 1.5px solid #ddd;
	    }

	    th, td {
	        padding: 10px;
	        text-align: center;
	    }

	</style>


</head>

<body>
    <c:choose>
        <c:when test="${tournament == null }">
            <!-- 大会情報がないとき -->
            <div class="header">
                <h2>大会情報が登録されていません</h2>
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
                                <td><select name="duel${duel}School1" id="duel${duel}School1">
                                        <c:forEach var="school" items="${schoolList }">
                                        	<c:if test="${school.name != ''}">
                                            	<option value="${school.schoolId }">${school.name }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select></td>
                                <td><select name="duel${duel}School2" id="duel${duel}School2">
                                        <c:forEach var="school" items="${schoolList }">
                                        	<c:if test="${school.name != ''}">
                                            	<option value="${school.schoolId }">${school.name }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select></td>
                                <td>
                                	<select name="duel${duel}Round">
                                        <c:forEach var="i" begin="0" end="5">
                                            <option value="${i+1}">${duelRound[i]}</option>
                                        </c:forEach>
                                    </select></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${insertNum == 0 }">
                        <div class="error-message">同じ開催日が登録されています。</div>
                    </c:if>
                    <c:if test="${insertNum == 1 }">
                        <div class="error-message">同じ高校は登録できません</div>
                    </c:if>
                    <c:if test="${dateCheck == -1 }">
                        <div class="error-message">開催日の入力に誤りがあります。</div>
                    </c:if>
                    <c:if test="${saleCheck == -1 }">
                        <div class="error-message">販売日の入力に誤りがあります。</div>
                    </c:if>
                    <input type="hidden" name="tournamentId" value="${tournament.tournamentId }">
                    <button type="submit" id ="button" >登録</button>
                </form>
            </div>
        </c:when>
    </c:choose>

	<script>
	<%-- なし以外で重複がないようにする --%>
	document.addEventListener('DOMContentLoaded', function () {
		<%-- 配列作成 --%>
	    var inputs = [];
	    for (let i = 1; i < 5; i++) {
	        var school1 = document.getElementById('duel' + i + 'School1');
	        var school2 = document.getElementById('duel' + i + 'School2');
	        inputs.push(school1);
	        inputs.push(school2);
	    }
	    const submitButton = document.getElementById('button');

	    <%-- 重複があるときはボタンを非活性 --%>
	    inputs.forEach(input => {
	        input.addEventListener('change', validateInputs);
	    });

	    <%-- 重複ありでボタン押されたらalert --%>
	    submitButton.addEventListener('click', function () {
	        validateInputs();
	        if (submitButton.disabled) {
	        	alert('同じ高校名は選択できません');
	            return false; // ボタンが無効な場合はフォームの送信をキャンセル
	        }
	    });

	    <%-- ボタン非活性メソッド --%>
	    function validateInputs() {
	        var result = inputs.filter(function (input) {
	            return input.value !== '0';
	        });

	        if (isDuplicated(result)) {
	            submitButton.disabled = true;
	        } else {
	            submitButton.disabled = false;
	        }
	    }

	    <%-- 重複確認メソッド --%>
	    function isDuplicated(array) {
	        var uniqueValues = new Set(array.map(input => input.value));
	        return array.length !== uniqueValues.size;
	    }
	});


	</script>
</body>
</html>