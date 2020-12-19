<%@page import="board.dto.TB_BOARD_COMMENT"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<% List<TB_BOARD_COMMENT> list = (List<TB_BOARD_COMMENT>) request.getAttribute("commentList"); %>
<style type="text/css">
.commentDiv{
    margin: 50px 0 0 515px;
    width: 800px;
}
.tdUserId{
	padding: 16px 0 8px 0;
	width: 100px;
	font-size: 18px;
}
.tdDate{
	padding: 3px 595px  0 12px;
    opacity: .5;
    font-size: 12px;
    float: right
}
.tdContent{
    padding: 5px 0px 17px 15px;
    border-bottom: 1px solid #EFEFEF;

}
#deletBtn{
    border: 0;
    outline: 0;
    cursor:pointer;
    float: right;
    margin: -32px 576px 0 0px;
    background-color: white;
}
</style>

<script type="text/javascript">
// $(document).ready(function(){
// // 	console.log("${loginid}")
// // 	console.log($(".tdUserId").text())
	
// 	if("${loginid}" != $(".tdUserId").text()){
// 		$("#deletBtn").hide();
// 	} 
// 	if ("${loginid}" == $(".tdUserId").text()) {
// 		$("#deletBtn").show();
// 	}
	
// })



//댓글 삭제
$('.deletBtn').on('click', (e) => { 
	
	console.log(e.target.value); 
	$.ajax({
		url:"/board/commentdelete",
		type:"POST",
		data:{
			commentId : e.target.value
// 			,boardno : $("#boardNo").attr("value")
		},
		dataType:'json',
		success: function(data){
			if (data.msg=="ok") {
				$(".result").load("/board/comment?boardno="+$("#boardNo").attr("value"))
				//comment.jsp에서 askDetail.jsp에 있는 요소의 class인 result를 선택할 수 있다.
				//이유는 askDetail.jsp에서 컨트롤러를 통해 Ajax방식으로 comment.jsp를 포워딩 했기 때문이다.
				//askDetail.jsp에 해당하는 html과, 그 html의 일부분인 commetn.jsp에 해당하는 html이 
				//브라우저가 해석할때는 하나의 html이 되기 때문이다
			}else {
				alert("댓글 삭제 실패");
			}
		}
		});
	})
</script>

<div class="commentDiv">
<%if(list != null){ %>
<%	for(int i=0; i<list.size(); i++) { %>
<div class="tdWrap">
	<strong class="tdUserId"><%=list.get(i).getUser_id()%></strong>
	<span class="tdDate"><%=list.get(i).getComment_date() %></span>
	<%if((list.get(i).getUser_id()).equals(session.getAttribute("loginid"))){ %>
		<button class="deletBtn" type="button" id="deletBtn"  value="<%=list.get(i).getComment_id()%>">X</button>
	<%} %>
	<input id="boardNo" type="hidden" value="<%=list.get(i).getBoardno()%>">
</div>
<div>	
	<p class="tdContent"><%=list.get(i).getContent() %></p>
</div>
<%} %>
<%} %>
</div>


