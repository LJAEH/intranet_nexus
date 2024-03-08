const calendar = document.querySelector(".calendar"),
      date = document.querySelector(".date"),
      daysContainer = document.querySelector(".days"),
      prev = document.querySelector(".prev"),
      next = document.querySelector(".next"),
      todayBtn = document.querySelector(".today-btn"),
      gotoBtn = document.querySelector(".goto-btn"),
      dateInput = document.querySelector(".date-input"),
      eventDay = document.querySelector(".event-day"),
      eventDate = document.querySelector(".event-date"),
      eventsContainer = document.querySelector(".events"),
      addEventSubmit = document.querySelector(".add-event-btn");

let today = new Date();
let activeDay;
let month = today.getMonth();
let year = today.getFullYear();

const months = [
  "1월",
  "2월",
  "3월",
  "4월",
  "5월",
  "6월",
  "7월",
  "8월",
  "9월",
  "10월",
  "11월",
  "12월",
];

let eventsArr = [];

// getEvents();

// 날짜 함수
function initCalendar() {
  const firstDay = new Date(year, month, 1);
  const lastDay = new Date(year, month + 1, 0);
  const prevLastDay = new Date(year, month, 0);
  const prevDays = prevLastDay.getDate();
  const lastDate = lastDay.getDate();
  const day = firstDay.getDay();
  const nextDays = 7 - lastDay.getDay() - 1;

  date.innerHTML = year + "년 " + months[month] + " ";

  let days = "";

  for (let x = day; x > 0; x--) {
    days += `<div class="day prev-date" > ${prevDays - x + 1}</div>`;
  }

  for(let i = 1; i <= lastDate; i++) {

    let event = false;
    eventsArr.forEach((eventObj) => {
      if(
        eventObj.day === i &&
        eventObj.month === month + 1 &&
        eventObj.year === year
      ) {
        event = true;
      }
    })


    if(
      i === new Date().getDate() && 
      year === new Date().getFullYear() && 
      month === new Date().getMonth()
    ) {


      // --------------------------------------------------------
      // 엑티브 데이에 대한 함수 지정
      // --------------------------------------------------------
      activeDay = i;
      getActiveDay(i);
      updateEvents(i);

      if(event) {
        days += `<div class="day today active event" > ${i}</div>`;
      } else {
        days += `<div class="day today active" > ${i}</div>`;
      }
    } 
    else if (new Date(year, month, i).getDay() === 0) {
      days += `<div class="day sun" > ${i}</div>`; 
    }
     else {
      if(event) {
        days += `<div class="day event" > ${i}</div>`;
      } else {
        days += `<div class="day" > ${i}</div>`;
      }
    }
  }

  for (let j = 1; j <= nextDays; j++) {
    days += `<div class="day next-date" > ${j}</div>`;
  }

 
  daysContainer.innerHTML = days;
  addListner();
}

initCalendar();


// 이전 달
function prevMonth() {
  month--;
  if(month < 0) {
    month = 11;
    year--;
  }
  initCalendar();
}

// 다음 달
function nextMonth() {
  month++;
  if(month > 11) {
    month = 0;
    year++;
  }
  initCalendar();
}


// 회의실 예약 스타일
$(document).ready(function() {
  $(".drop .option").click(function() {
    var val = $(this).attr("data-value"),
        $drop = $(".drop"),
        prevActive = $(".drop .option.active").attr("data-value"),
        options = $(".drop .option").length;
    $drop.find(".option.active").addClass("mini-hack");
    $drop.toggleClass("visible");
    $drop.removeClass("withBG");
    $(this).css("top");
    $drop.toggleClass("opacity");
    $(".mini-hack").removeClass("mini-hack");
    if ($drop.hasClass("visible")) {
      setTimeout(function() {
        $drop.addClass("withBG");
      }, 400 + options*100); 
    }
    triggerAnimation();
    if (val !== "placeholder" || prevActive === "placeholder") {
      $(".drop .option").removeClass("active");
      $(this).addClass("active");
    };
  });
  
  function triggerAnimation() {
    var finalWidth = $(".drop").hasClass("visible") ? 100 : 100;
    $(".drop").css("width", "100%");
    setTimeout(function() {
      $(".drop").css("width", finalWidth + "%");
    }, 400);
  }
});



// ---------------------------------------------------------------------
// ---------- 객체 생성 -------------------------------------------------
// ---------------------------------------------------------------------



// 세션에서 멤버정보 가져오기
var loginMemNo = $("input[id='memNo']").val();


// ---------------------------------------------------------------------
// ---------- 이벤트 목록 -----------------------------------------------
// ---------------------------------------------------------------------


// 달력조작 이벤트
prev.addEventListener("click", prevMonth);
next.addEventListener("click", nextMonth)

// 이벤트 추가
const addEventBtn = document.querySelector(".add-event"),
      addEventContainer = document.querySelector(".add-event-wrapper"),
      addEventCloseBtn = document.querySelector(".close");
      
// 회의실 예약 모달창 생성 클릭이벤트
addEventBtn.addEventListener("click", () => {
  addEventContainer.classList.toggle("active");
});
addEventCloseBtn.addEventListener("click", () => {
  addEventContainer.classList.remove("active");
});
document.addEventListener("click", (e) => {
  if(e.target !== addEventBtn && !addEventContainer.contains(e.target)) {
    addEventContainer.classList.remove("active");
  }
})



// 사이트 진입시 ajax 연결
$(document).ready(function() {});

