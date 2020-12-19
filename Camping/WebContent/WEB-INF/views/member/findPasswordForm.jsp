<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
window.onload = function() {
	searchForm.onsubmit = function() {
		if(user_id.value == "" || user_name.value == "" || email.value == "" || phone.value == "") {
			alert("모든 정보를 입력해주세요.");
			return false;
		}
	}
}

</script>

<style type="text/css">

#searchForm{
    width:600px;
    margin-left:auto; 
    margin-right:auto;
    text-align: -webkit-center;
}
table{
    border:3px solid skyblue
}
td{
    border:1px solid skyblue
}
#title{
    background-color: skyblue
}

</style>


</head>
<body>

<section>
	<form id="searchForm" action="/member/find/password" method="POST">
	
	<div id="idsearch">
	<h3>비밀번호 찾기</h3>
	<hr>
	<table class="Form" style="margin:0 auto; width: min-content;">
	
	
		<tr>
			<td>아이디 : <input type="text" name="user_id" id="user_id" class="form-control" placeholder="아이디를 입력하세요"></td>
		</tr>
		<tr>
			<td>이름 : <input type="text" name="user_name" id="user_name" class="form-control" placeholder="이름을 입력하세요"></td>
		</tr>
		<tr>
			<td>이메일 : <input type="text" name="email" id="email" class="form-control" placeholder="이메일을 입력하세요"></td>
		</tr>
		<tr>
			<td>휴대폰 번호 : <input type="text" name="phone" id="phone" class="form-control" placeholder="휴대폰 번호를 입력하세요 (- 제외)"></td>
		</tr>
	</table>
	<br>
	<button id="searchBtn" class="btn" >검색하기</button>
	</div>
	</form>
	

</section>

</body>
</html>