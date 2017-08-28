var o_json_data;
function mod_requset(value) {
	var k = $(value).attr("data-json");
	$('#mod_oid').val(o_json_data[k].oid);
	$('#mod_oname').val(o_json_data[k].oname);
	$('#mod_oaddress').val(o_json_data[k].oaddress);
	$('#mod_ototalprice').val(o_json_data[k].ototalprice);
	$('#mod_ostime').val(o_json_data[k].ostime);
	$('#mod_oetime').val(o_json_data[k].oetime);
	$('#mod_ophone').val(o_json_data[k].ophone);
	$('#mod_opostcode').val(o_json_data[k].opostcode);
	$('#mod_ostate').val(o_json_data[k].ostate);
	$('#m_mod_box').show();
}

function del_requset(value) {
	var k = $(value).attr("data-json");
	$('#o_delete_id_show').html(o_json_data[k].oid);
	$('#m_del_box').show();
}


function toNonNull(t) {
	if (t == null)
		return "";
	return t;
}

function toNull(t) {
	if (t == null)
		return null;
	t = t.trim();
	if (t == '')
		return null;
	return t;
}
$(document).ready(
		function() {
			
			$.ajaxSetup({
				cache : false
			});

			function showAlldmin() {

				$.get(Mgr.rootUrl +"queryAllOrders.do", {}, function(data, status) {
					if (status) {
						if (data.length > 0) {
							o_json_data=data;
							o_home_init(data);
							o_mod_init(data);
							o_del_init(data);
						}

					} else {
						alert('请求失败');
					}

				});

			}
			Mgr.addEvent(showAlldmin);
			function o_home_init(data) {

				$('#o_home_page').show();
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#body_frame"));
					$("<td >" + data[key].oid + "</td>").appendTo(tr);
					$("<td >" + toNonNull(data[key].oname) + "</td>").appendTo(
							tr);
					$("<td >" + toNonNull(data[key].oaddress) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ototalprice) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].oetime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ophone) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].opostcode) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostate) + "</td>")
							.appendTo(tr);
				}
			}
			function o_mod_init(data) {
		
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#o_mod_body_frame"));
					$("<td >" + data[key].oid + "</td>").appendTo(tr);
					$("<td >" + toNonNull(data[key].oname) + "</td>").appendTo(
							tr);
					$("<td >" + toNonNull(data[key].oaddress) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ototalprice) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].oetime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ophone) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].opostcode) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostate) + "</td>")
							.appendTo(tr);
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='mod_requset(this)' value='"
									+ data[key].oid + "'>修改</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}
			}
			function o_del_init(data) {
				
				for ( var key in data) {
					var tr = $("<tr/>").appendTo($("#o_del_body_frame"));
					$("<td >" + data[key].oid + "</td>").appendTo(tr);
					$("<td >" + toNonNull(data[key].oname) + "</td>").appendTo(
							tr);
					$("<td >" + toNonNull(data[key].oaddress) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ototalprice) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].oetime) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ophone) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].opostcode) + "</td>")
							.appendTo(tr);
					$("<td >" + toNonNull(data[key].ostate) + "</td>")
							.appendTo(tr);
					var tr1 = $("<td > </td>").appendTo(tr);
					var bt = $(
							"<button onclick='del_requset(this)' value='"
									+ data[key].oid + "'>删除</button>")
							.appendTo(tr1);
					bt.attr("data-json", key);
				}
			}

		});
/* 修改 */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});

	$("#o_sure_bt").click(function() {

		var oid = toNull($('#mod_oid').val());
		var oname = toNull($('#mod_oname').val());
		var oaddr = toNull($('#mod_oaddress').val());
		var otlprice = toNull($('#mod_ototalprice').val());
		var ostime = toNull($('#mod_ostime').val());
		var oetime = toNull($('#mod_oetime').val());
		var ophone = toNull($('#mod_ophone').val());
		var opostcode = toNull($('#mod_opostcode').val());
		var ostate = toNull($('#mod_ostate').val());

		$.post(Mgr.rootUrl +"updateOrders.do", {
			oid : oid,
			oname : oname,
			oaddress : oaddr,
			ototalprice : otlprice,
			ostimes : ostime,
			oetimes : oetime,
			ophone : ophone,
			opostcode : opostcode,
			ostate : ostate
		}, function(data, status) {

			dowith(data, status);

		});

	});

	$('#o_cancel_bt').click(function() {

		$("#m_mod_box").hide();
	});
	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl + "order.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});
/* 删除 */
$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});

	$("#o_delete_bt").click(function() {

		var admin_ser = $('#o_delete_id_show').text();

		$.post(Mgr.rootUrl +"deleteOrdersById.do", {
			oid : admin_ser
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
				window.location.href = Mgr.mgrUrl + "order.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});