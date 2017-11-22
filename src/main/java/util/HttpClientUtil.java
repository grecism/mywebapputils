package util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;  
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.util.CollectionUtils;
import com.google.gson.Gson;

public class HttpClientUtil {  
	
	private final static Log logger = LogFactory.getLog(HttpClientUtil.class);
  
    public static String doGet(String url, Map<String, String> param) {  
  
        // 创建Httpclient对象  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
  
        String resultString = "";  
        CloseableHttpResponse response = null;  
        try {  
            // 创建uri  
            URIBuilder builder = new URIBuilder(url);  
            if (param != null) {  
                for (String key : param.keySet()) {  
                    builder.addParameter(key, param.get(key));  
                }  
            }  
            URI uri = builder.build();  
  
            // 创建http GET请求  
            HttpGet httpGet = new HttpGet(uri);  
  
            // 执行请求  
            response = httpclient.execute(httpGet);  
            // 判断返回状态是否为200  
            if (response.getStatusLine().getStatusCode() == 200) {  
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
            logger.error("通过get方式调用HttpClientUtil请求出错", e);
        } finally {  
            try {  
                if (response != null) {  
                    response.close();  
                }  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();
                logger.error("通过get方式调用HttpClientUtil关闭连接时出错", e);
            }  
        }  
        return resultString;  
    }  
  
    public static String doGet(String url) {  
        return doGet(url, null);  
    }  
  
    public static String doPost(String url, Map<String, String> param) {  
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpResponse response = null;  
        String resultString = "";  
        try {  
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);  
            // 创建参数列表  
            if (param != null) {  
                List<NameValuePair> paramList = new ArrayList<>();  
                for (String key : param.keySet()) {  
                    paramList.add(new BasicNameValuePair(key, param.get(key)));  
                }  
                // 模拟表单  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");  
                httpPost.setEntity(entity);  
            }  
            // 执行http请求  
            response = httpClient.execute(httpPost);  
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace(); 
            logger.error("通过post方式调用HttpClientUtil请求出错", e);
        } finally {  
            try {  
                response.close();  
            } catch (IOException e) {  
            	logger.error("通过post方式调用HttpClientUtil关闭连接时出错", e);
                e.printStackTrace();  
            }  
        }  
  
        return resultString;  
    }  
  
    public static String doPost(String url) {  
        return doPost(url, null);  
    }  
      
    public static String doPostJson(String url, String json) {  
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpResponse response = null;  
        String resultString = "";  
        try {  
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);  
            // 创建请求内容  
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);  
            httpPost.setEntity(entity);  
            // 执行http请求  
            response = httpClient.execute(httpPost);  
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("通过post方式调用HttpClientUtil请求发送json格式数据出错", e);
        } finally {  
            try {  
                response.close();  
            } catch (IOException e) {  
            	logger.error("通过post方式调用HttpClientUtil发送json格式数据关闭连接时出错", e);
                e.printStackTrace();  
            }  
        }  
  
        return resultString;  
    }  
    
    public static String exec(String url, String is_post, String is_json, Map<String, Object> inParams) {
		CloseableHttpClient httpClient = null;
		HttpResponse resp = null;
		try {
			if (url.startsWith("https")) {
				httpClient = exec_ignoreSSLHttpClient();
			} else {
				httpClient = HttpClients.createDefault();
			}
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
			if ("1".equals(is_post)) {
				HttpPost httpost = new HttpPost(url);
				if (is_json.equals("0")) {
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					if (!CollectionUtils.isEmpty(inParams)) {
						for(String key : inParams.keySet()){
							list.add(new BasicNameValuePair(key, inParams.get(key) == null ? "" : String.valueOf(inParams.get(key))));
						}
					}
					UrlEncodedFormEntity urlFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
					httpost.setEntity(urlFormEntity);
					list = null;
				} else {
					httpost.setHeader("Content-Type", "application/json;charset=UTF-8");
					String json = new Gson().toJson(inParams);
					StringEntity entity = new StringEntity(json, "UTF-8");
					httpost.setEntity(entity);
				}
				httpost.setConfig(requestConfig);
				resp = httpClient.execute(httpost);
			} else {
				URIBuilder builderGet = new URIBuilder(url);
				builderGet.setCharset(Charset.forName("UTF-8"));
				if (!CollectionUtils.isEmpty(inParams)) {
					for (String key : inParams.keySet()) {
						builderGet.addParameter(key, inParams.get(key) == null ? "" : (String)inParams.get(key));
					}
				}
				HttpGet httpGet = new HttpGet(builderGet.build());
				httpGet.setConfig(requestConfig);
				resp = httpClient.execute(httpGet);
			}
			int resultCode = resp.getStatusLine().getStatusCode();
			String result = EntityUtils.toString(resp.getEntity(), "UTF-8");
			if (resultCode == 200) {
				return result;
			} else {
				throw new Exception("返回结果异常..." + resultCode+",服务器返回:"+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	private static CloseableHttpClient exec_ignoreSSLHttpClient() throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
    
}