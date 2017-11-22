/**   
* @Title: XsjfURL.java 
* @Package com.hello.config 
* @Description: 向上金服接口配置文件
* @author fanjianmin@xiangshang360.com   
* @date 2017年10月14日 下午5:54:58 
* @version V1.0   
*/
package config;

/**
 * 
 *<p>Title	: XsjfURL</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:39:40
 */
public class XsjfURL {
	
	/**
	 * 开户授权发送短信验证码接口名称
	 */
	public static final String XSJF_SEND_MESSAGE_URL="hd.send.message";
	/**
	 * 借款人开户接口名称
	 */
	public static final String XSJF_BORROW_ACCOUNT_CREATE_URL="hd.borrow.account.create";
	/**
	 * 借款人账户查询接口名称
	 */
	public static final String XSJF_BORROW_ACCOUNT_SEARCH_URL="hd.borrow.account.search";
	/**
	 * 借款人更换银行卡接口名称
	 */
	public static final String XSJF_BORROW_BANK_BIND_URL="hd.borrow.bank.bind";
	/**
	 * 校验借款人是否为理财人接口名称
	 */
	public static final String XSJF_BORROW_USER_CHECK_URL="hd.borrow.user.check";
	/**
	 * 校验借款人是否已经开过户
	 */
	public static final String XSJF_BORROW_VERDICT_OPEN_URL="hd.borrow.user.account.check";
	/**
	 * 担保人账户查询接口名称
	 */
	public static final String XSJF_ASSU_ACCOUNT_SEARCH_URL="hd.assu.account.search";
	/**
	 * 担保人费用提现接口名称
	 */
	public static final String XSJF_ASSU_ACCOUNT_WITHDRAW_URL="hd.assu.account.withdraw";
	/**
	 * 额度申请接口名称
	 */
	public static final String XSJF_LOAN_QUOTA_APPLY_URL="hd.loan.quota.apply";
	/**
	 * 进件调配完成通知接口名称
	 */
	public static final String XSJF_LOAN_ALLOCATION_FINISH_URL="hd.loan.allocation.finish";
	/**
	 * 查询进件列表接口名称
	 */
	public static final String XSJF_LOAN_BASE_SEARCH_URL="hd.loan.base.search";
	/**
	 * 查询进件详细信息接口名称
	 */
	public static final String XSJF_LOAN_DETAIL_SEARCH_URL="hd.loan.detail.search";
	/**
	 * 查询进件附件信息接口名称
	 */
	public static final String XSJF_LOAN_ATTACH_SEARCH_URL="hd.loan.attach.search";
	/**
	 * 放款提现通知接口名称
	 */
	public static final String XSJF_BORROW_ACCOUNT_WITHDRAWALS_NOTICE_URL="hd.borrow.account.withdrawals.notice";
	/**
	 * 放款提现查询接口名称
	 */
	public static final String XSJF_BORROW_ACCOUNT_WITHDRAWALS_SEARCH_URL="hd.borrow.account.withdrawals.search";
	/**
	 * 查询放款电子签章附件信息接口名称
	 */
	public static final String XSJF_LOAN_SIGNATURE_SEARCH_URL="hd.loan.signature.search";
	/**
	 * 查询还款计划信息接口名称
	 */
	public static final String XSJF_LOAN_REFUNDPLAN_SEARCH_URL="hd.loan.refundplan.search";
	/**
	 * 还款通知接口名称
	 */
	public static final String XSJF_REPAYMENT_NOTICE_URL="hd.repayment.notice";
	/**
	 * 查询提现结果接口名称
	 */
	public static final String XSJF_ACCOUNT_WITHDRAW_SEARCH_URL="hd.account.withdraw.search";
	/**
	 * 请求API接口名称
	 */
	public String xsjf_req_method;
	/**
	 * 接口请求签名 对API输入参数进行 md5 加密获得
	 */
	public String xsjf_req_sign;
	/**
	 * 接口请求内容 请求内容密文，明文为json格式
	 */
	public String xsjf_req_content;
	/**
	 * 接口请求时间戳 时间戳，格式为yyyy-mm-dd HH:mm:ss，例如：2016-11-07 13:52:03。服务端允许客户端请求时间误差为10分钟。
	 */
	public String xsjf_req_timestamp;
	/**
	 * 接口调用返回码-成功访问接口标识
	 */
	public static final String XSJF_RESP_CODE_21001="21001";
	/**
	 * 接口调用返回码-验签通过标识
	 */
	public static final String XSJF_RESP_CODE_31001="31001";
	/**
	 * 接口调用返回码-签名验证不通过标识
	 */
	public static final String XSJF_RESP_CODE_41001="41001";
	/**
	 * 接口调用返回码-访问接口失败标识
	 */
	public static final String XSJF_RESP_CODE_41002="41002";
	/**
	 * 接口调用返回码-校验不通过标识
	 */
	public static final String XSJF_RESP_CODE_41003="41003";
	/**
	 * 接口调用返回码-商户不存在标识
	 */
	public static final String XSJF_RESP_CODE_41004="41004";
	/**
	 * 接口调用返回码-没有对应接口标识
	 */
	public static final String XSJF_RESP_CODE_41005="41005";
	/**
	 * 接口调用返回码-协议版本不支持标识
	 */
	public static final String XSJF_RESP_CODE_41006="41006";
	/**
	 * 接口调用返回码-请求已过期标识
	 */
	public static final String XSJF_RESP_CODE_41007="41007";
	/**
	 * 接口调用返回码-不支持的签名加密标识
	 */
	public static final String XSJF_RESP_CODE_41008="41008";
	/**
	 * 接口调用返回码-没有收到参数标识
	 */
	public static final String XSJF_RESP_CODE_41009="41009";
	/**
	 * 接口响应编码
	 * 21001:成功访问接口；31001:验签通过；41001:签名验证不通过；
	 * 41002:访问接口失败；41003:校验不通过；41004:商户不存在；
	 * 41005:没有对应接口；41006:协议版本不支持；41007:请求已过期；
	 * 41008:不支持的签名加密；41009:没有收到参数
	 */
	public String xsjf_resp_retCode;
	/**
	 * 接口响应信息
	 */
	public String xsjf_resp_message;
	/**
	 * 接口响应方法名
	 */
	public String xsjf_resp_method;
	/**
	 * 接口响应md5标签
	 */
	public String xsjf_resp_sign;
	
