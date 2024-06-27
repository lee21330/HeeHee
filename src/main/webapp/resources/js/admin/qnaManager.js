//table 동적 업데이트 Ajax용

$(document).ready(function() {

	console.log("테스트");

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
            		'keyword': keyword },
            success: function(data) {
                var tableBody = $('#tableBody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = `<tr>
                        <td><input type="checkbox" class="rowCheckbox" data-id="${item.seq_qna_bno}"></td>
                        <td>${item.seqQnaBno}</td>
                        <td>${item.qnaOption}</td>
                        <td>${item.qnaTitle}</td>
                        <td>${item.id}</td>
                        <td>${new Date(item.qna_time).toLocaleDateString()}</td>
                    </tr>`;
                    tableBody.append(row);
                });
            },
            error: function(xhr, status, error) {
                alert('데이터를 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    //열람/답변 버튼 클릭 시
    $('#editButton').click(function() {
        var selected = getSelectedRow();
        
        if (selected.length === 1) {
            var row = selected.closest('tr');
            var seq_qna_bno = selected.data('id');
            
			//숫자가 아닌 id를 거르기
			if(isNaN(seq_qna_bno)) {
				alert('Invaild seq_qna_bno: ' + seq_qna_bno);
				return;
			}
			
			//수정할 내용 입력란을 추가
			if(row.next().hasClass('qnaContentRow')) {
				row.next().next().remove();
				row.next().remove();
			} else {
				$.ajax({
					url: '/heehee/admin/getQnaContent',
					method: 'GET',
					data: {'seq_qna_bno': seq_qna_bno , 
							},
					success: function(contentData){
		                var editRow = `
		                    <tr class="editRow">
								<td colspan="6">
								    <div class="updateContainer">
								        <p class="productUpdate">답변 입력</p>
								    </div>
								    <input type="text" id="editInput${contentData.seq_qna_bno}" class="singleInput" placeholder="답변하실 내용을 입력하세요.">
								    <button class="saveEditButton" data-id="${contentData.seq_qna_bno}">답변 등록</button>
								</td>
		                    </tr>`;
						
		                var qnaContentRow = `
							<tr class="qnaContentRow">
		                        <td colspan="6">
		                            <div class="qnaContainer">
		                                <div class="qnaContentText">
		                                    <p class="productUpdate">문의내용</p>
		                                    <br>
		                                	<p>${contentData.qna_content}</p>
		                                </div>
		                                	<br>
		                                <div class="qnaAnsText">
		                                	<p class="productUpdate">답변내용</p>
		                                	<br>
		                                	<p>${contentData.qna_ans}</p>
		                                </div>
		                            </div>
		                        </td>
		                    </tr>`;
			                row.after(editRow);
			                row.after(qnaContentRow);
			            console.log(`${contentData.seq_qna_bno}`)
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
    
    // 저장 버튼 클릭 시
    $(document).on('click', '.saveEditButton', function() {
        var seq_qna_bno = $('.saveEditButton').attr("data-id");
        var newValue = $(`#editInput${seq_qna_bno}`).val();
		console.log(seq_qna_bno);
        $.ajax({
            url: '/heehee/admin/updateQnaAns',
            method: 'post',
            data: { 'seq_qna_bno': seq_qna_bno , 
            		'newValue': newValue 
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
                    var seq_qna_bno = $(this).attr("data-id");
					console.log('번호를 잘 가져왔나?' + seq_qna_bno);
                    $.ajax({
                        url: '/heehee/admin/deleteQna',
                        method: 'POST',
                        data:{'seq_qna_bno': seq_qna_bno
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