<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Staff" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    /* 既存のスタイル */

	    .staff-icon-container {
	        display: flex;
	        align-items: center;
	    }

	    .staff-icon-container i {
	        margin-right: 5px; /* Adjust the margin between the icon and text */
	    }

	    .staff-text {
	        font-size: 20px; /* Adjust the font size of the text */
	    }


	    .nav-menu {
	        float: right;
	        margin-top: 10px; /* 適切なマージンを設定 */
	    }

	    .staff-header {
	        background-color: fff;
	        color: #555555; /* テキストの色を灰色に変更 */
	        font-size: 20px; /* フォントサイズを大きくする（適宜調整） */
	        font-weight: bold; /* フォントの太さを太くする */
	        padding: 0.1px 0;
	    }
	    .staff-header a {
	        color: #555555; /* リンクの色も灰色に変更 */
	    }

	    .nav-menu a {
	        color: #555555; /* テキストの色を灰色に変更 */
	        text-decoration: none;
	    }

	    /* フォントアイコンの色を変更 */
	    .nav-menu a i {
	        color: #555555;
	    }

	    .nav-menu a:hover {
	        text-decoration: underline;
	    }

	    .dropdown {
	        display: none;
	        position: absolute;
	        background-color: #333; /* ドロップダウンの背景色を黒に変更 */
	        min-width: 160px;
	        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
	        z-index: 1;
	    }

	    .dropdown a {
	        color: #fffff;
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
	        background: fff;
	        margin: 15px 0;
	    }
	</style>

</head>
<body>
   <!-- Header Start -->
   <header class="staff-header">
       <div class="staff__wrapper">
        <div class="staff-icon-container">
            <i class="fa-solid fa-user-tie"></i>
            <div class="staff-text">Staff</div>
        </div>
        <ul class="nav-menu">

           <%-- ポジションが2の時は管理者用 --%>
           <c:if test="${staff.position == 2}">
               <li class="nav__item">
                   <a href="#"><i class="fas fa-baseball-ball"></i> 試合情報 &#9662;</a>
                   <!-- ドロップダウンメニューのトリガー -->
                   <div class="dropdown">
                       <a href="MatchRegistrationInput"><i class="fas fa-plus"></i> 試合情報登録</a>
                       <a href="MatchDisplay"><i class="fas fa-eye"></i> 試合情報表示</a>
                       <a href="TournamentRegistrationInputDisplay"><i class="fas fa-trophy"></i> 大会情報登録</a>
                       <a href="TournamentUpdateInput"> 大会情報変更</a>
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
           </c:if>
           <%-- ポジションが1の時はスタッフ用 --%>
           <c:if test="${staff.position == 1}">
               <li class="nav__item"><a href="MatchDisplay"><i class="fas fa-eye"></i> 試合情報表示</a></li>
               <li class="nav__item"><a href="Refund"><i class="fas fa-money-bill-wave"></i> 払い戻し</a></li>
               <li class="nav__item"><a href="EntryExit"><i class="fas fa-sign-in-alt"></i> 入退場</a></li>
               <li class="nav__item"><a href="Logout"><i class="fas fa-sign-out-alt"></i> ログアウト</a></li>
           </c:if>
           </ul>
       </div>
       <hr class="hr-style"> <!-- ここに移動させました -->
   </header>
</body>
</html>
