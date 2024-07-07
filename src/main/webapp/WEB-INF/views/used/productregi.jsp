<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>물품 등록 페이지</title>
<link rel="stylesheet" href="/heehee/resources/css/productregiModi.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/product.js"></script> <!-- 이미지파일 업로드때문에 필요 -->
	<script src="/heehee/resources/js/productregiCategory.js"></script>
	<form action="/heehee/sell/productRegistry" method="POST" enctype="multipart/form-data">
	<div class="productRegistrate">
	<div id="test">
		<main>
			<p id="regi_title">물품 등록</p>
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
			            <%-- 카테고리 대분류 --%>
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
			            <%-- 카테고리 소분류 --%>
			            <div class="category_content1">
	                         <ul class="content_list1" id="subCategoryList">
	                             <!-- 여기에 소분류 항목들 추가됨 -->
	                         </ul>
	                         <input id="selCateSeq" type="hidden" value="" name="cateSeq" required>
                        </div>
			        </div>
			    </div>
			</div>
	<div class="regi_item">
		<p class="setmargin">글 제목</p>
		<input type="text" class="input_name" placeholder="글 제목을 입력해주세요." name="articleTitle" required>
	</div>
	<div class="regi_item">
		<p>상품 이름</p>
		<input type="text" class="input_name" placeholder="상품명을 입력해주세요." name="prodName" required>
	</div>
	<div class="regi_item">
		<p>상품 상태</p>
		<div class="state_radio">
		    <input type="radio" id="new" name="state" value="신품" checked="checked">
		    <label for="new">신품(미사용)</label>
		
		    <input type="radio" id="slightly_used" name="state" value="사용감 적음" class="radio_order">
		    <label for="slightly_used">사용감 적음</label>
		
		    <input type="radio" id="used" name="state" value="사용감 많음" class="radio_order">
		    <label for="used">사용감 많음</label>
		
		    <input type="radio" id="damaged" name="state" value="고장/파손" class="radio_order">
		    <label for="damaged">고장/파손</label>
		</div>
	</div>
	<div class="regi_item">
		<p>상품 설명</p>
		<textarea id="introduce_box" class="input_name1" name="introduce" placeholder="설명을 입력해주세요." required></textarea>
	</div>
	<div class="regi_item">
		<p>상품 가격</p>
		<input type="number" class="input_name" name="productPrice" placeholder="가격을 입력해주세요." required max="2000000000">
	</div>
	<div class="regi_item">
    <p>거래 유형</p>
    <div class="state_radio1">
        <input type="radio" id="package" name="deal" class="radio_order" value="택배" checked="checked">
        <label for="package">택배</label>

        <input type="radio" id="direct" name="deal" class="radio_order" value="직거래">
        <label for="direct">직거래</label>
    </div>
	</div>
	<div class="regi_item">
	    <p class="setmargin">배송비</p>
	    <input id="d_charge" type="number" class="input_name" name="dCharge" placeholder="배송비를 입력해주세요." disabled required max="2000000000">
	</div>
		</main>
		</div>
	</div>
	<div id="sub_button">
		<input type="submit" value="등록하기">
	</div>
	
	</form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<script>
	$(document).ready(function() {
		$('form').on('submit', function(e) {
	        if (!validateForm()) {
	            e.preventDefault(); // 유효성 검사를 통과하지 못하면 폼 제출을 막음
	        }
	    });
		
		$('#modify_form').submit(function(event) {
	        // 카테고리 선택 확인
	        var selectedCategory = $('#selCateSeq').val();
	        if (selectedCategory === '') {
	            alert('카테고리를 선택해주세요.');
	            event.preventDefault(); // 폼 제출 방지
	        }
	    });
		
		$('#modify_form').submit(function(event) {
	        // 이미지 선택 확인
	        var numFiles = $('#input_file1')[0].files.length + "${prodImgList.size()}";
	        if (numFiles < 1) {
	            alert('이미지를 최소 1개 이상 선택해주세요.');
	            event.preventDefault(); // 폼 제출 방지
	        }
	    });
		
		var delArr = [];
		
		var selectedStatus = "${info.condition}";
        if (selectedStatus === "신품") {
            $("#new").prop("checked", true);
        } else if (selectedStatus === "사용감 적음") {
        	$("#slightly_used").prop("checked", true);
        } else if (selectedStatus === "사용감 많음") {
        	$("#used").prop("checked", true);
        } else if (selectedStatus === "파손") {
        	$("#damaged").prop("checked", true);
        }
    	
        var selectedDeal = "${info.deal}";
        if (selectedDeal === "택배") {
            $("#package").prop("checked", true);
            $('#d_charge').val('');
            $('#d_charge').attr("placeholder", "0");
            $('#d_charge').attr('disabled', false);
            /* $('#d_charge').focus(); */
        } else if (selectedDeal === "직거래") {
            $("#direct").prop("checked", true);
            $('#d_charge').val('');
            $('#d_charge').attr("placeholder", "0");
            $('#d_charge').attr('disabled', true);
        }

	    $('.content_list1 p').click(function(event) {
	        event.preventDefault();
	        $('.content_list1 p').removeClass('selected');
	        $(this).addClass('selected');
	    });

	    $('.radio_order').on('click', function() {
	        var valueCheck = $('input[name="deal"]:checked').attr('id');
	        if (valueCheck === 'package') {
	            $('#d_charge').attr('disabled', false);
	            $('#d_charge').focus();
	        } else {
	            $('#d_charge').val('');
	            $('#d_charge').attr('disabled', true);
	        }
	    });

	    if ($('#package').is(':checked')) {
	        $('#d_charge').attr('disabled', false);
	    } else {
	        $('#d_charge').attr('disabled', true);
	    }
	    
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

	    // 이미지 삭제 기능 추가
	    $(document).on('click', '.remove_img', function() {
	    	var imgSeq = $(this).closest('.img_container').attr('data');
	    	var newIdx = $(this).closest('.img_container').attr('newIdx');

	    	if(imgSeq != undefined) delArr.push(imgSeq);
	    	if(newIdx != undefined) removeFile(newIdx);
	    	$("#delArr").val(delArr);
	    	console.log(imgSeq);
	        $(this).closest('.img_container').remove();
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
	        console.log(cateSeq);
	    });
	});
	
	
	function validateForm() {
        // 이미지 파일 검사
        var inputFile = $('#input_file1')[0];
        var existingImages = "${prodImgList.size()}";
        var newImages = inputFile.files.length;
        if (existingImages + newImages < 1) {
            alert('적어도 하나의 이미지를 추가해주세요.');
            return false;
        }
        // 이미지 파일 타입 검사
        var validImageTypes = ["image/gif", "image/jpeg", "image/png", "image/jpg"];
        for (var i = 0; i < inputFile.files.length; i++) {
            var file = inputFile.files[i];
            if ($.inArray(file.type, validImageTypes) === -1) {
                alert('올바른 이미지 파일 형식이 아닙니다.');
                return false;
            }
        }
        // 카테고리 검사
        var selectedCategory = $("#selCateSeq").val();
        if (!selectedCategory) {
            alert('카테고리를 선택해주세요.');
            return false;
        }
        return true;
    }
</script>
</body>



</html>