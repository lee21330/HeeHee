$(document).ready(function() {
    // Function to copy the URL to clipboard
    function copyToClipboard(text) {
        var $temp = $('<input>');
        $('body').append($temp);
        $temp.val(text).select();
        document.execCommand('copy');
        $temp.remove();
        alert('URL이 복사되었습니다.');
    }

    // Event listener for copying the URL
    $('#url_copy').on('click', function(e) {
        e.preventDefault();
        var link = window.location.href; // Get the current page URL
        copyToClipboard(link); // Call the function to copy the URL
    });

    // Event listener for scrolling to the top
    $('#gotop').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, 500);
    });
    
    $("#regi_img").on("change", function(e) {
		var file = e.target.files[0];
		if(isImageFile(file)) {
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
});

function isImageFile(file) {
	// 파일명에서 확장자를 가져옴
	var ext = file.name.split(".").pop().toLowerCase(); 
	return ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) ? false : true;
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