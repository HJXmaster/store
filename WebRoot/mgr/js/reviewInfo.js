function request_del(bt) {
	$('#r_del_bt').val(bt.value);
	$('#r_del_frame').show();
}
function r_home() {
  
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	function initGoods(data) {
		$('#c_name').html(data.cname);
		$('#c_id').html(data.cid);
		$('#c_price').html(data.cprice);
		$('#c_remain').html(data.cremain);
		$('#goods_pic').attr('src',Mgr.rootUrl+data.miniPic);
		if (data.btype != null) {
			$('#btype_content').show();
			$('#c_btname').html(data.btype);
		}
		if (data.stype != null) {
			$('#stype_content').show();
			$('#c_stname').html(data.stype);
		}

	}

	function initReview(list) {
		for ( var k in list) {
			
			var data = list[k];
			var html = '<div>' + '<div class="r_title">'
					+ '<span class="username">' + data.username
					+ '</span><span>' + data.time + '</span>'
					+ '<button class="r_delete_bt" value="' + data.id
					+ '" onclick="request_del(this);">删除</button>' + '</div>'
					+ '<div class="text">' + data.text + '</div>' + '</div>';
			$('#review_content').append(html);
		}
	}

	var c_id = GetQueryString('id');

	$.post(Mgr.rootUrl +"getGoodsByCid.do", {
		cid : c_id,
	}, function(data, status) {

		if (status) {
			initGoods(data);

		} else {
			alert('请求失败');
		}
	});

	$.post(Mgr.rootUrl +"queryReview.do", {
		cid : c_id,
	}, function(data, status) {

		if (status) {
			if (data.list&&data.list.length>0)
				initReview(data.list);
			else{
				$('#no_review').show();
			}
		} else {
			alert('请求失败');
		}
	});

	
	$('#r_del_bt').click(function() {

		$.post(Mgr.rootUrl +"deleteReview.do", {
			id : $('#r_del_bt').val()
		}, function(data, status) {

			if (status) {
				if (data.status) {
					window.location.reload();
				}

			} else {
				alert('请求失败');
			}
		});
	});
	$('#r_cancel_bt').click(function() {

		$('#r_del_frame').hide();
	});
}
$(document).ready(r_home);
