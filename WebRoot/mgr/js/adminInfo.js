$(document).ready(function() {

	$.get(Mgr.mgrUrl+"queryAdmin.do", {}, function(data, status) {
		if (status) {
			home_init(data);
		} else {
			alert('请求失败');
		}
	});
	
	function home_init(data){
		$('#admin_bigname').html(data.aname);
		$('#admin_id').html(data.aid);
		$('#admin_aname').html(data.aname);
		$('#admin_type').html(data.atype);
	}

});