
//------------------------------------------------------------------

// 내가 작성한 글모달창
const checkedModal = document.getElementById('checked-modalWrap');
const checkedCloseBtn = document.getElementById('checked-closeBtn');
const checkedModalBody = document.querySelector('.checked-modalBody');
const checkedCancellBtn = document.getElementById('checked-cancell-btn'); // 결재취소
const checkedModalTitle = document.querySelector('.checked-modal-title');
const checkedModalDetail = document.querySelector('.checked-modal-detail'); // 내용
const checkedPreview = document.querySelector('.checked-preview');
const checkedCopyBtn = document.querySelector('.copy-btn');
const checkedSuccessBtn = document.getElementById('checked-success-btn'); //확인
const checkedModalApprover = document.querySelector('.checked-modal-approver'); // 결재자
const checkedStartDate = document.getElementsByClassName("checked-modal-startDate"); // 시작일
const checkedEndDate = document.getElementsByClassName("checked-modal-endDate"); // 종료일
const checkedModalTaskList = document.querySelector('.checked-modal-taskList'); 
const checkedModalTaskDiv = document.querySelector('.modal-task-div');
const checkedModalTableBody = document.querySelector('.modal-table-body');
const txt = document.getElementById("content");
const sumTxt1 = document.getElementsByClassName("note-editable");
const checkSeniorTeam = document.querySelectorAll(".check-senior-team");




