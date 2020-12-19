<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header.jsp 삽입하기 --%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type = "text/css">
 	.prodUpdate_page_wrap{ 
 	flex-wrap: wrap; 
	height: 765px; 
 	width: 700px; 
 	background-color: #ccc; 
 	margin: -256px 97px 0px 500px; 
 	opacity: 0.6; 
 	} 
 	.prodUpdate_page_wrap_content{ 
 	display: table-row-group; 
 	vertical-align: middle; 
 	} 

	.prodUpdate_page_wrap_content > tr{ 
	display: table-row; 
 	vertical-align: inherit; 
 	} 


 	.key{ 
	
 	padding-left: 30px; 
 	width: 60px; 
 	height:100px; 
 	color: #111; 
 	font-size: 14px; 
 	text-align: right; 
 	padding-right: 50px; 
	
	
 	} 

 	.td_input{ 
	
 	padding: 15px 30px 12px 30px; 
 	color: #888;  
	
 	} 
 	
 	.submitbox{ 
 	margin-top: 178px;
 	margin-left: 500px;
 	}
 	
 	.pre_img {
 	width: 200px;
 	height: 200px;
 	}
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

var result = true;

	function preview(target) {
		var files = target.files;
		var fileArr = Array.prototype.slice.call(files);
		
		var preview = document.querySelector('.pre_div');
		preview.innerHTML = "";
		
		if(files.length > 5 ) {
			alert("최대 5장까지만 업로드 가능합니다.");
			result = false;
		} else {
			fileArr.forEach(function(f) {
				if(!f.type.match("image/.*")) {
					alert("이미지만 업로드 가능합니다.");
					result = false;
					return;
				} else {
					var reader = new FileReader();
					reader.onload = function(e) {
						var img = document.createElement('img');
						img.src = e.target.result;
						img.dataFile = f.name;
						img.className = 'pre_img';
						preview.append(img);
					}
					reader.readAsDataURL(f);
					result = true;
				}
			})
		}
	}
	
	function submit_result() {
		if(!result) {
			alert("파일 갯수 혹은 확장자를 확인해주세요. 이미지만 업로드 가능합니다.");
		}
		return result;
	}

</script>

<section>
<article>
    <div class="prodUpdate_page_wrap">
	 <form action="/admin/produpdate?prod_id=${prod_id}" method="post" enctype="multipart/form-data">
	  <table summary="상품 정보 입력 받는 표">
	  <colgroup>
	   <col width="100">
	   <col>
	  </colgroup>
	  <thead>
	   <tr>
	    <th scope="col">상품 정보 변경</th>
	   </tr>
	   <tr>
	    <th scope="col"><br></th>
	   </tr>
	  </thead>
	   <tbody class="prodUpdate_page_wrap_content">
	   	<tr>
	   	<td class="key">
	   	상품 이름 : 
	   	</td>
		 <td>
		 	<input id="prod" type="text" name="prodName" class="input" size="50" value="${prod_name}" placeholder="${prod_name}">
		 </td>			
		</tr>

		<tr>
		 <td class="key">
		  	금액 : 
		 	</td>
		 <td>
		 	<input id="price" type="number" name="prodPrice" size="50" class="input" value="${price}" placeholder="${price}">
		 	<span  class="hsmg" id="hLayernic"></span>
		 </td>			
		</tr>

		<tr>
		 <td class="key">
		 	수량 :
		 </td>
		 <td>
		 	<input id="num" type="number" name="prodNum" size="50" class="input" value="${prod_num}" placeholder="${prod_num}">
		 	<span  class="hsmg" id="hLayernic"></span>
		 </td>			
		</tr>
		<tr>
		 <td class="key"> 상품 내용 :
		</td>
		 </tr>
		 <tr>
		 <td> </td>
		 <td>
		 	<textarea id="content" name="prodContent" rows="10" cols="40" style="margin-top: -110px;" placeholder="${prod_content}">
		 	${prod_content}
		 	</textarea>
		 	</td>
		</tr>
		
		<tr>
		
		 <td>
		  	사진 첨부
		 </td>
		
		<td>	 	
		 	<input type="file" name="upfile" onchange="preview(this);" style="margin:30px 0px 20px 70px;" multiple/>
		</td>
		</tr>
	   </tbody>
	  </table>
	  <div class="pre_div">
			</div>
		
	  
	  
	  
	 <div class="submitbox">
    	<input type="submit" value="상품 변경" class="prodInsert_btn">
    	<a href="/admin/prodpage?prod_id=${prod_id}"><input type="button" value="변경 취소" class="prodCancel_btn"></a>
    </div>
	 </form>
	 
	 
	  
	 
    </div>
</article>
</section>
<%-- footer.jsp 삽입하기 --%>
<%@ include file="../../footer.jsp" %>
