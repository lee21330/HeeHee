$(document).ready(function() {
    // Function to copy the URL to clipboard
    function copyToClipboard(text) {
        var $temp = $('<input>');
        $('body').append($temp);
        $temp.val(text).select();
        document.execCommand('copy');
        $temp.remove();
        alert('URL이 복사되었습니다.');
    }

    // Event listener for copying the URL
    $('#url_copy').on('click', function(e) {
        e.preventDefault();
        var link = window.location.href; // Get the current page URL
        copyToClipboard(link); // Call the function to copy the URL
    });

    // Event listener for scrolling to the top
    $('#gotop').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, 500); // Smooth scroll to top
    });
});
