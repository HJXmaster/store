document.write('<script src="js/data.js"><\/script>');

function no_login_jmp(){
	window.location.href="login.html";
}

$(document).ready(function() {
        var arrayList =[];	    
		$.ajaxSetup({cache:false}); 
		$.get(Mgr.mgrUrl +"checkLogin.do", {
		}, function(data, status) {
			
			if(status){
				if(data.status){
					initLogin(data.username);
					executeEvent();
				}else{
					no_login_jmp();
				}       
			}else{
				alert('请求失败');				
			}
			 
		});
	
	 function executeEvent(){
	    	for(var k in arrayList){
	    		arrayList[k]();
	    	}
	 }
	  Mgr.addEvent=function (e){
    	 arrayList.push(e);
     };
     
     function initLogin(username){
        $('#user_name').html(username);
     	$('#login_span').toggle();
     	$('#user_span').toggle();
   
     }
     
     $('#logout_bt').click(function(){
    	 $.ajaxSetup ({ cache: false });
    	 $.get(Mgr.mgrUrl +"logoutAdmin.do", {
 		}, function(data, status) {
 			
 			if(status){
 				 window.location.href=Mgr.mgrUrl +'index.html'; 				
 			}else{
 				alert('请求失败');				
 			}
 			 
 		});
    	 return false;
     });

});