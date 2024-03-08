function validateName() {
    const regName = /^[가-힣]{2,10}$|^[a-zA-Z]{2,10}$/;
    const nameInput = $("#employee-name");
    const nameCheck = $("#name-check");
  
    if (regName.test(nameInput.val())) {
      nameCheck.text('유효한 형식입니다');
      nameCheck.css('color', 'var(--green)');
      nameCheck.css('marginLeft', '230px');
      return true;
    } else if (nameInput.val() == '') {
      nameCheck.text('한글 또는 영문으로 2-10글자 이내로 작성해 주세요');
      nameCheck.css('color', 'var(--gray400)');
      nameCheck.css('marginLeft', '30px');
      return false;
    } else {
      nameCheck.text('잘못된 형식입니다');
      nameCheck.css('color', 'red');
      nameCheck.css('marginLeft', '230px');
      return false;
    }
  }
  
  function validateSsn() {
    const regSsn = /\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-][1-6]\d{6}/;
    const ssnInput = $("#employee-ssn");
    const ssnCheck = $("#ssn-check");
  
    const jumin = ssnInput.val();
    if (jumin.length === 6) {
      ssnInput.val(jumin + '-');
    }
  
    if (regSsn.test(ssnInput.val())) {
      ssnCheck.text('유효한 형식입니다');
      ssnCheck.css('color', 'var(--green)');
      ssnCheck.css('marginLeft', '170px');
      return true;
    } else if (ssnInput.val() == '') {
      ssnCheck.text('하이픈(-)포함 14자 이내로 작성해주세요');
      ssnCheck.css('color', 'var(--gray400)');
      ssnCheck.css('marginLeft', '30px');
      return false;
    } else {
      ssnCheck.text('잘못된 형식입니다');
      ssnCheck.css('color', 'red');
      ssnCheck.css('marginLeft', '170px');
      return false;
    }
  }
  
  function validateTel() {
    const regTel = /\b010-\d{3,4}-\d{4}\b/;
    const telInput = $("#employee-tel");
    const telCheck = $("#tel-check");
  
    const phone = telInput.val();
    if (phone.length === 3) {
      telInput.val(phone + '-');
    }
  
    if (regTel.test(telInput.val())) {
      telCheck.text('유효한 형식입니다');
      telCheck.css('color', 'var(--green)');
      telCheck.css('marginLeft', '160px');
      return true;
    } else if (telInput.val() == '') {
      telCheck.text('하이픈(-)포함 010- 으로 시작해 주세요');
      telCheck.css('color', 'var(--gray400)');
      telCheck.css('marginLeft', '30px');
      return false;
    } else {
      telCheck.text('잘못된 형식입니다');
      telCheck.css('color', 'red');
      telCheck.css('marginLeft', '160px');
      return false;
    }
  }
  
  function validateEmail() {
    const regEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    const emailInput = $("#employee-email");
    const emailCheck = $("#email-check");
  
    if (regEmail.test(emailInput.val())) {
      emailCheck.text('유효한 형식입니다');
      emailCheck.css('color', 'var(--green)');
      emailCheck.css('marginLeft', '148px');
      return true;
    } else if (emailInput.val() == '') {
      emailCheck.text('@포함 이메일 형식으로 작성해 주세요');
      emailCheck.css('color', 'var(--gray400)');
      emailCheck.css('marginLeft', '30px');
      return false;
    } else {
      emailCheck.text('잘못된 형식입니다');
      emailCheck.css('color', 'red');
      emailCheck.css('marginLeft', '148px');
      return false;
    }
  }
  
  // input 요소에 이벤트 리스너를 추가합니다.
  $("#employee-name").on("input", validateName);
  $("#employee-ssn").on("input", validateSsn);
  $("#employee-tel").on("input", validateTel);
  $("#employee-email").on("input", validateEmail);
  
  // 폼 제출 시 유효성 검사를 수행합니다.
  function memberUpdateCheck() {
    const isNameValid = validateName();
    const isSsnValid = validateSsn();
    const isTelValid = validateTel();
    const isEmailValid = validateEmail();
  
    if (!isNameValid) {
        Swal.fire('이름을 올바르게 적어주세요');
    } else if (!isSsnValid) {
        Swal.fire('주민번호를 올바르게 적어주세요');
    } else if(!isTelValid) {
        Swal.fire('전화번호를 올바르게 적어주세요');
    } else if(!isEmailValid) {
        Swal.fire('이메일을 올바르게 적어주세요');
    }

    return isNameValid && isSsnValid && isTelValid && isEmailValid;
  }




  // 카카오 api
   // 우편번호 찾기 찾기 화면을 넣을 element
   var element_wrap = document.getElementById('wrap');

   function foldDaumPostcode() {
       // iframe을 넣은 element를 안보이게 한다.
       element_wrap.style.display = 'none';
   }

   function sample3_execDaumPostcode() {
       // 현재 scroll 위치를 저장해놓는다.
       var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
       new daum.Postcode({
           oncomplete: function(data) {
               // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 각 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var addr = ''; // 주소 변수
               

               //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
               if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                   addr = data.roadAddress;
               } else { // 사용자가 지번 주소를 선택했을 경우(J)
                   addr = data.jibunAddress;
               }

               

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               
               document.getElementById("sample3_address").value = addr;
               // 커서를 상세주소 필드로 이동한다.
               document.getElementById("sample3_detailAddress").focus();

               // iframe을 넣은 element를 안보이게 한다.
               // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
               element_wrap.style.display = 'none';

               // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
               document.body.scrollTop = currentScroll;
           },
           // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
           onresize : function(size) {
               element_wrap.style.height = size.height+'px';
           },
           width : '100%',
           height : '100%'
       }).embed(element_wrap);

       // iframe을 넣은 element를 보이게 한다.
       element_wrap.style.display = 'block';
   }



