<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Staff" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <link rel="stylesheet" type="text/css" href="/baseball/css/Staff.css">

    <!-- FontAwesomeのCDNを追加 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <!-- 追加したスタイル -->
    <style>
	    .staff-header {
	        background-color: f3f2e7; /* ヘッダーの背景色を変更 */
	        color: brown; /* 文字色を変更 */
	        font-size: 20px;
	        padding: 1px 0;
	    }


        .nav-menu a {
            color: black;
            text-decoration: none; /* Ensure no underline on links */
        }

        .nav-menu a:hover {
            text-decoration: underline; /* Underline on hover */
        }

        .dropdown {
            display: none;
            position: absolute;
            background-color: black;
            min-width: 160px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown a {
            color: white;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {
            background-color: #555;
        }

        .nav__item:hover .dropdown {
            display: block;
        }

        .hr-style {
            border: 0;
            height: 1px;
            background: white;
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <!-- Header Start -->
    <header class="staff-header">
        <div class="staff__wrapper">
            <ul class="nav-menu">

            <%
                //管理職がログイン
                Staff staff = (Staff) session.getAttribute("staff");
                int position = Integer.parseInt(staff.getPosition());
                if (position == 2) {
            %>
                <li class="nav__item">
                    <a href="#"><i class="fas fa-futbol"></i> 試合情報 &#9662;</a>
                    <!-- ドロップダウンメニューのトリガー -->
                    <div class="dropdown">
                        <a href="MatchRegistrationInput"><i class="fas fa-plus"></i> 試合情報登録</a>
                        <a href="MatchDisplay"><i class="fas fa-eye"></i> 試合情報表示</a>
                        <a href="TournamentRegistrationInputDisplay"><i class="fas fa-trophy"></i> 大会情報登録</a>
                    </div>
                </li>
                <li class="nav__item"><a href="TournamentList"><i class="fas fa-school"></i> 高校情報</a></li>
                <li class="nav__item"><a href="SaleStop"><i class="fas fa-stop"></i> 販売停止</a></li>
                <li class="nav__item"><a href="Refund"><i class="fas fa-money-bill-wave"></i> 払い戻し</a></li>
                <li class="nav__item">
                    <a href="#"><i class="fas fa-user"></i> 職員情報 &#9662;</a>
                    <div class="dropdown">
                        <a href="StaffRegister"><i class="fas fa-user-plus"></i> 新規職員登録</a>
                        <a href="StaffDelete"><i class="fas fa-user-minus"></i> 職員情報削除</a>
                        <a href="StaffPassDisplay"><i class="fas fa-key"></i> パスワード再設定</a>
                    </div>
                </li>
                <li class="nav__item"><a href="EntryExit"><i class="fas fa-sign-in-alt"></i> 入退場</a></li>
                <li class="nav__item"><a href="Logout"><i class="fas fa-sign-out-alt"></i> ログアウト</a></li>
            <%
                } else {
                    // スタッフがログイン
            %>
                <li class="nav__item"><a href="MatchDisplay"><i class="fas fa-eye"></i> 試合情報表示</a></li>
                <li class="nav__item"><a href="Refund"><i class="fas fa-money-bill-wave"></i> 払い戻し</a></li>
                <li class="nav__item"><a href="EntryExit"><i class="fas fa-sign-in-alt"></i> 入退場</a></li>
                <li class="nav__item"><a href="Logout"><i class="fas fa-sign-out-alt"></i> ログアウト</a></li>
            <%
                }
            %>
            </ul>
        </div>
        <hr class="hr-style"> <!-- ここに移動させました -->
    </header>
</body>
</html>
