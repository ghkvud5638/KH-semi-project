<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">

table, tr, td {
/* border: 1px solid #ccc; */
}


.userprofile {
/*    table-layout: fixed; */
   margin: -25px 0px 0px 160px;
}
.userinfo {
/*    table-layout: fixed; */
   margin: -175px 0px 0px 360px;
}
.prodinfo td{
/*     border:1px solid #ccc;  */
    width: 700px;
    height: 50px;
    padding-top:10px;
}

.wrap{
/*     border-top: 1px solid #ccc; */
    margin: 0px 0 0 355px;
    width:700px;
    height: 700px;
}

.topBar{
   margin:30px 0 30px -160px;
}
.userinfodetail{
   font-size: 13px;
    font-weight: bold;
    margin-left: 10px;
}

.content{
/*     border-top: 1px solid #ccc; */
/*     border-bottom: 1px solid #ccc; */
    
    padding: 72px 5px 0 0px;
    font-size: 20px;
}
.contentBtn{
    padding: 72px 5px 0 0px;
    font-size: 20px;
}
#updateBtn{
    width:100px;
/*     background-color: rgb(97, 118, 138, .6); */
    border: none;
/*     color:#fff; */
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
#removeBtn{
    width:100px;
    border: none;
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
#backBtn{
    width:100px;
    border: none;
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
    border-radius: 4px;
}
.contentBtn{
     float: right; 
   margin-right: 100px;
}

</style>

<%@ include file="../adminUserManage.jsp" %>


<div class="wrap">
<div class="topBar">
<table class="userprofile">
<tr>
<td style="width: 200px; height:200px"><img id="profile" name="picture" alt="올바르지 못한 경로" src="/upload/${picture}" style="width:200px; height:200px;">
</td>
</tr>
</table>
<table class="userinfo">
<tr>

<td>   <span class="userinfodetail">회원 아이디 : ${id}</span></td>
<td>    <span class="userinfodetail">회원 이름 : ${name}</span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 닉네임 : ${nickname}</span></td>
<td>     <span class="userinfodetail">회원 이메일 : ${email}</span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 성별 : ${gender}</span></td>
<td>     <span class="userinfodetail">회원 전화번호 : ${phone}</span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 주소1 : ${addr1}</span></td>
<td>     <span class="userinfodetail">회원 주소2 : ${addr2}</span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 등급 : ${grade}</span></td>
<td>     <span class="userinfodetail">가입 날짜 : ${join_date}</span></td>
</tr>
</table>
</div>
<div class="content">
<span class="contentBtn">
       <a href="/admin/usermodify?user_id=${id}"><button type="button" id="updateBtn">정보 변경</button></a>
       <a href="/admin/userremove?user_id=${id}"><button type="button" id="removeBtn">정보 삭제</button></a>
       <a href="/admin/userlist"><button type="button" id="backBtn">게시판으로</button></a>

</span>
</div>
</div>

<%@ include file="../../footer.jsp" %>
