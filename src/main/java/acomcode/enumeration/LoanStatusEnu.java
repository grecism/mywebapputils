package acomcode.enumeration;

/**
 * 
 *<p>Title	: LoanStatusEnu</p>
 * @Description	: 贷款状态枚举
 * @author	: admin
 * @date	: 2017年11月21日下午8:40:29
 */
public enum LoanStatusEnu {

	loan_Status_Auditing(0, "审核中"), 
	loan_Status_Audit_Success(1, "审核成功（放款待确认）"), 
	loan_Status_Audit_Fail(2, "审核失败"), 
	loan_Status_Comit_Fail(3, "确认失败"), 
	loan_Status_Borrowwing(4, "放款中"), 
	loan_Status_Pay_fail(5, "放款失败"), 
	loan_Status_Pay_Success(6, "放款成功");

	int code;
	String msg;

	private LoanStatusEnu(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getMessage(int code) {
		for (LoanStatusEnu eum : LoanStatusEnu.values()) {
			if (eum.getCode() == code) {
				return eum.getMsg();
			}
		}
		return "";
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
