<%@page import="mypage.dto.TB_COMMENT"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<TB_COMMENT> list = (List<TB_COMMENT>) request.getAttribute("commentList"); %>


<div class="commentDiv">
<%if(list != null){ %>
<%	for(int i=0; i<list.size(); i++) { %>
<div class="tdWrap">
	<strong class="tdUserId"><%=list.get(i).getUser_id() %></strong>
	<p class="tdDate"><%=list.get(i).getComment_date() %></p>
</div>
<div>	
	<p class="tdContent"><%=list.get(i).getContent() %></p>
</div>
<%} %>
<%} %>

</div>