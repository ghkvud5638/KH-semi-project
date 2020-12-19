<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/css/styles.css" />
<link rel="stylesheet" href="/css/board1.css" />
<link rel="stylesheet" href="/css/header.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>

<!-- 헤더 메뉴바 -->
 <header class="header" id="menu">
<%--     컨텍스트 패스: <%=request.getContextPath()%><br>
 --%>    
      <div class="header-wrap">
      
        <div class="header__userbar">
        <!-- 로고 이미지 -->
          <img alt="로고 이미지 넣어야함 png로" src='/images/logo.png' id="logo" style="width: 80px; height: 80px; float: left;">
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
            <li class="header__userbar-btn">
              <a class="header__userbar-link" href="/myPage/favcamp">마이페이지</a>
            </li>
<!--             <li class="header__userbar-btn"> -->
<%--          	  id : <%=session.getAttribute("loginid") %> --%>
<!--             </li> -->
          <%} %>
          </ul>
        </div>
        
        <div class="header__menubar">
        
          <ul class="header__menubar-list">
          
            <li class="header__menubar-btn">
              <a class="header__menubar-link" href="#">메뉴1</a>
            </li>
            
            <li class="header__menubar-btn">
            <a class="header__menubar-link" href="#">메뉴2</a>
            	<ul>
            	 <li><a class="child__menubar-link" href="/board/apply">메뉴2-1</a></li>
            	 <li><a class="child__menubar-link" href="/shopping/prodlist">메뉴2-2</a></li>
            	 <li><a class="child__menubar-link" href="#">메뉴2-3</a></li>
            	</ul>
            </li>
            
            <li class="header__menubar-btn">
              <a class="header__menubar-link" href="#">메뉴3</a>
                 <ul>
	              <li><a class="child__menubar-link" href="#">메뉴3-1</a></li>
	              <li><a class="child__menubar-link" href="#">메뉴3-2</a></li>
	              <li><a class="child__menubar-link" href="#">메뉴3-3</a></li>
            	 </ul>
            </li>
            <li class="header__menubar-btn">
              <a class="header__menubar-link" href="#">메뉴4</a>
            </li>
          </ul>
        </div>
        
        
      </div>
    </header>


<!-- Footer -->
<footer class="footer">
	 <p>뿌터뿌터</p>
</footer>
	    
    
    

</body>
</html>