<%@page import="board.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%	List<Board> list = (List<Board>) request.getAttribute("list"); %>

<style type="text/css">
/* .tableWrap{ */
/* /* 	margin:-120px 0px 0px 494px */ */
/* } */
/* .tableWrap > table{ */
/* 	table-layout: fixed; */
/* 	width: 1504px; */
/* } */
/* .articleWrap > td{ */
/* 	padding: 15px 71px 14px 84px; */
/* } */
/* .text-center{ */
/* 	margin:69px 0px 0px 600px; */
/* 	width: 600px; */
/* } */
/* caption{ */
/*     padding-bottom: 58px; */
/*     margin: -172px 0 -35px -1395px; */
/*     font-size: 19px; */
/*     font-weight: bolder */
/* } */
/* .thWrap{ */
/* 	border-top: 1px solid #ccc; */
/* } */
/* .thWrap > th{ */
/* 	border-top: 1px solid #ccc; */
/* 	padding-top: 15px; */
/* 	padding-bottom: 15px; */
/* } */
/* .articleWrap{ */
/* 	border-top: 1px solid rgb(202 196 196 / 50%);; */
/* 	padding-top: 10px; */
/* } */
#deleteBtn{
 	margin: 78px 0px -89px 74px;
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

.link:hover{
	text-decoration:underline;
}

</style>
<div class="tableWrap">

<% int size = list.size(); %>
<% if (size > 5) {%>
<% size = 5; } else{}%>
<%	for(int i=0; i<size; i++) { %>
	<p>
<%-- 	<%=list.get(i).getBoardno() %> --%>
	<a class="link" href="/board/detail?boardno=<%=list.get(i).getBoardno() %>"> <%=list.get(i).getTitle() %></a>
<%-- 	<%=list.get(i).getUser_id()%> --%>
<%-- 	<%=list.get(i).getHit() %> --%>
</p>
<%	} %>
</div>


