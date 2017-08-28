var json_data;
function mod_requset(value) {
	var k = $(value).attr("data-json");
	$('#st_mod_id').val(json_data[k].stid);
	$('#st_mod_name').val(json_data[k].stname);
	$('#mod_big_tag').val(json_data[k].btid);
	$('#m_mod_box').show();
}

function del_requset(value) {
	var k = $(value).attr("data-json");
	$('#st_delete_id_show').html(json_data[k].stid);
	$('#m_del_box').show();
}

$(document).ready(
		function() {
			
			$.ajaxSetup({
				cache : false
			});

			$.post(Mgr.rootUrl +"queryBtype.do", {
			}, function(data, status) {

				if (status) {
					var list = data.btypeList;

					if (list.length > 0)
						initBtype(list);

				} else {
					alert('请求失败');
				}

			});

			function initBtype(list) {
				
				for (var key in list) {

					$('#add_big_tag').append(
							'<option value="' + list[key].btid + '">'
									+ list[key].btname + '</option>');
					$('#mod_big_tag').append(
							'<option value="' + list[key].btid + '">'
									+ list[key].btname + '</option>');

				}
			}

			function showAlldmin() {

				$.get(Mgr.rootUrl +"queryAllStype.do", {}, function(data, status) {
					if (status) {
						if (data.stypeList.length > 0) {
							json_data = data.stypeList;
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
					$("<td >" + data[key].stid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);
					$("<td >" + data[key].stname + "</td>").appendTo(tr);

				}

			}

			function mod_init(data) {

				// $('#m_mod').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#mod_body_frame"));
					$("<td >" + data[key].stid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);
					$("<td >" + data[key].stname + "</td>").appendTo(tr);
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='mod_requset(this)' value='"
									+ data[key].stid + "'>修改</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
			function del_init(data) {

				// $('#m_del').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#del_body_frame"));
					$("<td >" + data[key].stid + "</td>").appendTo(tr);
					$("<td >" + data[key].btname + "</td>").appendTo(tr);
					$("<td >" + data[key].stname + "</td>").appendTo(tr);

					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='del_requset(this)' value='"
									+ data[key].stid + "'>删除</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}

			}
			$('#jmp_to_btype').click(function() {

				window.location.href =Mgr.mgrUrl + "btype.html";
			});
		});
/* add */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});
	$("#add_bt").click(function() {

		var admin_ser = $('#st_add_name').val();
		var admin_bt = $('#add_big_tag').val();

		$.post(Mgr.rootUrl +"addStype.do", {
			name : admin_ser,
			id:admin_bt
		}, function(data, status) {
			dowith(data, status);
		});

	});

	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl  + "stype.html";
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
		var admin_aid = $('#st_mod_id').val();
		var admin_ser = $('#st_mod_name').val();
		var admin_btid = $('#mod_big_tag').val();

		$.post(Mgr.rootUrl +"updateStype.do", {
			stid : admin_aid,
			stname : admin_ser,
			btid : admin_btid
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
				window.location.href = Mgr.mgrUrl + "stype.html";
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

		var admin_ser = $('#st_delete_id_show').text();

		$.post(Mgr.rootUrl +"deleteStype.do", {
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
				window.location.href = Mgr.mgrUrl +"stype.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});