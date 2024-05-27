<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
    
.modal {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: none;
	background-color: rgba(0, 0, 0, 0.4);
	z-index: 100;
}

.modal.show {
	display: block;
}

.modal_body {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 350px;
	height: 320px;
	background-color: rgb(255, 255, 255);
	border-radius: 10px;
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
	transform: translateX(-50%) translateY(-60%);
}

.m_body {
	height: 80%;
	padding: 20px;
}


.modal_title {
	font-size: 18px;
	color: gray;
	font-weight: 500;
}

.close_btn {
	font-size: 20px;
	color: rgb(139, 139, 139);
	font-weight: 900;
	cursor: pointer;
	width: 20px;
    float: right;
}

.modal_label {
	padding-top: 10px;
}

.input_box {
	width: 100%;
	border: 1px solid rgb(189, 189, 189);
	border-radius: 5px;
	height: 30px;
}

.modal_btn {
	width: 315px;
    height: 30px;
    border-radius: 5px;
    text-align: center;
    font-size: 20px;
    font-weight: bolder;
    padding-top: 5px;
    margin-top: 20px;
    cursor: pointer;
}

.cancle {
	background-color: white;
	color: black;
}

.save {
	background-color: rgb(50, 77, 158);
	color: white;
}

.selDiv {
	width: 240px;
    height: 55px;
    margin: 15px auto;
    text-align: center;
    font-size: 30px;
}

.selDiv div {
	display: inline-block;
    padding: 7px 0;
    width: 110px;
    cursor: pointer;
}

.selectedArea{
	display: none;
}

.selectedArea.show {
	display: block;
}

.topSel {
	border: 1px solid gray;
	border-radius: 15px;
}


#signupImgArea {
	height: 130px;
}

#signupImgArea input {
	width: 180px;
}

.btn-upload {
  width: 150px;
  height: 30px;
  background: #fff;
  border: 1px solid rgb(77,77,77);
  border-radius: 10px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  &:hover {
    background: rgb(77,77,77);
    color: #fff;
  }
}

#imgFile {
  display: none;
}

#signupImgArea .imgLabel {
	display: inline-block;
    vertical-align: top;
    width: 170px;
}
</style>
<script>
    $(function() {
        $("#loginBtn").on("click", openLogin);
        $("#loginClose").on("click", closeLogin);
    })
    
    
    function openLogin() {
        $("#loginModal").addClass("show");
    }
    
    function closeLogin() {
        $("#loginModal").removeClass("show");
        $("#signupArea").removeClass("show");
        $("#loginArea").addClass("show");
        $(".selDiv").children().removeClass("topSel");
        $(".loginBox").addClass("topSel");
        $(".modal_body").css("height", "320px");
        initLogin();
    }
    
    function initLogin() {
        $("#logId").val("");
        $("#logPw").val("");
        $("#imgFile").val("");
        $("#signId").val("");
        $("#signPw").val("");
        $("#signName").val("");
    }
    
    </script>
</head>
<body>

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

</body>