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
      text-align: center;
      background-color: #f4f4f4;
    }

    h1 {
      margin: 20px 0;
      font-size: 40px;
      color: #000; /* 修正：色指定が正しくないため修正 */
    }

    #emergency {
      color: red;
      font-size: 24px;
    }

    #allGreen {
      color: green;
      font-size: 24px;
    }

    #canvas {
      width: 70%;
      max-width: 640px;
      height: auto;
      margin: 20px auto;
      border: 5px solid gray;
      border-radius: 10px;
    }

    #output {
      margin-top: 20px;
      background: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    #output div {
      padding-bottom: 10px;
      word-wrap: break-word;
    }

    #noQRFound {
      text-align: center;
      color: #555;
      font-size: 10px;
      margin-top: 20px;
    }

    .header {
      margin-top: 20px;
    }

    .header button {
      background-color: #007BFF;
      color: #fff;
      padding: 12px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 18px;
      transition: background-color 0.3s;
    }

    .header button:hover {
      background-color: #0056b3;
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
  <p>${testId } ${test } ${test.status } ${test.ticketsId } ${test.purchaseId }</p>
    <input type="hidden" id="ticketId" name="ticketId" value="">
    <input type="hidden" id="sound" name="sound" value="${sound}">
  </form>
  <script src="../js/jsQR.js"></script>
  <script src="../js/ticketEntry.js"></script>
</body>
</html>
