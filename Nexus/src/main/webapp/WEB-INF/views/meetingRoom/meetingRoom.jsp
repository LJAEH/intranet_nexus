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
      <link rel="stylesheet" href="${contextPath}/resources/css/meetingRoom/meetingRoom.css ">
      <link rel="stylesheet" href="${contextPath}/resources/css/meetingRoom/style.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
      <!-- sweetAlert2 cdn -->
      <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
      <!-- jquery cdn -->
      <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
      <!-- font-awesome cdn -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />


      <title>회의실</title>
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
            <li>회의실</li>
            <li><a href="${contextPath}/meetingRoom/reservation"><span>예약신청</span> </a></li>
          </ul>
        </div>

        

        <!-- 사이드 바 -->
        <div class="side-bar menu">
          <!-- 페이지마다 바뀌는 제목 -->
          <h1>회의실</h1>

          <!-- 페이지마다 바뀌는 부제목 -->
          <ul>
            <li><a href="${contextPath}/meetingRoom/reservation"><span>예약신청</span> </a></li>
          </ul>
        </div>

        <!-- 컨텐츠 내용 -->
        <div class="content-all-page">


          <!-- 컨텐츠 내용 윗부분 -->
          <div class="content-all-top-area">

            <!-- 컨텐츠 제목 부분 -->
            <p class="content-all-top-text1">회의실 / </p>
            <p class="content-all-top-text2">예약신청</p>
            <!-- 선 -->
            <div class="content-all-top-line"></div>

          </div>

          <!-- 컨텐츠 내용 아랫부분 -->

          <div class="content-all-bottom-area">

            <!-- 부서일정 -->

            <div class="container">
              <div class="left">
                <div class="calendar">
                  <div class="month">
                    <i class="fa fa-angle-left prev"></i>
                    <div class="date"></div>
                    <i class="fa fa-angle-right next"></i>
                  </div>
                  <div class="weekdays">
                    <div style="color : red;">일</div>
                    <div>월</div>
                    <div>화</div>
                    <div>수</div>
                    <div>목</div>
                    <div>금</div>
                    <div>토</div>
                  </div>
                  <div class="days">
                    <!-- 추가되는 날짜 js -->
                  </div>

                </div>
              </div>
              <div class="right">
                <div class="today-date">
                  <div class="event-day"></div>
                  <div class="event-date"></div>
                </div>


                <!-- 해당 날짜의 회의실 등록 확인 -->
                <div class="events reservationBox">
                </div>

                <div class="add-event-wrapper">
                  <div class="add-event-header">
                    <div class="title">회의실 예약</div>
                    <i class="fas fa-times close"></i>
                  </div>
                  <div class="add-event-body">
                    <div class="meetingRoom-box">
                      <div class="drop">
                        <div class="option active placeholder" data-value="placeholder">
                          회의실 선택
                        </div>
                        <div class="option" data-value="1" id="meetingRoom1">
                          회의실 1
                        </div>
                        <div class="option" data-value="2" id="meetingRoom2">
                          회의실 2
                        </div>
                        <div class="option" data-value="3" id="meetingRoom3">
                          회의실 3
                        </div>
                        <div class="option" data-value="4" id="meetingRoom4">
                          회의실 4
                        </div>
                      </div>
                    </div>

                    <div class="day-time-box">
                      <label class="day-times">
                        <input type="radio" name="time" value="morning">
                        <span>오전</span>
                      </label>
                      <label class="day-times">
                        <input type="radio" name="time" value="afternoon">
                        <span>오후</span>
                      </label>
                    </div>

                  </div>
                  <div class="add-event-footer">
                    <button class="add-event-btn">예약</button>
                  </div>
                </div>

              </div>
              <button class="add-event">
                <i class="fas fa-plus"></i>
              </button>
            </div>

          </div>

        </div>








        <!-- 채팅창 -->
        <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
      </section>

      <input type="hidden" id="memNo" value="${loginMember.memNo}">


      <script src="${contextPath}/resources/js/meetingRoom/meetingRoom.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>


    </body>

    </html>