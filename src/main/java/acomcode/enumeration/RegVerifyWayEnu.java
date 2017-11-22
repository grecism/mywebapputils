package acomcode.enumeration;

/**
 * 
 *<p>Title	: RegVerifyWayEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:41:16
 */
public enum RegVerifyWayEnu {
	 Direct_Reg(0, "直接注册"), Verify_Code_Reg(1, "验证码方式校验注册");

	private int code;
	private String msg;

	private RegVerifyWayEnu(int code, String msg) {
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
