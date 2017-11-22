package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum Marriage {
	unmarried(1, "未婚"),
	married(2, "已婚"),
	divorce(3, "离婚"),
	widowed(4, "丧偶");
	
	private int code;
	private String msg;

	private Marriage(int code, String msg) {
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
		for(Marriage value : Marriage.values()){
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
		for(Marriage value : Marriage.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
