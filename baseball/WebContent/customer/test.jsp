<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <style>
        /* ナビゲーションメニューのスタイル */
        .nav-menu {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .nav-menu > li {
            display: inline-block;
            margin-right: 10px;
        }

        /* ドロップダウンメニューのスタイル */
        .nav-menu li .dropdown {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .nav-menu li .dropdown a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .nav-menu li .dropdown a:hover {
            background-color: #f1f1f1;
        }

        /* マウスオーバー時にドロップダウンメニューを表示 */
        .nav-menu li:hover .dropdown {
            display: block;
        }
    </style>

</head>
<body>

<ul class="nav-menu">
    <li>
    <a href="${pageContext.request.contextPath}/main">
        <img src="${pageContext.request.contextPath}/customer/images/logo.png" alt="ロゴ" />
        <!-- コンテキストパスを利用して、ロゴ画像をクリックするとMain.javaにマッピングされたURLに遷移 -->
    </a>
</li>

    <li><a href="TicketPurchase">チケット購入</a></li>
    <li><a href="TicketDisplay">チケット表示</a></li>
    <li>
        <a href="#">マイページ &#9662;</a> <!-- ドロップダウンメニューのトリガー -->
        <div class="dropdown">
            <a href="MemberInformation">会員情報</a>
            <a href="TicketHistory">購入履歴表示</a>
        </div>
    </li>
    <li><a href="Logout">ログアウト</a></li>
</ul>

</body>
</html>
