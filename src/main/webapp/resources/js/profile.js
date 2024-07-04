$(function() {
	// 프로필 사진 클릭 -> 프로필 편집 영역 보임
	$("#profileImg img").click(function() {
		$("#profile").hide();
		$("#editProfile").show();
	});

	$("#fileInput").on('change', function() {
		readURL(this);
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

// 주소 API
function openAddressApiModify() {
	new daum.Postcode({
		oncomplete: function(data) {
			console.log(data);
			$("#mod_addr_input").val(data.address);
		}
	}).open();
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