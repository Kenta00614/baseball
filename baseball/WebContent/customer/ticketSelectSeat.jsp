<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>


 <link rel="stylesheet" type="text/css" href="/baseball/css/Ticket.css">
 <style>


</style>
</head>
<body>
	<%-- 戻るボタン --%>
	<form action="TicketSelectAll" method="post"><br>
		<input type="hidden" name="count" value="${count }">
		<input type="hidden" name="seat" value="${seat }">
		<button type="submit" class="return-btn">戻る</button>
	</form>
<%-- 大会名 --%>
    <h1>第${tour.ordinalNum }回　${tour.name }</h1>

<%-- 席選択 --%>
	<div id="app">
		<table>
		<%-- 座席の画像--%>
		<transition-group>
		<tr v-for="(seats,index1) in seatsList" v-bind:key="seats[0].seatId">
			<td v-for="(seat,index2) in seats" v-bind:key="seat.seatId" >
				 	<img v-if="(seat.seatId.length > 5)" alt="座席" :src="'${pageContext.request.contextPath}/customer/image/'+ seat.imgsrc + '.jpg'" value="index" v-bind:value="seat.check" v-on:click="changeClass(index1,index2)" >
			</td>
		</tr>
		</transition-group>
		</table>
		<p class="ground">グラウンド側</p>
		<%-- 選択したら出てくる情報 --%>
          <transition-group>
			    <li v-for="(ticket, index) in selectedTickets" v-bind:key="ticket.ticketsId" class="selected-ticket">
			        <p>
			             {{ ticket.typeStr }} {{ ticket.step }}段 {{ ticket.number }}番
			             {{ ticket.gate }}番ゲート {{ ticket.passage }}通路
			             <button id="childBtn" value="index" v-bind:value="ticket.check" v-on:click="changeChild(index)" v-bind:class="{ 'age': ticket.checkStr === '大人', 'kids': ticket.checkStr === '子供' }">
			                {{ ticket.checkStr }}<br>
			            </button>
			        </p>
			    </li>
		 </transition-group>



		<form name="myForm" action="TicketConfirm" method="post" >
			<input type="hidden" id="tickets" name="tickets" value="" ><!-- 隠しパラメータ ticketのIDリスト-->
			<input type="hidden" id="child" name="child" value="">
			<button type="button" v-on:click="submitFunc" :disabled="${count } != selectedTickets.length"  class="next-btn" >次へ</button><!-- 送信ボタン -->
		</form>
	</div>




	<script>
		new Vue({
			el: '#app',
			data: {
				<%-- 購入できるチケットの情報 --%>
				ticketsList: [
					<c:forEach var="ticket" items="${tickets}">
						{ticketsId:"${ticket.ticket.ticketsId}", seatId:"${ticket.ticket.seatId}",status:${ticket.ticket.status},seatType:"${ticket.seat.type}",
							step:"${ticket.seat.step}",number:${ticket.seat.number},typeStr:"${ticket.seat.typeStr}",gate:"${ticket.seat.gate}",passage:"${ticket.seat.passage}",check:false,checkStr:"大人",
						},
					</c:forEach>
						],
				<%-- 座席ID --%>
				seatsList:[
					<c:forEach var="seats" items="${seatsList}">[
						<c:forEach var="seat" items="${seats}">
							{seatId:"${seat.seatId}",seatStep:"${seat.step}",seatNumber:"${seat.number}",imgsrc:"seat_0",check:false},
						</c:forEach>
					],
					</c:forEach>],
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
				        	for(var k = 0;k<this.seatsList[j].length;k++){
				        		if (this.seatsList[j][k].seatId === this.ticketsList[i].seatId) {
					                this.seatsList[j][k].imgsrc = "seat_1";
					                break;
					            }
				        	}
				        }
				    }
				},
				<%-- 座席の解除選択したときにselectTicketsから値を削除 --%>
				remove:function(index1,index2){
					var selectedSeatId = this.seatsList[index1][index2].seatId;
				    for (var i = 0; i < this.selectedTickets.length; i++) {
				    	if (selectedSeatId === this.selectedTickets[i].seatId) {
							this.selectedTickets.splice(i, 1);
							break;
						}
					}
				},
				<%-- 座席選択されたときにticketsListに値を追加 --%>
				add:function(index1,index2){
					var selectedSeatId = this.seatsList[index1][index2].seatId;
					for (var i = 0; i < this.ticketsList.length; i++) {
						if (selectedSeatId === this.ticketsList[i].seatId) {
				            if (this.ticketsList[i].status === 2) {
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

		        	}else{
		        		this.selectedTickets[index].check=false;
		        		this.selectedTickets[index].checkStr="大人";
		        	}
				},

				<%-- 選択・解除されたときの画像・checkフラグの変更、selectedTicketsに追加・削除 --%>
				changeClass:function(index1,index2){
					<%-- 選択された座席idのチケット情報statusを探す --%>
					var selectedSeatId = this.seatsList[index1][index2].seatId;
					var selectStatus = 0;
				    for (var i = 0; i < this.ticketsList.length; i++) {
				        if (selectedSeatId === this.ticketsList[i].seatId) {
				            selectStatus=this.ticketsList[i].status;
				            break;
				        }
				    }
				    <%-- 販売中の時のみ画像変更 --%>
				    if(this.seatsList[index1][index2].imgsrc !== "seat_0"){
						if(!this.seatsList[index1][index2].check){
							this.seatsList[index1][index2].imgsrc="seat_3";
							this.seatsList[index1][index2].check=true;
		                    this.add(index1,index2);
						}else{
							this.seatsList[index1][index2].imgsrc="seat_1";
							this.seatsList[index1][index2].check=false;
		                    this.remove(index1,index2);
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