function c_home() {

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
	function initModGoods(data) {
		$('#mod_cname').val(data.cname);
		$('#mod_cid').val(data.cid);
		$('#mod_cprice').val(data.cprice);
		$('#mod_cremain').val(data.cremain);
		$('#mod_goods_pic').attr('src',Mgr.rootUrl+data.miniPic);
		$('#big_tag').val(data.btid);
		initStype(data.btid);
		setTimeout(function() {			
			$('#small_tag').val(data.stid);
		}, 500);

	}
	function initDelGoods(data) {
		$('#del_cname').val(data.cname);
		$('#del_cid').val(data.cid);
		$('#del_cprice').val(data.cprice);
		$('#del_cremain').val(data.cremain);
		$('#del_goods_pic').attr('src',Mgr.rootUrl+data.miniPic);
		$('#del_btname').val(data.btype);
		$('#del_stname').val(data.stype);

	}

	var c_id = GetQueryString('id');
	var operators = $('#nav_operator a');

	$.post(Mgr.rootUrl +"getGoodsByCid.do", {
		cid : c_id,
	}, function(data, status) {

		if (status) {
			initGoods(data);
			initModGoods(data);
			initDelGoods(data);
		}
	});

	$("#mod_bt").click(function() {
		var cname = $('#mod_cname').val();
		var cid = $('#mod_cid').val();
		var cprice = $('#mod_cprice').val();
		var cremain = $('#mod_cremain').val();
		var btid = $('#big_tag').val();
		var stid = $('#small_tag').val();

		$.post(Mgr.rootUrl +"updateGoods.do", {
			id : cid,
			name : cname,
			price : cprice,
			size : cremain,
			btype : btid,
			stype : stid
		}, function(data, status) {

			if (status) {
				if (data.status)
					window.location.reload();
			} else {
				alert("访问失败");
			}
		});
	});
	$("#del_bt").click(function() {

		var cid = $('#del_cid').val();

		$.post(Mgr.rootUrl +"deleteGoods.do", {
			id : cid,
		}, function(data, status) {

			if (status) {
				if (data.status)
					window.location.href=Mgr.mgrUrl +"goods.html";
			} else {
				alert("访问失败");
			}
		});
	});

}
$(document).ready(c_home);
