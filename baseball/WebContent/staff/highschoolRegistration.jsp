<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>

<head>
    <title>高校登録</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .form-container {
            text-align: center;
            margin: 50px auto;
            max-width: 800px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333333;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #dddddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #333333;
            font-weight: bold;
        }

        input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="form-container">
     <a href="TournamentList" style="display: block; margin-bottom: 20px; color: #007bff; text-decoration: none; font-weight: bold; text-align: left;">戻る</a>
        <h2>高校情報登録</h2>
        <form action="HighschoolRegistration" method="post">
            <!-- 大会IDの隠しフィールド -->
            <input type="hidden" name="tournamentId" value="${tournamentId}">
            <table>
                <% for (int i = 1; i <= 52; i++) { %>
                    <% if (i == 1) { %>
                        <tr>
                    <% } %>
                    <td>
                        <label for="schoolName<%=i%>">高校名<%=i%>:</label>
                        <input type="text" name="schoolName<%=i%>" id="schoolName<%=i%>">
                    </td>
                    <% if (i % 4 == 0 && i != 52) { %>
                        </tr><tr>
                    <% } else if (i == 52) { %>
                        </tr>
                    <% } %>
                <% } %>
            </table>
            <div>
                <input type="submit" value="登録">
            </div>
        </form>
    </div>
</body>

</html>
