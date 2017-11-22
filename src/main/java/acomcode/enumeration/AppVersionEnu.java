package acomcode.enumeration;

/**
 * 
 *<p>Title	: AppVersionEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:40:01
 */
public enum AppVersionEnu {

	app_version_v1_0_0("v1.0.0","第一个版本"),
	app_version_v1_0_1("v1.0.1","第二个版本"),
	app_version_1_0_2("1.0.2","第三个版本"),
	app_version_1_0_3("1.0.3","第四个版本"),
	app_version_1_0_4("1.0.4","第五个版本"),
	app_version_1_0_5("1.0.5","第六个版本");
	
	private String code;
	private String msg;
	
	private AppVersionEnu(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
