<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>물품 등록 페이지</title>
<link rel="stylesheet" href="/heehee/resources/css/auction/aucProdRegi.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/product.js"></script> <!-- 이미지파일 업로드때문에 필요 -->
	<script src="/heehee/resources/js/productregiCategory.js"></script>
	
	<form id="auctionForm" action="/heehee/auc/regi" method="POST" enctype="multipart/form-data">
	
	<div class="productRegistrate">
	<div id="main_container">
		<main>
			<p id="regi_title">경매 등록</p>
			<div id="regi_info">
    <p id="prod_img">상품 이미지</p>
    <div id="regi_img">
        <div class="img_container">
            <h6 class="preview" id="prv_img1" style="cursor: pointer;">사진 추가</h6>
            <input type="file" id="input_file1" class="input_file" name="uploadImgs" accept="image/*" multiple>
        </div>
    </div>
    <div id="preview_container">
    	<c:forEach var="img" items="${prodImgList}">
            <div class="img_container" data="${img.imgSeq}">
                <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${img.imgName}" alt="Product Image">
                <button type="button" class="remove_img">x</button>
            </div>
        </c:forEach>
    </div>
    <div id = "new_preview_container">
    
    </div>
	</div>
	<input type="hidden" id="delArr" name="delArr" value="">
				<p id="cate">카테고리</p>
				<div id="elementColumn">
			    <div class="nav_inner1">
			        <div class="nav_title1">
			            <div class="category_name1">
			                <p>대분류</p>
			            </div>
			            <div class="category_content1">
			                <nav>
			                    <ul class="category_list1">
			                        <c:forEach var="bigCate" items="${categoryList}">
									    <li class="bigCategory" data-category="${bigCate.category}">${bigCate.category}</li>
									</c:forEach>
			                    </ul>
			                </nav>
			            </div>
			        </div>
			        <div class="nav_content1">
			            <div class="category_name1">
			                <p>소분류</p>
			            </div>
			            <div class="category_content1">
	                         <ul class="content_list1" id="subCategoryList">
	                             <!-- 여기에 소분류 항목들 추가됨 -->
	                         </ul>
	                         <input id="selCateSeq" type="hidden" value="" name="auctionSeq">
                        </div>
			        </div>
			    </div>
			</div>
	<div class="regi_item">
		<p class="setmargin">글 제목</p>
		<input type="text" class="input_name" placeholder="글 제목을 입력해주세요." name="auctionTitle" required>
	</div>

	<div class="regi_item">
		<p>상품 상태</p>
		<div class="state_radio">
		    <input type="radio" id="new" name="condition" value="신품" checked="checked">
		    <label for="new">신품(미사용)</label>
		
		    <input type="radio" id="slightly_used" name="condition" value="사용감 적음" class="radio_order">
		    <label for="slightly_used">사용감 적음</label>
		
		    <input type="radio" id="used" name="condition" value="사용감 많음" class="radio_order">
		    <label for="used">사용감 많음</label>
		
		    <input type="radio" id="damaged" name="condition" value="고장/파손" class="radio_order">
		    <label for="damaged">고장/파손</label>
		</div>
	</div>
	<div class="regi_item">
		<p>상품 설명</p>
		<textarea id="introduce_box" class="input_name1" name="introduce" placeholder="설명을 입력해주세요." required></textarea>
	</div>
	<div class="regi_item">
		<p>경매 마감일(시간)</p>
		<input id="expTime" type="datetime-local" class="input_name" name="expTime" required>
	</div>
	<div class="regi_item">
		<p>경매 시작가</p>
		<input type="number" class="input_name" name="startPrice" placeholder="가격을 입력해주세요." required>
	</div>
	<div class="regi_item">
		<p>경매 증가 금액</p>
		<input type="number" class="input_name" name="increasePrice" placeholder="증가금액을 입력해주세요." required>
	</div>
		</main>
		</div>
	</div>
	<div id="sub_button">
		<input type="submit" value="등록하기">
	</div>
	</form>
	
	<script>
	$(document).ready(function() {
		let date = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, 16);
		$("#expTime").val(date);
		$("#expTime").attr("min", date);
		$("#testBtn").on("click",requiredCheck);

	    $('.content_list1 p').click(function(event) {
	        event.preventDefault();
	        $('.content_list1 p').removeClass('selected');
	        $(this).addClass('selected');
	    });

	    $("#prv_img1").click(function() {
	        $("#input_file1").click(); // 이미지 클릭 시 파일 입력 필드를 활성화
	    });

	    $("#input_file1").on('change', function() {
	    	$("#new_preview_container").empty();
	        if (this.files.length > 5) {
	            alert('최대 5개의 이미지만 선택 가능합니다.');
	            this.value = ''; // 선택된 파일들 초기화
	            return; // 초과 선택 시 더 이상 진행하지 않음
	        }

	        for (var i = 0; i < this.files.length; i++) {
	            var file = this.files[i];

	            if (file.type.startsWith('image/')) {
	                var reader = new FileReader();

	                var temp = 0;
	                reader.onload = function(e) {
	                    var imgHtml = '<div class="img_container" newIdx="' + temp++ + '"><img src="' + e.target.result + '" alt="Selected Image"><button type="button" class="remove_img">x</button></div>';
	                    $('#new_preview_container').append(imgHtml); // 생성된 이미지 태그를 미리보기 컨테이너에 추가
	                };

	                reader.readAsDataURL(file);
	            }
	        }
	        
	    });
	    
	    function removeFile(index) {
            var inputFile = $('#input_file1')[0];
            var files = Array.from(inputFile.files); // FileList를 배열로 변환
            files.splice(index, 1); // 배열에서 파일 삭제
            updateFileInput(files);
        }
	    
	    function updateFileInput(files) {
            var dataTransfer = new DataTransfer(); // DataTransfer 객체 생성 (크롬, 파이어폭스 등 최신 브라우저에서 지원)
            files.forEach(file => dataTransfer.items.add(file)); // DataTransfer 객체에 파일 추가
            $('#input_file1')[0].files = dataTransfer.files; // 파일 입력 요소에 업데이트된 파일 목록 설정
        }

	    function showSubCategories(category) {
	        $('#subCategoryList').empty(); // 기존의 소분류 항목을 비움
	        $.ajax({
	            type: 'GET',
	            url: '/heehee/sell/detailCate',
	            data: { "category": category },
	            success: function(data) {
	                data.forEach(function(detailCategory) {
	                    var li = $('<li>');
	                    li.html('<p style="cursor: pointer" data="' + detailCategory.productCateSeq + '">' + detailCategory.detailCategory + '</p>');
	                    $('#subCategoryList').append(li);
	                });
	            },
	            error: function() {
	                alert('소분류를 가져오는 중 오류가 발생했습니다.');
	            }
	        });
	    }

	    // 대분류에 마우스가 들어왔을 때 소분류를 동적으로 업데이트하는 이벤트 설정
	    $('.category_list1').on('mouseenter', 'li.bigCategory', function() {
	        var category = $(this).data('category');
	        showSubCategories(category);
	    });
	    
	    
	    $(document).on('click', '#subCategoryList p', function() {
	        $('#subCategoryList p').css('color', ''); // 기존 모든 p 태그의 색상 초기화
	        $(this).css('color', '#abc3ff'); // 클릭한 p 태그의 색상 변경
	        
	        var cateSeq = $(this).attr('data');
	        $("#selCateSeq").val(cateSeq);
	    });
		
		$("#auctionForm").submit(function(event) {
			if (!requiredCheck()) {
				event.preventDefault(); // 조건이 만족되지 않으면 submit 이벤트 중지
			}
		});
		
		function requiredCheck() {
			console.log($("#input_file1").val());
			var uploadImg = $("#input_file1").val();
			if(uploadImg == "") {showTost("이미지를 업로드해주세요."); return false;}
			var selCate = $("#selCateSeq").val();
			if(selCate == "") {showTost("카테고리를 선택해주세요."); return false;}
			return false;
		}
	});

	
</script>
</body>
</html>