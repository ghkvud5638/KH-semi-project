<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="admin.dto.TB_REV"%>
<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>



<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>
<% List<TB_REV> revList = (List<TB_REV>) request.getAttribute("revList"); %>
<% 
	int randomNum = (int)(Math.random()*20)+1;
%>
<style type="text/css">
.RevBoardTitle{
	margin:-300px 0 0 400px;
    position: absolute;
    font-size: 28px;
    font-weight: bolder;
}
.listWrap{
	border-top: 1px solid rgb(202 196 196 / 50%);;
	padding-top: 10px;
}
.listWrap > td {
	padding: 10px 0 10px 0;
	text-align: center;
}
.RevCampListWrap > table{
	table-layout: fixed;
	width: 1504px;
}
.RevCampListWrap{
	border-top: 3px solid black;
    width: 1503px;
    height: 100px;
	margin: -180px 0px 0px 400px;
}
#FirstDatePick{
	position: absolute;
    margin: 5px 0 0 -120px;
}
.calendarWrap{
	margin: -219px 212px 0 1768px;    
	position: absolute;
    width: 230px;
 }


</style>
<script type="text/javascript"> 
$(document).ready(function(){
	$(function(){
	    $("#FirstDatePick").datepicker({
    	  changeYear:true,
    	  changeMonth:true,
          showOn: "both",
          buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
          buttonImageOnly: true,
          buttonText: "Select date",
          value:"2020-11-01",
          dateFormat: "yy/mm/dd"
	    });
// 	    $("#SecondDatePick").datepicker({
// 	  	  changeYear:true,
// 	  	  changeMonth:true,
// 	      showOn: "both",
// 	      buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
// 	      buttonImageOnly: true,
// 	      buttonText: "Select date",
// 	      dateFormat: "yy/mm/dd"
// 	  });
	});
})
</script>
<%
Date today = new Date();     	   
SimpleDateFormat date = new SimpleDateFormat("yy/MM/dd");
String toDay = date.format(today);

Calendar mon = Calendar.getInstance();
mon.add(Calendar.MONTH , -1);
String beforeMonth = new java.text.SimpleDateFormat("yy/MM/dd").format(mon.getTime());

int totalPrice = 0;
for(int i=0; i<revList.size(); i++){
	totalPrice+=revList.get(i).getTotal();
}
%>

<div class="calendarWrap">
	<form action="/admin/revSearch" method="post">
		<input type="text" name="FirstDate" id="FirstDatePick" size="12" value="<%=toDay%>"/> 
		<input id="search" type="submit" value="조회">
	</form>
</div>

<div class="RevBoardTitle"><h1>캠핑장 예약 내역</h1></div>
<div class="RevCampListWrap">
	<table>
		<tr class="thWrap">
			<th>예약 기간</th>
			<th>주문 일자 [주문번호]</th>
			<th>캠핑장 이름</th>
			<th>인원</th>
			<th>총 금액</th>
		</tr>
		
		<%if(revList!=null){ %>
		<%for(int i=0; i<revList.size(); i++){ %>
		<tr class="listWrap">
			<td><%=revList.get(i).getFirst()%> ~ <%=revList.get(i).getLast()%></td>
			<td><%=revList.get(i).getPaymentdate()%></td>
			<td><%=revList.get(i).getCamp_name()%></td>
			<td><%=revList.get(i).getPeople()%></td>
			<td><fmt:formatNumber value="<%=revList.get(i).getTotal()%>" pattern="#,###"/>원
			</td>
		</tr>
		<%} %>
		<%} %>
	</table>
</div>