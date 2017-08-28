package com.mall.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.entity.ShoppingCart;
import com.mall.service.UserService;

public class ShoppingCartUtil {
	//从cookie中获得购物车信息
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		ObjectMapper om=new ObjectMapper();
		ShoppingCart shoppingCart=null;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie :cookies){
				if(cookie.getName().equals("shoppingCart")){
					try {
						shoppingCart=om.readValue(URLDecoder.decode(cookie.getValue(),"UTF-8"), ShoppingCart.class);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
		}
		return shoppingCart;
	}
	//把购物车信息写入cookie中
	public static void writeCookie(HttpServletResponse response,ShoppingCart shoppingCart){
		Writer w=new StringWriter();
		try {
			ObjectMapper om=new ObjectMapper();
			om.writeValue(w, shoppingCart);
			Cookie cookie=new Cookie("shoppingCart",URLEncoder.encode(w.toString(),"UTF-8"));
			cookie.setPath("/");
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
