<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 정지 유저 페이지</title>
<link rel="stylesheet" href="${path}/resources/css/banUser.css">
<script type="text/javascript">
	$(function () {
		$("#banBtn").on("click", banUser);
	});
	
	function banUser () {
		location.href = ${path}/main;
	}
</script>
</head>
<body>
	<div class="ban_container">
	        <div class="ban_inner_container">
	            <div class="ban_title">
	                <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo.png" alt="로고 이미지">
	            </div>
	            <div>
	                <h1>서비스 이용제한 안내</h1>
	            </div>
	            <div>
	                <h3>안녕하세요. 희희낙찰 입니다.</h3>
	                <h3>아래와 같은 이유로 희희낙찰을 이용할 수 없게 되었습니다.</h3>
	            </div>
	            <div>
	            	<%-- BAN_HISTORY 컬럼명 임시로 적음 --%>
	                <p>제재이유 : ${BAN_CONTENT}</p>
	                <p>제재기간 : ${BAN_STR ~ BAN_END}</p>
	            </div>
	            <%-- 메인으로 redirect --%>
	            <div id="banBtn" class="ban_btn">확인</div>
	        </div>
	    </div>
</body>
</html>