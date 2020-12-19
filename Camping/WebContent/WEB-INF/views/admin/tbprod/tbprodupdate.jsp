<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style>

.controller {
	margin:-320px 0px 0px 400px;
	
	width: 840px;
}
#wriTitle{
	text-align: center;
	background-color: #f0e8df;
	width: 800px;
	height: 20px;
	padding: 12px 0;
}

#cate_id{
	margin: 20px 0px 10px 0px;

	width: 300px;
	height: 40px;
	font-size: 18px;
}

#prod_name{
	margin: 10px 0px 10px 0px;
	width: 700px;
	height: 40px;
	font-size: 18px;
}

#prod_price{
	margin: 10px 0px 10px 0px;
	width: 700px;
	height: 40px;
	font-size: 18px;
}

#prod_num{
	margin: 10px 0px 10px 0px;
	width: 700px;
	height: 40px;
	font-size: 18px;
}
#main {
	margin: 150px 0px 0px 0px;
}
#res {

	margin: -35px 0px 0px 550px;

}
button{
 	width:100px;

    padding: 5px 0;
    text-align: center; 
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
	font-weight: bolder;
    border-radius: 4px;
    background-color: #f0e8df;
        
}

#mainUpdiv {
	margin: -20px 0px 30px 440px;
}
#detailUpdiv {
	margin-top: 50px;
}

#mainUpload, #detailUpload {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 30px 30px 30px 30px;
    background-color: #f0e8df;
	
}

#mainUpload:hover, #detailUpload:hover {
 color:#695e2c;
 background-color:#d3ceb5;
 cursor:pointer;

}

button:hover {
 color:#695e2c;
 background-color:#d3ceb5;
 cursor:pointer;
 
 
}


.mainPreview{
 margin: -85px 0px 125px 600px;
}
.mainPreimg{
width: 90px;
height: 90px;
	}
.detailPreview{
 margin: -60px 0px 0px 150px;

}
.detailPreimg{
width: 300px;
height: 300px;
}

	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function mainPreview(target) {
	var files = target.files;
	var fileArr = Array.prototype.slice.call(files);
	var preview = document.querySelector('.mainPreview');
	if (preview.innerHTML == ""){
	$('.mainPreview').css('margin','-85px 0px 0px 667px');
	}
	
	fileArr.forEach(function(f) {
		if(!f.type.match("image/.*")) {
			alert("이미지만 업로드 가능합니다.");
			return;
		} else {
			var reader = new FileReader();
			reader.onload = function(e) {
				preview.innerHTML = "";
				var img = document.createElement('img');
				img.src = e.target.result;
				img.dataFile = f.name;
				img.className = 'mainPreimg';
				preview.append(img);
			}
			reader.readAsDataURL(f);
		}
	})

}

function detailPreview(target) {
	var files = target.files;
	var fileArr = Array.prototype.slice.call(files);
	var preview = document.querySelector('.detailPreview');
	if (preview.innerHTML == ""){
	$('.detailPreview').css('margin','-60px 0px 0px 150px');
	}
	
	fileArr.forEach(function(f) {
		if(!f.type.match("image/.*")) {
			alert("이미지만 업로드 가능합니다.");
			return;
		} else {
			var reader = new FileReader();
			reader.onload = function(e) {
				preview.innerHTML = "";
				var img = document.createElement('img');
				img.src = e.target.result;
				img.dataFile = f.name;
				img.className = 'detailPreimg';
				preview.append(img);
			}
			reader.readAsDataURL(f);
		}
	})

}
$(document).ready(function(){

	$('#updateBtn').on('click', function(){

		if( "selectnono"== ($('#cate_id').val())) {
		    alert("물건 종류를 선택해 주세요");
			} else {
			alert("성공")
		  	$('#updateform').submit();
			}
		
		});
			
	});
		
</script>


    <div class="controller">
	<div id="wriTitle"><b>상품 변경</b></div>
	 <form action="/admin/produpdate" method="post" enctype="multipart/form-data" id="updateform">
	 <input type="hidden" name="prod_id" value="${prod_id}">
		<select name="cate_id" id="cate_id">
			<% if(request.getAttribute("cate_id").equals("tent")) {%>
			<option value="tent" selected>텐트</option>
			<option value="sleepingbag">침낭</option>
			<option value="tablechair">의자</option>
			<option value="etc">기타</option>
				
			<% } else if(request.getAttribute("cate_id").equals("sleepingbag")) {%>	
			<option value="tent">텐트</option>
			<option value="sleepingbag" selected>침낭</option>
			<option value="tablechair">의자</option>
			<option value="etc">기타</option>
			<% } else if(request.getAttribute("cate_id").equals("tablechair")) {%>	
			<option value="tent">텐트</option>
			<option value="sleepingbag">침낭</option>
			<option value="tablechair"  selected>의자</option>
			<option value="etc">기타</option>
			<% } else {%>	
			<option value="tent">텐트</option>
			<option value="sleepingbag">침낭</option>
			<option value="tablechair">의자</option>
			<option value="etc" selected>기타</option>
			<% } %>
			
		</select>
	<div id="mainUpdiv">
	<label for="mainUpfile" id="mainUpload">메인이미지</label>
	
	<input type="file" id="mainUpfile" name="mainUpfile" onchange="mainPreview(this);" style="display: none;">
	</div>		
	<div class="mainPreview">
	<img src="${prod_picturetitle}"  class="mainPreimg">
	</div>
	<input id="prod_name" type="text" name="prod_Name" class="input" value="${prod_name}" placeholder="상품 이름을 입력하세요" required="required">
				
	<input id="prod_price" type="number" name="prod_Price" size="50" class="input" value="${prod_price}" placeholder="금액을 입력하세요" required="required">
		
	<input id="prod_num" type="number" name="prod_Num" size="50" class="input" value="${prod_num}" placeholder="상품 수량을 입력하세요" required="required">
				 	
	
	<div id="detailUpdiv">
	<label for="detailUpfile" id="detailUpload">내용이미지</label>
	<input type="file" id="detailUpfile" name="detailUpfile" onchange="detailPreview(this);" style="display: none;">
	</div>
	<div class="detailPreview">
	<img src="${prod_picturedetail}" class="detailPreimg" >
	</div>
				
	<div id="main">
				<a href="/myPage/main">	<button type="button">메인페이지로</button> </a>
					</div>
		<div id="res">
				<button type="button" id="updateBtn">변경</button>
				<a href="/admin/prodpage?prod_id=${prod_id}"><button type="button">취소</button></a>
		</div>	
		<input type="hidden" name="himain" value="${prod_picturetitle }">
		<input type="hidden" name="hidetail" value="${prod_picturedetail }">

</form>
	
	 
    </div>

<%-- footer.jsp 삽입하기 --%>
<%@ include file="../../footer.jsp" %>
