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
        <link rel="stylesheet" href="${contextPath}/resources/css/work/work-send.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/write-approval.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/checked-approval.css">
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
        <!-- sweetAlert2 cdn -->
        <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>

        <!-- jquery cdn -->
        <script src="https://code.jquery.com/jquery-3.7.0.js"
          integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

        <!-- 폰트어썸 -->
        <script src="https://kit.fontawesome.com/3cd0aae50a.js" crossorigin="anonymous"></script>


        <!-- 날짜조회api-->
        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript"
          src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

        <!-- 서머노트를 위해 추가해야할 부분 -->
        <script src="${contextPath}/resources/js/summernote/summernote-lite.js"></script>
        <script src="${contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
        <link rel="stylesheet" href="${contextPath}/resources/css/summernote/summernote-lite.css">


        <!-- 애니메이트.api -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

        <link rel="stylesheet" type="text/css"
          href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


        <title>결재</title>
      </head>

      <body>

        <c:if test="${not empty messageFlag}">
          <c:if test="${messageFlag == '성공'}">
            <script>
              Swal.fire('결재 상신이 완료되었습니다.', '', 'success');
            </script>
          </c:if>
          <c:if test="${messageFlag == '실패'}">
            <script>
              Swal.fire('결재 상신이 실패하였습니다.', '다시 시도해주세요.', 'warning');
            </script>
          </c:if>
          <c:if test="${messageFlag == '임시 저장 성공'}">
            <script>
              Swal.fire('임시 저장이 완료되었습니다.', '', 'success');
            </script>
          </c:if>
          <c:if test="${messageFlag == '임시 저장 실패'}">
            <script>
              Swal.fire('임시 저장이 실패하였습니다.', '다시 시도해주세요.', 'warning');
            </script>
          </c:if>
        </c:if>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <!--------------------------------------------------- 사이드 바 --------------------------------------------------->
        <section>

          <!-- 사이드바 반응형 -->
          <div class="sidebarIconToggle">
            <div class="spinner diagonal part-1"></div>
            <div class="spinner horizontal"></div>
            <div class="spinner diagonal part-2"></div>
          </div>
          <div id="sidebarMenu">
            <ul class="sidebarMenuInner">
              <li>결재</li>
              <li><a href="workSend"><span>상신함</span> </a></li>
              <li><a href="workInbox"><span>수신함</span> </a></li>
              <li><a href="workTemp"><span>임시저장</span> </a></li>
            </ul>
          </div>


          <div class="side-bar menu">
            <h1>결재</h1>

            <ul>
              <li><a href="workSend"><span>상신함</span> </a></li>
              <li><a href="workInbox"><span>수신함</span> </a></li>
              <li><a href="workTemp"><span>임시저장</span> </a></li>
            </ul>
          </div>
          <!-------------------------------------------------- 사이드바 영역 끝 -------------------------------------------------->

          <!---------------------------------------------------- 컨텐츠 내용 ----------------------------------------------------->
          <div class="content-all-page">
            <div class="content-all-top-area">
              <p class="content-all-top-text1">결재 / </p>
              <p class="content-all-top-text2">내가 작성한 결재(${fn:length(list) + fn:length(projectList) + fn:length(taskList)})</p>
              <!-- 날짜 선택 -->
              <div class="content-all-date">

                <div class="content-all-date-p">

                  <p class="date-p">
                    시작일
                  </p>

                  <p class="date-p">
                    종료일
                  </p>

                </div>

                <div class="content-all-date-input">
                  <input type="text" id="dateClick" name="daterange" value="01/01/2023 - 01/15/2023" />
                </div>

              </div>

            </div>

            <div class="content-all-bottom-area">

              <div class="content-all-bottom-area-header">

                <select placeholder="전체" id="attnTypeSelect">
                  <option value="0">전체</option>
                  <option value="1">일반</option>
                  <option value="2">연차</option>
                  <option value="3">출장</option>
                  <option value="4">프로젝트</option>
                  <option value="5">과제</option>
                </select>

                <div class="button-box">
                  <button type="button" class="omen-btn" id="popupBtn">결재 작성하기</button>
                </div>

              </div>

              <div class="content-all-bottom-area-content">

                <table>

                  <thead>
                    <tr>
                      <th>종류</th>
                      <th style="width: 500px;">제목</th>
                      <th>상태</th>
                      <th>첨부파일</th>
                      <th>작성일</th>
                    </tr>
                  </thead>

                  <tbody id="listBody">

                    <c:forEach var="list" items="${list}">
                      <tr class="listTr" onclick="detailModal(${list.workNo})">
                        <td class="listTypeNo" style="display: none;">${list.typeNo}</td>
                        <td>${list.typeName}</td>
                        <td>${list.title}</td>
                        <c:choose>
                          <c:when test="${list.workState == '진행중'}">
                            <td style="color: var(--primary400);">${list.workState}</td>
                          </c:when>
                          <c:when test="${list.workState == '승인'}">
                            <td style="color: var(--green);">${list.workState}</td>
                          </c:when>
                          <c:otherwise>
                            <td style="color: red;">${list.workState}</td>
                          </c:otherwise>
                        </c:choose>
                        <c:if test="${empty list.fileRename}">
                          <td style="color: var(--gray400)">없음</td>
                        </c:if>
                        <c:if test="${not empty list.fileRename}">
                          <td style="color: var(--primary700)">있음</td>
                        </c:if>
                        <td>${fn:substring(list.sendDate, 0, 11)}</td>
                      </tr>
                    </c:forEach>

                    <!-- 프로젝트 리스트 생성 -->
                    <c:forEach var="projectList" items="${projectList}">
                      <tr class="listTr" onclick="projectDetailModal(${projectList.workNo})">
                        <td class="listTypeNo" style="display: none;">${projectList.typeNo}</td>
                        <td>${projectList.typeName}</td>
                        <td>${projectList.title}</td>
                        <c:choose>
                          <c:when test="${projectList.workState == '진행중'}">
                            <td style="color: var(--primary400);">${projectList.workState}</td>
                          </c:when>
                          <c:when test="${projectList.workState == '승인'}">
                            <td style="color: var(--green);">${projectList.workState}</td>
                          </c:when>
                          <c:otherwise>
                            <td style="color: red;">${projectList.workState}</td>
                          </c:otherwise>
                        </c:choose>
                        <c:if test="${empty projectList.fileRename}">
                          <td style="color: var(--gray400)">없음</td>
                        </c:if>
                        <c:if test="${not empty projectList.fileRename}">
                          <td style="color: var(--primary700)">있음</td>
                        </c:if>
                        <td>${fn:substring(projectList.sendDate, 0, 11)}</td>
                      </tr>
                    </c:forEach>

                    <!-- 과제 리스트 생성 -->
                    <c:forEach var="taskList" items="${taskList}">
                      <tr class="listTr" onclick="taskDetailModal(${taskList.workNo})">
                        <td class="listTypeNo" style="display: none;">${taskList.typeNo}</td>
                        <td>${taskList.typeName}</td>
                        <td>${taskList.title}</td>
                        <c:choose>
                          <c:when test="${taskList.workState == '진행중'}">
                            <td style="color: var(--primary400);">${taskList.workState}</td>
                          </c:when>
                          <c:when test="${taskList.workState == '승인'}">
                            <td style="color: var(--green);">${taskList.workState}</td>
                          </c:when>
                          <c:otherwise>
                            <td style="color: red;">${taskList.workState}</td>
                          </c:otherwise>
                        </c:choose>
                        <c:if test="${empty taskList.fileRename}">
                          <td style="color: var(--gray400)">없음</td>
                        </c:if>
                        <c:if test="${not empty taskList.fileRename}">
                          <td style="color: var(--primary700)">있음</td>
                        </c:if>
                        <td>${fn:substring(taskList.sendDate, 0, 11)}</td>
                      </tr>
                    </c:forEach>


                  </tbody>

                </table>

              </div>
            </div>

          </div>

          <!-------------------------------------------------- 컨텐츠 내용 끝 --------------------------------------------------->

          <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />

        </section>



        <jsp:include page="/WEB-INF/views/work/write-approval.jsp" />
        <jsp:include page="/WEB-INF/views/work/checked-approval.jsp" />

      </body>

      <script src="${contextPath}/resources/js/work/work-send.js"></script>
      <script src="${contextPath}/resources/js/work/write-approval.js"></script>
      <script src="${contextPath}/resources/js/work/checked-approval.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>

      </html>