package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *<p>Title	: Duration</p>
 * @Description	: 居住时长枚举类
 * @author	: admin
 * @date	: 2017年11月22日上午9:26:46
 */
public enum Duration {
	/* 1-6个月 */
	one_six_month(1, "1-6个月 "),
	/* 6－12个月 */
	six_twelve_month(2, "6－12个月 "),
	/* 1-2年 */
	one_two_year(3, "1-2年"),
	/* 3年及以上 */
	three_year_and_more(4, "3年及以上");

	private int code;
	private String msg;

	private Duration(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public static String getValue(int code){
		for(Duration value : Duration.values()){
			if(value.getCode() == code){
				return value.getMsg();
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @Description : 将所有值转成Json对象返回
	 * @return
	 */
	public static JSONArray getValues(){
		JSONArray array = new JSONArray();
		for(Duration value : Duration.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
