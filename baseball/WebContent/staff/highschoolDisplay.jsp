<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.School" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>高校情報一覧</title>
</head>
<body>
    <h2>高校情報一覧</h2>
    <form action="HighschoolUpdate" method="post">
        <table border="1">
            <tr>
                <th>高校名</th>
            </tr>
            <%
            List<School> schools = (List<School>)request.getAttribute("schools");
            for (School school : schools) {
            %>
                <tr>
                    <td>
                        <input type="text" name="schoolName<%= school.getSchoolId() %>" value="<%= school.getName() %>">
                    </td>
                </tr>
            <% } %>
        </table>
        <input type="submit" value="変更">
        <input type="hidden" name="tournamentId" value="<%= request.getAttribute("tournamentId") %>">
    </form>
</body>
</html>
