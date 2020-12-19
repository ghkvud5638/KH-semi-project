<%@page import="mypage.dto.TB_ASK_BOARD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%	List<TB_ASK_BOARD> list = (List<TB_ASK_BOARD>) request.getAttribute("list"); %>
<style type="text/css">
.wrap{
	border-top:3px solid black;
	width: 1503px;
	height: 500px;
	margin: -95px 0 0 400px;
}
.title{
	margin: -135px 0 0 400px;
    position: absolute;
    font-size: 28px;
    font-weight: bolder;
}
.articleTitleWrap{
	margin: 30px 0 30px 0px;
}
label{
	margin: 0 25px 0px 0px;
	font-weight: bolder;
}
.txtarea{
	margin: 0 0 0 55px;;
}
.contentTitle{
	position: absolute;
    top: 405px;
}
#applyBtn{
 	width: 140px;
    height: 46px;
    background: #fcfcfc;
    border: 2px solid #1e1e1f;
    margin: -95px 0 0 700px;
    float: right;
    position: absolute;
	left: 1000px;
    top: 767px;
    font-size: 15px;
    
}
.askBoardWrap{
	border-top: 3px solid black;
    width: 1503px;
    height: 100px;
	margin:70px 0px 0px 400px;
}
.askBoardTitle
{
	margin: 24px 0 0 400px;
    position: absolute;
    font-size: 28px;
    font-weight: bolder;
}



.askBoardWrap{
}
.askBoardWrap > table{
	table-layout: fixed;
	width: 1504px;
	margin: 3px 0 0 0;
}
/* .articleWrap > td{ */
/* 	padding: 15px 71px 14px 84px; */
/* } */
.text-center{
	margin:60px 0px 0px 600px;
	width: 600px;
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
.articleWrap > td {
	padding: 10px 0 10px 0;
	text-align: center;
}
#deleteBtn{
	width: 140px;
    height: 46px;
    background: #fcfcfc;
    border: 2px solid #1e1e1f;
    margin: -90px 0 0 0;
    float: right;
    position: absolute;
	left: 1700px;
    top: 1225px;
    font-size: 15px;
}
.link:hover{
	text-decoration:underline;
}
h1 {
	font-size: 25px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#deleteBtn").on("click", function(){
		console.log("here")
		if (confirm("1:1문의 게시글을 삭제합니다.")==true) {
		$.each($("input:checkbox[id='chk']:checked"), function(item) {
	   		console.log($(this).val());
	   		$.ajax({
	   			url:"/myPage/askdelete",
				type:"POST",
				data:{
					boardno : $(this).val()
				},
				dataType:'json',
				success: function(data){
					if (data.msg=="ok") {
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
<div class="title"><h1>1:1 문의</h1></div>
<div class="wrap">
	<form action="/myPage/askarticleapply" method="post">
		<div class="articleTitleWrap">
		 <label>제목</label><input type="text" name="title" size=50 placeholder="제목을 입력해주세요"/>
		   <select name="category" id="category" >
			<option>community</option>
			<option>shopping</option>
			<option>assignment</option>
		  </select>
		</div>
		<div class="articleContentWrap">
		 <label class="contentTitle">내용</label><textarea name="content" class="txtarea" cols="150" rows="20" placeholder="내용을 입력해주세요"></textarea>
		</div>
		
		<div class="btnWrap">
		 <input type="submit" id="applyBtn" value="등록"/> 
		</div>
	</form>	
</div>
<div class="askBoardTitle"><h1>내가 작성한 1:1 문의 게시글</h1></div>
<div class="askBoardWrap">
	<table>
		<tr class="thWrap">
			<th>선택</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>카테고리</th>
			<th>작성일</th>
		</tr>
		<%	for(int i=0; i<list.size(); i++) { %>
		<tr class="articleWrap">
			<td><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getBoardno() %>"></td>
			<td id="boardno"><%=list.get(i).getBoardno() %></td>
			<td><a class="link" href="/myPage/askdetail?boardno=<%=list.get(i).getBoardno()%>"><%=list.get(i).getTitle()%></a></td>
			<td><%=list.get(i).getUser_id()%></td>
			<td><%=list.get(i).getBoard_cate()%></td>
			<td><%=list.get(i).getWrittendate() %></td>
		</tr>
		<%	} %>
	</table>
	<button type="button" id="deleteBtn">삭제</button>
<%-- 	<jsp:include page="/WEB-INF/views/common/AskBoardListPaging.jsp" /> --%>
</div>
