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

// 닉네임 값 변경되면 '중복체크' 버튼 활성화/비활성화
function changeBtn(originalNick) {
	var originalNick = $("#nickName").val(); // 원래 닉네임 값
	var nickName = $("#nickName").val();

	if (nickName !== originalNick) { // 입력된 닉네임과 원래 닉네임이 다를 경우
		$('#btn-originalNick').hide();
		$('#btn-nick').show();
	} else {
		$('#btn-originalNick').show();
		$('#btn-nick').hide();
	}
}

// 중복 체크 버튼 클릭 
function dupMyNickCheck(originalNick) {
	var nickName = $("#nickName").val();
	if (nickName === "") {
		//$("#my_nick_dup_result").css("visibility", "visible");
		$("#my_nick_dup_result").text("닉네임을 입력해주세요");
	} else if (originalNick === nickName) {
		// 원래 닉네임과 입력된 닉네임이 같은 경우 
	} else {
		$.ajax({
			url: '/heehee/mypage/profile/dupNickCheck',
			method: 'GET',
			data: { "nickName": nickName },
			success: function(data) {
				$("#my_nick_dup_result").css("visibility", "visible");
				if (data.able == true) {
					$("#my_nick_dup_result").removeClass("red").addClass("green");
				} else {
					$("#my_nick_dup_result").removeClass("green").addClass("red");
				}
				$("#my_nick_dup_result").text(data.message);
			},
			error: function(xhr, status, error) {
				console.error(error);
			}
		});
	}
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

// 현재 비밀번호 체크
function currentPwCheck() {
	var currentPw = $("#currentPw").val();
	if (currentPw === '') {
		$("#my_pw_check").text("비밀번호를 입력하세요.");
	} else {
		$.ajax({
			url: '/heehee/mypage/profile/currentPwCheck',
			method: 'POST',
			data: { "currentPw": currentPw },
			success: function(data) {
				location.href = '';
			}, error: function(data, status, err) {
				console.log(err);
			}
		});
	}
}

