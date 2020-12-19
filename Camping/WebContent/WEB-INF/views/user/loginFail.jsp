<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 style="color:red;">로그인 실패</h1>
<hr>

<%=session.getAttribute("msg") %><br>
입력한 아이디 : <%=session.getAttribute("loginid") %><br>
입력한 pw : <%=session.getAttribute("loginpw") %><br>

로그인 상태 : <%=session.getAttribute("login") %>

<h3>ID와 PW를 다시 확인해라</h3>

<a href="/user/login"><button>로그인 페이지로 이동</button></a>

</body>
</html>