package config;

/**
 * 
 *<p>Title	: PushMessageConfig</p>
 * @Description	: 消息推送常量
 * @author	: admin
 * @date	: 2017年11月21日下午8:39:02
 */
public class PushMessageConfig {
	//IOS消息推送appKey
	public static final String IosAppKey = "59a38c434ad1566a88001f77";
	//IOS消息推送AppMasterSecret
	public static final String IosAppMasterSecret = "kdp6mc189brfvm8aojqtq91wuteii1ku";
    //Android消息推送appKey
	public static final String androidAppKey = "59a3880eae1bf858f000175c";
	//Android消息推送AppMasterSecret
	public static final String AndroidAppMasterSecret = "mbqlynqp3ckyn4mzhuggvq6akdcjdxdo";

	public static final String typeUnicast = "unicast";//单播
	public static final String typeBroadcast = "broadcast";//广播
	public static final String method = "POST";//post方式请求
	public static final String USER_AGENT = "Mozilla/5.0";
	public static final String DISPLAY_TYPE ="notification";
	public static final String TICKER ="借款结果通知";  //借款通知头
	public static final String TITLE ="借款结果通知";  //借款通知标题
	public static final String RepayTICKER ="还款结果通知";//还款通知信息头
	public static final String RepayTITLE ="还款结果通知";//还款通知信息标题
	public static final String PUSH_URL ="http://msg.umeng.com/api/send";//消息推送友盟地址
	
	/**
	 * go_app: 打开应用
	 * go_url: 跳转到URL
	 * go_activity: 打开特定的activity
	 * go_custom: 用户自定义内容
	 */
	public static final String AFTER_OPEN ="go_app";

}
