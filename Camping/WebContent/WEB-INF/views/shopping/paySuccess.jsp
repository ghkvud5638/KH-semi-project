<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 60%;
    height: 100px;
    margin: auto;
}

th{
	font-size: 2em;
	margin: 5px;
}

td {
	font-size: 1.2em;
    padding-left: 10px;
    padding-right: 10px;
}

#mypagetb {
	text-align: right;
}

.click {
	padding: 5px;
    margin-top: 10px;
}

</style>
</head>
<body>

<table>
<tr><th colspan="2">결제가 완료되었습니다</th><th></th></tr>
<tr><th colspan="2"><img src="https://cdn.pixabay.com/photo/2017/05/24/08/06/males-2339835__340.jpg"></th><th></th></tr>
<tr>
<td id="mypagetb"><input type="submit" value="마이페이지로 돌아가기" onclick="location.href='/myPage/main'" class="click"/></td>
<td id="listtb"><input type="submit" value="다른 상품도 찾아보기" onclick="location.href='/shopping/prodlist'" class="click"/></td>
</tr>
</table>


</body>
</html>