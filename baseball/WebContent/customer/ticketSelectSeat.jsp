<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>

<%-- これ読み込ませると縦に並んじゃうのでコメントにしてます
<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
 --%>
    <%-- ↓css後で修正してください↓ --%>
	<style>
		.v-enter-active,.v-leave-active{
			transition: 0.5s;
		}
		.v-enter,.v-leave-to{
			opacity: 0;
			transform: translateX(20px);
		}
		li{
            list-style:none;
        }
        <%-- 座席横に並べるクラス --%>
		.seat-img {
		  float: left;
		}
		<%-- floatを解除するクラス --%>
		.clear {
		  clear: both;
		}
	</style>
    <%-- ↑css後で修正してください↑ --%>

</head>
<body>
<%-- 大会名 --%>
    <h3>第${tour.ordinalNum }回　${tour.name }</h3>

<%-- 席選択 --%>
	<div id="app">
		<%-- 座席の画像--%>
		<transition-group>
		<%-- stepが変わったらclear(floatの解除)のclassを適用 --%>
		<div  v-for="(seat,index) in seatsList" v-bind:key="seat.seatId"  :class="{ 'clear': index>0 && seat.seatStep !== seatsList[index-1].seatStep }">
			<%-- seatをfloat:leftしてる --%>
			<li class="seat-img">
				<label>
				 	<img alt="座席" :src="'${pageContext.request.contextPath}/customer/image/' + seat.imgsrc + '.jpg'" value="index" v-bind:value="seat.check" v-on:click="changeClass(index)">
				</label>
			</li>
		</div>
		</transition-group>

		<%-- float解除 --%>
		<div class="clear"></div>
		<%-- 選択したら出てくる情報 --%>
          <transition-group>
			<li v-for="(ticket,index) in selectedTickets" v-bind:key="ticket.ticketsId">
				<p>{{ticket.typeStr}}　{{ticket.step}}段　{{ticket.number}}番　{{ticket.gate}}番ゲート　{{ticket.passage}}通路　<button id="childBtn" value="index" v-bind:value="ticket.check" v-on:click="changeChild(index)">{{ticket.checkStr}}</button></p>
			</li>
		</transition-group>

		<form name="myForm" action="TicketConfirm" method="post">
			<input type="hidden" id="tickets" name="tickets" value="" ><!-- 隠しパラメータ ticketのIDリスト-->
			<input type="hidden" id="child" name="child" value="">
			<button type="button" v-on:click="submitFunc" :disabled="${count } != selectedTickets.length">次へ</button><!-- 送信ボタン -->
		</form>
	</div>

	<%-- 戻るボタン --%>
	<form action="TicketSelectAll" method="post">
		<input type="hidden" name="count" value="${count }">
		<input type="hidden" name="seat" value="${seat }">
		<button type="submit">戻る</button>
	</form>


	<script>
		new Vue({
			el: '#app',
			data: {
				<%-- 購入できるチケットの情報 --%>
				ticketsList: [
					<c:forEach var="ticket" items="${tickets }">
						{ticketsId:"${ticket.ticket.ticketsId}", seatId:"${ticket.ticket.seatId}",status:${ticket.ticket.status},seatType:"${ticket.seat.type}",
							step:"${ticket.seat.step}",number:${ticket.seat.number},typeStr:"${ticket.seat.typeStr}",gate:"${ticket.seat.gate}",passage:"${ticket.seat.passage}",check:false,checkStr:"大人",
						},
					</c:forEach>
						],
				<%-- 座席ID --%>
				seatsList: [
					<c:forEach var="seat" items="${seats}">
						{seatId:"${seat.seatId}",seatStep:"${seat.step}",imgsrc:"seat_0",check:false},
					</c:forEach>
				],
				<%-- 選択されたチケット --%>
			    selectedTickets:[],
			},
			<%-- 起動したときに動く処理 --%>
			created: function() {
		        this.initializeSeats();
		    },
			methods:{
				<%-- 購入可能な座席はseat_1.jgp(白)にする --%>
				initializeSeats: function() {
				    for (var i = 0; i < this.ticketsList.length; i++) {
				        for (var j = 0; j < this.seatsList.length; j++) {
				            if (this.seatsList[j].seatId === this.ticketsList[i].seatId) {
				                this.seatsList[j].imgsrc = "seat_1";
				                break;
				            }
				        }
				    }
				},
				<%-- 座席の解除選択したときにselectTicketsから値を削除 --%>
				remove:function(index){
					var selectedSeatId = this.seatsList[index].seatId;
				    for (var i = 0; i < this.selectedTickets.length; i++) {
				        if (selectedSeatId === this.selectedTickets[i].seatId) {
				            this.selectedTickets.splice(i, 1);
				            break;
				        }
				    }
				},
				<%-- 座席選択されたときにticketsListに値を追加 --%>
				add:function(index){
					var selectedSeatId = this.seatsList[index].seatId;
					for (var i = 0; i < this.ticketsList.length; i++) {
				        if (selectedSeatId === this.ticketsList[i].seatId) {
				            if (this.ticketsList[i].status === 3) {
				                this.selectedTickets.push(this.ticketsList[i]);
				            }
				            break;
				        }
				    }
				},
				<%-- 大人と子供のチケットボタン変更 --%>
				changeChild: function(index) {
				    if(!this.selectedTickets[index].check){
		        		this.selectedTickets[index].check=true;
		        		this.selectedTickets[index].checkStr="子供";
		        		console.log(selectedTickets[index]);
		        	}else{
		        		this.selectedTickets[index].check=false;
		        		this.selectedTickets[index].checkStr="大人";
		        	}
				},

				<%-- 選択・解除されたときの画像・checkフラグの変更、selectedTicketsに追加・削除 --%>
				changeClass:function(index){
					<%-- 選択された座席idのチケット情報statusを探す --%>
					var selectedSeatId = this.seatsList[index].seatId;
					var selectStatus = 0;
				    for (var i = 0; i < this.ticketsList.length; i++) {
				        if (selectedSeatId === this.ticketsList[i].seatId) {
				            selectStatus=this.ticketsList[i].status;
				            break;
				        }
				    }
				    <%-- status==3の時は実行しない --%>
				    if(selectStatus == 3){
						if(!this.seatsList[index].check){
							this.seatsList[index].imgsrc="seat_3";
							this.seatsList[index].check=true;
		                    this.add(index);
						}else{
							this.seatsList[index].imgsrc="seat_1";
							this.seatsList[index].check=false;
		                    this.remove(index);
						}
				    }
				},
				<%-- 次へボタンを押されたら送る値をticketsIdのみにする --%>
				submitFunc:function(){
					var chilStr="";
					var str = "";
					this.selectedTickets.forEach(obj => {
						str += obj.ticketsId + ",";
						chilStr += obj.checkStr + ",";
					});
					document.getElementById("tickets").value=str;
					document.getElementById("child").value=chilStr;
					document.myForm.submit();
				},
			}
		})
	</script>
</body>
</html>
