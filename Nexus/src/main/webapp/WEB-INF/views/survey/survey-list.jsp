<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
        <link rel="stylesheet" href="${contextPath}/resources/css/survey/survey-list.css ">
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
        <!-- sweetAlert2 cdn -->
        <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
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
              <c:choose>
                <c:when test="${loginMember.jobNo <= 5}">
                  <li><a href="${contextPath}/survey/surveyManage"><span>설문 생성/관리</span> </a></li>
                </c:when>
              </c:choose>
            </ul>
          </div>

          <!-- 사이드 바 -->
          <div class="side-bar menu">
            <!-- 페이지마다 바뀌는 제목 -->
            <h1>설문</h1>

            <!-- 페이지마다 바뀌는 부제목 -->
            <ul>
              <li><a href="${contextPath}/survey/surveyList"><span>설문 리스트</span> </a></li>
              <c:choose>
                <c:when test="${loginMember.jobNo <= 5}">
                  <li><a href="${contextPath}/survey/surveyManage"><span>설문 생성/관리</span> </a></li>
                </c:when>
              </c:choose>
            </ul>
          </div>

          <!-- 컨텐츠 내용 -->
          <div class="content-all-page">

            <!-- 컨텐츠 내용 윗부분 -->
            <div class="content-all-top-area">

              <div class="content-all-top-area-detail">

                <!-- 컨텐츠 제목 부분 -->
                <div class="content-all-top-texts">
                  <a id="total" class="content-all-top-text clickText"
                    onclick="filterSurveyList('all')">전체(<span>${fn:length(surveyList)}</span>)</a>
                  <c:forEach var="survey" items="${surveyList}">
                    <c:choose>
                      <c:when test="${survey.participation == ''}">
                        <c:set var="nonCnt" value="${nonCnt + 1}" />
                      </c:when>
                      <c:otherwise>
                        <c:set var="parCnt" value="${parCnt + 1}" />
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                  <a id="non" class="content-all-top-text2"
                    onclick="filterSurveyList('notparticipated')">미참여(<span>${nonCnt}</span>)</a>
                  <a id="par" class="content-all-top-text3"
                    onclick="filterSurveyList('participated')">참여(<span>${parCnt}</span>)</a>
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
                      <th>기간</th>
                      <th>참여여부</th>
                    </tr>
                  </thead>

                  <tbody>

                    <c:choose>
                      <c:when test="${empty surveyList}">
                        <tr>
                          <th colspan="4" class="survey-none">설문이 존재하지 않습니다.</th>
                        </tr>
                      </c:when>

                      <c:otherwise>
                        <c:forEach var="survey" items="${surveyList}">
                          <tr onclick="surveyDetail('${survey.surveyNo}', '${survey.participation}', '${survey.end}')"
                            class="trList">
                            <td id="status-color">${survey.end}</td>
                            <td>${survey.surveyTopic}</td>
                            <td>${survey.start} ~ ${survey.end}</td>
                            <td id="participation-color" class="part">${survey.participation}</td>
                          </tr>
                        </c:forEach>
                      </c:otherwise>
                    </c:choose>

                  </tbody>
                </table>
              </div>

              <div class="pagination-area">
                <c:set var="url" value="${boardCode}?cp=" />

                <ul class="pagination">
                  <li><a href="${url}1${sURL}">&lt;&lt;</a></li>

                  <c:if test="${pagination.currentPage > 1}">
                    <li class="prevPage"><a href="${url}${pagination.currentPage - 1}${sURL}" class="blue">이전</a></li>
                  </c:if>
                  <c:if test="${pagination.currentPage == 1}">
                    <li class="prevPage"><a href="javascript:void(0);">이전</a></li>
                  </c:if>

                  <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
                    <c:if test="${i == pagination.currentPage}">
                      <li><a class="current">${i}</a></li>
                    </c:if>
                    <c:if test="${i != pagination.currentPage}">
                      <li><a href="${url}${i}${sURL}">${i}</a></li>
                    </c:if>
                  </c:forEach>

                  <c:if test="${pagination.currentPage < pagination.maxPage}">
                    <li class="nextPage"><a href="${url}${pagination.currentPage + 1}${sURL}" class="blue">다음</a></li>
                  </c:if>
                  <c:if test="${pagination.currentPage == pagination.maxPage}">
                    <li class="nextPage"><a href="javascript:void(0);">다음</a></li>
                  </c:if>

                  <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                </ul>
              </div>

            </div>


          </div>








          <!-- 채팅창 -->
          <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
        </section>



        <script src="${contextPath}/resources/js/survey/survey-list.js"></script>
        <script src="${contextPath}/resources/js/component.js"></script>

      </body>

      </html>