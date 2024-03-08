
(function(){
  var calendarEl = document.getElementById('calendar');

    // 프로젝트 막대 컬러 
    let bgColor = ['#525FE1', '#F86F03', '#FFA41B', '#6554AF', '#3AA6B9', '#9575DE', '#FF9EAA', '#C1ECE4'];
    // JSON으로 받은 데이터 객체화 시키기
    let proList = JSON.parse(pList);

    // 객체에 프로젝트 막대 컬러 추가
    for(let i = 0; i < proList.length; i++){
      let bIndex = i % 8;
      proList[i].backgroundColor = bgColor[bIndex];
    }

    // 휴가자 리스트 프로젝트 객체에 추가
    let vacList = JSON.parse(vList);
    for(let i = 0; i < vacList.length; i++){
      proList.push(vacList[i]);
    }
    
    // 출장 리스트 프로젝트 객체에 추가
    let btList = JSON.parse(bList);
    for(let i = 0; i < btList.length; i++){
      proList.push(btList[i]);
    }

    // 캘린더 API
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      // editable: true, // 수정가능여부
      // selectable: true, // 달력 일자 드래그 설정가능
      // businessHours: true,
      // dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
      views: {
        dayGridMonth: {
          dayMaxEventRows: 4 // 최대 4줄 그 이상은 3줄 +more
        }
      },
      expandRows: true, // 화면에 맞게 높이 재설정
      locale: 'ko', // 한국어 설정
      events: proList
    });

    calendar.render();
})();


