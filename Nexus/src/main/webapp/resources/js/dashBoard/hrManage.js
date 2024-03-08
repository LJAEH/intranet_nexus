var targetDateValue = document.getElementById("targetDate").value;
var targetDateParts = targetDateValue.split('/'); 

document.getElementById('decreaseBtn').addEventListener("click",function(){
    var currentYear = parseInt(targetDateParts[0], 10); 
    var currentMonth = parseInt(targetDateParts[1], 10)-1; 

    var targetDate = currentYear + "-" + currentMonth;

    hrManage(targetDate);
})

document.getElementById('increaseBtn').addEventListener("click",function(){

    var currentYear = parseInt(targetDateParts[0], 10); 
    var currentMonth = parseInt(targetDateParts[1], 10)+1; 
    
    var targetDate = currentYear + "-" + currentMonth;

    hrManage(targetDate);
})

function hrManage(targetDate){
    var url = "/intranet/dashBoard/humanResourceManageC/" + targetDate; 
    window.location.href = url; 
}