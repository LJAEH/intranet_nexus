$(document).ready(function() {
  $.ajax({
    url: "데이터를 가져올 URL",
    method: "GET",
    success: function(responseData) {
      // 요청이 성공했을 때 실행될 콜백 함수
      // responseData를 사용하여 데이터를 처리하고 페이지에 출력

      responseData.forEach(function(question) {
        
        var questionTitle = $("<h1>" + question.title + "</h1>"); // 질문 제목 생성
        $("#survey-create-top").append(questionTitle); 

        question.choices.forEach(function(choice) {
         
          var radioId = "radio-" + question.id + "-" + choice.id; // 고유한 ID 생성
          var radio = $('<input type="radio" name="' + question.id + '">'); // input radio 요소 생성
          radio.attr("id", radioId); // radio 요소에 ID 속성 추가
          radioContainer.append(radio); // div에 input radio 추가
          radioContainer.append(choice.value); // div에 선택지 추가
          $("#survey-create-content").append(radioContainer); // 질문을 감싸는 div에 선택지를 추가
        });

      
      });
    },
    error: function(xhr, status, error) {
      // 요청이 실패했을 때 실행될 콜백 함수
      // 실패 처리 로직을 구현
    }
  });
});




const backBtn = document.getElementById("survey-create-bottom-cancle");


backBtn.addEventListener('click', () => {
  window.history.back()
})



