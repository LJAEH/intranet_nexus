<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="${contextPath}/resources/css/member/login.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/common/component/font.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">

            <script src="https://code.jquery.com/jquery-3.7.0.js"
                integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
            <!-- Favicon-->
            <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
            <!-- sweetAlert2 cdn -->
            <link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
            <title>로그인페이지</title>
        </head>

        <body>


            <div class="container">

                <div class="login-box">
                    <img class="logo-width" src="${contextPath}/resources/images/logo-height.png" alt="">
                    <form action="member/login" method="POST" name="login-form" onsubmit="return loginValidate()">
                        <div class="user-box">
                            <input type="text" id="inputId" name="memId" autocomplete="off" required="">
                            <label>ID</label>
                        </div>
                        <div class="user-box">
                            <input type="password" id="inputPw" name="memPw" required="">
                            <label>Password</label>
                        </div>

                        <a href="main">
                            <span></span>
                            <span></span>
                            <span></span>
                            <span></span>
                            <button>login</button>
                        </a>
                    </form>
                </div>
            </div>

            <script src="${contextPath}/resources/js/member/login.js"></script>


            <c:if test="${ !empty message }">
                <script>
                    Swal.fire("${message}");
                </script>
            </c:if>

        </body>

        </html>