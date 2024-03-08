<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:set var="pagination" value="${map.pagination}" />
    <c:set var="surveyList" value="${map.surveyList}" />

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/survey/survey-manage.css ">
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
      <!-- sweetAlert2 cdn -->
      <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
      <!-- fontawesome cdn -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
      <!-- jquery cdn -->
      <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
      <title>설문</title>
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
            <li>설문생성/관리</li>
            <li><a href="${contextPath}/survey/surveyList"><span>설문 리스트</span> </a></li>
            <li><a href="${contextPath}/survey/surveyManage"><span>설문 생성/관리</span> </a></li>
          </ul>
        </div>

        <!-- 사이드 바 -->
        <div class="side-bar menu">
          <!-- 페이지마다 바뀌는 제목 -->
          <h1>설문생성/관리</h1>

          <!-- 페이지마다 바뀌는 부제목 -->
          <ul>
            <li><a href="${contextPath}/survey/surveyList"><span>설문 리스트</span> </a></li>
            <li><a href="${contextPath}/survey/surveyManage"><span>설문 생성/관리</span> </a></li>
          </ul>
        </div>

        <!-- 컨텐츠 내용 -->
        <div class="content-all-page">

          <!-- 컨텐츠 내용 윗부분 -->
          <div class="content-all-top-area">

            <div class="content-all-top-area-detail">

              <!-- 컨텐츠 제목 부분 -->
              <div class="content-all-top-texts">
                <a href="surveyCreate" class="omen-btn">
                  설문 생성
                </a>
              </div>
            </div>

          </div>

          <!-- 컨텐츠 내용 아랫부분 -->
          <div class="content-all-bottom-area">
            <div class="survey-table">
              <table>

                <thead>
                  <tr>
                    <th>상태</th>
                    <th>제목</th>
                    <th>설문 생성일</th>
                    <th>URL복사</th>
                    <th>응답수</th>
                    <th>설문 결과</th>
                    <th>삭제</th>
                  </tr>
                </thead>

                <tbody>
                  <c:choose>
                    <c:when test="${empty surveyList}">
                      <tr>
                        <th colspan="6" class="survey-none">설문이 존재하지 않습니다.</th>
                      </tr>
                    </c:when>

                    <c:otherwise>
                      <c:forEach var="survey" items="${surveyList}">
                        <tr>
                          <td id="status-color">${survey.end}</td>
                          <td>${survey.surveyTopic}</td>
                          <td>${survey.start}</td>
                          <td id="clip" onclick="return copyURL(${survey.surveyNo})"></td>
                          <td>${survey.respMember}/${survey.totalMember}</td>
                          <td>
                            <a id="survey-result-btn" href="../survey/surveyResult/${survey.surveyNo}">결과 보기</a>
                          </td>
                          <td>
                            <a href="#" onclick="trashBtn(${survey.surveyNo})" class="fa-solid fa-trash"
                              style="color: #c7c7c7;"></a>
                          </td>
                        </tr>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>
                </tbody>
              </table>
            </div>
          </div>


        </div>








        <!-- 채팅창 -->
        <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
      </section>



      <script src="${contextPath}/resources/js/survey/survey-manage.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>


    </body>

    </html>