// 직원 검색
const searchBtn = document.getElementById("search-btn");
const searchInput = document.getElementById("search");

searchBtn.addEventListener('click', searchMemberAjax);

searchInput.addEventListener('keyup', (event) => {
    if (event.key === 'Enter') {
        searchMemberAjax();
    }

})

function searchMemberAjax() {
    $.ajax({
        url: "searchMember",
        data: {"search" : searchInput.value},
        type: "GET",
        dataType : "JSON",
        success : function(searchedMem) {

          if(searchedMem === null || searchedMem === undefined){
            Swal.fire('해당하는 사원이 없습니다');
          } else if(searchedMem.secession === 'Y') {
            Swal.fire('이미 퇴사한 사원입니다');
          } else {
            const name = document.getElementById("employee-name"),
              ssn = document.getElementById("employee-ssn"),
              tel = document.getElementById("employee-tel"),
              email = document.getElementById("employee-email"),
              address = document.getElementById("sample3_address"),
              detailAddress = document.getElementById("sample3_detailAddress"),
              jobRadios = document.querySelectorAll("input[name='jobNo']"),
              deptSelect = document.getElementById("dept"),
              teamSelect = document.getElementById("dept-option");
          
  
            // 모든 input 요소의 readOnly 해제
            const inputElements = document.querySelectorAll('input');
            inputElements.forEach((input) => {
                input.readOnly = false;
                input.disabled = false;
            });
    
            // 모든 select 요소의 disabled 해제
            const selectElements = document.querySelectorAll('select');
            selectElements.forEach((select) => {
                select.disabled = false;
            });  
    
            // 모든 버튼 disabled 해제
            const btnElements = document.querySelectorAll('button');
            btnElements.forEach((btn) => {
                btn.disabled = false;
            })
            
    
            name.value = searchedMem.memName;
            ssn.value = searchedMem.memRNo;
            tel.value = searchedMem.memTel;
            email.value = searchedMem.memEmail;
            address.value = searchedMem.memAddress.split(",")[0]; // 첫 번째 주소
            if(searchedMem.memAddress.split(",")[1] === undefined) {
              detailAddress.value = "";
            } else {
              detailAddress.value = searchedMem.memAddress.split(",")[1]; // 두 번째 주소
            }
    
    
            
            // 직책 선택
            for (let i = 0; i < jobRadios.length; i++) {
                if (jobRadios[i].value === searchedMem.jobNo.toString()) {
                    jobRadios[i].checked = true;
                    break;
                }
            }
            
            // 부서 선택
            deptSelect.value = searchedMem.deptNo.toString();
            
            // 부서에 따라 팀 옵션 설정
            deptSelect.addEventListener('change', function() {
            const selectOption = document.querySelector("select[name='teamNo']");
            selectOption.innerHTML = ""; // 기존 옵션 제거
    
            const deptNo = parseInt(this.value); // 문자열을 정수로 변환
    
            if (deptNo === 1) {
                selectOption.innerHTML += '<option value="11">재무팀</option>';
                selectOption.innerHTML += '<option value="12">인사총무팀</option>';
            } else if (deptNo === 2) {
                selectOption.innerHTML += '<option value="21">영업팀</option>';
                selectOption.innerHTML += '<option value="22">기술개발팀</option>';
            } else if (deptNo === 3) {
                selectOption.innerHTML += '<option value="31">공사팀</option>';
                selectOption.innerHTML += '<option value="32">공무팀</option>';
                selectOption.innerHTML += '<option value="33">견적팀</option>';
                selectOption.innerHTML += '<option value="34">자재팀</option>';
            } else if (deptNo === 4) {
                selectOption.innerHTML += '<option value="41">개발기획팀</option>';
                selectOption.innerHTML += '<option value="42">분양홍보팀</option>';
                selectOption.innerHTML += '<option value="43">설계기획팀</option>';
            } else if (deptNo === 5) {
                selectOption.innerHTML += '<option value="51">경영팀</option>';
                selectOption.innerHTML += '<option value="52">전략팀</option>';
            }
            });
          
    
            // 초기화
            const deptNoSelect = document.querySelector("select[name='deptNo']");
            deptNoSelect.dispatchEvent(new Event('change'));
              
            
            teamSelect.value = searchedMem.teamNo.toString();
    
            // 멤버 번호를 폼의 action URL에 추가하여 설정
            const updateForm = document.getElementById("update-form");
            updateForm.setAttribute("action", "update?memNo=" + searchInput.value);
          
          }

       
        
    },
    error : function() {
        console.log("에러");
    }
    
})
}

