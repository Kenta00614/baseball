<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Staff" %>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>

    <link rel="stylesheet" type="text/css"  href ="/baseball/css/Staff.css">
</head>

<body>
<!-- Header Start -->
    <header class="staff-header">
        <div class="staff__wrapper">
            <ul class="nav-menu">

            <%
                //管理職がログイン
                Staff staff=(Staff)session.getAttribute("staff");
                int position = Integer.parseInt(staff.getPosition());
                if (position == 2) {
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
                <li class="nav__item">
                    <a href="#">職員情報 &#9662;</a>
                    <div class="dropdown">
                        <a href="StaffRegister">新規職員登録</a>
                        <a href="StaffDelete">職員情報削除</a>
                        <a href="StaffPassDisplay">パスワード再設定</a>
                    </div>
                </li>
                <li class="nav__item"><a href="EntryExit">入退場</a></li>
                <li class="nav__item"><a href="Logout">ログアウト</a></li>
            <%
                } else {
                    // スタッフがログイン
            %>
<<<<<<< HEAD
				<li class="nav__item"><a href="MatchInformation">試合情報表示</a></li>
			    <li class="nav__item"><a href="Refund">払い戻し</a></li>
				<li class="nav__item"><a href="EntryExit">入退場</a></li>
				<li class="nav__item"><a href="Logout">ログアウト</a></li>
=======
                <li class="nav__item"><a href="MatchInformationDisplay">試合情報表示</a></li>
                <li class="nav__item"><a href="Refund">払い戻し</a></li>
                <li class="nav__item"><a href="EntryExit">入退場</a></li>
                <li class="nav__item"><a href="Logout">ログアウト</a></li>
>>>>>>> branch 'master' of https://github.com/Kenta00614/baseball.git

            <%
                }
            %>
            </ul>


        </div>
        <hr>
    </header>
<!-- Header End -->

</body>