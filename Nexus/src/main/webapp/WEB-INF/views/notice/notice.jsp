<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <c:set var="pagination" value="${map.pagination}" />
      <c:set var="boardList" value="${map.boardList}" />

      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/notice/notice.css">
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
        <!-- sweetAlert2 cdn -->
        <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <!-- jquery cdn -->
        <script src="https://code.jquery.com/jquery-3.7.0.js"
          integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

        <title>공지사항</title>
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
              <li>공지사항</li>
              <li><a href=""><span>공지사항</span> </a></li>
            </ul>
          </div>


          <!-- 사이드 바 -->
          <div class="side-bar menu">
            <!-- 페이지마다 바뀌는 제목 -->
            <h1>공지사항</h1>

            <!-- 페이지마다 바뀌는 부제목 -->
            <ul>
              <li><a href=""><span>공지사항</span> </a></li>

            </ul>
          </div>

          <!-- 컨텐츠 내용 -->
          <div class="content-all-page">

            <!-- 컨텐츠 내용 윗부분 -->
            <div class="content-all-top-area">

              <div class="content-all-top-area-detail">

                <!-- 컨텐츠 제목 부분 -->
                <div>
                  <p class="content-all-top-text1">공지사항 / </p>
                  <p class="content-all-top-text2">공지사항</p>
                </div>

                <!-- 오른쪽 버튼 부분 -->

                <!-- 공지사항 모달창 -->
                <div>
                  <button type="button" class="omen-btn" id="popupBtn">공지사항 추가</button>
                </div>

                <div id="modalWrap">
                  <div class="modalBody">
                    <span id="closeBtn">
                      <img src="${contextPath}/resources/images/Xbtn.png" alt="">
                    </span>
                    <h1>공지사항</h1>
                    <!-- 선1 -->
                    <div class="modal-line"></div>

                    <!-- 제목 -->
                    <form action="writeNotice" enctype="multipart/form-data" method="post">
                      <div class="modal-title">
                        <p>제목</p>
                        <input type="text" name="title" placeholder="제목을 입력해주세요" required>
                      </div>
                      <!-- 내용 -->
                      <div class="modal-detail">
                        <p>내용</p>
                        <textarea name="content" id="" onkeydown="handleResizeHeight(this)"
                          onkeyup="handleResizeHeight(this)"></textarea>
                      </div>
                      <!-- 파일 업로드 -->
                      <div class="file-box">
                        <button type="button" id="file-remove">파일 지우기</button>
                        <label for="file-uploads">파일 올리기</label>
                        <input type="file" id="file-uploads" name=" uploadFile" accept="" multiple>
                      </div>
                      <!-- 선택된 파일 -->
                      <div class="preview"></div>
                      <!-- 선2 -->
                      <div class="modal-line"></div>
                      <!-- 버튼 -->
                      <div class="notice-submit-reset-btns">
                        <button type="reset" id="cancell-btn">취소</button>
                        <button id="success-btn">확인</button>
                      </div>
                    </form>
                  </div>
                </div>

              </div>




            </div>

            <!-- 컨텐츠 내용 아랫부분 -->
            <div class="content-all-bottom-area">
              <div class="notice-table">
                <table>

                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>제목</th>
                      <th>작성일</th>
                    </tr>
                  </thead>

                  <tbody>
                    <c:choose>
                      <c:when test="${empty boardList}">
                        <tr class="noneHover">
                          <th colspan="4" class="noneNotice">게시글이 존재하지 않습니다.</th>
                        </tr>
                      </c:when>

                      <c:otherwise>
                        <c:forEach var="board" items="${boardList}">
                          <tr onclick="detailModal(${board.noticeNo})">
                            <td>${board.noticeNo}</td>
                            <td>${board.title}</td>
                            <td>
                              <fmt:parseDate value='${board.createDate}' var='boardDate' pattern='yyyymmdd' />
                              <fmt:formatDate pattern="yyyy-MM-dd" value="${boardDate}" />
                            <td>
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
                  <c:if test="${pagination.currentPage > 1}">
                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
                    <li class="prevPage"><a href="${url}${pagination.currentPage - 1}${sURL}" class="blue">이전</a></li>
                  </c:if>
                  <c:if test="${pagination.currentPage == 1}">
                    <li><a href="javascript:void(0);">&lt;&lt;</a></li>
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
                    <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                  </c:if>
                  <c:if test="${pagination.currentPage == pagination.maxPage}">
                    <li class="nextPage"><a href="javascript:void(0);">다음</a></li>
                    <li><a href="javascript:void(0);"">&gt;&gt;</a></li>
              </c:if>
              </ul>
            </div>
          </div>

          <!-- 사용자가 작성한 공지사항 열기 -->
          <div id="check-modalWrap">
                        <div class="check-modalBody">
                          <span id="check-closeBtn">
                            <img src="${contextPath}/resources/images/Xbtn.png" alt="">
                          </span>
                          <h1>공지사항</h1>
                          <!-- 선1 -->
                          <div class="modal-line"></div>

                          <!-- 제목 -->
                          <div>
                            <p>제목</p>
                            <div class="check-modal-title" id="check-modal-title"></div>
                          </div>
                          <!-- 내용 -->
                          <div>
                            <p>내용</p>
                            <div class="check-modal-detail"></div>
                          </div>

                          <!-- 선택된 파일 -->
                          <p>첨부파일</p>
                          <div class="check-preview"></div>
                          <!-- 선2 -->
                          <div class="modal-line"></div>
                          <!-- 버튼 -->
                          <div class="notice-submit-reset-btns">
                            <button id="check-remove-btn">삭제</button>
                            <button type="reset" id="check-cancell-btn">닫기</button>
                            <button id="check-success-btn">수정</button>
                          </div>

                        </div>
              </div>

            </div>







            <!-- 채팅창 -->
            <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
        </section>




        <script src="${contextPath}/resources/js/notice/notice.js"></script>
        <script src="${contextPath}/resources/js/component.js"></script>

      </body>

      </html>