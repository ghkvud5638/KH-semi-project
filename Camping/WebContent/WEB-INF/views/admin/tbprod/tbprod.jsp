<%@page import="admin.dto.TB_PROD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%	List<TB_PROD> list = (List<TB_PROD>) request.getAttribute("list"); %>


<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type="text/css">
.tableWrap{
	margin:0px 0px 0px 394px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1004px;
}
.articleWrap > td{
	padding: 15px 71px 14px 84px;
/* 	border: 1px solid #ccc; */
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
		font-size: 15px;
	font-weight: bolder;
	text-align: center;
}

#allchk{
/* 	border-top: 1px solid #ccc; */
	padding-top: 15px;
	padding-bottom: 15px;
		font-size: 15px;
	font-weight: bolder;
	text-align: center;
}

.articleWrap{
	border-top: 1px solid rgb(202 196 196 / 50%);;
	padding-top: 10px;
}

.articleWrap td{
 vertical-align: middle;
 text-align: center;
 font-size: 15px;
 }

.btn{
 	width:100px;

    padding: 5px 0;
    text-align: center; 
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
	font-weight: bolder;
    border-radius: 4px;
    background-color: #f0e8df;
        
}
.search{
  	margin: 200px 0px -200px 100px;   

}
#cate_id{
	width: 150px;
	height: 35px;
	font-size: 15px;
	font-weight: bolder;
	text-align: center;
	background-color: #f0e8df;
	cursor: pointer;
	
}
#prod_search {
	width: 450px;
	height: 35px;
	font-size: 15px;
/* 	background-color: #f0e8df; */

}

#chk {
	width: 20px;
	height: 20px;
 	margin: 0px 0px 0px -70px; 
}

.btnfloat{
 	margin: 100px 0px 0px 800px; 

}

#allchk:hover{
	color: #ccc;
	cursor:pointer;
}

#prodPick{
	margin: 0px 0px 0px -55px;
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
// 						alert("게시글 삭제 완료"); //-> 지운 게시글 개수만큼 여러번뜸 , 고칠방법이 뭐가 있을까
						location.href = data.redirectUrl;
					}else {
// 						alert("게시글 삭제 실패");
					}
				}
	   		});

		}); 
	})
})


</script>

<%@ include file="../adminProdManage.jsp" %>


<!-- <title> + 나의 게시글 목록 + </title> -->
<div class="tableWrap">
<table>
<caption>상품 목록</caption>
<tr class="thWrap">
	<th width="100px"><span id="allchk">전체 선택</span></th>
	<th width="150px">상품 사진</th>
	<th width="325px">상품 이름</th>
	<th>상품 가격</th>
	<th>상품 수량</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr class="articleWrap">
	<td width="100"><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getProd_id() %>"></td>
	<td ><img src="<%=list.get(i).getProd_picturetitle() %>" width=100px; height=100px; id="prodPick"></td>
	<td><a href="prodpage?prod_id=<%= list.get(i).getProd_id() %>">
	<span style="font-size: 15px;"><%=list.get(i).getProd_name() %></span></a></td>
	<td><%=list.get(i).getProd_price()%>원</td>
	<td><%=list.get(i).getProd_num() %>개</td>
</tr>
<%	} %>
</table>
<div class="btnfloat">
<a href="/admin/prodinsert"><button type="button" class="btn">등록</button></a>
<button type="button" id="deleteBtn" class="btn">삭제</button>
</div>

<form action="/admin/prodlist" method="GET">
<div class="search">
				<select name="cate" id="cate_id" >
							<option value="all">전체 선택</option>
							<option value="tent">텐트</option>
							<option value="sleepingbag">침낭</option>
							<option value="tablechair">의자</option>
							<option value="etc">기타</option>
						</select>
<input type="text" name="search" id="prod_search">

<button type="submit" class="btn">검색</button>
</div>
</form>

</div>
<jsp:include page="/WEB-INF/views/common/tbpaging.jsp" />


<%@ include file="../../footer.jsp" %>
