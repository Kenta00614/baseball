<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<style>
    body {
      font-family: 'Ropa Sans', sans-serif;
      color: #333;
      margin: 0 auto;
      position: relative;
    }
    h1 {
      margin: 10px 0;
      font-size: 40px;
    }
    #emergency {
    	color : red;
    	font-size : 40px;
    }
    #allGreen {
    	color : rightGreen;
    	font-size : 40px;
    }
    #canvas {
      width:100%;
      hight:50%;
    }
    #output {
      margin-top: 20px;
      background: #eee;
      padding: 10px;
      padding-bottom: 0;
    }
    #output div {
      padding-bottom: 10px;
      word-wrap: break-word;
    }
    #noQRFound {
      text-align: center;
    }
  </style>
</head>
<body>
  <h1>入場カメラ</h1>
  <div class="header">
   <form action="EntryExit" method="get"><button type="submit">戻る</button></form>
  </div>
  <form id="form" action="EntryConduct" >
   <select name="seatType">
   	<c:forEach var="obj" items="${seatTypeList}">
   	 <option value="${obj.key}" <c:if test="${obj.key == seatType}">selected</c:if>>${obj.value}</option>
   	</c:forEach>
   </select>
   <% if(request.getAttribute("massage") != null){ %><div id="${classStr}">${massage}</div><%}%>
   <canvas id="canvas" hidden></canvas>
   <input type="hidden" id="ticketId" name="ticketId" value="">
  </form>
  <script src="../js/jsQR.js"></script>
  <script src="../js/ticketEntry.js"></script>
</body>
</html>
