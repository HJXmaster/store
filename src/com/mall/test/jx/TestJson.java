package com.mall.test.jx;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;

import com.mall.entity.User;

public class TestJson {
	@Test
	public void testJson() throws JsonParseException, JsonMappingException, IOException{
		User user=new User();
		user.setUnickname("大师");
		user.setUphone("18819259357");
		ObjectMapper om=new ObjectMapper();
		//属性为null时不转换为json
		om.setSerializationInclusion(Inclusion.NON_NULL);
		//将对象转换成json字符串
		Writer wr = new StringWriter();
		om.writeValue(wr, user);
		System.out.println(wr.toString());

		//转回对象
		User r = om.readValue(wr.toString(), User.class);
		System.out.println(r.toString());
	}
}
