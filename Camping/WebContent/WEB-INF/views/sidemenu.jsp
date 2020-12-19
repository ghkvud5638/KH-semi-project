<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 사이드 메뉴 */
ul#navi {
  width: 300px;
  text-indent: 50px;
  margin-top:220px;
  padding:0 20px 55px 20px;
  list-style:none;
  height: 40px;
}
li.sidemenu {
  margin-bottom: 3px;
  margin-top:30px;
  border-right:1px solid #ccc;
  padding: 25px; 
}
li.sidemenu div.insidemenu {
    height: 35px;
    line-height: 35px;
    cursor:pointer;
}
div.insidemenu:hover a{
	color:#25a5f0;
}
div.insidemenu{
	margin: 40px 0 40px 0 ;
}
div.insidemenu a{
	text-decoration:none;
	padding:10px;
	color:black;
}
.insidemenuID{
	font-weight: bold;
	padding-bottom: 15px;
}
</style>
</head>
<body>
	<% if( ("admin").equals(session.getAttribute("grade"))) { %>
   
      <ul id="navi">
        <li class="sidemenu">
	
        	<div class="insidemenuID">${loginid}님 관리자 페이지</div>
            <div class="insidemenu"><a href="/admin/usermanager">회원 관리</a></div>
            <div class="insidemenu"><a href="/admin/campmanager">캠핑장 예약 관리</a></div>
            <div class="insidemenu"><a href="/admin/prodmanager">쇼핑몰관리</a></div>
            <div class="insidemenu"><a href="/admin/boardmanager">게시판 관리</a></div>
        </li>
    </ul>
    <% } else { %>
     <ul id="navi">
        <li class="sidemenu">
        	<div class="insidemenu">
        	<img id="profile" name="picture" alt="올바르지 못한 경로" src="../upload/${picture}" style="width:80px; height:80px; margin:-14px 0px 0px 0px; ">
        	</div>
        	<div class="insidemenuID"> <%=session.getAttribute("loginid")%>님 마이페이지</div>
            <div class="insidemenu"><a href="favcamp">관심있는 캠핑장</a></div>
            <div class="insidemenu"><a href="myBoard">나의 게시글</a></div>
            <div class="insidemenu"><a href="cartlist">장바구니</a></div>
            <div class="insidemenu"><a href="payList">결제 처리 현황</a></div>
            <div class="insidemenu"><a href="modify">개인 정보 수정</a></div>
            <div class="insidemenu"><a href="ask">1:1 문의</a></div>
        </li>
    </ul>
    <% } %>
</body>
</html>