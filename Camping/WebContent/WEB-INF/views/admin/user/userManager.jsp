<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">


#menu > table {
 	margin:-300px 0px 0px 494px;

/* 	list-style-type:none; */
}
.select {
	width: 300px;
	text-align: center;
/* 	margin-left: 100px; */
 	background-color:#e6e3d2;
/* 	display: inline; */
/* 	border: 3px solid #ccc; */
	color: #000000;
	text-shadow:0px 1px 1px;
	font-size: 20px;
  	padding: 50px 20px 350px 10px;  
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
	padding: 0px 100px 0px 100px;
}

</style>

<script type="text/javascript">
$(document).ready(function() {

	$('#userInfo').on('click', function(){
		location.href = "/admin/userlist";
	});
// 	$('#ask').on('click', function(){
// 		alert("작동")
// 		location.href = "/admin/ask";
// 	});

});
</script>


<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>


<div id="menu">
<table>
<tr>
<td id="userInfo" class="select">
<ul>
<li style="font-size: 30px;">회원 관리</li>
<li><br></li>
<li class="select_content">회원들의 목록을 확인할 수 있습니다</li>
</ul>
</td>
<!-- <td class="no"> -->
<!-- </td> -->
<!-- <td id="ask" class="select"><ul> -->
<!-- <li style="font-size: 30px;">1:1문의 확인</li> -->
<!-- <li><br></li> -->

<!-- <li class="select_content">회원들이 올린 1:1문의를 확인할 수 있습니다</li> -->
<!-- </ul> -->
<!-- </td> -->
<!-- </tr> -->
</table>
</div>


<%@ include file="../../footer.jsp" %>
