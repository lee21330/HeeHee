<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<%-- section 부분 추가 해야함 --%>
<style type="text/css">
  	.modal_test {
		height: auto !important;
		overflow: scroll;
	}
	
	.scroll::-webkit-scrollbar {
		display: none;
	}
	
	.scroll {
		-ms-overflow-style: none; /* 인터넷 익스플로러 */
		scrollbar-width: none; /* 파이어폭스 */
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
<body>
	<%@ include file="/WEB-INF/views/common/loginModal.jsp"%>
	
	<h1>Hello world!</h1>
	
	<div class="modal_test">
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	
	<div>
		<p>section 부분</p>
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
		
		<footer><h1>모달 창 떴을때 스크롤 확인용</h1></footer>
	</div>

</body>
</html>