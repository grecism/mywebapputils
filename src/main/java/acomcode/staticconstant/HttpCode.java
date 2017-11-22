package acomcode.staticconstant;

/**
 * 
 *<p>Title	: HttpCode</p>
 * @Description	: HTTP状态码类
 * @author	: admin
 * @date	: 2017年11月22日上午9:27:49
 */
public class HttpCode {

	/****************************** 公用状态码 ******************************/
	/**
	 * 成功Response
	 */
	public static final int HTTP_OK = 0;
	public static final int HTTP_FAIL = 1;

	/**
	 * 失败Response
	 */
	public static final int HTTP_ERROR = 201;

	/**************************** 三方平台状态码 **********************************/
	public static final int HTTP_SAAS_OPT_FAST = 12; // req 参数为 null
	public static final int HTTP_SAAS_PARA_NULL = 100002; // req 参数为 null
	public static final int HTTP_SAAS_PARA_PARA_NULL = 100003;// 部分参数为空
	public static final int HTTP_SAAS_SYS_BUSY = 100004;// 系统繁忙，请稍后重试
	public static final int HTTP_SAAS_USER_LOGOUT = 100005;// 用户已退出登录
	public static final int HTTP_SAAS_OPT_LIMIT = 100006;// 操作受限
	public static final int HTTP_SAAS_LOCK_FAIL = 100007;// 加锁失败
	public static final int HTTP_SAAS_TOKEN_INVALID = 100008;// token 失效，请重新登录
	public static final int HTTP_SAAS_MOBILE_FORMAT_ERR = 201001;// 手机号格式不正确
	public static final int HTTP_SAAS_CODE_SEND_FAIL = 201002;// 验证码发送失败
	public static final int HTTP_SAAS_MOBILE_REG = 201003;// 手机号已注册，请更换其它手机号
	public static final int HTTP_SAAS_MOBILE_NOTEXIT = 201004;// 手机号不存在，请重新输入
	public static final int HTTP_SAAS_MOBILE_INTERVAL = 201005;// 验证码 60
																// 秒内不能再次发送，请稍后再试
	public static final int HTTP_SAAS_MOBILE_CODE_ERR = 202001;// 您填写的手机验证码有误，请重新填写
	public static final int HTTP_SAAS_PASS_FORMAT_ERR = 202002;// 密码格式不正确
	public static final int HTTP_SAAS_MOBILE = 202003;// 该手机号已注册
	public static final int HTTP_SAAS_PASS_ERRS = 203001;// 密码多次错误，请三小时后重试（可通过重置密码直接登录）
	public static final int HTTP_SAAS_PASS_ERR = 203002;// 密码有误，如密码再次输入错误，你的账户将会被锁定
														// 3 小时
	public static final int HTTP_SAAS_MOBILE_OR_PASS_ERR = 203003;// 手机号不存在或密码错误
	public static final int HTTP_SAAS_TRADER_PASS_ERR = 205001;// 交易密码错误
	public static final int HTTP_SAAS_CERTIFICATIONED = 207101;// 已实名认证
	public static final int HTTP_SAAS_REALNAME_CARD = 207102;// 已存在实名提现卡
	public static final int HTTP_SAAS_CARD_INVALID = 207103;// 现卡 bin 名称无效
	public static final int HTTP_SAAS_CARD_TYPE_INVALID = 207104;// 现卡操作类型无效
	public static final int HTTP_SAAS_CARD_BANK_INVALID = 207105;// 现卡所属银行名无效
	public static final int HTTP_SAAS_CODE_HANDLE = 207201;// 正在处理卡验证码
	public static final int HTTP_SAAS_CERTIFICATION_HANDLE = 207301;// 正在实名认证
	public static final int HTTP_SAAS_CERTIFICATION_FAIL = 207999;// 实名失败
	public static final int HTTP_SAAS_REGED = 208101;// 已注册
	public static final int HTTP_SAAS_LOGININ_FAIL = 208102;// 登录失败
	public static final int HTTP_SAAS_OPEN_ACCOUNT_FAIL = 208999;// 开户失败
	public static final int HTTP_SAAS_SYST_BUSY = 401001;// 系统繁忙，请稍后尝试
	public static final int HTTP_SAAS_CHECK_CARD = 401002;// 无法根据卡号确认相关信息，请检查您填写的银行卡号
	public static final int HTTP_SAAS_SYS_EX = 402001;// 系统异常，请稍后再试
	public static final int HTTP_SAAS_OPT_INVALID = 403001;// 无效的操作
	public static final int HTTP_SAAS_CARD_LIMIT = 403002;// 您已添够 10 张银行卡，最多只能添加
															// 10 张哦
	public static final int HTTP_SAAS_CARD_ADD_FAIL = 403003;// 添加银行卡失败，请稍后再试
	public static final int HTTP_SAAS_USER_CERTIFICATIONED = 403004;// 该用户已实名认证
	public static final int HTTP_SAAS_IDCARD_CERTIFICATIONED = 403005;// 该身份证号已实名认证，请重新输入
	public static final int HTTP_SAAS_NOT_CERTIFICATIONED = 403006;// 该用户没有实名认证，不能绑卡
	public static final int HTTP_SAAS_SYSTE_BUSY = 403007;// 系统繁忙，请稍后尝试。
	public static final int HTTP_SAAS_BANK_CARD_HANDLE = 403008;// 您的添加银行卡请求正在处理中，请稍后再试
	public static final int HTTP_SAAS_BANK_CARD_BINDING = 403009;// 您添加的银行卡已经绑卡成功
	public static final int HTTP_SAAS_USER_CERTIFICATION_HANDLE = 403010;// 实名认证处理中，请耐心等待
	public static final int HTTP_SAAS_MOBILE_ERR = 404001;// 您填写的手机验证码有误，请重新填写
	public static final int HTTP_SAAS_CODE_SEND_FAIL2 = 405001;// 发送验证码失败，请稍后再试
	public static final int HTTP_SAAS_SYSTEM_EX = 406001;// 系统异常，请稍后尝试
	public static final int HTTP_SAAS_BANKCARD_DEL_FAIL = 408001;// 删除银行卡失败，请稍后再试
	public static final int HTTP_SAAS_BANKCARD_CANNOT_DEL = 408002;// 该银行卡不允许删除
	public static final int HTTP_SAAS_USER_NOT_EIXT = 601001;// 用户不存在
	public static final int HTTP_SAAS_USER_CREDIT_NOT_EIXT = 2002;// 用户不存在
	public static final int HTTP_SAAS_ORDER_EXIT = 602001;// 订单号已存在
	public static final int HTTP_SAAS_USER_NOT_EXIT = 602002;// 用户不存在
	public static final int HTTP_SAAS_USER_FREEZE = 602003;// 用户已冻结
	public static final int HTTP_SAAS_ORDER_OVERDUE = 602004;// 您有逾期账单，还款后再来吧
	public static final int HTTP_SAAS_LOAN_FORMAT_ERR = 602005;// 借款金额必须为 100
																// 的整数倍
	public static final int HTTP_SAAS_LOAN_TOOSMALL = 602006;// 借款金额小于最低借款金额
	public static final int HTTP_SAAS_BANCKCARD_NOT_EIXT = 602007;// 银行卡不存在
	public static final int HTTP_SAAS_LOAD_TOOBIG = 602008;// 借款金额大于可借金额
	public static final int HTTP_SAAS_SYSTEM_BUSY = 602009;// 系统繁忙，请稍后尝试
	public static final int HTTP_SAAS_LOAD_INVALID = 602010;// 申请借款金额无效
	public static final int HTTP_SAAS_CREDIT_EXTENSION_EX = 602011;// 风控授信异常
	public static final int HTTP_SAAS_INFO_GET_ERR = 602019;// 信息获取失败，请稍后重试
	public static final int HTTP_SAAS_GET_MESSAGE_ERR = 602020;// 信息获取失败，请稍后重试
	public static final int HTTP_SAAS_USER_STATUS_ERR = 602021;// 信息获取失败，请稍后重试
	public static final int HTTP_SAAS_EX_UNPASS = 603002;// 审核未通过
	public static final int HTTP_SAAS_ORDER_NOT_EXIT = 603003;// 订单不存在
	public static final int HTTP_SAAS_LOAN_ACCOUNT_ERR = 603004;// 借款金额大于可借金额
	public static final int HTTP_SAAS_SYS_ISBUSY = 603001;// 系统繁忙，请稍后尝试
	public static final int HTTP_SAAS_ORDER_NOT_EXISTS = 604001;// 订单不存在
	public static final int HTTP_SAAS_EX_HANDLE = 606001;// 资料审核中/审核成功/审核拒绝，信息ᨀ交失败
	public static final int HTTP_SAAS_BUS_INFO_GET_ERR = 606002;// 商户信息获取失败，请重试
	public static final int HTTP_SAAS_ORDER_NOEXISTS = 606003;// 订单不存在，信息ᨀ交失败
	public static final int HTTP_SAAS_ORDER_RESUBMIT = 606004;// 订单已审核成功/失败/审核中，请勿重复ᨀ交
	public static final int HTTP_SAAS_ORDER_TYPE_ERR = 606005;// 订单类型错误，信息ᨀ交失败
	public static final int HTTP_SAAS_LOAN_ORDER_NOEXISTS = 607001;// 借款订单不存在
	public static final int HTTP_SAAS_LOAN_MESSAGE_NOAUDIT = 607002;// 借款审核未通过
	public static final int HTTP_SAAS_MSG_REPEAT = 607003;// 不允许重复取消
	public static final int HTTP_SAAS_ORDER_CONFIRM = 607004;// 借款订单已确认
	public static final int HTTP_SAAS_ORDER_USER_ERR = 607005;// 用户与订单不匹配
	public static final int HTTP_SAAS_ORDER_USER_NOT_EIXT = 304001;// 没有该用户借款
	public static final int HTTP_SAAS_WITHHOLD_TOOSMALL = 701001;// 扣款的钱 null
																	// 或者小于 0
	public static final int HTTP_SAAS_GIVING_SUC_OR_FAIL = 701002;// 不是打款成功或者失败的
																	// LoanStatus（5,6）
	public static final int HTTP_SAAS_LATE_FEE = 701003;// 逾期没有计算滞纳金
	public static final int HTTP_SAAS_REPAYMENT_TOOBIG = 701004;// 还款金额大于应还金额
	public static final int HTTP_SAAS_PRODUT_CON_NOT_EIXT = 701005;// 产品配置不存在
	public static final int HTTP_SAAS_EXTERNAL = 701006;// 是对外渠道，但是进行了部分还款
	public static final int HTTP_SAAS_BANK_CORD_NOEXISTS = 701007;// 银行卡不存在
	public static final int HTTP_SAAS_BLUE_CARD = 701008;// 还款卡类型为信用卡
	public static final int HTTP_SAAS_CHECK_WITHHOLD_EX = 701009;// 检查是否发送扣款验证码出现异常
	public static final int HTTP_SAAS_USER_NOT_EIXT_FOR_PWD = 702001;// 用户不存在（检查密码）
	public static final int HTTP_SAAS_RETRY_TOOBIG = 702002;// 重试次数大于等于最大次数
	public static final int HTTP_SAAS_PWD_ERR = 702003;// 密码错误
	public static final int HTTP_SAAS_APPID_NOT_EIXT = 704001;// APpid 不存在

