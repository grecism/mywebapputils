package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum CompanyType {
	t1(1, "水利、环境和公共设施管理业"),
	t2(2, "居民服务和其他服务业"),
	t3(3, "教育"),
	t4(4, "卫生、社会保障和社会福利业"),
	t5(5, "文化、体育和娱乐业"),
	t6(6, "公共管理和社会组织"),
	t7(7, "国际组织"),
	t8(8, "农、林、牧、渔业"),
	t9(9, "采掘业"),
	t10(10, "制造业"),
	t11(11, "电力、燃气及水的生产和供应业"),
	t12(12, "建筑业"),
	t13(13, "交通运输、仓储和邮政业"),
	t14(14, "信息传输、计算机服务和软件业"),
	t15(15, "批发零售业"),
	t16(16, "住宿餐饮业"),
	t17(17, "金融业"),
	t18(18, "房地产业"),
	t19(19, "租赁和商务服务业"),
	t20(20, "个体户"),
	t21(21, "其他");

	
	private int code;
	private String msg;

	private CompanyType(int code, String msg) {
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
		for(CompanyType value : CompanyType.values()){
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
		for(CompanyType value : CompanyType.values()){
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
