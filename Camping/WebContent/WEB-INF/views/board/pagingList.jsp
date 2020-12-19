<%@page import="mypage.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	List<Board> list = (List<Board>) request.getAttribute("list"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> + 게시글 목록 + </title>
</head>
<body>

<h1>게시글 목록</h1>
<hr>

<table>
<tr>
	<th>글번호</th>
	<th>제목</th>
	<th>작성자 아이디</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr>
	<td><%=list.get(i).getBoardno() %></td>
	<td><%=list.get(i).getTitle() %></td>
	<td><%=list.get(i).getUser_id() %></td>
	<td><%=list.get(i).getHit() %></td>
	<td><%=list.get(i).getWrittendate() %></td>
</tr>
<%	} %>
</table>

<jsp:include page="/WEB-INF/views/common/paging.jsp" />

</body>
</html>