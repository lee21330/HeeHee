$(function() {
    changeStatus('all');
    showUserInfo();
    
    $("#deal_auc").hide();
    
    $("#btn_search").on("click", show);
    $(".mModal_close").on("click", hide);
  
    $("#btn_charge").on("click", chargePoint);
});
function changeDeal(deal) {
    if (deal === '중고물품') {
        $("#deal_sell").show();
        $("#deal_auc").hide();
        
        $("#sell").addClass("select_deal");
        $("#auction").removeClass("select_deal");
        
        changeStatus('all');
        $("#menu li").removeClass("select");
        $("#menu li").eq(0).addClass("select");
        $('#selectMenu').val("all");

    
        
    } else {
        $("#deal_auc").show();
        $("#deal_sell").hide();
        
        $("#auction").addClass("select_deal");
        $("#sell").removeClass("select_deal");
        
        changeStatus_auc('all');
        $("#menu_auc li").removeClass("select");
        $("#menu_auc li").eq(0).addClass("select");
       	$('#selectMenuAuc').val("all");
     
    }
}
function show() {
    $("#search").addClass("show");
}
function hide() {
    $("#search").removeClass("show");
}
function showUserInfo() {
    //평점 별로 표현
    var userRating = parseInt($('#myRating').text(), 10);
    var stars = $('.star');
    for (var i = 0; i < userRating; i++) {
        stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
    }
}

function changeMenu(event) {
    $("#menu li").removeClass("select");
    $(event.target).addClass("select");
}
function changeMenuAuc(event) {
    $("#menu_auc li").removeClass("select");
    $(event.target).addClass("select");
}

