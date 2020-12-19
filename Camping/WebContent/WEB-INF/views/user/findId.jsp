<%@page import="user.dto.TB_USER"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%  TB_USER user = null; %>
<% if( "false".equals(request.getAttribute("user"))) { %>
<% } else { %>
<% user = (TB_USER) request.getAttribute("user"); %>
<% request.setAttribute("msg", user.getUser_name() + "님의 아이디는 " + user.getUser_id() + "입니다."); %>
<% } %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
   
   
   
   if( "false" == '<%=request.getAttribute("user")%>') {
      alert('<%=request.getAttribute("msg")%>');
      location.href="/member/find/loginId";   
   } else {
      if(confirm('<%=request.getAttribute("msg")%>') == true) {    
      location.href='<%=request.getAttribute("loc")%>';
      } else {


      location.href="/member/find/loginId";   
      }
      
   }

});


</script>

<title></title>
</head>
<body>

   

</body>
</html>