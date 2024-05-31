//수정 창 실행시
document.addEventListener


//테이블 체크박스 선택 js
$(document).ready(function () {

  $("tbody tr").click(function(){
    //console.log($(this).children(":nthchild(2)").html().trim());
      var checkbox = $(this).find('td:first-child :checkbox');
      checkbox.prop('checked', !checkbox.is(':checked'));

      var arr = [];
      
      $("tbody .cb:checked").each(function () { 
        arr.push($(this).val()); 
      })
      
      $("#res").html(arr.toString());
      

      if(arr.length == $("tbody .cb").length){
        $("thead .cb").prop("checked", true);
        
      } else { 
        $("thead .cb").prop("checked", false);
      }
});

$("tbody .cb").click(function(){
    var arr = [];
    $("tbody .cb:checked").each(function () { 
      arr.push($(this).val()); 
    })
    
    $("#res").html(arr.toString());

    var checkbox = $(this);
    checkbox.prop('checked', !checkbox.is(':checked'));


    if(arr.length == $("tbody .cb").length){ 
      $("thead .cb").prop("checked", true);
      
    } else { 
      $("thead .cb").prop("checked", false);
    }
    

});

$("thead .cb").click(function() { 
    if($(this).is(":checked")){ 
      $("tbody .cb").prop("checked", true);
    } else { 
      $("tbody .cb").prop("checked", false); 
    }

    var arr = [];
    
    $("tbody .cb:checked").each(function () { 
      arr.push($(this).val()); 
    })
    
    $("#res").html(arr.toString());

  }); 
  
  
}); 

//테이블 체크박스 선택시 js
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