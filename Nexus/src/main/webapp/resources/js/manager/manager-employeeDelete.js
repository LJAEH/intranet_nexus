const deleteBtn = document.getElementById("delete-btn"),
      deleteNo = document.getElementById("delete-no");

deleteBtn.addEventListener("click", () => {
  if(deleteNo.value == '') {
    Swal.fire(
      '사원번호를 입력해주세요',
      '',
      'warning'
    )
  } else {
    $.ajax({
      url : "deleteSearch",
      data : { "memNo" : deleteNo.value },
      type : "POST",
      dataType : "JSON",
      success : function(memserach){
        if(memserach === null || memserach === undefined) {
          Swal.fire('해당하는 사원이 없습니다');
        } else if(memserach.secession === 'Y'){
          Swal.fire('이미 퇴사한 사원입니다');
        } else {
          Swal.fire({
            title: '정말 퇴사처리 하시겠습니까?',
            html: `<div><b style="color:var(--primary400)">이름</b> : ${memserach.memName}</div><br>
            <div><b style="color:var(--primary400)">직책</b> : ${memserach.jobName}</div><br>
            <div><b style="color:var(--primary400)">부서</b> : ${memserach.deptName}</div><br>
            <div><b style="color:var(--primary400)">팀</b> : ${memserach.teamName}</div>`,
            icon: 'warning',
            
            showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
            confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
            cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
            confirmButtonText: '확인', // confirm 버튼 텍스트 지정
            cancelButtonText: '취소', // cancel 버튼 텍스트 지정
            
            reverseButtons: true, // 버튼 순서 거꾸로
            
          }).then(result => {
            if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
              $.ajax({
                url : "delete",
                data : { "memNo" : deleteNo.value },
                type : "POST",
                dataType : "JSON",
                success : function(result) {
                  Swal.fire('퇴사 처리 되었습니다', '', 'success');
                  deleteNo.value = '';
                },
                error : function() {
                  console.log("에러");       
                }
              })
            } else {
              Swal.fire('취소 되었습니다.');
              deleteNo.value = '';
            } 
          })
        }
        
      },
      error : function() {
        console.log("에러");
      }
    })
   
  }

})

