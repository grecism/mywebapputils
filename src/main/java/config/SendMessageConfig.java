package config;

public class SendMessageConfig {
	
	/**
	 * 短信配置账号
	 */
	public static final String USR="xique";
	/**
	 * 短信配置密码
	 */
	public static final String PWD="qwer1234";
	/**
	 * 短信配置用户号
	 */
	public static final String SRC="130";
	/**
	 * 标识get方式请求
	 */
	public static final String METHOD_GET="GET";
	/**
	 * 标识post方式请求
	 */
	public static final String METHOD_POST="POST";
	/**
	 * 标识营销渠道短信通道
	 */
	public static final String CHANNEL_ONE="1";
	/**
	 * 标识验证码渠道短信通道
	 */
	public static final String CHANNEL_TWO="2";
	/**
	 * 验证码渠道短信发送地址
	 */
	public static final String VERIFICATIONCODE_CHANNEL="http://push.realsms.cn:38811/api/v10/send";
	/**
	 * 营销渠道短信发送地址
	 */
	public static final String MARKETING_CHANNEL="http://push3.realsms.cn:38812/api/v10/send";

}
