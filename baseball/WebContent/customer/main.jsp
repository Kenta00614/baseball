<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp"%>

<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メイン画面</title>
    <link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
<style>

	/*テーブル設定*/

	table{
		border: initial;
	    width: initial;
	    margin: initial;
	}

	table th {
	    background-color:initial;
	    width:initial;
	    color: initial;
	}

	table td {
	    background-color: initial;
	    width: initial;
	}

	table th,
	table td {
	    border-bottom:  initial;
	    padding: 20px; /* 枠線指定 */
	}

	table tr:last-child * {
	     border-bottom: none;
	}

	  .main_display {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .tournament {
            margin-bottom: 10px;
        }

        .schedule {
            border: 3px solid #000000;
            padding: 7px;
            margin-bottom: 10px;
            margin-top: 25px;
            border-radius: 20px;
        }

        .schedule > div {
            margin-bottom: 5px;
        }

        .date-center {
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 10px;
        }

</style>
</head>
<body>
<div class="main_display">
    <c:choose>
        <%-- 試合情報がないとき --%>
        <c:when test="${fn:length(matchList) == 0 }">
            試合情報が登録されていません
        </c:when>
        <%-- 試合情報があるとき --%>
        <c:when test="${fn:length(matchList) > 0 }">
            <h1 class="tournament">第${tournament.ordinalNum }回${tournament.name }</h1>
            <%-- 開催日ごとのテーブル --%>
            <c:forEach begin="0" end="${fn:length(duelList)-1}" step="1" var="i">
                <div class="schedule">

                    <table>
						<tr><th  colspan="4" class="date-center">${matchList[i].eventDateStr }(${matchList[i].eventDayOfWeek})</th></tr>
                        <c:forEach begin="0" end="${fn:length(duelList[i])-1}" step="1" var="j">
								<c:if test="${duelList[i][j].roundStr != null}">
                                <tr><td>第${j + 1}試合(${duelList[i][j].roundStr })</td>
                                	<td>${duelList[i][j].schoolNameA }</td>
                                	<td>${duelList[i][j].statusStr }</td>
                                	<td>${duelList[i][j].schoolNameB }</td></tr>
								</c:if>
                        </c:forEach>

                    </table>
                </div>
            </c:forEach>
        </c:when>
    </c:choose>
</div>
</body>
</html>

