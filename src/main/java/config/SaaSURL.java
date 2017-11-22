package config;

import java.io.InputStream;
import java.util.Properties;

import util.HttpsRequest;

public class SaaSURL {

	private static String SAAS_CONFIG_FILE_LOCATION = "saasConfig.properties";

	public static String URL;
	public static String SAAS_URL_AFTER;
	public static String CREDIT_STATUS_URL;
	public static String CREDIT_LIST_URL;
	public static String CREDIT_DATA_URL;
	

	public static String SAAS_B;
	public static String SAAS_CH;
	public static String SAAS_AK;
	public static String SAAS_SK;
	public static String SX_AK;
	public static String SX_SK;
    public static final String SAAS_C="2";//自动任务调用默认"android"

	public static String APP_DOWNLOAN_URL;

	public static String APP_GET_ACTIVITY_URL;
	public static String APP_TO_ACTIVITY_REGISTER_URL;
	public static String APP_ACTIVITY_TITLE;
	public static String APP_ACTIVITY_SHAREDESC;
	public static String APP_ACTIVITY_SHAREURL;
	public static String APP_ACTIVITY_IMAGEURL;
	
	static {
		Properties properties = new Properties();
		try {
			InputStream in = HttpsRequest.class.getClassLoader().getResourceAsStream(SAAS_CONFIG_FILE_LOCATION);
			properties.load(in);
			URL = properties.getProperty("SAAS.URL");
			SAAS_URL_AFTER=properties.getProperty("SAAS.URL.AFTER");
			CREDIT_STATUS_URL = properties.getProperty("SAAS.CREDIT_STATUS_URL");
			CREDIT_LIST_URL = properties.getProperty("SAAS.CREDIT_LIST_URL");
			SAAS_B = properties.getProperty("SAAS.HTTPS.REQUEST.SUFFIX.B");
			SAAS_CH = properties.getProperty("SAAS.HTTPS.REQUEST.SUFFIX.CH");
			SAAS_AK = properties.getProperty("SAAS.AK");
			SAAS_SK = properties.getProperty("SAAS.SK");
			CREDIT_DATA_URL = properties.getProperty("SAAS.CREDIT_DATA_URL");
			SX_AK = properties.getProperty("SX.AK");
			SX_SK = properties.getProperty("SX.SK");
			APP_DOWNLOAN_URL = properties.getProperty("APP.DOWNLOAN.URL");
			APP_GET_ACTIVITY_URL = properties.getProperty("APP.GET_ACTIVITY.URL");
			APP_TO_ACTIVITY_REGISTER_URL = properties.getProperty("APP.TO_ACTIVITY_REGISTER.URL");
			APP_ACTIVITY_TITLE   =   properties.getProperty("APP.ACTIVITY.TITLE");
			APP_ACTIVITY_SHAREDESC = properties.getProperty("APP.ACTIVITY.SHAREDESC");
			APP_ACTIVITY_SHAREURL =  properties.getProperty("APP.ACTIVITY.SHAREURL");
			APP_ACTIVITY_IMAGEURL =  properties.getProperty("APP.ACTIVITY.IMAGEURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3.2.1 申请发送短信验证码
	 */
	public static final String SMS_SENDCODE = "/userinfo/sendcode";
	/**
	 * 3.2.2 用户注册接口
	 */
	public static final String USER_REGISTER = "/userinfo/register";
	/**
	 * 3.2.3 用户登录接口
	 */
	public static final String USER_LOGIN = "/userinfo/login";
	/**
	 * 3.2.4 修改登录密码
	 */
	public static final String UPDATELOGINPWD = "/userinfo/updateloginpwd";
	/**
	 * 3.2.5 设置交易密码
	 */
	public static final String SETTRANSPWD = "/userinfo/settranspwd";
	/**
	 * 3.2.6 注册登录合并接口（密码方式）
	 */
	public static final String REGISTERANDLOGIN = "/userinfo/registerandlogin";
	/**
	 * 3.2.7 实名接口
	 */
	public static final String AUTHUSER = "/userinfo/authuser";
	/**
	 * 3.2.8 直接开户接口（注册实名，首次调用默认登录）
	 */
	public static final String BASEACCOUNT = "/userinfo/baseaccount";
	/**
	 * 3.3.1 用户基础信息汇总接口
	 */
	public static final String BASEINFO = "/useraccount/baseinfo";
	/**
	 * 3.3.2 用户账户信息汇总接口
	 */
	public static final String SUMMARY = "/useraccount/summary";
	/**
	 * 3.3.3 用户账单列表
	 */
	public static final String LOANLIST = "/useraccount/loanlist";
	/**
	 * 3.3.4 借款账单详情
	 */
	public static final String LOANDETAIL = "/useraccount/loandetail";
	/**
	 * 3.3.5 还款记录列表
	 */
	public static final String REPAYMENTLIST = "/useraccount/repaymentlist";
	/**
	 * 3.4.1 获取银行卡卡 BIN 信息
	 */
	public static final String CARDBIN = "/bankcard/cardbin";
	/**
	 * 3.4.2 查询支持银行列表
	 */
	public static final String SUPPORTBANKS = "/bankcard/supportbanks";
	/**
	 * 3.4.3 发起添加银行卡
	 */
	public static final String COMMITINFO = "/bankcard/commitinfo";
	/**
	 * 3.4.4 提交手机验证码
	 */
	public static final String COMMITVERIFYCODE = "/bankcard/commitverifycode";
	/**
	 * 3.4.5 申请重发验证码
	 */
	public static final String RESENDVERIFYCODE = "/bankcard/resendverifycode";
	/**
	 * 3.4.6 查询银行卡状态
	 */
	public static final String STATUS = "/bankcard/status";
	/**
	 * 3.4.7 用户银行卡列表
	 */
	public static final String BANKCARDLIST = "/bankcard/bankcardlist";
	/**
	 * 3.4.8 删除银行卡
	 */
	public static final String DELETE = "/bankcard/delete";
	/**
	 * 3.4.9 添加银行卡
	 */
	public static final String ADDBANKCARD = "/bankcard/addbankcard";
	/**
	 * 3.5.1 页面授信
	 */
	public static final String Credit_PAGE = CREDIT_LIST_URL + "/html/newlist.html";
	/**
	 * 3.5.2 查询用户授信状态
	 */
	public static final String QUERYBASE = "/gold/hio/queryBase";
	/**
	 * 3.5.3 提交用户实名和授信数据
	 */
	public static final String UPLOADMESSAGE = "/uploadMessage";
	/**
	 * 3.5.4 获取用户原始授信数据
	 */
	public static final String QUERY = "/data/credit/v1/query";
	/**
	 * 3.5.5 上传用户补充资料
	 */
	public static final String REPORT = "/suckerfish/data/ka/v1/report";
	/**
	 * 3.5.4.6 身份认证照片
	 */
	public static final String USERCARD_QUERY = "/data/credit/v1/query";
	/**
	 * 3.6.1 获取用户贷前信息
	 */
	public static final String PRELOANINFO = "/loan/preloaninfo";
	/**
	 * 3.6.2 提交借款申请
	 */
	public static final String APPLYLOAN = "/loan/applyloan";
	/**
	 * 3.6.3 确认放款
	 */
	public static final String CONFIRM = "/loan/confirm";
	/**
	 * 3.6.3.1 提交确认放款验证码
	 */
	public static final String SUBMITVERIFYCODE = "/loan/submitverifycode";
	/**
	 * 3.6.3.2 重发确认放款验证码
	 */
	public static final String LOAN_RESENDVERIFYCODE = "/loan/resendverifycode";
	/**
	 * 3.6.4 查询借款状态
	 */
	public static final String QUERYSTATUS = "/loan/querystatus";
	/**
	 * 3.6.5 查询产品信息
	 */
	public static final String PRODUCTLIST = "/loan/productlist";
	/**
	 * 3.6.6 上传交易补充信息
	 */
	public static final String UPLOADSCENEINFO = "/loan/uploadsceneinfo";
	/**
	 * 3.6.7 取消订单
	 */
	public static final String CANCEL = "/loan/cancel";
	/**
	 * 3.7.1 申请还款接口
	 */
	public static final String APPLY = "/repayment/apply";
	/**
	 * 3.7.2 确认还款（提交交易密码/短信验证码）
	 */
	public static final String REPAYMENT_DO = "/repayment/do";
	/**
	 * 3.7.3 重发还款验证码
	 */
	public static final String REPAYMENT_RESENDVERIFYCODE = "/repayment/resendverifycode";
	/**
	 * 3.7.4 查询还款状态
	 */
	public static final String REPAYMENTSTATUS = "/repayment/repaymentstatus";
	/**
	 * 3.8 消息推送接口
	 */

	/**
	 * 3.9.1 对账信息上报接口
	 */
	public static final String ACCOUNTCHECK_UPLOAD = "/accountcheck/upload";
}
