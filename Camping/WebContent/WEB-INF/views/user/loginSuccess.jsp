<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>로그인 성공</h1>
<hr>

로그인 상태 : <%=session.getAttribute("login") %></h3>
<h3><%=session.getAttribute("loginid")%> 님 환영합니다. </h3><br>

<h3>환영함</h3>

<a href="/user/login"><button>로그인 페이지로 이동</button></a>


</body>
</html>