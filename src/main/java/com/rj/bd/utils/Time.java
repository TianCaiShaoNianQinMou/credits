package com.rj.bd.utils;
/**
 * @desc 格式化时间
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public static String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s.trim());
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
}
