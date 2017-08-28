
function showGoods(id) {

	window.location.href = Mgr.mgrUrl + 'goodsInfo.html?id=' + id;
}


function searchStype(value,st_id) {

	$.post(Mgr.rootUrl +"queryStype.do", {
		btid : value
	}, function(data, status) {

		if (status) {
			var list = data.stypeList;

			if (list.length > 0)
				$('#'+st_id).prepend(
						'<option value="">&nbsp;&nbsp;</option>');
			for (key in list) {

				$('#'+st_id).append(
						'<option value="' + list[key].stid + '">'
								+ list[key].stname + '</option>');
			}

		} else {
			alert('请求失败');
		}

	});
}


function initStype() {
	var value = $("#big_tag").find("option:selected").val();
	$('#small_tag').empty();
	
	if (value != "") {
		searchStype(value,'small_tag');
	}
}
function home() {

	function addGoods(goods) {

		var html = '<div class="product_info" onclick="showGoods(' + goods.cid
				+ ')">' + '<div class="product_img">'
				+ '<img  src="'+Mgr.rootUrl+goods.miniPic+'">' + '</div>' + '<div>'
				+ goods.cid + '：'
				+ goods.cname + '</div>' + '<div>'
				+ '<span>剩余量 <span style="color:green" >' + goods.cremain
				+ '</span></span>' + '</div>' + '<div>'
				+ '<span style="color: red;">¥<span id="money_title">'
				+ goods.cprice + '</span></span>' + '</div>' + '</div>';

		$('#goods_content').append(html);
	}

	function queryGoods() {
		$('#goods_content').empty();
		var bt_id = $("#big_tag").find("option:selected").val();
		var st_id = $("#small_tag").find("option:selected").val();
		var text_ser = $("#search_text").val();
		$.post(Mgr.rootUrl +"search.do", {
			btid : bt_id,
			stid : st_id,
			condition : text_ser,
			currentPage:1,
			pageSize:9999
			
		}, function(data, status) {

			if (status) {
				var list = data;
                var i=0;
				for (;i<list.length-1;i++) {
					addGoods(list[i]);
				}
                if(i<list[list.length-1].totalCount){
                	queryGoods();
                }
			} else {
				alert('请求失败');
			}

		});

	}
	$.post(Mgr.rootUrl +"queryBtype.do", {

	}, function(data, status) {

		if (status) {
			var list = data.btypeList;

			for (key in list) {
				$('#big_tag').append(
						'<option value="' + list[key].btid + '">'
								+ list[key].btname + '</option>');
				
			}

		} else {
			alert('请求失败');
		}

	});

	queryGoods();
	$("#big_tag").change(initStype);
	
	$('#search_bt').click(queryGoods);

}
$(document).ready(home);
