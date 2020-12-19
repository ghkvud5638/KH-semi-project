<%@page import="shopping.dto.TB_USER"%>
<%@page import="shopping.dto.TB_PROD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% TB_PROD payProdInfo = (TB_PROD)request.getAttribute("payProdInfo"); %>
<% TB_USER payUserInfo = (TB_USER)request.getAttribute("payUserInfo"); %>
<% String amount=(String)request.getAttribute("amount"); %>
<% 
int j=Integer.parseInt(amount);
int total=j*payProdInfo.getProd_price(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% TB_PROD detail = (TB_PROD)request.getAttribute("detail"); %>
<script type="text/javascript"src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<script type="text/javascript">
$(document).ready(function(){
$("#module").click(function () {

		console.log("email : "+$("#email").val())
	
        var IMP = window.IMP; 
        IMP.init('imp84218542'); 
        var msg;
        
        IMP.request_pay({
		    pg : 'kakao',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : $("#prodname").val(),
		    amount : $("#totalprice").val(),
// 		    buyer_email : 'iamport@siot.do',
			buyer_email : $("#email").val() ,
		    buyer_name : $("#username").val(),
		    buyer_tel : $("#tel").val(),
		    buyer_addr : $("#addr").val(),
		    buyer_postcode : $("#postcode").val(),


// }, function(rsp) {
//     if ( rsp.success ) {
//         var msg = '결제가 완료되었습니다.';
//         msg += '고유ID : ' + rsp.imp_uid;
//         msg += '상점 거래ID : ' + rsp.merchant_uid;
//         msg += '결제 금액 : ' + rsp.paid_amount;
//         msg += '카드 승인번호 : ' + rsp.apply_num;
        
//     } else {
//         var msg = '결제에 실패하였습니다.';
//         msg += '에러내용 : ' + rsp.error_msg;
//     }
//     alert(msg);
<%--    	location.href="<%=request.getContextPath()%>/shopping/pay/success?prodno=<%= payProdInfo.getProd_id() %>"; --%>
   
    
// }); //IMP.request_pay end
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });

                //성공시 이동할 페이지
                location.href="<%=request.getContextPath()%>/shopping/pay/success?prodno=<%= payProdInfo.getProd_id() %>&amount=<%=amount%>&total=<%=total%>";
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                alert(msg);
                location.href="<%=request.getContextPath()%>/shopping/detail?prodno=<%= payProdInfo.getProd_id() %>";
            }
        });

    }) 
    

        
        

        
        
$("#back").click(function () {
	history.go(-1)
})//$("#module").click end

})//$(document).ready end
</script>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 60%;
    height: 100px;
    margin: auto;
}

th {
	text-align: left;
	border-bottom: 3px solid #ccc;
	padding-bottom: 10px;
    padding-top: 10px;
}

td {
	padding : 5px;
/* 	background-color: #A0ADB9; */
}


#username, #tel, #prodname, #price, #totalprice{
/* 	background-color: #A0ADB9; */
	color: #5c5b5b; 
	border:none;
}



#pricetb, #pricetb2, #pricetb3, #totaltb,#totaltb2, #totaltb3 {
	border-bottom : 3px solid #ccc;
}

.input {
	text-align: right;
}

#totaltb, #totaltb2, #totaltb3 {
	padding-bottom: 10px;
    padding-top: 10px;
/*     background-color: #779aba;  */
}

#totalprice {
/* 	background-color: #779aba; */
	
}


/* 결제하기 */
p {
    width: 60%;
    height: 100px;
    margin: auto;
    text-align: left;
}

p> input {
	padding: 5px;
    margin-top: 10px;
}
/* 주소 버튼 */
#btnaddr1, #btnaddr2 {
	margin-left: -80px;
	padding-top: 5px;
}

/* 글자크기, 색상 */
table {
	font-size: 1.3em;
}

th {
	font-size: 1.2em;
	margin: 5px;
}

input {
	font-size: 20px;
	width: 320px;
}

td>label {
    color: #5c5b5b;
}

</style>
</head>
<body>

<table>
<tr>
	<th colspan="2">결제 정보</th>
	<th></th>
</tr>
<tr>
	<td><label id="emailtb">회원 이메일</label></td><td id="emailtb2" class="input"><input id="email" type="email" value="" placeholder="이메일 입력"/></td><td></td>
</tr>
<tr >
	<td><label>회원 이름</label></td><td class="input"><input id="username" type="text" value="<%=payUserInfo.getUser_name() %>"/></td><td></td>
</tr>
<tr>
	<td><label>회원 전화번호</label></td><td class="input"><input id="tel" type="tel" value="<%=payUserInfo.getPhone() %>"/></td><td></td>
</tr>
<tr >
	<td><label>회원 주소</label></td><td class="input"><input  type="text" value="" placeholder="주소 입력" id="sample4_roadAddress"/></td>
	<td><button id="btnaddr1" onclick="sample4_execDaumPostcode()">주소찾기</button></td>
</tr>
<tr>
<td><label>상세주소</label><td class="input"><input type="text" value="" placeholder="상세주소 입력"></td><td></td>
</tr>
<tr >
	<td><label>회원 우편번호</label></td><td class="input"><input type="text" value="" placeholder="우편번호 입력" id="sample4_postcode"/></td><td></td>
</tr>
<tr >
	<td><label>상품아이디</label></td><td class="input"><input id="prodname" type="text" value="<%=payProdInfo.getProd_id() %>" /></td><td></td>
</tr>
<tr >
	<td id="pricetb"><label>가격</label></td><td id="pricetb2" class="input"><input id="price" type="text" value="<%=payProdInfo.getProd_price()%>" pattern="#,###"/></td><td id="pricetb3"></td>
</tr>
<tr >
	<td id="totaltb"><label>총 가격</label></td><td id="totaltb2" class="input"><input type="text" id="totalprice" value="<%=total %>" pattern="#,###"></td><td id="totaltb3"></td>
</tr>
</table>
<p>
    <input id="module" type="submit" value="카카오페이로 결제하기" />

</p>

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


</body>
</html>