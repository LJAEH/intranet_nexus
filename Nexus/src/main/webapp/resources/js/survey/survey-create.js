const optionAdd = document.getElementById("survey-option-add"),
    optionAddBox = document.querySelector(".servey-option-add-area"),
    cancleBtn = document.getElementById("survey-create-bottom-cancle"),
    submitBtn = document.getElementById("survey-create-bottom-submit"),
    surveyTitle = document.getElementById("survey-create-top-title"),
    surveyQuestion = document.getElementById("survey-create-content-question"),
    surveyDate = document.querySelector("input[name='end']"),
    surveyOptionList = document.querySelector("input[name='optionList']");
      



// 옵션 추가 이벤트
optionAdd.addEventListener("click", () => {
  
    optionAdd.scrollIntoView();

    const optionLi = document.createElement("li");
    optionLi.classList.add("survey-create-content-option");
    
    const optionCheck = document.createElement("i");
    optionCheck.classList.add("fa-solid","fa-check","survey-check");
    
    const optionInput = document.createElement("input");
    optionInput.placeholder = "옵션을 입력해 주세요"
    optionInput.name = "optionList";
    
    const optionDelete = document.createElement("button");
    optionDelete.id = "survey-option-delete";
    optionDelete.setAttribute("type", "button");

    const optionDeleteI = document.createElement("i");
    optionDeleteI.classList.add("fa-solid","fa-xmark","fa-xl");

    optionDelete.appendChild(optionDeleteI);

    optionLi.appendChild(optionCheck);
    optionLi.appendChild(optionInput);
    optionLi.appendChild(optionDelete);

    const surveyCreateContent = document.querySelector(".survey-create-content");
    surveyCreateContent.insertBefore(optionLi, surveyCreateContent.lastChild);

    surveyCreateContent.appendChild(optionAddBox);

    // 엑스 버튼 클릭 이벤트
    optionDelete.addEventListener("click", () => {
        optionLi.remove();
    });

})


// 취소 버튼 
cancleBtn.addEventListener('click', () => {
    window.history.back();
})


//필수 입력값 지정
function validation() {
    if (surveyTitle.value === "") {
        Swal.fire("제목을 입력해주세요");
        return false;
    }
    
    if (surveyDate.value === "") {
        Swal.fire("종료일을 지정해 주세요");
        return false;
    }
    
    if (new Date(surveyDate.value) < new Date().setHours(0, 0, 0, 0)) {
        Swal.fire("입력한 날짜는 오늘보다 이전입니다.");
        return false;
    }

    if (surveyQuestion.value === "") {
        Swal.fire("질문을 작성해 주세요");
        return false;
    }
    
    if (surveyOptionList.value === "") {
        Swal.fire("옵션을 입력해 주세요");
        return false;
    }

    return true;
}




