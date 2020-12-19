<%@page import="admin.dto.TB_PROD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%@ include file="../header.jsp" %>
<%-- <%@ include file="adminsidemenu.jsp" %> --%>

<style type="text/css">

#menu > table {
    margin: 150px 0px 0px 194px;

}
.select {
   width: 300px;
   text-align: center;
/*    margin-left: 100px; */
    background-color:#e6e3d2;
/*    display: inline; */
/*     border: 3px solid #ccc;  */
   color: #000000;
/*    font-family:궁서; */
   text-shadow:0px 1px 1px;
   font-size: 20px;
     padding: 50px 10px 350px 10px;  
    border-radius: 40px;
    
}

.select_content {
   text-indent: 10px;
   text-align: left;
   margin:0px 37px 0px 37px;
   
}


.select:hover {
 color:#695e2c;
 background-color:#d3ceb5;
 cursor:pointer;
 
 
}

.no {
   padding: 0px 30px 0px 30px;
}


</style>

<script type="text/javascript">
$(document).ready(function() {

   $('#userManage').on('click', function(){
//       alert("userManage 작동")
      location.href = "/admin/usermanager";
   });
   $('#campManage').on('click', function(){
//       alert("prodManage 작동")
      location.href = "/admin/campmanager";
   });
   $('#prodManage').on('click', function(){
//       alert("prodManage 작동")
      location.href = "/admin/prodmanager";
   });
   $('#boardManage').on('click', function(){
//       alert("boardManager 작동")
      location.href = "/admin/boardmanager";
   });

});
</script>

<div id="menu">
<table>
<tr>
<td id="userManage" class="select">
<ul>
<li style="font-size: 30px;">회원 관리</li>
<li><br><br></li>
<li class="select_content">회원을 관리할 수 있는 페이지로 이동합니다</li>
</ul>
</td>
<td class="no">
</td>
<td id="campManage" class="select">
<ul>
<li style="font-size: 30px;">캠핑장 예약 관리</li>
<li><br><br></li>
<li class="select_content">캠핑장 예약 상태를 확인 할 수 있습니다</li>
</ul>
</td>
<td class="no">
</td>
<td id="prodManage" class="select"><ul>
<li style="font-size: 30px;">쇼핑몰 관리</li>
<li><br><br></li>

<li class="select_content">쇼핑몰 매출 및 상품을 등록/수정/삭제 할 수 있습니다</li>
</ul>
</td>
<td class="no">
</td>
<td id="boardManage" class="select"><ul>
<li style="font-size: 30px;">게시판 관리</li>
<li><br><br></li>

<li class="select_content">전체 게시판 및 회원의 1:1 문의를 확인할 수 있습니다.</li>
</ul>
</td>
</tr>
</table>
</div>

<%@ include file="../footer.jsp" %>