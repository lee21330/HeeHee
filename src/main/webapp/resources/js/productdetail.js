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

    // Event listener for the url_copy image
    $('#url_copy').on('click', function(e) {
        e.preventDefault();
        var link = window.location.href; // Get the current page URL
        copyToClipboard(link); // Call the function to copy the URL
    });
});

