<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <link rel="stylesheet" type="text/css" href="/baseball/css/Staff.css">
    <!-- Font Awesome CSS を追加 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- 追加したスタイル -->
    <style>
    .staff-header {
       background-color: black;
       color: white;
       font-size: 20px;
       padding: 1px 0;
    }

    .nav-menu a {
        color: white;
        display: flex;
        align-items: center;
    }

    .nav-menu i {
        margin-right: 5px;
    }

    .dropdown a {
        padding: 8px 10px;
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
                        <a href="#"><i class="fas fa-baseball-ball"></i> 試合情報 &#9662;</a>
                        <div class="dropdown">
                            <a href="#"><i class="fas fa-calendar-plus"></i> 試合情報登録</a>
                            <a href="#"><i class="fas fa-calendar-alt"></i> 試合情報表示</a>
                            <a href="TournamentRegistrationInputDisplay"><i class="fas fa-trophy"></i> 大会情報登録</a>
                        </div>
                    </li>
                    <li class="nav__item"><a href="TournamentList"><i class="fas fa-school"></i> 高校情報</a></li>
                    <li class="nav__item"><a href="SaleStop"><i class="fas fa-stop"></i> 販売停止</a></li>
                    <li class="nav__item"><a href="Refund"><i class="fas fa-undo"></i> 払い戻し</a></li>
                    <li class="nav__item"><a href="StaffList"><i class="fas fa-users"></i> 職員情報</a></li>
                    <li class="nav__item"><a href="EntryExit"><i class="fas fa-sign-in-alt"></i> 入退場</a></li>
                    <li class="nav__item"><a href="Logout"><i class="fas fa-sign-out-alt"></i> ログアウト</a></li>
                <% } else { %>
                    <!-- ログインしていない時のメニュー -->
                    <li class="nav__item">
                        <a href="#"><i class="fas fa-baseball-ball"></i> 試合情報 &#9662;</a>
                        <div class="dropdown">
                            <a href="#"><i class="fas fa-calendar-plus"></i> 試合情報登録</a>
                            <a href="#"><i class="fas fa-calendar-alt"></i> 試合情報表示</a>
                            <a href="TournamentRegistrationInputDisplay"><i class="fas fa-trophy"></i> 大会情報登録</a>
                        </div>
                    </li>
                    <li class="nav__item"><a href="TournamentList"><i class="fas fa-school"></i> 高校情報</a></li>
                    <li class="nav__item"><a href="SaleStop"><i class="fas fa-stop"></i> 販売停止</a></li>
                    <li class="nav__item"><a href="Refund"><i class="fas fa-undo"></i> 払い戻し</a></li>
                    <li class="nav__item"><a href="StaffList"><i class="fas fa-users"></i> 職員情報</a></li>
                    <li class="nav__item"><a href="EntryExit"><i class="fas fa-sign-in-alt"></i> 入退場</a></li>
                    <li class="nav__item"><a href="LoginDisplay"><i class="fas fa-sign-in-alt"></i> ログイン</a></li>
                <% } %>
            </ul>
        </div>
        <hr>
    </header>
    <!-- Header End -->
</body>
</html>
