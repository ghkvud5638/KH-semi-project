<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<style type="text/css">

table, tr, td {
/*  border: 1px solid #ccc;  */
}


.userprofile {
/*    table-layout: fixed; */
   margin: -25px 0px 0px 160px;
}
.userinfo {
/*    table-layout: fixed; */
   margin: -205px 0px 0px 360px;
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

#upload {
   margin-left: 45px;
   border: 1px solid #ccc;
   border-radius: 5px;
   padding: 5px 20px 5px 20px;
   text-align: center;
   
}

#upload:hover {
 color:#695e2c;
 background-color:#d3ceb5;
 cursor:pointer;

}

</style>
<script type="text/javascript">
function profilePreview(target) {
   var files = target.files;
   var fileArr = Array.prototype.slice.call(files);
   
   fileArr.forEach(function(f) {
      if(!f.type.match("image/.*")) {
         alert("이미지만 업로드 가능합니다.");
         return;
      } else {
         var reader = new FileReader();
         reader.onload = function(e) {
            $("#profile").attr("src", e.target.result);

         }
         reader.readAsDataURL(f);
      }
   })

}

$(document).ready(function(){

   $('#updateBtn').on('click', function(){

           $('#usermodifyform').submit();      
      });
         
   });
</script>

<%@ include file="../adminUserManage.jsp" %>


<div class="wrap">
<div class="topBar">
<form action="/admin/usermodify" method="post" enctype="multipart/form-data" id="usermodifyform">
<table class="userprofile">
<tr>
<td><img id="profile" name="picture" alt="올바르지 못한 경로" src="/upload/${picture}" style="width:200px; height:200px;">
</td>
</tr>
<tr>
<td>
<br>
   <label for="upfile" id="upload">사진 변경</label>
   
   <input type="file" id="upfile" name="upfile" onchange="profilePreview(this);" style="display: none;">

</td>
</tr>
</table>
<table class="userinfo">
<tr>
<td>   <span class="userinfodetail">회원 아이디 : ${id}</span></td>
<td>    <span class="userinfodetail">회원 이름 : ${name}</span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 닉네임 : <input type="text"  size="8" name="nicname"  value="${nicname}"></span></td>
<td>     <span class="userinfodetail">회원 이메일 : <input type="email" name="email" size="8" value="${email}"></span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 성별 : ${gender}</span></td>
<td>     <span class="userinfodetail">회원 전화번호 : <input type="text" name="phone" size="8" value="${phone}"></span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 주소1 : <input type="text"  size="8" name="addr1"  value="${addr1}"></span></td>
<td>     <span class="userinfodetail">회원 주소2 : <input type="text" name="addr2" size="8" value="${addr2}"></span></td>
</tr>
<tr>
<td>     <span class="userinfodetail">회원 등급 : ${grade}</span></td>
<td>     <span class="userinfodetail">가입 날짜 : ${join_date}</span></td>
</tr>
</table>
      <input type="hidden" name="id" value="${id}">
      <input type="hidden" name="hiprofile" value="${picture}">

</form>
</div>
<div class="content">
<span class="contentBtn">
       <button type="button" id="updateBtn">변경</button>
       <a href="/admin/userdetail?user_id=${id}"><button type="button" id="backBtn">취소</button></a>

</span>
</div>
</div>

<%@ include file="../../footer.jsp" %>
