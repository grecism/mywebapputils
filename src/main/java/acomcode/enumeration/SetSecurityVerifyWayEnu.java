package acomcode.enumeration;

/**
 * 
 *<p>Title	: SetSecurityVerifyWayEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:41:56
 */
public enum SetSecurityVerifyWayEnu {
	Verify_Oldpwd(1, "验证旧密码方式确认"), Verify_Code(2, "验证码方式确认");

	private int code;
	private String msg;

	private SetSecurityVerifyWayEnu(int code, String msg) {
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
