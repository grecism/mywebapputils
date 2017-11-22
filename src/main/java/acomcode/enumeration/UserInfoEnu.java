package acomcode.enumeration;

/**
 * 
 *<p>Title	: UserInfoEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:42:15
 */
public enum UserInfoEnu {
	User_Init(0,"初始状态") , User_Log(1,"注册状态")  , User_Real(2,"实名状态");
	
	private int code;
	private String msg;

	private UserInfoEnu(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
