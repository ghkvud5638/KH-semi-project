<%@page import="admin.dto.TB_PROD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%@ include file="../header.jsp" %>
<%-- <%@ include file="adminsidemenu.jsp" %> --%>

<style type="text/css">

td.board:hover a{
	
	color:#25a5f0;

}

.tableWrap{
	margin:120px 0px 0px 200px
}
.tableWrap > table{
	table-layout: fixed;
	width: 1000px;
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
    margin: 0 0 -35px -900px;
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
.Btn{
 	margin: 78px 0px -89px 74px;
 	width:100px;
/*     background-color: rgb(97, 118, 138, .6); */
    border: none;
/*     color:#fff; */
    padding: 15px 0;
/*     text-align: center; */
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
        border-radius: 4px;
}



</style>

<script type="text/javascript">

</script>

<!-- <title> + 나의 게시글 목록 + </title> -->
<div class="tableWrap">
<table>
<caption>관리자페이지</caption>
<tr class="thWrap">
	<th width="300">게시판 이름</th>
	<th width="700">게시판 설명</th>
	
</tr>
<tr class="articleWrap">
	<td class="board"><a href="/admin/ask">회원 관리</a></td>
	<td>회원 관리하는 페이지로 이동합니다</td>
</tr>
<tr class="articleWrap">
	<td class="board"><a href="#">캠핑장 관리</a></td>
	<td>캠핑장 매출 및 예약현황을 확인할 수 있습니다</td>
</tr>
<tr class="articleWrap">
	<td class="board"><a href="/admin/prodmanager">쇼핑몰 관리</a></td>
	<td>쇼핑몰 매출 및 상품을 등록/수정/삭제 할 수 있습니다</td>
</tr>
<tr class="articleWrap">
	<td class="board"><a href="#">게시판 관리</a></td>
	<td>게시판을 확인하여 게시글을 등록/수정/삭제 할 수 있습니다</td>
</tr>
<tr class="articleWrap">
	<td class="board"><a href="#">1:1 문의 관리</a></td>
	<td>유저의 1:1문의를 확인하여  답변할 수 있습니다</td>
</tr>
</table>


</div>

<%@ include file="../footer.jsp" %>
