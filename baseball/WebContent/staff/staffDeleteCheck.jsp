<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Staff,java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("staffList"); %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>職員情報の削除</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .header {
            max-width: 800px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px 12px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
        }

        button[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
		.btn-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: transparent;
            color: #007bff;
            text-decoration: underline;
            border: none;
            border-radius: 4px;
            cursor: pointer;

    </style>
</head>

<body>
    <div class="header">
        <form action="StaffDeleteComplete" method="get">
            <br><a href="StaffDelete" class="btn-link">戻る</a>
            <h1>職員情報の削除</h1>
            <p>以下の情報を削除しますか？</p>
            <table>
                <tr>
                    <th>ID</th>
                    <th>氏名</th>
                    <th>役職</th>
                </tr>
                <% for(Staff s : list){ %>
                <tr>
                    <td><%=s.getStaffId() %></td>
                    <td><%=s.getName() %></td>
                    <% String state = s.getPosition();
                    String grade = (state.equals("1")) ? "スタッフ" : "管理者"; %>
                    <td><%=grade %></td>
                </tr>
                <% } %>
            </table>
            <button type="submit">確認</button>
        </form>
    </div>
</body>

</html>
