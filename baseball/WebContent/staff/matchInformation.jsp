<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp" %>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
</head>
<body>
<c:choose>
<%-- 試合情報がないとき --%>
<c:when test="${matchList == null }">
	試合情報が登録されていません
</c:when>
<%-- 試合情報があるとき --%>
<c:when test="${matchList != null }">
	<h2>試合情報</h2>

	<div id="app">
		<%-- ドロップダウンリスト --%>
		<select>
			<c:forEach var="match" items="${matchList }">
	    	<option value="${match.eventDate }">${match.eventDateStr }</option>
	    	</c:forEach>
	    </select>
	    <%-- 開催日ごとのテーブル --%>
		<c:forEach begin="0" end="${fn:length(duelList)-1}" step="1" var="i">
			<table border="1">
			<tr><th>開催日</th><th>試合順</th><th>高校名</th><th>高校名</th><th>試合状況</th><th>第何回戦</th><th>販売日時</th></tr>

				 <c:forEach begin="0" end="${fn:length(duelList[i])-1}" step="1" var="j">
				<tr>
				<c:if test="${j == 0 }">
					<td rowspan="4"><a href="MatchUpdateInput?date=${matchList[i].eventDate }">${matchList[i].eventDateStr }</a></td>
				</c:if>

				<%--<v-for value="(match ,index in matchList)" >

				</v-for>
	--%>

				<c:choose>
					<c:when test="${j == 0 }">
						<td>第一試合</td>
					</c:when>
					<c:when test="${j == 1 }">
						<td>第二試合</td>
					</c:when>
					<c:when test="${j == 2 }">
						<td>第三試合</td>
					</c:when>
					<c:when test="${j == 3 }">
						<td>第四試合</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${duelList[i][j].schoolNameA == null}">
						<td>未登録</td><td>未登録</td><td>未登録</td><td>未登録</td>
					</c:when>
					<c:when test="${duelList[i][j].schoolNameA != null}">
						<td>${duelList[i][j].schoolNameA }</td>
						<td>${duelList[i][j].schoolNameB }</td>
						<td>${duelList[i][j].statusStr }</td>
						<td>${duelList[i][j].roundStr }</td>
					</c:when>
				</c:choose>
				<c:if test="${j == 0 }">
					<td rowspan="4">${matchList[i].saleStartAtStr }</td>
				</c:if>
				</tr>
				</c:forEach>
			</table>
		</c:forEach>
		</div>
	   </c:when>
	</c:choose>

<%--
	<script>
		new Vue({
			el: '#app',
			data: {
				matchList:[
					<c:forEach var="match" items="${matchList}">
						{eventDate:${match.eventDate },eventDateStr:${match.eventDateStr },saleStartAt:${match.saleStartAtStr },},
					</c:forEach>
				],
				duelList:[
					<c:forEach begin="0" end="${fn:length(duelList)-1}" step="1" var="i">
					<c:forEach begin="0" end="${fn:length(duelList[i])-1}" step="1" var="j">
						{schoolNameA:${duelList[i][j].schoolNameA },schoolNameB:${duelList[i][j].schoolNameB },statusStr:${duelList[i][j].statusStr },roundStr:${duelList[i][j].roundStr },},
					</c:forEach>
					</c:forEach>
				],
				selectedEventDate:"",
			},
			created: function() {

		    },
			methods:{
				select:function(index){
					var selectedSeatId = this.seatsList[index].seatId;
					for (var i = 0; i < this.ticketsList.length; i++) {
				        if (selectedSeatId === this.ticketsList[i].seatId) {
				            if (this.ticketsList[i].status === 3) {
				                this.selectedTickets.push(this.ticketsList[i]);
				            }
				            break;
				        }
				    }
				},
			},
			)}
	</script>
--%>
</body>
</html>