var json_data;
function mod_requset(value) {
	var k = $(value).attr("data-json");
	$('#bt_mod_id').val(json_data[k].btid);
	$('#bt_mod_name').val(json_data[k].btname);
	$('#m_mod_box').show();
}

function del_requset(value) {
	var k = $(value).attr("data-json");
	$('#bt_delete_id_show').html(json_data[k].btid);
	$('#m_del_box').show();
}

$(document).ready(
		function() {		
			$.ajaxSetup({
				cache : false
			});

			function showAlldmin() {

				$.get( Mgr.rootUrl+"queryBtype.do", {},
						function(data, status) {
							if (status) {
								if (data.btypeList.length > 0) {
									json_data = data.btypeList;
									home_init(json_data);
									mod_init(json_data);
									del_init(json_data);
								}

							} else {
								alert('请求失败');
							}
						});

			}
			Mgr.addEvent(showAlldmin);
			function home_init(data) {

				$('#bs_home_page').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#body_frame"));
					$("<td >" + data[key].btid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);
					
				}

			}

			function mod_init(data) {

				//$('#m_mod').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#mod_body_frame"));
					$("<td >" + data[key].btid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);					
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='mod_requset(this)' value='"
									+ data[key].btid + "'>修改</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
			function del_init(data) {

				//$('#m_del').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#del_body_frame"));
					$("<td >" + data[key].btid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);
					
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $("<button onclick='del_requset(this)' value='"
									+ data[key].btid + "'>删除</button>").appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
			$('#jmp_to_stype').click(function(){
				
				window.location.href=Mgr.mgrUrl +"stype.html";
			});
		});
/* add */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});
	$("#add_bt").click(function() {

		var admin_ser = $('#bt_add_name').val();
		
		$.post(Mgr.rootUrl+"addBtype.do", {
			name : admin_ser,		
		}, function(data, status) {
			dowith(data, status);
		});

	});

	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl + "btype.html";
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
		var admin_aid = $('#bt_mod_id').val();
		var admin_ser = $('#bt_mod_name').val();
	
		$.post( Mgr.rootUrl +"updateBtype.do", {
			btid : admin_aid,
			btname : admin_ser,
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
				window.location.href = Mgr.mgrUrl  + "btype.html";
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

		var admin_ser =$('#bt_delete_id_show').text();

		$.post(Mgr.rootUrl +"deleteBtype.do", {
			id : admin_ser
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
				window.location.href = Mgr.mgrUrl + "btype.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});