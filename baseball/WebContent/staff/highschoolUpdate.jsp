<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.School" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>高校情報変更</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h2 {
            text-align: center;
            color: #555555;
        }

        .form-container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }


        input[type="text"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        div.center {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <div class="form-container">
        <h2>高校情報変更</h2>
        <form action="HighschoolUpdateCompletion" method="post">
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
                            if (count == 0 || count % 4 == 0) {
                    %>
                                <tr>
                            <%}%>
                            <td>
							    <input type="text" name="schoolName<%= school.getSchoolId() %>" value="<%= school.getName() %>" style="color: f5f5f5;">
							</td>
                            <%
                                count += 1;
                                if (count % 4 == 0) {
                            %>
                                    </tr>
                            <%
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
                <input type="submit" value="保存">
                <input type="hidden" name="tournamentId" value="<%= request.getAttribute("tournamentId") %>">
            </div>
        </form>
    </div>
</body>

</html>
