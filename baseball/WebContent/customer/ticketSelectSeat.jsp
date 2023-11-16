<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css"  href ="/baseball/css/Customer.css">
	<%-- ↓後で消す↓ --%>
	<style>
        .seat {
            width: 30px;
            height: 40px;
            border: 1px solid #000;
            display: inline-block;
            margin: 2px;
        }
        .selected {
            background-color: #f00;
        }
    </style>
    <%-- ↑後で消す↑ --%>
</head>
<body>
<%-- 戻るボタン --%>
	<form action="TicketSelectAll" method="post">
		<input type="hidden" name="matchId" value="${matchId }">
		<input type="hidden" name="count" value="${count }">
		<input type="hidden" name="seat" value="${seat }">
		<button type="submit">戻る</button>
	</form>
<%-- 席選択 --%>
	<form action="TicketConfirm" method="get">
		<input type="hidden" id="row" value="${row }">
		<input type="hidden" id="col" value="${col }">
		<div id="seats"></div>
		<button id="submit" type="submit">次へ</button>
	</form>





<script>
        var seats = document.getElementById('seats');
        var row = document.getElementById('row');
        var col = document.getElementById('col');
        var seatArray = [];
        for (var i = 0; i < col; i++) {
            for (var j = 0; j < row; j++) {
                var seat = document.createElement('div');
                seat.className = 'seat';
                seat.id = 'seat-' + i + '-' + j;
                seat.addEventListener('click', function() {
                    this.classList.toggle('selected');
                });
                seats.appendChild(seat);
                seatArray.push(seat);
            }
            seats.appendChild(document.createElement('br'));
        }

        document.getElementById('submit').addEventListener('click', function() {
            var selectedSeats = seatArray.filter(function(seat) {
                return seat.classList.contains('selected');
            }).map(function(seat) {
                return seat.id;
            });
            alert('Selected seats: ' + selectedSeats.join(', '));
        });
    </script>
</body>
</html>
