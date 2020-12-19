<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/css/styles.css" />
<link rel="stylesheet" href="/css/board1.css" />
<link rel="stylesheet" href="/css/header.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
<title>Insert title here</title>
<style type="text/css">
html{
	position: relative;
    min-height: 100%;
    margin: 0;
    background-color: #eceef6;
}

.footInfos > dl{
	margin: -9px 0 0 0;
}
.footInfos{
	margin: 20px 0 0 0;
	width: 705px;
    position: absolute;
}
dd{
	display: block;
	margin-inline-start: 40px;
}

.logo2{
    margin: 0 0 0 -877px;
}


</style>

<script type="text/javascript">
document.addEventListener('scroll', function() {
    var currentScrollValue = document.documentElement.scrollTop;
    console.log('currentScrollValue is ' + currentScrollValue);
});

</script>

</head>
<body>

<!-- 헤더 메뉴바 -->
 <header class="header" id="menu">
<%--     컨텍스트 패스: <%=request.getContextPath()%><br>
 --%>    
      <div class="header-wrap">
          <a href="/myPage/main"><img alt="로고 이미지 넣어야함 png로" src="/images/logo3.png" id="logo" style="width: 146px; height: 80px; float: left; margin: 1px -75px 0px -60px;"></a>      
        <div class="header__userbar">
        <!-- 로고 이미지 -->
          
      
          <ul class="header__userbar-list">
          
          <!-- 로그인 세션 감지 -->
          <% if(session.getAttribute("loginid") == null) {%>
            <li class="header__userbar-btn">
              <a class="header__userbar-link" href="/user/login">로그인</a>
            </li>
            <li class="header__userbar-btn">
              <a class="header__userbar-link" href="/user/signup">회원가입</a>
            </li>
          <%}else{%>
	           <li class="header__userbar-btn">
	             <a class="header__userbar-link" onclick="location.href='/user/logout'">로그아웃</a>
	           </li>
<!-- 	           session.getAttribute("grade").equals("admin") -->
	          <% if( ("admin").equals(session.getAttribute("grade"))) { %>
	           <li class="header__userbar-btn">
	             <a class="header__userbar-link" href="/admin/prodlist">관리자페이지</a>
	           </li>
	          <% }else { %>
	           <li class="header__userbar-btn">
	             <a class="header__userbar-link" href="/myPage/favcamp">마이페이지</a>
	           </li>
	         <%} %>
          
          <%} %>
          	   <li class="header__userbar-btn">
	             <a class="header__userbar-link" href="/myPage/main">메인 화면</a>
	           </li>
          
          </ul>
        </div>
        
        <div class="header__menubar">
        
          <ul class="header__menubar-list">
          
            <li class="header__menubar-btn">
              <a class="header__menubar-link" href="/camp/search">캠핑장 검색</a>
            </li>
            
            <li class="header__menubar-btn">
            <a class="header__menubar-link" href="/board/community">게시판</a>
            	<ul>
            	 <li><a class="child__menubar-link" href="/board/apply">게시글 작성</a></li>
            	 <li><a class="child__menubar-link" href="/board/community">게시판</a></li>
            	</ul>
            </li>
            
            <li class="header__menubar-btn">
              <a class="header__menubar-link" href="/shopping/prodlist">쇼핑몰</a>
<!--                  <ul> -->
<!-- 	              <li><a class="child__menubar-link" href="/camp/search">캠핑장 검색</a></li> -->
<!-- 	              <li><a class="child__menubar-link" href="#">메뉴3-2</a></li> -->
<!-- 	              <li><a class="child__menubar-link" href="#">메뉴3-3</a></li> -->
<!--             	 </ul> -->
            </li>
<!--             <li class="header__menubar-btn"> -->
<!--               <a class="header__menubar-link" href="#">메뉴4</a> -->
<!--             </li> -->
          </ul>
        </div>
        
        
      </div>
    </header>


<footer class="footer">
<div class="logo2">
	<img alt="캠핑의 법칙" src="/images/logo2.png" style="width: 200px; height:70px; magin:40px;">
</div>
<div class="footInfos">
		<dl>
			<dd>
				<p>서울특별시 강남구 선릉로 330-23 캠핑의 법칙<p>
			</dd>
		</dl>
		<dl>
			<dd>
				<p>TEL : 02-3823-9981  (상담시간: 평일 10:00 - 18:00)</p>
			</dd>
		</dl>
		<dl>
			<dd>
				<p>email : campingcp@camping.co.kr</p>
			</dd>
		</dl>
		
		<dl>
			<dd>
				<p>Copyrights(c) 2018 KOREA TOURISM ORGANIZATION ALL RIGHTS RESERVED.</p>
			</dd>
		</dl>
</div>

<!-- <div class="footcontent"> -->
<!-- 	<dl> -->
<!-- 		<dd> -->
<!-- 			<p><a href ="#">개인정보취급방침</a></p> -->
<!-- 		</dd> -->
<!-- 	</dl> -->
<!-- 	<dl> -->
<!-- 		<dd> -->
<!-- 			<p><a href ="#">홈페이지 이용약관</a></p> -->
<!-- 		</dd> -->
<!-- 	</dl> -->
<!-- 	<dl> -->
<!-- 		<dd> -->
<!-- 			<p><a href ="#">공지사항</a></p> -->
<!-- 		</dd> -->
<!-- 	</dl> -->
<!-- 	<dl> -->
<!-- 		<dd> -->
<!-- 			<p><a href ="#">고객센터</a></p> -->
<!-- 		</dd> -->
<!-- 	</dl> -->
<!-- <br> -->
<!-- </div> -->
</footer>
	    
    
    

</body>
</html>