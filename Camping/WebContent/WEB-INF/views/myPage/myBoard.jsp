<%@page import="mypage.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%	List<Board> list = (List<Board>) request.getAttribute("list"); %>

<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>

<style type="text/css">
.tableWrap{
	margin:-130px 0px 0px 370px;
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
    margin: 0 -50px -10px -1395px;
    font-size: 30px;
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
    width: 116px;
    height: 43px;
    background: #fcfcfc;
    border: 2px solid #1e1e1f;
    margin: 80px 0 0 0;
    float: right;
    position: absolute;
    left: 1700px;
    top: 875px;
    font-size: 15px;
    cursor: pointer;
}
.titleLink:hover{
	text-decoration: underline;
}

</style>
<script type="text/javascript">
$(document).ready(function(){

	
	$("#deleteBtn").on("click",function(){
		
	if (confirm("게시글을 삭제합니다.") == true) {
		console.log("here")
		$.each($("input:checkbox[id='chk']:checked"), function(item) {
	   		console.log($(this).val()); // 선택된 체크박스의 value
	   		$.ajax({
	   			url:"/myPage/deleteArticle",
				type:"POST",
				data:{
					boardno : $(this).val() // 여러개 체크 했을 경우 - 컨트롤러 여러번 감..
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
	  }
	})
})
</script>
<!-- <title> + 나의 게시글 목록 + </title> -->
<div class="tableWrap">
<table>
<caption>작성한 게시글</caption>
<tr class="thWrap">
	<th>선택</th>
	<th>글번호</th>
	<th>제목</th>
	<th>카테고리</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %> 
<tr class="articleWrap">
	<td><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getBoardno() %>"></td>
	<td id="boardno"><%=list.get(i).getBoardno() %></td>
	
	
	<td><a class="titleLink" href="/board/detail?boardno=<%=list.get(i).getBoardno() %>"><%=list.get(i).getTitle() %></a></td>
	<td><%=list.get(i).getBoardCate()%></td>
	<td><%=list.get(i).getHit() %></td>
	<td><%=list.get(i).getWrittendate() %></td>
</tr>
<%	} %>
</table>

<button type="button" id="deleteBtn">삭제</button>
<%-- <jsp:include page="/WEB-INF/views/common/paging.jsp" /> --%>
</div>

<%@ include file="../footer.jsp" %>
