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
                    <div class="item">
                        <p>프로필 사진</p>
                        <input type="file" id="file">
                    </div>
                    <div class="item">
                        <p>아이디</p>
                        <input type="text" readonly="readonly">
                    </div>
                    <div class="item">
                        <p>이름</p>
                        <input type="text" readonly="readonly">
                    </div>
                    <div class="item">
                        <p>비밀번호</p>
                        <input type="text" readonly="readonly">
                    </div>
                    <div class="item">
                        <p>이메일</p>
                        <input type="text"> <input type="button" value="중복체크" class="btn">
                    </div>
                    <div class="item">
                        <p>전화번호</p>
                        <input type="text">
                    </div>
                    <div class="item">
                        <p>닉네임</p>
                        <input type="text"> <input type="button" value="중복체크" class="btn">
                    </div>
                    <div class="item">
                        <p>주소</p>
                        <input type="text">
                    </div>
                    <hr>
                    <div>
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