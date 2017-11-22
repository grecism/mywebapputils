package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 *<p>Title	: DateUtil</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月22日上午9:37:01
 */
public class DateUtil {
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_REPLACE = "yyyy.MM.dd";
	public static final String HH_MM_SS = "HH:mm:ss";

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String HHMMSS = "HHmmss";
	public static final String YYYYNMMYDDR = "yyyy年MM月dd日";
	public static final String MMYDDR = "MM月dd日";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final int TIME_CONSTANTS = 1000*3600*24;
	
	/**
	 * 
	 * @Description : 获取当前时间的unix时间戳
	 * @return int
	 */
	public static int unixTimestamp(){
		return (int)(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 
	 * @Description : 计算endDate - startDate的分钟数
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	public static long subMinute(long startDate, long endDate) {
		return (endDate - startDate) / 60000;
	}

	/**
	 * 
	 * @Description : 判断date是否为符合format格式的日期
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static boolean isDate(String dateStr, String pattern){
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			format.parse(dateStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Description : 时间加法
	 * @param date 日期
	 * @param incr 增加，也就是要增加的数量，可以为负值，负值表示减去
	 * @param c c y/Y年，M月，d日，H/h时，m分,s秒
	 * @return
	 */
	public static long add(Date date, int incr, char c) {
		Calendar cal = Calendar.getInstance();
		int type = -1;
		switch(c){
		case 'y':
		case 'Y': type = Calendar.YEAR;break;
		case 'M': type = Calendar.MONTH;break;
		case 'd': type = Calendar.DAY_OF_YEAR;break;
		case 'h':
		case 'H': type = Calendar.HOUR_OF_DAY;break;
		case 'm': type = Calendar.MINUTE;break;
		case 's': type = Calendar.SECOND;break;
		}
		if(type == -1){
			throw new RuntimeException("Illigal type");
		}
		cal.add(type, incr);
		return cal.getTimeInMillis();
	}
	
	/**
	 * long类型的时间转String
	 * @param longtime
	 * @return
	 */
	public static String longToDateStr(long time,String format){
		return new SimpleDateFormat(format).format(new Date(time));
	}
	
	/**
	 * 当前日期加上天数后的日期，并转为long型
	 * @param num 为增加的天数
	 * 
	 * @return
	 */
	public static Long plusDay(int num) {
		return add(new Date(), num, 'd');
	}
	
	/**
	 * 
	 * @Description : 判断是否为今日
	 * @param time
	 * @return
	 */
	public static boolean isToday(long time) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTimeInMillis(time);
		return year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) && day == c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * @Description : 将日期对象转换为format格式的字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 
	 * @Description :  将字符串对象转换为日期对象
	 * @param source
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String source, String format){
		try {
			return new SimpleDateFormat(format).parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal fomat \" " + format + "\" for string:" + source);
		}
	}

	/**
	 * 
	 * @Description : 将Unix时间戳转为日期字符串
	 * @param time
	 * @param format
	 * @return
	 */
	public static String unixTimestampToStr(int time,String format){
		return longToDateStr(time * 1000L, format);
	}

	/**
	 * 
	 * @Description : 将Unix时间戳转为日期字符串 yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String unixTimestampToStr(int time){
		return longToDateStr(time * 1000L, YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 
	 * @Description : 两个秒级时间戳相差几天
	 * 返回值=0：同一天，>0:end时间靠后，<0,end时间靠前
	 * 
	 * @param start 秒级时间戳
	 * @param end 秒级时间戳
	 * @return
	 */
	public static int diffDays(int start, int end){
		String startStr = unixTimestampToStr(start,YYYY_MM_DD);//2017-03-03
		Date StartDate = stringToDate(startStr, YYYY_MM_DD);
		String endStr = unixTimestampToStr(end,YYYY_MM_DD);
		Date endDate = stringToDate(endStr, YYYY_MM_DD);
		int days = (int) ((endDate.getTime() - StartDate.getTime()) / (TIME_CONSTANTS));
		return days;
	}
	
	/**
	 * 
	 * @Description :  获取当天0点时刻的Unix时间戳
	 * @return
	 */
	public static int getZeroTimestamp(){
		Calendar zero = Calendar.getInstance();  
		zero.set(Calendar.HOUR_OF_DAY, 0);  
		zero.set(Calendar.MINUTE, 0);  
		zero.set(Calendar.SECOND, 0);
        return (int)(zero.getTimeInMillis() / 1000);
	}

	/**
	 * 
	 * @Description : 获得10位时间戳
	 * @param time
	 * @return
	 */
	public static int getMilliesTime(long time){
		return (int) (time / 1000);
	}


}
