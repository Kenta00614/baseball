<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.School" %>
<%@ include file="header.jsp" %>
<%! int formContainerMargin = 100; %>

<%
    // Check if a request parameter for margin is present
    String marginParam = request.getParameter("margin");
    if (marginParam != null && !marginParam.isEmpty()) {
        try {
            formContainerMargin = Integer.parseInt(marginParam);
        } catch (NumberFormatException e) {
            // Handle parsing error if necessary
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>高校情報一覧</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color:#555555;
            color: white;
        }

        .form-container {
            max-width:800px;
            margin: <%= formContainerMargin %>px;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .center {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="form-container" style="max-width: 600px; margin: <%= formContainerMargin %>px auto;">
        <h2>高校情報一覧</h2>
        <form action="HighschoolUpdate" method="post">
            <table>
                <thead>
                    <tr>
                        <th>高校名</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<School> schools = (List<School>) request.getAttribute("schools");
                        int count = 0;
                        for (School school : schools) {
                            if (!school.getName().equals("")) {
                                if (count == 0 || count % 4 == 0) {
                    %>
                                    <tr>
                                <%
                                }
                    %>
                                <td><%= school.getName() %></td>
                    <%
                                count += 1;
                                if (count % 4 == 0) {
                    %>
                                    </tr>
                    <%
                                }
                            }
                        }
                        if (count % 4 != 0) {
                    %>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <div class="center">
                <input type="submit" value="変更">
                <input type="hidden" name="tournamentId" value="<%= request.getAttribute("tournamentId") %>">
            </div>
        </form>
    </div>
</body>
</html>
