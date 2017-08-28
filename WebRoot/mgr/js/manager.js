var json_data;
function mod_requset(value) {
	var k = $(value).attr("data-json");
	$('#admin_mod_aid').val(json_data[k].aid);
	$('#admin_mod_aname').val(json_data[k].aname);
	//$('#admin_mod_password').val(json_data[k].apassword);
	$('#admin_mod_atype').val(json_data[k].atype);
	$('#m_mod_box').show();
}

function del_requset(value) {
	var k = $(value).attr("data-json");
	$('#m_delete_id_show').html(json_data[k].aid);
	$('#m_del_box').show();
}

$(document).ready(
		function() {
		
			$.ajaxSetup({
				cache : false
			});

			function showAlldmin() {

				$.get(Mgr.mgrUrl + "queryAdminInfo.do", {},
						function(data, status) {
							if (status) {
								if (data.length > 0) {
									json_data = data;
									home_init(data);
									mod_init(data);
									del_init(data);
								}

							} else {
								alert('请求失败');
							}
						});

			}
			Mgr.addEvent(showAlldmin);
			function home_init(data) {

				$('#m_home_page').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#body_frame"));
					$("<td >" + data[key].aid + "</td>").appendTo(tr);
					$("<td >" + data[key].aname + "</td>").appendTo(tr);
					$("<td >" + data[key].atype + "</td>").appendTo(tr);
				}

			}

			function mod_init(data) {

				//$('#m_mod').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#mod_body_frame"));
					$("<td >" + data[key].aid + "</td>").appendTo(tr);
					$("<td >" + data[key].aname + "</td>").appendTo(tr);
					$("<td >" + data[key].atype + "</td>").appendTo(tr);
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='mod_requset(this)' value='"
									+ data[key].aid + "'>修改</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
			function del_init(data) {

				//$('#m_del').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#del_body_frame"));
					$("<td >" + data[key].aid + "</td>").appendTo(tr);
					$("<td >" + data[key].aname + "</td>").appendTo(tr);
					$("<td >" + data[key].atype + "</td>").appendTo(tr);
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $("<button onclick='del_requset(this)' value='"
									+ data[key].aid + "'>删除</button>").appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
		});
/* add */
$(document).ready(function() {

	$.ajaxSetup({
		cache : false
	});
	$("#add_bt").click(function() {

		var admin_ser = $('#admin_add_aname').val();
		var pass_ser = $('#admin_add_password').val();
		var a_type = $('#admin_add_atype').val();
		$.post(Mgr.mgrUrl +  "addAdmin.do", {
			aname : admin_ser,
			apassword : pass_ser,
			atype : a_type
		}, function(data, status) {
			dowith(data, status);
		});

	});

	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl + "manager.html";
			else {
				$('#add_error-message').html(data.msg);
				$('#add_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});
/* modify */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});
	$("#mod_sure_bt").click(function() {
		var admin_aid = $('#admin_mod_aid').val();
		var admin_ser = $('#admin_mod_aname').val();
		var pass_ser = $('#admin_mod_password').val();
		var admin_mod_atype = $('#admin_mod_atype').val();
		$.post(Mgr.mgrUrl + "updateAdmin.do", {
			aid : admin_aid,
			aname : admin_ser,
			apassword : pass_ser,
			atype : admin_mod_atype
		}, function(data, status) {
			dowith(data, status);
		});

	});

	$('#mod_cancel_bt').click(function() {

		$("#m_mod_box").hide();
	});
	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl + "manager.html";
			else {
				$('#mod_error-message').html(data.msg);
				$('#mod_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});
/* delete */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});

	$("#m_delete_bt").click(function() {

		var admin_ser =$('#m_delete_id_show').text();

		$.post(Mgr.mgrUrl +"deleteAdmin.do", {
			aid : admin_ser
		}, function(data, status) {

			dowith(data, status);

		});

	});

	$('#del_cancel_bt').click(function() {
		$('#m_del_box').hide();
	});
	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href =Mgr.mgrUrl + "manager.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});