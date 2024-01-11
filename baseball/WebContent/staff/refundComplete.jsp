<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Tickets,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% List<Tickets> list = (List<Tickets>)request.getAttribute("list"); %>
<% List<Integer> price = (List<Integer>)request.getAttribute("price"); %>
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

        .confirmation-results {
            margin-bottom: 20px;
        }

        p {
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
        <form action="Refund" method="get">
            <% for(Tickets t : list){ %>
                <h1>払い戻し完了</h1>
                <div class="confirmation-results">
                    <p>チケット番号: <%= t.getTicketsId()%></p>
                    <!-- 他の入力フィールドの内容も同様に表示できます。 -->
                </div>
                <p>チケット価格:<%= price.get(0) %>円</p>
                <p>払い戻し対応を行いました。</p>
            <% } %>
            <button type="submit">次へ</button>
        </form>
    </div>
</body>

</html>
