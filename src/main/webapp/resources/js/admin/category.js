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
			url: '/heehee/admin/searchCategoryInfo',
			method: 'GET',
			data: { 'category': category, 
					'keyword': keyword, 
					},
			success: function(data) {
				var tableBody = $('#tableBody');
				tableBody.empty();

				data.forEach(function(item) {
					var row = 
						"<tr>" + 
							"<td><input type='checkbox' class='rowCheckbox' data-id='" + item.productCateSeq + "'></td>" + 
							"<td>" + item.productCateSeq + "</td>" + 
							"<td>" + item.category + "</td>" + 
							"<td>" + item.detailCategory + "</td>" + 
							"<td>" + item.id + "</td>" + 
							"<td>" + new Date(item.createDate).toLocaleDateString() + "</td>" + 
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

			// 수정할 내용 입력란을 추가
			if (row.next().hasClass('editRow')) {
				row.next().remove();
				} else {
					var editRow = 
						"<tr class='editRow'>" + 
							"<td colspan='6'>" + 
								"<div class='updateContainer'>" + 
									"<p class='editTitle'>카테고리<br>수정</p>" + 
								"<input type='text' id='editCategory" + id + "' class='categoryInputSmall' placeholder='카테고리 입력' value='" + row.find('td').eq(2).text() + "'>" + 
								"<input type='text' id='editDetailCategory" + id + "' class='categoryInputBigger' placeholder='세부 카테고리 입력' value='" + row.find('td').eq(3).text() + "'>" + 
								"<button id='saveEditButton' class='saveEditButton' data-id='" + id + "'>수정 등록</button>" + 
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

	// 신규 등록 버튼 클릭 시
	$('#addButton').click(function() {
		var id = 'new'; //임시ID

		// 기존의 수정 행을 제거
		if ($('.editRow').length > 0){
			$('.editRow').remove();
			}

		// 신규 등록할 내용 입력란을 추가
		if ($('#tableBody').children('.newRow').length > 0) {
			$('.newRow').remove();
			} else {
				var newRow = 
					"<tr class='newRow'>" + 
						"<td colspan='6'>" + 
							"<div class='updateContainer'>" + 
								"<p class='editTitle'>카테고리<br>신규등록</p>" + 
							"<input type='text' id='newCategory' class='categoryInputSmall' placeholder='카테고리 입력'>" + 
							"<input type='text' id='newDetailCategory' class='categoryInputBigger' placeholder='세부 카테고리 입력'>" + 
							"<button id='saveNewButton' class='saveNewButton' data-id='" + id + "'>신규 등록</button>" + 
							"</div>" + 
						"</td>" + 
					"</tr>";
				$('#tableBody').append(newRow);
			}
	});

	// 저장 버튼 클릭 시 (수정 등록)
	$(document).on('click', '#saveEditButton', function() {
		var productCateSeq = $(this).attr('data-id');
		var category = $("#editCategory" + productCateSeq).val();
		var detailCategory = $("#editDetailCategory" + productCateSeq).val();

		$.ajax({
			url: '/heehee/admin/updateCategory',
			method: 'POST',
			data: { 'productCateSeq': productCateSeq, 
					'category': category, 
					'detailCategory':detailCategory 
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
		var category = $('#newCategory').val();
		var detailCategory = $('#newDetailCategory').val();

		$.ajax({
			url: '/heehee/admin/insertCategory',
			method: 'POST',
			data: { 'category': category, 
					'detailCategory': detailCategory 
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
					var productCateSeq = $(this).attr('data-id');

					$.ajax({
						url: '/heehee/admin/deleteCategory',
						method: 'POST',
						data:{'productCateSeq': productCateSeq
								},
						success: function() {
							loadTable();
						},
						error: function() {
							alert('삭제 중 오류가 발생했습니다.');
						}
					});
				})
			}
		} else {
            alert('삭제할 항목을 선택해주세요');
        }
    });

	function getSelectedRow() {
		return $('input.rowCheckbox:checked');
	};
});

/* 
<script>
   const products = [];

    // JSP에서 서버 데이터 받아오기
    <c:forEach var="product" items="${prodAllOrders}">
        products.push({
            img_url: '${product.IMG_URL}',
            brand: '${product.BRAND}',
            prod_name: '${product.PROD_NAME}',
            prod_price: parseInt('${product.PROD_PRICE}')
        });
    </c:forEach>
    console.log(products);
    const productsPerPage = 12;
    let currentPage = 1;
    let sortedProducts = [...products];

    const sortProducts = (criteria) => {
        if (criteria === 'low') {
            sortedProducts.sort((a, b) => a.prod_price - b.prod_price);
        } else if (criteria === 'high') {
            sortedProducts.sort((a, b) => b.prod_price - a.prod_price);
        }
        renderProducts();
        renderPagination();
    };

    document.getElementById('sort-low-price').addEventListener('click', () => {
        sortProducts('low');
    });

    document.getElementById('sort-high-price').addEventListener('click', () => {
        sortProducts('high');
    });

    const renderProducts = () => {
        const productList = document.getElementById('product-list');
        productList.innerHTML = '';
        const start = (currentPage - 1) * productsPerPage;
        const end = start + productsPerPage;
        const paginatedProducts = sortedProducts.slice(start, end);
        
        paginatedProducts.forEach(product => {
            const productDiv = document.createElement('div');
            productDiv.classList.add('product');
            productDiv.innerHTML = ` 
                <img src='\${product.img_url}' alt='상품 이미지'>
                <p>\${product.brand}</p>
                <p>\${product.prod_name}</p>
                <p>\${product.prod_price}원</p>
                <button>❤</button>
            `; 
            
            productList.appendChild(productDiv);
        });
    };

    const renderPagination = () => {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = '';
        const totalPages = Math.ceil(sortedProducts.length / productsPerPage);

        const createButton = (text, pageNumber, disabled = false) => {
            const button = document.createElement('button');
            button.innerText = text;
            button.disabled = disabled;
            button.classList.toggle('active', pageNumber === currentPage);
            button.addEventListener('click', () => {
                currentPage = pageNumber;
                renderProducts();
                renderPagination();
            });
            return button;
        };

        pagination.appendChild(createButton('<<', 1, currentPage === 1));
        pagination.appendChild(createButton('<', currentPage - 1, currentPage === 1));
        for (let i = 1; i <= totalPages; i++) {
            pagination.appendChild(createButton(i, i));
        }
        pagination.appendChild(createButton('>', currentPage + 1, currentPage === totalPages));
        pagination.appendChild(createButton('>>', totalPages, currentPage === totalPages));
    };

    document.addEventListener('DOMContentLoaded', () => {
        renderProducts();
        renderPagination();
    });
</script>    
*/