	public static final int HTTP_SAAS_INFO_FAIL = 100000;// 信息获取失败，请刷新后重试
	public static final int HTTP_SAAS_SYS_BUSYNESS = 1114;// 系统繁忙，请稍后重试(1114)
	public static final int HTTP_SAAS_FAILCODE = 1047;//验证码失败次数过多，请重新获取验证码
	public static final int HTTP_SAAS_REPAYMENTDEAL = 4021;//您有还款处理中交易，请稍后再试
	/**************************** 三方平台状态码 end **********************************/

	/**************************** 我方平台状态码 start **********************************/
	// #业务代码定义从900001-901000用户相关
	public static final int HTTP_PARA_NULL_USAGETYPE = 900001;// usageType不能为空
	public static final int HTTP_PARA_NULL_MOBILE = 900002;// mobile参数不能为空
	public static final int HTTP_PARA_ERROR_MOBILE = 900003;// mobile参数格式不正确
	public static final int HTTP_PARA_NULL_MOBILEANDUSERGID = 900004;// mobile或userGid二选一
	public static final int HTTP_PARA_NULL_USER_USERGID = 900005;// userGid不能为空
	public static final int HTTP_PARA_NULL_LOGINTOKEN = 900006;// loginToken不能为空
	public static final int HTTP_PARA_NULL_AUTHTYPE = 900007;// authType不能为空
	public static final int HTTP_PARA_NULL_LOGINPWD = 900008;// loginPwd不能为空
	public static final int HTTP_PARA_NULL_VERIFYCODE = 900009;// verifyCode不能为空
	public static final int HTTP_PARA_NULL_TRANSPWD = 900010;// TransPwd不能为空
	public static final int HTTP_PARA_MOBILE_ERROR = 900011;// mobile填写错误
	public static final int HTTP_PARA_NULL_NEWPWD = 900012;// transPwd不能为空
	public static final int HTTP_PARA_NULL_CONFIRMNEWPWD = 900013;// confirmTransPwd不能为空
	public static final int HTTP_PARA_PWD_ATYPISM = 900014;// 两次密码输入不一致
	public static final int HTTP_PARA_USERIDCARD_ERROR = 900015;// userIdCard填写错误
	public static final int HTTP_PARA_NULL_USERIDCARD = 900016;// userIdCard不能为空
	public static final int HTTP_PARA_NULL_LOGINUSER = 900017;// 用户不存在
	public static final int HTTP_PARA_NULL_PAYEENAME = 900018;// 收款人姓名不能为空
	public static final int HTTP_PARA_NULL_AMT = 900019;// 实际打款金额不能为空
	public static final int HTTP_PARA_NULL_APPVERSION = 900020;// 应用程序版本过低，请更新高版本后再继续使用，更新地址
	public static final int HTTP_PARA_ERR_APPVERSION = 900021;//应用程序版本过低，请到官网https://helloapi.ttbye.com下载最新应用
	public static final int HTTP_PARA_NULL_PARTNERORDERID = 900022;//打款唯一订单号不能为空
	// #业务代码定义从901001-902000授信相关

