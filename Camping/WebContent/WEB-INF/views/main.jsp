<%@page import="mypage.dto.Board"%>
<%@page import="mypage.dto.CAMP_INFO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<% List<CAMP_INFO> topCampList = (List<CAMP_INFO>) request.getAttribute("topCampList"); %>
<%	List<Board> CommunityList = (List<Board>) request.getAttribute("CommunityList"); %>
<%	List<Board> AssignmentList = (List<Board>) request.getAttribute("AssignmentList"); %>


<style type="text/css">
 html{ 
 	position: relative; 
    min-height: 100%; 
	margin: 0; 
	height: 1705px;
} 
body{
	height: 1600px;
}

.mainPic{
	width: 2117px;
    height: 1020px;
    opacity: .82;
}
.mainPicWrap{
	width: 2117px;
    height: 1020px;
}
/* 이미지를 감싸고 있는 가장 상위 박스 (div) */
#sliderbox{ 
	width: 600px;
	height: 400px;
	border: 1px solid #ccc;
	/* 외부정렬 : 가운데 */
	margin: 0 auto;
	/* div영역을 벗아난 부분 안보이게 처리하기 */
	overflow: hidden;
	/* overflow: visible; */	
}
/* 이미지목록 (ul) */
#slider{
	/* ul태그의 기본 스타일 없애기 */
	padding:0;
	margin:0;
	list-style: none;
	
	/* 자식요소 absolute박스의 기준점으로 설정 */
	position: relative;
}
/* 이미지 항목 */
#slider li{
	/* 자유롭게 위치를 이동시킬 수 있게 설정 */
	position: absolute;
}
/* 이미지 */
#slider li img{
	/* 모든 이미지의 크기를 같은 크기로 고정 */
	width: 600px;
	height: 400px;
}
.campName{
	z-index: 999;
	text-align: center;
	position: absolute;
	top: 80%;
	left: 50%;	
	width:320px;
	padding: 5px 10px;
	color: white;
	transform: translate( -50%, -50% );
/* 	font-weight: bolder; */
	font-size: 19px;
	font-family: "한컴바탕";
	text-shadow: 1px 1px 10px #ccc;
	
}
.test_img{
	position: relative;
}
.g_item > ul{
	margin: 20px 0 0 0;
}
.rank{
	width:50px;
	height:50px;
	text-align: center;
    position: absolute;
    top: 10%;
    left: 16%;
    padding: 5px 10px;
    transform: translate( -50%, -50% );
}
/* .colorbox{ */
/* 	background-color: black; */
/* 	opacity:  */
/* } */

.middleWrap{
	border:1px solid #ccc;
}

/* ================================================== */


/* main.css */
.container {
	height: 825px;
	margin: 40px;
	padding: 3rem;
	flex-wrap: nowrap;
	align-items: center;

}

.newcamp {
	display: flex;
	position: relative;
	justify-content: space-around;
	text-overflow: ellipsis;
}

.newcamp div ul li {
	margin: 5px;
    padding: 10px;
}

.area_newcamp ul li:hover img {
	-webkit-transform:scale(.9);
    -moz-transform:scale(.9);
    -ms-transform:scale(.9);   
    -o-transform:scale(.9);
    transform:scale(.9);
}

.campnotice {
	display: flex;
	position: relative;
	justify-content: flex-start;
	background-color: rgba(8, 64, 52, 0.54);
	height: 100px;
}

.camplatestnews {
	display: flex;
	position: absolute;
	width: 1300px;
}

.news {
	width: 450px;
	height: 500px;
}

.news img {
	overflow: hidden;
}

.news:hover img {
    -webkit-transform:scale(.9);
    -moz-transform:scale(.9);
    -ms-transform:scale(.9);   
    -o-transform:scale(.9);
    transform:scale(.9);
}

.newsimage{
	margin: 0 0 50 60;
    display: flow-root;
}

.newsimage img {
	width: 250px;
    height: 150px;
    margin-left: 90px;
    margin-right: 50px;
    margin-bottom: 50px;
}

.newscontent {
	
	margin-top: 0px;

}

.newscontent ul li{
	width: 700px;
	height: 200px;
}

.newscontent ul li p {
	
	text-align: left;
    margin-left: 5px;
    margin-bottom: 0px;
    width: 700px;

}

.campreview {
	display: flex;
	margin-top: 12px;
	background-color: #ccc;
	paddig: 50px 0 60px 0;
	background: #f1f2f9;
    height: 240px;
    position: absolute;
    width: 1945px;
    margin: 17px 0 0 0;
}

