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
							"<td>" + item.status + "</td>" + 
							"<td>" + item.name + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + item.banContent + "</td>" + 
							"<td>" + item.banStr + "</td>" + 
							"<td>" + item.banEnd + "</td>" + 
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

		if (selected.length === 1) {
			var row = selected.closest('tr');
			var id = selected.data('id');

			// 수정할 내용 입력란을 추가
			if (row.next().hasClass('editRow')) {
				row.next().remove();
				} else {
					var editRow = `
						<tr class="editRow">
							<td colspan="8">
								<div class="updateContainer">
								<p class="productUpdate">정지내용<br>입력</p>
								</div>
								<select id="editStatus${id}">
									<option value="Y" ${row.find('td').eq(1).text() === 'Y' ? 'selected' : ''}>Y</option>
									<option value="N" ${row.find('td').eq(1).text() === 'N' ? 'selected' : ''}>N</option>
								</select>
								<input type="text" id="editReason${id}" class="userbanInput" placeholder="정지 혹은 해제 사유를 입력해주세요" value="${row.find('td').eq(5).text()}">
								<input type="date" id="startDateSelect">
								<input type="date" id="endDateSelect">
								<button class="saveEditButton" data-id="${id}">수정 등록</button>
							</td>
						</tr>`;
					row.after(editRow);
					}
				} else if (selected.length === 0) { 
					alert('수정할 항목을 선택해주세요');
				} else {
					alert('수정할 항목을 하나만 선택해주세요');
				}
	});

	// 저장 버튼 클릭 시
	$(document).on('click', '.saveEditButton', function() {
		var id = $(this).data('id');
		var newStatus = $(`#editStatus${id}`).val();
		var newReason = $(`#editReason${id}`).val();

		$.ajax({
			url: '/your-server-endpoint/',
			method: 'PUT',
			data: { 'newStatus': newStatus, 
					'newReason': newReason
					},
			success: function() {
				loadTable();
			},
			error: function() {
				alert('등록 중 오류가 발생했습니다.');
			}
		});
	});

	function getSelectedRow(){
		return $('input.rowCheckbox:checked');
	}

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
	});
 });