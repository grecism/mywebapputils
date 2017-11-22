package message.pushmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import config.PushMessageConfig;

/**
 * 
 *<p>Title	: PushModule</p>
 * @Description	: push消息
 * @author	: admin
 * @date	: 2017年11月22日上午9:28:06
 */
public class PushModule {
	private final Log log = LogFactory.getLog(PushModule.class);

	/**
	 * 
	 * @Description : 分情况进行代码组装，分IOS和Android
	 * @param type
	 * @param deviceTokens
	 * @param deviceType
	 * @param displayType
	 * @param ticker
	 * @param title
	 * @param text
	 * @param afterOpen
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void push(String type, String deviceTokens, String deviceType, String displayType,
			String ticker, String title, String text, String afterOpen) throws ClientProtocolException, IOException {
			log.info("开始发送push消息,其设备token为：" + deviceTokens + ",设备类型为：" + deviceType + ",发送消息为" + text);
			JSONObject paramJson = new JSONObject();
			//1为ios,2为Android
			if (deviceType.equals("1")) {
				paramJson = buildParamForIos(type, deviceTokens, ticker, title, text);
				sendQuest(paramJson, PushMessageConfig.IosAppMasterSecret);
			}else if (deviceType.equals("2")) {
				paramJson = buildParamForAndroid(type, deviceTokens, displayType, ticker, title, text, afterOpen);
				sendQuest(paramJson, PushMessageConfig.AndroidAppMasterSecret);
			}
		log.info("push发送成功");
	}

	/**
	 * 
	 * @Description : 发送请求
	 * @param paramJson
	 * @param appMasterSecret
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private boolean sendQuest(JSONObject paramJson, String appMasterSecret)
			throws ClientProtocolException, IOException {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String paramJsonStr = paramJson.toString();
					CloseableHttpClient client = HttpClients.createDefault();
					String url = PushMessageConfig.PUSH_URL;
					String singStr = PushMessageConfig.method + url + paramJsonStr + appMasterSecret;
					String sign = DigestUtils.md5Hex((singStr).getBytes("utf8"));
					url = url + "?sign=" + sign;
					HttpPost post = new HttpPost(url);
					post.setHeader("User-Agent", PushMessageConfig.USER_AGENT);
					StringEntity se = new StringEntity(paramJsonStr, "UTF-8");
					post.setEntity(se);
					// 设置超时时间
		 			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(10000)
		 					.build();
		 			post.setConfig(requestConfig);
					HttpResponse response = client.execute(post);
					int status = response.getStatusLine().getStatusCode();
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					log.info("友盟返回数据为：" + result.toString());
					if (status == 200) {
						log.info("Notification sent successfully.");
					} else {
						log.info("Failed to send the notification!" + paramJsonStr);
					}
					client.close();
				} catch (Exception e) {
					log.error("打款发送短信或者push出错", e);
				}
			}
		}).start();

		return true;
	}
	
	/**
	 * 
	 * @Description : 单条/广播推送消息Ios
	 * @param type
	 * @param displayToken
	 * @param mainTitle
	 * @param subTitle
	 * @param text
	 * @return
	 */
	private JSONObject buildParamForIos(String type, String displayToken, String mainTitle, String subTitle,
			String text) {
		long timeNow = System.currentTimeMillis();
		JSONObject jsonHead = new JSONObject();
		JSONObject payloadJson = new JSONObject();
		JSONObject apsJson = new JSONObject();
		JSONObject alertJson = new JSONObject();
		JSONObject bodyJson = new JSONObject();
		JSONObject modeJson = new JSONObject();
		jsonHead.put("appkey", PushMessageConfig.IosAppKey);
		jsonHead.put("timestamp", String.valueOf(timeNow));
		if (type.equals(PushMessageConfig.typeUnicast)) {
			jsonHead.put("type", PushMessageConfig.typeUnicast);
			jsonHead.put("device_tokens", displayToken);
		}
		if (type.equals(PushMessageConfig.typeBroadcast)) {// 消息发送类型,广播
			jsonHead.put("type", PushMessageConfig.typeBroadcast);
		}
		bodyJson.put("title", mainTitle);
		// bodyJson.put("subtitle", subTitle);
		bodyJson.put("body", text);
		alertJson.put("alert", bodyJson);
		apsJson.put("aps", alertJson);
		payloadJson.put("payload", apsJson);
		modeJson.put("production_mode", String.valueOf(false));
		jsonHead.putAll(payloadJson);
		jsonHead.putAll(modeJson);
		return jsonHead;
	}
	
	/**
	 * 
	 * @Description :  单条、光爆推送消息Android
	 * @param type
	 * @param displayToken
	 * @param displayType
	 * @param mainTitle
	 * @param subTitle
	 * @param text
	 * @param afterOpen
	 * @return
	 */
	private JSONObject buildParamForAndroid(String type, String displayToken, String displayType, String mainTitle,
			String subTitle, String text, String afterOpen) {
		long timeNow = System.currentTimeMillis();
		JSONObject jsonHead = new JSONObject();
		JSONObject payloadJson = new JSONObject();
		JSONObject contentJson = new JSONObject();
		JSONObject bodyJson = new JSONObject();
		jsonHead.put("appkey", PushMessageConfig.androidAppKey);// AppKey
		jsonHead.put("timestamp", timeNow);// 时间戳
		if (type.equals(PushMessageConfig.typeUnicast)) {
			jsonHead.put("type", PushMessageConfig.typeUnicast);
			jsonHead.put("device_tokens", displayToken);
		}
		if (type.equals(PushMessageConfig.typeBroadcast)) {// 消息发送类型,广播
			jsonHead.put("type", PushMessageConfig.typeBroadcast);
		}
		contentJson.put("display_type", displayType);// 消息类型，值可以为:
														// notification-通知，message-消息
		bodyJson.put("ticker", mainTitle);// 通知栏提示文字
		bodyJson.put("title", mainTitle);// 通知标题
		bodyJson.put("text", text);// 内容
		bodyJson.put("after_open", afterOpen);
		contentJson.put("body", bodyJson);
		payloadJson.put("payload", contentJson);
		jsonHead.putAll(payloadJson);
		return jsonHead;
	}
}
