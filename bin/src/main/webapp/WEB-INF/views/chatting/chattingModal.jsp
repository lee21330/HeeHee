<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/chatting.css">
<title>Insert title here</title>
</head>
<body>
	<div class="chatMmodal">
	    <div class="modal-content">
	        <div class="current-price">기존 가격: 50000원</div>
  	  	    <input type="number" class="new-price" placeholder="수정할 가격을 입력해주세요. (원)">
            <button class="submit-price">수정하기</button>
            <button class="cancel-edit">취소하기</button>
	    </div>
	</div>
</body>
</html>