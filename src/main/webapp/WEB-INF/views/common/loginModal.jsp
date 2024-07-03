<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${path}/resources/css/loginModal.css">

<script>

	var idFlag = false;
	var nickNameFlag = false;
	var emailFlag = false;
	var authFlag = false;
	
	$(window).on('unload', function() {
	    var cookieName = 'Authorization';
	    document.cookie = encodeURIComponent(cookieName) + "=deleted; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
	});
	
	
	
	$(function() {
		$("#loginBtn").on("click", openLogin);
		$("#loginClose").on("click", closeLogin);
		$("#signupBtn").on("click", openSignup);
		$("#google_log_btn").on("click",googleLogin);
		$("#kakao_log_btn").on("click",kakaoLogin);
		$("#naver_log_btn").on("click",naverLogin);
		$("#addrBtn").on("click", openAddressApi);
		$("#id_dup_btn").on("click",dupIdCheck);
		$("#nick_dup_btn").on("click",dupNickCheck);
		$("#email_dup_btn").on("click",dupEmailCheck);
		$("#sms_send_btn").on("click",smsSend);
		$("#sms_auth_btn").on("click",smsAuthCheck);
		$("#reg_user_btn").on("click", signUp);
		
		document.getElementById("phone_input").addEventListener("keyup", function(event) {
            $("#phone_input").val(inputPhoneNumber(event.target));
        });
		
	$("#logPw").on("keyup",function(key){
		if(key.keyCode==13) {
			login();
		}
	 });
	
	// OAuth 가입 체크
	var status = "${param.status}";
	if(status == "signup") {
		$("#loginModal").addClass("show");
		$("#loginArea").removeClass("show");
        $("#signupArea").addClass("show");
        $(".modal_body").css("width", "400px");
        $(".modal_body").css("height", "auto");
        
    	 // 새로고침 눌렀을 시 파라미터 초기화
    	$(document).keydown(function(e){
    		key = (e)?e.keyCode:event.keyCode;
    		if(key == 116 || (event.ctrlKey && event.keyCode == 82)) {
    			history.replaceState({}, null, location.pathname);
    		}
    	})
	}
	});
	
	function googleLogin() {
		location.href="/heehee/oauth2/authorize/google";
	}
	
	function kakaoLogin() {
		location.href="/heehee/oauth2/authorize/kakao";
	}
	
	function naverLogin() {
		location.href="/heehee/oauth2/authorize/naver";
	}
	
	function openLogin() {
		$("#loginModal").addClass("show");
		$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
	}

	function closeLogin() {
		$("#loginModal").removeClass("show"); /* 로그인 */
		$("#passArea").removeClass("show"); /* 본인인증 */
		$("#signupArea").removeClass("show"); /* 회원가입 */
		$("#loginArea").addClass("show");
		$(".selDiv").children().removeClass("topSel");
		$(".loginBox").addClass("topSel");
		$(".modal_body").css("height", "auto");
		$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
		
		initLogin();
	}
	
	function openSignup() {
		$("#loginModal").addClass("show");
		$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
	}

	function initLogin() {
		$("#logId").val("");
		$("#logPw").val("");
		$("#imgFile").val("");
		$("#signId").val("");
		$("#signPw").val("");
		$("#signName").val("");
	}
	
	function join(sel) { 
		if (sel == "signup") {
			// 로그인 화면 없애고 본인인증 화면 보여주기
			$("#loginArea").removeClass("show");
            $("#signupArea").addClass("show");
            $(".modal_body").css("width", "400px");
            $(".modal_body").css("height", "auto");
			
		} else {
			// 회원가입 화면 보여주기
			$("#loginArea").removeClass("show");
			$("#signupArea").addClass("show");
			$(".modal_body").css("margin-top", "70px"); /* 입력칸이 많아서 맨 윗부분 가려짐, 간격주기 */
			$(".selDiv").children().removeClass("topSel");
			$(".signupBox").addClass("topSel");
			$(".modal_body").css("width", "350px"); /* 본인인증 화면이랑 가로 넓이 다름, 기존 크기로 변경 */
			$(".modal_body").css("height", "auto");
		}
	}
	
	function login() {
		var userId = $("#logId").val();
		var userPw = $("#logPw").val();
		
		$.ajax({
		    url: '/heehee/user/login-processing',
		    method: 'POST',
		    data : {"userId" : userId, "userPw": userPw},
		    success: function (data, status, xhr) {
		    	console.log(xhr.getResponseHeader('Authorization'))// 헤더에 있는 토큰을 받아와서
		    	localStorage.setItem('accessToken', xhr.getResponseHeader('Authorization')) // 로컬스토리지
		    	closeLogin();
		    	location.reload();
		    },
		    error: function (data, status, err) {
		    	if (xhr.status === 404) {
	                showTost("존재하지 않는 계정입니다.");
	            } else {
	                showTost("로그인 중 오류가 발생했습니다. 다시 시도해 주세요.");
	            }
		    }
		});
	}
	
	function logout() {
		$.ajax({
		    url: '/heehee/user/logout',
		    method: 'GET',
		    success: function (data, status, xhr) {
		    	console.log(data);
		    	console.log(status);
		    	console.log(xhr);
		    	location.reload();
		    },error: function (xhr, status, err) {
		    	showTost("로그아웃 중 오류가 발생했습니다. 다시 시도해 주세요.");
		    }
		});
	}
	
	function loginCheck() {
		$.ajax({
		    url: '/heehee/user/loginCheck',
		    method: 'GET',
		    success: function (data) {
		    	$(".login_menu").html(data);
		    	$("#loginBtn").on("click", openLogin);
				$("#loginClose").on("click", closeLogin);
				$("#signupBtn").on("click", openSignup);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function openAddressApi() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            console.log(data);
	            $("#addr_input").val(data.address);
	        }
	    }).open();
	}
	
	function dupIdCheck() {
		var signId = $("#signId").val();
		$.ajax({
		    url: '/heehee/user/dupIdCheck',
		    method: 'GET',
		    data: {"id": signId},
		    success: function (data) {
		    	$("#id_dup_result").attr("style", "display: block");
		    	if(data.able == true) {
		    		$("#id_dup_result").removeClass("red");
		    		$("#id_dup_result").addClass("green");
		    		idFlag = true;
		    	} else {
		    		$("#id_dup_result").removeClass("green");
		    		$("#id_dup_result").addClass("red");
		    		idFlag = false;
		    	}
		    	$("#id_dup_result").text(data.message);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function dupNickCheck() {
		var nickName = $("#signNickName").val()
		$.ajax({
		    url: '/heehee/user/dupNickCheck',
		    method: 'GET',
		    data: {"nickName": nickName},
		    success: function (data) {
		    	$("#nick_dup_result").attr("style", "display: block");
		    	if(data.able == true) {
		    		$("#nick_dup_result").removeClass("red");
		    		$("#nick_dup_result").addClass("green");
		    		nickNameFlag = true;
		    	} else {
		    		$("#nick_dup_result").removeClass("green");
		    		$("#nick_dup_result").addClass("red");
		    		nickNameFlag = false;
		    	}
		    	$("#nick_dup_result").text(data.message);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function dupEmailCheck() {
		var email = $("#signEmail").val();
		$.ajax({
		    url: '/heehee/user/dupEmailCheck',
		    method: 'GET',
		    data: {"email": email},
		    success: function (data) {
		    	$("#email_dup_result").attr("style", "display: block");
		    	if(data.able == true) {
		    		$("#email_dup_result").removeClass("red");
		    		$("#email_dup_result").addClass("green");
		    		emailFlag = true;
		    	} else {
		    		$("#email_dup_result").removeClass("green");
		    		$("#email_dup_result").addClass("red");
		    		emailFlag = false;
		    	}
		    	$("#email_dup_result").text(data.message);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function smsSend() {
		var phoneNum = $("#phone_input").val();
		$.ajax({
		    url: '/heehee/sms/send',
		    method: 'POST',
		    data: {"phoneNum": phoneNum},
		    success: function (data) {
		    	console.log(data);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function smsSend() {
		var phoneNum = $("#phone_input").val();
		$.ajax({
		    url: '/heehee/sms/send',
		    method: 'POST',
		    data: {"phoneNum": phoneNum},
		    success: function (data) {
		    	console.log(data);
		    	showTost(data.message);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function smsAuthCheck() {
		var authNo = $("#sms_auth_input").val();
		$.ajax({
		    url: '/heehee/sms/authNo',
		    method: 'GET',
		    data: {"authNo": authNo},
		    success: function (data) {
		    	console.log(data);
		    	$("#sms_auth_result").attr("style", "display: block");
		    	if(data.status == 200) {
		    		$("#sms_auth_result").removeClass("red");
		    		$("#sms_auth_result").addClass("green");
		    		authFlag = true;
		    	} else {
		    		$("#sms_auth_result").removeClass("green");
		    		$("#sms_auth_result").addClass("red");
		    		authFlag = false;
		    	}
		    	$("#sms_auth_result").text(data.message);
		    },error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function signUp() {
		var authNo = $("#sms_auth_input").val();
		if(idFlag == false) {showTost("ID 중복 인증 해주세요"); return false;}
		if(nickNameFlag == false) {showTost("닉네임 중복 인증을 해주세요"); return false;}
		if(emailFlag == false) {showTost("이메일 중복 인증을 해주세요"); return false;}
		if(authFlag == false) {showTost("핸드폰 인증을 진행해주세요"); return false;}
		
		$.ajax({
		    url: '/heehee/user/signup',
		    method: 'POST',
		    data: {
		    	"username": $("#signId").val(),
		    	"realName": $("#realName").val(),
		    	"password": $("#signPw").val(),
		    	"email": $("#signEmail").val(),
		    	"phoneNum": $("#phone_input").val(),
		    	"accountNum": $("#acc_num_input").val(),
		    	"nickName": $("#signNickName").val(),
		    	"address": $("#addr_input").val(),
		    	"detailAddress": $("#detail_addr_input").val()
		    	},
		    success: function (data) {
		    	console.log(data);
		    	if(data.success == true) {
		    		closeLogin();
		    		showTost("회원가입에 성공했습니다.");
		    	} else {
		    		closeLogin();
		    		showTost("회원가입에 실패했습니다. 관리자에게 문의 바랍니다.");
		    	}
		    },error: function (data, status, err) {
		    	console.log(err);
		    	closeLogin();
		    	showTost("회원가입에 실패했습니다. 관리자에게 문의 바랍니다.");
		    }
		});
	}
</script>
</head>
<body>
	<div class="modal" id="loginModal">
        <div class="modal_body">
            <div class="m_body">
                <div class="close_btn" id="loginClose">
                    <img src="${path}/resources/images/icon_login_close.png" alt="로그인 창 닫기 아이콘">
                </div>
                <div class="selDiv">
                    <div>
                        <img src="${path}/resources/images/logo.png" alt="로고 이미지">
                    </div>
                </div>
                <%-- 로그인 --%>
                <div id="loginArea" class="selectedArea show">
                    <div class="modal_label">
                        <input type="text" class="input_box" id="logId" placeholder="아이디" />
                    </div>
                    <div class="modal_label">
                        <input type="password" class="input_box" id="logPw" placeholder="비밀번호" />
                    </div>
                    <div class="log_find">
                        <div>아이디 찾기</div>
                        <div>|</div>
                        <div>비밀번호 찾기</div>
                    </div>
                    <div class="modal_btn save" id="log_btn" onclick="login()">로그인</div>
                    
                    <div class="modal_btn google_save" id="google_log_btn">구글 로그인</div>
                    <div class="modal_btn kakao_save" id="kakao_log_btn">카카오 로그인</div>
                    <div class="modal_btn naver_save" id="naver_log_btn">네이버 로그인</div>
                    <div id="signup_btn" class="signup" onclick="join('signup')">회원가입</div>
                </div>
                <%-- 본인인증 --%>
                <div id="passArea" class="selectedArea">
                    
                    <div class="modal_pass">
                        <input class="pass_input" placeholder="주민번호 앞자리">
                        <input class="pass_input" placeholder="주민번호 뒷자리">
                        <div>
                            <input class="pass_btn" type="button" value="실명인증">
                        </div>
                    </div>
                    <div id="signup_btn" class="modal_btn save" onclick="join('')">다음</div>
                </div>
                <%-- 회원가입 --%>
                <div id="signupArea" class="selectedArea">
                    <form method="POST" enctype="multipart/form-data" id="signUpForm">
	                    <div class="modal_label">
	                        <input id="realName" type="text" class="input_box" placeholder="이름" value="${param.name}">
	                    </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" id="signId" name="id" placeholder="사용하실 아이디를 입력하세요" />
                            <input type="button" id="id_dup_btn" class="dup_btn" value="중복체크">
                            <div id="id_dup_result" class="dup_result"></div>
                        </div>
                        <div class="modal_label">
                            <input type="password" class="input_box" id="signPw" name="pw" placeholder="사용하실 비밀번호를 입력하세요" />
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" id="signNickName" name="name" placeholder="사용하실 닉네임을 입력하세요" value="${param.nickName}"/>
                            <input type="button"  id="nick_dup_btn" class="dup_btn" value="중복체크">
                            <div id="nick_dup_result" class="dup_result"></div>
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" id="signEmail" placeholder="사용하실 이메일을 입력하세요" value="${param.email}">
                            <input type="button"  id="email_dup_btn" class="dup_btn" value="중복체크">
                            <div id="email_dup_result" class="dup_result"></div>
                        </div>
                        <div class="modal_label">
                            <input type="text" id="addr_input" class="input_box" placeholder="주소를 검색하세요">
                            <input type="button" id="addrBtn" class="dup_btn" value="검색">
                        </div>
                        <div class="modal_label">
                            <input type="text" id="detail_addr_input" class="input_box" placeholder="사용하실 상세 주소">
                        </div>
                        <div class="modal_label">
                            <input type="text" id="phone_input" class="input_box" placeholder="사용하실 전화번호를 입력하세요" maxlength="13" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}">
                            <input type="button" id="sms_send_btn" class="dup_btn sms_send_btn" value="인증번호 발송">
                        </div>
                        <div class="modal_label">
                        	<input type="text" id="sms_auth_input" class="input_box" placeholder="인증번호를 입력해주세요.">
                        	<input type="button" id="sms_auth_btn" class="dup_btn sms_auth_btn" value="인증하기">
                        	<div id="sms_auth_result" class="dup_result"></div>
                        </div>
                        <div class="modal_bank">
                            <select>
                                <option>은행</option>
                                <option>신한은행</option>
                            </select>
                        </div>
                        <div class="modal_bank">
                            <input id="acc_num_input"type="text" placeholder="계좌번호를 입력하세요">
                        </div>
                    </form>
                    <div class="modal_btn save" id="reg_user_btn">회원가입</div>
                </div>
            </div>
        </div>
    </div>
</body>