<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <link rel="stylesheet" type="text/css" href="/baseball/css/Staff.css">

    <!-- 追加したスタイル -->
    <style>
        .staff-header {
            background-color: black; /* ヘッダーの背景色を黒に設定 */
            color: white; /* ヘッダーテキストの色を白に設定 */
            font-size: 20px; /* ヘッダーテキストのフォントサイズを20pxに設定 */
        }

        .nav-menu a {
            color: white; /* ナビゲーションメニューテキストの色を白に設定 */
        }
    </style>
</head>
<body>
    <!-- Header Start -->
    <header class="staff-header">
        <div class="staff__wrapper">
            <ul class="nav-menu">
                <% if (session.getAttribute("staff") != null) { %>
                    <!-- ログインしている時のメニュー -->
                    <li class="nav__item">
                        <a href="#">試合情報 &#9662;</a>
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
                <% } else { %>
                    <!-- ログインしていない時のメニュー -->
                    <li class="nav__item">
                        <a href="#">試合情報 &#9662;</a>
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
                <% } %>
            </ul>
        </div>
        <hr>
    </header>
    <!-- Header End -->
</body>
</html>
