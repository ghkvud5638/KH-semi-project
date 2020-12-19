<%@page import="board.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<%@ include file="../header.jsp" %>
<%@ include file="../BoardSidemenu.jsp" %>s

<% Board detail = (Board)request.getAttribute("board"); %>



<style type="text/css">
.wrap{
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	margin: -291px 0 0 515px;
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
	margin: 0px 55px 0px 55px;
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
    font-size: 20px;
}
.btnWrap{
	margin: 36px 0 0 514px;
}
</style>
<script type="text/javascript">
</script>
<form action="/board/modify" method="post">
<div class="wrap">
<div class="articleTopbar">
	<input name="title" class="articleTitle" value="<%=detail.getTitle() %>"/>
	<input name="boardno" class="boardno" value="<%=detail.getBoardno() %>" readonly style="border:none" >
</div>
<div class="articleUserId">
	<span class="boardCate"><%=detail.getBoardCate() %></span>
	 <label>작성자</label> <span class="userId"><%=detail.getUser_id() %></span>
	<span class="date"><%=detail.getWrittendate() %></span>
	<span class="hit">조회수 <%=detail.getHit() %></span>
</div>
<textarea class="content" name="content" rows="22" cols="100" ><%=detail.getContent() %></textarea>
</div>

<div class="btnWrap">
	<input type="submit" id="modifyBtn" value="수정 완료"/> 
</div>

</form>





