package message.sendmessage;

import java.util.concurrent.CountDownLatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import config.SendMessageConfig;

public class MessageSendTask implements Runnable{
	private final static Log logger = LogFactory.getLog(MessageSendTask.class);
 
    private Integer currentIndex;//当前索引
    private Integer rows;//处理数据条数
    private CountDownLatch doneSignal;//处理线程条数
    private Message message;
 
    /**
     * 
     * @param message
     * @param currentIndex
     * @param rows
     * @param doneSignal
     */
    public MessageSendTask(Message message,Integer currentIndex,Integer rows, CountDownLatch doneSignal) {
    	this.message=message;
        this.currentIndex = currentIndex;
        this.rows = rows;
        this.doneSignal = doneSignal;
    }
     
    @Override
    public void run() {
        try {
            if(SendMessageConfig.METHOD_GET.equalsIgnoreCase(message.getMethod())){
            	//HttpClientUtil.doGet(message.getUrl());
            	System.out.println(message.getUrl());
            }
            if(SendMessageConfig.METHOD_POST.equalsIgnoreCase(message.getMethod())){
            	//HttpClientUtil.doPost(message.getUrl(), message.getMap());
            	System.out.println(message.getMap());
            }
        } catch (Exception e) {
            logger.info("send message thread exception=>{}{}{}{}"+message);
        }finally{
            doneSignal.countDown();
        }
    }

}
