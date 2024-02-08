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
<%-- 戻るボタン --%>
	<form action="TicketApplication" method="post">
    	<button type="submit" class="return-btn">戻る</button>
	</form>
<%-- 大会名 --%>
    <h1 class="select-title">第${tour.ordinalNum }回　${tour.name }</h1>

     <c:if test="${remain <= count && remain != -1}" >
    	<b class="tiket-soldout"> 購入枚数分用意できません<br>　</b>
    </c:if>

<%-- 指定された座種の画像。クソデカなのでclass="seat-type-img"で指定してます --%>
<c:choose>
	<c:when test="${seat == '0B' }">
		<img src="${pageContext.request.contextPath}/customer/image/toshiba_green_page.jpg" alt="バックネット裏" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0F' }">
		<img src="${pageContext.request.contextPath}/customer/image/infield1.jpg" alt="内野一塁側" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0T' }">
		<img src="${pageContext.request.contextPath}/customer/image/infield3.jpg" alt="内野三塁側" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0R' }">
		<img src="${pageContext.request.contextPath}/customer/image/right.jpg" alt="外野ライト" class="seat-type-img" />
	</c:when>
	<c:when test="${seat == '0L' }">
		<img src="${pageContext.request.contextPath}/customer/image/left.jpg" alt="外野レフト" class="seat-type-img" />
	</c:when>
</c:choose>
    <hr>



	<div id="app" class="select-all-seat">
		<form name="myForm" action="TicketSelectSeat" method="post" >

		<%-- 左のドロップダウン --%>
			段数
            <select id="leftDropdown" v-model="selectedLeft" @change="updateRightDropdown" class="ticket-all-se">
                <option v-for="(num, index) in leftNumbers" :value="num" class="element-option">{{ num }}</option>
            </select>

		<%-- 右のドロップダウン --%>
			 番号
            <select id="rightDropdown" v-model="selectedRight" class="ticket-all-se">
                <option v-for="(num, index) in rightNumbers" :value="num" >{{ num }}</option>
            </select>
			<input type="hidden" id="block" name="block" value="" >

		<%-- ボタン --%>
            <button type="submit"  v-on:click="submitFunc" class="home-btn">次へ</button>
        </form>
    </div>

	<script>
        new Vue({
            el: '#app',
            data: {
                <%-- 1-1,1-2...の数字のみのリスト --%>
            	numbers: [
                	<c:forEach begin="0" end="${fn:length(blocks)-1 }" step="1" var="i">
						"${ fn:substring(blocks[i], 2, fn:length(blocks[i]))}",
					</c:forEach>
				],
				<%-- 重複をなくした左ドロップダウン用のリスト --%>
                leftNumbers:[],
                <%-- 重複をなくした右ドロップダウン用のリスト --%>
                rightNumbers: [],
                <%-- 左で選択した数字 --%>
                selectedLeft: '',
                <%-- 右で選択した数字 --%>
                selectedRight: '',
                <%-- 座種 --%>
                seatType: "${ fn:substring(blocks[0], 0, 2)}",
            },

            methods: {
            	<%-- 左で選択された数字の番号だけのrightNumbers作成 --%>
            	updateRightDropdown: function() {
                    const rightList = this.numbers.filter(num => num.startsWith(this.selectedLeft + "-"));
                    const uniqueRightNumbers = [...new Set(rightList.map(item => item.split('-')[1]))];
		    	    this.rightNumbers = uniqueRightNumbers;
		    	    this.selectedRight = this.rightNumbers[0];
                },
                <%-- leftNumbers作成 --%>
                leftNumbersCreate:function(){
		    		const uniqueLeftNumbers = [...new Set(this.numbers.map(item => item.split('-')[0]))];
		    	    this.leftNumbers = uniqueLeftNumbers;
		    	    this.selectedLeft = this.leftNumbers[0];
		    	},
		    	<%-- ボタン押されたらservletに送る値を生成。"0B1-1"などの形 --%>
		    	submitFunc:function(){
					var blockValue = this.seatType + this.selectedLeft + '-' + this.selectedRight;
					document.getElementById("block").value=blockValue;
					document.myForm.submit();
				},
            },
            mounted() {
                this.leftNumbersCreate();
                this.updateRightDropdown();
            }
        });
    </script>
</body>
</html>