<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<html>

<head>
    <title>高校登録</title>
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

        form {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            padding: 20px;
        }

        div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555555;
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
	<a href="TournamentList" type="submit">戻る</a>
    <h2>高校情報登録</h2>
    <form action="HighschoolRegistration" method="post">
        <!-- 大会IDの隠しフィールド -->
        <input type="hidden" name="tournamentId" value="${tournamentId}">
        <table>
	        <% for (int i = 1; i <= 52; i++) { %>

	            <%if(i == 1){ %>
	            	<tr>
	            <%} %>
	            <td>
	                <label for="schoolName<%=i%>">高校名<%=i%>:</label>
	                <input type="text" name="schoolName<%=i%>" id="schoolName<%=i%>">
                </td>
                <%if(i%4 == 0 && i!=52) {%>
	            	</tr><tr>
	            <%}else if(i == 52){ %>
	            </tr>
	            <%} %>
	        <% } %>
        </table>
        <div>
            <input type="submit" value="登録">
        </div>
    </form>
</body>

</html>
