<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 포인트 내역</title>
<link rel="stylesheet" href="${path}/resources/css/pointList.css">
</head>
<%@ include file="../common/header.jsp"%>
<body>
	<script src="/heehee/resources/js/point.js"></script>
	<%@ include file="/WEB-INF/views/mypage/pointModal.jsp"%>
		<div class="pointList">
			<div id="point_text">
				<p id="balance">현재 포인트</p>
				<div id="balance_t">
					<fmt:formatNumber value="${sellerInfo.userPoint}" pattern="#,###"
						var="formattedPoint" />
					<p id="balance_text">
						<span class="formatted-point">${formattedPoint}</span>원
					</p>
				</div>

				<button id="btn-point">충전하기</button>
			</div>
			<div id="point_box">
				<div id="point_box_text">
					<div id="month">

						<input type="month" id="month_option" onchange="searchPointList()">

					</div>
				</div>

				<div id="point_history"></div>
			</div>
		</div>

</body>
</html>