<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/dept/dept-schedule.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
      <!-- sweetAlert2 cdn -->
      <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
      <!-- jquery cdn -->
      <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
      <!-- fullcalendar cdn -->
      <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
      <title>부서 일정</title>
    </head>

    <body>

      <jsp:include page="/WEB-INF/views/common/header.jsp" />

      <section>

        <!-- 사이드바 반응형 -->
        <div class="sidebarIconToggle">
          <div class="spinner diagonal part-1"></div>
          <div class="spinner horizontal"></div>
          <div class="spinner diagonal part-2"></div>
        </div>
        <div id="sidebarMenu">
          <ul class="sidebarMenuInner">
            <li>부서</li>
            <li><a href="${contextPath}/dept/deptNotice"><span>부서 공지사항</span> </a></li>
            <li><a href="${contextPath}/dept/deptBoard"><span>부서 게시판</span> </a></li>
            <li><a href="${contextPath}/dept/deptSchedule"><span>부서 일정</span> </a></li>
          </ul>
        </div>

        <!-- 사이드 바 -->
        <div class="side-bar menu">
          <!-- 페이지마다 바뀌는 제목 -->
          <h1>부서</h1>

          <!-- 페이지마다 바뀌는 부제목 -->
          <ul>
            <li><a href="deptNotice"><span>부서 공지사항</span> </a></li>
            <li><a href="deptBoard"><span>부서 게시판</span> </a></li>
            <li><a href="deptSchedule"><span>부서 일정</span> </a></li>
          </ul>
        </div>

        <!-- 컨텐츠 내용 -->
        <div class="content-all-page">

          <!-- 컨텐츠 내용 윗부분 -->
          <div class="content-all-top-area">

            <div class="content-all-top-area-detail">

              <!-- 컨텐츠 제목 부분 -->
              <div>
                <p class="content-all-top-text1">부서 / </p>
                <p class="content-all-top-text2">부서 일정</p>
              </div>

            </div>

          </div>

          <!-- 컨텐츠 내용 아랫부분 -->
          <div class="content-all-bottom-area">

            <!-- 부서일정 -->
            <!-- calendar 태그 -->
            <div id='calendar-container'>
              <div id='calendar'></div>
            </div>

          </div>

        </div>

        <!-- 채팅창 -->
        <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
      </section>



      <script>
        var pList = '${pList}';
        var vList = '${vList}';
        var bList = '${bList}';
      </script>

      <script src="${contextPath}/resources/js/dept/dept-schedule.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>

    </body>

    </html>