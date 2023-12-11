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
            background-color: #f4f4f4; /* 背景色を灰色に変更 */
            color: #333; /* テキスト色を黒に変更 */
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .header {
            background-color: #333; /* ヘッダーの背景色を黒に変更 */
            color: white;
            padding: 20px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            background-color: #333; /* 背景色を黒に変更 */
            color: white; /* テキスト色を白に変更 */
        }

        input[type="checkbox"] {
            background-color: white; /* チェックボックスの背景色を白に変更 */
        }

        button {
            background-color: white; /* ボタンの背景色を白に変更 */
            color: #333; /* ボタンのテキスト色を黒に変更 */
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
            color: white;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>職員情報の削除</h1>
    </div>
    <div class="container">
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
            <button type="submit">削除</button>
            <script>
                new Vue({
                    el: 'form', // form要素内のすべての要素をバインド
                    data: {
                        selectedIDs: [] // 選択されたIDを格納する配列
                    }
                })
            </script>
        </form>
    </div>
</body>
</html>
