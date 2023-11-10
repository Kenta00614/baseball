<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>

    <link rel="stylesheet" type="text/css"  href ="/baseball/css/CustHeader.css">
</head>
<body>
	<nav class="nav-menu">
		<a href="${pageContext.request.contextPath}/Main">
			<img src="${pageContext.request.contextPath}/customer/image/logo.png" alt="ロゴ" class="logo-image"/>
			<!-- コンテキストパスを利用して、ロゴ画像をクリックするとMain.javaにマッピングされたURLに遷移 -->
		</a>
		   
	
			<ul>
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
		</nav>

</body>
</html>
