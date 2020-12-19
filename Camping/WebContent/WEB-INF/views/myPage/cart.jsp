<%@page import="mypage.dto.TB_CART"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>


<%List<TB_CART> list = (List<TB_CART>) request.getAttribute("list"); %>

<style type="text/css">
.tableWrap{
	margin:-100px 0px 0px 494px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1504px;
}
.articleWrap > td{
	padding: 15px 0px 14px 84px;
}
.text-center{
	margin:69px 0px 0px 600px;
	width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: 0 0 -35px -1395px;
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
.prodImg{
	width:100px;
}
.prodname{
	
}
</style>


<script type="text/javascript">
$(document).ready(function(){
	
	$("#deleteBtn").on("click",function(){
		console.log("here")
		$.each($("input:checkbox[id='chk']:checked"), function(item) {
	   		console.log($(this).val()); // 선택된 체크박스의 value
	   		$.ajax({
	   			url:"/myPage/deleteCart",
				type:"POST",
				data:{
					cartId : $(this).val() // 여러개 체크 했을 경우 - 컨트롤러 여러번 감..
				},
				dataType:'json',
				success: function(data){
// 					성공
					if (data.msg=="ok") {
// 						alert("게시글 삭제 완료"); -> 지운 게시글 개수만큼 여러번뜸 , 고칠방법이 뭐가 있을까
						location.href = data.redirectUrl;
					}else {
						alert("게시글 삭제 실패");
					}
				}
	   		});
		}); 
	})
})
</script>

<div class="tableWrap">
<table>
<caption>장바구니</caption>
<tr class="thWrap">
	<th>선택</th>
	<th>상품 이미지</th>
	<th>장바구니 번호</th>
	<th>상품 ID</th>
	<th>상품 명</th>
	<th>수량</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr class="articleWrap">
	<td><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getCart_id() %>"></td>
	<td><a href="/shopping/detail?prodno=<%=list.get(i).getProd_id() %>"><img  class="prodImg" alt="준비중" src="<%=list.get(i).getImg_path() %>"></a></td>
	<td id="cartId"><%=list.get(i).getCart_id() %></td>
	<td><%=list.get(i).getProd_id() %></td>
	<td class="prodname"><%=list.get(i).getProd_name()%></td>
	<td><%=list.get(i).getCnt() %></td>
</tr>
<%	} %>
</table>

<button type="button" id="deleteBtn">삭제</button>

<jsp:include page="/WEB-INF/views/common/CartPaging.jsp" />
</div>

