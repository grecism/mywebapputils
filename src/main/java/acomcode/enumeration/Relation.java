package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *<p>Title	: Relation</p>
 * @Description	: 与本人关系
 * @author	: admin
 * @date	: 2017年11月22日上午9:27:17
 */
public enum Relation {
	parent(1, "父母"),
	spouse(2, "配偶"),
	brother(3, "子女"),
	sister(4, "兄弟姐妹"),
	other(5, "其他");
	
	private int code;
	private String msg;
	
	private Relation(int code, String msg) {
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
		for(Relation value : Relation.values()){
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
		for(Relation value : Relation.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
