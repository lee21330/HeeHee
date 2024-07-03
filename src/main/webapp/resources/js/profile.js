$(function() {
	$("#profileImg img").click(function() {
		$("#profile").hide();
		$("#editProfile").show();
	});

	$("#fileInput").on('change', function() {
		readURL(this);
	});

	$("#btn_delete").click(function() {
		var defaultImageUrl = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg';
		$('#fileInput').val('');
		$('#original').val('');
		$('#previewImage').attr('src', defaultImageUrl);
	});

});
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#previewImage').attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	}
}

function openAddressApiModify() {
	new daum.Postcode({
		oncomplete: function(data) {
			console.log(data);
			$("#mod_addr_input").val(data.address);
		}
	}).open();
}

function chooseFile() {
	$('#fileInput').click();
}

function changeBtn() {
	$('#btn-originalNick').hide();
	$('#btn-nick').show();
}
function dupMyNickCheck(originalNick) {
	var nickName = $("#nickName").val();
	if (nickName === "") {
		$("#my_nick_dup_result").css("visibility", "visible");
		$("#my_nick_dup_result").text("닉네임을 입력해주세요");
	} else if (originalNick===nickName) {
		
	} else {
		$.ajax({
			url: '/heehee/mypage/profile/dupNickCheck',
			method: 'GET',
			data: { "nickName": nickName },
			success: function(data) {
				$("#my_nick_dup_result").css("visibility", "visible");
				if (data.able == true) {
					$("#my_nick_dup_result").removeClass("red");
					$("#my_nick_dup_result").addClass("green");
				} else {
					$("#my_nick_dup_result").removeClass("green");
					$("#my_nick_dup_result").addClass("red");
				}
				$("#my_nick_dup_result").text(data.message);
			}, error: function(data, status, err) {
				console.log(err);
			}
		});
	}



}
function updateProfile(originalNick) {
	var nick = $("#my_nick_dup_result").text();
	var newNick = $("#nickName").val();
	if (nick != "중복 확인") {
		alert("닉네임 중복체크를 해주세요");
	} else {
		$.ajax({
			url: '/heehee/mypage/profile/updateProfile',
			method: 'POST',
			data: { "profileImages": profileImages, "originalProfileImg": originalProfileImg, 'nickName': nickName, 'userIntroduce': userIntroduce },
			success: function(data) {

			}, error: function(data, status, err) {
				console.log(err);
			}
		});
	}
}
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