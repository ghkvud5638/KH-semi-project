<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="camping.dto.Camp"%>
<%@page import="java.util.*" %>
<%@ include file="../header.jsp" %>


<%  Camp camp = (Camp) request.getAttribute("camp"); %> <!-- 캠핑장 정보 -->
<% List<String> list = (List) request.getAttribute("image"); %> <!-- 이미지 정보 리스트 -->
<% String msg=(String) request.getAttribute("msg"); %> <!-- 찜 버튼 변경 값 -->
<% String count = (String)request.getAttribute("count"); %> <!-- 추천 수 -->
<%String j=(String)request.getAttribute("j"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">

<script>
$(document).ready(function() {
	$("#start-date").datepicker({
    	showOn:"button"
            , buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
            ,buttonImageOnly: true
            ,changeMonth:true
            ,changeYear:true
            ,dateFormat:"yy-mm-dd"
    });
    $("#last-date").datepicker({
    	showOn:"button"
            , buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
            ,buttonImageOnly: true
            ,changeMonth:true
            ,changeYear:true
            ,dateFormat:"yy-mm-dd"
    });  
});
</script>
<script>
function like() {
	$.ajax({
		url : "/camp/like",
		type : "POST",
		data : {
			camp : $("#hid-val").val()
		},
		dataType: "json",
		success : function(data) {
			alert(data.msg)
			$("#like").html("추천:"+data.cnt)
		}
	    ,error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	})
}
function jjim() {
	$.ajax({
		url : "/camp/jjim",
		type : "POST",
		data : {
			camp : $("#hid-val").val()
		},
		dataType: "json",
		success : function(data) {
			alert(data.a)
			$("#jjim").html(data.b)
		}
	    ,error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	})
}
</script>
<meta charset="UTF-8">
<style>
p {
	font-family: "한컴바탕";
}

.body-item{
	padding-top:300px;
	padding-left:300px;
}
.table{
border-collapse: collapse;
border: 1px solid #A4A4A4;
}
th, td {
    border: 1px solid #444444;
    padding: 10px;
  }
.td-s{
	background: #BDBDBD;
	font-weight: bold;
}
.info-view{
float:left;
padding-left:40px;
}

.image-view{
float:left;	
}
.rev{
clear:both;
padding-left:170px;
}
.button{
color:white;
border:none;
border-radius: 5px;
background-color: #2E9AFE;
font-size:25px;
width:150px;
height:50px;
text-align:center;

}
.button-rev{
color:white;
border:none;
border-radius: 5px;
background-color: #2E9AFE;
font-size:15px;
width:100px;
height:40px;
text-align:center;
}
.up-button{
color:white;
border:none;
border-radius: 5px;
background-color: #2E9AFE;
font-size:13px;
width:40px;
height:25px;
}
.coo-button{
padding-left:70px;
float:left;
}
#like{
background-color: #2E9AFE;
color:white;
border-radius: 5px;
border:none;
text-align: center;
}
.up{
padding-left:400px;
}
.intro-view{
clear:both;
}
.intro-body{
width:800px;
font-size:15px;
padding: 3px;
}
.middle-image-view{
float:left;
}

.bottom{
clear:both;
}
#map{
padding-left:100px;
}
.top-image img{
	position: absolute; top:0; left: 0;
	padding-top:80px;
	width: 100%;
	height:10%;	
}
</style>
</head>
<body>
<!-- 	<div class="top-image"> -->
<!-- 		<img src="/images/campfire.jpg" > -->
	
<!-- 	</div> -->
<div class="body-item"> <!-- 전체 div -->

<div class="top-title">
	<p style="font-size:40px;"><%=camp.getCAMP_NAME() %></p> 
