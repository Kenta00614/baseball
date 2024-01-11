<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ticketId = (String) request.getAttribute("ticketId");
    String state = (String) request.getAttribute("state");
%>
<%@ include file="header.jsp" %>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .header {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            text-align: center;
        }

        h1 {
            color: #3498db;
        }

        .confirmation-results p {
            margin: 10px 0;
        }

        button {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
        }

        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>

<body>
    <div class="header">
        <form action="RefundComplete" method="get">
            <h1>払い戻し対応</h1>
            <div class="confirmation-results">
                <p>チケット番号: <%= ticketId %></p>
            </div>

            <% if(state.equals("6")){ %>
                <p>払い戻し可能です。</p>
                <button type="submit">払い戻し</button>
            <% }else if(state.equals("5")){ %>
                <p>払い戻し済みです。</p>
            <% }else if(state.equals("-1")){ %>
                <p>照合するチケット番号がありません。</p>
            <% }else{ %>
                <p>払い戻し対応が不要なチケットです。</p>
            <% } %>
        </form>
    </div>
</body>

</html>
