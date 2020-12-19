<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">


#nav_menu > table {
 	margin:0px 0px 0px 494px;

	list-style-type:none;
}
#nav_menu td {
	margin-left: 100px;
 	background-color:#e5ddb4;
	display: inline;
	border: 3px solid #ccc;
	color: #000000;
	font-size: 20px;
 	padding: 300px 100px 300px 100px; 
}

#nav_menu td:hover {
 color:#D4F4FA;
 background-color:yellow;
 
}

</style>

<script type="text/javascript">
$(document).ready(function() {

	$('#prodsales').on('click', function(){
		alert("작동")
// 		$.ajax({
// 	   			url:"/admin/sales",
// 				type:"GET",
// 				dataType:'html',
// 				success: function(data){
// 						alert("성공");
// 						$("#proddiv").html( data );
		
// 				},
// 				error: function() {
					
// 						alert("게시글 삭제 실패");
// 				}
// 		});
	});

});
</script>


<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>


<div id="nav_menu">
<table>
<tr>
<td id="prodsales">매출 관리 <br>
매출을 확인하는 곳입니다</td>
<td>상품 확인</td>
</tr>
</table>
</div>


<%@ include file="../../footer.jsp" %>