	// #业务代码定义从902001-903000银行卡相关
	public static final int HTTP_BANK_NULL_TYPE = 902002;// 暂不支持信用卡
	public static final int HTTP_BANK_NULL_CODE = 902003; // 验证码为空
	public static final int HTTP_BANK_NULL_BANKCARD = 902004; // 暂不支持该银行卡
	public static final int HTTP_BANK_NULL_ERRBANKCARD = 902005; // 银行卡格式不正确
	public static final String HTTP_BANK_LOGOURL ="https://s6.weshare.com.cn/icon/";
	// #业务代码定义从903001-904000借款相关

	// #业务代码定义从904001-905000还款相关
	public static final int HTTP_REPARA_NULL_LOANGID = 904001;// loanGid不能为空
	public static final int HTTP_PARA_NULL_CARDGID = 904002;// cardGid不能为空
	public static final int HTTP_PARA_NULL_REPAYMENTAMOUNT = 904003;// repaymentAmount不能为空
	public static final int HTTP_PARA_NULL_SUBLOANLIST = 904004;// subLoanList不能为空
	public static final int HTTP_REPARA_NULL_ORDERGID = 904005;// orderGid不能为空
	public static final int HTTP_REPARA_NULL_TRANSPWD = 904006;// transPwd不能为空
	public static final int HTTP_REPARA_NULL_REPAYMENTGID = 904007;// repaymentGid不能为空
	public static final int HTTP_REPARA_NULL_USERIDCARD = 904008;// userIdCard身份证号码不能为空
	public static final int HTTP_REPARA_USERIDCARD_ERROR = 904009;// userIdCard身份证号码error
	public static final int HTTP_REPARA_LOANNUMBER_ERROR = 904010;// loanNumber不能为空
	// #业务代码定义从905001-906000查询相关
	public static final int HTTP_HEADER_NULL_TOKEN = 990001;// token不能为空
	public static final int HTTP_SAAS_SERVICE_RETURN_NULL = 990002;// 三方服务返回结果为空