</div>
<br><br><br>
<div class="top-view"><!-- 상세 상단 -->
	<div class="image-view">
		<img src="/campimage/image<%=j %>.jpg" width="400" height="350">
	</div>
	
	<div class="info-view">
		<div class="up">
			<div class="up-body-one"><p id="like" >추천:<%=count%></p></div>
			<div class="up-body-two"></div>
		</div>
		<table class="table">
			<tr>
				<td class="td-s">주소</td>
				<td><%=camp.getADDR() %></td>
			</tr>
			<tr>
				<td class="td-s">대표 전화</td>
				<td><%=camp.getTEL() %></td>
			</tr>
			<tr>
				<td class="td-s">캠핑장 유형</td>
				<td><%=camp.getINDUTY() %></td>
			</tr>
			<tr>
				<td class="td-s">편의 시설</td>
				<td><%=camp.getFACIL() %></td>
			</tr>
			<tr>
				<td class="td-s">운영 기간</td>
				<td>봄, 여름, 가을, 겨울</td>
			</tr>
			<tr>
				<td class="td-s">홈페이지</td>
				<td><%=camp.getURL() %></td>
			</tr>
			<tr>
				<td class="td-s">예약 방법</td>
				<td>전화, 현장, 온라인</td>
			</tr>	
		</table>
		<br>
		<div class="two-button">
		<div class="coo-button">
		<input type="button" class="button" id="coo" value="추천하기" onclick="like();">&nbsp&nbsp&nbsp&nbsp
		</div>
		<input type="hidden" id="hid-val" value=<%=camp.getCAMP_ID() %>>
		<div class="jjim-button">
		<input type="button" class="button" id="jjim" value="<%=msg %>" onclick="jjim();">
		</div>
		</div>
		<br>

	</div>
		<div class="rev">
			<form action="/camp/rev" method="get">
			<select name="people">
				<option value="0">인원</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
			<input type="hidden" id="hid-campid" name="hid-campid" value="<%=camp.getCAMP_NAME() %>">
			<input type="text" id="start-date" name="start-date">
			<input type="text" id="last-date" name="last-date">
			<input type="submit" class="button-rev" value="예약하기">
			</form>
		</div>
</div>

<div class="middle-view"><!-- 중간 소개 부분 -->
	<div class="intro-view">
	<br><br>
		<p style="font-size:30px; text-weight: bolder;">캠핑장 소개
		<hr width="800px" align="left">
		<p class="intro-body" style="font-size:20px; text-indent: 1em;"><%= camp.getINTRO() %></p>
	<br><br><br><br><br><br><br>
	</div>
</div> <!-- 중간 소개 끝 -->
<div class="middle-image-view"> <!-- 사진 리스트 뷰 -->
	<p style="font-size:30px;">사진
	<hr width="800px" align="left">
		
	<%
	 if(list.size()!=0){%>
	 <% 
		for (int i = 0; i < 5; i++) {
			
	%>
	<img src="<%=list.get(i) %>" width="150" height="150" alt="손상된 이미지 파일">
	<%
		}
	%>
	<%
	}else if(list.size()==0){
	%>
	<img src="data:image/png;base64,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" width="150" height="150" alt="손상된 이미지 파일">
	<%
	}
	%>
	<br><br><br><br><br><br><br>
</div> <!--  사진 뷰 끝 -->
<div class="bottom"> <!-- 지도 안내 부분 시작 -->
<br>
<p style="font-size:30px;">찾아오시는 길
<hr width="800px" align="left">
<br><br>
<div id="map" style="width:700px;height:400px;"></div>
	
<!-- 키값 : 2839883285d5293951571fa58223465e -->
</div><!-- 지도 안내 부분 끝  -->



</div><!-- 전체 end -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2839883285d5293951571fa58223465e"></script>
	<script>
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(<%=camp.getLATITUDE()%>,<%=camp.getLONGITUDE()%>), //지도의 중심좌표.
			level : 3//지도의 레벨(확대, 축소 정도)
		};
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(<%=camp.getLATITUDE()%>,<%=camp.getLONGITUDE()%>);
	
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});
		marker.setMap(map);
	</script>
</body>

</html>