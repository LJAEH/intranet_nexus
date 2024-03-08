

// 상신 결재 목록 만드는 함수 (kjw)
function workSendList(obj){
  const listBody = document.getElementById("listBody");
  listBody.innerText = "";

  for(let item of obj){
    const tr = document.createElement("tr");
    tr.classList.add('listTr');
    tr.setAttribute("onclick", "detailModal(" + item.workNo + ")");
    
    const td1 = document.createElement("td");
    td1.innerText = item.typeNo;
    td1.classList.add('listTypeNo');
    td1.style.display = 'none';

    const td2 = document.createElement("td");
    td2.innerText = item.typeName;


    const td4 = document.createElement("td");
    td4.innerText = item.title;

    const td5 = document.createElement("td");
    td5.innerText += item.workState;
    if(item.workState == '진행중'){
      td5.style.color = "var(--primary400)";
    } else if(item.workState == '승인'){
      td5.style.color = "var(--green)";
    } else{
      td5.style.color = "red";
    }

    const td6 = document.createElement("td");
    if(item.fileRename == null){
      td6.innerText = "없음";
    } else{
      td6.innerText = "있음";
    }

    const td7 = document.createElement("td");
    item.sendDate = item.sendDate.substring(0, 11);
    td7.innerText = item.sendDate;

    tr.append(td1, td2, td4, td5, td6, td7);

    listBody.append(tr);
  }

  const attnTypeSelect = document.getElementById("attnTypeSelect");
  attnTypeSelect.value = 0;



  let cntList = document.getElementsByClassName("content-all-top-text2");
  cntList[0].innerText = "내가 작성한 결재(" + obj.length + ")";

};

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

  cntList[0].innerText = "내가 작성한 결재(" + cnt + ")";
  
});


// 날짜 조회 (kjw)
$(function() {
  $('input[name="daterange"]').daterangepicker({
    "locale": {
        "format": "YYYY-MM-DD",
        "separator": " ~ ",
        "applyLabel": "확인",
        "cancelLabel": "취소",
        "fromLabel": "From",
        "toLabel": "To",
        "customRangeLabel": "Custom",
        "weekLabel": "W",
        "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
        "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],   
    },
    "firstDayOfWeek": 1,
    "startDate":new Date(),
    "endDate": new Date(),
    "drops": "down"
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));

    $.ajax({
      url : "workSendSelectDate",
      type : "GET",
      dataType : "JSON",
      data : {"startDate" : start.format('YYYY-MM-DD'),
              "endDate" : end.format('YYYY-MM-DD')
              },
      success : function(list){
        workSendList(list);
      }
    });
  });
});

function detailModal(workNo){
  $.ajax({
    url : "detail",
    type : "GET",
    dataType : "JSON",
    data : {"workNo" : workNo},
    success : function(detailSelect){

      successDetailModal(detailSelect);

    }
  });
};


// 프로젝트 디테일
function projectDetailModal(workNo){
  $.ajax({
    url : "projectDetail",
    type : "GET",
    dataType : "JSON",
    data : {"workNo" : workNo},
    success : function(detailSelect){

      successDetailModal(detailSelect);

    }
  });
};

// 과제 디테일
function taskDetailModal(workNo){
  $.ajax({
    url : "taskDetail",
    type : "GET",
    dataType : "JSON",
    data : {"workNo" : workNo},
    success : function(detailSelect){

      successDetailModal(detailSelect);

    }
  });
};



// 사이드바 크기조정
var contentAllPage = document.querySelector('.content-all-page');
var sideBar = document.querySelector('.side-bar');

function adjustSideBarHeight() {
  if (contentAllPage.offsetHeight >= 900) {
    sideBar.style.height = 'unset';
  } else {
    sideBar.style.height = '100vh !impotant';
  }
}

window.addEventListener('resize', function() {
  setTimeout(adjustSideBarHeight, 100);
});

adjustSideBarHeight();
