<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- font-awesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />

    <header>

      <div class="content-header-area">

        <div class="content-header-area-left">

          <!-- 로고 -->
          <div>
            <a href="${contextPath}/main">
              <img src="${contextPath}/resources/images/logo-width.png" alt="">
            </a>
          </div>

          <!-- 네비바 -->
          <div>
            <ul class="content-header-ul">
              <li><a href="${contextPath}/main">홈</a> </li>
              <li><a href="${contextPath}/notice/list">공지사항</a> </li>
              <li><a href="${contextPath}/dept/deptNotice">부서</a> </li>
              <li><a href="${contextPath}/work/workSend">결재</a> </li>
              <li><a href="${contextPath}/attendance/list">근무/휴가</a> </li>
              <li><a href="${contextPath}/meetingRoom/reservation">회의실</a> </li>
              <li><a href="${contextPath}/survey/surveyList">설문</a> </li>
              <li class="employee-rank-change"><a href="${contextPath}/dashBoard/dashBoardMain">대시보드</a></li>
            </ul>
          </div>

        </div>

        <!-- 개인 정보 -->

        <div class="btn-container">
          <button class="btn"><span>${loginMember.memName}
              ${loginMember.jobName}</span>
            <ul class="dropdown">
              <li><a href="${contextPath}/member/myPageProfile">마이페이지</a></li>
              <li><a class="header-log-out">Log Out</a></li>
            </ul>
          </button>
        </div>

      </div>


      <div class="Responsive-header">

        <a href="${contextPath}/main">
          <img src="${contextPath}/resources/images/logo-width.png" alt="">
        </a>
        <input type="checkbox" class="menu-btn" id="menu-btn" />
        <label class="menu-icon" for="menu-btn"><span class="navicon"></span></label>
        <ul class="menu">
          <li><a href="${contextPath}/main">홈</a> </li>
          <li><a href="${contextPath}/notice/list">공지사항</a> </li>
          <li><a href="${contextPath}/dept/deptNotice">부서</a> </li>
          <li><a href="${contextPath}/work/workSend">결재</a> </li>
          <li><a href="${contextPath}/attendance/list">근무/휴가</a> </li>
          <li><a href="${contextPath}/meetingRoom/reservation">회의실</a> </li>
          <li><a href="${contextPath}/survey/surveyList">설문</a> </li>
          <li class="Responsive-employee-rank-change"><a href="${contextPath}/dashBoard/dashBoardMain">대시보드</a></li>
          <li><a href="${contextPath}/member/myPageProfile">마이페이지</a> </li>
          <li><a class="log-out">로그아웃</a></li>
        </ul>
      </div>




    </header>

    <script>
      const loginMemberHeader = "<c:out value='${loginMember}' />";
      const contextPath = "<c:out value='${contextPath}' />";
    </script>

    <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1"></script>


    <script>
      // 대시보드
      const jobNoRegexHeader = /jobNo=(\d+)/;
      const jobNoMatchHeader = loginMemberHeader.match(jobNoRegexHeader);
      const loginMemberJobNoHeader = jobNoMatchHeader[1];
      const employeeRankChangeHeader = document.querySelector(".employee-rank-change");
      const responsiveEmployeeRankChangeHeader = document.querySelector(".Responsive-employee-rank-change");
      if (parseInt(loginMemberJobNoHeader) === 6) {
        employeeRankChangeHeader.style.display = 'none';
        responsiveEmployeeRankChangeHeader.style.display = 'none'
      }

    
      

      // 로그아웃
      const logOut = document.querySelector('.header-log-out');
      const logOut2 = document.querySelector('.log-out');
      logOut.addEventListener('click', logoutEvent);
      logOut2.addEventListener('click', logoutEvent);

      function logoutEvent() {
        Swal.fire({
          title: '로그아웃을 하시겠습니까?',
          text: "",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '그래, 할거야!',
          cancelButtonText: '아직..나에게는 할일이 남아있어..'
        }).then((result) => {
          if (result.isConfirmed) {
            confetti({
              particleCount: 150,
              spread: 60
            });
            Swal.fire(
              '축하합니다',
              '퇴근하십시오.',
              'success'
            ).then((result) => {
            
              const url = contextPath + "/member/logout";
              window.location.href = url;

            })
          }
        })
      }

    </script>