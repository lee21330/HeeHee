<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 홈페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<%-- <%@ include file="/WEB-INF/views/common/loginModal.jsp"%> --%>
	
	<div class="home_container">
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	
		<div>
			<h1>section area</h1>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
			<p>헤더 고정 확인</p>
		</div>
		
		<footer>
			<h1>footer area</h1>
		</footer>
		<c:forEach var="item" items="${arr}">
			<p>${item.PW}</p>
			<p>${item.CREATE_DATE}</p>
		</c:forEach>
	</div>
</body>
</html>