<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰</title>
</head>
<body>
	<div id="headerArea">
		<!-- headerInclude 해줘야 해요 -->
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->

	<!-- https://github.com/sockjs/sockjs-client -->
	<script
		src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script type="text/javascript"><%@ include file="/resources/js/chatting.js"></script>
</body>
</html>