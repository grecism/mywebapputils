package util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *<p>Title	: OrderUtil</p>
 * @Description	: 订单工具类
 * @author	: admin
 * @date	: 2017年11月22日上午9:38:11
 */
public class OrderUtil {

	 /**
	  * 生成订单号   
	  */
	 public static String generateOrderNo(int userId,int orderId){
		StringBuffer orderNoBuffer = new StringBuffer();
		orderNoBuffer.append("J");
		orderNoBuffer.append(userId);
		orderNoBuffer.append("U");
		orderNoBuffer.append(orderId);
		orderNoBuffer.append("O");
		String dataStr = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
		orderNoBuffer.append(dataStr);
		String orderNo = orderNoBuffer.toString();
		return orderNo;
	 }
	
	 /**
	  * 显示订单号是截取，避免订单号显示过长
	  */
	 public static String subOrderNo(String orderNo){
		if(StringUtils.isBlank(orderNo)){
			return "";
		}
		char[] orderNoCh = orderNo.toCharArray();
	    if(orderNoCh.length>17){
	    	orderNo = orderNo.substring(0, 17);
	    }
		return orderNo;
	 }
	 
	//根据订单号生成相应的还款单号
	public static String createRepayNo(String noOrder, Integer orderPlanId, Integer userId) {
		StringBuffer sb = new StringBuffer(noOrder);
		int endOIndex = noOrder.indexOf("O");
		String timeStr = sb.substring(endOIndex + 1, sb.length());
		int time = (int)(DateUtil.stringToDate(timeStr, DateUtil.YYYYMMDDHHMMSS).getTime() / 1000);
		StringBuffer su = new StringBuffer();
		su.append("H").append(userId).append("U").append(orderPlanId).append("OP").append(time);
		return su.toString();

	}
	 
	 /**
	  * 生成单个的分期的编号
	  */
	public static String createRepayNo(Integer orderPlanId, Integer userId) {
		int now = (int) (System.currentTimeMillis() / 1000);
		StringBuffer su = new StringBuffer();
		su.append("H").append(userId).append("U").append(orderPlanId).append("OP").append(now);
		return su.toString();

	}
	 
	 
	 
	/**
	 * 生成还款单号
	 */
//	public static String generateRepayNo(int userId,int orderId){
//		StringBuffer repayNoBuffer = new StringBuffer();
//		repayNoBuffer.append("H");
//		repayNoBuffer.append(userId);
//		repayNoBuffer.append("U");
//		repayNoBuffer.append(orderId);
//		repayNoBuffer.append("O");
//		String dataStr = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
//		repayNoBuffer.append(dataStr);
//		String orderNo = repayNoBuffer.toString();
//		return orderNo;
//	}
	
	/**
	 * 连连所支持的银行卡
	 */
	public static String[] supportName(){
		String[] bankName = {"工商银行","农业银行","中国银行","建设银行","招商银行","光大银行","浦发银行","平安银行","华夏银行","兴业银行","中信银行","储蓄银行","广发银行"};
	    return bankName;
	}
	
	public static String[] supportNameCode(){
		String[] bankCode = {"01020000","01030000","01040000","01050000","03080000","03030000","03100000","03070000","03040000","03090000","03020000","01000000","03060000"};
	    return bankCode;
	}
	
}
