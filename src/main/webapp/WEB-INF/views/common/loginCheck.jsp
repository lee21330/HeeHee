<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<%-- 로그인 전 --%>
<sec:authorize access="isAnonymous()">
	<div id="loginBtn">로그인</div>
	<div class="div_line"></div>
	<div id="signupBtn" class="login_text" onclick="join('signup')">회원가입</div>
</sec:authorize>
<%-- 로그인 후 --%>
<sec:authorize access="isAuthenticated()">
	
	⭐ ${userNickName}님 안녕하세요 ⭐
	<div onclick="logout()">로그아웃</div>
	<div class="div_line"></div>
	<sec:authorize access="hasRole('ADMIN')">
		<div class="login_text" onclick="location.href='${path}/admin/main'">관리자 페이지</div>
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
			<div class="login_text" onclick="location.href='${path}/mypage/main'">마이페이지</div>
	</sec:authorize>
</sec:authorize>