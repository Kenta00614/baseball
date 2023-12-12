<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="header.jsp"%>
<html>
<body>
<h2>第${tournament.ordinalNum }回　${tournament.name }</h2>
	<form action="MatchUpdateCompletion" method="post">
		<p>開催日　${matchList[0].eventDateStr }</p>
     	<p>チケット販売開始日　${matchList[0].saleStartAtStr }</p>
     	<table>
     		<tr><th></th><th>高校名</th><th>高校名</th><th>試合状況</th><th>第何回戦</th></tr>
     		<tr>
     			<td>第一試合</td>
     			<td><select name="duel1School1">
	     			<c:forEach begin="0" end="${fn:length(schoolList)-2}" step="1" var="i">
					<option value="${schoolList[i].schoolId }" <c:if test="${duelList[0].schoolId1 == schoolList[i].schoolId}">selected</c:if>>${schoolList[i].name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="duel1School2">
	     			<c:forEach begin="0" end="${fn:length(schoolList)-2}" step="1" var="i">
					<option value="${schoolList[i].schoolId }" <c:if test="${duelList[0].schoolId2 == schoolList[i].schoolId}">selected</c:if>>${schoolList[i].name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="status1">
     				<c:forEach begin="0" end="${fn:length(duelStatus)-1}" step="1" var="i">
     				<option value="${i+1 }" <c:if test="${duelList[0].status == i+1}">selected</c:if>>${duelStatus[i] }</option>
     				</c:forEach>
     			</select></td>
     			<td><select name="duel1Round">
					<option value="1" <c:if test="${duelList[0].round == 1}">selected</c:if>>第一回戦</option>
					<option value="2" <c:if test="${duelList[0].round == 2}">selected</c:if>>第二回戦</option>
					<option value="3" <c:if test="${duelList[0].round == 3}">selected</c:if>>第三回戦</option>
					<option value="4" <c:if test="${duelList[0].round == 4}">selected</c:if>>準々決勝</option>
					<option value="5" <c:if test="${duelList[0].round == 5}">selected</c:if>>準決勝</option>
					<option value="6" <c:if test="${duelList[0].round == 6}">selected</c:if>>決勝</option>
     			</select></td>
     		</tr>
     		<tr>
     			<td>第二試合</td>
     			<td><select name="duel2School1">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[1].schoolId1 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="duel2School2">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[1].schoolId2 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="status2">
     				<c:forEach begin="0" end="${fn:length(duelStatus)-1}" step="1" var="i">
     				<option value="${i+1 }" <c:if test="${duelList[1].status == i+1}">selected</c:if>>${duelStatus[i] }</option>
     				</c:forEach>
     			</select></td>
     			<td><select name="duel2Round">
					<option value="1" <c:if test="${duelList[1].round == 1}">selected</c:if>>第一回戦</option>
					<option value="2" <c:if test="${duelList[1].round == 2}">selected</c:if>>第二回戦</option>
					<option value="3" <c:if test="${duelList[1].round == 3}">selected</c:if>>第三回戦</option>
					<option value="4" <c:if test="${duelList[1].round == 4}">selected</c:if>>準々決勝</option>
					<option value="5" <c:if test="${duelList[1].round == 5}">selected</c:if>>準決勝</option>
					<option value="6" <c:if test="${duelList[1].round == 6}">selected</c:if>>決勝</option>
     			</select></td>
     		</tr>
     		<tr>
     			<td>第三試合</td>
     			<td><select name="duel3School1">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[2].schoolId1 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="duel3School2">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[2].schoolId2 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="status3">
     				<c:forEach begin="0" end="${fn:length(duelStatus)-1}" step="1" var="i">
     				<option value="${i+1 }" <c:if test="${duelList[2].status == i+1}">selected</c:if>>${duelStatus[i] }</option>
     				</c:forEach>
     			</select></td>
     			<td><select name="duel3Round">
					<option value="1" <c:if test="${duelList[2].round == 1}">selected</c:if>>第一回戦</option>
					<option value="2" <c:if test="${duelList[2].round == 2}">selected</c:if>>第二回戦</option>
					<option value="3" <c:if test="${duelList[2].round == 3}">selected</c:if>>第三回戦</option>
					<option value="4" <c:if test="${duelList[2].round == 4}">selected</c:if>>準々決勝</option>
					<option value="5" <c:if test="${duelList[2].round == 5}">selected</c:if>>準決勝</option>
					<option value="6" <c:if test="${duelList[2].round == 6}">selected</c:if>>決勝</option>
     			</select></td>
     		</tr>
     		<tr>
     			<td>第四試合</td>
     			<td><select name="duel4School1">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[3].schoolId1 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="duel4School2">
	     			<c:forEach var="school" items="${schoolList }">
					<option value="${school.schoolId }" <c:if test="${duelList[3].schoolId2 == school.schoolId}">selected</c:if>>${school.name }</option>
	     			</c:forEach>
     			</select></td>
     			<td><select name="status4">
     				<c:forEach begin="0" end="${fn:length(duelStatus)-1}" step="1" var="i">
     				<option value="${i+1 }" <c:if test="${duelList[3].status == i+1}">selected</c:if>>${duelStatus[i] }</option>
     				</c:forEach>
     			</select></td>
     			<td><select name="duel4Round">
					<option value="1" <c:if test="${duelList[3].round == 1}">selected</c:if>>第一回戦</option>
					<option value="2" <c:if test="${duelList[3].round == 2}">selected</c:if>>第二回戦</option>
					<option value="3" <c:if test="${duelList[3].round == 3}">selected</c:if>>第三回戦</option>
					<option value="4" <c:if test="${duelList[3].round == 4}">selected</c:if>>準々決勝</option>
					<option value="5" <c:if test="${duelList[3].round == 5}">selected</c:if>>準決勝</option>
					<option value="6" <c:if test="${duelList[3].round == 6}">selected</c:if>>決勝</option>
     			</select></td>
     		</tr>
     	</table>

		<input type="hidden" value="${matchList[0].duel1 }" name="duel1">
		<input type="hidden" value="${matchList[0].duel2 }" name="duel2">
		<input type="hidden" value="${matchList[0].duel3 }" name="duel3">
		<input type="hidden" value="${matchList[0].duel4 }" name="duel4">
		<input type="hidden" value="${matchList[0].matchId }" name="matchId">
		<button type="submit">試合変更</button>
	</form>

	<form action="MatchUpdateDelete" method="post">
		<input type="hidden" value="${matchList[0].eventDate }" name="eventDate">
		<input type="hidden" value="${tournament.tournamentId }" name="tournamentId">
    	<button type="submit">試合削除</button>
	</form>
</body>
</html>
