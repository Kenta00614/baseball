<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="bean.Staff, java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("list"); %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
</head>
<body>
    <div class="header">
        <h1>職員情報の削除</h1>
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
                        <th><label><input type="checkbox" name="selectedIDs" value=<%=s.getStaffId() %> v-model="selectedIDs"></label></th>
                        <th><%=s.getStaffId() %></th>
                        <th><%=s.getName() %></th>
                        <% String state = s.getPosition();
                        String grade = (state.equals("1")) ? "スタッフ" : "管理者"; %>
                        <th><%=grade %></th>
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
