package acomcode.enumeration;

/**
 * 
 *<p>Title	: Code</p>
 * @Description	:
 *  第一位0表示成功，1表示失败；第二位与第三位：模块名称;第四位与第五位表示业务码（该码的真正含义）
 * 全局码 00
 * 注册登录 10
 * 认证 22
 * 绑卡 24
 * 订单 25
 * 邀请好友28
 * 文件上传 30
 * @author	: admin
 * @date	: 2017年11月22日上午9:25:26
 */
public enum Code {
	/*********全局返回码00**************/
	request_success("00000", "请求成功"),
	request_failure("10000", "请求失败"),
	request_illegal("10001", "非法访问"),
	request_time_inconsistency("10002", "请求时间与服务器时间不一致"),
	//非法的token
	request_invalid_token("10003", "登录超时，请重新登录"),
	request_session_expire("10004", "登录会话过期，请重新登录"),
	request_remote_login("100008", "您的账号已经在另一台设备登陆"),
	request_message_send_failure("10006", "发送失败"),
	request_illegal_paramter("10007", "请求参数不正确"),
	request_return_err("10008", "响应参数不正确"),
	request_time_format_err("10009","请求时间格式错误"),
	request_processing("10010", "您的请求正在处理"),
	
	/************登录10**************/
	login_mobile_dynamic_is_empty("11000", "手机号或验证码为空"),
	login_mobile_invalid("11001", "手机号输入错误"),
	login_dynamic_invalid("11002", "验证码输入错误"),
	login_dynamic_error("11003", "验证码输入错误"),
	login_user_forbidden("11004", "用户被禁用"),
	login_user_locked("11005", "10分钟发送动态码3次或一天之内发送动态码超过10次"),
	login_failure("11006","登录失败"),
	
	/**********认证22**************/
	auth_user_id_card_first("12211", "请先进行实名认证"),
	auth_user_id_card_failure("12212", "实名认证失败"),
	auth_user_id_card_done("12213","该用户已实名认证"),
	auth_user_info_first("12221", "请先进行用户资料认证"),
	auth_user_info_failure("12222", "用户资料认证失败"),
	auth_user_info_done("12223", "该用户资料已认证"),
	auth_user_mobile_failure("12232", "手机号授权认证失败"),
	auth_user_mobile_busy("12233", "运营商系统繁忙，请稍候重试"),
	auth_user_mobile_pwd_error("12234", "密码不正确"),
	auth_user_mobile_done("12235", "该手机号已授权认证"),
	auth_user_bank_card_failure("12242", "银行卡绑定失败"),
	auth_user_bank_card_done("12243", "该银行卡已绑定"),
	auth_mobile_auth_failure("12214","请先完成手机认证"),
	auth_user_mobile_sms_failure("12215","短信验证码错误"),
	
	/**********上传文件**************/
	file_format_error("13001","上传图片格式错误"),
	/**********app版本**************/
	app_version("13002","您的应用版本太旧，无法完成借款，请到搜狗应用市场下载最新版应用");
	private String code;
	private String msg;
	
	private Code(String code, String msg){
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
