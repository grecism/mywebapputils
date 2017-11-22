package config;

/**
 * 
 *<p>Title	: Constants</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:38:40
 */
public class Constants {
	public static String AES_PASS_KEY_ACT = "4B203CF3D3505BF841UFED63A78E3B78"; // aes密钥
	public static String USER_ONLINENUM = "50000";    //默认在线加入人数
	public static String USER_UNAUDIT_CREDITLINE = "5000";    //默认授信额度
	public static String USER_UNAUDIT_AVAILAMOUNT = "5000";    //默认可借额度
	public static String SUCCESS_MESSAGE = "操作成功";    //默认成功返回信息
	public static int USER_UNAUDIT_LOANNUM = 0;    //用户默认借款数
	public static int USER_UNAUDIT_OVERDUENUM = 0;    //用户默认逾期数
	public static String USER_UNAUDIT_CURRENTBALANCEE = "0";    //默认总待还金额
	public static String REPAYMENT_SUCCESS_MESSAGE = "已还款";    //默认已还款
	public static String REPAYMENT_COURSE_ACCOUNT = "放款中";    //放款中
	public static int REPAYMENT_COURSE_CODE = 0;    //置灰状态
	public static int REPAYMENT_SUCCESS_CODE = 1;    //显示状态
	public static int USER_HISTORY_LOAN_LIST = 2;    //历史账单类型
	public static int USER_FUND_LOAN_LIST = 1;    //在借账单类型
	public static int USER_ALL_LOAN_LIST = 0;    //全部账单类型
	public static String COMPANY_CODE = "swqian";    //三方分配合作公司编码
	public static String IDCARD_AUDIT = "authpic";    //身份认证
	public static String CARD_PHOTO_AUDIT = "idcardpic";    //身份证照片
	public static int LOANL_LIST_TIME_OUT = 24 * 60 * 60;    //Redis超时时间
	public static int USER_REAL_NAME_AUTHSTATUS = 2;    //用户实名认证状态
	public static String USER_IDCARD_IMAGE_URL = "/data0/Urlpictures/";    //拉取用户照片url存放地址
	public static String USER_IDCARD_FILE_URL = "/data0/file/";    //拉取用户手机通信,淘宝url存放地址
	public static String USER_LOGIN_LOSE = "100008";    //用户登录失效
	
	/**
     * UTF-8编码
     */
    public static final String UTF8 = "";
    /**
     * HTTP POST请求
     */
    public static final String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static final String GET = "GET";
	
	
}
