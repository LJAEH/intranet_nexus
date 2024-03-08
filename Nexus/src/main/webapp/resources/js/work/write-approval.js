// 모달창 스타일
const body = document.querySelector('body');
const btn = document.getElementById('popupBtn');
const modal = document.getElementById('modalWrap'); // 모달창
const closeBtn = document.getElementById('closeBtn'); // X 버튼 (span)
const approverCloseBtn = document.getElementById('approver-closeBtn'); // X 버튼 (span)
const plusBtn = document.querySelector('.work-modal-projectBox'); // 프로젝트 추가 버튼
const pulsApproverBtn = document.querySelector('.work-modal-approverBox'); // 결재자 추가 버튼
const modalBody = document.querySelector('.work-modalBody'); // 모달창 내부
const approverModal = document.getElementById('approver-modal-wrap') // 결재 모달창
const approverModalBody = document.querySelector('.approver-modal-Body'); // 결재 모달창 내부
const cancellBtn = document.getElementById('cancell-btn'); // 취소 버튼
const approverCancellBtn = document.getElementById('approver-cancell-btn'); // 결재자 취소 버튼
const workModaltitle = document.querySelector('.work-modal-title'); // 모달창 제목
const workStartDate = document.querySelector('.work-modal-startDate'); // 모달창 시작날짜
const workEndDate = document.querySelector('.work-modal-endDate'); // 모달창 종료 날짜
const workTitle = document.querySelector('.work-modal-title > input'); // 모달창 제목 (input)
const workContent = document.querySelector('.work-modal-detail textarea'); // 모달창 내용 (textarea)
const workApprover = document.querySelector('.work-modal-approver > input'); // 모달창 결재자 (input)
const workTemplateSelect = document.getElementById('work-template'); // 모달창 템플릿
const normalCheckSelect = document.getElementById('normal-checked'); // 일반 선택
const projectCheckSelect = document.getElementById('project-checked'); // 프로젝트 선택
const assignmentCheckSelect = document.querySelectorAll('.assignment-checked'); // 과제 선택
const workDetail = document.querySelector('.work-modal-detail'); // 모달창 내용
const workProjectbox = document.querySelector('.projectBox'); // 과제명, 과제 내용
const modalProjectbox = document.querySelector('.work-modal-projectBox'); // 과제추가 버튼
const workStartDateText = document.querySelector('.work-modal-startDate > input'); // 시작 날짜 
const workEndDateText = document.querySelector('.work-modal-endDate > input'); // 종료 날짜 
const workProjectboxText = workProjectbox.querySelectorAll('input'); // 과제명, 과제내용 
const showMember = document.getElementById("showMemName"); // 결재자 이름
const seniorTeam = document.querySelectorAll(".senior-team"); // 임원팀


const sumTxt = document.getElementsByClassName("note-editable");


// 날짜 조건 걸기(kjw)
(function(){
    // 오늘 날짜
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    
    if(month < 10) month = "0" + month;
    if(day < 10) day = "0" + day;
    
    let today = year + "-" + month + "-" + day;

    workStartDateText.setAttribute("min", today);
    workEndDateText.setAttribute("min", today);
})();


// 결제창 버튼 이벤트
btn.addEventListener("click", () => {

  // 그냥 작성일 때와 임시저장에서 작성 할 때의 아이디 변경을 통해
  // 다른 함수가 실행되게 한다. 그래서 임시저장 확인에서는 해당 문서 삭제를 진행하고 새로운 글로 입력되게 한다
  successBtn.style.display = "inline-block";
  tempSuccessBtn.style.display = "none";

  sumTxt[0].setAttribute("spellcheck", "false");
  
  $('.summernote').summernote('code', '');
  if(preview.firstChild) {preview.removeChild(preview.firstChild);}
  workTitle.value = '';
  sumTxt[0].innerHTML = '';
  workApprover.value = '';
  workProjectbox.querySelectorAll('input').forEach(input => input.value = '');
  showMember.innerText = '-';
  normalCheckSelect.value = 'userCustom';

  workTemplateSelect.value = 'normal-check';

  body.style.overflow = "hidden";
  modal.style.display = 'block';
  workModaltitle.style.display = 'block';
  normalCheckSelect.style.display='block';
  workDetail.style.display='block';
  modalProjectbox.style.display ='none';
  workProjectbox.style.display = 'none';
  workStartDate.style.display = 'none'; 
  workEndDate.style.display = 'none';
  projectCheckSelect.style.display = 'none';

  assignmentCheckSelect.forEach(select => {
    select.style.display = 'none';
  });
  modalBody.classList.add('modal-open');

}) 