function changeStatus(status) {
    $(".list").empty();
    $(".sub_menu").show();
    $.ajax({
        url: '/heehee/mypage/main/searchSaleStatus',
        method: 'GET',
        data: { 'status': status },
        success: function(data) {
            var salelist = $('#salelist');
            console.log(data);
            var output = '';
            if (data.length === 0) {
                output = "<p class='message'>해당 판매 내역이 없습니다.</p>";
            } else {
                data.forEach(function(sale, index) {
                    var detailUrl = '';
                    if (sale.proStatus === '판매중' || sale.proStatus === '판매보류' || sale.proStatus === '판매중지') {
                        detailUrl = "/heehee/sell/productdetail/" + sale.productSeq;
                    } else {
                        detailUrl = "/heehee/mypage/saledetail/" + sale.productSeq;
                    }
                    var dropdownId = 'dropdown_' + index;
                    var subDropdownId = 'sub_dropdown_' + index;
                    var statusChangeMenu = '';
                    var productBanReason = '';
                    if (sale.proStatus === '판매중') {
                        statusChangeMenu = `
                            <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                onclick="dropdown('${dropdownId}')">
                            <ul id="${dropdownId}" class="dropdown dropdown-menu" style="display: none;">
                                <li onclick="location.href='/heehee/sell/productmodify/${sale.productSeq}'">상품수정</li>
                                <li onclick="sub_dropdown('${subDropdownId}')">상태변경</li>
                                <li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            </ul>
                            <ul id="${subDropdownId}" class="sub_dropdown sub-dropdown-menu" style="display: none;">
                                <li onclick="hideSubDropdown('${subDropdownId}')">취소</li>
                                <li onclick="updateStatus(${sale.productSeq},'판매중')" id="statusDropdown">판매보류</li>
                            </ul>`;
                    } else if (sale.proStatus === '판매보류') {
                        statusChangeMenu = `
                            <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                onclick="dropdown('${dropdownId}')">
                            <ul id="${dropdownId}" class="dropdown dropdown-menu" style="display: none;">
                                <li onclick="sub_dropdown('${subDropdownId}')">상태변경</li>
                                <li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            </ul>
                            <ul id="${subDropdownId}" class="sub_dropdown sub-dropdown-menu" style="display: none;">
                                <li onclick="hideSubDropdown('${subDropdownId}')">취소</li>
                                <li onclick="updateStatus(${sale.productSeq},'판매보류')" id="statusDropdown">판매중</li>
                            </ul>`;
                    } else if (sale.proStatus === '판매중지') {
                        productBanReason = `<div class="productBanReason"><p>판매중지</p></div>`;
                    }
                    
                     // "판매중지" 상태일 때의 배경색 지정
                    var productStyle = sale.proStatus === '판매중지' ? 'background-color: orangered;' : '';
                    var imgClass = sale.proStatus === '판매중지' ? 'gray-scale' : '';
                   
                    output += `             
                        <div class="product">
                            <div>${statusChangeMenu}</div>
                            <div onclick="location.href='${detailUrl}'">
                                <div class="product_img ${imgClass}">
                                <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
                                ${productBanReason}
                                </div>
                                <p>${sale.articleTitle}</p>
                                <p id="productPrice">${sale.productPrice}원</p>
                                <span id="s_statusVal_div"><p id="s_statusVal" style="${productStyle}">${sale.proStatus}</p></span>
                            </div>
                        </div>`;
                });
            }
            salelist.html(output);
        },
        error: function(xhr, status, error) {
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        }
    });
}
function dropdown(id) {
    // 다른 모든 드롭다운 메뉴 숨기기
    $('.dropdown-menu').hide();
    $('#' + id).show(); // 특정 id를 가진 요소를 토글
}
function sub_dropdown(id) {
    // 다른 모든 서브 드롭다운 메뉴 숨기기
    $('.sub-dropdown-menu').hide();
    $('#' + id).show(); // 특정 id를 가진 요소를 토글
}
function hideSubDropdown(id) {
    $('#' + id).hide(); // 특정 id를 가진 요소를 숨기기
}
function updateStatus(productSeq, proStatus) {
    $.ajax({
        url: '/heehee/mypage/main/updateStatus',
        method: 'POST',
        data: { 'productSeq': productSeq, 'proStatus': proStatus },
        success: function(data, status, xhr) {
            if (data.success) {
                showTost(data.message); // 성공 메시지를 표시
            } else {
                showTost(data.message); // 실패 메시지를 표시
            }
            window.location.reload();
        },
        error: function(xhr, status, error) {
            console.error('판매상태 업데이트 오류:', error);
        }
    });
}
function deleteSale(productSeq) {
    $.ajax({
        url: '/heehee/mypage/main/deleteSale',
        method: 'POST',
        data: { 'productSeq': productSeq },
        success: function(data, status, xhr) {
            if (data.success) {
                showTost(data.message); // 성공 메시지를 표시
            } else {
                showTost(data.message); // 실패 메시지를 표시
            }
        },
        error: function(xhr, status, error) {
            console.error('판매 삭제 오류:', error);
        }
    });
}
function showPurchaseList() {
    $(".list").empty();
    $(".sub_menu").hide();
    $.ajax({
        url: '/heehee/mypage/main/purchaselist',
        method: 'POST',
        success: function(data) {
            var purchaselist = $('#purchaselist');
            console.log(data);
            var output = '';
            if (data.length === 0) {
                output = "<p class='message'>해당 구매 내역이 없습니다.</p>";
            } else {
                data.forEach(function(purchase) {
                    output += `
                            <div class="product" onclick="location.href='/heehee/mypage/purchasedetail/${purchase.productSeq}'">
                                <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${purchase.imgName}">
                                <p>${purchase.articleTitle}</p>
                                <p>${purchase.productPrice}원</p>
                            </div>`;
                });
            }
            purchaselist.html(output);
        },
        error: function(xhr, status, error) {
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        }
    });
}
function showJjimList() {
    $(".list").empty();
    $(".sub_menu").hide();
    $.ajax({
        url: '/heehee/mypage/main/jjimlist',
        method: 'POST',
        success: function(data) {
            var jjimlist = $('#jjimlist');
            console.log(data);
            var output = '<div id="deleteJjim"><input type="button" value="삭제" class="btn_jjim" onclick="deleteJjim()"></div>';
            if (data.length === 0) {
                output = "<p class='message'>찜 내역이 없습니다.</p>";
            } else {
                data.forEach(function(jjim) {
                    output += `
                            <div class="product">
                                <input type="checkbox" name="checkBno" value="${jjim.productSeq}"> 
                                <div onclick="location.href='/heehee/sell/productdetail/${jjim.productSeq}'">
                                    <img class="product_img"
                                        src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
                                    <p>${jjim.articleTitle}</p>
                                    <p>${jjim.productPrice}원</p>
                                </div>
                            </div>`;
                });
            }
            jjimlist.html(output);
        },
        error: function(xhr, status, error) {
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        }
    });
}
function deleteJjim() {
    var seqList = [];
    $('input[name="checkBno"]:checked').each(function() {
        seqList.push($(this).val()); // 선택된 체크박스의 값(proSeq)을 배열에 추가
    });
    if (seqList.length === 0) {
        alert('삭제할 상품을 선택해주세요.');
        return;
    }
    $.ajax({
        url: '/heehee/mypage/main/deleteJjim',
        method: 'POST',
        traditional: true, // jQuery에서 배열 파라미터를 전송할 때 필요한 옵션
        data: { seq: seqList },
        success: function(response) {
            alert('선택한 상품을 삭제했습니다.');
            showJjimList(); // 삭제 후 목록을 다시 불러옴
        },
        error: function(xhr, status, error) {
            alert('삭제 중 오류가 발생했습니다.');
        }
    });
}
//경매페이지
function changeStatus_auc(status) {
    $(".list").empty();
    $(".sub_menu").show();
    $.ajax({
        url: '/heehee/mypage/main/searchSaleStatusAuc',
        method: 'GET',
        data: { 'status': status },
        success: function(data) {
            var salelist = $('#salelist_acu');
            console.log(data);
            var output = '';
            if (data.length === 0) {
                output = "<p class='message'>해당 판매 내역이 없습니다.</p>";
            } else {
                data.forEach(function(sale) {
                    var detailUrl = sale.aucStatus === '입찰' ? '/heehee/auc/detail/' + sale.productSeq : '/heehee/mypage/saledetailAuc/' + sale.productSeq;
                    // 가격 표시를 조건부로 설정
                    var priceDisplay = (sale.aucStatus === '낙찰' || sale.aucStatus === '거래 완료') ? `<p>${sale.aucPrice}원</p>` : '';
                    output += `                         
                            <div class="product" onclick="location.href='${detailUrl}'">
                                <div class="product_slider">
                                    <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${sale.imgName}">
                                </div>     
                                <p>${sale.auctionTitle}</p>
                                ${priceDisplay}
                                <p id="s_statusVal">${sale.aucStatus}</p>
                            </div>`;
                });
            }
            salelist.html(output);
        },
        error: function(xhr, status, error) {
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        }
    });
}
function showPurchaseList_auc() {
    $(".list").empty();
    $(".sub_menu").hide();
    $.ajax({
        url: '/heehee/mypage/main/purchaselistAuc',
        method: 'POST',
        success: function(data) {
            var purchaselist = $('#purchaselist_acu');
            console.log(data);
            var output = '';
            if (data.length === 0) {
                output = "<p class='message'>해당 구매 내역이 없습니다.</p>";
            } else {
                data.forEach(function(purchase) {
                    output += `
                            <div class="product" onclick="location.href='/heehee/mypage/purchasedetailAuc/${purchase.productSeq}'">
                                <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${purchase.imgName}">
                                <p>${purchase.auctionTitle}</p>
                                <p>${purchase.aucPrice}원</p>
                            </div>`;
                });
            }
            purchaselist.html(output);
        },
        error: function(xhr, status, error) {
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        }
    });
}
