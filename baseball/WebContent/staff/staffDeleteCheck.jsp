<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Staff,java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("staffList"); %>
<%@include file="header.jsp"%>
<html>

<body>
    <div class="header">

        <form action="StaffDeleteComplete" method="get">
        <br><a href="StaffDelete" class="btn">戻る</a>
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
                        <th><%=s.getStaffId() %></th>
                        <th><%=s.getName() %></th>
                        <% String state = s.getPosition();
                        String grade = (state.equals("1")) ? "スタッフ" : "管理者"; %>
                        <th><%=grade %></th>
                    </tr>
                <% } %>
            </table>
    　　　　<button type="submit">確認</button>
　　　　</form>
    </div>

</body>
</html>
