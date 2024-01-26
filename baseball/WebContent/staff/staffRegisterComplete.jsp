<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="bean.Staff,java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規職員登録</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .header h1 {
            color: #333;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;

        }

        .header p {
            margin: 10px 0;
            font-size: 16px;
            text-align: left;
        }

        .grade-staff {
            color: #007bff;
        }

        .grade-admin {
            color: #dc3545;
        }
    </style>
</head>

<body>
    <div class="container">
        <% for(Staff s: list) { %>
<div class="header" style="text-align: center; margin-bottom: 20px;">
    <h1>新規職員登録</h1>
    <p>以下の職員の登録が完了しました。</p>
    <p>ID：<%= s.getStaffId() %></p>
    <p>氏名:<%= s.getName() %></p>
    <p>生年月日(PW):<%= s.getPassword() %></p>
    <%
        String state = s.getPosition();
        String grade = null;

        if(state.equals("1")) {
            grade = "スタッフ";
        } else {
            grade = "管理者";
        }
    %>
    <p>職員ステータス:
        <% if(grade.equals("スタッフ")) { %>
            <span class="grade-staff"><%= grade %></span>
        <% } else { %>
            <span class="grade-admin"><%= grade %></span>
        <% } %>
    </p>
</div>



        <% } %>
    </div>
</body>

</html>
