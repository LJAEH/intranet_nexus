<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
  <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
  <link rel="stylesheet" href="${contextPath}/resources/css/member/myPage-pwChange.css">
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
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
  <title>메인페이지</title>
</head>

<body>

  <jsp:include page="/WEB-INF/views/common/header.jsp"/>
  <section>

      <!-- 사이드바 반응형 -->
      <div class="sidebarIconToggle">
        <div class="spinner diagonal part-1"></div>
        <div class="spinner horizontal"></div>
        <div class="spinner diagonal part-2"></div>
      </div>
      <div id="sidebarMenu">
        <ul class="sidebarMenuInner">
          <li>마이메뉴</li>
          <li><a href="${contextPath}/member/myPageProfile"><span>프로필</span> </a></li>
          <li><a href="${contextPath}/member/myPagePwChange"><span>비밀번호 변경</span></a></li>
        </ul>
      </div>

    <!-- 사이드 바 -->
    <div class="side-bar menu">
      <!-- 페이지마다 바뀌는 제목 -->
      <h1>마이메뉴</h1>

      <!-- 페이지마다 바뀌는 부제목 -->
      <ul>
        <li><a href="${contextPath}/member/myPageProfile"><span>프로필</span> </a></li>
        <li><a href="${contextPath}/member/myPagePwChange"><span>비밀번호 변경</span></a></li>
      </ul>
    </div>

    <!-- 컨텐츠 내용 -->
    <div class="content-all-page">
      <a href="#" id="sidebar-toggle"></a>

      <!-- 컨텐츠 내용 윗부분 -->
      <div class="content-all-top-area">
        <p class="content-all-top-text1">마이메뉴 / </p>
        <p class="content-all-top-text2">비밀번호 변경</p>
        <!-- 선 -->
        <div class="content-all-top-line"></div>
      </div>

      <!-- 컨텐츠 내용 아랫부분 -->
      <div class="content-all-bottom-area">
        <form name="myForm" action="myPagePwChangeFunction" method="post" onsubmit="return changePwValidate()">
          <!-- 현재 비밀번호 -->
          <div class="myPage-pw-input-container">
            <div class="myPage-pw-input">
              <input type="password" id="current-pw" name="currentPw" maxlength="15" required>
              <label>현재 비밀번호</label>
              <span></span>
            </div>
            <div id="current-pw-inspection"></div>
          </div>
          <!-- 변경할 비밀번호 -->
          <div class="myPage-pw-input-container">
            <div class="myPage-pw-input">
              <input type="password" id="new-pw" name="newPw" maxlength="15" required>
              <label>변경할 비밀번호</label>
              <span></span>
            </div>
            <div id="new-pw-inspection">영문, 숫자, 특수기호(!@#$%^*+=-) 조합으로 8-15자리 이내로 입력해주세요</div>
          </div>
          <!-- 변경할 비밀번호 확인 -->
          <div class="myPage-pw-input-container">
            <div class="myPage-pw-input">
              <input type="password" id="new-pw-check" maxlength="15" required>
              <label>변경할 비밀번호 확인</label>
              <span></span>
            </div>
            <div id="new-pw-check-inspection"></div>
          </div>

          <button class="password-btn">비밀번호 변경</button>

        </form>
      </div>

    </div>

    <!-- 채팅창 -->
   <jsp:include page="/WEB-INF/views/common/chat-modal.jsp" />
  </section>

  <script src="${contextPath}/resources/js/member/myPage-pwChange.js"></script>
  <script src="${contextPath}/resources/js/component.js"></script>
</body>

</html>