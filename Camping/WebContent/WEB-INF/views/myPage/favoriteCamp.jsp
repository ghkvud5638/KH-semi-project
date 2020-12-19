<%@page import="java.util.List"%>
<%@page import="mypage.dto.CAMP_INFO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../sidemenu.jsp" %>

<%-- header.jsp 삽입하기 --%>
<%@ include file="../header.jsp" %>
<!-- <link href="../../../css/board1.css" rel="stylesheet" type="text/css"> -->

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<% List<CAMP_INFO> list = (List<CAMP_INFO>) request.getAttribute("camplist"); %>

<style type="text/css">
#deleteBtn{
 	margin: 244px 0px -89px 50px;
 	width:100px;
background-color: rgb(97, 118, 138, .6);
    border: none;
color:#fff; 
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
</style>
<% 
	int randomNum = (int)(Math.random()*20)+1;
%>
<script type="text/javascript">
$(document).ready(function(){
	$("#deleteBtn").on("click",function(){
		console.log("here")
		$.each($("input:checkbox[id='chk']:checked"), function(item) {
	   		console.log($(this).val()); // 선택된 체크박스의 value
	   		$.ajax({
	   			url:"/myPage/FavCampDelete",
				type:"POST",
				data:{
					campId : $(this).val()
				},
				dataType:'json',
				success: function(data){
					if (data.msg=="ok") {
						location.href = data.redirectUrl;
					}else {
						alert("찜한 캠핑장 삭제 실패");
					}
				}
	   		});
		}); 
	})
})
</script>
    <div class="product_board">
    	<ul class="product_board_list">
    	<%for(int i=0; i<list.size(); i++) {%>
	    	<li class="product_board_content">
		    	<div class="product_board_content_wrap_img">
		    		<a href="/camp/detail?camp_id=<%=list.get(i).getCamp_id()%>&j=<%=randomNum%>"><img src="<%=list.get(i).getPicture() %>" style="width:200px; height: 150px;"></a>
		    	</div>
		    	
	    		<h3 class="product_board_content-title">
                	<a href="/camp/detail?camp_id=<%=list.get(i).getCamp_id()%>&j=<%=randomNum%>"><%=list.get(i).getCamp_name()%></a>
                </h3>
                <p class="card-text"> <%=list.get(i).getTel() %> </p>
                <input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getCamp_id()%>">
                <!-- 체크 박스 삭제 처리 해야함. -->
	    	</li>
	    <%	} %>
    	</ul>
    	    <button type="button" id="deleteBtn">삭제</button> 
    </div>
