ㄴ<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%
	String camp_name = (String) request.getAttribute("camp_name");
	String startdate = (String) request.getAttribute("startdate");
	String lastdate = (String) request.getAttribute("lastdate");
	String people = (String) request.getAttribute("people");
	String name = (String) request.getAttribute("name");
	String day=(String) request.getAttribute("day");
	int p = Integer.parseInt(people);
	int d=Integer.parseInt(day);
	d=d*15000;
	int total = p * 5000;
	total = total + d;
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html{
    position: relative;
    min-height: 100%;
    margin: 0;
}

.right {
	clear: both;
}

.left {
	float: left;
}

.left-chd {
	float: left;
}

.body-item {
	padding-top: 300px;
	padding-left: 300px;
}

.table {
	border-collapse: collapse;
/* 	border: 1px solid #A4A4A4; */
}

.top {
	padding-left: 100px;
}

.top-text {
	padding-left: 180px;
}

th, td {
	border: 1px solid #444444;
	padding: 10px;
}

.td-s {
	background: #BDBDBD;
	font-weight: bold;
}

.rev {
	font-size: 25px;
}

.back {
	background: #F2F2F2;
	width: 500px;
	height: 700px;
}

.back-bot {
	background: #F2F2F2;
	width: 500px;
	height: 120px;
}

.bot {
	color: red;
}

.b {
	font: bold;
}

textarea {
	width: 480px;
	height: 80px;
	resize: none;
}

#module {
	color: white;
	border: none;
	border-radius: 5px;
	background-color: #2E9AFE;
	font-size: 15px;
	width: 100px;
	height: 40px;
	text-align: center;
}

.end {
	padding-left: 180px;
}
.no{
	border: none;
}
</style>

