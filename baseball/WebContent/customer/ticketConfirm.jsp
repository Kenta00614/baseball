<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
</head>
<body>
<%-- 大会名 --%>
	<h1>第${tour.ordinalNum }回　${tour.name }</h1>
	<hr>
	<div>
	<table>
		<tr><th colspan="2" style="background-color:#6BEF9E;color:cadetblue;">申込内容</th></tr>
		<%-- 日付と曜日 --%>
		<tr><th>開催日</th><td>${match.eventDate }(${match.eventDayOfWeek })</td></tr>
		<%-- 座種 --%>
		<tr><th>座種</th><td>${selTicketsData[0].seat.typeStr }</td></tr>
		<%-- ↓選択された座席分の情報↓ --%>
		<c:forEach begin="0" end="${fn:length(selTicketsData)-1 }" step="1" var="i">
			<tr><th>座席内容</th><td>${selTicketsData[i].seat.step }段　${selTicketsData[i].seat.number }番　${selTicketsData[i].seat.gate }番ゲート　${selTicketsData[i].seat.passage }通路　${selChils[i] }　値段:&yen;${price[i] }</td></tr>
		</c:forEach>
		<%-- ↑選択された座席分の情報↑ --%>
		<tr><th>支払い方法/受け取り方法</th><td>QRコード/Paypal</td></tr>

		<tr id="app">
			<c:if test="${point > 0}">
				<th>ポイント</th><td>${point }(&yen;${point }相当)利用可能</td>
				<input type="number" v-model="inputValue">
				<button type="button" v-on:click="addPoint" :disabled="!isButtonEnabled">適用</button>
			</c:if>
			<th>ご請求</th><td>&yen; {{disPrice}}<c:if test="${point > 0}">(内ポイント利用&yen;{{usePoint}})</c:if></td>
		</tr>
	</table>
	</div>

<%-- 戻るボタン --%>
    <form action="TicketApplication" method="post">
		<button type="submit">席種・枚数選択に戻る</button>
	</form>

   <%-- 購入ボタン --%>
    <form action="TicketComplete" method="post">
    	<input type="hidden" value="" id="usePointNum" name="usePointNum">
		<button type="submit">購入</button>
	</form>

    <script>
		new Vue({
			el: '#app',
			data: {
				<%-- 合計金額 --%>
			    allPrice:0,
			    <%-- ポイント使用金額 --%>
				disPrice:0,
			    <%-- ボタン活性化に必要 --%>
				inputValue:0,
				<%-- 使用ポイント --%>
				usePoint:0,
			},
			<%-- 起動したときに動く処理 --%>
			created: function() {
		        this.addPrice();
		    },
		    computed: {
		    	<%-- 保有ポイントより多いときのみボタン活性化 --%>
		        isButtonEnabled() {
		          return this.inputValue >= 0 && this.inputValue <= ${point };
		        }
		      },
			methods:{
				<%-- 合計金額の計算 --%>
				addPrice: function() {
					<c:forEach items="${price }" var="p">
						this.allPrice += ${p };
					</c:forEach>
					this.disPrice = this.allPrice;
					document.getElementById("usePointNum").value=this.usePoint;
				},
				<%-- 割引後の金額計算 --%>
				addPoint: function(){
					this.usePoint = this.inputValue;
					this.disPrice = this.allPrice - this.inputValue;
					document.getElementById("usePointNum").value=this.usePoint;
				},
			}
		})
	</script>
</body>
</html>