.camptip {
	display: flex;
	margin-top: 650px;
}


.camp_wrap {
	margin: 0 auto;
	height: 300px;
}

.review {
	font-size: 1.3em;
	font-weight: 600;
	margin-bottom: 16px;
	color: #393b46;
	line-height: 1.7;
}


#comm dd {
	padding: 8px, 0px;
	margin: 0;
	font-size: 18px;
	line-height: 1;
}

#comm dd a:hover{color:#25a5f0;}


#comm dd i.fa {
	color: #9fa4bc;
}

.fas{
	display: inline-block;
	font: normal normal normal 14px/1 FontAwesome;
}

#comm dl {
	float: left;
	padding: 0;
	margin: 0 75px 0 0;
	width: 255px;

}

dl{
	display: block;
/* 	margin-block-start: 1em; */
/* 	margin-block-end: 1em;35 */
/* 	margin-inline-start: 0px; */
	margin-inline-end: 0px;
}

#comm dl.endreview {
	margin-right: 0;
}

dt {
display: block;
}


ul {
	text-align: center;
}

/* li {
	display: inline-block;
} */

.area_newcamp {
border-left: 1px solid #ccc;
border-right: 1px solid #ccc;

}
.newtitle {
	font-size: 25px; 
 	margin:15px;
 	font-family: "한컴바탕";
 	font-weight: bolder;
}

.intro {
	font-size: 20px;
	padding: 5px;
	width: 641px;
	line-height: 1.3;
	text-indent: 1em;
	font-family: "한컴바탕";
	text-align: left;
}
#pr {
	white-space: nowrap; 
	width: 230px;
	overflow: hidden;
	text-overflow: ellipsis;

}

.campreview div ul li {
	padding: 10px;
}

/* .logo2 { */
/* 	margin-left: 150px; */
/* } */

.footcontent {
	display: flex;
	position: relative;
	margin-left: 150px;
}


.footcontent dl dd p {
	margin-left: 40px;
}

.footInfo{
	display: flex;
	position: relative;
	margin-top: 100px;
	margin-left: -1050px;
	
}

.footInfo dl dd p {
	font-size: 18px;
	padding: 0px;
}
.boardTB1{
	table-layout: fixed;
	height: 200px;
	margin: 0 0 0px 223px;
}
.boardTB2{
	table-layout: fixed;
	height: 200px;
	margin: 0 0px 0px -460px;
}
.fr_more{
	font-family: "한컴바탕";
	font-weight: bolder;
 	font-size: 18px;

}

.ArticleTitle{
	margin: 0 0 0 16px;
	font-family: "한컴바탕";
 	
}
.boardHit{
	padding: 20px 0px 0px 41px;
}
div.boardLink:horver a{
	color:#25a5f0;
	text-decoration:underline;
}
.Mainlogo{
	margin: 224px 0 0 -1458px;
    z-index: 990;
    position: absolute;
    width: 791px;
}

@font-face {
   font-family: "BMHANNAAIR";
   src: url(/font/BMHANNAAIR_TTF.TTF) format("truetype");
   font-style: normal;
   font-weight: normal;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
	  var gall  = setInterval(galleryFun, 1800);
	  var inter = true;
	  var idx = 2;
	  
	  function galleryFun(){
	      $(".gallery ul").animate({
	        "left":-300*idx+"px"
	      },300);
	     $(".g_item ul li").eq(idx-1).addClass("on").siblings().removeClass("on");
	     idx++;
	     if(idx> $(".gallery ul li").length-3){
	       $(".gallery ul").animate({
	         "left":0
	       },0);
	       idx=0;
	       
	     }
	   }
	   $(".gallery , .g_item").hover(function(){
	     if(inter==true){
	       clearInterval(gall);
	       inter=false;
	     }
	   },function(){
	     if(inter==false){
	       gall  = setInterval(galleryFun, 2000);
	       inter=true;
	     }
	     
	   });
	   $(".g_item ul li").on('click',function(){
	     $(this).addClass("on").siblings().removeClass("on");
	     idx = $(this).index()+1;
	     $(".gallery ul").animate({
	        "left":-300*idx+"px"
	      },1000);
	   });
});
</script>
</head>
<body>
<div class="mainPicWrap">
	<img  class="mainPic" alt="#" src="/images/main.jpg">
	<img alt="#" src="/images/logo2.png" class="Mainlogo">
</div>
<% 
	int randomNum = (int)(Math.random()*20)+1;
%>

