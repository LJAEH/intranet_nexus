<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common/component.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/common/variable.css">
     <!-- font-awesome cdn -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
     integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
     crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>빛나는넥서스</title>
    <style>
       
        body {
            
            overflow: hidden;
            background-image: url("${contextPath}/resources/images/popupBG.jpg");
            background-size: cover;
            background-repeat: no-repeat;
        }


        h1 {
            color: white;
            font-weight: bold;
            text-align: center;
            font-size: 40px;
            padding-top: 35px;
            text-shadow: 1px 4px 15px rgba(128, 128, 128, 3);
        }

        p {
            color: white;
            text-align: center;
        }

        .surveyBtn {
            width: 145px;
            margin: auto;
            margin-top: 12rem;
            background-color: var(--gray50);
            box-shadow: 0px 4px 15px rgba(128, 128, 128, 0.8);
            padding: 10px;
            color: var(--black);
            border-radius: 10px;
            font-family: 'NanumSquareNeo-Variable';
            cursor: pointer;
        }

        .surveyBtn:hover {
            background-color: var(--primary400);
            color: white;
            box-shadow: 0px 4px 15px rgba(128, 128, 128, 0.8);
        }

        .surveyBtn > span {
            padding: 18px 0;
        }

        .today-none {
            height: 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 142px;
            background-color: var(--black);
            color: white;
            font-family: 'NanumSquareNeo-Variable';
        }

        .today-check {
            display: flex;
            gap: 5px;
            padding-left: 10px;
            align-items: flex-start;
        }

        .today-close {
            padding-right: 10px;
            cursor: pointer;
        }

        .today-close:hover {
            color: var(--primary400);
            text-decoration: underline;
        }

        .size {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        
    </style>
</head>
<body>

    <div class="size">
        <h1>설문이 진행중 입니다.</h1>
        
        <div class="surveyBtn">
            <span onclick="changeParent();">설문참여 하러가기</span>
            <i class="fa-solid fa-angle-right"></i>
        </div>
    
        <div class="today-none">
            <div class="today-check">
                <input type="checkbox" id="check" >
                <span>오늘 하루동안 보지 않기</span>
            </div>
                <span class="today-close" onclick="closePopup();">끝내기</span>
        </div>
    </div>

       
    
   
    <script>
    function setCookie(name, value, expiredays) {
    var date = new Date();
    date.setDate(date.getDate() + expiredays);
    document.cookie = escape(name) + "=" + escape(value) + "; expires=" + date.toUTCString();
    }

    function closePopup() {
        if (document.getElementById("check").checked) {
            setCookie("popupYN", "N", 1);
            self.close();
        } else {
            window.close();
        }
    }

    function changeParent() {
        window.opener.location.href = "${contextPath}/survey/surveyList";
        window.close();
    }
    </script>
</body>
</html>