package acomcode.enumeration;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *<p>Title	: SaaSEnu</p>
 * @Description	: SaaS 第三方请求渠道号
 * @author	: admin
 * @date	: 2017年11月21日下午8:41:29
 */
public enum SaaSEnu {
	wechat(0, "微信"), ios(1, "IOS客户端"), android(2, "Android客户端"), wap(4, "wap网页");

	private int code;
	private String msg;

	private SaaSEnu(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static String getValue(int code) {
		for (SaaSEnu value : SaaSEnu.values()) {
			if (value.getCode() == code) {
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
	public static JSONArray getValues() {
		JSONArray array = new JSONArray();
		for (SaaSEnu value : SaaSEnu.values()) {
			JSONObject json = new JSONObject();
			json.put("sid", Integer.toString(value.getCode()));
			json.put("name", value.getMsg());
			array.add(json);
		}
		return array;
	}
}
