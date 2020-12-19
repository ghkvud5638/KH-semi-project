<%@page import="mypage.dto.TB_REV"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="mypage.dto.TB_PAY"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>
<% List<TB_PAY> list = (List<TB_PAY>) request.getAttribute("payList"); %>
<% List<TB_REV> revList = (List<TB_REV>) request.getAttribute("RevList"); %>
<% 
	int randomNum = (int)(Math.random()*20)+1;
%>


<style type="text/css">
.thWrap{
	border-top: 1px solid #ccc;
}
.thWrap > th{
	border-top: 1px solid #ccc;
	padding-top: 15px;
	padding-bottom: 15px;
}
.payBoardTitle{
	margin: -25px 0 0 400px;
    position: absolute;
    font-size: 28px;
    font-weight: bolder;
}
.payBoardWrap{
	border-top: 3px solid black;
    width: 1503px;
    height: 100px;
	margin:15px 0px 0px 400px;
}
.payBoardWrap > table{
	table-layout: fixed;
	width: 1504px;
/* 	margin: 3px 0 0 0; */
}
.RevCampListWrap > table{
	table-layout: fixed;
	width: 1504px;
}
.RevCampListWrap{
	border-top: 3px solid black;
    width: 1503px;
    height: 100px;
	margin:475px  0px 0px 400px;
}
.RevBoardTitle{
	margin:430px 0 0 400px;
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
.img{
	margin: 0px 11px -3px 0;
}
#FirstDatePick{
	position: absolute;
	margin: 2px 0 0 -55px;
}
#SecondDatePick{
	position: absolute;
    margin: 2px 0 0 -220px;
}
.calendarWrap{
    margin: -38px 212px 0 1597px;
    position: absolute;
    width: 230px;
 }
 
 
 
 margin: 4px 0 0 22px;
 
 
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(function(){
	    $("#FirstDatePick").datepicker({
    	  changeYear:true,
    	  changeMonth:true,
          showOn: "both",
          buttonImage: "/images/calendar.png",
          buttonImageOnly: true,
          buttonText: "Select date",
          value:"2020-08-01",
          dateFormat: "yy/mm/dd"
	    });
	    $("#SecondDatePick").datepicker({
	  	  changeYear:true,
	  	  changeMonth:true,
	      showOn: "both",
	      buttonImage: "/images/calendar.png",
	      buttonImageOnly: true,
	      buttonText: "Select date",
	      dateFormat: "yy/mm/dd"
	  });
	});
})

</script>
<%
Date today = new Date();        
SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
String toDay = date.format(today);

Calendar mon = Calendar.getInstance();
mon.add(Calendar.MONTH , -1);
String beforeMonth = new java.text.SimpleDateFormat("yyyy/MM/dd").format(mon.getTime());

int totalPrice = 0;
for(int i=0; i<list.size(); i++){
	totalPrice+=list.get(i).getTotalprice();
}
%>



<div class="calendarWrap">
	<form action="/myPage/paySearch" method="post">
		<input type="text" name="FirstDate" id="FirstDatePick" size="12" value="<%=beforeMonth%>"/> ~
		<input type="text" name="SecondDate" id="SecondDatePick" size="12" value="<%=toDay%>"/>
		<input id="search" type="submit" value="조회">
	</form>
</div>
<div class="payBoardTitle"><h1>상품 결제 내역</h1></div>
<div class="payBoardWrap">
	<table>
		<tr class="thWrap">
			<th>주문 일자 [주문번호]</th>
			<th>상품 정보</th>
			<th>수량</th>
			<th>구매 금액</th>
		</tr>
		<%if(list != null){ %>
		<%	for(int i=0; i<list.size(); i++) { %>
		<tr class="listWrap">
			<td id="boardno">
<%-- 			<% String date = list.get(i).getPay_date(); date=date.substring(0,7); %> --%>
<%-- 				<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" /> --%>
				<%=list.get(i).getPay_date() %>
			</td>
			<td>
				<a class="link" href="/shopping/detail?prodno=<%=list.get(i).getProd_id()%>"><img src="<%=list.get(i).getProd_picturetitle()%>" style="width: 72px;" class="img"></a>
				<a class="link" href="/shopping/detail?prodno=<%=list.get(i).getProd_id()%>"><%=list.get(i).getProd_name()%></a>
			</td>
			
<%-- 			<td><%=list.get(i).getUser_id()%></td> --%>
			<td><%=list.get(i).getProd_cnt()%></td>
			<td>
				<fmt:formatNumber value="<%=list.get(i).getTotalprice()%>" pattern="#,###"/>원
			</td>
		</tr>
		<% } %>
		<% } %>
	</table>
<%-- 	<div>총 지출 :<fmt:formatNumber value="<%=totalPrice%>"pattern="#,###" />원 </div> --%>
</div>


<div class="RevBoardTitle"><h1>캠핑장 예약 내역</h1></div>
<div class="RevCampListWrap">
	<table>
		<tr class="thWrap">
			<th>주문 일자 [주문번호]</th>
			<th>캠핑장 이름</th>
			<th>인원</th>
			<th>총 금액</th>
		</tr>
		<%if(revList!=null){ %>
		<%for(int i=0; i<revList.size(); i++){ %>
		<tr class="listWrap">
			<td><%=revList.get(i).getPaymentdate()%></td>
			<td><a href="/camp/detail?camp_id=<%=revList.get(i).getCamp_id()%>&j=<%=randomNum%>"><%=revList.get(i).getCamp_name()%></a></td>
			<td><%=revList.get(i).getPeople()%></td>
			<td><fmt:formatNumber value="<%=revList.get(i).getTotal()%>" pattern="#,###"/>원
			</td>
		</tr>
		<%} %>
		<%} %>
	</table>
</div>


