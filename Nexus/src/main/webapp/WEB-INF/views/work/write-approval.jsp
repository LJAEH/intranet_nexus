<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<body>
  <div id="modalWrap">
    <div class="work-modalBody">
      <span id="closeBtn">
        <img src="${contextPath}/resources/images/Xbtn.png" alt="">
      </span>
      <h1>결재상신</h1>
      <!-- 선1 -->
      <div class="work-modal-line"></div>

      <form action="write" method="post" enctype="multipart/form-data" id="writeForm">
        <!-- 템플릿 -->
        <div class="work-modal-template">
          <p>결재 타입</p>

          <div class="work-modal-template-select">

            <select name="workTypeWord" id="work-template">
              <option value="normal-check">일반</option>
              <option value="business-trip">출장</option>
              <option value="vacation">연차</option>
              <option value="project">프로젝트</option>
              <option value="assignment">과제</option>
            </select>
            
            <select name="" id="normal-checked">
              <option value="userCustom">직접작성</option>
              <option value="normalEx1">경조금신청서(예시)</option>
              <option value="normalEx2">구매요청서(예시)</option>
              <option value="normalEx3">자산요청서(예시)</option>
              <option value="normalEx4">지출결의서(예시)</option>
            </select>

            <select name="projectNo" id="project-checked">
              <option value="">-- 프로젝트 선택 --</option>
              <c:forEach var="pList" items="${pList}">
                <option value="${pList.projectNo}">${pList.projectTitle}</option>
              </c:forEach>
            </select>

            <c:forEach var="pList" items="${pList}">
              <select name="taskNo" class="assignment-checked" data-project-no="${pList.projectNo}">
                <!-- <option value="">--- 과제 선택 ---</option> -->
                <c:forEach var="ptList" items="${ptList}" varStatus="status">
                  <c:if test="${ptList.projectNo == pList.projectNo}">
                    <option value="${ptList.taskNo}">${ptList.taskTitle}</option>
                  </c:if>
                </c:forEach>
              </select>
            </c:forEach>

            <input name="taskNo" type="hidden">
            
          </div>

          <!-- 제목 -->
          <div class="work-modal-title">
            <p>제목</p>
            <input type="text" placeholder="제목을 입력해주세요" name="title">
          </div>
          <!-- 날짜 박스 -->
          <div class="date-Box">

            <!-- 시작날짜 -->
            <div class="work-modal-startDate">
              <p>시작날짜</p>
              <input type="date" name="start" >
            </div>

            <!-- 종료날짜 -->
            <div class="work-modal-endDate">
              <p>종료날짜</p>
              <input type="date" name="end" >
            </div>

          </div>
          <!-- 내용 -->
          <div class="work-modal-detail">
            <p>내용</p>
            <textarea name="content" class="summernote" spellcheck="false"></textarea>
          </div>

          <!-- 프로젝트박스 -->
          <div class="work-modal-projectBox">
            <span id="pulsProject">
              <img src="${contextPath}/resources/images/plus.png" alt="">
              과제 추가
            </span>
          </div>
        
          <div class="projectBox"> 
            <p class="taskTitleSpan" style="margin-top: 10px;">과제명</p>
            <input name="taskTitle" type="text">
          </div>
        </div>   

        <!-- 결재자 -->
        <div class ="work-modal-approverBox">
          <span id="pulsApprover">
            <img src="${contextPath}/resources/images/plus.png" alt="">
            결재자 추가
          </span>  
        </div>

        <div class="work-modal-approver">
          <p>결재자</p>
          <div id="showMemName">-</div>
          <input style="display: none;" type="text" name="next">
        </div>

        <!-- 파일 업로드 -->
        <div class="work-file-box">
          <button type="button" id="file-remove">파일 지우기</button>
          <label for="file-uploads">파일 올리기</label>
          <input type="file" id="file-uploads" name="file-uploads" accept="" multiple>
        </div>

        <!-- 선택된 파일 -->
        <div class="work-preview"></div>
      
        <!-- 선2 -->
        <div class="work-modal-line"></div>
      
        <!-- 임시저장 버튼 -->
        <div class="work-modal-save">
          <button type="button" id="save-draft" value="tempSaveBtn">임시 저장</button>
          <input type="text" id='tempSaveBtn-checkbox' name="tempSave" style="display: none;" value="false">
        </div>

        <!-- 버튼 -->
        <div class="work-submit-reset-btns">
          <button type="button" id="cancell-btn">취소</button>
          <button type="button" id="success-btn" value="submitBtn">확인</button>
          <button type="button" id="tempSuccessBtn" value="submitBtn" style="display: none;">확인</button>
        </div>
        
        <div id="approver-modal-wrap">
          <div class="approver-modal-Body">
            <span id="approver-closeBtn">
              <img src="${contextPath}/resources/images/Xbtn.png" alt="">
            </span>
            <h1>결재 라인 설정</h1>
            <!-- 선1 -->
            <div class="approver-modal-line1"></div>

            <div class="approver-modal-container">

                <div class="dept-Box">
                  <ul class="executives"><p class="heartBeat">임원</p>

                  </ul>
                  <ul class="dept"><p class="heartBeat">부서</p>
                    <li class="dept-list"><p>관리기획부</p></li>
                    <li class="dept-list"><p>업무기획부</p></li>
                    <li class="dept-list"><p>공무기획부</p></li>
                    <li class="dept-list"><p>개발사업부</p></li>
                    <li class="dept-list end"><p>전략기획실</p></li>
                  </ul>
                </div>

                <div class="team-Box">

                  <ul class="senior">
                    <li class="senior-list"><p>대표이사</p></li>
                    <li class="senior-list"><p>전무이사</p></li>
                    <li class="senior-list"><p>상무이사</p></li>
                  </ul>

                  <ul class="team">
                    <li class="team-list"><p>재무팀</p></li>
                    <li class="team-list"><p>인사총무팀</p></li>

                    <li class="team-list"><p>영업팀</p></li>
                    <li class="team-list"><p>기술개발팀</p></li>

                    <li class="team-list"><p>공사팀</p></li>                     
                    <li class="team-list"><p>공무팀</p></li>
                    <li class="team-list"><p>견적팀</p></li>
                    <li class="team-list"><p>자재팀</p></li>

                    <li class="team-list"><p>개발기획팀</p></li>
                    <li class="team-list"><p>분양홍보팀</p></li>
                    <li class="team-list"><p>설계기획팀</p></li>

                    <li class="team-list"><p>경영팀</p></li>
                    <li class="team-list"><p>전략팀</p></li>
                  </ul>

                </div>

                <div class="teamone-Box">


                  <ul style="display: flex; flex-direction: column;">
                    <li class="senior-team"></li>
                    <li class="senior-team"></li>
                    <li class="senior-team"></li>
                  </ul>

                  <ul style="display: flex; flex-direction: column;">

                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                    <li class="approval-team"></li>
                      
                  </ul>
                </div>

            </div>

            <div class="approver-modal-line2"></div>
            <!-- 버튼 -->
            <div class="approver-submit-reset-btns">
              <button type="button" id="approver-cancell-btn">취소</button>
              <button type="button" id="approver-success-btn">확인</button>
            </div>
          
          </div>

        
        </div>
      
      </div>

        
      </form>

    </div>

  </div>

</body>
