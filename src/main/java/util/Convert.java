package util;

import java.math.BigDecimal;

public class Convert {
	public static int strToInt(String str, int defaultValue) {
		int value = defaultValue;
		try {
			value = Integer.parseInt(str);
		} catch (Exception localException) {
//			localException.printStackTrace();
		}
		return value;
	}

	public static long strToLong(String str, long defaultValue) {
		long l = defaultValue;
		try {
			l = Long.parseLong(str);
		} catch (Exception localException) {
//			localException.printStackTrace();
		}
		return l;
	}

	public static float strToFloat(String str, float defaultValue) {
		float value = defaultValue;
		try {
			value = Float.parseFloat(str);
		} catch (Exception localException) {
//			localException.printStackTrace();
		}
		return value;
	}

	public static double strToDouble(String str, double defaultValue) {
		double d = defaultValue;
		try {
			d = Double.parseDouble(str);
		} catch (Exception localException) {
//			localException.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 
	 * @Description :BigDecimal 转 String剩余两位小数
	 * @param value
	 * @return
	 */
    public static String BigDecimalToString(BigDecimal value){
    	return value == null ? null : value.setScale(2, BigDecimal.ROUND_FLOOR).toString();
    }
}