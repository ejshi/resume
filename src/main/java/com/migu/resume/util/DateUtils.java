package com.migu.resume.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期处理工具类
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月2日 下午2:30:54
 * @version: V1.0
 */
public class DateUtils {
	// 默认显示的日期格式(年-月-日)
	public static final String DATAFORMAT_STR = "yyyy-MM-dd";
	// 默认显示日期时间的格式(年-月-日 时:分:秒)
	public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";
	private static DateFormat dateFormat = null;
	private static DateFormat dateTimeFormat = null;

	static {
		dateFormat = new SimpleDateFormat(DATAFORMAT_STR);
		dateTimeFormat = new SimpleDateFormat(DATATIMEF_STR);
	}

	private static DateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATAFORMAT_STR)) {
			return dateFormat;
		} else if (formatStr.equalsIgnoreCase(DATATIMEF_STR)) {
			return dateTimeFormat;
		} else {
			return new SimpleDateFormat(formatStr);
		}
	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = getDateFormat(formatStr);
			sdf.setLenient(false);//严格按照标准方法进行转换
			java.util.Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将日期格式为"yyyy-mm-dd hh:mm:ss"转化为Date类型
	 * 参数dateTimeStr必须是"yyyy-mm-dd hh:mm:ss"的形式
	 */
	public static Date getDateByYYMMDDHHMMSS(String dateTimeStr) {
		return getDate(dateTimeStr, DATATIMEF_STR);
	}
	
	/**
	 * 将日期格式为"yyyy-mm-dd"转化为Date类型
	 * 参数dateTimeStr必须是"yyyy-mm-dd"的形式
	 */
	public static Date getDateByYYMMDD(String dateTimeStr) {
		return getDate(dateTimeStr, DATAFORMAT_STR);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”
	 */
	public static String dateToDateTimeString(Date date) {
		return dateToDateString(date, DATATIMEF_STR);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd”
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, DATAFORMAT_STR);
	}
	
	/**
	 * 将Date转换成formatStr格式的字符串
	 */
	public static String dateToDateString(Date date, String formatStr) {
		if(date != null){
			DateFormat df = getDateFormat(formatStr);
			return df.format(date);
		}else{
			return "";
		}
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 */
	public static String getCurrentDate() {
		// return dateToDateString(new Date(),DATAFORMAT_STR);
		return dateToDateString(Calendar.getInstance().getTime(),
				DATAFORMAT_STR);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 */
	public static String getCurrentDateTime() {
		return dateToDateString(new Date(), DATATIMEF_STR);
	}
	
	/**
	 * 获取Date类型的年
	 */
	public static int getYear(Date date) {
		Calendar dd = Calendar.getInstance();
		dd.setTime(date);
		int year = dd.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获取Date类型的月
	 */
	public static int getMonth(Date date) {
		Calendar dd = Calendar.getInstance();
		dd.setTime(date);
		int month = dd.get(Calendar.MONTH)+1;
		return month;
	}
	
	/**
	 * 获取Date类型的日
	 */
	public static int getDay(Date date) {
		Calendar dd = Calendar.getInstance();
		dd.setTime(date);
		int day = dd.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * 
	 * 方法描述:一个月的第一天
	 * 方法创建时间:2013-8-30 下午4:18:28
	 * @author 赵麟宇
	 * @param date YYMMDD格式的字符串
	 * @return
	 */
	public static Date getMonthFirstDay(String date) {  
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(getDateByYYMMDD(date));
	    calendar.set(Calendar.DAY_OF_MONTH, calendar      
	            .getActualMinimum(Calendar.DAY_OF_MONTH));      
	    return calendar.getTime();      
	}      
    
	/**
	 * 
	 * 方法描述:一个月的最后一天
	 * 方法创建时间:2013-8-30 下午4:20:30
	 * @author 赵麟宇
	 * @param date YYMMDD格式的字符串
	 * @return
	 */
	public static Date getMonthLastDay(String date) {      
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(getDateByYYMMDD(date));
	    calendar.set(Calendar.DAY_OF_MONTH, calendar      
	            .getActualMaximum(Calendar.DAY_OF_MONTH));      
	    return calendar.getTime();      
	} 
}
