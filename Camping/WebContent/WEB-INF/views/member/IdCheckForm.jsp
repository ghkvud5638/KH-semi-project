<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<title>아이디 중복 체크</title>
 
<style type="text/css">
#wrap {
    width: 490px;
    text-align :center;
    margin: 0 auto 0 auto;
}
#chk{
    text-align :center;
}
#cancelBtn{
    visibility:visible;
}
#useBtn{
     visibility:hidden;
}
</style>

<script type="text/javascript">     
     // 회원가입창의 아이디 입력란의 값을 가져온다.
//      function pValue(){
//          document.getElementById("userId").value = opener.document.userInfo.id.value;
//      }
     
     // 아이디 중복체크
     function idCheck(){

         var id = document.getElementById("userId").value;

         if (!id) {
             alert("아이디를 입력하지 않았습니다.");
             return false;
         } 
//          else if(  (id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
//              alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
//              return false;
//          }
         else
         {
        	 //Ajax를 사용한 값 넘기기
             var param="id="+id
             var url = "/user/IdCheckAction";
             sendRequest("POST", url, param, callback);
         }
     }
     
     function callback(){
         if(httpRequest.readyState == 4){
             // 결과값을 가져온다.
             var resultText = httpRequest.responseText;
             <%boolean res = (boolean)request.getAttribute("ok");%>
             console.log(res)
             if(!res){
                 alert("사용할수없는 아이디입니다.");
                 document.getElementById("cancelBtn").style.visibility='visible';
                 document.getElementById("useBtn").style.visibility='hidden';
                 document.getElementById("msg").innerHTML ="사용할 수 없는 아이디";
             } 
             else{ 
                 document.getElementById("cancelBtn").style.visibility='hidden';
                 document.getElementById("useBtn").style.visibility='visible';
                 document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
             }
         }
     }
     
     // 사용하기 클릭 시 부모창으로 값 전달 
     function sendCheckValue(){
//          // 중복체크 결과인 idCheck 값을 전달한다.
//          opener.document.userInfo.idDuplication.value ="idCheck";
//          // 회원가입 화면의 ID입력란에 값을 전달
//          opener.document.userInfo.id.value = document.getElementById("userId").value;
         
//          if (opener != null) {
//              opener.chkForm = null;
//              self.close();
//          }    
         
         
     }    
//          $("input[name='id']").val(document.getElementById("userId").value)
</script>
 
</head>
<body onload="pValue()">
<div id="wrap">
    <br>
    <b><font size="4" color="gray">아이디 중복체크</font></b>
    <hr size="1" width="400">
    <br>
    <div id="chk">
        <form id="checkForm">
        	<%request.getParameter("id");%>
            <input type="text" name="idinput" id="userId"/>
            <input type="button" value="중복확인" onclick="idCheck()">
            <input type="button" value="tetete"/>
        </form>
        <div id="msg"></div>
        <br>
<!--         <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br> -->
<!--         <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()"> -->
        <a href="#" rel="modal:close"><button id="cancelBtn" onclick="window.close()">취소</button></a>
        <a href="#" rel="modal:close"><button id="useBtn" onclick="sendCheckValue()">사용하기</button></a>
    </div>
</div>    
</body>
</html>