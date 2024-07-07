$(document).ready(function() {
	var todayDate = $("#month_option").val(new Date().toISOString().slice(0,7));
	searchPointList(todayDate);
 //   $('#month_list').on('click', function() {
 //       $('#month_option').click();
  //      $('#month_option').show();
 
    //});

});

function searchPointList(){
	var month = $("#month_option").val();

	$.ajax({
		url: '/heehee/mypage/pointlist/searchPoint',
		method: 'GET',
		data: { 'month': month },
		success: function(data) {
			var pointHistory = $('#point_history');
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>충전 내역이 없습니다.</p>";
			} else {

				data.forEach(function(p) {
					let dt = new Date(p.payDate).toLocaleDateString();
					const formattedPoint = new Intl.NumberFormat().format(p.amount);
					output += `
                            <div class="detail">
						<p class="detail_date">${dt}</p>
						<p id="detail_point">${formattedPoint}원</p>
					</div>`;
				});
			}

			pointHistory.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}