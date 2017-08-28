var u_json_data;
function u_del_requset(value) {
	var k = $(value).attr("data-json");
	$('#u_delete_id_show').html(u_json_data[k].uid);
	$('#m_del_box').show();
}

function toNonNull(t) {
	if (t == null)
		return "";
	return t;
}

$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});

	function showAlldmin() {

		$.get(Mgr.mgrUrl  + "queryAllUser.do", {}, function(data, status) {
			if (status) {
				if (data.length > 0) {
					u_json_data=data;
					u_home_init(data);
					u_del_init(data);
				}

			} else {
				alert('请求失败');
			}
		});

	}
	Mgr.addEvent(showAlldmin);
	function u_home_init(data) {

		$('#u_home_page').show();
		for ( var key in data) {
			var tr = $("<tr/>").appendTo($("#body_frame"));
			$("<td >" +toNonNull( data[key].uid) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].unickname) + "</td>").appendTo(tr);
			$("<td >" +toNonNull( data[key].uemail) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uaddress) + "</td>").appendTo(tr);
			$("<td >" +toNonNull( data[key].uphone) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].upostcode) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uidcard) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uname )+ "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uquestion) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uanswer )+ "</td>").appendTo(tr);
			
		}

	}

	function u_del_init(data) {
		
		for ( var key in data) {
			var tr = $("<tr/>").appendTo($("#u_del_body_frame"));
			$("<td >" +toNonNull( data[key].uid) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].unickname) + "</td>").appendTo(tr);
			$("<td >" +toNonNull( data[key].uemail) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uaddress) + "</td>").appendTo(tr);
			$("<td >" +toNonNull( data[key].uphone) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].upostcode) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uidcard) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uname )+ "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uquestion) + "</td>").appendTo(tr);
			$("<td >" + toNonNull(data[key].uanswer )+ "</td>").appendTo(tr);
			var tr1 = $("<td > </td>").appendTo(tr);
			var bt = $(
					"<button onclick='u_del_requset(this)' value='"
							+ data[key].aid + "'>删除</button>")
					.appendTo(tr1);
			bt.attr("data-json", key);

		}

	}
});

$(document).ready(function() {
	
	$.ajaxSetup({
		cache : false
	});

	$('#del_cancel_bt').click(function() {

		$("#m_del_box").hide();
	});
	$("#u_delete_bt").click(function() {

		var admin_ser = $('#u_delete_id_show').text();

		$.post(Mgr.mgrUrl + "deleteUser.do", {
			uid : admin_ser
		}, function(data, status) {

			dowith(data, status);

		});

	});

	function dowith(data, status) {

		if (status) {
			if (data.status)
				window.location.href = Mgr.mgrUrl + "user.html";
			else {
				$('#del_error-message').html(data.msg);
				$('#del_error_span').show();
			}

		} else {
			alert('请求失败');
		}

	}

});