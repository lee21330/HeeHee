<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<link rel="stylesheet" href="${path}/resources/css/profile.css">
</head>
<body>
    <header>
        <%@include file="../common/header.jsp"%>
    </header>
    <section>
        <div class="inner">
            <div class="profile_container">
                <form action="${path}/editProfile" method="post">
                    <hr>
                    <div class="align">
                        <div class="item">
                            <p>프로필 사진</p>
                            <img class="photo" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${profile.profileImg}">
                            <input type="file" class="file" name="profileImg">
                            <input type="button" value="사진 변경" class="btn_photo">
                            
                        </div>
                        <div class="item">
                            <p>아이디</p>
                            <input type="text" readonly="readonly" class="readonly" placeholder="${profile.id}" name="id"> 
                        </div>
                        <div class="item">
                            <p>이름</p>
                            <input type="text" readonly="readonly" class="readonly" placeholder="${profile.name}" name="name">
                        </div>
                        <div class="item">
                            <p>비밀번호</p>
                            <input type="text" placeholder="${profile.pw}" name="pw">
                        </div>
                        <div class="item">
                            <p>이메일</p>
                            <input type="text" placeholder="${profile.email}" name="email"> <input type="button" value="중복체크" class="btn">
                        </div>
                        <div class="item">
                            <p>전화번호</p>
                            <input type="text" placeholder="${profile.phoneNum}" name="phoneNum">
                        </div>
                        <div class="item">
                            <p>닉네임</p>
                            <input type="text" placeholder="${profile.nickName}" name="nickName"> <input type="button" value="중복체크" class="btn">
                        </div>
                        <div class="item">
                            <p>주소</p>
                            <div class="address">
                            	
                            	<input type="text" placeholder="${profile.address}" name="address">
                            	<input type="text" value="101동 101호">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="submit">
                    	<button class="btn">수정 완료</button>
                    </div>
                </form>
            </div>
           
            <div class="bottom">
            	<%@include file="/WEB-INF/views/mypage/withdrawalModal.jsp"%>
                <p id="drawal">회원 탈퇴</p>
            </div>
        </div>
        
    </section>
    
    <script>
      $(function() {
            $(".btn_photo").click(function () {
            $(".file").click();
            });
			

        });
    </script>
</body>
</html>