// 모달창 엑스 버튼
closeBtn.addEventListener("click", () => {
  modalClose();
});

// 모달창 플러스 버튼
plusBtn.addEventListener("click", () => {

  pulsApproverBtn.scrollIntoView();

  const containerDiv = document.createElement('div'); // 전체를 감싸는 div 요소
  containerDiv.style.marginTop = '10px'; 

  const div = document.createElement('div');
  div.style.marginBottom = '10px'; 
  const projectName = document.createElement('span');
  projectName.innerText = "과제명";
  projectName.style.color = 'var(--gray400)'; 
  projectName.style.marginTop = 'auto'; 
  const taskTitle = document.createElement('input');
  taskTitle.name = "taskTitle";
  const buttonDiv = document.createElement('div');
  buttonDiv.style.display = 'flex';
  buttonDiv.style.justifyContent = 'space-between';

  const xbutton = document.createElement('button');
  xbutton.innerText = "x";
  xbutton.style.width = '30px';
  xbutton.style.height = '30px';
  xbutton.style.border = 'none';
  xbutton.style.color = 'var(--gray600)'; 
  xbutton.style.cursor = 'pointer';
  xbutton.style.backgroundColor = "white";

  buttonDiv.append(projectName, xbutton);

  div.append(buttonDiv, taskTitle);
  containerDiv.appendChild(div); 
  workProjectbox.append(containerDiv);


  xbutton.addEventListener("click", () => {
    workProjectbox.removeChild(containerDiv);
  })

  btn.addEventListener("click", () =>{
     workProjectbox.removeChild(containerDiv);
  })

  workTemplateSelect.addEventListener('change', () => {
     workProjectbox.removeChild(containerDiv);
  })

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

  workContent.style.overflow = 'hidden';
  body.style.overflow = "auto";
  workContent.style.height = 'inherit';
}

// work-template 선택란의 값이 변경될 때마다 이벤트 핸들러 실행
workTemplateSelect.addEventListener('change', () => {
  // 선택된 옵션의 값 가져오기
  const selectedValue = workTemplateSelect.value;

  workTitle.value = '';
  sumTxt[0].innerHTML = '';
  workApprover.value = '';
  workDetail.querySelector('textarea').value = '';
  workProjectbox.querySelectorAll('input').forEach(input => input.value = '');
  showMember.innerText = '-';
  normalCheckSelect.value = 'userCustom';
  workStartDateText.value = '';
  workEndDateText.value = '';


  normalCheckSelect.style.display='none';
  workModaltitle.style.display='none';
  workStartDate.style.display = 'none';
  workEndDate.style.display = 'none'; 
  workDetail.style.display = 'none';
  workProjectbox.style.display = 'none';
  modalProjectbox.style.display = 'none';
  projectCheckSelect.style.display = 'none';
  assignmentCheckSelect.forEach(select => {
    select.style.display = 'none';
  });
  
  if (selectedValue === 'normal-check') {
    normalCheckSelect.style.display = 'block';
    workModaltitle.style.display = 'block'; 
    workDetail.style.display = 'block';
    sumTxt[0].innerHTML = '';

  } 

  // kjw
  if(selectedValue === 'business-trip') {
    workStartDate.style.display = 'block'; 
    workEndDate.style.display = 'block';
    workDetail.style.display = 'block';
    sumTxt[0].innerHTML = 
    '<h1 style="text-align: center; ">출장 신청서</h1><p><br></p><h3><br></h3><h3><br></h3><h3>' + 
    '1. 출장 인원</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4>성명</h4></td><td style="text-align: center;">' +
    '<h4>소속</h4></td><td style="text-align: center;"><h4>직급</h4></td><td style="text-align: center;"><h4>사번</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr>' + 
    '<tr><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td>' +
    '<td><br></td><td><br></td><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '2. 출장지&nbsp;</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '3. 출장 내용</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '4. 비고</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p>';
  }

  if(selectedValue === 'vacation') {
    workStartDate.style.display = 'block'; 
    workEndDate.style.display = 'block'; 

  }

  if(selectedValue === 'project') {
    workModaltitle.style.display = 'block'; 
    workDetail.style.display = 'block'
    workProjectbox.style.display = 'block';
    modalProjectbox.style.display = 'block';
    workStartDate.style.display = 'block'; 
    workEndDate.style.display = 'block'; 
  }

  if(selectedValue === 'assignment') {
    projectCheckSelect.style.display = 'block';
    assignmentCheckSelect.forEach(select => {
      select.style.display = 'none';
    });
    workDetail.style.display = 'block'

    document.getElementById('project-checked').addEventListener('change', function() {
      var projectNo = this.value;
      
      // 각 'assignment-checked' select 요소를 숨기고 
      // 선택한 프로젝트 번호에 일치하는 것만 표시
      var assignmentSelects = document.getElementsByClassName('assignment-checked');

      for (var i = 0; i < assignmentSelects.length; i++) {
        var select = assignmentSelects[i];
        select.addEventListener('click', function() {
          var taskNo = this.value;
          document.getElementsByName('taskNo')[0].value = taskNo;
        });
      }

      for (var i = 0; i < assignmentSelects.length; i++) {
          var select = assignmentSelects[i];
          if (select.getAttribute('data-project-no') == projectNo) {
            select.style.display = 'block';
            
          } else {
          select.style.display = 'none';
          }
      }

      
      });

  }
  
});

// 일반 결재의 세부사항 선택창 변경시 내용 변경(kjw)
normalCheckSelect.addEventListener("change", function(){
  sumTxt[0].innerHTML = '';

  if(normalCheckSelect.value == 'normalEx1'){
    sumTxt[0].innerHTML = 
    '<h1 style="text-align: center; ">경조금 지급 신청서</h1><p><br></p><h3><br></h3><h3><br></h3><h3>1. 경조금 수혜자</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; ">' + 
    '<h4>성명</h4></td><td style="text-align: center;"><h4>소속</h4></td><td style="text-align: center;"><h4>직급</h4></td><td style="text-align: center;">' + 
    '<h4>사번</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '2. 지급 사유</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4>경조 대상자 성명</h4></td><td style="text-align: center;">' +
    '<h4>관계</h4></td><td style="text-align: center;"><h4>경조일 (장례식 발인일 입력)</h4></td><td style="text-align: center;">' +
    '<h4>경조 내용</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><p><br></p><p><br></p>' +
    '<h3>3. 지급 금액</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '4. 지급 계좌</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p>'
  } else if(normalCheckSelect.value == 'normalEx2'){
    sumTxt[0].innerHTML = 
    '<h1 style="text-align: center; ">구매 요청서</h1><p><br></p><h3><br></h3><h3><br></h3><h3>' +
    '1. 구매 요청 부서</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4 style="text-align: left; "><br></h4></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '2. 구매 목적 (필요성)</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4 style="text-align: left; "><br></h4></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '3. 희망 납기일</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '4. 구매 요청 품목</h3><table class="table table-bordered"><tbody><tr><td><h4 style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;품목명&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;모델명&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; 수량&nbsp;&nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; 단가&nbsp;&nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; 금액&nbsp;&nbsp;</h4></td><td><h4 style="text-align: center;">사용부서 (사용자)</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><p><br></p><p><br></p><h3>' + 
    '5. 합계금액</h3><table class="table table-bordered"><tbody><tr><td><br></td></tr></tbody></table><br><p><br></p><h3>' +
    '6. 관련 구매 링크</h3><table class="table table-bordered"><tbody><tr><td><h4 style="text-align: center; ">품목명</h4></td><td><h4 style="text-align: center; ">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;링크&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h4></td></tr><tr><td><br></td><td><br></td></tr></tbody></table><h3><br></h3>';
  } else if(normalCheckSelect.value == 'normalEx3'){
    sumTxt[0].innerHTML = 
    '<h1 style="text-align: center; ">자산 요청서</h1><p><br></p><h3><br></h3><h3><br></h3><h3>1. 자산 요청 부서</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4 style="text-align: left; "><br></h4></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '2. 자산 요청 목적 (필요성)</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4 style="text-align: left; "><br></h4></td></tr></tbody></table><p><br></p><p><br></p><h3>' +
    '3. 자산 요청 품목<br></h3><table class="table table-bordered"><tbody><tr><td><h4 style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;품목명&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;모델명&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h4></td><td><h4 style="text-align: center;">&nbsp; 수량&nbsp;&nbsp;</h4></td><td><h4 style="text-align: center;">사용부서 (사용자)</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><p><br></p>';
  } else if(normalCheckSelect.value == 'normalEx4'){
    sumTxt[0].innerHTML = 
    '<h1 style="text-align: center; ">지출 결의서</h1><p><br></p><h3><br></h3><h3><br></h3><h3>' +
    '1. 지출 결의서</h3><table class="table table-bordered"><tbody><tr><td style="text-align: center; "><h4>거래일</h4></td><td style="text-align: center;"><h4>거래처명</h4></td><td style="text-align: center;"><h4>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 내용&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</h4></td><td style="text-align: center;"><h4>금액</h4></td><td style="text-align: center;"><h4>지급요청일</h4></td><td style="text-align: center;"><h4>예금주</h4></td><td style="text-align: center;"><h4>은행</h4></td><td style="text-align: center;"><h4>계좌번호</h4></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><h3><br></h3>';
  }

});

// 결재자 클릭 이벤트
pulsApproverBtn.addEventListener("click",() => {

  closeBtn.scrollIntoView();
  body.style.overflow = "hidden";
  modalBody.style.overflow ="hidden";
  modal.style.overflow = "hidden";
  approverModal.style.overflow = "hidden";
  approverModal.style.display = 'block';
  approverModalBody.classList.add('approver-modal-open');

  for(let i = 0; i <SeniorList.length; i++){
    SeniorList[i].classList.remove('show');
  }

  for(let i = 0; i < deptList.length; i++){
    deptList[i].classList.remove('show');
  }

  for(let i = 0; i < teamList.length; i++) {
    teamList[i].classList.remove('show');
  }

  for(let i = 0; i < seniorTeam.length; i++) {
    seniorTeam[i].classList.remove('show');
  }

  for(let i = 0; i < approvalTeam.length; i++) {
    approvalTeam[i].classList.remove('show');
  }

  $.ajax({
    url : "approvalMember",
    type : "GET",
    dataType : "JSON",
    
    success : function(list) {

      const approvalList = list;
      const team = document.querySelectorAll(".approval-team");
      
      for(let i=0; i < team.length; i++){
        team[i].innerText = "";
      } 
      
      for(let i=0; i < seniorTeam.length; i++){
        seniorTeam[i].innerText = "";
      } 
  
      for (let value of approvalList) {
        let div = document.createElement("div");
        div.className = "divBox"

        let label = document.createElement("label");
        label.setAttribute("for", "mem" + value.memNo);
        label.innerText = value.memName;
        
        let input = document.createElement("input");
        input.setAttribute("type", "radio");
        input.setAttribute("name", "approver-check");
        input.setAttribute("id", "mem" + value.memNo);
        input.classList.add("approver-checkBox");
        input.value = value.memNo;


          if(value.jobNo == 1){
            div.append(input);
            div.append(label);

            seniorTeam[0].append(div);
          } else if(value.jobNo == 2){
            div.append(input);
            div.append(label);

            seniorTeam[1].append(div);
          } else if(value.jobNo == 3){
            div.append(input);
            div.append(label);

            seniorTeam[2].append(div);
          } else if (value.teamNo == 11) {
            div.append(input);
            div.append(label);

            team[0].append(div);
          } else if (value.teamNo == 12) {
            div.append(input);
            div.append(label);

            team[1].append(div);
          } else if (value.teamNo == 21) {
            div.append(input);
            div.append(label);

            team[2].append(div);
          } else if (value.teamNo == 22) {
            div.append(input);
            div.append(label);

            team[3].append(div);
          } else if (value.teamNo == 31) {
            div.append(input);
            div.append(label);

            team[4].append(div);
          } else if (value.teamNo == 32) {
            div.append(input);
            div.append(label);

            team[5].append(div);
          } else if (value.teamNo == 33) {
            div.append(input);
            div.append(label);

            team[6].append(div);
          } else if (value.teamNo == 34) {
            div.append(input);
            div.append(label);

            team[7].append(div);
          } else if (value.teamNo == 41) {
            div.append(input);
            div.append(label);

            team[8].append(div);
          } else if (value.teamNo == 42) {
            div.append(input);
            div.append(label);

            team[9].append(div);
          } else if (value.teamNo == 43) {
            div.append(input);
            div.append(label);

            team[10].append(div);
          } else if (value.teamNo == 51) {
            div.append(input);
            div.append(label);

            team[11].append(div);
          } else if (value.teamNo == 52) {
            div.append(input);
            div.append(label);

            team[12].append(div);
          }

        }
        
      //  // 결재자 클릭 이벤트 핸들러
      const approverSuccessBtn = document.getElementById('approver-success-btn');
      const approverCheckBtn = document.getElementsByClassName('approver-checkBox');

      
      approverSuccessBtn.addEventListener("click", () => {

        for (let i = 0; i < approverCheckBtn.length; i++) {
          let radio = approverCheckBtn[i]; // <input[=radio]> 태그 내부의 라디오 버튼 선택
          if (radio.checked) {  
            workApprover.value = radio.value; // 체크된 라디오 버튼의 값을 가져옵니다.
            const showMember = document.getElementById("showMemName");
            showMember.innerText = radio.nextElementSibling.innerText; 
            aproverModalClose();
          }
        } 
      });

    }

  });

})

// 결재자 부서 클릭 이벤트
const dept = document.querySelector(".dept");
const Executives = document.querySelector(".executives");
const SeniorList = document.querySelectorAll(".senior-list");
const deptList = document.querySelectorAll(".dept-list");
const teamList = document.querySelectorAll(".team-list");


// 임원 조회 이벤트
Executives.querySelector("p").addEventListener("click", ()=> {

  for(let i = 0; i < teamList.length; i++) {
    teamList[i].classList.remove('show');
  }

  for(let i = 0; i < deptList.length; i++){
    deptList[i].classList.remove('show');
  }

  for(let x = 0; x < approvalTeam.length; x++) {
    approvalTeam[x].classList.remove('show');
  }

  for(let x = 0; x < seniorTeam.length; x++) {
    seniorTeam[x].classList.remove('show');
  }

  for(let b of SeniorList){
    b.classList.toggle('show');
  }
})

// 사장, 전무이사, 상무이사 조회
for(let i = 0; i < SeniorList.length; i++){

  SeniorList[i].addEventListener("click", ()=>{

    for(let x = 0; x < seniorTeam.length; x++) {
      seniorTeam[x].classList.remove('show');
    }

    if( i === 0){
      seniorTeam[0].classList.toggle('show');
    } else if( i === 1){
      seniorTeam[1].classList.toggle('show');
    } else if( i === 2){
      seniorTeam[2].classList.toggle('show');
    }  
  })
}



// 부서 조회 이벤트
dept.querySelector("p").addEventListener("click", ()=> {

  for(let i = 0; i < SeniorList.length; i++){
    SeniorList[i].classList.remove('show');
  }

  for(let i = 0; i < teamList.length; i++) {
    teamList[i].classList.remove('show');
  }

  for(let x = 0; x < approvalTeam.length; x++) {
    approvalTeam[x].classList.remove('show');
  }

  for(let x = 0; x < seniorTeam.length; x++) {
    seniorTeam[x].classList.remove('show');
  }

  for(let a of deptList){
    a.classList.toggle('show');
  }
})

// 팀 조회 이벤트
for(let i= 0; i < deptList.length; i++ ){

  deptList[i].addEventListener("click", () => {

    for(let x = 0; x < teamList.length; x++) {
      teamList[x].classList.remove('show');
    }

    if(i === 0){

      teamList[0].classList.toggle('show');
      teamList[1].classList.toggle('show');

    } else if(i === 1){

      teamList[2].classList.toggle('show');
      teamList[3].classList.toggle('show');

    } else if(i === 2){

      teamList[4].classList.toggle('show');
      teamList[5].classList.toggle('show');
      teamList[6].classList.toggle('show');
      teamList[7].classList.toggle('show');

    } else if(i === 3){

      teamList[8].classList.toggle('show');
      teamList[9].classList.toggle('show');
      teamList[10].classList.toggle('show');
    } else if(i === 4){

      teamList[11].classList.toggle('show');
      teamList[12].classList.toggle('show');
    } 

  }
  
  )

}

const approvalTeam = document.querySelectorAll('.approval-team');

// 팀원 조회 이벤트
for (let i = 0; i < teamList.length; i++) {
  teamList[i].addEventListener('click', () => {
    // 해당 deptList 내부의 팀 요소들을 선택합니다.
    
    for(let x = 0; x < approvalTeam.length; x++) {
      approvalTeam[x].classList.remove('show');
    }

    // 선택한 deptList 내부의 팀 요소들을 보여주거나 숨깁니다.
    switch(i){
      case 0 : approvalTeam[0].classList.toggle('show');break;
      case 1 : approvalTeam[1].classList.toggle('show');break;
      case 2 : approvalTeam[2].classList.toggle('show');break; 
      case 3 : approvalTeam[3].classList.toggle('show');break;
      case 4 : approvalTeam[4].classList.toggle('show');break;
      case 5 : approvalTeam[5].classList.toggle('show');break;
      case 6 : approvalTeam[6].classList.toggle('show');break;
      case 7 : approvalTeam[7].classList.toggle('show');break;
      case 8 : approvalTeam[8].classList.toggle('show');break;
      case 9 : approvalTeam[9].classList.toggle('show');break;
      case 10 : approvalTeam[10].classList.toggle('show');break;
      case 11 : approvalTeam[11].classList.toggle('show');break;
      case 12 : approvalTeam[12].classList.toggle('show');break;
      default : return;
    }   

  })

}





// 결재자 모달창 외부 영역 이벤트
window.onclick = function(event) {
  if (event.target == approverModal) {
    aproverModalClose();
  }
}

approverCloseBtn.addEventListener("click", () =>{
  aproverModalClose();
})

// 결재자 모달창 취소버튼 이벤트
approverCancellBtn.addEventListener("click", () => {
  aproverModalClose();
})


// 결재자 모달창 닫는 함수
function aproverModalClose() {
  approverModalBody.classList.add('approver-modal-close');
  
  setTimeout(() => {
    approverModal.style.display = 'none';
    approverModalBody.classList.remove("approver-modal-close");

    // modalBody.style.overflow ="hidden";
    modal.style.overflow = "hidden";
    modalBody.style.overflow ="auto";
    approverModal.style.overflow = "auto";
  }, 350);
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
const preview = document.querySelector('.work-preview');

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
    checkedPreview.innerText = fileInfo;
  };

  reader.readAsDataURL(upFile);

  // 다운로드

}

