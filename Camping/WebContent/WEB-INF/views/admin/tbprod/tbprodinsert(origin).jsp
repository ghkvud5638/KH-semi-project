<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header.jsp 삽입하기 --%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type = "text/css">
 	.prodInsert_page_wrap{ 
 	flex-wrap: wrap; 
	height: 765px; 
 	width: 700px; 
 	background-color: #ccc; 
 	margin: -256px 97px 0px 500px; 
 	opacity: 0.6; 
 	} 
 	.prodInsert_page_wrap_content{ 
 	display: table-row-group; 
 	vertical-align: middle; 
 	} 

	.prodInsert_page_wrap_content > tr{ 
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
		
		if(files.length > 2 ) {
			alert("최대 2 	장까지만 업로드 가능합니다.");
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
    <div class="prodInsert_page_wrap">
	 <form action="/admin/prodinsert" method="post" enctype="multipart/form-data">
	  <table summary="상품 정보 입력 받는 표">
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
	   <tbody class="prodInsert_page_wrap_content">
	   	<tr>
	   	<td class="key">
	   	상품 이름 : 
	   	</td>
		 <td>
		 	<input id="prod_name" type="text" name="prod_Name" class="input" size="50" value="${prod_name}" placeholder="상품 이름을 입력하세요">
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		  	금액 : 
		 	</td>
		 <td>
		 	<input id="prod_price" type="number" name="prod_Price" size="50" class="input" value="${prod_price}" placeholder="금액을 입력하세요">
		 	<span  class="hsmg" id="hLayernic"></span>
		 </td>			
		</tr>
		<tr>
		 <td class="key">
		 	수량 :
		 </td>
		 <td>
		 	<input id="prod_num" type="number" name="prod_Num" size="50" class="input" value="${prod_num}" placeholder="상품 수량을 입력하세요">
		 	<span  class="hsmg" id="hLayernic"></span>
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
    	<input type="submit" value="상품 등록" class="prodInsert_btn">
    	<a href="/admin/prodlist"><input type="button" value="등록 취소" class="prodCancel_btn"></a>
    </div>
	 </form>
	 
	 
	  
	 
    </div>
</article>
</section>
<%-- footer.jsp 삽입하기 --%>
<%@ include file="../../footer.jsp" %>
