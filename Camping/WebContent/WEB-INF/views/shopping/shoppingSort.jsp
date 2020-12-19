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


<section>
<article>
    <div class="product_board" style="height: 926px;">
    <div class="price_order" style="margin-left: 60px;">
       <input id = "searchAsc" type="button" value="낮은가격순" name="ASC"> <input id="searchDesc" type="button" value="높은가격순" name="DESC">
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
    <div>
    	<jsp:include page="/WEB-INF/views/common/ProdListPaging.jsp" />
    </div>     
    </article>
</section>
<%-- footer.jsp 삽입하기 --%>

<%@ include file="../footer.jsp" %>
