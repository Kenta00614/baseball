<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
@SuppressWarnings("unchecked")
HashMap<Character,String> seatTypeList=(HashMap<Character,String>)request.getAttribute("seatTypeList");
String seatType = "";
if(request.getAttribute("seatType") != null){
	seatType = request.getAttribute("seatType").toString();
}

%>
<%! HashMap<Integer,String> map=new HashMap<Integer,String>(){
	{
		put(1,"0B");
		put(2,"0F");
		put(3,"0T");
		put(4,"0R");
		put(5,"0L");
	}
}; %>
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
      color: 000;
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
  <h1>入場カメラ</h1>
  <div class="header">
   <form action="EntryExit" method="get"><button type="submit">戻る</button></form>
  </div>
  <% if(request.getAttribute("massage") != null){ %><div id="${classStr}">${massage}</div><%}%>
  <form id="form" action="EntryConduct">
<select name="seatType" style="
    padding: 12px;
    font-size: 16px;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: #f8f8f8;
    color: #333;
    outline: none;
    transition: border-color 0.3s;
    width: 200px; /* Adjust the width as needed */
">

<% for (int i = 1; i <= 5; i++) { %>
    <option value="<%= map.get(i) %>" <% if (map.get(i).equals(seatType)) { %> <%= "selected" %> <% } %>>
        <%= seatTypeList.get(map.get(i)) %>
    </option>
<% } %>

</select>

  <canvas id="canvas" hidden></canvas>
  <div id="output"></div>
  	<input type="hidden" id="ticketId" name="ticketId" value="">
  </form>
  <script src="../js/jsQR.js"></script>
  <script src="../js/ticketEntry.js"></script>
</body>
</html>