const fileTypes = [
  "image/apng",
  "image/bmp",
  "image/gif",
  "image/jpeg",
  "image/pjpeg",
  "image/png",
  "image/svg+xml",
  "image/tiff",
  "image/webp",
  "image/x-icon"
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


// form 넘기기
const saveDraft = document.getElementById("save-draft"); // 임시저장 버튼
const successBtn = document.getElementById("success-btn"); // 제출 버튼
const tempSuccessBtn = document.getElementById("tempSuccessBtn"); // 임시 저장 제출 버튼
const writeForm = document.getElementById("writeForm"); // form 요소
const tempSaveFlag = document.getElementById("tempSaveBtn-checkbox");

// 임시저장 클릭
saveDraft.addEventListener("click", function(){
  if(workTemplateSelect.value === 'normal-check' || workTemplateSelect.value === 'project'){
    if(workTitle.value === "") {
      Swal.fire('제목을 입력해 주세요');
    } else{
      tempSaveFlag.value = true;
      writeForm.submit();
    }
  } else{
    tempSaveFlag.value = true;
    writeForm.submit();
  }
})

// 제출 버튼 조건 확인
successBtn.addEventListener("click", function(){
  tempSaveFlag.value = false;

  if(workTemplateSelect.value === 'normal-check'){
  
    if(workTitle.value === "") {
      Swal.fire('제목을 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      writeForm.submit();
    }

  } else if(workTemplateSelect.value === 'business-trip'){

    if(workStartDateText.value === "") {
      Swal.fire('시작날짜를 입력해 주세요');
    } else if(workEndDateText.value === "") {
      Swal.fire('종료날짜를 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('출장내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      writeForm.submit();
    }

  } else if(workTemplateSelect.value === 'vacation'){
    if(workStartDateText.value === "") {
      Swal.fire('시작날짜를 입력해 주세요');
    } else if(workEndDateText.value === "") {
      Swal.fire('종료날짜를 입력해 주세요');
    } else if(workApprover.value == '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      writeForm.submit();
    }

  } else if(workTemplateSelect.value === 'project'){
    if(workTitle.value === "") {
      Swal.fire('제목을 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workProjectboxText.value === "") {
      Swal.fire('프로젝트 내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else { 
      writeForm.submit();
    }
  } else if(workTemplateSelect.value === 'assignment'){
    if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workProjectboxText.value === "") {
      Swal.fire('과제 내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else { 
      writeForm.submit();
    }
  }

  
})

// 임시저장에서 제출 확인버튼 누를 때
function tempWriteDelete(workNo){
  tempSaveFlag.value = false;

  if(workTemplateSelect.value === 'normal-check'){
  
    if(workTitle.value === "") {
      Swal.fire('제목을 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      tempDelete(workNo);
    }

  } else if(workTemplateSelect.value === 'business-trip'){

    if(workStartDateText.value === "") {
      Swal.fire('시작날짜를 입력해 주세요');
    } else if(workEndDateText.value === "") {
      Swal.fire('종료날짜를 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('출장내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      tempDelete(workNo);
    }

  } else if(workTemplateSelect.value === 'vacation'){
    if(workStartDateText.value === "") {
      Swal.fire('시작날짜를 입력해 주세요');
    } else if(workEndDateText.value === "") {
      Swal.fire('종료날짜를 입력해 주세요');
    } else if(workApprover.value == '') {
      Swal.fire('결재자를 입력해 주세요')
    } else{
      tempDelete(workNo);
    }

  } else if(workTemplateSelect.value === 'project'){
    if(workTitle.value === "") {
      Swal.fire('제목을 입력해 주세요');
    } else if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workProjectboxText.value === "") {
      Swal.fire('프로젝트 내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else { 
      tempDelete(workNo);
    }
  } else if(workTemplateSelect.value === 'assignment'){
    if(workContent.value === "") {
      Swal.fire('내용을 입력해 주세요');
    } else if(workProjectboxText.value === "") {
      Swal.fire('과제 내용을 입력해 주세요');
    } else if(workApprover.value === '') {
      Swal.fire('결재자를 입력해 주세요')
    } else { 
      tempDelete(workNo);
    }
  }
}

// 문서 지우기
function tempDelete(workNo){
  $.ajax({
    url : "tempDelete",
    dataType : "JSON",
    type : "GET",
    data : {"workNo" : workNo},
    success : function(){
      writeForm.submit();
    },
    error : function(){
      console.log("임시저장 지우다가 에러");
    }
  });
}

  // --------------------------------------------------------------------------------
//썸머노트 테스트
$(document).ready(function() {
	//여기 아래 부분
	$('.summernote').summernote({
    toolbar: [
      // [groupName, [list of button]]
      ['fontname', ['fontname']],
      ['fontsize', ['fontsize']],
      ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
      ['color', ['forecolor','color']],
      ['table', ['table']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['height', ['height']]
    ],
  fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
  fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],

		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
      popover: {//팝오버 설정

      image: [], //이미지 삭제

      link: [], //링크 삭제

      air: []

   }
	});

  $('#summernote').summernote('insertText', 'TEST');
});
