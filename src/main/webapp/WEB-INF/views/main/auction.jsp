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
		 
		 setInterval(function() {
			 getAucPrice();
		 },500);
		
		 $(".card").on("click", function() {
			var aucSeq = $(this).children(".seq").val();
			location.href = "/heehee/auc/detail/" + aucSeq;
		 });
	})
	
	function getAucPrice() {
		var cards = $(".card");
		var seqArr = [];
		for(let i = 0; i < cards.length; i++) {
			var seq = $(".card").eq(i).children(".seq").val();
			seqArr.push(seq);
		}
		
		$.ajax({
		    url: '/heehee/auc/prices',
		    method: 'GET',
		    data : {"seqArr" : seqArr},
		    dataType : 'json',
		    success: function (data, status, xhr) {
		    	for(let i = 0; i < data.length; i++) {
		    		$("#pr_" + data[i].productSeq).text(data[i].aucPrice + "원")
		    	}
		    },
		    error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
	
	function getExpTime() {
		var cards = $(".card");
		for(let i = 0; i < cards.length; i++) {
			var seq = $(".card").eq(i).children(".seq").val();
			var expDate = $(".card").eq(i).children(".aucExpDate").val();
			var expTime = $(".card").eq(i).children(".aucExpTime").val();
			remaindTime(seq, expDate, expTime);
		}
	}
	
	function fGet2char(num) {
	      return num < 10 ? "0" + num : String(num);
	}
	
	function remaindTime(seq, expDate, expTime) {
			var expD = expDate;
			var dSplit = expD.split("/");
			
			var expT = expTime;
			var tSplit = expT.split(":");
			
		    var now = new Date();
		    var exp = new Date(dSplit[0],dSplit[1] - 1,dSplit[2],tSplit[0],tSplit[1],tSplit[2]);

	      	let gap = Math.max(exp.getTime() - now.getTime(), 0);
	      	let day = Math.floor((gap / 3600000)/24);
	      	let HH = fGet2char(Math.floor(gap / 3600000) % 24 + (day * 24));
	      	let MM = fGet2char(Math.floor((gap % 3600000) / 60000));
	      	let SS = fGet2char(Math.floor((gap % 60000) / 1000));
	      
		    if((gap/60000) < 30) {
		    	$(".c_" + seq).removeClass("blue");
		    	$(".c_" + seq).addClass("red");
		    }
		    
		    $(".h_" + seq).html(HH);
		    $(".m_" + seq).html(MM);
		    $(".s_" + seq).html(SS);
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
						<c:forEach items="${aucList}" var="aucProd">
							<div class="card">
							<input class="seq" type="hidden" value="${aucProd.productSeq}">
							<input class="aucExpDate" type="hidden" value="${aucProd.expDate}">
							<input class="aucExpTime" type="hidden" value="${aucProd.expTime}">
							<p>${aucProd.auctionTitle}</p>
							<div class="timer blue c_${aucProd.productSeq}">
								<span class="h_${aucProd.productSeq}"></span>
								:
								<span class="m_${aucProd.productSeq}"></span>
								:
								<span class="s_${aucProd.productSeq}"></span>
							</div>
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${aucProd.imgName}" alt="${aucProd.imgName}">
							<div class="price">
								<p>입찰가</p>
								<p id="pr_${aucProd.productSeq}">${aucProd.aucPrice}원</p>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
        <p>&copy; 2024 희희낙찰. All rights reserved.</p>
    </footer>
</body>
</html>