<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="bean.Staff,java.util.List" %>
<% List<Staff> list = (List<Staff>)request.getAttribute("list"); %>
<html>

<body>
<% for(Staff s: list) {%>
    <div class="header">
    <h1>新規職員登録</h1>
	<p>以下の職員の登録が完了しました。</p><br>
	<p>ID：<%=s.getStaffId() %>></p>
	<p>氏名:<%=s.getName() %></p>
	<p>生年月日(PW):<%=s.getPassword() %></p>

	<%
	String state = s.getPosition();
	String grade = null;

	if(state.equals("1")){
		grade = "スタッフ";
	}else{
		grade = "管理者";
	}
	%>
	<p>職員ステータス:<%=grade %></p><br>
    </div>
<% } %>
 </body>
 </html>