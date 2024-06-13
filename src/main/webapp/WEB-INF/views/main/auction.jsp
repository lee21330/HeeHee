<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 홈페이지</title>
<link rel="stylesheet" href="${path}/resources/css/main/auction.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		getExpTime();
		 setInterval(function() {
			 getExpTime();
		 },1000);
		
	})
	
	function getExpTime() {
		var cards = $(".card");
		for(let i = 0; i < cards.length; i++) {
			var seq = $(".card").eq(i).children(".seq").val();
			var expDate = $(".card").eq(i).children(".aucExpDate").val();
			remaindTime(seq,expDate);
		}
	}
	
	function remaindTime(seq,time) {
			var txt = time
			var tSplit = txt.split(":");
		    var now = new Date();
		    var exp = new Date(now.getFullYear(),now.getMonth(),now.getDate(),tSplit[0],tSplit[1],tSplit[2]);
		  
		    var nt = now.getTime();
		    var et = exp.getTime();
		    
		     sec =parseInt(et - nt) / 1000;
		     day  = parseInt(sec/60/60/24);
		     sec = (sec - (day * 60 * 60 * 24));
		     hour = parseInt(sec/60/60);
		     sec = (sec - (hour*60*60));
		     min = parseInt(sec/60);
		     sec = parseInt(sec-(min*60));
		     
		     if(hour<10){hour="0"+hour;}
		     if(min<10){min="0"+min;}
		     if(sec<10){sec="0"+sec;}
		     
		      $(".h_" + seq).html(hour);
		      $(".m_" + seq).html(min);
		      $(".s_" + seq).html(sec);
		 }
</script>

</head>
<body>
	<%-- <%@ include file="/WEB-INF/views/common/loginModal.jsp"%> --%>

	<div class="home_container">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<div></div>
		<div id="main_container">
			<div>
				<div id="prodRankArea">
					<p class="classifyTitle">마감 임박 상품</p>
					<div id="auctionListArea">
						<div class="card">
							<input class="seq" type="hidden" value="1">
							<input class="aucExpDate" type="hidden" value="23:10:00">
							<div class="timer blue">
								<span class="h_1"></span>
								:
								<span class="m_1"></span>
								:
								<span class="s_1"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="2">
							<input class="aucExpDate" type="hidden" value="23:15:00">
							<div class="timer blue">
								<span class="h_2"></span>
								:
								<span class="m_2"></span>
								:
								<span class="s_2"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="3">
							<input class="aucExpDate" type="hidden" value="23:17:00">
							<div class="timer blue">
								<span class="h_3"></span>
								:
								<span class="m_3"></span>
								:
								<span class="s_3"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="4">
							<input class="aucExpDate" type="hidden" value="23:18:00">
							<div class="timer blue">
								<span class="h_4"></span>
								:
								<span class="m_4"></span>
								:
								<span class="s_4"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="5">
							<input class="aucExpDate" type="hidden" value="23:22:00">
							<div class="timer blue">
								<span class="h_5"></span>
								:
								<span class="m_5"></span>
								:
								<span class="s_5"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="6">
							<input class="aucExpDate" type="hidden" value="23:30:00">
							<div class="timer red">
								<span class="h_6"></span>
								:
								<span class="m_6"></span>
								:
								<span class="s_6"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
						<div class="card">
							<input class="seq" type="hidden" value="7">
							<input class="aucExpDate" type="hidden" value="23:40:00">
							<div class="timer red">
								<span class="h_7"></span>
								:
								<span class="m_7"></span>
								:
								<span class="s_7"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/image+29.png" alt="Sheep">
							<div class="price">
								입찰가<br>1000원
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>