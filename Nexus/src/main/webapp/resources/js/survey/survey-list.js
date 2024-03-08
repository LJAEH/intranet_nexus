// 날짜 => 글자 변경 로직
function getCurrentDate() {
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');
    var currentDateStr = year + '.' + month + '.' + day;
    return currentDateStr;
  }

  
  // 설문 디테일창 리다이렉트
  function surveyDetail(surveyNo,participation,surveyEnd) {
      var currentDate = getCurrentDate();
      var endDate = surveyEnd;
	if(participation == 0) {
        if(endDate >= currentDate){
            var url = "../survey/surveyDetail/" + surveyNo; 
            window.location.href = url; 
        } else {
            var url = "../survey/surveyResult/" + surveyNo; 
            window.location.href = url; 
        }
    } else {
        var url = "../survey/surveyResult/" + surveyNo; 
        window.location.href = url; 
    }
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

function addParticipationLabel() {
    var participationElements = document.querySelectorAll("#participation-color");
    
    participationElements.forEach(function (element){
        var participation = element.textContent.trim();
        if (participation >= 1){
            element.style.color = 'grey';
            element.textContent = '참여';
        } else {
            element.textContent = '미참여';
        }       
    });
}

window.addEventListener('DOMContentLoaded', function () {
    addStatusLabel();
    addParticipationLabel();

    var surveyList = surveyList;
    var surveyListLength = surveyList.length;
    var participationCount = 0;
    
    for (var i = 0; i < surveyListLength; i++) {
      if (surveyList[i].participation >= 1) {
        participationCount++;
      }
    }
   
});

function filterSurveyList(flag){

    // tr과 참여부분 클래스 요소로 만들기
    const trList = document.getElementsByClassName("trList");
    const part = document.getElementsByClassName("part");

    // 전체 미참여 참여 요소화하기
    const total = document.getElementById("total");
    const non = document.getElementById("non");
    const par = document.getElementById("par");

    // 모든 요소의 클래스 지우기 
    // css 먹이기용
    total.classList.remove("clickText");
    non.classList.remove("clickText");
    par.classList.remove("clickText");

    for(let i = 0; i < trList.length; i++){
        trList[i].style.display = 'table-row';
    }

    if(flag == 'all'){
        total.classList.add("clickText");

        for(let i = 0; i < trList.length; i++){
            trList[i].style.display = 'table-row';
        }
    } else if(flag == 'notparticipated'){
        non.classList.add("clickText");

        for(let i = 0; i < trList.length; i++){
            if(part[i].innerText != '미참여'){
                trList[i].style.display = 'none';
            }
        }
    } else{
        par.classList.add("clickText");

        for(let i = 0; i < trList.length; i++){
            if(part[i].innerText != '참여'){
                trList[i].style.display = 'none';
            }
        }
    }
}