<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="camping.dto.Camp"%>


<%
   List<Camp> camp = (List) request.getAttribute("list");
%>

   <%
      for (int i = 0; i < camp.size(); i++) {
         int j=(int)(Math.random()*20)+1;
   %>

<style type="text/css">
.campfont {
	font-family: "한컴바탕";
	font-size: 16px;
	font-weight: bolder;
	}

</style>
   <div class="item" onclick="setCenter(this);">
      <div class="box1">
         <img
            src="/campimage/image<%=j %>.jpg" width="100" height="100">
      </div>
      <div class="box2">
         <a href="#" class="campfont"><input type="hidden" class="campName"
            value="<%=camp.get(i).getCAMP_NAME()%>" /><%=camp.get(i).getCAMP_NAME()%></a>
         <p style="font-size: 10px; margin-top: 5px;"><%=camp.get(i).getINDUTY()%></p>
         <p style="font-size: 8px; margin-top: 5px;"><%=camp.get(i).getADDR()%></p>
         <input type="hidden" value=<%=camp.get(i).getLONGITUDE()%>
            class="long" /> <input type="hidden"
            value=<%=camp.get(i).getLATITUDE()%> class="lati" />
            <input type="hidden" value="<%=camp.get(i).getADDR()%>" class="addR" />
            
      </div>
      <div class="box3">
         <button type="button" class="rev-btn" name="rev-btn" id="rev-btn" onclick="page_move(<%=camp.get(i).getCAMP_ID() %>,<%=j %>);">예약하기</button>
      </div>
   </div>
   <hr>
   <%
      }
   %>
   <jsp:include page="/WEB-INF/views/common/campsearchPaging.jsp" />
   