package acomcode.enumeration;

/**
 * 
 *<p>Title	: MessageReturnEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:40:48
 */
public enum MessageReturnEnu {
	
	
	reqSuccess("0000","请求成功"),
	reqFail("0001","请求失败"),
	reqTransactionSuccess("0000","交易处理成功"),
	reqTransactionFail("0001","交易处理失败"),
	reqRecordNoFind("0002","订单不存在"),
	reqRecordLost("0003","数据缺失");
	
	
	private String code;
	private String msg;
	
	private MessageReturnEnu(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	}
