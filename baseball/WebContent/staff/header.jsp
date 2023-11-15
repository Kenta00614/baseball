<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                if (session.getAttribute("staff") != null) {
                    // ログインしている時のメニュー
            %>
            　　<li class="nav__item">
			        <a href="#">試合情報 &#9662;</a> <!-- ドロップダウンメニューのトリガー -->
			        <div class="dropdown">
						<a href="">試合情報登録</a>
						<a href="">試合情報表示</a>
						<a href="TournamentRegistrationInput">大会情報登録</a>
				    </div>
				</li>
			    <li class="nav__item"><a href="HighschoolList">高校情報</a></li>
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
						<a href="">大会情報登録</a>
				    </div>
				</li>
			    <li class="nav__item"><a href="HighschoolList">高校情報</a></li>
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



