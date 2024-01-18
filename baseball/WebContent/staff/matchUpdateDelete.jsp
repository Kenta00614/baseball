<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*,java.util.*,bean.Tournament" %>
<% String date = (String)request.getAttribute("eventDate"); %>
<% Tournament t = (Tournament)request.getAttribute("tournament"); %>
<%@ include file="header.jsp"%>

<html>
<head>
    <style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f5f5f5;
	        margin: 0;
	        padding: 0;
	    }

	    h2 {
	        color: #333;
	    }

	    p {
	        color: #666;
	    }

	    form {
	        margin-top: 20px;
	    }

	    button {
	        background-color: #3498db; /* Blue color */
	        color: white;
	        padding: 10px 15px;
	        border: none;
	        border-radius: 4px;
	        cursor: pointer;
	        font-size: 16px;
	    }

	    button:hover {
	        background-color: #2980b9; /* Darker blue color on hover */
	    }
    </style>
</head>
<body>
    <div style="margin: 20px; padding: 20px; background-color: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
        <h2><%=t.getOrdinalNum() %>回　<%=t.getName() %></h2>
        <p><%=date.substring(0,4) %>年<%=date.substring(5,7) %>月<%=date.substring(8,10) %>日の試合情報を削除します。</p>

        <form action="MatchUpdateInput" method="post">
            <input type="hidden" value="${eventDate}" name="date">
            <button type="submit">戻る</button>
        </form>

        <form action="MatchInformation" method="post">
            <input type="hidden" value="${tournamentId}" name="tournamentId">
            <input type="hidden" value="${eventDate}" name="delEventDate">
            <button type="submit">確認</button>
        </form>
    </div>
</body>
</html>
