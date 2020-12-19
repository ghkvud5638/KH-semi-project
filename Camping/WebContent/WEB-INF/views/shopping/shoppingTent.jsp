<%@page import="shopping.dto.TB_PROD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- header.jsp 삽입하기 --%>
<%@ include file="../header.jsp" %>
<%@ include file="./sidemenu.jsp" %>
<% List<TB_PROD> tentList = (List<TB_PROD>)request.getAttribute("tentList"); %>

<section>
<article>
    <div class="product_board">
    	<ul class="product_board_list">
<% for(int i=0; i<tentList.size(); i++) { %> 
	<li class="product_board_content" style="width:200px; height: 250px">
		    	<div class="product_board_content_wrap_img">
		    		<a href="/shopping/detail?prodno=<%= tentList.get(i).getProd_id() %>" ><img src="<%= tentList.get(i).getProd_picturetitle() %>" style="width:200px; height:150px;"></a>
		    	</div>
		    	<h3 class="product_board_content-title">
		    		<a href="/shopping/detail?prodno=<%= tentList.get(i).getProd_id() %>" ><%= tentList.get(i).getProd_id() %></a>
		    	</h3>
		    	<p>
		    		<a href="/shopping/detail?prodno=<%= tentList.get(i).getProd_id() %>" ><%= tentList.get(i).getProd_name() %> </a>
		    	</p>
		    	<p>
		    		<a href="/shopping/detail?prodno=<%= tentList.get(i).getProd_id() %>" ><%= tentList.get(i).getProd_price() %>원</a>
		    	</p>
<% } %>



    	</ul>
    	
    	<ul ><li>
			<jsp:include page="/WEB-INF/views/shopping/paging/pagingT.jsp" />
		</li></ul>
    	
    
    </div> 

    </article>
</section>

<!-- Footer -->
<footer class="footer">
	 <p>뿌터뿌터</p>
</footer>
	    
</body>
</html>