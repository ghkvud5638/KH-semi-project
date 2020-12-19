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
        	<div class="insidemenu">
        	<img id="profile" name="picture" alt="올바르지 못한 경로" src="/upload/${picture}" style="width:80px; height:80px; margin:-14px 0px 0px 0px; ">
        	</div>
        	
        	<div class="insidemenuID"> <%=session.getAttribute("loginid")%>님 마이페이지</div>
            <div class="insidemenu"><a href="/shopping/prodlist">전체</a></div>
            <div class="insidemenu"><a href="/shopping/list/tent">텐트/타프</a></div>
            <div class="insidemenu"><a href="/shopping/list/sleepingbag">침낭</a></div>
            <div class="insidemenu"><a href="/shopping/list/table">테이블/의자</a></div>
            <div class="insidemenu"><a href="/shopping/list/cooking">취사용품</a></div>
        </li>
    </ul>



</body>
</html>