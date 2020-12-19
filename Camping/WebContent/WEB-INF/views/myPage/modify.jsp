<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<link rel="stylesheet" href="/css/privacy.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<style type="text/css"> 
.deleteBtn{
	margin: -24px 0 0 89px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
// 	/* propertychange change keyup paste  */
//  input null 감지
	$("#id").on("input propertychange change keyup paste", function() {
		if(!($("#id").val())){
			$("#idS").css("color","red");		
		} else{
			$("#idS").css("color","black");
		}
	});
	$("#name").on("input propertychange change keyup paste", function() {
		if(!($("#name").val())){
			$("#nameS").css("color","red");		
		} else{
			$("#nameS").css("color","black");
		}
	});
	$("#nick").on("input propertychange change keyup paste", function() {
		if(!($("#nick").val())){
			$("#nickS").css("color","red");		
		} else{
			$("#nickS").css("color","black");
		}
	});
	$("#email").on("input propertychange change keyup paste", function() {
		if(!($("#email").val())){
			$("#emailS").css("color","red");		
		} else{
			$("#emailS").css("color","black");
		}
	});
})
</script>
<section>
<article>
    <div class="privacy_page_wrap">
	 <form action="/myPage/modifyProcess" method="post" enctype="multipart/form-data">
	  <table summary="회원가입 기본정보 입력 받는 표">
	  <colgroup>
	   <col width="100">
	   <col>
	  </colgroup>
	  <thead>
	   <tr>
	    <th scope="col"></th>
	   </tr>
	   <tr>
	    <th scope="col"></th>
	   </tr>
	  </thead>
	   <tbody class="privacy_page_wrap_content">
	   	<tr>
		 <td class="key">
		 	아이디<span id="idS">*</span>
		 </td>
		 <td>
		 	<input id="id" type="text" name="id" class="input" value="${id}">
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		 	이름
		 	<span id="nameS">*</span>
		 </td>
		 <td class="td_input">
		 	<input id="name" type="text" name="name" maxlength="10" class="input" value="${name}">
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		 	닉네임
		 	<span id="nickS">*</span>
		 </td>
		 <td>
		 	<input id="nick" type="text" name="nic" maxlength="20" class="input" value="${nickname}">
		 	<span  class="hsmg" id="hLayernic"></span>
		 	<div>닉네임은 자신을 표현할 수 있는 단어로 20자까지 사용할 수 있습니다.</div>
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		 	이메일<span id="emailS">*</span>
		 </td>
		 <td>
		 	<input id="email" type="text" name="email" size="35" class="input" value="${email}">
		 	<span  class="hsmg" id="hLayernic"></span>
		 	<div>주로 사용하는 이메일 주소를 입력해 주세요.</div>
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		  	사진
		 </td>
		</tr>
		<tr>
		<td></td>
		  <td>
		 	 <img id="profile" name="picture" alt="올바르지 못한 경로" src="../upload/${picture}" style="width:200px; height:200px; margin:-110px 0 0 39px;">
		  </td>
		<td>	 	
		 	<input type="file" name="upfile" style="margin:0px -24px 0px -70px;"/>
		</td>
		 </tr>
	   </tbody>
	  </table>	  
	 <div class="submitbox">
    	<input type="submit" value="정보 수정" class="privacy_btnM">
     </div>
	</form>
	 
	<form action="/myPage/memberdelete" method="post">
		<div class="deleteBtn">
	    	<input type="submit" value="계정 삭제" class="privacy_btn" onclick="return confirm('계정을 삭제합니다.')"/> <!-- 삭제하기 전에 한번 물어보기 -->
	    </div>
	</form>
 
   </div>
</article>
</section>
<%-- footer.jsp 삽입하기 --%>
<%@ include file="../footer.jsp" %>
