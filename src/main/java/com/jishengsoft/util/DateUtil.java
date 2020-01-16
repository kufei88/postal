package com.jishengsoft.util;

import java.text.SimpleDateFormat;

public class DateUtil {
	public static String getNow(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long start = System.currentTimeMillis(); 
		return sdf.format(start);
	}
}
