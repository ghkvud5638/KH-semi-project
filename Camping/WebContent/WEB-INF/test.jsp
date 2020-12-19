<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//JavaScript
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

// submit_result는 form에 onsubmit으로 사용하기 위해 만들었다!
function submit_result() {
	if(!result) {
		alert("파일 갯수 혹은 확장자를 확인해주세요. 이미지만 업로드 가능합니다.");
	}
	return result;
}

</script>
</head>
<body>
<section class="addImageWrapper">
	<div class="addImageDiv">
		<img src="../resources/img/addImage2.png" class="addImageIcon">
		<p class="addImageText">사진첨부</p>
		<input type="file" id="addImageHidden" name="image" onchange="preview(this);" multiple>
	</div>
</section>

<section class="previewWrapper">
	<p class="previewText">미리보기</p>
	<div class="pre_div">
	</div>
</section>



</body>
</html>