<div class="gallery">
 <ul class="clearfix">
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(7).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(7).getPicture()%>"></a></div><div class="campName"><p><%=topCampList.get(7).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(0).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(0).getPicture()%>"></a></div><div class="rank"><img src="/images/1st.png" style="margin:13px 0 0 -25px; "></div><div class="campName"><p><%=topCampList.get(0).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(1).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(1).getPicture()%>"></a></div><div class="rank"><img src="/images/2st.png" style="margin:13px 0 0 -25px; "></div><div class="campName"><p><%=topCampList.get(1).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(2).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(2).getPicture()%>"></a></div><div class="rank"><img src="/images/3st.png" style="margin:13px 0 0 -25px; "></div><div class="campName"><p><%=topCampList.get(2).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(3).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(3).getPicture()%>"></a></div><div class="campName"><p><%=topCampList.get(3).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(4).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(4).getPicture()%>"></a></div><div class="campName"><p><%=topCampList.get(4).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(5).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(5).getPicture()%>"></a></div><div class="campName"><p><%=topCampList.get(5).getCamp_name()%></p></div></li>
  <li class="test_img"><div class="img scaleB"><a href="/camp/detail?camp_id=<%=topCampList.get(6).getCamp_id()%>&j=<%=randomNum%>"><img id="slideimg" alt="이미지 준비중" src="<%=topCampList.get(6).getPicture()%>"></a></div><div class="campName"><p><%=topCampList.get(6).getCamp_name()%></p></div></li>
 </ul>                                                                                                                       
</div>

<div class="g_item">
  <ul>
    <li class="on"></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ul>
</div>


<div class="container">
	<article class="newcamp">
		<div class="area_newcamp">
			<ul>
				<li>
					<a href="/camp/detail?camp_id=<%=topCampList.get(0).getCamp_id()%>&j=<%=randomNum%>">
					<img src="<%=topCampList.get(0).getPicture()%>" style="width:350px; height:230;"></a>
					<p class="newtitle"><%=topCampList.get(0).getCamp_name()%></p>
					<p class="intro"><%=topCampList.get(0).getIntro()%></p>
				</li>
			</ul>
		</div>
		<div class="area_newcamp">
			<ul>	
				<li>
					<a href="/camp/detail?camp_id=<%=topCampList.get(1).getCamp_id()%>&j=<%=randomNum%>">
					<img src="<%=topCampList.get(1).getPicture()%>" style="width:350px; height:230;"></a>
					<p class="newtitle"><%=topCampList.get(1).getCamp_name()%></p>
					<p class="intro"><%=topCampList.get(1).getIntro()%></p>
				</li>
			</ul>
		</div>
		<div class="area_newcamp">	
			<ul>	
				<li>
					<a href="/camp/detail?camp_id=<%=topCampList.get(2).getCamp_id()%>&j=<%=randomNum%>">
					<img src="<%=topCampList.get(2).getPicture()%>" style="width:350px; height:230;"></a>
					<p class="newtitle"><%=topCampList.get(2).getCamp_name()%></p>
					<p class="intro"><%=topCampList.get(2).getIntro()%></p>
				</li>
			</ul>
		</div>
	</article>
	

	

<div id="comm" class="campreview">
	<div class="camp_wrap">
		<table class="boardTB1">
		<caption><a href="/board/community" class="fr_more" title="더 보기">커뮤니티</a></caption>
		<%for(int i=0; i<CommunityList.size(); i++){ %>
		<tr>
			<td><%=CommunityList.get(i).getBoardno()%></td>
			<td><a href="/board/detail?boardno=<%=CommunityList.get(i).getBoardno()%>" class="ArticleTitle"><%=CommunityList.get(i).getTitle()%></a></td>
	<%-- 		<td><%=CommunityList.get(i).getUser_id()%></td> --%>
			<td class="boardHit"><%=CommunityList.get(i).getHit()%></td>		
		</tr>
		<%} %>	
		</table>
	</div>

	<div class="camp_wrap">
		<table class="boardTB2">
		<caption><a href="/board/community" class="fr_more" title="더 보기">양도</a></caption>
		<%for(int i=0; i<AssignmentList.size(); i++){ %>
		<tr>
			<td><%=AssignmentList.get(i).getBoardno()%></td>
			<td><a href="/board/detail?boardno=<%=AssignmentList.get(i).getBoardno()%>" class="ArticleTitle"><%=AssignmentList.get(i).getTitle()%></a></td>
	<%-- 		<td><%=CommunityList.get(i).getUser_id()%></td> --%>
			<td class="boardHit"><%=AssignmentList.get(i).getHit()%></td>		
		</tr>
		<%} %>
		</table>
	</div>
</div> <!-- container div end -->

</div>






