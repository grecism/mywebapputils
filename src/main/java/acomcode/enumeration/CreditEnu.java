package acomcode.enumeration;

/**
 * 
 *<p>Title	: CreditEnu</p>
 * @Description	: 授信相关枚举参数
 * @author	: admin
 * @date	: 2017年11月21日下午8:40:20
 */
public enum CreditEnu {

	credit_item_authpic("authpic","上身份认证"),
	credit_item_carrierreport("carrierreport","上手机认证"),
	credit_item_nearcontact("carrierreport","上手机认证"),
	credit_item_bankcard("bankcard","上手机认证"),
	credit_item_taobao("taobao","淘宝");
	
	private String code;
	private String msg;
	
	private CreditEnu(String code, String msg){
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
