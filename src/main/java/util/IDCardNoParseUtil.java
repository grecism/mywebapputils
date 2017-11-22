package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import thirdparty.security.Encrypt;

/**
 * 
 *<p>Title	: IDCardNoParseUtil</p>
 * @Description	: 身份证号码解析 根据身份证计算年龄和生日
 * @author	: admin
 * @date	: 2017年11月22日上午9:37:42
 */
public class IDCardNoParseUtil {
	
	
	public static final Map<String, String> provinceMap = new HashMap<>();
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	static{
		provinceMap.put("11", "北京");
        provinceMap.put("12", "天津");
        provinceMap.put("13", "河北");
        provinceMap.put("14", "山西");
        provinceMap.put("15", "内蒙古");
        provinceMap.put("21", "辽宁");
        provinceMap.put("22", "吉林");
        provinceMap.put("23", "黑龙江");
        provinceMap.put("31", "上海");
        provinceMap.put("32", "江苏");
        provinceMap.put("33", "浙江");
        provinceMap.put("34", "安徽");
        provinceMap.put("35", "福建");
        provinceMap.put("36", "江西");
        provinceMap.put("37", "山东");
        provinceMap.put("41", "河南");
        provinceMap.put("42", "湖北");
        provinceMap.put("43", "湖南");
        provinceMap.put("44", "广东");
        provinceMap.put("45", "广西");
        provinceMap.put("46", "海南");
        provinceMap.put("50", "重庆");
        provinceMap.put("51", "四川");
        provinceMap.put("52", "贵州");
        provinceMap.put("53", "云南");
        provinceMap.put("54", "西藏");
        provinceMap.put("61", "陕西");
        provinceMap.put("62", "甘肃");
        provinceMap.put("63", "青海");
        provinceMap.put("64", "宁夏");
        provinceMap.put("65", "新疆");
        provinceMap.put("71", "台湾");
        provinceMap.put("81", "香港");
        provinceMap.put("82", "澳门");
        provinceMap.put("91", "国外");
	}

	/**
	 * 根据身份证号码计算年龄
	 * @param idCardNo
	 * @return
	 */
	public static Integer IDCardNoToAge(String idCardNo){
		int length = idCardNo.length();
		String dates = "";
		if(length == 18){
			dates = idCardNo.substring(6,10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new Date());
			int u = Integer.parseInt(year)-Integer.parseInt(dates);
			return u ;
		}else{
			if(length == 15){ ///???
				dates = idCardNo.substring(6,8);
				return Integer.parseInt(dates);
			}else{
				return 0;
			}
		}
	}
	
	/**
	 * 根据身份证号码计算性别
	 * 性别（0女，1男，2默认未填写）
	 * @param idCardNo
	 * @return
	 */
	public static Integer IDCardNoToSex(String idCardNo){
		int length = idCardNo.length();
		if(length == 18){
//			return (idCardNo.charAt(17) & 1);
			String sCardNum = idCardNo.substring(16, 17);
	        if (Integer.parseInt(sCardNum) % 2 != 0) {
	            return 1;
	        } else {
	            return 0;
	        }
		}
		return 2;
	}
	/**
	 * 根据身份证号码计算出生日期
	 * @param idCardNo
	 * @return
	 */
	
	public static Date IDCardNoToBirthday(String idCardNo){
		idCardNo = idCardNo.substring(6, 14);
		SimpleDateFormat  df = new SimpleDateFormat("yyyyMMdd"); 
		Date birthday = null;
		try {
			birthday = df.parse(idCardNo);
			return birthday;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static final Date convertStringToDate(String aMask, String strDate)  
            throws ParseException {  
		strDate=strDate.substring(6, 14);
        SimpleDateFormat df = null;  
        Date date = null;  
        df = new SimpleDateFormat(aMask);  
  
        try {  
            date = df.parse(strDate);
        } catch (ParseException pe) {  
            throw pe;  
        }  
        return (date);  
    }  
	public static final int NO_LENGTH = 18;
	/**
	 * 校验18位身份证
	 * @param idCardNo
	 * @return
	 */
	public static boolean isValid(String idCardNo){
		if(idCardNo == null || idCardNo.length() != 18){
			return false;
		}
		char[] code = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
		char[] cs = idCardNo.toCharArray();
		int coff = 1, sum = 0, modular = 11;
		for(int i = NO_LENGTH - 2; i >= 0; --i){
			if(cs[i] < '0' || cs[i] > '9'){
				return false;
			}
			coff = (coff << 1) % modular;
			sum += coff * (cs[i] - '0');
		}
		sum = sum % modular;
		if(cs[NO_LENGTH - 1] == 'x'){
			cs[NO_LENGTH - 1] = 'X';
		}
		if(cs[NO_LENGTH - 1] != code[sum]){
			return false;
		}
		String bs = idCardNo.substring(6, 14);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date birthday = sdf.parse(bs);
			if(!bs.equals(sdf.format(birthday))){
				return false;
			}
			if(birthday.after(new Date()) || birthday.before(sdf.parse("19000101"))){
				return false;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		if(!provinceMap.containsKey(idCardNo.substring(0, 2))){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取身份证归属地
	 * @param idCardNo
	 * @return
	 */
	public static String getProvince(String idCardNo){
		if(!provinceMap.containsKey(idCardNo.substring(0, 2))){
			return "";
		}else{
			return provinceMap.get(idCardNo.substring(0, 2));
		}
	}

	public static void main(String[] args) throws ParseException {	
//	System.out.println("18位"+isValid("422827199307082034"));
//	System.out.println(IDCardNoParseUtil.isValid("142725199211112020"));
//	System.err.println(IDCardNoToSex("gr2mqo1KvkGs1pf4naLpncbtNHL0S9JQwbjGvNQ2Weg="));
//	System.err.println(IDCardNoToSex("1Z8owGMVuCWledjv+gSuns86Pdh7tlddErFfnVre94Y="));
	try {
		System.out.println(IDCardNoParseUtil.IDCardNoToSex(Encrypt.decryptAES("gr2mqo1KvkGs1pf4naLpncbtNHL0S9JQwbjGvNQ2Weg="))==0?true:false);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	System.err.println(IDCardNoToBirthday("142733199502090317"));
//	System.err.println(convertStringToDate("yyyyMMdd","142733199502090317"));
	}
}
