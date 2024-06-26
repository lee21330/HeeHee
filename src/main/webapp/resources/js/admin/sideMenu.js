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
    });

    // Hide all submenus on page load
    document.querySelectorAll('.submenu').forEach(function(sub) {
        sub.style.display = 'none';
    });
});