<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<style>
    body {
      font-family: 'Ropa Sans', sans-serif;
      color: #333;
      margin: 0 auto;
      position: relative;
      text-align: center;
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
      width: 50%;
      max-width: 640px;
      height: auto;
      margin: 20px auto;
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
  <h1>退場カメラ</h1>
  <div class="header">
   <form action="EntryExit" method="get"><button type="submit">戻る</button></form>
  </div>
  <% if(request.getAttribute("massage") != null){ %><div id="${classStr}">${massage}</div><%}%>
  <canvas id="canvas" hidden></canvas>
  <form id="form" action="ExitConduct" >
  	<input type="hidden" id="ticketId" name="ticketId" value="">
  	<input type="hidden" id="sound" name="sound" value="${sound}">
  </form>
  <script src="../js/jsQR.js"></script>
  <script src="../js/ticketEntry.js"></script>
</body>
</html>

