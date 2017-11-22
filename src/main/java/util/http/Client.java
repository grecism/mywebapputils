package util.http;

import util.http.HttpUtil;
import util.http.Response;

/**
 * 
 *<p>Title	: Client</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年11月21日下午8:26:43
 */
public class Client {
    /**
     * 发送请求
     *
     * @param request request对象
     * @return Response
     * @throws Exception
     */
    public static Response execute(Request request) throws Exception {
        switch (request.getMethod()) {
            case GET:
                return HttpUtil.httpGet(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case POST_FORM:
                return HttpUtil.httpPost(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getBodys(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case POST_STRING:
                return HttpUtil.httpPost(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getStringBody(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case POST_BYTES:
                return HttpUtil.httpPost(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getBytesBody(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case PUT_STRING:
                return HttpUtil.httpPut(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getStringBody(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case PUT_BYTES:
                return HttpUtil.httpPut(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getBytesBody(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            case DELETE:
                return HttpUtil.httpDelete(request.getHost(), request.getPath(), 
                		request.getTimeout(), 
                		request.getHeaders(), 
                		request.getQuerys(),
                		request.getSignHeaderPrefixList(), 
                		request.getAppKey(), request.getAppSecret());
            default:
                throw new IllegalArgumentException(String.format("unsupported method:%s", request.getMethod()));
        }
    }
}
