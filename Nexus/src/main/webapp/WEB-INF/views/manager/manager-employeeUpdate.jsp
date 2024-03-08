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
      <link rel="stylesheet" href="${contextPath}/resources/css/manager/manager-employeeUpdate.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/common/style.css">

      <!-- sweetAlert2 cdn -->
      <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
      <!-- jquery cdn -->
      <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
      <!-- 우편번호 api -->
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />


      <title>관리자-직원수정</title>
    </head>

    <body>

      <jsp:include page="/WEB-INF/views/common/manager-header.jsp" />
      <section>

           <!-- 사이드바 반응형 -->
           <div class="sidebarIconToggle">
            <div class="spinner diagonal part-1"></div>
            <div class="spinner horizontal"></div>
            <div class="spinner diagonal part-2"></div>
          </div>
          <div id="sidebarMenu">
            <ul class="sidebarMenuInner">
              <li>직원/공지사항</li>
              <li><a href="${contextPath}/member/memberAdd"><span>추가</span> </a></li>
              <li><a href="${contextPath}/member/memberUpdate"><span>정보 수정</span> </a></li>
              <li><a href="${contextPath}/member/memberDelete"><span>퇴사</span> </a></li>
              <li><a href="${contextPath}/member/memberCheck"><span>조회</span> </a></li>
              <li><a href="${contextPath}/member/notice"><span>공지사항 등록</span> </a></li>
            </ul>
          </div>

        <!-- 사이드 바 -->
        <div class="side-bar menu">
          <!-- 페이지마다 바뀌는 제목 -->
          <h1>직원</h1>
          <ul class="employee-ul">
            <li><a href="${contextPath}/member/memberAdd"><span>추가</span> </a></li>
            <li><a href="${contextPath}/member/memberUpdate"><span>정보 수정</span> </a></li>
            <li><a href="${contextPath}/member/memberDelete"><span>퇴사</span> </a></li>
            <li><a href="${contextPath}/member/memberCheck"><span>조회</span> </a></li>

          </ul>
          <h1>공지사항</h1>
          <ul class="notice-ul">
            <li><a href="${contextPath}/member/notice"><span>공지사항 등록</span> </a></li>
          </ul>
        </div>


        <!-- 컨텐츠 내용 -->
        <div class="content-all-page">
          <a href="#" id="sidebar-toggle"></a>

          <!-- 컨텐츠 내용 윗부분 -->
          <div class="content-all-top-area">
            <p class="content-all-top-text1">직원 / </p>
            <p class="content-all-top-text2">정보 수정</p>
            <!-- 선 -->
            <div class="content-all-top-line"></div>
          </div>

          <!-- 컨텐츠 내용 아랫부분 -->
          <div class="content-all-bottom-area">

            <!-- 직원 찾기 -->
            <div class="employee-search">
              <p>직원 찾기</p>
              <label for="search"></label>
              <input id="search" type="search" name="memNo" placeholder="사원번호를 입력해 주세요" maxlength="10"
                autocomplete="off" autofocus required />
              <button id="search-btn">검색하기</button>
            </div>



            <!-- 직원 수정 -->
            <form action="update" method="post" id="update-form" onsubmit="return memberUpdateCheck()">
              <!-- 이름 -->
              <div class="employee-area-container">
                <div class="employee-area">
                  <p>이름</p>
                  <input type="text" id="employee-name" name="memName" maxlength="10" autocomplete="off" required
                    readonly>
                </div>
                <div id="name-check" class="employee-check">한글 또는 영문으로 2-10글자 이내로 작성해 주세요</div>
              </div>
              <!-- 주민번호  -->
              <div class="employee-area-container">
                <div class="employee-area">
                  <p>주민번호</p>
                  <input type="text" id="employee-ssn" name="memRNo" maxlength="14" autocomplete="off" required
                    readonly>
                  <span></span>
                </div>
                <div id="ssn-check" class="employee-check">하이픈(-)포함 14자 이내로 작성해주세요</div>
              </div>
              <!-- 전화번호  -->
              <div class="employee-area-container">
                <div class="employee-area">
                  <p>전화번호</p>
                  <input type="tel" id="employee-tel" name="memTel" maxlength="13" autocomplete="off" required readonly>
                  <span></span>
                </div>
                <div id="tel-check" class="employee-check">하이픈(-)포함 010- 으로 시작해 주세요</div>
              </div>
              <!-- 이메일  -->
              <div class="employee-area-container">
                <div class="employee-area">
                  <p>이메일</p>
                  <input type="email" id="employee-email" name="memEmail" maxlength="30" autocomplete="off" required
                    readonly>
                  <span></span>
                </div>
                <div id="email-check" class="employee-check">@포함 이메일 형식으로 작성해 주세요</div>
              </div>
              <!-- 주소 -->
              <div class="employee-area-container">
                <div class="employee-area">
                  <p>주소</p>
                  <input type="text" id="sample3_address" name="memAddress" required readonly>
                  <span></span>
                </div>
                <button class="address-btn" type="button" onclick="sample3_execDaumPostcode()" disabled>주소찾기</button>
              </div>
              <div id="wrap"
                style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap"
                  style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()"
                  alt="접기 버튼">
              </div>
              <div class="employee-area">
                <p>상세주소</p>
                <input type="text" id="sample3_detailAddress" name="memAddress" required readonly>
                <span></span>
              </div>

              <!-- 직책선택-->
              <div class="employee-rank">
                <p>직책</p>
                <div class="employee-rank-radio-box">
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-junior" value="6">
                    <label for="job-junior">사원</label>
                  </div>
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-senior" value="5">
                    <label for="job-senior">팀장</label>
                  </div>
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-director" value="4">
                    <label for="job-director">부장</label>
                  </div>
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-managing-director" value="3">
                    <label for="job-managing-director">상무이사</label>
                  </div>
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-executive-director" value="2">
                    <label for="job-executive-director">전무이사</label>
                  </div>
                  <div class="employee-rank-radio">
                    <input type="radio" name="jobNo" id="job-ceo" value="1">
                    <label for="job-ceo">대표이사</label>
                  </div>
                </div>
              </div>

              <!-- 부서선택 -->
              <div class="employee-dept">
                <p>부서</p>
                <div>
                  <select name="deptNo" id="dept" disabled>
                    <option value="" selected>=== 부서선택 ===</option>
                    <option value="1">관리기획부</option>
                    <option value="2">업무기획부</option>
                    <option value="3">공무기획부</option>
                    <option value="4">개발사업부</option>
                    <option value="5">전략기획실</option>
                  </select>
                  <select name="teamNo" id="dept-option" disabled>

                  </select>
                </div>
              </div>

              <!-- 직원수정 버튼 -->
              <button class="employee-add-btn" disabled>수정</button>
            </form>

          </div>
        </div>

        <!-- 채팅창 -->
        <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
      </section>

      <script src="${contextPath}/resources/js/manager/manager-employeeUpdate.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>
    </body>

    </html>