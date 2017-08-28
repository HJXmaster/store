﻿/*
*
* JQUERY 之简易放大镜插件
* Author:翅膀的初衷 4585839
* Date:2011-11-16
*
*/
(function($){
    $.fn.JNMagnifier=function(setting){
    
		if(setting&&setting.renderTo){
			if(typeof(setting.renderTo)=="string"){
				setting.renderTo = $(setting.renderTo);
			}
		}else{
			return;
		}
		var _img_org_ = this.children("img");
		_img_org_.css("cursor","pointer");

		var __w = 0;
		var __h = 0;
		
		var __left = this.offset().left;
		var __top = this.offset().top;
		
		if(this.offsetParent())
		{
		    __left+=this.offsetParent().offset().left;
		    __top+=this.offsetParent().offset().top;
		}

		var _move_x = 0;
		var _move_y = 0;
		
		var _val_w = (setting.renderTo.width() / 2);
		var _val_h = (setting.renderTo.height() / 2);

		_img_org_.mouseover(function(){
			setting.renderTo.html('<img src="' + _img_org_.attr("src") + '" style="position:absolute;" id="JNMagnifierrenderToImg" />');
			setting.renderTo.show();
			var timer = setInterval(function(){
				__w = $("#JNMagnifierrenderToImg").width() / _img_org_.width();
				__h = $("#JNMagnifierrenderToImg").height() /_img_org_.height();
				if(__w>0){
					clearInterval(timer);
				}
			},100);
		});
		
		_img_org_.mouseout(function(){
			setting.renderTo.hide();
		});
		
		_img_org_.mousemove(function(e){
			_move_x =0-Math.round((document.documentElement.scrollLeft+e.clientX-__left) * __w - _val_w);
			_move_y =0-Math.round((document.documentElement.scrollTop+e.clientY-__top) * __h - _val_h);
			$("#JNMagnifierrenderToImg").css({"left":_move_x + "px ","top":_move_y + "px"});
		});
	}
})(jQuery);