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

<!-- Header Start -->
    <header class="cust-header">
    	<div class="cust_wrapper">
			<div class="logo">
			    <a href="${pageContext.request.contextPath}/Main">
			        <img src="${pageContext.request.contextPath}/customer/image/logo.png" alt="ロゴ" class="logo-image"/>
			        <!-- コンテキストパスを利用して、ロゴ画像をクリックするとMain.javaにマッピングされたURLに遷移 -->
			    </a>
			</div>
			<div class="menu">
				<ul class="nav-menu">
				    <li><a href="TicketPurchase">チケット購入</a></li>
				    <li><a href="TicketDisplay">チケット表示</a></li>
				    <li>
				        <a href="#">マイページ &#9662;</a> <!-- ドロップダウンメニューのトリガー -->
				        <div class="dropdown">
				            <a href="MemberInformation">会員情報</a>
				            <a href="PurchaseHistory">購入履歴表示</a>
				        </div>
				    </li>
				    <li><a href="ProvisionalSignupDisplay">新規会員登録</a></li>
				</ul>
			</div>
		</div>
	</header>
<!-- Header End -->

</body>
</html>
