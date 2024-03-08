// 직원 검색
const searchDept = document.getElementById('search-dept'),
  searchTeam = document.getElementById('search-team'),
  searchName = document.getElementById('search-name'),
  searchBtn = document.getElementById('search-btn'),
  tbody = document.querySelector('tbody');

searchBtn.addEventListener('click', () => {
  if(searchName.value === '') {
     Swal.fire("이름을 입력 해주세용");
  }

  $.ajax({
    url : "selectMem",
    data : { "memName" : searchName.value },
    type : "GET",
    dataType : "JSON",
    success : function(selectMem) {
      
      tbody.innerHTML = "";
      if (selectMem.length === 0) {
        Swal.fire("검색 결과가 없습니다.");
      } else {
        
        for(let item of selectMem) {

          const tr = document.createElement('tr');
  
          const memNo = document.createElement('td');
          memNo.innerText = item.memNo;
  
          const memId = document.createElement('td');
          memId.innerText = item.memId;
          
          const memName = document.createElement('td');
          memName.innerText = item.memName;
          
          const jobName = document.createElement('td');
          jobName.innerText = item.jobName;
          
          const deptName = document.createElement('td');
          deptName.innerText = item.deptName;
          
          const teamName = document.createElement('td');
          teamName.innerText = item.teamName;
  
          const memSSN = document.createElement('td');
          memSSN.innerText = item.memRNo;
          
          const tel = document.createElement('td');
          tel.innerText = item.memTel;
          
          const address = document.createElement('td');
          address.innerText = item.memAddress;
          
          const email  = document.createElement('td');
          email.innerText = item.memEmail;
          
          const enrollDate = document.createElement('td');
          enrollDate.innerText = item.enrollDate;
          
          const departureDate = document.createElement('td');
          departureDate.innerText = item.departureDate;
  
          tr.append(memNo, memId, memName, jobName, deptName, teamName, memSSN, tel, address, email, enrollDate, departureDate)
  
          document.querySelector('tbody').append(tr);
        }
      }
      
    },
    error : function() {
      console.log("에러");
    }
  })
})


// 전체보기
const memAll = document.getElementById("memAll-btn");
memAll.addEventListener('click', selectAll);


// 직원 전제조회 AJAX
function selectAll() {
  $.ajax({
    url: "selectAll",
    dataType : "JSON",
    success : function(memList) {
      searchName.value = "";
      tbody.innerHTML = "";
      for(let item of memList) {

        const tr = document.createElement('tr');
        

        const memNo = document.createElement('td');
        memNo.innerText = item.memNo;

        const memId = document.createElement('td');
        memId.innerText = item.memId;
        
        const memName = document.createElement('td');
        memName.innerText = item.memName;
        
        const jobName = document.createElement('td');
        jobName.innerText = item.jobName;
        
        const deptName = document.createElement('td');
        deptName.innerText = item.deptName;
        
        const teamName = document.createElement('td');
        teamName.innerText = item.teamName;

        const memSSN = document.createElement('td');
        memSSN.innerText = item.memRNo;
        memSSN.classList.add('data-tooltip1');
        memSSN.setAttribute('data-tooltip1', item.memRNo);
        memSSN.style.maxWidth = '70px'; 
        memSSN.style.overflow = 'hidden';
        memSSN.style.textOverflow = 'ellipsis'; // 말줄임 표시
        memSSN.style.whiteSpace = 'nowrap'; // 텍스트 줄바꿈 방지
        
        const tel = document.createElement('td');
        tel.innerText = item.memTel;
        tel.classList.add('data-tooltip2');
        tel.setAttribute('data-tooltip2', item.memTel);
        tel.style.maxWidth = '70px'; 
        tel.style.overflow = 'hidden';
        tel.style.textOverflow = 'ellipsis'; // 말줄임 표시
        tel.style.whiteSpace = 'nowrap'; // 텍스트 줄바꿈 방지
        
        const address = document.createElement('td');
        address.innerText = item.memAddress;
        address.classList.add('data-tooltip3');
        address.setAttribute('data-tooltip3', item.memAddress);
        address.style.maxWidth = '100px'; 
        address.style.overflow = 'hidden';
        address.style.textOverflow = 'ellipsis'; // 말줄임 표시
        address.style.whiteSpace = 'nowrap'; // 텍스트 줄바꿈 방지
        
        const email  = document.createElement('td');
        email.innerText = item.memEmail;
        email.classList.add('data-tooltip4');
        email.setAttribute('data-tooltip4', item.memEmail);
        email.style.maxWidth = '100px'; 
        email.style.overflow = 'hidden';
        email.style.textOverflow = 'ellipsis'; // 말줄임 표시
        email.style.whiteSpace = 'nowrap'; // 텍스트 줄바꿈 방지
        
        const enrollDate = document.createElement('td');
        enrollDate.innerText = item.enrollDate;
        
        
        const departureDate = document.createElement('td');
        departureDate.innerText = item.departureDate;
        

        tr.append(memNo, memId, memName, jobName, deptName, teamName, memSSN, tel, address, email, enrollDate, departureDate)

      
       


        document.querySelector('tbody').append(tr);
      }
    },
    error : function() {
      console.log("에러");
    }
  })
}

(function() {
  selectAll();
})();

