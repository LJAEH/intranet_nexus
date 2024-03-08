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
        <link rel="stylesheet" href="${contextPath}/resources/css/work/work-temp.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/checked-approval.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/work/write-approval.css">
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

        <!-- 서머노트를 위해 추가해야할 부분 -->
        <script src="${contextPath}/resources/js/summernote/summernote-lite.js"></script>
        <script src="${contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
        <link rel="stylesheet" href="${contextPath}/resources/css/summernote/summernote-lite.css">

        <title>결재-임시저장</title>
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
              <p class="content-all-top-text2">임시 저장(${fn:length(tempList)})</p>
            </div>

            <div class="content-all-bottom-area">

              <div class="content-all-bottom-area-header">

                <select placeholder="전체" id="attnTypeSelect">
                  <option value="0">전체</option>
                  <option value="1">일반</option>
                  <option value="2">연차</option>
                  <option value="3">출장</option>
                </select>

                <div class="button-box">

                  <button type="button" class="omen-btn" id="popupBtn">결재 작성하기</button>

                </div>

              </div>

              <div class="content-all-bottom-area-content">

                <table>

                  <thead>

                    <tr>
                      <th style="display: none;">결재번호</th>
                      <th style="display: none;">타입번호</th>
                      <th>종류</th>
                      <th style="width: 500px;">제목</th>
                      <th>작성일</th>
                      <th>삭제</th>
                    </tr>

                  </thead>

                  <tbody>
                    <c:forEach var="item" items="${tempList}">
                      <tr class="listTr" onclick="tempWrite(${item.workNo})">
                        <td style="display: none;">${item.workNo}</td>
                        <td style="display: none;" class="listTypeNo">${item.typeNo}</td>
                        <td>${item.typeName}</td>
                        <td>${item.title}</td>
                        <td>${fn:substring(item.sendDate, 0, 11)}</td>
                        <td><a class="fa-solid fa-trash" onclick="event.stopPropagation(); deleteTemp(${item.workNo})" style="color: #c7c7c7;"></a></td>
                      </tr>
                    </c:forEach>
                  </tbody>

                </table>

              </div>

            </div>

          </div>
          <!-------------------------------- 컨텐츠 내용 영역 끝 --------------------------------------------------------------------->

        </section>

        <!-- 페이지 정의 -->
        <c:set var="flag" value="1" scope="request" />
        <jsp:include page="/WEB-INF/views/work/write-approval.jsp" />
        <jsp:include page="/WEB-INF/views/work/checked-approval.jsp" />

        <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />

        <script src="${contextPath}/resources/js/work/work-temp.js"></script>
        <script src="${contextPath}/resources/js/work/write-approval.js"></script>
        <script src="${contextPath}/resources/js/work/checked-approval.js"></script>
        <script src="${contextPath}/resources/js/component.js"></script>

      </body>

      </html>