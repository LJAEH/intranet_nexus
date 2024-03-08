// 타입 선택별 리스트 변화(kjw)
const attnTypeSelect = document.getElementById("attnTypeSelect");
const listTr = document.getElementsByClassName("listTr");
const listTypeNo = document.getElementsByClassName("listTypeNo");
const cntList = document.getElementsByClassName("content-all-top-text2");

attnTypeSelect.addEventListener("change", function(){

  let cnt = 0;

  for(let i = 0; i < listTr.length; i++){
    listTr[i].style.display = 'table-row';
  }

  if(attnTypeSelect.value == 0){

    for(let i = 0; i < listTr.length; i++){
        listTr[i].style.display = 'table-row';
        cnt++;
    }

  } else{

    for(let i = 0; i < listTr.length; i++){
      if(listTypeNo[i].innerText != attnTypeSelect.value){
        listTr[i].style.display = 'none';
      } else{
        cnt++;
      }
    }

  }

  cntList[0].innerText = "임시 저장(" + cnt + ")";
  
});

function tempWrite(workNo){
    $.ajax({
        url : "detail",
        type : "GET",
        dataType : "JSON",
        data : {"workNo" : workNo},
        success : function(detailSelect){

        // 임시저장된 행 테이블 지우기

        // workNo를 기준으로 저장된 내용 불러와서 작성창에 넣기
        againWirte(detailSelect);

        }
    });
};


function againWirte(obj){


  let startDay = '연도-월-일';
  let endDay = '연도-월-일';
  if(obj.start != null){
    startDay = obj.start.substr(0, 10);
  }
  if(obj.end != null){
    endDay = obj.end.substr(0, 10); 
  }

  // 내용 입력 및 모든 창 숨김
  workTitle.value = obj.title; // 제목 값

  if(obj.content == null) obj.content = ''; // 내용값이 없을 때 ''으로 수정
  sumTxt[0].innerHTML = obj.content; // 내용 값

  workStartDateText.value = startDay; // 시작일 값
  workEndDateText.value = endDay; // 종료일 값

  if(obj.next == null) obj.nextMemName = '-';
  workApprover.value = obj.next; // 결재자 값
  workProjectbox.querySelectorAll('input').forEach(input => input.value = ''); //과제명 값
  showMember.innerText = obj.nextMemName;

  body.style.overflow = "hidden"; // 뒷 배경
  modal.style.display = 'block'; // 모달창
  
  workTemplateSelect.value = 'normal-check'; // 결재타입 값
  normalCheckSelect.style.display='none'; // 일반결재의 세부타입 선택창
  workModaltitle.style.display='none'; // 제목 기입 영역
  workStartDate.style.display = 'none'; // 시작일 영역
  workEndDate.style.display = 'none'; // 종료일 영역
  workDetail.style.display = 'none'; // 내용 영역
  workProjectbox.style.display = 'none'; // 과제명 영역
  modalProjectbox.style.display = 'none'; // 과제추가 버튼
  projectCheckSelect.style.display = 'none'; // 프로젝트 선택창

  assignmentCheckSelect.forEach(select => {
    select.style.display = 'none';
  }); // 과제 선택창

  // 결재(일반, 출장, 연차 typeNo, 1==일반, 2==연차, 3==출장)
  if(obj.typeNo == 1){
      // 해당 영역 표시
      normalCheckSelect.style.display = 'block'; // 일반결재의 세부타입 선택창
      workModaltitle.style.display = 'block';  // 제목 기입 영역
      workDetail.style.display = 'block'; // 내용 영역
      
      // 타입 선택창 값 변경
      workTemplateSelect.value = 'normal-check';
  } else if(obj.typeNo == 2){
      workStartDate.style.display = 'block'; // 시작일 영역
      workEndDate.style.display = 'block'; // 종료일 영역

      workTemplateSelect.value = 'vacation';
  } else if(obj.typeNo == 3){
      workStartDate.style.display = 'block'; // 시작일 영역
      workEndDate.style.display = 'block'; // 종료일 영역
      workDetail.style.display = 'block'; // 내용 영역
  }

  // 임시저장일 경우 아이디값 변경으로 클릭시 새로운 함수 시행되게끔
  successBtn.style.display = "none";
  tempSuccessBtn.style.display = "inline-block";
  tempSuccessBtn.setAttribute("onclick", "tempWriteDelete(" + obj.workNo + ")");

  modalBody.classList.add('modal-open');
}
  
// 삭제버튼 클릭시
function deleteTemp(workNo){
  $.ajax({
    url : "tempDelete",
    dataType : "JSON",
    type : "GET",
    data : {"workNo" : workNo},
    success : function(){
      Swal.fire({
        title: '삭제를 완료했습니다.',
        text: '',
        icon: 'success',
        
        showCancelButton: false, // cancel버튼 보이기. 기본은 원래 없음
        confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
        cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정     
     }).then(result => {
        if(result.isConfirmed){
          window.location.reload();
        }
      }) 
    },
    error : function(){
      console.log("임시저장 삭제하다 에러");
    }
  });
}