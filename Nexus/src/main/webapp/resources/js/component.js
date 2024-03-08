$(document).ready(function () {

    $("#sidebar-toggle").on("click", function () {
        $("section").toggleClass("no-sidebar");
    });

});


// 사이드바 반응형
const sidebarToggle = document.querySelector('.sidebarIconToggle');
const sidebarMenu = document.getElementById('sidebarMenu');

sidebarToggle.addEventListener('click', function() {
  document.body.classList.toggle('show-sidebar');
});

window.addEventListener('click', function(event) {
  if (!sidebarMenu.contains(event.target) && !sidebarToggle.contains(event.target)) {
    document.body.classList.remove('show-sidebar');
  }

});