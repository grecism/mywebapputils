package acomcode.enumeration;

/**
 * 
 *<p>Title	: MessageLoanEnu</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:40:40
 */
public enum MessageLoanEnu {
	messageloanStatusAuditSuccess(1,"审核成功",1),
	messageloanStatusAuditFail(2,"审核失败",2),
	messageloanStatusBorrowSuccess(3,"借款成功",6),
	messageloanStatusBorrowFail(4,"借款失败",5),
	messageloanStatusPaySuccess(5 ,"打款成功",7);
	
	int code;
	String msg;
	int localCode;
	
	private MessageLoanEnu(int code, String msg,int localCode){
		this.code = code;
		this.msg = msg;
		this.localCode=localCode;
	}
	public static int getLocalCode(int code){
		for(MessageLoanEnu eum:MessageLoanEnu.values()){
			if(eum.getCode()==code){
				return eum.getLocalCode();
			}
		}
		return code;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public int getLocalCode() {
		return localCode;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setLocalCode(int localCode) {
		this.localCode = localCode;
	}
	
}
