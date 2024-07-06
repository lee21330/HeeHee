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
			url: '/heehee/admin/searchQnaAll',
			method: 'GET',
			data: { 'category': category, 
					'keyword': keyword
					},
			success: function(data) {
				var tableBody = $('#tableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var ansStatus = (!item.qnaAns || item.qnaAns.trim() === '' ) ? '처리대기' : '답변완료';
					var row = 
						"<tr>" + 
							"<td><input type='checkbox' class='rowCheckbox' data-id='" + item.seqQnaBno + "'></td>" + 
							"<td>" + item.seqQnaBno + "</td>" + 
							"<td>" + ansStatus + "</td>" + 
							"<td>" + item.qnaOption + "</td>" + 
							"<td>" + item.qnaTitle + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + new Date(item.qnaTime).toLocaleDateString() + "</td>" + 
						"</tr>";
					tableBody.append(row);
				});
			},
			error: function(xhr, status, error) {
				alert('데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};

	//열람/답변 버튼 클릭 시
	$('#editButton').click(function() {
		var selected = getSelectedRow();

		if (selected.length === 1) {
			var row = selected.closest('tr');
			var seqQnaBno = selected.attr('data-id');
			
			console.log("seqQnaBno : " + seqQnaBno);

			//숫자가 아닌 id를 거르기
			if (isNaN(seqQnaBno)) {
				alert('Invaild seqQnaBno: ' + seqQnaBno);
				return;
			}

			//수정할 내용 입력란을 추가
			if (row.next().hasClass('qnaContentRow')) {
				row.next().next().remove();
				row.next().remove();
				} else {
					$.ajax({
						url: '/heehee/admin/getQnaContent',
						method: 'GET',
						data: { 'seqQnaBno': seqQnaBno, 
								},
						success: function(contentData){
							console.log("success에서 응답확인", contentData.qnaContent);
							
							var editRow = 
								"<tr class='editRow'>" + 
									"<td colspan='7'>" + 
										"<div class='updateContainer'>" + 
										"<p class='editTitle'>답변 입력</p>" + 
										"<input type='text' id='editInput" + contentData.seqQnaBno + "' class='singleInput' placeholder='답변하실 내용을 입력하세요.'>" + 
										"<button id='saveEditButton' class='saveEditButtonSingle' data-id='" + contentData.seqQnaBno + "'>답변 등록</button>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";

							var qnaContentRow = 
								"<tr class='qnaContentRow'>" + 
									"<td colspan='7'>" + 
										"<div class='qnaContainer'>" + 
											"<div class='qnaContentText'>" + 
												"<p class='productUpdate'>문의내용</p>" + 
												"<br>" + 
												"<p>" + contentData.qnaContent + "</p>" + 
												"<br>" + 
												"<p class='productUpdate'>첨부 이미지</p>" + 
												"<div id='qnaImages'></div>" + 
											"</div>" + 
											"<br>" + 
											"<div class='qnaAnsText'>" + 
												"<p class='productUpdate'>답변내용</p>" + 
												"<br>" + 
												"<p>" + contentData.qnaAns + "</p>" + 
											"</div>" + 
										"</div>" + 
									"</td>" + 
								"</tr>";
								row.after(editRow);
								row.after(qnaContentRow);

								//이미지 동적 추가
								loadQnaImages(seqQnaBno);
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
	
	//이미지를 동적으로 가져오기
	function loadQnaImages(seqQnaBno){
		$.ajax({
			url: '/heehee/admin/getQnaImage', 
			method: 'GET', 
			data: { 'seqQnaBno': seqQnaBno
					},
			success: function(getImages){
				var qnaImagesDiv = $('#qnaImages');
				qnaImagesDiv.empty();

				getImages.forEach(function(getImage){
					var image = "<img class='qnaImageSize' src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/qnaBoard/" + getImage.imgName + "' alt='image'>" 
					qnaImagesDiv.append(image);
					console.log('loadQnaImages 함수 : ' + image)
				});
			},
			error: function(xhr, status, error){
				alert('이미지 데이터를 가져오는 중 오류가 발생했습니다.');
			}
		});
	};

	// 저장 버튼 클릭 시
	$(document).on('click', '#saveEditButton', function() {
		var seqQnaBno = $('#saveEditButton').attr("data-id");
		var newValue = $("#editInput" + seqQnaBno).val();
		var id = getSelectedRow().closest('tr').find('td').eq(5).text();
		
		$.ajax({
			url: '/heehee/admin/updateQnaAns',
			method: 'post',
			data: { 'seqQnaBno': seqQnaBno , 
					'newValue': newValue, 
					'id': id 
					},
			success: function() {
				loadTable();
			},
			error: function(e) {
				console.log(e);
				alert('등록 중 오류가 발생했습니다.');
			}
		});
	});

	// 삭제 버튼 클릭 시
	$('#deleteButton').click(function() {
		var selected = getSelectedRow();

		if (selected.length > 0) {
			if (confirm('선택된 항목을 삭제하시겠습니까?')) {
				selected.each(function() {
					var seqQnaBno = $(this).attr("data-id");

					$.ajax({
						url: '/heehee/admin/deleteQnaContent',
						method: 'POST',
						data:{ 'seqQnaBno': seqQnaBno
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

/* https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/ */