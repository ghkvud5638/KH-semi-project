<%@page import="board.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../BoardSidemenu.jsp" %>

<%	List<Board> list = (List<Board>) request.getAttribute("list"); %>

<style type="text/css">
.tableWrap{
	margin:-120px 0px 0px 494px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1504px;
}
.articleWrap > td{
	padding: 15px 71px 14px 84px;
}
.text-center{
	margin:69px 0px 0px 600px;
	width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: -172px 0 -35px -1395px;
    font-size: 19px;
    font-weight: bolder
}
.thWrap{
	border-top: 1px solid #ccc;
}
.thWrap > th{
	border-top: 1px solid #ccc;
	padding-top: 15px;
	padding-bottom: 15px;
}
.articleWrap{
	border-top: 1px solid rgb(202 196 196 / 50%);;
	padding-top: 10px;
}
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
<table>
<caption>커뮤니티 게시판</caption>
<tr class="thWrap">
	<th>글번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %>
<tr class="articleWrap">
	<td id="boardno"><%=list.get(i).getBoardno() %></td>
	<td><a class="link" href="/board/detail?boardno=<%=list.get(i).getBoardno() %>"> <%=list.get(i).getTitle() %></a></td>
	<td><%=list.get(i).getUser_id()%></td>
	<td id="hit"><%=list.get(i).getHit() %></td>
	<td><%=list.get(i).getWrittendate() %></td>
</tr>
<%	} %>
</table>
<jsp:include page="/WEB-INF/views/common/BoardListPaging.jsp" />
</div>
