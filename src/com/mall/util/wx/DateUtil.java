package com.mall.util.wx;

import java.util.ArrayList;
import java.util.List;

public class DateUtil {

	// 返回值是一个List，第一个参数是Map，存放着12个月的日期名，第二个参数是String[]，存放着12个月后拼接的日期
	public List<String[]> getNearlyYear() {
		java.util.Date date = new java.util.Date();

		// -----------------------------------------------------------------
		// 当前月，年
		int monthTemp = date.getMonth() + 1;
		int yearTemp = date.getYear() + 1900;

		// 一年
		// LinkedHashMap按插入顺序的
		int[] yearArr = new int[12];
		int[] monthArr = new int[12];
		String[] concatDateArr = new String[12];
		String[] dateNameArr = new String[12];
		for (int i = 12; i > 0; i--) {
			if (monthTemp <= 0) {
				// 当月份到0时，重置为12，年份-1
				monthTemp = 12;
				yearTemp--;
				yearArr[12 - i] = yearTemp;
				monthArr[12 - i] = monthTemp;
				dateNameArr[12 - i] = yearTemp + "年" + monthTemp + "月";
				monthTemp--;

			} else {
				yearArr[12 - i] = yearTemp;
				monthArr[12 - i] = monthTemp;
				dateNameArr[12 - i] = yearTemp + "年" + monthTemp + "月";
				monthTemp--;

			}
		}
		for (int i = 0; i < 12; i++) {
			if (monthArr[i] < 10) {
				concatDateArr[i] = yearArr[i] + "-0" + monthArr[i] + "-" + "01";
			} else {
				concatDateArr[i] = yearArr[i] + "-" + monthArr[i] + "-" + "01";
			}
		}

		List<String[]> list = new ArrayList<String[]>();
		list.add(0, dateNameArr);
		list.add(1, concatDateArr);
		
		return list;
	}
}
