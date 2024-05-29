

$(function() {
	const burger = document.querySelector('.burger');
	const navLinks = document.querySelector('.nav-links');
	
	burger.addEventListener('click', () => {
    navLinks.classList.toggle('nav-active');
	});
	
	
	$('#url_copy').on('click', function(e){
		e.preventDefault();
		var link = location.href;
		copyClip(link);
	});
	}
)

function copyClip(url){
		var $temp = $('<input>');
		$('body').append($temp);
		$temp.val(url).select();
		document.execCommand('copy');
		$temp.remove();
		alert('URL이 복사되었습니다.');
	}

