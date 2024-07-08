$(function() {
	// 프로필 사진 클릭 -> 프로필 편집 영역 보임
	$("#profileImg img").click(function() {
		$("#profile").hide();
		$("#editProfile").show();
	});

	$("#fileInput").on('change', function() {
		readURL(this);
	});

	// 모달창 숨기기
	$("#phoneModal").hide();
	$("#addressModal").hide();
	$("#pwModal").hide();
	$("#drawalModal").hide();

	// 전화번호 변경 모달
	$("#btn_send_phone").on("click", phoneSend);
	$("#btn_auth_phone").on("click", phoneAuthCheck);
	$("#btn_update_phone").on("click", updatePhone);
	document.getElementById("myphone_input").addEventListener("keyup", function(event) {
		$("#myphone_input").val(inputPhoneNumber(event.target));
	});

});

// 이미지 미리보기 
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#previewImage').attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	}
}

// 사진 변경 버튼
function chooseFile() {
	$('#fileInput').click();
}

// 전화번호 변경 모달 
function showPhone() {
	$("#phoneModal").show();
	$("body").css("overflow", "hidden");
}
function hidePhone() {
	$("#phoneModal").hide();
	$("body").css("overflow", "auto");

}
function phoneSend() {
	var phoneNum = $("#myphone_input").val();
	$.ajax({
		url: '/heehee/sms/send',
		method: 'POST',
		data: { "phoneNum": phoneNum },
		success: function(data) {
			console.log(data);
			showTost(data.message);
		}, error: function(data, status, err) {
			console.log(err);
		}
	});
}

function phoneAuthCheck() {
	var authNo = $("#phone_auth_input").val();
	$.ajax({
		url: '/heehee/sms/authNo',
		method: 'GET',
		data: { "authNo": authNo },
		success: function(data) {
			console.log(data);
			$("#phone_auth_result").attr("style", "display: block");
			if (data.status == 200) {
				$("#phone_auth_result").removeClass("red");
				$("#phone_auth_result").addClass("green");
				authFlag = true;
			} else {
				$("#phone_auth_result").removeClass("green");
				$("#phone_auth_result").addClass("red");
				authFlag = false;
			}
			$("#phone_auth_result").text(data.message);
		}, error: function(data, status, err) {
			console.log(err);
		}
	});
}
function updatePhone() {
	if (authFlag == false) { showTost("핸드폰 인증을 진행해주세요"); return false; }
	$.ajax({
		url: '/heehee/mypage/profile/updatePhone',
		method: 'POST',
		data: {
			"phoneNum": $("#myphone_input").val()
		},
		success: function(data) {
			console.log(data);
			if (data.success == true) {
				showTost(data.message);
			} else {
				showTost(data.message);
			}
			window.location.reload();
		}, error: function(data, status, err) {
			console.log(err);
			showTost(data.message);
		}
	});

}

// 전화번호 변경 모달 
function showAddress() {
	$("#addressModal").show();
	$("body").css("overflow", "hidden");
}
function hideAddress() {
	$("#addressModal").hide();
	$("body").css("overflow", "auto");

}

// 주소 변경 모달
// 주소 API
function openAddressApiModify() {
	new daum.Postcode({
		oncomplete: function(data) {
			console.log(data);
			$("#mod_addr_input").val(data.address);
		}
	}).open();
}

function updateAddress() {
	var address = $("#mod_addr_input").val();
	var detailAddress = $("#mod_detail_addr_input").val();

	$.ajax({
		url: '/heehee/mypage/profile/updateAddress',
		method: 'POST',
		data: {
			"address": address,
			"detailAddress": detailAddress
		},
		success: function(data) {
			console.log(data);
			if (data.success == true) {
				showTost(data.message);
			} else {
				showTost(data.message);
			}
			window.location.reload();
		}, error: function(data, status, err) {
			console.log(err);
			showTost(data.message);
		}
	});

}
// 비밀번호 변경 모달 
function showPW() {
	$("#pwModal").show();
	$("body").css("overflow", "hidden");
}
function hidePW() {
	$("#pwModal").hide();
	$("body").css("overflow", "auto");

}


function changePW() {
	var currentPassword = $("#currentPassword").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword").val();
	if (currentPassword === '') {
		$("#new_pw_check").text("현재 비밀번호를 입력하세요.");
	}
	
	else if(password === ''){
		$("#new_pw_check").text("새로운 비밀번호를 입력하세요.");
	
	}else if(confirmPassword === ''){
		$("#new_pw_check").text("새로운 비밀번호를 확인하세요.");
	
	}else{
		$.ajax({
		url: '/heehee/mypage/profile/updatePw',
		method: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify({
			currentPassword: currentPassword,
			password: password,
			confirmPassword: confirmPassword
		}),
		success: function(data) {
			if (data.able === true) {
				$("#new_pw_check").text("");
				showTost(data.message);
				setTimeout(() => window.location.reload(), 1000);
			} else {
				$("#new_pw_check").text(data.message);
			}
			
		},
		error: function(xhr, status, error) {
			console.error(error);
		}
	});
	}
	
}

// 회원탈퇴 모달 
function showDrawal() {
	$("#drawalModal").show();
	$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
}
function hideDrawal() {
	$("#drawalModal").hide();
	$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
}
function deleteUser() {
	$.ajax({
		url: "/heehee/mypage/profile/deleteUser",
		method: "DELETE",
		success: function(data) {
			console.log(data);
			logout();
		},
		error: function(err) {
			console.log(err)
		}
	});
}