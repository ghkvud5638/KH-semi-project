<%@page import="java.util.List"%>
<%@page import="mypage.dto.TB_COMMENT"%>
<%@page import="mypage.dto.TB_ASK_BOARD"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<% TB_ASK_BOARD detail = (TB_ASK_BOARD)request.getAttribute("askBoard"); %>

<style type="text/css">
body{
	overflow: auto;
}
.wrap{
	border-top: 1px solid #ccc;
/* 	border-bottom: 1px solid #ccc; */
	margin: -130px 0 0 515px;
	width:1100px; 
	height: 1000px;
}
.articleTitle{
	font-size: 30px;
    font-weight: bold;
}
.articleTopbar{
	margin:30px 0 30px 0px;
}
.userId{
	margin: 0px 50px 0px 3px;
}
.articleUserId{
	border-top: 1px solid #ccc;
	padding: 20px 0 20px 0px;
}
.hit{
	float:right;
}
.date{
/* 	border-left: 1px solid #ccc;  */
}
.content{
    border-top: 1px solid #ccc;
    padding: 72px 5px 0 0px;
    font-size: 20px;
}
#deleteBtn{
 	margin: 39px 0px -89px 513px;
 	width:100px;
/*     background-color: rgb(97, 118, 138, .6); */
    border: none;
/*     color:#fff; */
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
.btnWrap{
	margin: -184px 0 0 0px;
}
.boardno{
	float: right;
}
.modifyLink{
 	width:100px;
	 background-color: #EFEFEF; 
    border: none;
/*     color:#fff; */
    padding:20px 0px 17px 1px;
    
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
.boardCate{
	padding: 0 50px 0 0;
}

.commentWrap{
	margin: -222px 0 0 514px;
}
#commentApply{
	position: absolute;
    margin: -101px 0 0 1350px;
    width: 100px;
    height: 100px;
}


html{
    position: relative;
    min-height: 100%;
    margin: 0;
}

</style>

<script type="text/javascript">
$(document).ready(function(){
	console.log("start")
	$(".result").load("/myPage/comment?boardno="+$("#boardno").text()) //열리자마자 댓글 불러오기
	
	$("#commentApply").on("click", function(){
		console.log("here")
		console.log($("#commentContent").val())
		console.log($("#boardno").text())
   		$.ajax({
   			url:"/myPage/commentapply",
			type:"POST",
			data:{
				commentContent : $("#commentContent").val(),
				boardno : $("#boardno").text()
			},
			dataType:'json',
			success: function(data){
				if (data.msg=="ok") {
// 					alert("게시글 작성 성공");
// 					location.href = data.redirectUrl;
					console.log("im here")
					$(".result").load("/myPage/comment?boardno="+$("#boardno").text()) //작성 처리가 된 결과 댓글리스트 불러오기 
 					$("#commentContent").val(''); //댓글 작성 완료하면 textarea 내용 비우기
				}else {
					alert("댓글 작성 실패");
				}
			}
   		});	
	})
})
</script>



<div class="wrap">
<div class="articleTopbar">
	<span class="articleTitle"><%=detail.getTitle() %> </span>
	<span id="boardno" class="boardno"><%=detail.getBoardno() %></span>
</div>
<div class="articleUserId">
	<span class="boardCate"><%=detail.getBoard_cate() %></span>
	 작성자 :<span class="userId"><%=detail.getUser_id() %></span>
	<span class="date"><%=detail.getWrittendate() %></span>
<%-- 	<span class="hit">조회수 <%=detail.getHit() %></span> --%>
</div>
<div class="content"><%=detail.getContent() %></div>
</div>




<div class="commentWrap">
	<textarea id="commentContent" class="CommentContent" cols="120" rows="6" name="CommentContent" style="resize:none;"></textarea>
</div>
	<input id="commentApply" type="button" value="댓글 등록" />

<div class="result"></div>


<!-- <div class="commentDiv"> -->
<%-- <%if(list != null){ %> --%>
<%-- <%	for(int i=0; i<list.size(); i++) { %> --%>
<!-- <div class="tdWrap"> -->
<%-- 	<strong class="tdUserId"><%=list.get(i).getUser_id() %></strong> --%>
<%-- 	<p class="tdDate"><%=list.get(i).getComment_date() %></p> --%>
<!-- </div> -->
<!-- <div>	 -->
<%-- 	<p class="tdContent"><%=list.get(i).getContent() %></p> --%>
<!-- </div> -->
<%-- <%} %> --%>
<%-- <%} %> --%>
<!-- </div> -->


<!-- <div class="btnWrap"> -->
<!-- 	<button type="button" id="deleteBtn">삭제</button>  -->
<!-- <!-- 	<button type="button" id="modifyBtn">수정</button> --> -->
<%-- 	<a class="modifyLink" href="/board/modify?boardno=<%=detail.getBoardno() %>">수정</a> --%>
<!-- </div> -->

