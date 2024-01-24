<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.School" %>
<%@ include file="header.jsp" %>
<html>

<head>
    <title>高校情報変更</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        h2 {
            text-align: center;
            color: #3498db;
            margin-top: 20px;
        }

        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 80%;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ecf0f1;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }
    </style>
</head>

<body>
	<%--<a href="TournamentList" type="submit">戻る</a> --%>
    <h2>高校情報変更</h2>
    <form action="HighschoolUpdateCompletion" method="post">
        <table border="1">
            <tr>
                <th>高校名</th>
            </tr>
            <%
            List<School> schools = (List<School>) request.getAttribute("schools");
            for (School school : schools) {
            %>
                <tr>
                    <td>
                        <input type="text" name="schoolName<%= school.getSchoolId() %>" value="<%= school.getName() %>">
                    </td>
                </tr>
            <% } %>
        </table>
        <div style="text-align: center;">
            <input type="submit" value="保存">
            <input type="hidden" name="tournamentId" value="<%= request.getAttribute("tournamentId") %>">
        </div>
    </form>
</body>

</html>
