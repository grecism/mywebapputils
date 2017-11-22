package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpExecuter {

    private static String getParamUrl(String url,Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return url;
        }
        StringBuffer param = new StringBuffer();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (null==value) {
                continue;
            }
            param.append(key);
            param.append("=");
            param.append(value);
            param.append("&");
        }
        String paramString = "";
        if (param.toString().endsWith("&")) {
            paramString = param.substring(0, param.length() - 1);
        }
        if (url.contains("?")) {
            if(url.endsWith("?")){
                url=url+paramString;
            }else {
                url=url+"&"+paramString;
            }
        }else {
            url=url+"?"+paramString;
        }
        return url;
    }

    public static String post(String url, Map<String,Object> suffixParam, Map<String,Object> params) throws Exception {
        String paramUrl = getParamUrl(url, suffixParam);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        URIBuilder uriBuilder = new URIBuilder(paramUrl);
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        List<NameValuePair> nvps = new ArrayList<>();
        if(null!=params){
            for (String s : params.keySet()) {
                nvps.add(new BasicNameValuePair(s, params.get(s).toString()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        return EntityUtils.toString(entity);
    }
}