	/**
	 * 接口响应数据
	 */
	public String xsjf_resp_data;

	public String getXsjf_req_method() {
		return xsjf_req_method;
	}

	public void setXsjf_req_method(String xsjf_req_method) {
		this.xsjf_req_method = xsjf_req_method;
	}

	public String getXsjf_req_sign() {
		return xsjf_req_sign;
	}

	public void setXsjf_req_sign(String xsjf_req_sign) {
		this.xsjf_req_sign = xsjf_req_sign;
	}

	public String getXsjf_req_content() {
		return xsjf_req_content;
	}

	public void setXsjf_req_content(String xsjf_req_content) {
		this.xsjf_req_content = xsjf_req_content;
	}

	public String getXsjf_req_timestamp() {
		return xsjf_req_timestamp;
	}

	public void setXsjf_req_timestamp(String xsjf_req_timestamp) {
		this.xsjf_req_timestamp = xsjf_req_timestamp;
	}

	public String getXsjf_resp_retCode() {
		return xsjf_resp_retCode;
	}

	public void setXsjf_resp_retCode(String xsjf_resp_retCode) {
		this.xsjf_resp_retCode = xsjf_resp_retCode;
	}

	public String getXsjf_resp_message() {
		return xsjf_resp_message;
	}

	public void setXsjf_resp_message(String xsjf_resp_message) {
		this.xsjf_resp_message = xsjf_resp_message;
	}

	public String getXsjf_resp_method() {
		return xsjf_resp_method;
	}

	public void setXsjf_resp_method(String xsjf_resp_method) {
		this.xsjf_resp_method = xsjf_resp_method;
	}

	public String getXsjf_resp_sign() {
		return xsjf_resp_sign;
	}

	public void setXsjf_resp_sign(String xsjf_resp_sign) {
		this.xsjf_resp_sign = xsjf_resp_sign;
	}

	public String getXsjf_resp_data() {
		return xsjf_resp_data;
	}

	public void setXsjf_resp_data(String xsjf_resp_data) {
		this.xsjf_resp_data = xsjf_resp_data;
	}

}
