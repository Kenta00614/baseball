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

	 .main_display {
    	margin-top:-10px;
	    background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed; /* 背景画像を固定 */
        background-color: rgba(255,255,255,0.3);
  		background-blend-mode: lighten;
	    background-image: url("${pageContext.request.contextPath}/customer/image/main_background.JPG") ;
	    z-index:1;
	    width: 100%;
	}

/*テーブル設定*/

	table{
		border: initial;
	    width: initial;
	    margin: initial;
	    z-index:2;
	    width: 95%;
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
	    padding: 20px ;
	}

	tr:last-child * {
	    border-bottom: none;
	}

    .not-match-main{
    	width: 1000px;
    	padding-left: 15px;
    	padding-right: 0px;
    }
    body{
		 background-color: initial;

    }

	h1{
		padding: 10px;
		background-color: white;
		border-radius: 20px;
		opacity: 0.9
	}


@media screen and (max-width: 1050px){
	/* スマートフォン用CSS記述 */

	.main_display {
    	margin-top:-10px;
	    background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed; /* 背景画像を固定 */
        background-color: rgba(255,255,255,0.5);
  		background-blend-mode: lighten;
	    background-image: url("${pageContext.request.contextPath}/customer/image/main_background2.jpg") ;
	    z-index:1;
	    width: 100%;
	}

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

    .num_match{/* 〇試合目 */
		width: 22%;
    }



	/*header*/
    .nav__item{
		  margin:3px ;
		  font-size: 11px;
		  margin-right: 2px;
		  margin-top: 12px;
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

	.not-match-main{
    	padding-left: 30px;
    	padding-bottom: 20px;
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

