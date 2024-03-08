
document.getElementById('project-checked').addEventListener('change', function() {
var projectNo = this.value;

// 각 'assignment-checked' select 요소를 숨기고 선택한 프로젝트 번호에 일치하는 것만 표시합니다.
var assignmentSelects = document.getElementsByClassName('assignment-checked');
for (var i = 0; i < assignmentSelects.length; i++) {
    var select = assignmentSelects[i];
    if (select.getAttribute('data-project-no') == projectNo) {
    select.style.display = 'block';
    } else {
    select.style.display = 'none';
    }
}
});

// 처음 생성된 과제 띄우기
document.addEventListener('DOMContentLoaded', function() {
var selects = document.getElementsByClassName('assignment-checked');
if(selects.length > 0) {
    selects[0].style.display = 'block';
} 
});

document.addEventListener('DOMContentLoaded', function() {
var selects = document.getElementsByClassName('assignment-checked');
for (var i = 1; i < selects.length; i++) {
    selects[i].style.display = 'none';
}
});