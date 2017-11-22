package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *<p>Title	: Degree</p>
 * @Description	: 学历枚举类
 * @author	: admin
 * @date	: 2017年11月22日上午9:25:47
 */
public enum Degree {
	/*初中及以下*/
	middle_school_and_less(1, "初中及以下"),
	/*高中*/
	high_school(2, "高中"),
	/*专科*/
	college_degree(3, "专科"),
	/*本科学士*/
	bachelor(4, "本科"),
	/*硕士及以上*/
	postgraduate_and_more(5, "硕士及以上");
	
	private int code;
	private String msg;

	private Degree(int code, String msg) {
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
		for(Degree value : Degree.values()){
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
		for(Degree value : Degree.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
