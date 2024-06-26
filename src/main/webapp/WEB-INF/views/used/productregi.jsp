<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 물품 등록 페이지</title>
<link rel="stylesheet" href="/heehee/resources/css/productregiModi.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/product.js"></script> <!-- 이미지파일 업로드때문에 필요 -->
	<script src="/heehee/resources/js/productregiCategory.js"></script>
	<form action="#" method="GET">
	<div class="productRegistrate">
	<div id="test">
		<main>
			<p id="regi_title">물품 등록</p>
			<div id="regi_info">
    <p id="prod_img">상품 이미지</p>
    <div id="regi_img">
        <div class="img_container">
            <h6 class="preview" id="prv_img1" style="cursor: pointer;">사진 추가</h6>
            <input type="file" id="input_file1" class="input_file" accept="image/*" multiple>
        </div>
    </div>
    <div id="preview_container"></div>
	</div>
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
                        </div>
			        </div>
			    </div>
			</div>
	<div class="regi_item">
		<p class="setmargin">글 제목</p>
		<input type="text" class="input_name" placeholder="글 제목을 입력해주세요.">
	</div>
	<div class="regi_item">
		<p>상품 이름</p>
		<input type="text" class="input_name" placeholder="상품명을 입력해주세요.">
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
		<textarea id="introduce_box" class="input_name1" placeholder="설명을 입력해주세요."></textarea>
	</div>
	<div class="regi_item">
		<p>상품 가격</p>
		<input type="number" class="input_name" placeholder="가격을 입력해주세요.">
	</div>
	<div class="regi_item">
    <p>거래 유형</p>
    <div class="state_radio1">
        <input type="radio" id="package" name="deal_type" class="radio_order" value="택배" checked="checked">
        <label for="package">택배</label>

        <input type="radio" id="direct" name="deal_type" class="radio_order" value="직거래">
        <label for="direct">직거래</label>
    </div>
	</div>
	<div class="regi_item">
	    <p class="setmargin">배송비</p>
	    <input id="d_charge" type="number" class="input_name" placeholder="배송비를 입력해주세요." disabled>
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
            $('#d_charge').attr('disabled', false);
            /* $('#d_charge').focus(); */
        } else if (selectedDeal === "직거래") {
            $("#direct").prop("checked", true);
            $('#d_charge').val('');
            $('#d_charge').attr('disabled', true);
        }

	    $('.content_list1 p').click(function(event) {
	        event.preventDefault();
	        $('.content_list1 p').removeClass('selected');
	        $(this).addClass('selected');
	    });

	    $('.radio_order').on('click', function() {
	        var valueCheck = $('input[name="deal_type"]:checked').attr('id');
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
	        // 파일 선택 시 실행되는 함수
	        $('#preview_container').empty(); // 미리보기 컨테이너 초기화

	        if (this.files.length > 5) {
	            alert('최대 5개의 이미지만 선택 가능합니다.');
	            this.value = ''; // 선택된 파일들 초기화
	            return; // 초과 선택 시 더 이상 진행하지 않음
	        }

	        // 선택된 모든 파일에 대해 루프
	        for (var i = 0; i < this.files.length; i++) {
	            var file = this.files[i];

	            // 파일이 이미지인지 확인
	            if (file.type.startsWith('image/')) {
	                var reader = new FileReader();

	                // 파일 읽기가 완료되면 실행
	                reader.onload = function(e) {
	                    var imgHtml = '<img src="' + e.target.result + '" style="width:125px; height:125px; object-fit: contain; margin: 10px; border: 1px solid #acacac; border-radius: 10px;">';
	                    $('#preview_container').append(imgHtml); // 생성된 이미지 태그를 미리보기 컨테이너에 추가
	                };

	                // 파일 읽기 시작
	                reader.readAsDataURL(file);
	            }
	        }
	    });

	    function showSubCategories(category) {
	        $('#subCategoryList').empty(); // 기존의 소분류 항목을 비움
	        $.ajax({
	            type: 'GET',
	            url: '/heehee/sell/detailCate',
	            data: { "category": category },
	            success: function(data) {
	                data.forEach(function(detailCategory) {
	                    var li = $('<li>');
	                    li.html('<p style="cursor: pointer">' + detailCategory.detailCategory + '</p>');
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
	    });
	});

</script>
</body>



</html>