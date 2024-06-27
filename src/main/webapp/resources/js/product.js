$(document).ready(function() {
	jQuery.noConflict();
	
	$(document).ready(function() {
	
		$('.product_slider').slick({
			  infinite: true,
			  slidesToShow: 1,
			  slidesToScroll: 1
			});
	});

    // URL을 클립보드에 복사하는 함수
    function copyToClipboard(text) {
        var $temp = $('<input>');
        $('body').append($temp);
        $temp.val(text).select();
        document.execCommand('copy');
        $temp.remove();
        alert('URL이 복사되었습니다.');
    }

    // URL 복사를 위한 이벤트 리스너
    $('#url_copy').on('click', function(e) {
        e.preventDefault();
        var link = window.location.href; // 현재 페이지의 URL을 가져옵니다
        copyToClipboard(link); // URL을 복사하는 함수를 호출합니다
    });

    // 맨 위로 스크롤하는 이벤트 리스너
    $('#gotop').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, 500);
    });
    
    $("#regi_img").on("change", function(e) {
        var file = e.target.files[0];
        if (isImageFile(file)) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $("#preview").attr("src", e.target.result);
            }
            reader.readAsDataURL(file);
        } else {
            alert("이미지 파일만 첨부 가능합니다.");
            $("#regi_img").val("");
            $("#preview").attr("src", "");
        }
    });
    
    $("img[id='preview']").click(function () {
        $("#input_file").click();
    });

    // 슬라이더 초기화
    $('.product-images').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1
    });
});


function isImageFile(file, imgElement, fileInput) {
    var ext = file.name.split(".").pop().toLowerCase();
    if ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) {
        // 이미지 파일이 아닐 경우 alert를 띄우고 src를 지정된 이미지로 변경
        alert("이미지 파일만 선택 가능합니다.");
        imgElement.src = "/heehee/resources/images/picture.png";
        // 파일 입력 요소의 값을 초기화
        fileInput.value = "";
        return false;
    } else {
        return true;
    }
}

$(function() {
			$(".category_list li").mouseenter(function() {
			 	var categoryName = $(this).text();
				$(".detail_category .category_name p").text(categoryName); 
				$(".detail_category").show();
				$(".category_list li").css({
					"background" : "white",
					"color" : "black"
				});
				$(this).css({
					"background" : "#3F51A1",
					"color" : "white"
				});
			});
		});