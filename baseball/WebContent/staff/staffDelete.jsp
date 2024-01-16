<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="bean.Staff, java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("list"); %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5F5F5;
            color: #333;
        }

        .header {
            background-color:#F5F5F5;
            color:  #555555;
            padding: 0.1px;
            text-align: center;
        }

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .control {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            cursor: pointer;
        }

        button {
            background-color: #0066FF;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        table {
            width: 100%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            background-color: #F5F5F5;
            color: #555555;
        }

        th {
            background-color: #DCDCDC;
        }

        input[type="checkbox"] {
            background-color: white;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>職員情報の削除</h1>
    </div>
    <div class="form-container">
        <form action="StaffDeleteCheck" method="get">
            <table>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>氏名</th>
                    <th>役職</th>
                </tr>
                <% for(Staff s : list){ %>
                    <tr>
                        <td><label><input type="checkbox" name="selectedIDs" value=<%=s.getStaffId() %> v-model="selectedIDs"></label></td>
                        <td><%=s.getStaffId() %></td>
                        <td><%=s.getName() %></td>
                        <% String state = s.getPosition();
                        String grade = (state.equals("1")) ? "スタッフ" : "管理者"; %>
                        <td><%=grade %></td>
                    </tr>
                <% } %>
            </table>
            <c:if test="${noSelectFlg == 0 }">
            	<p>職員が選択されていません</p>
            </c:if>
            <button type="submit">削除</button>
            <script>
                new Vue({
                    el: 'form',
                    data: {
                        selectedIDs: []
                    }
                })
            </script>
        </form>
    </div>
</body>
</html>
