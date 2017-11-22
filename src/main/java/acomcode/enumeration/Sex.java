package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum Sex {
	s0(0, "未填写 "),
	s1(1, "女"),
	s2(2, "男");
	
	private int code;
	private String msg;
	
	private Sex(int code, String msg) {
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
		for(Sex value : Sex.values()){
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
		for(Sex value : Sex.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