	// 借款流程相关业务代码
	public static final int HTTP_PARA_NULL_USER = 903001;// user对象不能为空
	public static final int HTTP_PARA_NULL_USERID = 903002;// userId不能为空
	public static final int HTTP_PARA_NULL_PRODUCTID = 903003;// productId不能为空
	public static final int HTTP_PARA_NULL_USERGID = 903004;// userGid不能为空
	public static final int HTTP_PARA_NULL_LOANAMOUNT = 903005;// 申请借款金额不能为空
	public static final int HTTP_PARA_NULL_ORDERGID = 903006;// 订单号不能为空
	public static final int HTTP_PARA_NULL_OPTTYPE = 903007;// 确认方式不能为空
	public static final int HTTP_PARA_NULL_BANKCARDGID = 903008;// 银行卡Gid不能为空
	public static final int HTTP_ORDER_CONFIRM_LOAN = 903021;// 不能取消已放款的订单
	public static final int HTTP_ORDER_APPLY_LOAN_TRANSPWD = 903022;// 交易密码不能为空
	public static final int HTTP_ORDER_APPLY_LOAN_AMOUNT_LIMIT = 903023;// 感谢您参与哈喽贷内测，今日放款已达上限，请您明日再来申请!

	public static final int HTTP_PARA_NULL_ACCOUNTTYPE = 900301;// accountType，账户类型不能为空
	public static final int HTTP_PARA_NULL_LISTTYPE = 900302;// listType，账单类型不能为空
	public static final int HTTP_PARA_NULL_LOANGID = 900303;// loanGid，借款账单gid不能为空
	public static final int HTTP_PARA_NOTNULL_UNREPAY = 900304;// 您还有未结清账单，无法申请新的借款呦
	
	//xs返回的状态码
	public static final int HTTP_CORRECT_STATUS_CODE = 0000;// 操作成功
	public static final int HTTP_XS_ERR_CODE = 9998;// 手机号码不能为空或手机格式不正确
	public static final int HTTP_XS_ERR_CARD = 9997;// 未获取到银行卡
	public static final int HTTP_CORRECT_MOBLIE_CARD = 9996;// 请输入正确的预留手机号码
	public static final int HTTP_BANK_CARD_ERR = 9995;// 银行卡信息有误，请更换后重试
}
