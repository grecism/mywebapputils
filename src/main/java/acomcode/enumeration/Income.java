package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum Income {
	IN_1000_LESS(1, "1000元以下"),
	IN_1000_3000(2, "1000-3000元"),
	IN_3000_5000(3, "3000-5000元"),
	IN_5000_7000(4, "5000-7000元"),
	IN_7000_10000(5, "7000-10000元"),
	IN_10000_MORE(6, "10000元以上");
	
	private int code;
	private String msg;

	private Income(int code, String msg) {
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
		for(Income value : Income.values()){
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
		for(Income value : Income.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
