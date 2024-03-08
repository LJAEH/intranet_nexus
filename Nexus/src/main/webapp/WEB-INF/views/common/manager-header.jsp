<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
      .btn {
        font-size: 20px !important;
      }
    </style>


    <header>

      <div class="content-header-area">

        <div class="content-header-area-left">

          <!-- 로고 -->
          <div>
            <a>
              <img src="${contextPath}/resources/images/logo-width.png" alt="">
            </a>
          </div>
        </div>

        <!-- 개인 정보 -->
        <div class="btn-container">
          <button class="btn"><span>관리자님 안녕하세요! 저를 누르면 로그아웃을 할수 있습니다.</span>
            <ul class="dropdown">
              <li><a class="header-log-out">Log Out</a></li>
            </ul>
          </button>
        </div>

      </div>



    </header>

    <c:if test="${ !empty message }">
      <script>
        Swal.fire("${message}");

      </script>

    </c:if>
    <c:if test="${!empty resultMessage}">

     <script>
      const contextPath = "<c:out value='${contextPath}' />";
    </script>
      <script>
        var memIdMessage = "${resultMessage}";
        var temp = memIdMessage.split(",,");
        Swal.fire(temp[0])
          .then((result) => {
            if (result.isConfirmed) {
              Swal.fire("추가하신 직원의 아이디는 \n" + temp[1] + " 입니다.")
            }
          })

      </script>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1"></script>

    <script>
      // 로그아웃
      const logOut = document.querySelector('.header-log-out');
      logOut.addEventListener('click', logoutEvent);

      function logoutEvent() {
        Swal.fire({
          title: '로그아웃을 하시겠습니까?',
          text: "",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '그래, 할거야!',
          cancelButtonText: '아직..나에게는 할일이 남아있어..'
        }).then((result) => {
          if (result.isConfirmed) {
            confetti({
              particleCount: 150,
              spread: 60
            });
            Swal.fire(
              '축하합니다',
              '퇴근하십시오.',
              'success'
            ).then((result) => {

              const url = contextPath + "/member/logout";
              window.location.href = url;

            })
          }
        })
      }

    </script>