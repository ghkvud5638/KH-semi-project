<%@page import="shopping.dto.TB_PROD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../BoardSidemenu.jsp" %>
<% List<TB_PROD> list = (List<TB_PROD>) request.getAttribute("prodlist"); %>
<script type="text/javascript">
$(document).ready(function(){

})
</script>
<style type="text/css">
.sortForm{
	display: flex;
}
.sortBtn{
	margin: 0px 20px 0px 0px;
	border :0;
	outline: 0;
	background-color:white;
}
.sortBtn:hover {
	
	color: #25a5f0;
	border-bottom: 1px solid #25a5f0;
	cursor:pointer;
}
</style>

<section>
<article>

    <div class="product_board" style="height: 926px;">
	    
	    <div class="sortForm" style="margin-left: 60px;">
	    <form action="/shopping/sortSearch" method="get">
	       <input class="sortBtn" id = "searchAsc" type="submit" value="낮은가격순" name="ASC">
	       <input name="sort" type="hidden" value="ASC"/> 
	    </form>
	    
	    <form action="/shopping/sortSearch" method="get">
	    	<input class="sortBtn" id="searchDesc" type="submit" value="높은가격순" name="DESC">
	    	<input name="sort" type="hidden" value="DESC"/>
	    </form>
	    </div>
	    
    	<ul class="product_board_list">		
    	<%for(int i=0; i<list.size(); i++) {%>
	    	<li class="product_board_content">
		    	<div class="product_board_content_wrap_img">
		    		<a href="/shopping/detail?prodno=<%=list.get(i).getProd_id() %>">
		    			<img src="<%=list.get(i).getProd_picturetitle() %>" onclick="click()" style="width:200px; height: 150px;">
		    		</a>
		    	</div>
	    		<h3 class="product_board_content-title">
                	<a href="#"><%=list.get(i).getProd_name()%></a>
                </h3>
                <p class="card-text"> <%=list.get(i).getProd_price() %> </p>
<!--                 <input class="chk" type="checkbox" name="chk"> -->
	    	</li>
	    <%	} %>
    	</ul>
    </div>
    
    <%if(request.getAttribute("sorted") !=null){ %>
        <jsp:include page="/WEB-INF/views/common/ProdSortedListPaging.jsp" />    
    <%} else{ %>
        <jsp:include page="/WEB-INF/views/common/ProdListPaging.jsp" />
    <%} %>
<!--     </div> -->
    </article>
</section>
<%-- footer.jsp 삽입하기 --%>

<%@ include file="../footer.jsp" %>
