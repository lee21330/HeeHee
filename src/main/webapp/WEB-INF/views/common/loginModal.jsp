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
            $("#passArea").addClass("show");
            $(".modal_body").css("width", "400px");
            $(".modal_body").css("height", "auto");
			
		} else {
			// 회원가입 화면 보여주기
			$("#loginArea").removeClass("show");
			$("#passArea").removeClass("show");
			$("#signupArea").addClass("show");
			$(".modal_body").css("margin-top", "70px"); /* 입력칸이 많아서 맨 윗부분 가려짐, 간격주기 */
			$(".selDiv").children().removeClass("topSel");
			$(".signupBox").addClass("topSel");
			$(".modal_body").css("width", "350px"); /* 본인인증 화면이랑 가로 넓이 다름, 기존 크기로 변경 */
			$(".modal_body").css("height", "auto");
		}
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
                    <div class="modal_btn save" id="log_btn" onclick="">로그인</div>
                    <div class="modal_btn kakao_save" id="log_btn">카카오 로그인</div>
                    <div id="signup_btn" class="signup" onclick="join('signup')">회원가입</div>
                </div>
                <%-- 본인인증 --%>
                <div id="passArea" class="selectedArea">
                    <div class="modal_label">
                        <input type="text" class="input_box" placeholder="이름">
                    </div>
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
                            <input type="text" class="input_box" id="signId" name="id" placeholder="사용하실 아이디를 입력하세요" />
                            <input type="button" class="dup_btn" value="중복체크">
                        </div>
                        <div class="modal_label">
                            <input type="password" class="input_box" id="signPw" name="pw" placeholder="사용하실 비밀번호를 입력하세요" />
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" id="signName" name="name" placeholder="사용하실 닉네임을 입력하세요" />
                            <input type="button" class="dup_btn" value="중복체크">
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" placeholder="사용하실 이메일을 입력하세요">
                            <input type="button" class="dup_btn" value="중복체크">
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" placeholder="우편번호를 검색하세요">
                            <input type="button" class="dup_btn" value="검색">
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" placeholder="사용하실 주소를 입력하세요">
                        </div>
                        <div class="modal_label">
                            <input type="text" class="input_box" placeholder="사용하실 전화번호를 입력하세요">
                        </div>
                        <div class="modal_bank">
                            <select name="" id="">
                                <option>은행</option>
                                <option>신한은행</option>
                            </select>
                        </div>
                        <div class="modal_bank">
                            <input type="text" placeholder="계좌번호를 입력하세요">
                        </div>
                    </form>
                    <div class="modal_btn save" id="signup_btn">회원가입</div>
                </div>
            </div>
        </div>
    </div>
</body>