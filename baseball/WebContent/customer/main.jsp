<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メイン画面</title>
    <link rel="stylesheet" type="text/css"  href="/baseball/css/Customer.css">
<style>
		/* PC用CSS記述 */
/*テーブル設定*/

	table{
		border: initial;
	    width: initial;
	    margin: initial;
	}

	table th {
	    color: initial;
	    padding: 30px;
	}
	th,td {
		width: initial;
	    border-bottom:  initial;
	    background-color: initial;
	}
	td {
	    width: 180px;/*枠の横幅*/
	    padding: 20px;
	}

	tr:last-child * {
	    border-bottom: none;
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
            width: 98%;

        }

        .date-center {
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .not-match-main{
        	width: 90%;
        }

@media screen and (max-width: 1050px){
	/* スマートフォン用CSS記述 */

	/*テーブル設定*/
	td {
	    width: 28%;/*要素の横幅*/
	    font-size: 11px;
	    padding: 7px;
	}
	tr:last-child * {
	    border-bottom: none;
	}
	th {
	    padding: 15px;
	}

    .schedule {		/*1試合ごとの枠*/
        border: 3px solid #000000;
        padding: 2px;
        margin-bottom: 5px;
        margin-top: 20px;
        border-radius: 20px;
        width: 93%;
     }

        .date-center {/* 日付 */
            font-weight: bold;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .num_match{/* 〇試合目 */
			width: 23%;
        }

	/*header*/
    .nav__item{
		  margin:3px ;
		  font-size: 12px;
		  margin-right: 1px;
		  margin-top: 10px;;
	}
	.logo-image {
	    margin-left: 0px;
	    height: 25px;
	    /* 必要に応じてwidthを追加 */
	}
	.cust-header {
	    height: 35px;
	}

	.logo {
	    margin: 0px;
	    margin-top: 5px;
	    margin-left: 10px;
	 }

	 body{
	 	margin-top: 50px;
	 }
}

</style>
</head>
<body>
<div class="main_display">
    <c:choose>
        <%-- 試合情報がないとき --%>
        <c:when test="${fn:length(matchList) == 0 }">
            <h2 class="poster2">試合情報が登録されていません</h2>
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
                                <tr><td class="num_match">第${j + 1}試合(${duelList[i][j].roundStr })</td>
                                	<td>${duelList[i][j].schoolNameA }</td>
                                	<td>${duelList[i][j].statusStr }</td>
                                	<td>${duelList[i][j].schoolNameB }</td></tr>
								</c:if>
                        </c:forEach>
						<c:if test="${duelList[i][0].roundStr == null}">
							<tr><td class="not-match-main">試合情報は未定です</td></tr>
						</c:if>
                    </table>
                </div>
            </c:forEach>
        </c:when>
    </c:choose>
</div>
</body>
</html>

