<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>

    <style>
        /* Reset some default styles */
        body, h1, h2, h3, p, ul {
            margin: 0;
            padding: 0;
        }

        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
        }

        .staff__wrapper {
            max-width: 1200px;
            margin: 0 auto;
        }

        .staff-header {
            background-color: #fff;
            color: #333;
        }

        .nav-menu {
            list-style-type: none;
            display: flex;
            justify-content: space-around;
            align-items: center;
            padding: 10px 0;
        }

        .nav__item {
            margin: 0;
        }

        .nav__item a {
            text-decoration: none;
            color: #333;
            padding: 10px;
            display: block;
            font-weight: bold;
        }

        .nav__item:hover {
            background-color: #eee;
        }

        /* Dropdown Styles */
        .dropdown {
            display: none;
            position: absolute;
            background-color: #fff;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
        }

        .nav__item:hover .dropdown {
            display: block;
        }

        .dropdown a {
            color: #333;
            padding: 12px 16px;
            display: block;
            text-decoration: none;
            font-weight: bold;
        }

        .dropdown a:hover {
            background-color: #eee;
        }
    </style>
</head>

<body>
    <!-- Header Start -->
    <header class="staff-header">
        <div class="staff__wrapper">
            <ul class="nav-menu">

                <%
                    if (session.getAttribute("staff") != null) {
                        // ログインしている時のメニュー
                %>
                        <li class="nav__item">
                            <a href="#">試合情報 &#9662;</a> <!-- ドロップダウンメニューのトリガー -->
                            <div class="dropdown">
                                <a href="">試合情報登録</a>
                                <a href="">試合情報表示</a>
                                <a href="TournamentRegistrationInputDisplay">大会情報登録</a>
                            </div>
                        </li>
                        <li class="nav__item"><a href="TournamentList">高校情報</a></li>
                        <li class="nav__item"><a href="SaleStop">販売停止</a></li>
                        <li class="nav__item"><a href="Refund">払い戻し</a></li>
                        <li class="nav__item"><a href="StaffList">職員情報</a></li>
                        <li class="nav__item"><a href="EntryExit">入退場</a></li>
                        <li class="nav__item"><a href="Logout">ログアウト</a></li>
                <%
                    } else {
                        // ログインしていない時のメニュー
                %>
                        <li class="nav__item">
                            <a href="#">試合情報 &#9662;</a> <!-- ドロップダウンメニューのトリガー -->
                            <div class="dropdown">
                                <a href="">試合情報登録</a>
                                <a href="">試合情報表示</a>
                                <a href="TournamentRegistrationInputDisplay">大会情報登録</a>
                            </div>
                        </li>
                        <li class="nav__item"><a href="TournamentList">高校情報</a></li>
                        <li class="nav__item"><a href="SaleStop">販売停止</a></li>
                        <li class="nav__item"><a href="Refund">払い戻し</a></li>
                        <li class="nav__item"><a href="StaffList">職員情報</a></li>
                        <li class="nav__item"><a href="EntryExit">入退場</a></li>
                        <li class="nav__item"><a href="LoginDisplay">ログイン</a></li>
                <%
                    }
                %>
            </ul>
        </div>
        <hr>
    </header>
    <!-- Header End -->
</body>

</html>
