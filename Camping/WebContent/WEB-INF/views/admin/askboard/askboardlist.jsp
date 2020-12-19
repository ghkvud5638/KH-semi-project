<%@page import="admin.dto.TB_ASK_BOARD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%	List<TB_ASK_BOARD> list = (List<TB_ASK_BOARD>) request.getAttribute("list"); %>


<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type="text/css">
.tableWrap{
	margin:-30px 0px 0px 394px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1004px;
}
.articleWrap > td{
	padding: 15px 0px 14px 0px;
}
.text-center{
	margin:69px 0px 0px 600px;
	width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: 0 0 -35px -895px;
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
	text-align: center;
}
.articleWrap{
	border-top: 1px solid rgb(202 196 196 / 50%);;
	padding-top: 10px;
	text-align: center;
	vertical-align: center;
	
}

.deleteBtn{
 	margin: 78px 0px -89px 0px;
 	width:50px;
/*     background-color: rgb(97, 118, 138, .6); */
/*     border: none; */
/*     color:#fff; */
/*     padding: 15px 0; */
/*     text-align: center; */
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
        border-radius: 4px;
}
.insertBtn{
 	margin: 78px 0px -89px 10px;
 	width:50px;
/*     background-color: rgb(97, 118, 138, .6); */
/*     border: none; */
/*     color:#fff; */
/*     padding: 15px 0; */
/*     text-align: center; */
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
        border-radius: 4px;
}
.search{
	margin: 200px 0px -200px 650px; 


}
.searchBtn{
 	margin: 78px 0px -89px 10px;
 	width:50px;
/*     background-color: rgb(97, 118, 138, .6); */
/*     border: none; */
/*     color:#fff; */
/*     padding: 15px 0; */
/*     text-align: center; */
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
        border-radius: 4px;
}

#allchk{
/* 	border-top: 1px solid #ccc; */
	padding-top: 15px;
	padding-bottom: 15px;
		font-size: 15px;
	font-weight: bolder;
	text-align: center;
}
#allchk:hover{
	color: #ccc;
	cursor:pointer;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	var chk = false;
	$('#allchk').on('click', function(){
		if(chk == false) { 
			chk = true;
			$("input[name='deleteChkBox']").prop('checked', true);
           }
        else {
			chk = false;
           $("input[name='deleteChkBox']").prop('checked', false);
        }	
	});
	

	$("#deleteBtn").on("click",function(){
		console.log("here")
		$.each($("input:checkbox[id='chk']:checked"), function(item) {
	   		console.log($(this).val()); // 선택된 체크박스의 value
	   		$.ajax({
	   			url:"/admin/prodremove",
				type:"POST",
				data:{
					prod_id : $(this).val() // 여러개 체크 했을 경우 - 컨트롤러 여러번 감..
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

<%@ include file="../adminBoardManage.jsp" %>


<!-- <title> + 나의 게시글 목록 + </title> -->
<div class="tableWrap">
<table>
<caption>1:1문의 확인</caption>
<tr class="thWrap">
	<th width="100px"><span id="allchk">전체 선택</span></th>

	<th>제목</th>
	<th>작성자</th>
	<th>작성 날짜</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr class="articleWrap">
	<td><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getBoardno() %>"></td>
	<td style="text-align: left; padding-left: 30px;"><a href="/myPage/askdetail?boardno=<%= list.get(i).getBoardno() %>"><%=list.get(i).getTitle() %></a></td>
	<td><%=list.get(i).getUser_id()%></td>
	<td><%=list.get(i).getWrittendate() %></td>
</tr>
<%	} %>
</table>

<form action="/admin/prodlist" method="GET"><!-- 유저 조회  만들면 수정할 것-->
<div class="search">
<button type="button" id="deleteBtn" class="deletebtn">삭제</button>
<a href="/admin/prodinsert"><button type="button" class="insertbtn">등록</button></a>
				<select name="cate" id="cate_id" >
							<option value="all">선택 없음</option>
							<option value="tent">텐트</option>
							<option value="sleepingbag">침낭</option>
							<option value="tablechair">의자</option>
							<option value="etc">기타</option>
						</select>
<input type="text" name="search" id="prod_search" >

<button type="submit" class="searchBtn">검색</button>
</div>
</form>

<jsp:include page="/WEB-INF/views/common/tbpaging.jsp" />
</div>


<%@ include file="../../footer.jsp" %>
