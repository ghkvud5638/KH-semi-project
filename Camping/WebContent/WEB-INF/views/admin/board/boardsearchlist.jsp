<%@page import="board.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<%	List<Board> list = (List<Board>) request.getAttribute("list"); %>

<style type="text/css">
.tableWrap{
	margin:190px 0px 0px 394px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1004px;
}
.articleWrap > td{
	padding: 15px 0px 14px 0px;
	text-align: center;
}
.text-center{
	margin:69px 0px 0px 600px;
	width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: -172px 0 -35px -895px;
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
.btn{
 	width:100px;

    padding: 5px 0;
    text-align: center; 
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
	font-weight: bolder;
    border-radius: 4px;
    background-color: #f0e8df;
        
}
.search{
  	margin: 200px 0px -200px 100px;   

}
#prod_search {
	width: 450px;
	height: 35px;
	font-size: 15px;
/* 	background-color: #f0e8df; */

}

#cate_id{
	width: 150px;
	height: 35px;
	font-size: 15px;
	font-weight: bolder;
	text-align: center;
	background-color: #f0e8df;
	cursor: pointer;
	
}

.link:hover{
	text-decoration:underline;
}

</style>

<%@ include file="../adminBoardManage.jsp" %>


<div class="tableWrap">
<table>
<caption>전체 게시판</caption>
<tr class="thWrap">
	
	<th width="100">글번호</th>
	<th width="100">카테고리</th>
	<th width="300">제목</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %>
<tr class="articleWrap">
	<td id="boardno"><%=list.get(i).getBoardno() %></td>
	<td id="category"><%=list.get(i).getBoardCate() %></td>
	<td style="text-align:left; padding-left: 30px;"><a class="link" href="/board/detail?boardno=<%=list.get(i).getBoardno() %>"> <%=list.get(i).getTitle() %></a></td>
	<td><%=list.get(i).getUser_id()%></td>
	<td id="hit"><%=list.get(i).getHit() %></td>
	<td><%=list.get(i).getWrittendate() %></td>
</tr>
<%	} %>
</table>

<form action="/admin/boardlist" method="GET">
<div class="search">
				<select name="cate" id="cate_id" >
							<option value="all">전체 선택</option>
							<option value="community">community</option>
							<option value="shopping">shopping</option>
							<option value="assignment">assignment</option>
						</select>
<input type="text" name="search" id="prod_search" >

<button type="submit" class="btn">검색</button>
</div>
</form>

</div>
<jsp:include page="/WEB-INF/views/common/BoardListSearchAdminPaging.jsp" />


<%@ include file="../../footer.jsp" %>

