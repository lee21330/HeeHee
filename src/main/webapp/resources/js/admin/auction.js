//table 동적 업데이트 Ajax용 / 간격 탭간격으로 수정, snake -> camel로 변수명 수정, ₩₩ -> "" + 로 수정

$(document).ready(function() {
	// 페이지 로드 시 테이블을 초기화
	loadTable();

	// 검색 버튼 클릭 시
	$('#searchButton').click(function() {
		loadTable();
	});

	// 초기화 버튼 클릭 시
	$('#resetButton').click(function() {
		$('#searchInput').val('');
			loadTable();
	});

	// 테이블 데이터를 Ajax로 가져오기
	function loadTable() {
		var category = $('#searchCategory').val();
		var keyword = $('#searchInput').val();

		$.ajax({
			url: '/heehee/admin/searchAuctionDetail',
			method: 'GET',
			data: { 'category': category, 
					'keyword': keyword 
					},
			success: function(data) {
				var tableBody = $('#tableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var expTimePas = new Date(item.expTime).toLocaleDateString();
					var expTimeRev = expTimePas.substring(0, expTimePas.length -1).replaceAll(". ", "-");
					
					
					var row = 
						"<tr>" + 
							"<td><input type='checkbox' class='rowCheckbox' data-id='" + item.productSeq + "'></td>" + 
							"<td>" + item.productSeq + "</td>" + 
							"<td>" + item.category + "</td>" + 
							"<td>" + item.detailCategory + "</td>" + 
							"<td>" + item.sellerId + "</td>" + 
							"<td>" + item.auctionTitle + "</td>" + 
							"<td>" + expTimeRev + "</td>" + 
							"<td>" + item.aucStatus + "</td>" + 
						"</tr>";
					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				alert('데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	}

	// 수정 버튼 클릭 시
	$('#editButton').click(function() {
		var selected = getSelectedRow();

		if (selected.length === 1) {
			var row = selected.closest('tr');
			var id = selected.attr('data-id');

			// 수정할 내용 입력란을 추가
			if (row.next().hasClass('banReason')) {
				row.next().next().remove();
				row.next().remove();
				} else {
					$.ajax({
						url: '/heehee/admin/getAucBanReason',
						method:'GET',
						data:{ 'productSeq': id
								},
						success: function(contentData){
							var editRow = 
								"<tr class='editRow'>" + 
									"<td colspan='8'>" + 
										"<div class='updateContainer'>" + 
										"<p class='editTitle'>판매상태<br>수정</p>" + 
										"<select id='editStatus" + id + "' class='selectStatus'>" + 
											"<option value='입찰' " + (row.find('td').eq(7).text() === '입찰' ? 'selected' : '') + ">입찰</option>" + 
											"<option value='판매중지' " + (row.find('td').eq(7).text() === '판매중지' ? 'selected' : '') + ">판매중지</option>" +
										"</select>" + 
										"<input type='text' id='editInput" + id + "' class='singleInputPro' placeholder='판매중지 사유를 입력해주세요'>" + 
										"<button id='saveEditButton' class='saveEditButton' data-id='" + id + "'>수정 등록</button>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";

							var banReason = 
								"<tr class='banReason'>" + 
									"<td colspan='8'>" + 
										"<div class='aucBanReason'>" + 
										"<p class='productUpdate'>판매중지 사유</p>" + 
										"<p>" + contentData.aucBanReason + "</p>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";
								row.after(editRow);
								row.after(banReason);
								},
								error: function(xhr, status, error){
									alert('정지사유를 가져오는 중 오류가 발생했습니다.');
								}
							});
						}
					} else if (selected.length === 0) { 
						alert('수정할 항목을 선택해주세요');
					} else {
						alert('수정할 항목을 하나만 선택해주세요');
					}
	});

	// 저장 버튼 클릭 시
	$(document).on('click', '#saveEditButton', function() {
		var productSeq = $(this).attr('data-id');
		var aucStatus = $("#editStatus" + productSeq).val();
		var aucBanReason = $("#editInput" + productSeq).val();
		var currAucStatus = getSelectedRow().closest('tr').find('td').eq(7).text();
		var userid = getSelectedRow().closest('tr').find('td').eq(4).text();
		
		if (currAucStatus == '낙찰' || currAucStatus == '유찰' || currAucStatus == '거래완료'){
			alert('거래상태를 변경할 수 없습니다.');
			} else {
				$.ajax({
					url: '/heehee/admin/updateAucStatus',
					method: 'POST',
					data: { 'productSeq': productSeq, 
							'aucStatus': aucStatus, 
							'aucBanReason': aucBanReason, 
							'id': userid
							},
					success: function() {
						loadTable();
					},
					error: function() {
						alert('등록 중 오류가 발생했습니다.');
					}
				});
			}
	});

	// 삭제 버튼 클릭 시
	$('#deleteButton').click(function() {
		var selected = getSelectedRow();

		if (selected.length > 0) {
			if (confirm('선택된 항목을 삭제하시겠습니까?')) {
				selected.each(function() {
					var productSeq = $(this).attr('data-id');

					$.ajax({
						url: '/heehee/admin/deleteAuction',
						method: 'POST',
						data:{'productSeq': productSeq 
								},
						success: function() {
							loadTable();
						},
						error: function() {
							alert('삭제 중 오류가 발생했습니다.');
						}
					});
				});
			}
		} else {
			alert('삭제할 항목을 선택해주세요');
		}
	});

	function getSelectedRow() {
		return $('input.rowCheckbox:checked');
	}
});