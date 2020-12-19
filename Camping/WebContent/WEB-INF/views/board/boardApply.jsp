<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%@ include file="../header.jsp" %>
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
    /* background-color: rgb(97, 118, 138, .6); */
    width: 800px;
    height: 20px;
    padding: 12px 0;
    /* color: white; */
    border-bottom: 4px solid;
    font-size: 25px;
    font-weight: bold;
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
#vid{
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
	margin: 8px;
 	color: white; 
	cursor:pointer; 
	border-radius: 5px;

}

.button2 {
	width: 100px;
	padding: 5px 12px;
	border: none;
 	background-color: rgb(97, 118, 138, .6); 
	margin: 10px;
 	color: white; 
	cursor:pointer;
	border-radius: 5px;
	
}


/* button { */
/* 	padding: 5px 12px; */
/* 	background-color: white; */
/* 	border-width: 1px; */
/* } */
textarea {
	resize: none;
}
</style>
<script type="text/javascript">

$("upload").on('change',function (e) {
    var get_file = e.target.files;
    console.log(get_file)
})


</script>


    <div class="controller">
	<div id="wriTitle"><b>게시글 작성</b></div>
		<form action="/board/apply" method="post">
			<table>
				<tr>
					<td width="100px">카테고리</td> <!--카테고리 별로 데이터 삽입되게 업댓해야함  -->
					<td>
						<select name="category" id="category" >
							<option>community</option>
							<option>shopping</option>
							<option>assignment</option>
						</select>
					</td>
				</tr>
				<tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" required id="title" />
					</td>
				</tr>
				<tr>
					<td>동영상 url</td>
					<td><input type="text" name="vid" id="vid"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="12" cols="50" id="content"
							name="content" required></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="작성" class="button"></td>
					<td><input type="reset" value="내용 초기화" class="button"></td>
<!-- 					<td><input type='file' id='upload' name='upload'/></td> -->

				</tr>
			</table>
		</form>
		<div>
			<a href="/board/community">
				<button class="button2">게시판</button>
			</a>
			<a href="/myPage/main">
				<button class="button2">홈으로</button>
			</a>
		</div>
	</div>
	
<!-- 	<div id='preview'> -->
<!-- 		<iframe width="674" height="379" src="https://www.youtube.com/embed/9LlF5dYGMT8"></iframe> -->
<!--     </div> -->
