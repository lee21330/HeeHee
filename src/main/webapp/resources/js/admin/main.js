//table 동적 업데이트 Ajax용 / 간격 탭간격으로 수정, snake -> camel로 변수명 수정, ₩₩ -> "" + 로 수정

$(document).ready(function() {
	// 페이지 로드 시 테이블을 초기화
	loadTotalOrder();
	loadProStatus();
	loadAucStatus();
	loadRecentProductTable();
	loadRecentQuestionTable();
	loadRecentJoinTable();
	
	//전체 주문통계 테이블들의 데이터 Ajax로 가져오기
	//전체 주문현황(일반/경매)
	function loadTotalOrder(){
		$.ajax({
			url:'/heehee/admin/searchTotalOrder', 
			method:'GET', 
			dataType:'json', 
			success: function(data) {
				var dbData = data[0];
				$('#sellProCtn').text(dbData.sellProCtn.toLocaleString());
				$('#sellProPriceSum').text(dbData.sellProPriceSum.toLocaleString());
				$('#aucProCtn').text(dbData.aucProCtn.toLocaleString());
				$('#aucProPriceSum').text(dbData.aucProPriceSum.toLocaleString());
			},
			error: function(xhr, status, error) {
				console.error("데이터 가져오기 실패:", error);
				alert('데이터 로드에 실패하였습니다.');
			}
		});
	};
	
	//일반상품 주문현황
		function loadProStatus(){
		$.ajax({
			url:'/heehee/admin/searchProStatus', 
			method:'GET', 
			dataType:'json', 
			success: function(data) {
				var dbData = data[0];
				$('#sellIng').text(dbData.sellIng.toLocaleString());
				$('#sellRes').text(dbData.sellRes.toLocaleString());
				$('#sellEnd').text(dbData.sellEnd.toLocaleString());
				$('#sellStop').text(dbData.sellStop.toLocaleString());
			},
			error: function(xhr, status, error) {
				console.error("데이터 가져오기 실패:", error);
				alert('데이터 로드에 실패하였습니다.');
			}
		});
	};
	
	//경매상품 주문현황
		function loadAucStatus(){
		$.ajax({
			url:'/heehee/admin/searchAucStatus', 
			method:'GET', 
			dataType:'json', 
			success: function(data) {
				var dbData = data[0];
				$('#aucIng').text(dbData.aucIng.toLocaleString());
				$('#aucRes').text(dbData.aucRes.toLocaleString());
				$('#aucEnd').text(dbData.aucEnd.toLocaleString());
				$('#aucStop').text(dbData.aucStop.toLocaleString());
			},
			error: function(xhr, status, error) {
				console.error("데이터 가져오기 실패:", error);
				alert('데이터 로드에 실패하였습니다.');
			}
		});
	};
	
	//최근 상품 등록내역 테이블 데이터를 Ajax로 가져오기
	function loadRecentProductTable() {
		$.ajax({
			url: "/heehee/admin/searchRecentProduct",
			method: 'GET',
			success: function(data) {
				var tableBody = $('#recentProductTableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var buyerId = (!item.buyerId || item.buyerId.trim() === '' ) ? '' : item.buyerId ;
					var createDatePas = new Date(item.createDate).toLocaleDateString();
					var createDateRev = createDatePas.substring(0, createDatePas.length -1).replaceAll(". ", "-");
					
					var row = 
						"<tr>" + 
							"<td>" + item.productSeq + "</td>" + 
							"<td>" + buyerId + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + item.articleTitle + "</td>" + 
							"<td>" + item.proStatus + "</td>" + 
							"<td>" + item.productPrice.toLocaleString() + "</td>" + 
							"<td>" + createDateRev + "</td>" + 
						"</tr>";
					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				console.log('AJAX request failed:', status, error);
				alert('데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};
	
	
	//최근 회원가입 테이블 데이터를 Ajax로 가져오기
	function loadRecentQuestionTable() {
		$.ajax({
			url: "/heehee/admin/searchRecentQuestion",
			method: 'GET',
			success: function(data) {
				var tableBody = $('#recentQuestionTableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var ansStatus = (!item.qnaAns || item.qnaAns.trim() === '' ) ? '처리대기' : '답변완료';
					var qnaDatePas = new Date(item.qnaTime).toLocaleDateString();
					var qnaDateRev = qnaDatePas.substring(0, qnaDatePas.length -1).replaceAll(". ", "-");
					
					
					var row = 
						"<tr>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + ansStatus + "</td>" + 
							"<td>" + item.qnaTitle + "</td>" + 
							"<td>" + qnaDateRev + "</td>" + 
						"</tr>";
					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				console.log('AJAX request failed:', status, error);
				alert('데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};

	//최근 회원가입 테이블 데이터를 Ajax로 가져오기
	function loadRecentJoinTable() {
		$.ajax({
			url: "/heehee/admin/searchRecentJoin",
			method: 'GET',
			success: function(data) {
				var tableBody = $('#recentJoinTableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var createDatePas = new Date(item.createDate).toLocaleDateString();
					var createDateRev = createDatePas.substring(0, createDatePas.length -1).replaceAll(". ", "-");
					
					var row = 
						"<tr>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + item.name + "</td>" + 
							"<td>" + item.email + "</td>" + 
							"<td>" + item.phoneNum + "</td>" + 
							"<td>" + createDateRev + "</td>" + 
						"</tr>";
					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				console.log('AJAX request failed:', status, error);
				alert('데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};
	
	

 });
 