document.addEventListener('DOMContentLoaded', function() {
	var dropdowns = document.querySelectorAll('.dropdown > a');

	dropdowns.forEach(function(dropdown) {
		dropdown.addEventListener('click', function() {
			var submenu = this.nextElementSibling;
			var isVisible = submenu.style.display === 'block';

			// Hide all submenus
			document.querySelectorAll('.submenu').forEach(function(sub) {
				sub.style.display = 'none';
			});

			// Toggle the clicked submenu
			if (!isVisible) {
				submenu.style.display = 'block';
			} else {
				submenu.style.display = 'none';
			}
		});

		// Hover 효과 구현
		dropdown.addEventListener('mouseenter', function() {
			dropdown.classList.add('hovered');
		});

		dropdown.addEventListener('mouseleave', function() {
			dropdown.classList.remove('hovered');
		});

	});
	
		//클릭된 메뉴의 background 변경
		const clickableLinks = document.querySelectorAll(".movePage, .dropdown > a");

		function handleClick(event) {
		// div에서 모든 "click" 클래스 제거
		clickableLinks.forEach((link) => {
			link.classList.remove("click");
		});

		// 클릭한 div만 "click"클래스 추가
		event.target.classList.add("click");
		}

		clickableLinks.forEach((link) => {
			link.addEventListener("click", handleClick);
		});

	// Hide all submenus on page load
	document.querySelectorAll('.submenu').forEach(function(sub) {
		sub.style.display = 'none';
    });
});