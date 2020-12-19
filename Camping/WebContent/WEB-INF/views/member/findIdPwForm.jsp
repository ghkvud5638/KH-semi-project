<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<style type="text/css">

div {
	font-family: 'Oswald', 'Noto Sans KR', sans-serif;
}

h5 {
    display: block;
    font-size: 0.83em;
    margin-block-start: 1.67em;
    margin-block-end: 1.67em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
}

legend {
    display: block;
    padding-inline-start: 2px;
    padding-inline-end: 2px;
    border-width: initial;
    border-style: none;
    border-color: initial;
    border-image: initial;
}

label {
    cursor: default;
}

h2.tit_IdPw {
    font-size: 40px;
    text-align: left;
    color: #1e1e1f;
    letter-spacing: 2px;
}


.find_wrapper {
    border-top: 1px solid #1e1e1f;
    margin-top: 38px;
    width: 1280px;
}

.top_wrap {
    width: 1280px;
    height: 430px;
    border-bottom: 1px solid #c7c7c7;
    position: relative;
}

.id_find {
    float: left;
    width: 440px;
    height: 205px;
}


.id_find h5 {
    position: absolute;
    top: 45px;
    left: 166px;
}

.top_wrap h5 {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 16px;
}

.top_wrap input {
    width: 403px;
    height: 40px;
    border: 1px solid #c7c7c7;
    padding-left: 10px;
    margin: 5px 0;
}

form {
    display: block;
    margin-top: 0em;
}

.find_wrapper fieldset {
    border: none;
    margin: 0;
    padding: 0;
}

.fly {
    position: absolute;
    left: -99999em;
    top: -99999em;
}

.id_find .id_name {
    position: absolute;
    top: 97px;
    left: 166px;
}

.id_find .id_mail {
    position: absolute;
    top: 152px;
    left: 166px;
}

.id_find .phone {
    position: absolute;
    top: 207px;
    left: 166px;
}

.find_wrapper input {
    margin: 0;
}

.find_wrapper .idfind_Btn {
    width: 170px;
    height: 41px;
    background: #fcfcfc;
    border: 2px solid #1e1e1f;
    margin: 0;
    float: right;
    position: absolute;
    left: 283px;
    top: 270px;
}

.find_wrapper button {
    padding: 0;
    margin: 0;
    border: none;
    background: none;
}

.find_wrapper .idfind_Btn span {
    font-family: oswald;
    font-size: 15px;
}

button {
    appearance: button;
    -webkit-writing-mode: horizontal-tb !important;
    text-rendering: auto;
    color: -internal-light-dark(black, white);
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: center;
    align-items: flex-start;
    cursor: default;
    background-color: -internal-light-dark(rgb(239, 239, 239), rgb(59, 59, 59));
    box-sizing: border-box;
    margin: 0em;
    font: 400 13.3333px Arial;
    padding: 1px 6px;
    border-width: 2px;
    border-style: outset;
    border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
    border-image: initial;
}


.find_border {
    width: 15px;
    height: 205px;
    border-right: 1px solid #c7c7c7;
    position: absolute;
    top: 45px;
    left: 641px;
}

/* 패스워드 찾기 */

.pw_find {
    float: left;
    width: 440px;
    height: 280px;
    padding-right: 50px;
}

.pw_find h5 {
   	position: absolute;
    top: 45px;
    left: 709px;
}

.pw_find .pw_name {
   	width: 195px;
    height: 40px;
    position: absolute;
    top: 97px;
    left: 709px;
}

.pw_find .pw_id {
    width: 190px;
    height: 40px;
    position: absolute;
    top: 97px;
    left: 921.5px;
}

.pw_find .pw_mail {
    position: absolute;
    top: 152px;
    left: 709px;
}

.pw_find .phone {
    position: absolute;
    top: 207px;
    left: 709px;
}


.find_wrapper .pwfind_Btn {
    width: 170px;
    height: 41px;
    background: #fcfcfc;
    border: 2px solid #1e1e1f;
    margin: 0;
    float: right;
    position: absolute;
    left: 825px;
    top: 270px;
}

.find_wrapper .pwfind_Btn span {
    font-family: oswald;
    font-size: 15px;
}


</style>

<script type="text/javascript">
window.onload = function() {
	FID.onsubmit = function() {
		if(user_name.value == "" || email.value == "" || phone.value == "") {
			alert("모든 정보를 입력해주세요.");
			return false;
		}
	}

	FPWD.onsubmit = function() {
		if(user_id.value == "" || user_name.value == "" || email.value == "" || phone.value == "") {
			alert("모든 정보를 입력해주세요.");
			return false;
		}
	}
}


</script>


</head>
<body>

<div class="searchForm" style="visibility: visible; margin-top: 15em; margin-left: 400px;">

	<h2 class="tit_IdPw">FIND ID/FW</h2>
	<div class="find_wrapper">
		<div class="top_wrap">
			<div class="id_find">
				<h5>아이디 찾기</h5>
				<form name="FID" action="/member/find/loginId" method="post">
					<fieldset class="idfind_set">
						<legend class="fly">아이디 찾기 폼</legend>
						<input type="text" name="user_name" id="user_name" class="id_name" placeholder="이름">
						<label for="name" class="fly">이름</label>
						<input type="text" name="email" id="email" class="id_mail" placeholder="가입한 메일 주소">
						<label for="email" class="fly">가입한 메일 주소</label>
						<input type="text" name="phone" id="phone" class="phone" placeholder="가입한 휴대폰 번호">
						<label for="phone" class="fly">가입한 휴대폰 번호</label>
						<button value="FIND" id="searchBtn" class="idfind_Btn">
						<span>FIND</span>
						</button>
					</fieldset>					
				</form>
			</div>
			<div class="find_border"></div>
			<div class="pw_find">
				<h5>비밀번호 찾기</h5>
				<form name="FPWD" action="/member/find/password" method="post">
					<fieldset class="pwfind_set">
						<legend class="fly">비밀번호 찾기 폼</legend>
						<input type="text" name="user_name" id="user_name" class="pw_name" placeholder="이름">
						<label for="name" class="fly">이름</label>
						<input type="text" name="user_id" id="user_id" class="pw_id" placeholder="아이디">
						<label for="userid" class="fly">아이디</label>
						<input type="text" name="email" id="email" class="pw_mail" class="pw_email" placeholder="가입한 메일 주소">
						<label for="email" class="fly">가입한 메일 주소</label>
						<input type="text" name="phone" id="phone" class="phone" placeholder="가입한 휴대폰 번호">
						<label for="phone" class="fly">가입한 휴대폰 번호</label>
						<button value="FIND" id="searchBtn" class="pwfind_Btn">
						<span>FIND</span>
						</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- footer.jsp 삽입하기 --%>
<%@ include file="../footer.jsp" %>


</body>
</html>























