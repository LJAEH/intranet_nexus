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
        <link rel="stylesheet" href="${contextPath}/resources/css/work/work-inbox(1).css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/checked-approval.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/write-approval.css">
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
        <!-- sweetAlert2 cdn -->
        <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <!-- 폰트어썸 -->
        <script src="https://kit.fontawesome.com/3cd0aae50a.js" crossorigin="anonymous"></script>
        <!-- jquery cdn -->
        <script src="https://code.jquery.com/jquery-3.7.0.js"
          integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

        <!-- 서머노트를 위해 추가해야할 부분 -->
        <script src="${contextPath}/resources/js/summernote/summernote-lite.js"></script>
        <script src="${contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
        <link rel="stylesheet" href="${contextPath}/resources/css/summernote/summernote-lite.css">

        <!-- 애니메이트.api -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
        <title>결재-수신함-결재할것</title>
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
              <li>결재</li>
              <li><a href="workSend"><span>상신함</span> </a></li>
              <li><a href="workInbox"><span>수신함</span> </a></li>
              <li><a href="workTemp"><span>임시저장</span> </a></li>
            </ul>
          </div>

          <!------------------------------------- 사이드 바 --------------------------------------------------->
          <div class="side-bar menu">
            <h1>결재</h1>

            <ul>
              <li><a href="workSend"><span>상신함</span> </a></li>
              <li><a href="workInbox"><span>수신함</span> </a></li>
              <li><a href="workTemp"><span>임시저장</span> </a></li>
            </ul>
          </div>
          <!------------------------------------- 사이드 바 끝 ------------------------------------------------->

          <!------------------------------------- 컨텐츠 내용 -------------------------------------------------->
          <div class="content-all-page">

            <div class="content-all-top-area">
              <p class="content-all-top-text1">결재 / </p>
              <p class="content-all-top-text2">결재할 문서(${fn:length(list) + fn:length(projectList) + fn:length(taskList)})</p>
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
                      <th>작성자</th>
                      <th>첨부파일</th>
                      <th>작성일</th>
                    </tr>

                  </thead>

                  <tbody>
                    <c:forEach var="list" items="${list}">
                      <tr class="listTr" onclick="detailModal(${list.workNo})">
                        <td class="listTypeNo" style="display: none;">${list.typeNo}</td>
                        <td>${list.typeName}</td>
                        <td>${list.title}</td>
                        <td>${list.memName}</td>
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
                        <td>${projectList.memName}</td>
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
                        <td>${taskList.memName}</td>
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
          <!-------------------------------- 컨텐츠 내용 영역 끝 --------------------------------------------------------------------->

          <jsp:include page="/WEB-INF/views/work/write-approval.jsp" />

          <!-- 페이지 정의 -->
          <c:set var="flag" value="1" scope="request" />
          <jsp:include page="/WEB-INF/views/work/checked-approval.jsp" />

          <!-- 채팅창 -->
          <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />

        </section>


      </body>

      <script src="${contextPath}/resources/js/work/work-inbox(1).js"></script>
      <script src="${contextPath}/resources/js/work/write-approval.js"></script>
      <script src="${contextPath}/resources/js/work/checked-approval.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>


      </html>