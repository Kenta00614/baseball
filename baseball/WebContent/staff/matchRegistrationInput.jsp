<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<html>
<body>
	<c:choose>
		<%--大会情報がないとき --%>
		<c:when test="${tournament == null }">大会情報は登録されていません
			<form action="TournamentRegistrationInputDisplay" method="get">
	        	<button type="submit">大会情報登録</button>
	        </form>
	     </c:when>
		<%-- 大会情報があるとき --%>
		<c:when test="${tournament != null }">
		<h2>第${tournament.ordinalNum }回　${tournament.name }</h2>
	        <form action="MatchRegistrationCompletion" method="get">
	        	<p>開催日<input type="text" maxlength="2" name="eventDateMonth" pattern="[0-9]|1[0-2]" required>月<input type="text" maxlength="2" name="eventDateDate" pattern="[0-9]|1[0-2]" required>日</p>
	        	<p>チケット販売開始日<input type="text" maxlength="2" name="saleAtMonth" pattern="[0-9]|1[0-2]" required>月<input type="text" maxlength="2" name="saleAtDate" pattern="[0-9]|1[0-2]" required>日</p>
	        	<table>
	        		<tr><th></th><th>高校名</th><th>高校名</th><th>第何回戦</th></tr>
	        		<tr>
	        			<td>第一試合</td>
	        			<td><select name="duel1School1">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel1School2">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel1Round">
							<option value="1">第一回戦</option>
							<option value="2">第二回戦</option>
							<option value="3">第三回戦</option>
							<option value="4">準々決勝</option>
							<option value="5">準決勝</option>
							<option value="6">決勝</option>
	        			</select></td>
	        		</tr>
	        		<tr>
	        			<td>第二試合</td>
	        			<td><select name="duel2School1">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel2School2">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel2Round">
							<option value="1">第一回戦</option>
							<option value="2">第二回戦</option>
							<option value="3">第三回戦</option>
							<option value="4">準々決勝</option>
							<option value="5">準決勝</option>
							<option value="6">決勝</option>
	        			</select></td>
	        		</tr>
	        		<tr>
	        			<td>第三試合</td>
	        			<td><select name="duel3School1">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel3School2">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel3Round">
							<option value="1">第一回戦</option>
							<option value="2">第二回戦</option>
							<option value="3">第三回戦</option>
							<option value="4">準々決勝</option>
							<option value="5">準決勝</option>
							<option value="6">決勝</option>
	        			</select></td>
	        		</tr>
	        		<tr>
	        			<td>第四試合</td>
	        			<td><select name="duel4School1">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel4School2">
	        			<c:forEach var="school" items="${schoolList }">
							<option value="${school.schoolId }">${school.name }</option>
	        			</c:forEach>
	        			</select></td>
	        			<td><select name="duel4Round">
							<option value="1">第一回戦</option>
							<option value="2">第二回戦</option>
							<option value="3">第三回戦</option>
							<option value="4">準々決勝</option>
							<option value="5">準決勝</option>
							<option value="6">決勝</option>
	        			</select></td>
	        		</tr>
	        	</table>
	        	<input type="hidden" name="tournamentId" value="${tournament.tournamentId }">
	        	<button type="submit">試合情報登録</button>
	        </form>
	        <form action="TournamentUpdateInput" method="get">
	        	<button type="submit">大会情報変更</button>
			</form>
		</c:when>
	</c:choose>
</body>
</html>
