package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	/**
	 * 
	 * @Description : 手机号验证 
	 * @param str
	 * @return 验证通过返回true 
	 */
	 public static boolean isMobile(final String str) {  
	     Pattern p = null;  
	     Matcher m = null;  
	     boolean b = false;  
	     p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
	     m = p.matcher(str);  
	     b = m.matches();  
	     return b;  
	 }
	
	public static String mobileMask(String mobile) {
		if (mobile == null) {
			return null;
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}
	
	public static String idCardMask(String idCard) {
		if (idCard == null) {
			return null;
		}
		return idCard.replaceAll("(\\d{10})\\d{4}(\\w{4})", "$1****$2");
	}
	
	public static String idCardNoMask(String cardNo) {
		if (cardNo == null) {
			return null;
		}
		return cardNo.replaceAll("(\\d{4})\\d{4}\\d{4}(\\w{4})", "$1**** ****$2");
	}
	 
}
