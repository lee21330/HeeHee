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
                            <img class="photo" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/f2be618999b263e057b10ca8a28c2d46.jpeg">
                            <input type="file" class="file">
                        </div>
                        <div class="item">
                            <p>아이디</p>
                            <input type="text" value="dhfl123" readonly="readonly" id="readonly"> 
                        </div>
                        <div class="item">
                            <p>이름</p>
                            <input type="text" value="손동희" readonly="readonly" id="readonly">
                        </div>
                        <div class="item">
                            <p>비밀번호</p>
                            <input type="text" value="qwer1234">
                        </div>
                        <div class="item">
                            <p>이메일</p>
                            <input type="text" value="heendoonge@gmail.com"> <input type="button" value="중복체크" class="btn">
                        </div>
                        <div class="item">
                            <p>전화번호</p>
                            <input type="text" value="010-1234-1234">
                        </div>
                        <div class="item">
                            <p>닉네임</p>
                            <input type="text" value="흰둥이"> <input type="button" value="중복체크" class="btn">
                        </div>
                        <div class="item" id="address">
                            <p>주소</p>
                            <input type="text" value="우편번호를 검색하세요">
                            <input type="text" value="서울시 마포구 연남동">
                        </div>
                    </div>
                    <hr>
                    <div class="submit">
                    	<button class="btn">수정 완료</button>
                    </div>
                </form>
            </div>
           
            <div class="bottom">
                <a href="" class="withdrawal">회원 탈퇴</a>
            </div>
        </div>
        
    </section>
</body>
</html>