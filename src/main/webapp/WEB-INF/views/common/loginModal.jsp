<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${path}/resources/css/loginModal.css">

<script>
	$(function() {
		$("#loginBtn").on("click", openLogin);
		$("#loginClose").on("click", closeLogin);
		$("#signupBtn").on("click", openSignup);
	});

	function openLogin() {
		$("#loginModal").addClass("show");
		$("body").css("overflow", "hidden"); /* (추가) 모달 열리면 스크롤 불가능 */
	}

	function closeLogin() {
		$("#loginModal").removeClass("show");
		$("#signupArea").removeClass("show");
		$("#loginArea").addClass("show");
		$(".selDiv").children().removeClass("topSel");
		$(".loginBox").addClass("topSel");
		/* $(".modal_body").css("height", "450px"); */
		$(".modal_body").css("height", "auto");
		$("body").css("overflow", "scroll"); /* (추가) 모달 닫히면 스크롤 가능 */
		initLogin();
	}
	
	function openSignup() {
		$("#loginModal").addClass("show");
		$("body").css("overflow", "hidden");
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
		if (sel == "login") {
			$("#signupArea").removeClass("show");
			$("#loginArea").addClass("show");
			$(".selDiv").children().removeClass("topSel");
			$(".loginBox").addClass("topSel");
			$(".modal_body").css("height", "auto");
		} else {
			$("#loginArea").removeClass("show");
			$("#signupArea").addClass("show");
			$(".selDiv").children().removeClass("topSel");
			$(".signupBox").addClass("topSel");
			$(".modal_body").css("height", "auto");
		}
	}
</script>
</head>
<body>
	<div class="modal" id="loginModal">
        <div class="modal_body">
            <div class="m_body">
                <!-- <div class="close_btn" id="loginClose">X</div> -->
                <div class="close_btn" id="loginClose">
                    <img src="${path}/resources/images/icon_login_close.png" alt="로그인 창 닫기 아이콘">
                </div>
                <div class="selDiv">
                    <!-- <div class="loginBox topSel">로그인</div>
                    <div class="signupBox"> 회원가입</div> -->
                    <div>
                        <img src="${path}/resources/images/logo.png" alt="로고 이미지">
                    </div>
                </div>
                <div id="loginArea" class="selectedArea show">
                    <div class="modal_label">
                        <input type="text" class="input_box" id="logId" placeholder="아이디" />
                    </div>
                    <div class="modal_label">
                        <input type="password" class="input_box" id="logPw" placeholder="비밀번호" />
                    </div>
                    <div class="log_find">아이디 찾기 | 비밀번호 찾기</div>
                    <div class="modal_btn save" id="log_btn" onclick="">로그인</div>
                    <div class="modal_btn kakao_save" id="log_btn">카카오 로그인</div>
                    <div id="signup_btn" class="signup" onclick="join('signup')">회원가입</div>
                </div>
                <div id="signupArea" class="selectedArea">
                    <form method="POST" enctype="multipart/form-data" id="signUpForm">
                        <div id="signupImgArea">
                            <div class="modal_label">프로필 이미지</div>
                            <img id="preview" style="max-width: 100px;">
                            <div class="imgLabel">
                                <label for="imgFile">
                                    <span class="btn-upload">이미지 업로드</span>
                                </label>
                            </div>
                            <input type="file" name="uploadFile" id="imgFile" value="" />
                        </div>
                        <div class="modal_label">아이디</div>
                        <input type="text" class="input_box" id="signId" name="id" />
                        <div class="modal_label">패스워드</div>
                        <input type="password" class="input_box" id="signPw" name="pw" />
                        <div class="modal_label">이름</div>
                        <input type="text" class="input_box" id="signName" name="name" />
                    </form>
                    <div class="modal_btn save" id="signup_btn">회원가입</div>
                </div>
            </div>
        </div>
    </div>


	<%--
	<div class="modal" id="loginModal">
        <div class="modal_body">
            <div class="m_body">
                <div class="close_btn" id="loginClose">X</div>
                <div class="selDiv">
                    <div class="loginBox topSel">로그인</div>
                    <div class="signupBox"> 회원가입</div>
                </div>
                <div id="loginArea" class="selectedArea show">
                    <div class="modal_label">아이디</div>
                    <input type="text" class="input_box" id="logId" />
                    <div class="modal_label">패스워드</div>
                    <input type="password" class="input_box" id="logPw" />
                    <div class="modal_btn save" id="log_btn">로그인</div>
                </div>
                <div id="signupArea" class="selectedArea">
                <form method="POST" enctype="multipart/form-data" id="signUpForm">
                    <div id="signupImgArea">
                        <div class="modal_label">프로필 이미지</div>
                        <img id="preview" style="max-width: 100px;">
                        <div class="imgLabel">
                            <label for="imgFile">
                                <span class="btn-upload">이미지 업로드</span>
                            </label>
                        </div>
                          <input type="file" name="uploadFile" id="imgFile" value="" />
                    </div>
                    <div class="modal_label">아이디</div>
                    <input type="text" class="input_box" id="signId" name="id" />
                    <div class="modal_label">패스워드</div>
                    <input type="password" class="input_box" id="signPw" name="pw" />
                    <div class="modal_label">이름</div>
                    <input type="text" class="input_box" id="signName" name="name" />
                </form>
                    <div class="modal_btn save" id="signup_btn">회원가입</div>
                </div>
            </div>
        </div>
    </div>
    --%>
    
</body>