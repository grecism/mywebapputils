package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum Post {
	high_level_manager(1, "高级管理"),
	middle_level_manager(2, "中级管理"),
	basic_level_staff(3, "一般员工"),
	other_post(4, "其它");
	
	private int code;
	private String msg;
	
	private Post(int code, String msg) {
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
		for(Post value : Post.values()){
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
		for(Post value : Post.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
