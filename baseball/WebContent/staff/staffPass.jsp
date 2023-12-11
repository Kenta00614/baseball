<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #000;
            color: #fff;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #000;
            color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.1);
        }

        .control {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #fff;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 3px;
            color: #000; /* Input text color */
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: #ff0000;
            margin-top: 5px;
        }
    </style>
</head>

<body>
    <div class="header">
        <h1>パスワード再設定</h1>
    </div>
    <form action="StaffPass" method="post" onsubmit="return validatePassword()">
        <div class="control">
            <label for="id">ID</label>
            <input id="id" type="id" name="id" required>
        </div>
        <div class="control">
            <label for="password">パスワード</label>
            <input id="password" type="password" name="password" required>
        </div>
        <div class="control">
            <label for="password2">パスワード確認</label>
            <input id="password2" type="password" name="password2" required>
            <p class="error-message" id="passwordError"></p>
        </div>
        <p><input type="submit" value="Reset"></p>
    </form>

    <script>
        function validatePassword() {
            var password1 = document.getElementById("password").value;
            var password2 = document.getElementById("password2").value;
            var passwordError = document.getElementById("passwordError");

            if (password1 !== password2) {
                passwordError.innerHTML = "パスワードが一致しません。";
                return false; // フォームが送信されない
            } else {
                passwordError.innerHTML = "";
                return true; // フォームを送信
            }
        }
    </script>
</body>

</html>
