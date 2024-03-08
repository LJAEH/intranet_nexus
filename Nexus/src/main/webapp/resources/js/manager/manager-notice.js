
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
  noticeTitle.value = '';
  noticeContent.value = '';

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



// 자동 높이 조정 textarea

function handleResizeHeight(obj) {
  obj.style.height = 'auto';
  obj.style.height = obj.scrollHeight + 'px';
 
  
  const computedStyles = window.getComputedStyle(obj);
  if (obj.scrollHeight >= parseInt(computedStyles.maxHeight)) {
    obj.style.overflow = "scroll";
  } else {
    obj.style.overflow = "hidden";
  }
}


// 파일 업로드 스타일
const fileUpload = document.getElementById('file-uploads');
const preview = document.querySelector('.preview');

fileUpload.style.opacity = 0;

fileUpload.addEventListener('change', updateImageDisplay);

function updateImageDisplay(event) {
  while(preview.firstChild) {
    preview.removeChild(preview.firstChild);
  }

  const curFiles = fileUpload.files;
  if(curFiles.length === 0) {
    const para = document.createElement('p');
    para.textContent = '선택된 파일이 없습니다.';
    preview.appendChild(para);
  } else {
    const list = document.createElement('ol');
    preview.appendChild(list);

    for(const file of curFiles) {
      const listItem = document.createElement('li');
      const para = document.createElement('p');
      if(validFileType(file)) {
        para.innerHTML = `파일 이름 - ${file.name}<br> 파일 크기 - ${returnFileSize(file.size)}.`;
  
        listItem.appendChild(para);
      } else {
        para.textContent = `파일 이름 - ${file.name}: 해당파일은 불러올수 없습니다.`;
        listItem.appendChild(para);
      }

      list.appendChild(listItem);
    }
  }

  // 올린파일 들고오기
  const upFile = event.target.files[0];
  const reader = new FileReader();
  const fileInfo = `${upFile.name}`;

  reader.onload = function(e) {
    checkPreview.innerText = fileInfo;
  };

  reader.readAsDataURL(upFile);


  // 다운로드
  

}

const fileTypes = [
  // 이미지 파일
  "image/apng",
  "image/bmp",
  "image/gif",
  "image/jpeg",
  "image/pjpeg",
  "image/png",
  "image/svg+xml",
  "image/tiff",
  "image/webp",
  "image/x-icon",
  
  // 텍스트 파일
  "text/plain",
  "text/html",
  "text/css",
  "text/javascript",
  
  // 음악 파일
  "audio/mpeg",
  "audio/ogg",
  "audio/wav",
  
  // 비디오 파일
  "video/mpeg",
  "video/mp4",
  "video/quicktime",
  
  // 문서 파일
  "application/pdf",
  "application/msword",
  "application/vnd.ms-excel",
  "application/vnd.ms-powerpoint",
  
  // 압축 파일
  "application/zip",
  "application/x-rar-compressed",
  "application/x-tar",
  
  // 기타 파일 유형
  "application/octet-stream"
];


function validFileType(file) {
  return fileTypes.includes(file.type);
}

function returnFileSize(number) {
  if(number < 1024) {
    return number + 'bytes';
  } else if(number >= 1024 && number < 1048576) {
    return (number/1024).toFixed(1) + 'KB';
  } else if(number >= 1048576) {
    return (number/1048576).toFixed(1) + 'MB';
  }
}

// 파일 지우기
const fileRemove = document.getElementById("file-remove");

fileRemove.addEventListener("click", () => {
  preview.firstChild.remove();
  
})


// 글 추가시 나타나는 기능
const successBtn = document.getElementById('success-btn');


successBtn.addEventListener("click", () => {

  if(noticeTitle.value == "") {
    Swal.fire('제목을 입력해 주세요');
  } else if(noticeContent.value == '') {
    Swal.fire('내용을 입력해 주세요')
  } else {
    
    const tr = document.createElement('tr');
    const tdNum = document.createElement('td');
    const tdTitle = document.createElement('td');
    const tdNode = document.createTextNode(noticeTitle.value);
    const tdDate = document.createElement('td');
  
    tdTitle.append(tdNode);
    tr.append(tdNum, tdTitle, tdDate);
  
    document.querySelector('tbody').append(tr);
    
    // 수정 모달창 열기
    tr.addEventListener("click", function() {
      modifyModal();
    })
    
    // 모달 닫기
    modalClose();
  }

})

// 수정모달창
const checkModal = document.getElementById('check-modalWrap');
const checkCloseBtn = document.getElementById('check-closeBtn');
const checkModalBody = document.querySelector('.check-modalBody');
const checkCancellBtn = document.getElementById('check-cancell-btn');
const checkModalTitle = document.getElementById('check-modal-title');
const checkModalDetail = document.querySelector('.check-modal-detail');
const checkPreview = document.querySelector('.check-preview');
const modifyBtn = document.getElementById('check-success-btn');
const doneBtn = document.getElementById('done-btn');
const fileBox = document.querySelector('.check-file-box');

