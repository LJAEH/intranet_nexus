// sweetAlert 
const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-right',
  iconColor: 'white',
  customClass: {
    popup: 'colored-toast'
  },
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true
})

function copyURL(surveyNo){
    // 이걸로 지정한 값이 복사댐


    const textToCopy = "http://nexus.o-r.kr:8080/survey/surveyDetail/" + surveyNo;

    const textarea = document.createElement('textarea');
    textarea.value = textToCopy;
    document.body.appendChild(textarea);

    textarea.select();
    textarea.setSelectionRange(0, textarea.value.length);

    try {
      document.execCommand('copy');
      Toast.fire({
        icon: 'success',
        title: '복사가 되었습니다'
      });
    } catch (err) {
      console.error('복사에 실패했습니다.', err);
    }

    document.body.removeChild(textarea);
 
}

// 날짜 => 글자 변경 로직
function getCurrentDate() {
  var currentDate = new Date();
  var year = currentDate.getFullYear();
  var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
  var day = currentDate.getDate().toString().padStart(2, '0');
  var currentDateStr = year + '.' + month + '.' + day;
  return currentDateStr;
}


function addStatusLabel() {
  var endDateElements = document.querySelectorAll('#status-color');
  var currentDate = getCurrentDate();

  endDateElements.forEach(function (element) {
  var endDate = element.textContent.trim();
  if (endDate >= currentDate) {
      element.textContent = '진행중';
  } else {
      element.textContent = '종료';
      element.style.color = 'gray'; 
  }
});
}

window.addEventListener('DOMContentLoaded', function () {
  addStatusLabel();
});


function trashBtn(surveyNo) {
  Swal.fire({
    title: '정말 삭제 하시겠습니까?',
    text: "",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: '삭제',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = "../survey/surveyDelete/" + surveyNo;
    }
  })
}