<script type="text/javascript">
$( document ).ready(function() {
		
		
	$("#module").click(function(){
		if($("#ck").is(":checked")==true){
			var IMP = window.IMP;
			IMP.init('imp84218542');
		    IMP.request_pay({	
		        pg : 'kakaopay',
		        pay_method : 'card',
		        merchant_uid : 'merchant_' + new Date().getTime(),
			    name : $("#rev-camp").val(),
		        amount : <%=total%>,
		        buyer_email : 'iamport@siot.do',
		       	//buyer_email : $("#email").val() ,
		        buyer_name : $("#rev-name").val(),
		        buyer_tel : $("#call").val(),
		        buyer_addr : $("#carnum").val(),
		        buyer_postcode : $("#postcode").val(),
			}, function(rsp) {
				if ( rsp.success ) {
					//결제 완료 후 처리 -> 서버 내부 처리
					//	우리꺼 웹서버를 이용해서 DB에 데이터 저장하도록 요청보내는 곳
					//처리할 컨트롤러 만들고 data 속성으로 저장할 데이터 전송
					jQuery.ajax({
						url:"/camp/success",
						type: 'POST',
			            dataType: 'json',
			            data: {
			            	 imp_uid : rsp.imp_uid,
			            	 camp_name : $("#rev-camp").val(),
			            	 people : $("#rev-people").val(),
			            	 first : $("#rev-first").val(),
			            	 last: $("#rev-last").val(),
			            	 total : $("#rev-total").val(),
			            	 carnum : $("#carnum").val(),
			            	 tel:$("#call").val(),
			            	 name:$("#rev-name").val()
			            }
						
					}).done(function(data){
						if(everythings_fine){
								
							var msg = '결제가 완료되었습니다.';
							msg += '고유ID : ' + rsp.imp_uid;
							msg += '상점 거래ID : ' + rsp.merchant_uid;
							msg += '결제 금액 : ' + rsp.paid_amount;
							msg += '카드 승인번호 : ' + rsp.apply_num;
							alert(msg);
						}else{
							var msg = '결제실패';
							alert(msg);
						}
					});
					location.href='<%=request.getContextPath()%>/camp/success?camp_name=<%=camp_name%>'
					} else {
			        msg = '결제에 실패하였습니다.';
			        msg += '에러내용 : ' + rsp.error_msg;
			        alert(msg)
						
			    }
			});
		}else if($("#ck").is(":checked")==false){
				alert("동의 여부를 확인해주세요.");
		}
	});
		
});
</script>
</head>
<body>

	<div class="body-item">
		<div class="top-text">
			<span class="rev">예약하기</span>
		</div>
		<br>
		<div class="top">
			<table class="table">
				<tr>
					<td class="td-s">캠핑장명</td>
					<td><input type="hidden" id="rev-camp" value="<%=camp_name%>"><%=camp_name%></td>
				</tr>
				<tr>
					<td class="td-s">예약자명</td>
					<td><input type="text" name="rev-name" id="rev-name"></td>
				</tr>
				<tr>
					<td class="td-s">인원</td>
					<td><input type="hidden" id="rev-people" value=<%=people%>><%=people%></td>
				</tr>
				<tr>
					<td class="td-s">날짜</td>
					<td><%=startdate%> ~ <%=lastdate%> <input type="hidden"
						id="rev-first" value=<%=startdate%>> <input type="hidden"
						id="rev-last" value=<%=lastdate%>></td>
				</tr>
				<tr>
					<td class="td-s">차량번호</td>
					<td><input type="text" name="carnum" id="carnum"></td>
				</tr>
				<tr>
					<td class="td-s">핸드폰번호</td>
					<td><input type="text" name="call" id="call"></td>
					<td class="no"><input type="hidden" name="rev-total" id="rev-total" value=<%=total %>></td>
				</tr>
			</table>
		</div>
		<br> <br>
		<div>

			<div class="back">
				<p>※준수사항</p>
				<p>▶ 예약 준수 사항</p>
				<p>- 입실은 13시 부터 이며, 퇴실은 12실 이오니 다음분들을 위해 지켜주시기 바랍니다.</p>
				<br>
				<p>- 반려동물 동반입장 가능 합니다.</p>
				<br>
				<p>- 방문 차량과 추가인원에 대해서는 추가요금을 받습니다.</p>
				<br>
				<p>- 미성년자가 직접 예약 할 수 없으며, 보호자 동반 및 안전지도하에 가능합니다.</p>
				<br>
				<p>- 23시 이후에는 고성방가등 소음발생을 자재 바랍니다.</p>
				<br>
				<p>- 자동차는 항상 천천히 이동 바랍니다. ( 먼지 발생 및 아이들이 위험 합니다. )</p>
				<br>
				<p>- 이용객의 부주의로 일어나는 사고에 대해서는 책임을 지지않습니다.</p>
				<br>
				<p>- 쓰레기는 꼭 분리 수거해 주십시오.</p>
				<br>
				<p>- 애견 캠퍼 환영합니다. ( 목줄 필수 )</p>
				<br> <br>
				<p>
					▶ 환불 정책 <span> 예약 취소시 환불 정책은 다음과 같습니다.</span>
				</p>
				<table>
					<tr>
						<td class="td-c">10일전</td>
						<td class="td-c">7일전</td>
						<td class="td-c">5일전</td>
						<td class="td-c">3일전</td>
						<td class="td-c">1일전/당일</td>
					</tr>
					<tr>
						<td>100%</td>
						<td>80%</td>
						<td>60%</td>
						<td>40%</td>
						<td>10%</td>
					</tr>
				</table>
				<br>
				<p class="bot">* 당일취소는 14:00(입실시간)이전 까지 가능합니다.</p>
				<br>
				<p class="bot">
					* 접수 후 <span class="b">3시간 이내</span> 취소시 100% 환불 됩니다.
				</p>
				<p class="bot">* 100%환불시 금융 수수료 1000원 본인부담( 전자결제, 은행이체, SMS,
					보증보험등의 수수료)</p>
				<br>
				<p class="bot">
					* 환불시 발생 되는 <span class="b">취소 수수료</span> (LG 전자결제사, 은행사, 카드사,
					예약대행사, 부가세)는 <span class="b">본인 부담</span>입니다. -취소 수수료 15%(VAT 별도)
				</p>
				<br>
				<p class="bot">
					* <span class="b">환불 주기</span>는 결제일로 부터 7일 이후 매주 월요일(최대 15일 소요)에 환불
					됩니다.
				</p>
				<p class="bot">* 부분취소, 날짜변경등 어떠한 경우도 변경 불가함을 양지해주시기 바랍니다.(취소후
					재예약 가능)</p>
				<p class="bot">* 기상악화의 경우 별도 공지 하지 않는한 위 환불정책에 따라 진행됩니다.</p>
			</div>
			<br>
			<div class="back-bot">
				<p>▶ 개인정보 수집 및 이용에 대한 안내</p>
				<textarea>
		예약을 위해 이름, 전화번호, 차량번호등 개인정보를 수집하며, 해당 개인 정보는 원할한 예약을 위해 사용 됩니다.
		캠프링크에서 제공한 실시간 예약 시스템으로 예약 대행 업무 및 전국 캠핑장 예약 장소 찾기를 위해 사용 됩니다.
		</textarea>
			</div>
			<br>
			<div class="left-chd">
				<input type="checkbox" name="ck" id="ck">
			</div>
			<div class="left">
				<p>위 의 준수사항을 읽고 개인정보 수집 및 이용에 대한 내용에 동의합니다.</p>
			</div>
			<br> <br>
			<div class="right">
				<p>
					결제 금액 :
					<%=total %></p>
			</div>
			<br>

			<div class="end">
				<input type="button" name="btn" id="module" value="결제">
			</div>
		</div>
	</div>
</body>




</html>