// 예약 버튼 클릭 이벤트 + ajax
$(".add-event-btn").click(function() {
  // 옵션
  var selectedRoom = $(".drop .option.active").attr("data-value");
  // 라디오값
  var selectedReservationTime = $("input[name='time']:checked").val();
  // 날짜정보
  var trueMonth = month + 1;
  var dayDate = year + "-" + trueMonth + "-" + activeDay;

  // 회의실, 오전/오후 미선택시 알림창
  if(selectedRoom === "placeholder") {
    Swal.fire("회의실을 선택해 주세요");
  } else if ($("input[name='time']:checked").val() == undefined) {
    Swal.fire("오전과 오후중 선택해 주세요");
  } else (
    // 회의실 예약 ajax
    $.ajax({
      url: "reservationRoom",
      type: "GET",
      dataType : "JSON",
      data: { room: selectedRoom, time: selectedReservationTime, reservationDate: dayDate}, 
      success: function(result) {
        
        // 예약 조작
        if(result === 0) {
          Swal.fire("이미 예약된 회의실입니다.");
        } else {
          // 전체 리스트 업데이트
          searchResv();
        }
        // 모달창 + 라디오체크 오프
        addEventContainer.classList.remove("active");
        $("input[name='time']:checked").prop('checked', false);
      },
      error: function(int) {
        console.log(int);
      }
    })
  )
});

// 회의실 예약 취소 클릭이벤트 (memNo,reservationNo)
$(document).on('click','.event', function(){

  // data-(카멜케이스 안먹힘) ==> memNo => memno 로표기
  var memNo = $(this).data('memno');
  var reservationNo = $(this).data('reservationno');
  


  if(loginMemNo == memNo){

    $.ajax({
      url : "deleteResv",
      type : "GET",
      data : { reservationNo : reservationNo },
      success : function(result){

        if(result > 0) {
          Swal.fire("예약 정보가 삭제되었습니다.");
          searchResv();
        } else {
          Swal.fire("다시 시도해 주세요");
        }

      },
      error : function(error) {
        console.log(error);
      }
    })
  } 

})












// ---------------------------------------------------------------------
// ---------- 함수 목록 -------------------------------------------------
// ---------------------------------------------------------------------

// 모든 회의실 예약정보 조회
function searchResv(){
  $.ajax({
    url: 'allReservation', 
    method: 'GET',
    success: function(results) {

      // events DIV 초기화
      $(".events").empty();
      eventsArr = [];
      
      let events = "";

      // 날짜 분류후 객체 전부 저장
      for(let i = 0; i<results.length; i++){
        var result = results[i];

        const reservationDate = result.reservationDate.split('-');

        eventsArr.push({
          day:parseInt(reservationDate[2]),
          month:parseInt(reservationDate[1]),
          year:parseInt(reservationDate[0]),
          roomNo:result.roomNo,
          reservationTime:result.reservationTime,
          reservationNo:result.reservationNo,
          memNo:result.memNo,
          memName:result.memName,
          jobName:result.jobName,
          events: [{title:"회의실 선택"}]
        });
      }

      // 현재 activeDay에 해당하는 데이터만 회의실예약정보창에 div 추가
      eventsArr.forEach((resvInfo) => {
        if(
          resvInfo.day === activeDay &&
          resvInfo.month === month + 1 &&
          resvInfo.year === year
        ) {
            events += `
            <div class="event" data-memno="${resvInfo.memNo}" data-reservationno="${resvInfo.reservationNo}">
              <div class="title">
                <i class="fas fa-circle"></i>
                <h3 class="event-title">회의실 ${resvInfo.roomNo}</h3>
              </div>
              <div class="event-time">
                <span class="event-time">${resvInfo.reservationTime}</span>
              </div>
            </div>`;
          };
        });

      $(".events").append(events);

    },
    error: function(error) {
      console.log('Error:', error);
    }
  });
}


// 우측 상단에 현재 날짜 띄우기
function getActiveDay(date) {
  const dayNames = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
  const day = new Date(year, month, date);
  const dayName = dayNames[day.getDay()];
  eventDay.innerHTML = dayName;
  eventDate.innerHTML = year + "년" + " " + months[month] + " " + date + "일";
}

// 달력에 날짜를 클릭함으로써 acticeDay를 바꿔주는 함수
function addListner() {
  // days 하위의 day 클래스로된 div에 click 이벤트
  const days = document.querySelectorAll(".day");
  days.forEach((day) => {
    day.addEventListener("click", (e) => {
      


      getActiveDay(e.target.innerHTML);
      updateEvents(Number(e.target.innerHTML));
      activeDay = Number(e.target.innerHTML);

      days.forEach((day) => {
        day.classList.remove("active");
      })

      // 이전 달과 다음달을 오갈수 있는 클릭 이벤트
      if(e.target.classList.contains("prev-date")) {
        prevMonth();

        setTimeout(() => {
          const days = document.querySelectorAll(".day");

          days.forEach((day) => {
            if(
              !day.classList.contains("prev-date") && 
              day.innerHTML === e.target.innerHTML
              ) {
                day.classList.add("active");
              }
          });
        }, 100);
      } else if(e.target.classList.contains("next-date")) {
        nextMonth();

        setTimeout(() => {
          const days = document.querySelectorAll(".day");

          days.forEach((day) => {
            if(
              !day.classList.contains("next-date") && 
              day.innerHTML === e.target.innerHTML
              ) {
                day.classList.add("active");
              }
          });
        }, 100);
      }
      else {
        e.target.classList.add("active");
      }

    })
  })
}

// 이벤트 업데이트 함수
function updateEvents() {
  searchResv();
}

