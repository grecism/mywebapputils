package util;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
	
	/**
	 * 
	 * @Description : 隐藏手机号，如18600486733，返回186****6733
	 * @param mobile
	 * @return
	 */
	public static String partialDisplayMobile(String mobile){
		if(CheckUtil.isMobile(mobile)){
			return mobile.replaceAll("(^\\d{3})\\d{4}(\\d{4}$)","$1****$2");
		}
		return "";
	}
	
	//long转Int
	public static int longToInt(long longParam){
		long tempLong = longParam/1000;
		int lastInt =  Integer.parseInt(String.valueOf(tempLong));
		return lastInt;
	}
	
	
	public static String partialDisplayIdCardNo(String cardNo){
		if(StringUtils.isBlank(cardNo)){
			return "";
		}
		if(cardNo.length() == 18){			
			return cardNo.replaceAll("(^.{4})(.{10})(.{4}$)","$1******$3");
		}
		
		if(cardNo.length() == 15){
			return cardNo.replaceAll("(^.{4})(.{7})(.{4}$)","$1******$3");
		}
		
		return "";
	}
	
	/**
	 * 
	 * @Description : 身份证号隐藏4位生日
	 * @param cardNo
	 * @return
	 */
	public static String partialDisplayIdCardNo4(String cardNo){
		if(StringUtils.isBlank(cardNo)){
			return "";
		}
		if(cardNo.length() == 18){			
			return cardNo.replaceAll("(^.{10})(.{4})(.{4}$)","$1****$3");
		}
		
		if(cardNo.length() == 15){
			return cardNo.replaceAll("(^.{8})(.{4})(.{3}$)","$1****$3");
		}
		
		return "";
	}
	
	public static String partialDisplayUserRealName(String name){
		if(StringUtils.isBlank(name)){
			return "";
		}
		String out = "$1*$3";
		if(name.length() > 3){
			out = "$1**$3";
		}else if(name.length() < 3){
			out = "$1*";
		}
		return name.replaceAll("(^.{1})(.*)(.{1}$)", out);
	 }
	
	/**
	 * 
	 * @Description : 银行卡号保留前4位后4位
	 * @param cardNo
	 * @return
	 */
	public static String partialDisplayBankCard2(String cardNo){
		return cardNo.replaceAll("(^.{4})(.*)(.{4}$)", "$1 **** **** $3");
	}
	
	/**
	 * 
	 * @Description : 隐藏银行卡号
	 * @param cardNo
	 * @return
	 */
	public static String partialDisplayBankCard(String cardNo) {
		String cardNoStr = "";
		if (StringUtils.isNotBlank(cardNo)) {
			int length = cardNo.length();
			cardNoStr = cardNo.substring(0, 4) + "*******" + cardNo.substring(length - 4);
		}
		return cardNoStr;
	}
	
	/**
	 * 
	 * @Description : 取模运算,根据用户ID取模，现在是20
	 * @param userId
	 * @return
	 */
	public static int moduloByUserId(int userId){
		return userId % 20;
	}
	
	/**
	 * 
	 * @Description : 取模运算,根据用户ID取模，现在是10 用户信息取模10
	 * @param userId
	 * @return
	 */
	public static int moduloForUser(int userId){
		return userId % 10;
	}
   
	/**
	 * 
	 * @Description : 获取用户年龄
	 * @param birthday
	 * @return
	 */
	public static int getUserAge(String birthday) {
		Calendar birthDay = Calendar.getInstance();
		birthDay.setTime(DateUtil.stringToDate(birthday, DateUtil.YYYY_MM_DD));
		Calendar today = Calendar.getInstance();
		if(birthDay.after(today)){
			throw new IllegalArgumentException("生日错误，无法计算未来人的年龄");
		}
		
		int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		if(today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)){
			age -= 1;
		}
		
		return age;
	}
}
