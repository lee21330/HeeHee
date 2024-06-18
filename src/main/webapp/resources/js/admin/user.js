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
        var startDate = $('#startDate').val();
        var endDate = $('#endDate').val();

        $.ajax({
            url: '/your-server-endpoint',
            method: 'GET',
            data: { 
            	'category': category, 
            	'keyword': keyword, 
            	'startDate': startDate, 
            	'endDate': endDate 
            	},
            success: function(data) {
                var tableBody = $('#tableBody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = `<tr>
                        <td>${item.memberNumber}</td>
                        <td>${item.memberName}</td>
                        <td>${item.userID}</td>
                        <td>${item.email}</td>
                        <td>${item.phoneNumber}</td>
                        <td>${item.address}</td>
                        <td>${item.joinDate}</td>
                    </tr>`;
                    tableBody.append(row);
                });
            },
            error: function(xhr, status, error) {
                alert('데이터를 가져오는 중 오류가 발생했습니다.');
            }
        });
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
            var lastDayLastMonth = new Date(today.getFullYear(), today.getMonth(), 0);
            startDate = firstDayLastMonth.toISOString().split('T')[0];
            endDate = lastDayLastMonth.toISOString().split('T')[0];
        } else if (this.id === 'oneMonth') {
            startDate = new Date(today);
            startDate.setDate(today.getDate() - 30);
            startDate = startDate.toISOString().split('T')[0];
            endDate = new Date().toISOString().split('T')[0];
        } else if (this.id === 'threeMonth') {
            startDate = new Date(today);
            startDate.setDate(today.getDate() - 90);
            startDate = startDate.toISOString().split('T')[0];
            endDate = new Date().toISOString().split('T')[0];
        }
        
        $('#startDate').val(startDate);
        $('#endDate').val(endDate);
        loadTable();
    });
 });