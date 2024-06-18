//table 동적 업데이트 Ajax용

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
            url: '/your-server-endpoint',
            method: 'GET',
            data: { 'category': category, 'keyword': keyword },
            success: function(data) {
                var tableBody = $('#tableBody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = `<tr>
                        <td><input type="checkbox" class="rowCheckbox" data-id="${item.id}"></td>
                        <td>${item.regNumber}</td>
                        <td>${item.category}</td>
                        <td>${item.subCategory}</td>
                        <td>${item.sellerID}</td>
                        <td class="qnaContent">${item.title}</td>
                        <td>${item.postDate}</td>
                        <td>${item.status}</td>
                    </tr>`;
                    tableBody.append(row);
                });
            },
            error: function(xhr, status, error) {
                alert('데이터를 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    // 답변 버튼 클릭 시
    $('#editButton').click(function() {
        var selected = getSelectedRow();

        if (selected.length === 1) {
            var row = selected.closest('tr');
            var id = selected.data('id');

            // 수정할 내용 입력란을 추가
            if (row.next().hasClass('qnaContentRow')) {
				row.next().next().remove();
                row.next().remove();
            } else {
                var editRow = `
                    <tr class="editRow">
						<td colspan="6">
						    <div class="updateContainer">
						        <p class="productUpdate">답변<br>입력</p>
						    </div>
						    <input type="text" id="editInput${id}" class="singleInput" value="${row.find('td').eq(3).text()}">
						    <button class="saveEditButton" data-id="${id}">답변 등록</button>
						</td>
                    </tr>`;
				
                var qnaContentRow = `
					<tr class="qnaContentRow">
                        <td colspan="6">
                            <div class="qnaContainer">
                                    <p class="productUpdate">문의내용</p>
                                    <br>
                                <div class="qnaContentText">
                                    <p>여기에 문의내용이 나오도록 할 예정입니다. 그를 위해서 구역설정도 해줘야하고 이것저것 할게 많네.... asdkasldlaskjdlkasjdlkasjdlkasjdlkasjdlkasjdlkasjdlkasjdlkasjdlkasjdalskdjalksdjaslkdjaslkdjaslkdjaslkdjsalkdjaslkdjaslkdjaslkdjaslkjd</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                `;
                
                row.after(editRow);
                row.after(qnaContentRow);
                
            }
        } else if (selected.length === 0) { 
        	alert('열람할 항목을 선택해주세요');
        } else {
            alert('열람할 항목을 하나만 선택해주세요');
        }
    });
    
    //제목 글자 클릭 시
    $('.qnaContent').click(function(){
		var selected = getSelectedRow();
		
		
	});

    // 저장 버튼 클릭 시
    $(document).on('click', '.saveEditButton', function() {
        var id = $(this).data('id');
        var newStatus = $(`#editStatus${id}`).val();
        var newValue = $(`#editInput${id}`).val();

        $.ajax({
            url: '/your-server-endpoint/' + id,
            method: 'PUT',
            data: { 'newStatus': newStatus, 'newValue': newValue },
            success: function() {
                loadTable();
            },
            error: function() {
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
                    var id = $(this).data('id');

                    $.ajax({
                        url: '/your-server-endpoint/' + id,
                        method: 'DELETE',
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