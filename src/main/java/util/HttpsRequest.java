package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import config.SaaSURL;

public class HttpsRequest {

	private static Log log = LogFactory.getLog(HttpsRequest.class);
	private static long  ttTime = System.currentTimeMillis() / 1000 + 30*24*60*60;
	public static String sendThirdPost(String url, Map<String, String> suffixParam, Object params) {
		String urlSuffix = genParam(suffixParam);
		log.info("urlSuffix ："+urlSuffix);
		// 获取token的url
		String token = "";
		log.info("生成与三方交互的token ："+token);
		///获取用户原始授信数据时，URL与其他接口不同，token生成不相同
		if(url.equals(SaaSURL.QUERY)){
			token = TokenHelper.generateToken(url, "POST", "", JSON.toJSONString(params), ttTime );
			url=SaaSURL.CREDIT_DATA_URL+url;
			log.info("获取原始数据授信url :"+url);
		}else if(url.equals(SaaSURL.QUERYBASE)){
			token = TokenHelper.generateToken(url, "POST", urlSuffix, JSON.toJSONString(params), ttTime );
			url=SaaSURL.CREDIT_STATUS_URL+url;
			log.info("查询授信状态url :"+url);
		}else {
			token = TokenHelper.generateToken(SaaSURL.SAAS_URL_AFTER+url, "POST", urlSuffix, JSON.toJSONString(params), ttTime );
			url=SaaSURL.URL+SaaSURL.SAAS_URL_AFTER+url;
			log.info("主流程的url :"+url);
		}
//		if(!(url.contains(SaaSURL.CREDIT_STATUS_URL)||url.contains(SaaSURL.CREDIT_DATA_URL))){
//			
//		}
		log.info("[第三方请求开始]");
		log.info("[第三方请求] [URL] " + url);
		log.info("[第三方请求] [URL参数] " + JSON.toJSONString(suffixParam));
		
		String result = "";
		try {
			HttpClient httpsClient = getHttpsClient();
			String paraJson = JSON.toJSONString(params);
			log.info("[第三方请求] [POST Body 参数] " + paraJson);
			result = postThird(httpsClient, url + (StringUtils.isBlank(urlSuffix) ? "" : "?" + urlSuffix), new StringEntity(paraJson, "utf-8"), token);
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		// result = JSON.toJSONString(result);
		log.info("[第三方请求] [结果] " + result);
		return result;
	}

	private static String postThird(HttpClient httpClient, String url, StringEntity stringEntity, String token) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build());
		httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.setHeader("X-WeshareAuth-Token", token);
		httpPost.setEntity(stringEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
		String valueString = null;
		StringBuilder builder = new StringBuilder();
		while ((valueString = bufferedReader.readLine()) != null) {
			builder.append(valueString);
		}
		httpPost.abort();
		return builder.toString();
	}

	/**
	 * 
	 * @Description :
	 * @param url
	 * @param suffixParam url后缀参数，也就是这部分参数要追加在url后面
	 * @param params post请求体中的参数，即requestBody中的参数
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> suffixParam, Map<String, String> params) {
		if (url == null) {
			return "url is null";
		}
		String urlSuffix = genParam(suffixParam);
		String result = "";
		try {
			HttpClient httpsClient = getHttpsClient();
			Iterator<String> it = params.keySet().iterator();
			ArrayList<BasicNameValuePair> paras = new ArrayList<>();
			while (it.hasNext()) {
				String key = it.next();
				paras.add(new BasicNameValuePair(key, params.get(key)));
			}
			StringEntity entity = new UrlEncodedFormEntity(paras, "UTF-8");
			result = post(httpsClient, url + (StringUtils.isBlank(urlSuffix) ? "" : "?" + urlSuffix), entity);
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	public static String sendPost(String url, JSONObject json) throws Exception {
		if (url != null) {
			HttpClient httpClient;
			if (url.startsWith("https")) {
				httpClient = HttpsRequest.getHttpsClient();
			} else {
				httpClient = HttpClients.createDefault();
			}

			StringEntity entity = new StringEntity(json.toString(), "UTF-8");
			return post2(httpClient, url, entity);
		} else {
			return "url is null";
		}
	}

	/**
	 * 
	 * @Description : 功能通post方法，一模一样
	 * @param httpClient
	 * @param url
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private static String post2(HttpClient httpClient, String url, HttpEntity entity) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();

		String response = EntityUtils.toString(httpEntity, "utf-8");
		EntityUtils.consume(httpEntity);
		return response;
	}

	private static String genParam(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return "";
		}
		StringBuffer url = new StringBuffer();
		for (String key : map.keySet()) {
			String value = map.get(key);
			if (StringUtils.isBlank(value)) {
				continue;
			}
			url.append(key);
			url.append("=");
			url.append(value);
			url.append("&");
		}
		String input = "";
		if (url.toString().endsWith("&")) {
			input = url.substring(0, url.length() - 1);
		}
		return input;
	}

	private static HttpClient getHttpsClient() throws Exception {
		SSLContext context = new SSLContextBuilder().loadTrustMaterial((KeyStore) null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return true;
			}
		}).build();
		return HttpClients.custom().setSSLContext(context).build();
	}

	private static String post(HttpClient httpClient, String url, HttpEntity entity) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		JSONObject obj = new JSONObject();
		obj.put("usageType", "0");
		// obj.put("mobile", "13381321315");
		// obj.put("mobile", "18634691713");
		obj.put("mobile", "13811798733");
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
		// String token = TokenHelperDemo.generateToken(url, "POST",
		// url.substring(url.lastIndexOf("?") + 1, url.length()), null, 30 *
		// 60);
		String token = "v2-X1hFrzGWqrebuLaBhxAS-1800-69254b62a9407bbbc2fe04a65854e68e";
		System.out.println(token);
		httpPost.setHeader("X-WeshareAuth-Token", token);
		StringEntity se = new StringEntity(obj.toString());
		httpPost.setEntity(se);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
		String valueString = null;
		StringBuilder builder = new StringBuilder();
		while ((valueString = bufferedReader.readLine()) != null) {
			builder.append(valueString);
		}
		httpPost.abort();
		return builder.toString();
	}

	public static Map<String, String> getsuffixParam(String b, String c, String ch, Integer u, String t) {
		Map<String, String> suffixParam = new LinkedHashMap<String, String>();
		suffixParam.put("b", b);
		suffixParam.put("c", c);
		suffixParam.put("ch", ch);
		if (null != u) {
			suffixParam.put("u", String.valueOf(u));
		}
		if (StringUtils.isNotBlank(t)) {
			suffixParam.put("t", t);
		}
		return suffixParam;
	}

	public static Map<String, String> getsuffixParam(String c, Integer u, String t) {
		return getsuffixParam(SaaSURL.SAAS_B, c, SaaSURL.SAAS_CH, u, t);
	}

}
