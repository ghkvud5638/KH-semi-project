<%@page import="shopping.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../BoardSidemenu.jsp" %>
<% Board detail = (Board)request.getAttribute("reviewInfo"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
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
/*    border-left: 1px solid #ccc;  */
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
#modifyBtn{
    margin: 244px 0px -89px 30px;
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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
<div class="articleTopbar">
   <span class="articleTitle"><%=detail.getTitle() %> </span>
   <span id="boardno" class="boardno"><%=detail.getBoardno() %> </span>
</div>
<div class="articleUserId">
   <span class="boardCate">게시판 종류: <%=detail.getBoardCate() %></span>
   <span class="userId"> 작성자 : <%=detail.getUser_id() %></span>
   <span class="date"> 등록일 : <%=detail.getWrittendate() %></span>
   <span class="hit">조회수 <%=detail.getHit() %></span>
</div>
<div class="content"><%=detail.getContent() %></div>
</div>
</body>
</html>