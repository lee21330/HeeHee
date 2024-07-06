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
		$('#startDate').val('');
		$('#endDate').val('');
		loadTable();
	});

	// 테이블 데이터를 Ajax로 가져오기
	function loadTable() {
		var category = $('#searchCategory').val();
		var categoryDate = $('#dateCategory').val();
		var keyword = $('#searchInput').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();

		$.ajax({
			url: '/heehee/admin/userBanSearch',
			method: 'GET',
			data: { 'category': category, 
					'categoryDate': categoryDate,
					'keyword': keyword, 
					'startDate': startDate, 
					'endDate': endDate 
					},
			success: function(data) {
				var tableBody = $('#tableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var row = 
						"<tr>" + 
							"<td><input type='checkbox' class='rowCheckbox' data-id='" + item.id + "'></td>" + 
							"<td>" + item.name + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + item.banContent + "</td>" + 
							"<td>" + new Date(item.banStr).toLocaleDateString() + "</td>" + 
							"<td>" + new Date(item.banEnd).toLocaleDateString() + "</td>" + 
						"</tr>";
					tableBody.append(row);
					});
				},
				error: function(xhr, status, error) {
					alert('데이터를 가져오는 중 오류가 발생했습니다.');
				}
			});
	};

	// 수정 버튼 클릭 시
	$('#editButton').click(function() {
		var selected = getSelectedRow();
		
		if ($('.newRow').length > 0){
			$('.newRow').remove();
		}

		if (selected.length === 1) {
			var row = selected.closest('tr');
			var id = selected.attr('data-id');
			
			var startDateValue = new Date(row.find('td').eq(4).text()).toISOString().slice(0, 10);
			var endDateValue = new Date(row.find('td').eq(5).text()).toISOString().slice(0, 10);
			
			console.log("수정버튼 클릭 시 id : " + id);

			// 수정할 내용 입력란을 추가
			if (row.next().hasClass('editRow')) {
				row.next().remove();
				} else {
					var editRow = 
						"<tr class='editRow'>" + 
							"<td colspan='6'>" + 
								"<div class='updateContainer'>" + 
								"<p class='editTitle'>정지내용<br>수정입력</p>" + 
								"<input type='text' id='editReason" + id + "' class='userbanEditInput' placeholder='정지 혹은 해제 사유를 입력해주세요'>" + 
								"<input type='date' id='startDateSelect' value='" + startDateValue + "' class='userBanStr'>" + 
								"<input type='date' id='endDateSelect' value='" + endDateValue + "' class='userBanEnd'>" + 
								"<button id='saveEditButton' class='saveEditButtonBan' data-id='" + id + "'>수정 등록</button>" + 
								"</div>" + 
							"</td>" + 
						"</tr>";
					row.after(editRow);
					}
				} else if (selected.length === 0) { 
					alert('수정할 항목을 선택해주세요');
				} else {
					alert('수정할 항목을 하나만 선택해주세요');
				}
	});
	
	//정지 등록 버튼 클릭 시 (정지 신규추가)
	$('#addButton').click(function(){
		var id = 'new';
		
		//기존의 수정 행 제거
		if ($('.editRow').length > 0){
			$('.editRow').remove();
			}
		
		if ($('#tableBody').children('.newRow').length > 0){
			$('.newRow').remove();
			} else {
				var newRow = 
					"<tr class='newRow'>" + 
						"<td colspan='6'>" + 
							"<div class='updateContainer'>" + 
								"<p class='editTitle'>사용자<br>이용정지</p>" + 
								"<input type='text' id='newBanId' class='userBanIdInput' placeholder='정지할 사용자 ID 입력'>" + 
								"<input type='text' id='newBanContent' class='userBanContentInput' placeholder='이용정지 사유를 입력해주세요'>" + 
								"<input type='date' id='startDateSelect' class='userBanStr2'>" + 
								"<input type='date' id='endDateSelect' class='userBanEnd2'>" + 
								"<button id='saveNewButton' class='saveNewButtonBan' data-id='" + id + "'>정지 등록</button>" + 
							"</div>" + 
						"</td>" + 
					"</tr>";
				$('#tableBody').append(newRow);
			}
	});

	// 저장 버튼 클릭 시(수정등록)
	$(document).on('click', '#saveEditButton', function() {
		var id = $(this).attr('data-id');
		var banContent = $('#editReason' + id).val();
		var banStr = $('#startDateSelect').val();
		var banEnd = $('#endDateSelect').val();
		
		console.log("수정등록 id : " + id);
		console.log("수정등록 banContent : " + banContent);
		console.log("수정등록 banStr : " + banStr);
		console.log("수정등록 banEnd : " + banEnd);

		$.ajax({
			url: '/heehee/admin/updateBanUser',
			method: 'POST',
			data: { 'id': id, 
					'banContent': banContent, 
					'banStr': banStr, 
					"banEnd": banEnd, 
					},
			success: function() {
				loadTable();
			},
			error: function() {
				alert('등록 중 오류가 발생했습니다.');
			}
		});
	});
	
	// 저장 버튼 클릭 시 (신규등록)
	$(document).on('click', '#saveNewButton', function(){
		var id = $('#newBanId').val();
		var banContent = $('#newBanContent').val();
		var banStr = $('#startDateSelect').val();
		var banEnd = $('#endDateSelect').val();
		
		console.log("신규등록 id : " + id);
		console.log("신규등록 banContent : " + banContent);
		console.log("신규등록 banStr : " + banStr);
		console.log("신규등록 banEnd : " + banEnd);
		
		$.ajax({
			url:'/heehee/admin/insertBanUser', 
			method:'POST', 
			data: { 'id': id, 
					'banContent': banContent, 
					'banStr': banStr,
					'banEnd': banEnd 
					},
			success: function(){
				loadTable();
			},
			error: function(){
				alert('정지상태 신규등록 중 오류가 발생했습니다.');
			}
		});
	});

	function getSelectedRow(){
		return $('input.rowCheckbox:checked');
	}

	/* 향후 개선하여 추가 구현 예정
	// 라디오 버튼 클릭 시 날짜 필터 설정
	$('input[name="dateSelect"]').click(function() {
		var today = new Date();
		var startDate, endDate;

		if (this.id === 'today') {
			startDate = endDate = today.toISOString().split('T')[0];
		} else if (this.id === 'week') {
			startDate = new Date(today.setDate(today.getDate() - 7)).toISOString().split('T')[0];
			endDate = new Date().toISOString().split('T')[0];
		} else if (this.id === 'lastMonth') {
			var firstDayLastMonth = new Date(today.getFullYear(), today.getMonth() -1, 2);
			var lastDayLastMonth = new Date(today.getFullYear(), today.getMonth(), 1);
			startDate = firstDayLastMonth.toISOString().split('T')[0];
			endDate = lastDayLastMonth.toISOString().split('T')[0];
		} else if (this.id === 'oneMonth') {
			startDate = new Date(today.setMonth(today.getMonth() - 1)).toISOString().split('T')[0];
			endDate = new Date().toISOString().split('T')[0];
		} else if (this.id === 'threeMonth') {
			startDate = new Date(today.setMonth(today.getMonth() - 3)).toISOString().split('T')[0];
			endDate = new Date().toISOString().split('T')[0];
		}

		$('#startDate').val(startDate);
		$('#endDate').val(endDate);
			loadTable();
	});*/
 });