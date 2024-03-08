<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
    <!-- 채팅창 영역 -->
        <!-- 채팅 버튼 -->
        <div id="chatting-function" class="chatting-box">
            <a href="#">
                <img class="chatting-img" src="${contextPath}/resources/images/chattImg.png" alt="">
            </a>
        </div>

        <!-- 채팅창 ui -->
        <div class="chat-box">
            <div class="center">
                <div class="contacts">
                    <div class="contact-header">
                        <h2 id="contact-h2">
                            채팅
                        </h2>
                        <i class="fa-solid fa-user fa-2x" id="friends-list"></i>
                        <i class="fa-solid fa-comment fa-2x" id="employee-list"></i>
                    </div>
                    <div id="contact-area">

                    </div>

                    <div class="employee-arrow">
                        <i class="fa-solid fa-caret-right fa-beat-fade fa-lg"></i>
                        <span>오른쪽 화면에 나타납니다</span>
                    </div>


                </div>


                <div id="chat-main">
                    <div class="chat-main-bar">
                        <div class="chat-header">
                            <h2>직원목록</h2>
                            <i class="fas fa-times" id="close"></i>
                        </div>
                    </div>

                    <div id="employee-area">
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>CEO</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-ceo">
                            </ul>
                        </div>
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>Executive Managing Director</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-executive-director">
                            </ul>
                        </div>
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>Managing Director</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-managing-director">
                            </ul>
                        </div>
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>Director</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-director">
                            </ul>
                        </div>
                        
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>Senior</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-senior">
                            </ul>
                        </div>
                        <div class="employee-group ">
                            <div class="employee-dropBox">
                                <span>Junior</span>
                                <i class="fa-solid fa-angle-down fa-lg employee-dropBox-i "></i>
                            </div>
                            <ul class="employee-dropBox-ul employee-junior">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="chat">
                    <div class="bar">
                        <div class="chat-header">
                            <div class="chat-name">

                            </div>
                            <i class="fas fa-times" id="chat-close"></i>

                        </div>

                    </div>


                    <div class="messages" id="chat">
                        <div class="time">
                        </div>

                        <div class="message stark">
                            <div class="typing typing-1"></div>
                            <div class="typing typing-2"></div>
                            <div class="typing typing-3"></div>
                        </div>
                    </div>
                    <div class="input">
                        <form action="#" class="chat-form">
                            <input id="chat-input" autocomplete="off" placeholder="메시지를 입력해주세요!" type="text" onkeypress="handleKeyPress(event)">
                            <i class="fa-solid fa-paper-plane" id="chat-submit"></i>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        
        
	<!-- https://github.com/sockjs/sockjs-client -->
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script>
		
		
			const cmNo = "${cmNo}";
			const contextPath = "${contextPath}";



			
			console.log(cmNo);
			
	
			const chattingSock = new SockJS(contextPath+"/chat");
	





	</script>
	 <script src="${contextPath}/resources/js/chat/chatting.js"></script>
	 
	<script>





	</script>

</body>

