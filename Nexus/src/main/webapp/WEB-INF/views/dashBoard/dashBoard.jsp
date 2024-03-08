<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>${dtList[0].deptName}_대시보드</title>
                <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                <script src="https://kit.fontawesome.com/3cd0aae50a.js" crossorigin="anonymous"></script>


                <!-- sweetAlert2 cdn -->
                <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
                <!-- jquery cdn -->
                <script src="https://code.jquery.com/jquery-3.7.0.js"
                    integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
                <!-- Favicon-->
                <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />

                <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
                <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
                <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
                <link rel="stylesheet" href="${contextPath}/resources/css/dashBoard/dashBoard.css">

            </head>

            <body>


                <jsp:include page="/WEB-INF/views/common/header.jsp" />
                <section>

                    <div class="container">

                        <div id="hr-div" class="mainDiv">
                            <div class="left personnel-management-left">
                                <div class="hr-head">
                                    <h1 class="managePageA">❐ 인적자원 관리</h1>
                                </div>
                                <div class="doughnut-div">
                                    <canvas id="doughnut"></canvas>
                                </div>
                            </div>

                            <div class="hr-content personnel-management-right">
                                <div class="info-div">
                                    <!-- 팀라디오 선택 -->
                                    <div class="team-selector">
                                        <div class="team-selector-box">
                                            <c:forEach var="team" items="${dtList}">
                                                <input type="radio" id="team${team.teamNo}-radio" name="team"
                                                    value="${team.teamNo}">
                                                <label for="team${team.teamNo}-radio">${team.teamName}</label><br>
                                            </c:forEach>
                                        </div>
                                        <div class="more-box">
                                            <a href="${contextPath}/dashBoard/humanResourceManage">전체보기 ></a>
                                        </div>
                                    </div>
                                    <!-- 팀별 조회용 기본적으로 display : none -->
                                    <c:forEach var="team" items="${dtList}">
                                        <div id="team-${team.teamNo}-data" class="team-data" style="display: none">
                                            <c:forEach var="hrList" items="${hrList}">
                                                <c:if test="${hrList.teamNo == team.teamNo}">
                                                    <div id="attn-info-div" class="team-bar">
                                                        <div class="mem-info">
                                                            <span class="name">${hrList.memName}</span>
                                                            <span class="job">${hrList.jobName}</span>
                                                        </div>
                                                        <div class="bar-info">
                                                            <div>
                                                                <progress name="progress" class="progress"
                                                                    id="progressBar" value="${hrList.workTime}"
                                                                    max="160"></progress>
                                                                <span class="tooltiptext" id="tooltipText"></span>
                                                            </div>
                                                            <div class="progressTemp">
                                                                <span>0H</span>
                                                                <span>160H</span>
                                                            </div>
                                                        </div>
                                                        <div class="work-time">
                                                            <span class="week-total">${hrList.workTime} 시간
                                                                ${hrList.workMin} 분</span>
                                                            <span class="extend">+${hrList.exTime}H
                                                                ${hrList.exMin}M</span>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                        </div>

                        <div class="project-div mainDiv">
                            <div class="left project-management-left">
                                <div class="project-head">
                                    <h1 class="managePageA">❐ 프로젝트 관리</h1>
                                </div>
                                <div class="polar-div">
                                    <canvas id="polarArea" aria-label="Hello ARIA World" role="img"></canvas>
                                </div>
                            </div>


                            <!-- 프로젝트 생성 -->
                            <div class="project-content project-management-right">
                                <div class="project-info-div">
                                    <c:forEach var="projectTotal" items="${prList}">
                                        <div id="projectDiv-${projectTotal.projectNo}" class="projectDiv">
                                            <table class="projects-table">
                                                <thead>
                                                    <tr class="projects-table-tr1">
                                                        <th>Project</th>
                                                        <th>Deadline</th>
                                                        <th>Leader + Team</th>
                                                        <th class="text-right">Progress</th>
                                                    </tr>
                                                </thead>
                                                <tr>
                                                    <td>
                                                        <P>${projectTotal.title}</P>
                                                        <p></p>
                                                    </td>
                                                    <td>
                                                        <P>${projectTotal.end}</P>
                                                        <P>마감일</P>
                                                    </td>
                                                    <td>
                                                        <P>${projectTotal.memName} ${projectTotal.jobName}</P>
                                                        <P>${projectTotal.teamName}</P>
                                                    <td class="project-progress">
                                                        <div class="single-chart">
                                                            <svg viewBox="0 0 36 36" class="circular-chart green">

                                                                <ellipse class="circle-bg" cx="18" cy="18" rx="14"
                                                                    ry="14" stroke-width="10" fill="transparent" />
                                                                <ellipse class="circle" cx="18" cy="18" rx="14" ry="14"
                                                                    stroke-dasharray="${projectTotal.percent}, 100"
                                                                    stroke-width="10" fill="transparent" />
                                                            </svg>
                                                            <text x="18" y="22"
                                                                class="percentage">${projectTotal.percent}%</text>
                                                        </div>
                                                    </td>
                                                    <td style="display: none;">
                                                        <P>Project No: ${projectTotal.projectNo}</P>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table class="projects-table">
                                                <thead>
                                                    <tr class="projects-table-tr2">
                                                        <th>Project Task</th>
                                                        <th>SendDate</th>
                                                        <th>Writer</th>
                                                        <th class="text-right">Work State</th>
                                                    </tr>
                                                </thead>
                                                <c:forEach var="taskTotal" items="${projectTotal.taskList}">
                                                    <tr>
                                                        <td>
                                                            <p>${taskTotal.title}</p>
                                                            <p></p>
                                                        </td>
                                                        <c:choose>
                                                            <c:when test="${empty taskTotal.approvalDate}">
                                                                <td>
                                                                    <p class="text-danger text-red">미제출</p>
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>
                                                                    <p>${taskTotal.approvalDate}</p>
                                                                    <p class="text-danger">제출일</p>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${empty taskTotal.memName}">
                                                                <td>
                                                                    <p class="text-danger text-red">미제출</p>
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td class="member">
                                                                    <div class="member-info">
                                                                        <p>${taskTotal.memName} ${taskTotal.jobName}</p>
                                                                        <p>${taskTotal.teamName}</p>
                                                                    </div>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td class="status">
                                  
                                                            <c:choose>
                                                                <c:when test="${taskTotal.workState == '승인'}">
                                                                    <span class="status-green"
                                                                        value="${taskTotal.workState}">
                                                                        ${taskTotal.workState}
                                                                    </span>
                                                                </c:when> 
                                                                <c:when test="${taskTotal.workState == '진행중'}">
                                                                    <span class="status-orange"
                                                                        value="${taskTotal.workState}">
                                                                        ${taskTotal.workState}
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${taskTotal.workState == '반려'}">
                                                                    <span class="status-red"
                                                                        value="${taskTotal.workState}">
                                                                        ${taskTotal.workState}
                                                                    </span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="status-blue"
                                                                        value="${taskTotal.workState}">
                                                                        미진행
                                                                    </span>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </td>
                                                    </tr>
                                                </c:forEach>


                                            </table>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>


                        </div>

                    </div>


                </section>





                <script src="${contextPath}/resources/js/dashBoard/dashBoardMain.js"></script>

            </body>

            </html>