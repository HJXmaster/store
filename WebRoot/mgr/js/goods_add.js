function a_home() {
	$.post(Mgr.rootUrl +"queryBtype.do", {

	}, function(data, status) {

		if (status) {
			var list = data.btypeList;

			for (key in list) {
				$('#add_big_tag').append(
						'<option value="' + list[key].btid + '">'
								+ list[key].btname + '</option>');
				
			}

		} else {
			alert('请求失败');
		}

	});
	$("#add_big_tag").change(function(){
		
		var value = $("#add_big_tag").find("option:selected").val();
		$('#add_small_tag').empty();
		
		if (value != "") {
			searchStype(value,'add_small_tag');
		}
	});
	$('#add_bt').click(function() {
		
		$('#add_frame').show();
	});
   $('#cancel_goods').click(function() {
		
		$('#add_frame').hide();
	});
	$("#add_goods").click(function() {
		var cname = $('#add_cname').val();
		var cprice = $('#add_cprice').val();
		var cremain = $('#add_cremain').val();
		var btid = $('#add_big_tag').val();
		var stid = $('#add_small_tag').val();
	
		$.post(Mgr.rootUrl +"addGoods.do", {
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

}

$(document).ready(a_home);
