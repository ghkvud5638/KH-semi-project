<%@page import="board.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../BoardSidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<% Board detail = (Board)request.getAttribute("detail"); %>

<style type="text/css">
.wrap{
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	margin:-310px 0 0 515px;
	width:1100px; 
	height: 700px;
}
.articleTitle{
	font-size: 30px;
    font-weight: bold;
}
.articleTopbar{
	margin:30px 0 30px 0px;
}
.userId{
	margin: 0px 55px 0px 10px;
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
 	margin: 135px  0px -89px 513px;
 	width:100px;
 background-color: rgb(97, 118, 138, .6);
    border: none;
color:#fff;
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
.btnWrap{
/* 	margin: -184px 0 0 0px; */
}
.boardno{
	float: right;
}
.modifyLink{
 	width:100px;
/* 	 background-color: #EFEFEF;  */
    background-color: #61768A;
    opacity:.6;
    
    border: none;     
    color:#fff;
    padding:20px 0px 17px 1px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
.commentWrap{
	margin:50px 0 0 514px;
}
#commentApply{
	position: absolute;
    margin: -101px 0 0 1350px;
    width: 100px;
    height: 99px;
}
html{
    position: relative;
    min-height: 100%;
    margin: 0;
}
.video{
	
}

.contentText{
	margin: 30px 0px 0px 0px;
}
.writer{
	margin: 0 0 0 55px;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$(".result").load("/board/comment?boardno="+$("#boardno").text()) //열리자마자 댓글 불러오기
	
	console.log("로그인 아이디 :" +"${loginid}")
	console.log("작성자 : "+$(".userId").text())

	if("${loginid}" != $(".userId").text()){
		$(".btnWrap").hide();
	} 
	if ("${loginid}" == $(".userId").text()) {
		$(".btnWrap").show();
	}
	
	
	$("#deleteBtn").on("click",function(){
	  if(confirm("게시글을 삭제합니다.")==true){
		console.log("here")
		$.ajax({
   			url:"/board/delete",
			type:"POST",
			data:{
				boardNo : $(".boardno").text()
			},
			dataType:'json',
			success: function(data){
				if (data.msg=="ok") {
					alert("게시글 삭제 완료"); 
					location.href = data.redirectUrl;
				}else {
					alert("게시글 삭제 실패");
				}
			}
   		});
	   }
	})
	
	
	//게시판 댓글
	$("#commentApply").on("click", function(){
		console.log("here")
		console.log($("#commentContent").val());
		$.ajax({
   			url:"/board/commentapply",
			type:"POST",
			data:{
				commentContent : $("#commentContent").val(),
				boardno : $("#boardno").text()
			},
			dataType:'json',
			success: function(data){
				if (data.msg=="ok") {
					$("#commentContent").val('');					
					$(".result").load("/board/comment?boardno="+$("#boardno").text());
				}else {
					alert("댓글 등록 실패");
				}
			}
   		});
	})

	
})
</script>

<%
String url = "";
if(detail.getVid_url()!=null){
	url = detail.getVid_url().split("v=")[1];
}
%>


<div class="wrap">
	<div class="articleTopbar">
		<span class="articleTitle"><%=detail.getTitle() %> </span>
		<span id="boardno" class="boardno"><%=detail.getBoardno() %></span>
	</div>
	<div class="articleUserId">
		<span class="boardCate"><%=detail.getBoardCate() %></span>
		 <label class="writer">작성자</label> <span class="userId"><%=detail.getUser_id() %></span>
		<span class="date"><%=detail.getWrittendate() %></span>
		<span class="hit">조회수 <%=detail.getHit() %></span>
	</div>
	<div class="content">
	<%if(detail.getVid_url()!=null){ %>
<%-- 	<iframe width="674" height="379" src="<%=detail.getVid_url()%>/k9_XH1YibcY"></iframe> --%>
	<div class="video">
	<iframe width="674" height="379" src="https://www.youtube.com/embed/<%=url%>"></iframe>
	</div>
	<%}%>
	<div class="contentText">
	<%=detail.getContent() %>
	</div>
	</div>
</div>

<div class="btnWrap">
	<button type="button" id="deleteBtn">삭제</button> 
	<a class="modifyLink" href="/board/modify?boardno=<%=detail.getBoardno() %>">수정</a>
</div>

<div class="commentWrap">
	<textarea id="commentContent" class="CommentContent" cols="120" rows="6" name="CommentContent" style="resize:none;"></textarea>
</div>
	<input id="commentApply" type="button" value="댓글 등록" />
<div class="result"></div>

