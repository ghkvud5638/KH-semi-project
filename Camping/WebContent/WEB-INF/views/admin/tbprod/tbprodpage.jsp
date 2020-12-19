<%@page import="board.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">


#category {
 	margin: -60px 0px 30px 0px;
/* 	padding: 30px; 0px 30px 0px; */
}
.prodinfo {
/* 	table-layout: fixed; */
	float: right;
	margin-top: -195px;
}
.prodinfo td{
/*  	border:1px solid #ccc;  */
 	width: 700px;
 	height: 50px;
 	padding-top:10px;
}

.wrap{
 	border-top: 1px solid #ccc;
 	margin: 0px 0 0 415px;
 	width:1100px;
 	height: 700px;
}
.prodName{
	font-size: 30px;
    font-weight: bold;
}
.topBar{
	margin:30px 0 30px 0px;
}
.prodnum{
	font-size: 20px;
    font-weight: bold;
}

.content{
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    
    padding: 72px 5px 0 0px;
    font-size: 20px;
}
.contentBtn{
    padding: 72px 5px 0 0px;
    font-size: 20px;
}
#updateBtn{
 	width:100px;
/*     background-color: rgb(97, 118, 138, .6); */
/*     border: none; */
/*     color:#fff; */
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
    background-color: #f0e8df;
}
#removeBtn{
 	width:100px;
/*     border: none; */
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
    background-color: #f0e8df;
}
#backBtn{
 	width:100px;
/*     border: none; */
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
    background-color: #f0e8df;
    
}
.contentBtn{
	margin-top: 200px;
 	float: right;
	margin-right: 100px;
}

</style>
<script type="text/javascript">

$(document).ready(function(){
	console.log("로그인 아이디 :" +"${loginid}")
	console.log("작성자 : "+$(".userId").text())

	if("${loginid}" != $(".userId").text()){
		$(".btnWrap").hide();
	} 
	if ("${loginid}" == $(".userId").text()) {
		$(".btnWrap").show();
	}
	
	
	$("#deleteBtn").on("click",function(){
		console.log("here")
		$.ajax({
   			url:"/board/detail",
			type:"POST",
			data:{
				boardNo : $(".boardno").text()
			},
			dataType:'json',
			success: function(data){
//					성공
				if (data.msg=="ok") {
					alert("게시글 삭제 완료"); 
					location.href = data.redirectUrl;
				}else {
					alert("게시글 삭제 실패");
				}
			}
   		});
	})
})
</script>

<%@ include file="../adminProdManage.jsp" %>


<div class="wrap">
<div class="topBar">
<div id="category">
<span class="prodnum">카테고리 : ${cate_id}</span>
</div>
<table>
<tr>
<td><a href="${prod_picturetitle}" download="대표이미지.jpg"><img src="${prod_picturetitle}" width="200px" height="200px"></a></td>
</tr>
</table>
<table class="prodinfo">
<tr>
<td>	<span class="prodName">${prod_name}</span></td>
</tr>
<tr>
<td>	 <span class="prodnum">수량 : ${prod_num}개</span></td>
</tr>
<tr>
<td>	  <span class="prodnum">가격 : ${prod_price}원</span></td>
</tr>

</table>
</div>
<div class="content">
<img src="${prod_picturedetail}">
<span class="contentBtn">
    	<a href="/admin/produpdate?prod_id=${prod_id}"><button type="button" id="updateBtn">상품 변경</button></a>
    	<a href="/admin/prodremove?prod_id=${prod_id}"><button type="button" id="removeBtn">상품 삭제</button></a>
    	<a href="/admin/prodlist"><button type="button" id="backBtn">게시판으로</button></a>

</span>
</div>
</div>

<%@ include file="../../footer.jsp" %>

