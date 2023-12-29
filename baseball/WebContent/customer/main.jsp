<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="header.jsp"%>

<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メイン画面</title>

     <link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
</head>
<body>
<div class=main_display>
	<c:choose>
	<%-- 試合情報がないとき --%>
	<c:when test="${matchList == null }">
		試合情報が登録されていません
	</c:when>
	<%-- 試合情報があるとき --%>
	<c:when test="${matchList != null }">
		<h1 class="tournament">第${tournament.ordinalNum }回${tournament.name }</h1>
	    <%-- 開催日ごとのテーブル --%>
		<c:forEach begin="0" end="${fn:length(duelList)-1}" step="1" var="i">
			<p class="date-center">${matchList[i].eventDateStr }(${matchList[i].eventDayOfWeek})</p>
				<c:forEach begin="0" end="${fn:length(duelList[i])-1}" step="1" var="j">
					<div class="schedule">
						<c:choose>
							<c:when test="${j == 0 }">
								<div>第一試合(${duelList[i][j].roundStr })</div>
							</c:when>
							<c:when test="${j == 1 }">
								<c:if test="${duelList[i][j].roundStr != null}">
									<div>第二試合(${duelList[i][j].roundStr })</div>
								</c:if>
							</c:when>
							<c:when test="${j == 2 }">
								<c:if test="${duelList[i][j].roundStr != null}">
									<div>第三試合(${duelList[i][j].roundStr })</div>
								</c:if>
							</c:when>
							<c:when test="${j == 3 }">
								<c:if test="${duelList[i][j].roundStr != null}">
									<div>第四試合(${duelList[i][j].roundStr })</div>
								</c:if>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${duelList[i][j].schoolNameA != null}">
								<div>${duelList[i][j].schoolNameA }</div>
								<div>${duelList[i][j].statusStr }</div>
								<div>${duelList[i][j].schoolNameB }</div>
							</c:when>
						</c:choose>
					</div>
				</c:forEach>
		</c:forEach>
		</c:when>
	</c:choose>
</div>
</body>
</html>


