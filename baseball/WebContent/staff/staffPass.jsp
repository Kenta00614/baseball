<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html>

<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color:#F5F5F5;
            color: #F5F5F5;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color:#F5F5F5;
            color: #555555;
            text-align: center;
            padding: 0.1px;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            background-color:white;
            color: #555555;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.1);
        }

        .control {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555555;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 3px;
            color: #000;
        }

        input[type="submit"] {
            background-color:#0066FF;
            color: white;
            cursor: pointer;
        }

        }

        .error-message {
            color: #555555;
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
		    <p>${id}</p>
		    <input id="id" type="hidden" name="id" value="${id}" >
        </div>
        <div class="control">
            <label for="password">パスワード</label>
            <input id="password" type="password" name="password" pattern="[!-~]{8,}" required>
        </div>
        <div class="control">
            <label for="password2">パスワード確認</label>
            <input id="password2" type="password" name="password2" pattern="[!-~]{8,}" required>
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

        alert('初期パスワードのままです、パスワードの再設定をしてください。');
    </script>
</body>

</html>
