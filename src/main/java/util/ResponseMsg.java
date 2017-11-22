package util;

/**
 * 
 *<p>Title	: ResponseMsg</p>
 * @Description	: APP请求返回值
 * @author	: admin
 * @date	: 2017年11月22日上午9:41:26
 */
public class ResponseMsg{
    /**
	 * 状态码
	 */
	private int status;
	/**
	 * 消息
	 */
	private String message;
	/**
	 * 内容
	 */
	private Object content;
	

	public ResponseMsg(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseMsg() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
 
//	
//	/**
//	 * 根据Code对象返回对应的ResponseMsg
//	 * @author liyanlong
//	 * @since  v1.0
//	 * @date   2016年12月23日下午1:28:04
//	 * @param code
//	 * @return
//	 */
//	public ResponseMsg getSuccessMsg(Code code){
//		return getResponseMsg(code.getCode() , code.getMsg());
//	}
//	
//	public ResponseMsg getSuccessMsg(Code code, Object data){
//		return getResponseMsg(code.getCode() , code.getMsg(), data);
//	}
//	
//	
//	/**
//	 * 根据Code对象返回对应的ResponseMsg
//	 * @author liyanlong
//	 * @since  v1.0
//	 * @date   2016年12月23日下午1:27:13
//	 * @param code
//	 * @return
//	 */
//	public ResponseMsg getFailMsg(Code code){
//		return getResponseMsg(code.getCode(), code.getMsg());
//	}
//	
//	public ResponseMsg getResponseMsg(String code, String msg){
//		this.code = code;
//		this.msg = msg;
//		return this;
//	}
//	
//	public ResponseMsg getResponseMsg(String code, String msg, Object data){
//		this.code = code;
//		this.msg = msg;
//		this.data = data;
//		return this;
//	}
//	
//	/**
//	 * 获取本对象的JSON串形式
//	 * @author liyanlong
//	 * @since  v1.0
//	 * @date   2016年12月27日上午10:12:01
//	 * @return
//	 */
//	public String toJSON(){
//		JSONObject json = new JSONObject();
//		json.put("code", this.code);
//		json.put("msg", this.getMsg());
//		json.put("data", JSONArray.fromObject(this.data == null ? "[]" : this.data));
//		return json.toString();
//	}
//
//	/**
//	 * 构造返回结果
//	 * @param code
//	 * @return
//	 */
//	public static ResponseMsg build(Code code){
//		return new ResponseMsg(code.getCode() , code.getMsg());
//	}
//
//	/**
//	 * 构造返回结果
//	 * @param code
//	 * @param msg
//	 * @return
//	 */
//	public static ResponseMsg build(String code,String msg){
//		return new ResponseMsg(code , msg);
//	}
}
