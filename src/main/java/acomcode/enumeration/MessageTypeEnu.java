package acomcode.enumeration;

/**
 * 
 *<p>Title	: MessageTypeEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:41:00
 */
public enum MessageTypeEnu {
	Regverify(0, "注册校验"), Upateloginpwd(1, "修改登录密码 "),Updatetradepwd(2,"修改交易密码"),
	/**放款确认方式**/
	ConfirmLoanPwd(1,"交易密码方式确认"), ConfirmLoanVerification(2,"验证码方式确认"),
	/**消息推送返回**/
	PushAuditMessageSuccess(1,"审核成功"),PushLoanMessageSuccess(2,"借款成功"),PushLoanMessageFail(3,"借款失败");
	private int code;
	private String msg;

	private MessageTypeEnu(int code, String msg) {
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
