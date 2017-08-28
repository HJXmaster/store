package com.mall.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UObjects {
     public static<T> boolean isNull(T t) {
		return t==null;
	 }
     
     public static<T> boolean isNonNull(T t) {
 		return t!=null;
 	 }
     
     public static<T> boolean isNonNullEmpty(String s) {
  		return s!=null&&!s.trim().isEmpty();
  	 }
     
     public static<T> String transferEmptyToNull(String s) {
    	 
    	 if(s==null)
    		 return null;
    	 
    	 s=s.trim();
    	 if(s.isEmpty())
    		 s=null;
    	 
   		return s;
   	 }
     
     
     /**
      * 将对象里面属性值为空（空白）字符串转为null
      * @param t 目标对象
      */
 	public static <T>void transferEmptyToNullInObject(T t) {

 		for (Field field : t.getClass().getDeclaredFields()) {
 			if (!Modifier.isStatic(field.getModifiers())
 					&& field.getType().equals(String.class)) {
 				field.setAccessible(true);
 				try {
 					String s = (String) field.get(t);
 					s=UObjects.transferEmptyToNull(s);
 					// System.out.println(field.getName()+" "+s);
 					field.set(t, s);
 				} catch (IllegalArgumentException e) {
 					//e.printStackTrace();
 				} catch (IllegalAccessException e) {
 					//e.printStackTrace();
 				}
 			}
 		}

 	}
 	/**
 	 * 将date转换成为2017-7-20的格式
 	 * @param d
 	 * @return
 	 */
 	public static String fomateDate(Date d) {
		String s="";
		try {
			s=new SimpleDateFormat("yyyy-MM-dd").format(d);	
		} catch (Exception e) {
		
		}	
 		return s;
	}

	public static Date parse(String ostimes) {
		
		try {
		  return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ostimes);
		} catch (Exception e) {
			 try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ostimes);
			} catch (Exception e1) {
				
			}
		}
		return null;
	}
	public static int getIntervalDays(Date fDate, Date oDate) {

	       if (null == fDate || null == oDate) {

	           return -1;

	       }

	       long intervalMilli = oDate.getTime() - fDate.getTime();

	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	    }
}