let globalNoticeNo = 0;
// 게시글 디테일 창 오픈
function detailModal(noticeNo) {

  $.ajax({
    url: "noticeDetail",
    data: { "noticeNo": noticeNo },
    type: "GET",
    dataType: "JSON",
    success: function (detail) {
      const checkModalTitleSpan = document.createElement("span");
      checkModalTitleSpan.innerText = detail.title;
      checkModalTitle.append(checkModalTitleSpan);

      const checkModalDetailSpan = document.createElement("span");
      checkModalDetailSpan.innerHTML = detail.content;
      checkModalDetail.append(checkModalDetailSpan);

      globalNoticeNo = noticeNo;

      if (detail.NoticeFileOrigin) {
        const checkPreviewA = document.createElement("a");
        checkPreviewA.innerText = detail.NoticeFileOrigin;
        checkPreviewA.href = "/intranet" + detail.NoticeFileRename;
        checkPreviewA.download = detail.boardFileOrigin;
        checkPreview.append(checkPreviewA);
      } else {
        const checkPreviewA = document.createElement("p");
        checkPreviewA.innerText = "파일 없음";
        checkPreview.append(checkPreviewA);
      }

      // 수정 버튼 눌렀을 때 이벤트
      let isEditMode = false; // 수정 모드 상태를 나타내는 변수

      modifyBtn.addEventListener('click', () => {

        if (isEditMode) {
          // 이미 수정 모드인 경우, 동작을 수행하지 않고 반환
          return;
        }
      
        isEditMode = true; // 수정 모드로 변경

        const noticeTitle = document.querySelector('.check-modal-title');
        const noticeContent = document.querySelector('.check-modal-detail');
       
        
        const modifyNoticeTitle = document.createElement('input');
        const modifyNoticeContent = document.createElement('textarea');
        
        modifyNoticeTitle.value = checkModalTitleSpan.innerText;
        modifyNoticeContent.innerHTML = checkModalDetailSpan.innerText.replace(/<br>/g, '\n');
       
        noticeTitle.innerHTML = '';
        noticeTitle.appendChild(modifyNoticeTitle);

        noticeContent.innerHTML = '';
        noticeContent.appendChild(modifyNoticeContent);

        modifyNoticeContent.style.minHeight = '350px';
        modifyNoticeContent.style.overflow = 'auto';

        fileBox.style.display = 'block';
        

        // 줄바꿈이 적용되도록 스타일 설정
        modifyNoticeContent.style.whiteSpace = 'pre-wrap';

        // 수정 버튼을 none으로 설정
        modifyBtn.style.display = "none";

        
        doneBtn.style.display = "inline-block";

        doneBtn.addEventListener('click', () => {
          if (!isEditMode) {
          // 수정 모드가 아닌 경우, 동작을 수행하지 않고 반환
          return;
          }

          isEditMode = false; // 수정 모드 종료

          const formData = new FormData();
          formData.append('noticeNo', globalNoticeNo);
          formData.append('title', modifyNoticeTitle.value);
          formData.append('content', modifyNoticeContent.value);

          // 파일 추가
          const fileInput = document.getElementById('file-uploads');
          const files = fileInput.files;
          for (let i = 0; i < files.length; i++) {
            formData.append('uploadFile', files[i]);
          }

        
          
          $.ajax({
            url: "updateNotice",
            data :formData,
            type: "POST",
            dataType: "JSON",
            processData: false,
            contentType: false,
            success: function (result) {
              
              if (result > 0) {
                

                Swal.fire("수정이 완료되었습니다.")
                  .then((result) => {
                    // 확인 버튼을 누르면 페이지 새로고침
                    if (result.isConfirmed) {
                      
                      location.reload();
                    }
                  });

              

                checkModalClose();
              }
            },
            error: function (req, status, error) {
              // 업데이트 실패 또는 오류 발생 시의 처리
              console.log("에러 발생");
              
            }
          });
        });
      });

      checkModal.style.display = 'block';
      checkModalBody.classList.add('check-modal-open');
    },
    error: function (req, status, error) {
      console.log("에러 발생");
      console.log(req.responseText);
    }
  });

  // 삭제 버튼 이벤트
const deleteBtn = document.getElementById('check-remove-btn');

deleteBtn.addEventListener('click', () => {
  Swal.fire({
    title: '정말 삭제 하시겠습니까?',
    html: `<div style="text-align:center">삭제 하시면 되돌릴수 없습니다</div>`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: '네, 삭제할게요!',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url : "deleteNotice",
        data : {"noticeNo" : globalNoticeNo},
        type : "POST",
        dataType : "JSON",
        success : function() {
          checkModalClose();
          Swal.fire(
            '삭제완료!',
            '',
            'success'
          )
          .then((result) => {
            if (result.isConfirmed) {
              location.reload();
            }
          })
        },
        error : function() {
          console.log('에러');
        }
      })
    }
  })
})



}


// 수정 모달창 닫기
// 수정 모달창 닫는 함수
function checkModalClose() {
  checkModalBody.classList.add('check-modal-close');


   checkModalTitle.innerHTML = '';
   checkModalDetail.innerHTML = '';
   checkPreview.innerHTML = '';
   
   doneBtn.style.display = "none";
   modifyBtn.style.display = "inline-block";

   fileBox.style.display = 'none';
 
    
  setTimeout(() => {
    checkModal.style.display = 'none';
    checkModalBody.classList.remove("check-modal-close");
  }, 350);

  noticeContent.style.overflow = 'hidden';

  noticeContent.style.height = 'inherit';

  
}

// 모달창 엑스 버튼
checkCloseBtn.addEventListener("click", () => {
  checkModalClose();
});

// 모달창 외부 영역 이벤트
$(window).click(function(event) {
  if (event.target == checkModal) {
    checkModalClose();
    
  }
});



// 취소버튼 이벤트
checkCancellBtn.addEventListener("click", () => {
  checkModalClose();
});