// ajax 성공 후 결재 디테일 모달창 내용 입력(kjw)
function successDetailModal(obj){


  const workNo = document.getElementById("workNo");
  const memName = document.getElementById("memName");
  const sendDate = document.getElementById("sendDate");
  const content = document.getElementById("content");
  const opinion = document.getElementById("opinion");
  const next1 = document.getElementById("next1");
  const next2 = document.getElementById("next2");
  const checkStart = document.querySelector('.checked-modal-startDate > input')
  const checkEnd = document.querySelector('.checked-modal-endDate > input')

  const uploadFile = document.getElementById("upload_file");


  checkedModalDetail.style.display = 'none';
  checkedStartDate[0].style.display = 'none';
  checkedEndDate[0].style.display = 'none';
  checkedModalTaskList.style.display = 'none';

  checkedModalTitle.innerHTML = "";
  workNo.innerText = "";
  memName.innerText = "";
  sendDate.innerText = "";
  opinion.value = "";
  next2.value = "";
  content.value = "";
  checkStart.value = "";
  checkEnd.value = "";
  app_list.innerText = "";
  uploadFile.innerText = "";

  if(obj.opinion == null){
    obj.opinion = "";
  }
  
  // 결재자 경로 
  let app_list_mem = obj.approvalList.split(",,");
  let cnt = 0;

  for(let i = 0; i < app_list_mem.length - 1; i++){
    let memArr = app_list_mem[i].split(",");

    let tr = document.createElement("tr");

    let td1 = document.createElement("td");
    td1.innerText = ++cnt;
    tr.append(td1);

    let td2 = document.createElement("td");
    td2.innerText = memArr[0];
    tr.append(td2);  

    let td3 = document.createElement("td");
    td3.innerText = memArr[1];
    if(td3.innerText == '진행중') td3.style.color = "var(--primary400)";
    else if(td3.innerText == '승인') td3.style.color = "var(--green)";
    else td3.style.color = "red";
    tr.append(td3);   

    let td4 = document.createElement("td");
    td4.innerText = memArr[2];
    tr.append(td4);  

    app_list.append(tr);
  }

  console.log(obj);
  // 첨부파일
  if(obj.fileOrigin != null){
    const fileNameA = document.createElement("a");
    fileNameA.innerText = obj.fileOrigin;
    fileNameA.href =`${contextPath}` +  obj.fileRename;
    fileNameA.download = obj.fileOrigin;
    uploadFile.append(fileNameA);
  }


  // 결재 타입에 따른 화면구성
  checkedModalTitle.innerHTML = "<h1>" + obj.title;
  workNo.innerText = obj.workNo;
  memName.innerText = obj.memName + " (" + obj.email +")";
  sendDate.innerText = obj.sendDate;

  sumTxt[1].setAttribute("contenteditable","false");
  
  if(obj.typeNo == 1){
    sumTxt[1].innerHTML = obj.content;

    opinion.value = obj.opinion;
    next2.value = obj.nextMemName + " (" + obj.nextMemEmail + ")";

    checkedModalDetail.style.display = 'block';

  } else if(obj.typeNo == 2){
    sumTxt[1].innerHTML = obj.content;
    checkStart.value = obj.start.substr(0,10);
    checkEnd.value = obj.end.substr(0,10);
    opinion.value = obj.opinion;
    next2.value = obj.nextMemName + " (" + obj.nextMemEmail + ")";

    checkedStartDate[0].style.display = 'block';
    checkedEndDate[0].style.display = 'block';

  } else if(obj.typeNo == 3){
    sumTxt[1].innerHTML = obj.content;
    checkStart.value = obj.start.substr(0,10);
    checkEnd.value = obj.end.substr(0,10);
    opinion.value = obj.opinion;
    next2.value = obj.nextMemName + " (" + obj.nextMemEmail + ")";

    checkedModalDetail.style.display = 'block';
    checkedStartDate[0].style.display = 'block';
    checkedEndDate[0].style.display = 'block';

  } else if(obj.typeNo == 4){
    sumTxt[1].innerHTML = obj.content;
    checkStart.value = obj.start.substr(0,10);
    checkEnd.value = obj.end.substr(0,10);
    opinion.value = obj.opinion;
    next2.value = obj.nextMemName + " (" + obj.nextMemEmail + ")";

    checkedModalDetail.style.display = 'block';
    checkedModalTaskList.style.display = 'block';
    checkedStartDate[0].style.display = 'block';
    checkedEndDate[0].style.display = 'block';
    checkedModalTaskDiv.innerHTML ="";

    for (var i = 0; i < obj.taskList.length; i++) {
      var task = obj.taskList[i];
      
      var div = document.createElement('div');
      var span1 = document.createElement('span');
      var span2 = document.createElement('span');
      span1.textContent = i+1 + ".";
      span2.textContent = task.taskTitle;
      
      div.appendChild(span1);
      div.appendChild(span2);
      
      checkedModalTaskDiv.appendChild(div);
    }

 
  } else if(obj.typeNo == 5){
    sumTxt[1].innerHTML = obj.content;
    opinion.value = obj.opinion;
    next2.value = obj.nextMemName + " (" + obj.nextMemEmail + ")";

    checkedModalDetail.style.display = 'block';


  }


  
  // 디테일 모달창 열기
  checkedModalOpen();

  const tempObj = obj;

  const sendBtn = document.getElementsByClassName("work-send-submit-btn");
  const inboxBtn = document.getElementsByClassName("work-inbox-submit-btn");

  // 상신 페이지 : 취소버튼 이벤트(kjw)
  if(sendBtn.length == 1){
    checkedCancellBtn.addEventListener("click", function(tempObj) {
      Swal.fire({
        title: '결재를 취소하시겠습니까?',
        text: '',
        icon: 'warning',
        
        showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
        confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
        cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정     
     }).then(result => {
        if(result.isConfirmed){
          workDelete(obj);
        }
     })
  
    });

  }
//------------------------------------------------------------------------------------------------------
  // 결재자 추가 버튼
  const checkPulsApproverBtn = document.querySelector(".check-modal-approverBox");
  const checkApproverModal = document.getElementById("check-approver-modal-wrap");
  const checkApproverModalBody = document.querySelector(".check-approver-modal-Body");

  // 체크 결재자 클릭 이벤트
  checkPulsApproverBtn.addEventListener("click",() => {

  closeBtn.scrollIntoView();
  body.style.overflow = "hidden";
  checkedModal.style.overflow ="hidden";
  checkApproverModal.style.display = 'block';
  checkApproverModalBody.classList.add('check-approver-modal-open');

  for(let i = 0; i < checkSeniorList.length; i++){
    checkSeniorList[i].classList.remove('show');
  }

  for(let i = 0; i < checkDeptList.length; i++){
    checkDeptList[i].classList.remove('show');
  }

  for(let i = 0; i < checkTeamList.length; i++) {
    checkTeamList[i].classList.remove('show');
  
  }
  for(let i = 0; i < checkSeniorTeam.length; i++) {
    checkSeniorTeam[i].classList.remove('show');
  }

  for(let i = 0; i < checkApprovalTeam.length; i++) {
    checkApprovalTeam[i].classList.remove('show');
  }

  $.ajax({
    url : "approvalMember",
    type : "GET",
    dataType : "JSON",
    // 동준작성
    success : function(list) {

      const approvalList = list;
      const team = document.querySelectorAll(".check-approval-team");
      
      for(let i=0; i < team.length; i++){
        team[i].innerText = "";
      } 

      for(let i=0; i < checkSeniorTeam.length; i++){
        checkSeniorTeam[i].innerText = "";
      } 
      
  
      for (let value of approvalList) {
        let div = document.createElement("div");
        div.className = "divBox"

        let label = document.createElement("label");
        label.setAttribute("for", "mem" + value.memNo);
        label.innerText = value.memName;
        
        let input = document.createElement("input");
        input.setAttribute("type", "radio");
        input.setAttribute("name", "check-approver-check");
        input.setAttribute("id", "mem" + value.memNo);
        input.classList.add("check-approver-checkBox");
        input.value = value.memNo;

        if(value.jobNo == 1){
          div.append(input);
          div.append(label);

          checkSeniorTeam[0].append(div);
        } else if(value.jobNo == 2){
          div.append(input);
          div.append(label);

          checkSeniorTeam[1].append(div);
        } else if(value.jobNo == 3){
          div.append(input);
          div.append(label);

          checkSeniorTeam[2].append(div);
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
      const CheckApproverSuccessBtn = document.getElementById('check-approver-success-btn');
      const CheckApproverCheckBtn = document.getElementsByClassName('check-approver-checkBox');
      const checkWorkApprover = document.querySelector('.checked-modal-approver > input'); // 체크 모달창 결재자 (input)

      
      CheckApproverSuccessBtn.addEventListener("click", () => {

        for (let i = 0; i < CheckApproverCheckBtn.length; i++) {
          let radio = CheckApproverCheckBtn[i]; // <input[=radio]> 태그 내부의 라디오 버튼 선택
          if (radio.checked) {  
            checkWorkApprover.value = radio.value; // 체크된 라디오 버튼의 값을 가져옵니다.
            const showMember2 = document.getElementById("showMemName2");
            showMember2.innerText = radio.nextElementSibling.innerText; 
            checkAproverModalClose();
          }
        } 
      });

    }

  });

})

// 결재자 부서 클릭 이벤트
const checkDept = document.querySelector(".check-dept");
const checkExecutives = document.querySelector(".check-executives");
const checkSeniorList = document.querySelectorAll(".check-senior-list");
const checkDeptList = document.querySelectorAll(".check-dept-list");
const checkTeamList = document.querySelectorAll(".check-team-list");

checkExecutives.querySelector("p").addEventListener("click", ()=> {

  for(let i = 0; i < checkTeamList.length; i++) {
    checkTeamList[i].classList.remove('show');
  }

  for(let i = 0; i < checkDeptList.length; i++){
    checkDeptList[i].classList.remove('show');
  }

  for(let x = 0; x < checkApprovalTeam.length; x++) {
    checkApprovalTeam[x].classList.remove('show');
  }

  for(let b of checkSeniorList){
    b.classList.toggle('show');
  }
})

checkDept.querySelector("p").addEventListener("click", ()=> {

  for(let i = 0; i < checkSeniorList.length; i++){
    checkSeniorList[i].classList.remove('show');
  }

  for(let x = 0; x < checkTeamList.length; x++) {
    checkTeamList[x].classList.remove('show');
  }

  for(let x = 0; x < checkApprovalTeam.length; x++) {
    checkApprovalTeam[x].classList.remove('show');
  }

  for(let a of checkDeptList){
    a.classList.toggle('show');
  }
})

// 사장, 전무이사, 상무이사 조회
for(let i = 0; i < checkSeniorList.length; i++){

  checkSeniorList[i].addEventListener("click", ()=>{

    for(let x = 0; x < checkSeniorTeam.length; x++) {
      checkSeniorTeam[x].classList.remove('show');
    }

    if( i === 0){
      checkSeniorTeam[0].classList.toggle('show');
    } else if( i === 1){
      checkSeniorTeam[1].classList.toggle('show');
    } else if( i === 2){
      checkSeniorTeam[2].classList.toggle('show');
    }  
  })
}


// 팀 조회 이벤트
for(let i= 0; i < checkDeptList.length; i++ ){

  checkDeptList[i].addEventListener("click", () => {

    for(let x = 0; x < checkTeamList.length; x++) {
      checkTeamList[x].classList.remove('show');
    }

    if(i === 0){

      checkTeamList[0].classList.toggle('show');
      checkTeamList[1].classList.toggle('show');

    } else if(i === 1){

      checkTeamList[2].classList.toggle('show');
      checkTeamList[3].classList.toggle('show');

    } else if(i === 2){

      checkTeamList[4].classList.toggle('show');
      checkTeamList[5].classList.toggle('show');
      checkTeamList[6].classList.toggle('show');
      checkTeamList[7].classList.toggle('show');

    } else if(i === 3){

      checkTeamList[8].classList.toggle('show');
      checkTeamList[9].classList.toggle('show');
      checkTeamList[10].classList.toggle('show');
    } else if(i === 4){

      checkTeamList[11].classList.toggle('show');
      checkTeamList[12].classList.toggle('show');
    } 

  }
  
  )

}

const checkApprovalTeam = document.querySelectorAll('.check-approval-team');

// 팀원 조회 이벤트
for (let i = 0; i < checkTeamList.length; i++) {
  checkTeamList[i].addEventListener('click', () => {
    // 해당 deptList 내부의 팀 요소들을 선택합니다.
    
    for(let x = 0; x < checkApprovalTeam.length; x++) {
      checkApprovalTeam[x].classList.remove('show');
    }

    // 선택한 deptList 내부의 팀 요소들을 보여주거나 숨깁니다.
    switch(i){
      case 0 : checkApprovalTeam[0].classList.toggle('show');break;
      case 1 : checkApprovalTeam[1].classList.toggle('show');break;
      case 2 : checkApprovalTeam[2].classList.toggle('show');break; 
      case 3 : checkApprovalTeam[3].classList.toggle('show');break;
      case 4 : checkApprovalTeam[4].classList.toggle('show');break;
      case 5 : checkApprovalTeam[5].classList.toggle('show');break;
      case 6 : checkApprovalTeam[6].classList.toggle('show');break;
      case 7 : checkApprovalTeam[7].classList.toggle('show');break;
      case 8 : checkApprovalTeam[8].classList.toggle('show');break;
      case 9 : checkApprovalTeam[9].classList.toggle('show');break;
      case 10 : checkApprovalTeam[10].classList.toggle('show');break;
      case 11 : checkApprovalTeam[11].classList.toggle('show');break;
      case 12 : checkApprovalTeam[12].classList.toggle('show');break;
      default : return;
    }   

  })

}

const checkApproverCloseBtn = document.getElementById("check-approver-closeBtn");
const checkApproverCancellBtn = document.getElementById("check-approver-cancell-btn");


// 결재자 모달창 외부 영역 이벤트
window.onclick = function(event) {
  if (event.target == approverModal) {
    checkAproverModalClose();
  }
}

checkApproverCloseBtn.addEventListener("click", () =>{
  checkAproverModalClose();
})

// 결재자 모달창 취소버튼 이벤트
checkApproverCancellBtn.addEventListener("click", () => {
  checkAproverModalClose();
})


// 결재자 모달창 닫는 함수
function checkAproverModalClose() {
  checkApproverModalBody.classList.add('check-approver-modal-close');
  
  setTimeout(() => {
    checkApproverModal.style.display = 'none';
    checkApproverModalBody.classList.remove("check-approver-modal-close");

 
    modal.style.overflow = "hidden";
    modalBody.style.overflow ="auto";
    approverModal.style.overflow = "auto";
  }, 350);
}

  // 반려 또는 승인 버튼 클릭(kjw)
  // 작업 필요 : 반려의 경우 - 버튼 클릭시 의견창 띄우고 입력된 의견 가져오기
  //            둘다 - 결재자, 문서번호, 받아오기
  const rejectBtn = document.getElementById("checked-reject-btn");
  const approveBtn = document.getElementById("checked-approve-btn");
  const checkbox = document.getElementById('checked-modal-checkbox');

  // 수신함 : 버튼 활성화
  if(inboxBtn.length == 1){

    // 반려
    rejectBtn.addEventListener("click", function(){
      let btnName = "reject"
      obj.opinion = opinion.value;
      obj.checkbox_flag = true;
      obj.next = next1.value;
      app_btn_click(btnName, obj);
    })
    // 승인
    approveBtn.addEventListener("click", function(){
      let btnName = "approve"
      checkbox_flag = checkbox.checked;
      obj.checkbox_flag = checkbox_flag;
      obj.next = next1.value;

      if(checkbox_flag == false && next1.value == ''){
        Swal.fire(
          '결재자 혹은 최종승인을 체크해 주세요.',
          '',
          'warning'
        );
      } else if(checkbox_flag == true && next1.value != ''){
        Swal.fire(
          '결재자 혹은 최종승인 중 \n 하나만 체크해 주세요.',
          '',
          'warning'
        );
      } else{
        app_btn_click(btnName, obj);
      }

    })
  }
}
////////////////

// 내가 작성한 글 모달창 오픈
function checkedModalOpen() {
  
  // 모달창 열기
  checkedModal.style.display = 'block';
  body.style.overflow = "hidden";
  checkedModal.style.overflow ="hidden";
  checkedModalBody.classList.add('.checked-modal-open');

}

// 내가 작성한 글 모달창 닫기
// 내가 작성한 글 모달창 닫는 함수
function checkedModalClose() {

  checkedModalBody.classList.add('.checked-modal-close');
  
  setTimeout(() => {
    checkedModal.style.display = 'none';
    checkedModalBody.classList.remove(".checked-modal-close");
  }, 350);

  body.style.overflow = "auto";
}

// 모달창 엑스 버튼
checkedCloseBtn.addEventListener("click", () => {
  checkedModalClose();
});

// 모달창 외부 영역 이벤트
window.onclick = function(event) {
  if (event.target == checkedModal) {
    checkedModalClose();
  }
}


// 취소버튼 작동(kjw)
function workDelete(obj){
  

  let cancleFlag = obj.approvalList.split(",,");
  let cancleFlag2 = cancleFlag[cancleFlag.length-2].split(",");

  if(cancleFlag.length > 2 || cancleFlag2[1] != '진행중'){

    Swal.fire(
      '결재가 이미 진행중입니다.',
      '취소가 불가능 합니다.',
      'warning'
    );

  } else{

    $.ajax({
      url : "workCancle",
      type : "GET",
      dataType : "JSON",
      data : {
        "workNo" : obj.workNo,
        "typeNo" : obj.typeNo
      },
      success : function(result){
        if(result != 0){
          Swal.fire({
            title: '결재취소를 완료했습니다.',
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
        } else{
          Swal.fire(
            '결재취소를 실패했습니다',
            '다시 시도해주세요.',
            'warning'
          );

          checkedModalClose();

        }
      },
      error : function(){
        console.log("결재취소 서버 접속 실패");
      }

    });

  }
}


// 반려, 승인 버튼 클릭 작동 (kjw)
function app_btn_click(btnName, obj, checkbox_flag){

  // 반려
  if(btnName == 'reject'){
    Swal.fire({
      title: '결재를 반려하시겠습니까?',
      text: '',
      icon: 'warning',
      
      showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
      confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
      cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
      confirmButtonText: '확인', // confirm 버튼 텍스트 지정
      cancelButtonText: '취소', // cancel 버튼 텍스트 지정     
   }).then(result => {
    if(result.isConfirmed){
      $.ajax({
        url : "clickApproval",
        type : "GET",
        dataType : "JSON",
        data : {
          "workNo" : obj.workNo,
          "approvalList" : obj.approvalList,
          "opinion" : obj.opinion,
          "btnName" : btnName,
          "checkbox_flag" : obj.checkbox_flag,
          "typeNo" : obj.typeNo
        },
        success : function(){
          Swal.fire({
            title: '결재를 완료했습니다.',
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
          console.log("반려 하다가 에러 발생");
        }
      });
    }
   })
  } else{ // 승인
    Swal.fire({
      title: '결재를 승인하시겠습니까?',
      text: '',
      icon: 'warning',
      
      showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
      confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
      cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
      confirmButtonText: '확인', // confirm 버튼 텍스트 지정
      cancelButtonText: '취소', // cancel 버튼 텍스트 지정     
   }).then(result => {
    if(result.isConfirmed){
      $.ajax({
        url : "clickApproval",
        type : "GET",
        dataType : "JSON",
        data : {
          "workNo" : obj.workNo,
          "approvalList" : obj.approvalList,
          "btnName" : btnName,
          "checkbox_flag" : obj.checkbox_flag,
          "next" : obj.next,
          "typeNo" : obj.typeNo,
          "start" : obj.start,
          "end" : obj.end,
          "memNo" : obj.memNo
        },
        success : function(){
          Swal.fire({
            title: '결재를 완료했습니다.',
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
          console.log("승인 하다가 에러 발생");
        }
      });
    }
   })
  }
}

//썸머노트 테스트
$(document).ready(function() {
	//여기 아래 부분
	$('.summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
});

const printBtn = document.getElementById("print-btn");

function printPage(){
  window.print();
}