<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="camping.dto.Rev"%>
<%Rev rev=(Rev)request.getAttribute("rev"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table>
<tr><th colspan="2">결제가 완료되었습니다</th><th></th></tr>
<tr><th colspan="2"><img src="https://cdn.pixabay.com/photo/2017/05/24/08/06/males-2339835__340.jpg"></th><th></th></tr>
<tr>
<td><input type="submit" value="홈으로 돌아가기" onclick="location.href='/myPage/main'"/>
</td>
<td><input type="submit" value="다른 캠핑장 찾아보기" onclick="location.href='/camp/search'"/></td>
</tr>
</table>


</body>
</html>