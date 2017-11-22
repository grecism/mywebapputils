package message.sendmessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import config.SendMessageConfig;

/**
 * 
 *<p>Title	: SendMessageUtil</p>
 * @Description	: 发送短信入口类
 * @author	: admin
 * @date	: 2017年11月22日上午9:30:01
 */
public class SendMessageUtil {
	private final static Log logger = LogFactory.getLog(SendMessageUtil.class);
    
	/**
	 * 
	 * @Description : 通过验证码渠道发送短信
	 * @param method
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public static String sendByVerificationCodeChannel(String method,String mobile,String msg){
		String url="";
		if(SendMessageConfig.METHOD_GET.equalsIgnoreCase(method)){
			url=SendMessageConfig.VERIFICATIONCODE_CHANNEL+"?usr="+SendMessageConfig.USR+"&pwd="+
					SendMessageConfig.PWD+"&src="+SendMessageConfig.SRC+"&dest="+mobile+"&msg="+msg;
			return HttpClientUtil.doGet(url);
		}
		if(SendMessageConfig.METHOD_POST.equalsIgnoreCase(method)){
			url=SendMessageConfig.VERIFICATIONCODE_CHANNEL;
			Map<String,String> map=new HashMap<>();
			map.put("usr", SendMessageConfig.USR);
			map.put("pwd", SendMessageConfig.PWD);
			map.put("src", SendMessageConfig.SRC);
			map.put("dest",mobile);
			map.put("msg", msg);
			return HttpClientUtil.doPost(url, map);
		}
		return "-1";
	}
	
	public static String sendByMarketingChannel(String type,String method,String mobile,String msg){
		String url="";
		if(SendMessageConfig.METHOD_GET.equalsIgnoreCase(method)){
			url=SendMessageConfig.MARKETING_CHANNEL+"?usr="+SendMessageConfig.USR+"&pwd="+
					SendMessageConfig.PWD+"&src="+SendMessageConfig.SRC+"&dest="+mobile+"&msg="+msg;
			logger.info(">>>通过get方式发送还款短信url"+url);
			return HttpClientUtil.doGet(url);
		}else{
			Map<String,String> map=new HashMap<>();
			url=SendMessageConfig.MARKETING_CHANNEL;
			map.put("usr", SendMessageConfig.USR);
			map.put("pwd", SendMessageConfig.PWD);
			map.put("src", SendMessageConfig.SRC);
			map.put("dest",mobile);
			map.put("msg", msg);
			logger.info(">>>通过post方式发送还款短信url"+url);
			logger.info(">>>通过post方式发送还款短信参数：usr="+SendMessageConfig.USR+
					",pwd="+SendMessageConfig.PWD+",src="+SendMessageConfig.SRC+",dest="+mobile+",msg="+msg);
			return HttpClientUtil.doPost(url, map);
		}
	}
	
	/*public static String sendByMarketingChannel(String type,String method,String mobile,String msg,int size){
		Message message=prepareParam(type,method, mobile, msg);
		try {
            logger.info("send message start...");
            long startTime = System.currentTimeMillis();
            BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(200);
            ThreadPoolExecutor executors = new ThreadPoolExecutor(5, 6, 60000, TimeUnit.SECONDS, queue);
            //要发送的消息总数
            logger.info("message all count=>{}"+size);
            //初始每个线程处理的短信数量
            final int eveLength = 2000;
            //计算处理所有短信需要的线程数量
            int eveBlocks = size / eveLength + (size % eveLength != 0 ? 1 : 0);
            logger.info("need thread's count=>{}"+eveBlocks);
            //线程计数器
            CountDownLatch doneSignal = new CountDownLatch(eveBlocks);
            //开启线程处理
            int doneCount = 0;
            for (int page = 0; page < eveBlocks; page++) {  //blocks太大可以再细分重新调度 
                MessageSendTask ms = new MessageSendTask(message,page + 1,eveLength,doneSignal);
                executors.execute(ms);
                doneCount++;
            }
            doneSignal.await();//等待所有计数器线程执行完
            long endTime = System.currentTimeMillis();
            logger.info("send message all thread ends!time(s)=>{}"+(startTime-endTime)/1000);
            logger.info("all thread count=>{}"+doneCount);
            return "88";
        } catch (Exception e) {
            logger.error("send message error=>{}",e);
        }
		return "-1";
	}

	private static Message prepareParam(String type,String method, String mobile, String msg) {
		Message message=new Message();
		String url="";
		Map<String,String> map=new HashMap<>();
		if(SendMessageConfig.METHOD_GET.equalsIgnoreCase(method)){
			url=SendMessageConfig.MARKETING_CHANNEL+"?usr="+SendMessageConfig.USR+"&pwd="+
					SendMessageConfig.PWD+"&src="+SendMessageConfig.SRC+"&dest="+mobile+"&msg="+msg;
		}
		if(SendMessageConfig.METHOD_POST.equalsIgnoreCase(method)){
			url=SendMessageConfig.MARKETING_CHANNEL;
			map.put("usr", SendMessageConfig.USR);
			map.put("pwd", SendMessageConfig.PWD);
			map.put("src", SendMessageConfig.SRC);
			map.put("dest",mobile);
			map.put("msg", msg);
		}
		message.setType(type);
		message.setUrl(url);
		message.setMethod(method);
		message.setMap(map);
		return message;
	}*/
}
