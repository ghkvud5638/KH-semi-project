<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">


#nav_menu > ul {
 	margin:0px 0px 0px 494px;

	list-style-type:none;
}
#nav_menu > ul > li {
	margin-left: 100px;
 	background-color:#e5ddb4;
	display: inline;
	border: 3px solid #ccc;
	color: #000000;
	font-size: 20px;
 	padding: 300px 100px 300px 100px; 
}

#nav_menu > ul > li:hover {
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
<ul>
<li id="prodsales"><p>매출 관리</p>
<span>상품의 매출을 날짜별로 확인할 수 있습니다</span>
 </li>
<li>상품 확인</li>
</ul>
</div>

<div id="proddiv">
</div>


<%@ include file="../../footer.jsp" %>
