<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<body>
    <div class="header">
        <form action="MemberInformation" method="get">
    　　　　<button type="submit">戻る</button>
　　　　</form>
    </div>
    <form action="MemberConfirm" method="post">
        <h2>会員情報変更</h2>
        <label for="name">氏名</label>
        <input type="text" id="name" name="name" placeholder="OOOOO">

        <label for="email">メールアドレス</label>
        <input type="email" id="email" name="email" placeholder="xxxxxx@stu.o-hara.ac.jp">

        <label for="phone">電話番号</label>
        <input type="tel" id="phone" name="phone" placeholder="0332378711">

        <label for="password">パスワード</label>
        <input type="password" id="password" name="password" placeholder="">

        <label for="confirm_password">パスワード (確認用)</label>
        <input type="password" id="confirm_password" name="confirm_password" placeholder="">

        <input type="submit" value="確定">
    </form>
</body>
</html>
