<%@page import="admin.dto.TB_PAY"%>
<%@page import="admin.dto.TB_PROD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%   Object obj = request.getAttribute("list"); %>
<%   List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj; %>

<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<script type="text/javascript">


$(document).ready(function() {
var datechk = false;

   $.datepicker.setDefaults({
        showOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다.
        buttonImage: "/images/calendar.png", // 버튼 이미지
        buttonImageOnly: true, // 버튼에 있는 이미지만 표시한다.
        changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
        changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
        minDate: '-10y', // 현재날짜로부터 100년이전까지 년을 표시한다.
        maxDate: 'today',
        nextText: '다음 달', // next 아이콘의 툴팁.
        prevText: '이전 달', // prev 아이콘의 툴팁.
        numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
        //stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
//         yearRange: 'c-10:c+0', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
        showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
        currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
        closeText: '닫기',  // 닫기 버튼 패널
        dateFormat: "yy/mm/dd", // 텍스트 필드에 입력되는 날짜 형식.
        showAnim: "slide", //애니메이션을 적용한다.
        showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월의 한글 형식.
       
        onSelect: function(datetext) {
           if(this.id == "startdate") {
              
             var minDate = $(this).datepicker("getDate")
             
            $("#enddate").datepicker("option", "minDate", minDate);
            $("#enddate").datepicker("option", "minDate", '-10y');
           } else if(this.id == "enddate"){
              
              var maxDate = $(this).datepicker("getDate");
             
              $("#startdate").datepicker("option", "maxDate", maxDate);
              $("#startdate").datepicker("option", "maxDate", "today");

              }
           }

   })

 $(".datepicker").datepicker()

$('#datechk').on('click', function(){ 
   if(datechk==false){
   datechk = true;
   $('#datepicker').css('display','block');
   } else {
   datechk = false;
   $('#datepicker').css('display','none');
      
   }

});


});

</script>

<style type="text/css">


.tableWrap{
   margin:0px 0px 0px 394px
}
.tableWrap > table{
   table-layout: fixed;
   width: 1004px;
}
.articleWrap > td{
   padding: 15px 71px 14px 74px;
}
.datechecklist > table{
   margin: -40px 0px 10px 700px;

}
.date > td{
    padding: 15px 31px 14px 34px;
    text-align: center;
/*     border: 3px solid #ccc; */
/*     font-style: italic; */
    font-weight: bolder;
   font-size: 18px;
}
.text-center{
   margin:69px 0px 0px 600px;
   width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: 0 0 -35px -15px;
    font-size: 19px;
    font-weight: bolder
}
.thWrap{
   border-top: 1px solid #ccc;
}
.thWrap > th{
   border-top: 1px solid #ccc;
   padding-top: 15px;
   padding-bottom: 15px;
}
.articleWrap{
   border-top: 1px solid rgb(202 196 196 / 50%);;
   padding-top: 10px;
   text-align: center;
}

#datepicker > td {
   font-size: 16px;
   font-weight: bolder;
}
.dateChk{
   margin: 30px 0px 10px 0px;
    width:300px;
    height: 50px;
     background-color: rgb(242, 240, 230, .6);

     text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    font-weight: bolder;
    cursor: pointer;
        border-radius: 4px;
}
.dateChkBtn{
    width:100px;
     background-color: rgb(242, 240, 230, .6);
     padding: 3px 0;
     text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
        border-radius: 4px;
}

</style>


<%@ include file="../adminProdManage.jsp" %>


<div class="tableWrap">
<form action="/admin/sales" method="post">
<table>
<tr>
<td style="font-size: 19px; font-weight:bolder;">
매출목록
</td>
</tr>
<tr>
<td><button type="button" id="datechk" class="datechk">날짜 조회</button></td>
</tr>
<tr id="datepicker" style="display: none" >
<td width="90px">
    날짜 선택 :
</td> 
<td>
    <input type="text" class="datepicker" id="startdate" name="startdate" value="${start }">
</td>
<td width="30px" style="text-align: center"> ~</td>
<td>
<input type="text" class="datepicker" id="enddate" name="enddate" value="${end}">
</td>
<td>
<button id="dachk" type="submit" class="datechkBtn">조회하기</button>
</td>
</tr>
</table>
<div class="datechecklist">
<table>
<tr class="date">

<td>
조회 날짜 : ${start} ~ ${end }</td>
</tr>
</table>
</div>
<table>
<tr class="thWrap">
<!--    <th width="100">선택</th> -->
   <th>품목</th>
   <th>판매 개수</th>
   <th>재고량</th>
   <th>매출액</th>
   <th>판매 날짜</th>

</tr>
<% int total = 0; %>
<% if(list.size() != 0) { %>
<%   for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr class="articleWrap">
   <td>
   <a href="prodpage?prod_id=<%= list.get(i).get("Prod_id") %>">
   <%=list.get(i).get("Prod_name") %></a></td>
   <td><%=list.get(i).get("Prod_cnt") %>개</td>
   <td><%=list.get(i).get("Prod_num") %>개</td>
   <td><%=list.get(i).get("Totalprice")%>원</td>
   <td><%=list.get(i).get("Pay_date")%></td>

   
</tr>
<% total += (int)(list.get(i).get("Totalprice"));  %>

<%   } %>
<% } else { %>
<tr class="articleWrap">
   <td>
   없음</td>
   <td>없음</td>
   <td>없음</td>
   <td>없음</td>
   <td>없음</td>

   
</tr>
<% } %>
</table>
</form>

<div style="margin: 50px 0px 0px 850px; font-weight:bolder; font-size:17px;"> 
   총 매출 : 
   <%=total %>원

</div>

<jsp:include page="/WEB-INF/views/common/adminSalesPaging.jsp" />
</div>


<%@ include file="../../footer.jsp" %>