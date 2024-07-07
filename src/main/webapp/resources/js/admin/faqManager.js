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
			url: '/heehee/admin/searchFaqAll',
			method: 'GET',
			data: { 'category': category, 
					'keyword': keyword },
			success: function(data) {
				var tableBody = $('#tableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var faqTimePas = new Date(item.faqTime).toLocaleDateString();
					var faqTimeRev = faqTimePas.substring(0, faqTimePas.length -1).replaceAll(". ", "-");
					
					var row = 
						"<tr>" + 
							"<td><input type='checkbox' class='rowCheckbox' data-id='" + item.seqFaqBno + "'></td>" + 
							"<td>" + item.seqFaqBno + "</td>" + 
							"<td>" + item.qnaOption + "</td>" + 
							"<td>" + item.faqContent + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + faqTimeRev + "</td>" + 
						"</tr>";
						tableBody.append(row);
					});
				},
				error: function(xhr, status, error) {
					alert('데이터를 가져오는 중 오류가 발생했습니다.');
				}
			});
	};

	//수정 시 카테고리 셀렉트로 선택
	function loadQnaOptions(selectElementId){
		$.ajax({
			url: '/heehee/admin/getQnaOptions',
			method: 'GET',
			success: function(getOptions){
				var selectElement = $(selectElementId);
				selectElement.empty();

				getOptions.forEach(function(getOption){
					var option = "<option value='" + getOption.seqQnaOption + "'>" + getOption.qnaOption + "</option>";
					selectElement.append(option);
				});
			},
			error: function(xhr, status, error){
				alert('유형 데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};
	

	//열람/수정 버튼 클릭 시
	$('#editButton').click(function() {
		var selected = getSelectedRow();

		//신규등록 창 있으면 닫기
		if ($('.newRow').length > 0){
			$('.newRow').remove();
			}

		if (selected.length === 1) {
			var row = selected.closest('tr');
			var id = selected.attr('data-id');

			//숫자가 아닌 id를 거르기
			if (isNaN(id)) {
				alert('Invaild id: ' + id);
				return;
			}

			//수정할 내용 입력란을 추가
			if (row.next().hasClass('qnaContentRow')) {
				row.next().next().remove();
				row.next().remove();
				} else {
					$.ajax({
						url: '/heehee/admin/getFaqContent',
						method: 'GET',
						data: {'id': id
								},
						success: function(contentData){
							var editRow = 
								"<tr class='editRow'>" + 
									"<td colspan='6'>" + 
										"<div class='updateContainer'>" + 
										"<p class='editTitle'>FAQ내용<br>열람/수정</p>" + 
										"<select id='editCategory" + id + "' class='selectStatus'></select>" + 
										"<input type='text' id='editTitleInput" + id + "' class='editFaqContentInput' placeholder='수정하실 제목을 입력하세요.'>" + 
										"<br>" + 
										"<input type='text' id='editContentInput" + id + "' class='editFaqAnsInput' placeholder='수정하실 내용을 입력하세요.'>" + 
										"<button id='saveEditButton' class='saveEditButtonFaq' data-id='" + id + "'>수정 등록</button>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";

							var qnaContentRow = 
								"<tr class='qnaContentRow'>" + 
									"<td colspan='6'>" + 
										"<div class='qnaContainer'>" + 
											"<p class='productUpdate'>FAQ 내용</p>" + 
											"<br>" + 
										"<div class='qnaContentText'>" + 
											"<p>" + contentData.faqAns + "</p>" + 
										"</div>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";
								row.after(editRow);
								row.after(qnaContentRow);
								
								loadQnaOptions("#editCategory" + id);
							},
						error: function(xhr, status, error){
							alert('문의 내용을 가져오는 중 오류가 발생했습니다.');
						}
					});
				}
			} else if (selected.length === 0) { 
				alert('열람할 항목을 선택해주세요');
			} else {
				alert('열람할 항목을 하나만 선택해주세요');
			}
	});

	// 신규 등록 버튼 클릭 시
	$('#addButton').click(function() {
		var id = 'new'; //임시ID

		// 기존의 수정 행을 제거
		if ($('.editRow').length > 0){
			$('.editRow').remove();
			$('.qnaContentRow').remove();
			}

		// 신규 등록할 내용 입력란을 추가
		if ($('#tableBody').children('.newRow').length > 0) {
			$('.newRow').remove();
			} else {
				var newRow = 
					"<tr class='newRow'>" + 
						"<td colspan='5'>" + 
							"<div class='updateContainer'>" + 
							"<p class='editTitle'>FAQ내용<br>신규 등록</p>" + 
							"<select id='editCategory" + id + "' class='selectStatus'></select>" + 
							"<input type='text' id='newContentInput' class='editFaqContentInput' placeholder='등록하실 제목을 입력하세요.'>" + 
							"<input type='text' id='newAnsInput' class='editFaqAnsInput' placeholder='등록하실 내용을 입력하세요.'>" +
							"<button id='saveNewButton' class='saveNewButtonFaq' data-id='" + id + "'>신규 등록</button>" + 
							"</div>" + 
						"</td>" + 
					"</tr>";
				$('#tableBody').append(newRow);
				
				loadQnaOptions("#editCategory" + id);
			}
	});

	// 저장 버튼 클릭 시 (수정 등록)
	$(document).on('click', '#saveEditButton', function() {
		var seqFaqBno = $(this).attr('data-id');
		var seqQnaOption = $("#editCategory" + seqFaqBno).val();
		var faqContent = $("#editTitleInput" + seqFaqBno).val();
		var faqAns = $("#editContentInput" + seqFaqBno).val();

		$.ajax({
			url: '/heehee/admin/updateFaq',
			method: 'POST',
			data: { 'seqFaqBno': seqFaqBno, 
					'seqQnaOption': seqQnaOption, 
					'faqContent': faqContent, 
					'faqAns': faqAns 
					},
			success: function() {
				loadTable();
			},
			error: function() {
				alert('등록 중 오류가 발생했습니다.');
			}
		});
	});

	// 저장 버튼 클릭 시 (신규 등록)
	$(document).on('click', '#saveNewButton', function() {
		var seqFaqBno = $(this).attr('data-id');
		var seqQnaOption = $("#editCategory" + seqFaqBno).val();
		var faqContent = $("#newContentInput").val();
		var faqAns = $("#newAnsInput").val();

		$.ajax({
			url: '/heehee/admin/insertFaq',
			method: 'POST',
			data: { 'faqContent': faqContent, 
					'faqAns': faqAns, 
					'seqQnaOption': seqQnaOption, 
					},
			success: function() {
				loadTable();
			},
			error: function() {
				alert('신규 등록 중 오류가 발생했습니다.');
			}
		});
	});

	// 삭제 버튼 클릭 시
	$('#deleteButton').click(function() {
		var selected = getSelectedRow();

		if (selected.length > 0) {
			if (confirm('선택된 항목을 삭제하시겠습니까?')) {
				selected.each(function() {
				var seqFaqBno = $(this).attr('data-id');

				$.ajax({
					url: '/heehee/admin/deleteFaq',
					method: 'POST',
					data:{'seqFaqBno': seqFaqBno 
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
	};
});