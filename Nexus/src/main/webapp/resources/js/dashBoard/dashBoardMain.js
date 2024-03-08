// 극좌표차트 클릭으로 보여지는 div들
var prDivs = document.querySelectorAll('.projectDiv');
// 오더 초기값 넘버
var orderNo = 0;

$(document).ready(function(){
    // 근태 도넛 ajax
    $.ajax({
        url : 'attnDoughnut',
        type : 'GET',
        success : function(data){

            

            var attnLabel = [];
            var attnCount = [];
            $.each(data, function(index, item) {
                attnLabel.push(item.typeName);
                attnCount.push(item.typeCount);
            });

            const centerText = {
                id : 'centerText',
            }


            var doughnut = document
                .getElementById('doughnut')
                .getContext('2d');
            var myChart = new Chart(doughnut, {
                type: 'doughnut', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    datasets: [
                        { //데이터
                            cutout: "50%", 
                            label: '근태타입', 
                            fill: false, 
                            data: attnCount,
                            backgroundColor: [
                                
                                '#30B893',//초록
                                '#ABD904',//연두
                                '#FF7B89',//분홍
                                '#04A2E4',//하늘
                                '#FBD315',//노랑
                                '#9BA4B1',// 회색
                                '#F20574',//진분홍
                                '#FFB4BF',
                                '#FF979D',
                                '#EDEBF0',  
                                '#04D9D9',
                                '#F25C05',
                            ],
                            borderColor: [
                                //경계선 색상
                                'white',
                                'white',
                                'white',
                                'white',
                                'white',
                                'white',
                                'white',
                            ],
                            borderWidth: 4 //경계선 굵기
                            
                        }
                    ],
                    labels: attnLabel,
                },
                options: {
                    responsive: true,
                    plugins:{ 
                
                        legend: {
                            display: true, // 범례 유무
                            position: 'right', // 범례위치
                            align: 'center', // 범례 정렬
                            labels:{
                                margin : 10, // 범례 패딩
                                font:{size: 15}, // 범례 폰트 사이즈
                                fontColor: 'black',
                            }
                            }
                        },
                },
                maintainAspectRatio :false
            });

        },
        error : function(error){
            console.log(error);
        }


    })

    // 프로젝트 극좌표차트 ajax
    $.ajax({
        url : 'projectPolar',
        type : 'GET',
        success : function(data){


            var projectLabel = [];
            var projectCount = [];
            var projectNo = [];
            $.each(data, function(index, item) {
                projectLabel.push(item.title);
                projectCount.push(item.percent);
                projectNo.push(item.projectNo);
            });
            
            var polarArea = document
            .getElementById('polarArea')
            .getContext('2d');
            var polarChart = new Chart(polarArea, {
                type: 'polarArea', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    labels: projectLabel,
                    datasets: [
                        {
                            data: projectCount,
                            backgroundColor: [
                                //색상
                                '#F67280',
                                '#C06C84',
                                '#6C5B7B',
                                '#355C7D',
                                '#FFF3E2',
                                '#FFE5CA',
                                '#FA9884',
                                '#E74646',
                            ],
                            borderColor: [
                                //경계선 색상
                                'white',

                           ],
                            borderWidth: 2 //경계선 굵기
                            
                        }
                    ],
                },
                options: {

                    plugins:{ 
                        legend: {
                            display: false, // 범례 유무
                            }
                        },
                    onClick: function(point, event){
                        console.log(event);
                        createProjectInfo(projectNo[event[0]['index']]);
                    },
                },
            });
        },
        error : function(error){
            console.log(error);
        }

    })

})



/* =================================================================================================
================================      함수 모음      =================================================
=============================================================================================  ====*/

// 온로드 함수 모음
window.onload = function() {

    // 프로그레스바 밸류값에의한 css 수정
    var progressBars = document.getElementsByClassName('progress');
    for (var i = 0; i < progressBars.length; i++) {
        var progressBar = progressBars[i];
        if (parseInt(progressBar.getAttribute('value')) > parseInt(progressBar.getAttribute('max'))) {
            progressBar.classList.add('overOrange');
        } else if(parseInt(progressBar.getAttribute('value')) < 120 ) {
            progressBar.classList.add('underBlue');
        }
    }

    // 생성된 라디오 버튼들
    var teamRadios = document.querySelectorAll('input[name="team"]');
 
    teamRadios[0].checked = true;
    document.querySelector('#team-' + teamRadios[0].value + '-data').style.display = 'block';
    
    // 에 대하여 이벤트 부여
    for (var i = 0; i < teamRadios.length; i++) {
        teamRadios[i].addEventListener('change', function() {

            // 클릭된 라디오의 밸류 (team.teamNo)
            var selectedTeamNo = this.value;
            var hrDivs = document.querySelectorAll('.team-data');
            
            // 팀넘버에따라 부여된 id로 div 추적/동작
            for (var j = 0; j < hrDivs.length; j++) {
                if (hrDivs[j].id == 'team-' + selectedTeamNo + '-data') {
                    hrDivs[j].style.display = 'block';
                } else {
                    hrDivs[j].style.display = 'none';
                }
            }
        });
    }

    for (var i = 0; i < prDivs.length; i++) {
        prDivs[i].style.display = 'none';
    }
    prDivs[1].style.display='block';
    

    

}


// 극좌표 클릭시 프로젝트/과제 전환
function createProjectInfo(projectNo){
    for(var i = 0; i <prDivs.length; i++){
        if(prDivs[i].id == 'projectDiv-'+projectNo ){
            prDivs[i].style.display = 'block';
        } else {
            prDivs[i].style.display = 'none';
        }
    }
}


