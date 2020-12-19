<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script type="text/javascript">

$(document).ready(function(){
	
	$('#userlist').on('click', function(){
		location.href = "/admin/userlist";
	});

	});
 </script>

<style type="text/css">
button:hover {
	color: #25a5f0;
/* 	background-color: #cac5ab; */
    cursor: pointer;
	
}

#menua {
margin: 50px 15px 0px 203px;
}

#menua > table {
 	margin: -370px 0px 50px 494px;

}
.select {
/* 	width: 300px; */
	text-align: center;
/* 	border-bottom:1px solid; */
 	margin-left: 400px; 
/* background-color: #f0e8df; */
/* 	display: inline; */
/* 	border: 3px solid #ccc; */
/* 	color: #000000; */
/* 	font-family:궁서; */
	text-shadow:0px 1px 1px;
	font-size: 20px;
  	padding: 0px 0px 30px 0px;  
    border-radius: 20px;
}

.select_content {
	text-indent: 10px;
	text-align: left;
	margin:0px 37px 0px 37px;
	
}


.select:hover {
	color: #25a5f0;
/*  background-color:#d3ceb5; */
 
 cursor:pointer;
 
 
}

.no {
	padding: 0px 30px 0px 30px;
}
</style>

<div id="menua">
<table>
<tr>
<td id="userlist" class="select">회원 관리
</td>
</tr>
</table>
</div>