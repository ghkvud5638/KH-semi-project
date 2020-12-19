<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String prodid=(String)request.getParameter("prodid");%>

<style>
* {
	margin: 4px 0;
}
.controller {
	padding: 25px 0;
	margin: auto;
	width: 840px;
	margin-top: 171px;
}
#wriTitle{
	text-align: center;
	background-color: rgb(97, 118, 138, .6);
	width: 800px;
	height: 20px;
	padding: 12px 0;
	color: white;
}
table {
	width: 840px;
	margin: 25px 0;
	padding: 20px;
	border-collapse: collapse;
}
#category {
	width: 100px;
	height: 30px;
}
#title {
	width: 654px;
	height: 24px;
}
textarea {
	width: 800px;
	height: 400px;
}
.button {
	width: 100px;
	padding: 5px 12px;
	border: none;
	background-color: rgb(97, 118, 138, .6);
	margin: 22px;
	color: white;
}
button {
	padding: 5px 12px;
	background-color: white;
	border-width: 1px;
}
textarea {
	resize: none;
}
</style>


    <div class="controller">
	<div id="wriTitle"><b>리뷰 게시글 작성</b></div>
		<form action="/shopping/review" method="post">
			<table>
				<tr>
					<td width="100px">카테고리</td> <!--카테고리 별로 데이터 삽입되게 업댓해야함  -->
					<td>
						<select name="category" id="category" >
							<option>review</option>
						</select>
					</td>
					<td><input type="hidden" name="prodid" value="<%=prodid%>"></td>
				</tr>
				<tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" required id="title" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="12" cols="50"
							name="content" required></textarea></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="작성" class="button"></td>
					<td><input type="reset" value="내용 초기화" class="button"></td>
				</tr>
			</table>
		</form>
		<div>
			<a href="#">
				<button>게시판</button>
			</a>
			<a href="/myPage/main">
				<button>홈으로</button>
			</a>
		</div>
	</div>
    