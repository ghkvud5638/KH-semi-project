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
  height: 200px;
}


li.sidemenu {
        margin-bottom: 3px;
        margin-top:30px;
        
        /* 세로선 */
        border-right:1px solid #ccc;
        
        /* 세로선 범위 */
        padding: 25px; 
}

li.sidemenu div.insidemenu {
    height: 35px;
    line-height: 35px;
    cursor:pointer;
}

/* 사이드메뉴에 마우스 올리면 */
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

    <ul id="navi">
        <li class="sidemenu">
	
        	<div class="insidemenuID">${loginid}님 관리자 페이지</div>
            <div class="insidemenu"><a href="/admin/usermanager">회원 관리</a></div>
            <div class="insidemenu"><a href="/admin/campmanager">캠핑장 예약 관리</a></div>
            <div class="insidemenu"><a href="/admin/prodmanager">쇼핑몰관리</a></div>
            <div class="insidemenu"><a href="/admin/boardmanager">게시판 관리</a></div>
        </li>
    </ul>



</body>
</html>