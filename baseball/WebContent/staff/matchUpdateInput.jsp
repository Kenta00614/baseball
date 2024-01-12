<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <style>
        body {
            font-family: 'Helvetica', 'Arial', sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }

        form {
            margin-bottom: 20px;
        }

        h2 {
            margin-top: 20px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f8f8f8;
            color: #555;
        }

        select {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            box-sizing: border-box;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            padding: 12px 18px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form action="MatchInformation" method="post">
        <input type="hidden" name="tournamentId" value="${tournament.tournamentId}">
        <button type="submit">戻る</button>
    </form>

    <h2>第${tournament.ordinalNum}回　${tournament.name}</h2>

    <form action="MatchUpdateCompletion" method="post">
        <!-- 試合情報表示 -->
        <p>開催日 ${matchList[0].eventDateStr}</p>
        <p>チケット販売開始日 ${matchList[0].saleStartAtStr}</p>

        <!-- 試合情報編集フォーム -->
        <table>
            <tr>
                <th></th>
                <th>高校名</th>
                <th>高校名</th>
                <th>試合状況</th>
                <th>第何回戦</th>
            </tr>
            <c:forEach var="duel" items="${duelList}" varStatus="loop">
                <tr>
                    <td>第${loop.index}試合</td>
                    <td>
                        <select name="duel${loop.index}School1">
                            <c:forEach var="school" items="${schoolList}">
                                <option value="${school.schoolId}" <c:if test="${duel.schoolId1 == school.schoolId}">selected</c:if>>${school.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="duel${loop.index}School2">
                            <c:forEach var="school" items="${schoolList}">
                                <option value="${school.schoolId}" <c:if test="${duel.schoolId2 == school.schoolId}">selected</c:if>>${school.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="status${loop.index}">
                            <c:forEach var="i" begin="0" end="${fn:length(duelStatus)-1}" step="1">
                                <option value="${i+1}" <c:if test="${duel.status == i+1}">selected</c:if>>${duelStatus[i]}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="duel${loop.index}Round">
                            <c:forEach var="round" items="${roundList}">
                                <option value="${round.value}" <c:if test="${duel.round == round.value}">selected</c:if>>${round.label}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <!-- 非表示フィールド -->
        <c:forEach var="duel" items="${duelList}" varStatus="loop">
            <input type="hidden" value="${duel.duelId}" name="duel${loop.index}">
        </c:forEach>

        <input type="hidden" value="${matchList[0].matchId}" name="matchId">
        <button type="submit">変更</button>
    </form>

    <form action="MatchUpdateDelete" method="post">
        <input type="hidden" value="${matchList[0].eventDate}" name="eventDate">
        <input type="hidden" value="${tournament.tournamentId}" name="tournamentId">
        <button type="submit">削除</button>
    </form>