<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>

    <%-- css --%>
	<style>
           li{
               list-style:none;
           }
		.selected{
               background-color:red;
               display: inline;
           }
   		.notSelect{
               display: inline;
           }
           .notSele{
           	background-color:black;
               display: inline;
           }
		.v-enter-active,.v-leave-active{
			transition: 0.5s;
		}
		.v-enter,.v-leave-to{
			opacity: 0;
			transform: translateX(20px);
		}
	</style>
    <%-- ↑css↑ --%>

</head>
<body>
<%-- 大会名 --%>
    <h3>第${tour.ordinalNum }回　${tour.name }</h3>

<%-- 席選択 --%>
	<div id="app">
		<transition-group>
			<li v-for="(ticket,index) in ticketsList" v-bind:key="ticket.ticketsId">
				<label>
				<%--
					<input type="checkbox" value="index" v-model="ticket.check" v-on:click="changeClass(index)">
				 --%>
				 	<img alt="座席" :src="'${pageContext.request.contextPath}/customer/image/' + ticket.imgsrc + '.jpg'" value="index" v-model="ticket.check" v-on:click="changeClass(index)">
					<p v-bind:class="ticket.class">&nbsp;&nbsp;</p>
				</label>
			</li>
		</transition-group>
		<hr>

		<%-- 選択したら出てくる --%>
           <transition-group>
			<li v-for="(ticket,index) in selectedTickets" v-bind:key="ticket.ticketsId">
				<p>{{ticket.seatType}}　{{ticket.step}}段　{{ticket.number}}番</p>
			</li>
		</transition-group>

		<form name="myForm" action="/TicketConfirm" method="GET">
			<button type="button" v-on:click="submitFunc">次へ</button><!-- 送信ボタン -->
			<input type="hidden" id="tickets" name="tickets" value="" ><!-- 隠しパラメータ ticketのIDリスト-->
		</form>
	</div>

	<%-- 戻るボタン --%>
	<form action="TicketSelectAll" method="post">
		<input type="hidden" name="matchId" value="${matchId }">
		<input type="hidden" name="count" value="${count }">
		<input type="hidden" name="seat" value="${seat }">
		<button type="submit">戻る</button>
	</form>

	<script>
		new Vue({
			el: '#app',
			data: {
				<%--ticketsList: [{ticketsId:"0R01001r00202403181000",seatId:"0R01001",status:3,seatType:"ライト指定席",step:1,number:1,class:"notSelect",check:false},
	                              {ticketsId:"0R01002r00202403181000",seatId:"0R01002",status:3,seatType:"ライト指定席",step:1,number:2,class:"notSelect",check:false},
	                              {ticketsId:"0R01003r00202403181000",seatId:"0R01003",status:3,seatType:"ライト指定席",step:1,number:3,class:"notSelect",check:false},],
			    --%>
				ticketsList: [
					<c:forEach var="ticket" items="${tickets }">
						{ticketsId:"${ticket.ticket.ticketsId}", seatId:"${ticket.ticket.seatId}",status:${ticket.ticket.status},seatType:"${ticket.seat.type}",step:"${ticket.seat.step}",number:${ticket.seat.number},imgsrc:"seat_1",class:"notSelect",check:false},
					</c:forEach>
						],
			    selectedTickets:[],
			},
			methods:{
				remove:function(index){
	                      var id = this.ticketsList[index].ticketsId;
					for(var i=0;i<this.selectedTickets.length;i++){
						if(this.selectedTickets[i].ticketsId == id){
							this.selectedTickets.splice(i,1);
							i--;
						}
					}
				},
				add:function(index){
					this.selectedTickets.push(this.ticketsList[index]);
				},
				changeClass:function(index){
					if(this.ticketsList[index].status==3){
						if(!this.ticketsList[index].check){
							this.ticketsList[index].class="selected";
							this.ticketsList[index].imgsrc="seat_3";
							this.ticketsList[index].check=true;
		                          this.add(index);
						}else{
							this.ticketsList[index].class="notSelect";
							this.ticketsList[index].imgsrc="seat_1";
							this.ticketsList[index].check=false;
		                          this.remove(index);
						}
					}
				},
				submitFunc:function(){
					var str = "";
					this.selectedTickets.forEach(obj => {
						str += obj.ticketsId + ",";
					});
					document.getElementById("tickets").value=str;
					document.myForm.submit();
				},
			}
		})
	</script>
</body>
</html>
