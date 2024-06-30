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
            url: '/heehee/admin/searchCategoryInfo',
            method: 'GET',
            data: { 'category': category, 'keyword': keyword },
            success: function(data) {
                var tableBody = $('#tableBody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = `<tr>
                        <td><input type="checkbox" class="rowCheckbox" data-id="${item.product_cate_seq}"></td>
                        <td>${item.product_cate_seq}</td>
                        <td>${item.category}</td>
                        <td>${item.detail_category}</td>
                    </tr>`;
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
            var id = selected.data('id');

            // 수정할 내용 입력란을 추가
            if (row.next().hasClass('editRow')) {
                row.next().remove();
            } else {
                var editRow = `
                    <tr class="editRow">
                        <td colspan="6">
                            <div class="updateContainer">
                                <p class="productUpdate">카테고리<br>수정</p>
                            </div>
                            <input type="text" id="editCategory${id}" class="categoryInputSmall" placeholder="카테고리 입력" value="${row.find('td').eq(2).text()}">
                            <input type="text" id="editDetailCategory${id}" class="categoryInputBigger" placeholder="세부 카테고리 입력" value="${row.find('td').eq(3).text()}">
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
    
    // 신규 등록 버튼 클릭 시
    $('#addButton').click(function() {
    	var id = 'new'; //임시ID
        // 기존의 수정 행을 제거
        if($('.editRow').length > 0){
        	$('.editRow').remove();
        }

        // 신규 등록할 내용 입력란을 추가
       if ($('#tableBody').children('.newRow').length > 0) {
                $('.newRow').remove();
            } else {
            var newRow = `
                <tr class="newRow">
                    <td colspan="6">
                        <div class="updateContainer">
                            <p class="productUpdate">카테고리<br>신규 등록</p>
                        </div>
                        <input type="text" id="newCategory" class="categoryInputSmall" placeholder="카테고리 입력">
                        <input type="text" id="newDetailCategory" class="categoryInputBigger" placeholder="세부 카테고리 입력">
                        <button class="saveNewButton" data-id="${id}">신규 등록</button>
                    </td>
                </tr>`;
            $('#tableBody').append(newRow);
        } 
    });

    // 저장 버튼 클릭 시 (수정 등록)
    $(document).on('click', '.saveEditButton', function() {
        var product_cate_seq = $(this).attr('data-id');
        var category = $(`#editCategory${product_cate_seq}`).val();
        var detail_category = $(`#editDetailCategory${product_cate_seq}`).val();

        $.ajax({
            url: '/heehee/admin/updateCategory',
            method: 'POST',
            data: { 'product_cate_seq': product_cate_seq, 
            		'category': category, 
            		'detail_category':detail_category 
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
    $(document).on('click', '.saveNewButton', function() {
        var category = $('#newCategory').val();
        var detail_category = $('#newDetailCategory').val();

        $.ajax({
            url: '/heehee/admin/insertCategory',
            method: 'POST',
            data: { 'category': category, 
            		'detail_category': detail_category 
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
                    var product_cate_seq = $(this).attr('data-id');

                    $.ajax({
                        url: '/heehee/admin/deleteCategory',
                        method: 'POST',
                        data:{'product_cate_seq': product_cate_seq
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