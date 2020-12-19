<%@page import="java.util.List"%>
<%@page import="camping.dto.Camp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<%
   List<Camp> camp = (List) request.getAttribute("list2");
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
   src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
      for (int i = 0; i < camp.size(); i++) {
         int j=(int)(Math.random()*20)+1;
   %>

   <div class="select-item">
      <div class="select-box1">
         <img
            src="/campimage/image<%=j %>.jpg" width="250" height="130">
      </div>
      <div class="select-box2">
         <div class="title">
            <span class="title-1"><%=camp.get(i).getCAMP_NAME()%></span>&nbsp&nbsp&nbsp
            <span class="title-2"><<%=camp.get(i).getINDUTY()%>></span>
         </div>
         <br>
         <div class="ADDRRR" style="font-size: 8px;"><%=camp.get(i).getADDR()%></div>
         <br>
         <div class="tel" style="font-size: 8px;">
            <span>대표 전화:</span><%=camp.get(i).getTEL()%></div>
      </div>
      <div class="select-box3">
         <div class="select-box-sub">
            <div class="facil" style="font-size: 11px;">
               <span class="facil-1">편의 시설: <%=camp.get(i).getFACIL()%></span>
            </div>
            <div class="intro">
               <p class="intro-1"><%=camp.get(i).getINTRO()%></p>
            </div>
         </div>
      </div>
      <div class="select-box4">
         <button type="button" class="select-rev-btn" name="select-rev-btn"
            id="select-rev-btn" onclick="page_move(<%=camp.get(i).getCAMP_ID() %>,<%=j %>);">예약하기</button>
      </div>
   </div>
   <br><br><hr><br>

   <%
      }
   %>

</body>
</html>