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
      <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
      <link rel="stylesheet" href="${contextPath}/resources/css/survey/survey-create.css ">
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
      <!-- sweetAlert2 cdn -->
      <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
      <!-- font-awesome cdn -->
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

          <form action="create" method="post" onsubmit="return validation()">
            <!-- 윗부분 -->
            <div class="survey-create-top">
              <input id="survey-create-top-title" name="surveyTopic" type="text" placeholder="제목 없는 설문">
              <input name="surveyContent" type="text" placeholder="설문에 대한 설명을 적어주세요.(선택사항)">
              <br>
              <input type="date" name="end" value="" class="input-txt" id="date" aria-required="true"
                aria-invalid="false" placeholder="종료일을 지정해 주세요"
                onchange="this.className=(this.value!=''?'has-value':'')">
            </div>

            <!-- 가운데 -->
            <div class="survey-create-content">
              <div>
                <input id="survey-create-content-question" name="question" type="text" placeholder="질문을 작성해 주세요.">
              </div>

              <!-- 옵션추가 부분 -->
              <ul class="survey-create-content-option">
                <li>
                  <i class="fa-solid fa-check survey-check"></i>
                  <input type="text" name="optionList" placeholder="옵션을 입력해 주세요.">
                  <button type="button" id="survey-option-delete" disabled>
                    <i class="fa-solid fa-xmark fa-xl"></i>
                  </button>
                </li>
              </ul>

              <div class="servey-option-add-area">
                <i class="fa-solid fa-plus fa-2xs"></i>
                <button type="button" id="survey-option-add">옵션 추가</button>
              </div>
            </div>

            <!-- 아래 취소 저장 부분 -->
            <div class="survey-create-bottom">
              <button type="button" id="survey-create-bottom-cancle">취소</button>
              <button type="submit" id="survey-create-bottom-submit">저장</button>
            </div>

          </form>

        </div>





      </section>



      <script src="${contextPath}/resources/js/survey/survey-create.js"></script>
      <script src="${contextPath}/resources/js/component.js"></script>


    </body>

    </html>