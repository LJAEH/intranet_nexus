<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/attendance/attendance.css">
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
        <!-- sweetAlert2 cdn -->
        <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <!-- jquery cdn -->
        <script src="https://code.jquery.com/jquery-3.7.0.js"
          integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

        <title>근무/휴가</title>
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
              <li>근무/휴가</li>
              <li><a href="./dept-notice.html"><span>근태 현황</span> </a></li>
            </ul>
          </div>

          <!-- 사이드 바 -->
          <div class="side-bar menu">
            <!-- 페이지마다 바뀌는 제목 -->
            <h1>근무/휴가</h1>

            <!-- 페이지마다 바뀌는 부제목 -->
            <ul>
              <li><a href="./dept-notice.html"><span>근태 현황</span> </a></li>
            </ul>
          </div>

          <!-- 컨텐츠 내용 -->
          <div class="content-all-page">

            <!-- 컨텐츠 내용 윗부분 -->
            <div class="content-all-top-area">

              <div class="content-all-top-area-detail">

                <!-- 컨텐츠 제목 부분 -->
                <div>
                  <p class="content-all-top-text2">근태현황</p>
                  <p class="content-all-top-text1">${loginMember.memName}님의 근태현황입니다.</p>
                </div>

              </div>

            </div>

            <!-- 컨텐츠 내용 아랫부분 -->
            <div class="content-all-bottom-area">

              <div class="YMSelect">
                <button type="button" id="leftBtn"><img src="${contextPath}/resources/images/LtAngle.png"></button>
                <p id="attnDate"></p>
                <input id="year" style="display:none">
                <input id="month" style="display:none">
                <button type="button" id="rightBtn"><img src="${contextPath}/resources/images/RtAngle.png"></button>
              </div>

              <div class="attendance-table">
                <table>

                  <thead>
                    <tr>
                      <th>분류</th>
                      <th>날짜</th>
                      <th>출근시간</th>
                      <th>퇴근시간</th>
                      <th>근무시간</th>
                      <th>연장근무시간</th>
                    </tr>
                  </thead>

                  <tbody id="attnList">
                    <c:choose>
                      <c:when test="${empty attendanceList}">
                        <tr id="emptyList">
                          <td colspan="6">조회 내역이 없습니다.</td>
                        </tr>
                      </c:when>
                    </c:choose>
                    <c:forEach var="attnList" items="${attendanceList}">
                      <tr>
                        <td>${attnList.attdTypeName}</td>
                        <td>${fn:substring(attnList.today, 3, 8)}</td>
                        <td>${attnList.attnStart}</td>
                        <td>${attnList.attnEnd}</td>
                        <td>${attnList.workingHours}</td>
                        <td>${attnList.extendedWorkingHours}</td>
                      </tr>
                    </c:forEach>

                  </tbody>
                </table>
              </div>
            </div>


          </div>








          <!-- 채팅창 -->
          <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
        </section>

        <!-- 사이드바 -->



        <script src="${contextPath}/resources/js/attendance/attendance.js"></script>
        <script src="${contextPath}/resources/js/component.js"></script>

      </body>

      </html>