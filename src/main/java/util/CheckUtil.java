package util;

import org.apache.commons.lang3.StringUtils;

public class CheckUtil {
	
	/**
	 * 
	 * @Description : 是否身份证号
	 * @param idNo 身份证号，最后一位为X时，大小写均可
	 * @return
	 */
	public static boolean isIdCarNo(String idNo){
		//空串返回否， 不是18位的返回否
		if(StringUtils.isBlank(idNo) || idNo.length() != 18){
			return false;
		}
		idNo = idNo.toUpperCase();//转大写
		//不符合正则表达式的返回否
		String regex = "^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X)?$";
		if(!idNo.matches(regex)){
			return false;
		}
		
		//生日不正确的，直接返回否
		if(!isBirthDay(idNo)){
			return false;
		}
		
		char lastChar = getLastCode(idNo);
		//最后一位校验码匹配的，返回是
		if(lastChar == idNo.charAt(17)){
			return true;
		}
		return false;
	}
		
	private static boolean isBirthDay(String idNo) {
		try{			
			String birthDay = idNo.substring(6,14);
			DateUtil.stringToDate(birthDay, DateUtil.YYYYMMDD);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private static char getLastCode(String idNo){
		int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 十七位数字本体码权重
		char[] validate = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // mod11,对应校验码字符值

		int sum = 0;
		int mode = 0;
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(String.valueOf(idNo.charAt(i))) * weight[i];
		}
		mode = sum % 11;
		return validate[mode];
	}
	
	/**
	 * 
	 * @Description : 是否手机号
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		//去空格，去连接符
		mobile = mobile.replaceAll("\\s|-", "");

		//去+86
		if(mobile.startsWith("+86")){
			mobile = mobile.substring(3);
		}
		
		//剩下的是不是手机号？
		if (mobile.matches("^1[3,4,5,7,8]\\d{9}$")) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @Description : 判断一个字符串是否为10位时间戳
	 * @param timestamp
	 * @return
	 */
	public static boolean isTimeStamp(String timestamp) {
		return StringUtils.isNotEmpty(timestamp) && timestamp.length() == 10 && timestamp.matches("[0-9]+");
    }

	/**
	 * 
	 * @Description : 判断一个字符串是否位8位十进制随机字符串
	 * @param nonce
	 * @return
	 */
	public static boolean isNonce(String nonce){
        return StringUtils.isNotEmpty(nonce) && nonce.length() == 8 && nonce.matches("[0-9]+");
	}


	/**
	 * 
	 * @Description : 是否是移动手机号
	 * @param mobile
	 * @return
	 */
//	public static boolean isYidong(String mobile) {
//		if (!isMobile(mobile)) {
//			return false;
//		}
//		long count = Stream
//				.of("134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "1703",
//						"1705", "1706", "178", "182", "183", "184", "187", "188")
//				.filter(m -> mobile.startsWith(m)).count();
//		return count > 0;
//	}

	/**
	 * 
	 * @Description : 是否是电信手机号
	 * @param mobile
	 * @return
	 */
//	public static boolean isTelecom(String mobile) {
//		if (!isMobile(mobile)) {
//			return false;
//		}
//		long count = Stream.of("133", "149", "153", "173", "1700", "1701", "1702", "177", "180", "181", "189")
//				.filter(m -> mobile.startsWith(m)).count();
//		return count > 0;
//	}

	/**
	 * 
	 * @Description : 是否是联通手机号
	 * @param mobile
	 * @return
	 */
//	public static boolean isUnicom(String mobile) {
//		if (!isMobile(mobile)) {
//			return false;
//		}
//		long count = Stream.of("130", "131", "132", "145", "155", "156", "1704", "1707", "1708", "1709", "171", "175",
//				"176", "185", "186").filter(m -> mobile.startsWith(m)).count();
//		return count > 0;
//	}
}
