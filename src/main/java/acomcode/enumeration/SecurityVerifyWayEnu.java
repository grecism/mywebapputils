package acomcode.enumeration;

/**
 * 
 *<p>Title	: SecurityVerifyWayEnu</p>
 * @Description	: 
 * @author	: admin
 * @date	: 2017年11月21日下午8:41:48
 */
public enum SecurityVerifyWayEnu {
	 Direct_Set(0, "直接设置"), Verify_Oldpwd(1, "验证旧密码方式确认（暂未开放） "),Update_Tradepwd(2,"修改交易密码");

	private int code;
	private String msg;

	private SecurityVerifyWayEnu(int code, String msg) {
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
