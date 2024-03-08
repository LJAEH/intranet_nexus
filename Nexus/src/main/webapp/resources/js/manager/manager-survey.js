// 모달창 스타일
const btn = document.getElementById('popupBtn');
const modal = document.getElementById('modalWrap');
const closeBtn = document.getElementById('closeBtn');
const modalBody = document.querySelector('.modalBody');
const cancellBtn = document.getElementById('cancell-btn');
const noticeTitle = document.querySelector('.modal-title > input');
const noticeContent = document.querySelector('.modal-detail > textarea');

// 공지사항 버튼 이벤트
btn.addEventListener("click", () => {
  
  modal.style.display = 'block';
  modalBody.classList.add('modal-open');

}) 

// 모달창 엑스 버튼
closeBtn.addEventListener("click", () => {
  modalClose();
});

// 모달창 외부 영역 이벤트
window.onclick = function(event) {
  if (event.target == modal) {
    modalClose();
  }
}

// 취소버튼 이벤트
cancellBtn.addEventListener("click", () => {
  modalClose();
})

// 모달창 닫는 함수
function modalClose() {
  modalBody.classList.add('modal-close');
  
  setTimeout(() => {
    modal.style.display = 'none';
    modalBody.classList.remove("modal-close");
  }, 350);

  noticeContent.style.overflow = 'hidden';

  noticeContent.style.height = 'inherit';
}











