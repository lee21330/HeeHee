$(document).ready(function() {
    $('#month_list').on('click', function() {
        var dropdown = $('#month_dropdown');
        if (dropdown.css('display') === 'none' || dropdown.css('display') === '') {
            dropdown.css('display', 'block');
        } else {
            dropdown.css('display', 'none');
        }
    });

    $('.month_option').on('click', function() { // 월 선택 시
        $('#month_dropdown').css('display', 'none'); // 월 선택상자 사라짐
    	// 여기다 월별 포인트 내역 구현하면 될거야 아마도..?
    });

    $(document).on('click', function(event) {
        if (!$(event.target).closest('#month_list, #month_dropdown').length) {
            $('#month_dropdown').css('display', 'none');
        }
    });
});