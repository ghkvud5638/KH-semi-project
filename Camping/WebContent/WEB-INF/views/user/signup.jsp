<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>

<html>
<head>
<title>회원가입 화면</title>

<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<style>


/* 회원가입 */

div {
	font-family: 'Oswald', 'Noto Sans KR', sans-serif;
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

h2.tit_signup {
    font-size: 40px;
    text-align: left;
    color: #1e1e1f;
    letter-spacing: 2px;
    margin: 0px 0px 15px 130px;
}


.signup_wrapper {
    margin-top: 38px;
    width: 1280px;
    margin: 0 0 0 445px;;
}

.top_wrap {
    width: 1280px;
    height: 850px;
    position: relative;
    
}

.signupForm {
    float: left;
    width: 440px;
    height: 205px;
}


.signupForm h5 {
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
/*     MARGIN-TOP: 3EM; */
/*     MARGIN-LEFT: 300PX; */
}

.signup_wrapper fieldset {
    border: none;
    margin: 0;
    padding: 0;
}

.fly {
    position: absolute;
    left: -99999em;
    top: -99999em;
}

.signupForm .id_name {
    position: absolute;
    top: 97px;
    left: 166px;
}

.signupForm .id_mail {
    position: absolute;
    top: 152px;
    left: 166px;
}

.signupForm .phone {
    position: absolute;
    top: 207px;
    left: 166px;
}

.signupForm input {
    padding: 5px;
    margin: 5px;
}

.signup_wrapper .idfind_Btn {
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

.signup_wrapper button {
    margin: 15px 0px 0px 15px;
    border: none;
    background: none;
    position: absolute;
    border: 1px solid;
    border-radius: 4px;
    padding: 3px 6px 3px 6px;
    font-weight: bolder;
    cursor: pointer;
}

.signup_wrapper .idfind_Btn span {
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

select {
	width: 300px;
    height: 40px;
    border: 1px solid #c7c7c7;
    padding-left: 10px;
    margin: 5px 0;

}





</style>



<script type="text/javascript">

	// 회원가입 화면의 입력값들을 검사한다.
	function checkValue()
	{
	    var form = document.userInfo;
	    
	    /* 정규식 */
		var checkId = /^[a-zA-Z0-9]{5,12}$/;
		var checkPw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]|.*[0-9]).{8,24}$/;
// 		var checkEm = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 이메일이 적합한지 검사할 정규식
		
		var id = document.getElementById("id");
		var pw = document.getElementById("pw");
		var email = document.getElementById("email");
		var num1 = document.getElementById("num1");
		var num2 = document.getElementById("num2");
	    
	
	    if(!form.id.value){
	        alert("아이디를 입력하세요.");
	        return false;
	    }
	    
	    if(!checkId.test(id.value)){
	    	alert("아이디는 4~12자의 영문 대소문자와 숫자로만 입력");
	    	return false;
	    }
	    
// 	    if(form.idDuplication.value != "idCheck"){
// 	        alert("아이디 중복체크를 해주세요.");
// 	        return false;
// 	    }
	    
	    if(!form.password.value){
	        alert("비밀번호를 입력하세요.");
	        return false;
	    }
	    
	    if(!checkPw.test(pw.value)){
	    	alert("비밀번호는 8~24자 영문대소문자, 숫자, 특수문자 혼합 사용");
	    	return false;
	    }
	    
	    // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
	    if(form.password.value != form.passwordcheck.value ){
	        alert("비밀번호를 동일하게 입력하세요.");
	        return false;
	    }    
	    
	    if(!form.name.value){
	        alert("이름을 입력하세요.");
	        return false;
	    }
	    
// 	    if(!form.birthyy.value){
// 	        alert("년도를 입력하세요.");
// 	        return false;
// 	    }
	    
// 	    if(isNaN(form.birthyy.value)){
// 	        alert("년도는 숫자만 입력가능합니다.");
// 	        return false;
// 	    }
	    
// 	    if(form.birthmm.value == "00"){
// 	        alert("월을 선택하세요.");
// 	        return false;
// 	    }
	    
// 	    if(!form.birthdd.value){
// 	        alert("날짜를 입력하세요.");
// 	        return false;
// 	    }
	    
// 	    if(isNaN(form.birthdd.value)){
// 	        alert("날짜는 숫자만 입력가능합니다.");
// 	        return false;
// 	    }
	    
	    if(!form.mail1.value){
	        alert("메일 주소를 입력하세요.");
	        return false;
	    }
	    
	    if(!checkEm.test(email.value)){
	    	alert("잘못된 Email 형식입니다.");
	    	return false;
	    }
	    
	    if(!form.phone.value){
	        alert("전화번호를 입력하세요.");
	        return false;
	    }
	    
	    if(isNaN(form.phone.value)){
	        alert("전화번호는 - 제외한 숫자만 입력해주세요.");
	        return false;
	    }
	    
// 	    if(!form.address.value){
// 	        alert("주소를 입력하세요.");
// 	        return false;
// 	    }
	}

	// 취소 버튼 클릭시 첫화면으로 이동
	function goFirstForm() {
	    location.href="/mainpage";
	}    
	
	// 아이디 중복체크 화면open
	function openIdChk(){
	
// 	    window.name = "parentForm";
// 	    window.open("/member/IdCheckForm.jsp",
// 	            "chkForm", "width=500, height=300, resizable = no, scrollbars = no");    
	}
	
	// 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
	// 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
	// 다시 중복체크를 하도록 한다.
	function inputIdChk(){
	    document.userInfo.idDuplication.value ="idUncheck";
	}
	

</script>





</head>
<body>
    
<div class="signupForm" style="visibility: visible; margin-top: 12em; margin-left: 400px;">

	<div class="signup_wrapper">
	<h2 class="tit_signup">회원가입</h2>
		<div class="top_wrap">
			<div class="signupForm">
		        <div id="chk" class="modal">모달확인</div>
		        
				<form action="/user/signupProcess" name="userInfo" method="post" onsubmit="return checkValue()">
					<fieldset class="idfind_set">
						<legend class="fly">회원가입</legend>
						
						<input type="text" name="id" id="id" class="signup" placeholder="아이디" onkeydown="inputIdChk()">
						<a href="/member/IdCheckForm.jsp" rel="modal:open"><button type="button">중복확인</button></a>
                        <input type="hidden" name="idDuplication" id="idDuplication" value="idUncheck" >
						<label for="name" class="fly">아이디</label>
						
						<input type="password" name="password" id="pw" class="signup" placeholder="비밀번호">
						<label for="name" class="fly">비밀번호</label>
						
						<input type="password" name="passwordcheck" id="passwordcheck" class="signup" placeholder="비밀번호확인">
						<label for="name" class="fly">비밀번호 확인</label>
						
						<input type="text" name="name" id="name" class="signup" placeholder="이름">
						<label for="name" class="fly">이름</label>
						
						<input type="radio" name="gender" value="남" checked style="width: 13px; height: 13px; margin: 10px;">남
                        <input type="radio" name="gender" value="여" style="width: 13px; height: 13px; margin: 10px;">여
						<label for="name" class="fly">성별</label>
						
						<input type="text" name="mail1" id="email" class="signup" placeholder="이메일">@
						<select name="mail2" style="margin: 10px;">
                            <option>naver.com</option>
                            <option>daum.net</option>
                            <option>gmail.com</option>
                            <option>nate.com</option>                        
                        </select>
						<label for="email" class="fly">이메일</label>
						
						<input type="text" name="phone" id="phone" class="signup" placeholder="휴대폰 번호">
						<label for="phone" class="fly">휴대폰 번호</label>
						
						
						<input type="text" id="sample4_postcode" placeholder="우편번호">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
						<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
						<label for="phone" class="fly">주소</label>
						
						
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress" placeholder="상세주소">
						<input type="text" id="sample4_extraAddress" placeholder="참고항목">
						<label for="phone" class="fly">상세주소</label>
						
						
						<input type="submit" value="가입"/> 
						<input type="button" value="취소" onclick="goFirstForm()">
					</fieldset>					
				</form>
			</div>
		</div>
	</div>
	
</div>
		
    
    
    <!-- DAUM 우편번호 API -->

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
    
</script>

<!-- DAUM 우편번호 API 종료 -->

<%@ include file="../footer.jsp" %>

</body>
</html>
