<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .login-box {
            width: 300px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .input {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
		    background-color: #000;
		    color: #fff;
		    cursor: pointer;
		}

		input[type="submit"]:hover {
		    background-color: #333;
		}
        a.btn {
            display: block;
            text-align: center;
            text-decoration: none;
            background-color:#0066FF;
            color: #fff;
            padding: 10px;
            border-radius: 4px;
        }

        a.btn:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Login</title>
</head>
<body>
    <h2>甲子園管理システム</h2>
    <div class="login-box">
        <form action="Login" method="post">
            <div class="input">
                <label for="user-id">ID</label>
                <input name="id" type="text" id="user-id" required>
            </div>
            <div class="input">
                <label for="password">Password</label>
                <input name="password" type="password" id="password" placeholder="Password" required>
            </div>
            <p><input type="submit" value="Login"></p>

        </form>
    </div>
    <c:if test="${sessionOut == 1 }">
	   <script>
	      	alert('セッションが切れています、ログインしなおしてください。');
	   </script>
   </c:if>
</body>
</html>
