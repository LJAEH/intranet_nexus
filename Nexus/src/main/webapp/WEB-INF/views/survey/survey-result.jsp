<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <c:set var="survey" value="${map.survey}" />
        <c:set var="surveyResultList" value="${map.surveyResultList}" />
        <c:set var="optionMemberCount" value="${map.optionMemberCount}" />
        <c:set var="respMember" value="${map.respMember}" />

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/survey/survey-result.css ">
            <!-- Favicon-->
            <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
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

            <title>설문 - 설문 결과</title>
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

                    <!-- 윗부분 -->
                    <div class="survey-result-top">
                        <h1>${survey.surveyTopic}</h1>
                        <span>${survey.surveyContent}</span>
                        <br>
                    </div>

                    <!-- 가운데 -->
                    <div class="survey-result-content">
                        <span class="survey-question">질문 : ${surveyResultList[0].question}</span>
                        <span class="survey-respMember">전체응답 : ${respMember}명</span>
                        <div id="survey-result-content-detail">
                            <c:forEach var="surveyResult" items="${surveyResultList}">
                                <div id="result-box">
                                    <div id="result-span-box">
                                        <span>${surveyResult.optionAnnotation} - </span>
                                        <span>${surveyResult.optionMemberCount}명</span>
                                    </div>
                                    <div>
                                        <progress class="progress" id="progressBar"
                                            value="${surveyResult.optionMemberCount}" max="${respMember}"></progress>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- 아래 취소 저장 부분 -->
                    <div class="survey-result-bottom">
                        <button type=button id="survey-result-bottom-cancle">목록으로</button>
                    </div>


                </div>









            </section>

            <!-- 사이드바 -->
            <script>
                document.addEventListener('click', function (event) {
                    var sidebarMenu = document.getElementById('sidebarMenu');
                    var openSidebarMenu = document.getElementById('openSidebarMenu');
                    var sidebarIconToggle = document.querySelector('.sidebarIconToggle');

                    var isClickInsideSidebar = sidebarMenu.contains(event.target);
                    var isClickInsideToggle = openSidebarMenu.contains(event.target);

                    if (!isClickInsideSidebar && !isClickInsideToggle && openSidebarMenu.checked) {
                        openSidebarMenu.checked = false;
                    }
                });
            </script>


            <script src="${contextPath}/resources/js/survey/survey-result.js"></script>
            <script src="${contextPath}/resources/js/component.js"></script>

        </body>

        </html>