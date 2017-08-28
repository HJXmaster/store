package com.mall.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * date转json，格式转化，使用注解
 * @author admin
 *
 */
public class DateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date date, JsonGenerator json, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format=sdf.format(date);
		json.writeString(format);
	}

}
