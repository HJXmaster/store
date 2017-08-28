$(document).ready(function() {

	$("#login_button").click(function() {
		
		var username = $('#username').val();
		var password = $('#password').val();
		$.get(Mgr.mgrUrl +"loginAdmin.do", {
			aid : username,
			apassword : password
		}, function(data, status) {
			
			if(status){
				if(data.status)
					//window.location.href=Mgr.mgrUrl +"index.html";
					if(document.referrer=="")
						window.location.href=Mgr.mgrUrl +"index.html";
					else
						history.back();
					
				else{
					$('#error-message').html(data.msg);
					$('#error_span').show();
				}
					
			}else{
				alert('请求失败');				
			}
			 
		});

	});

});