
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

  cntList[0].innerText = "결재할 문서(" + cnt + ")";
  
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

