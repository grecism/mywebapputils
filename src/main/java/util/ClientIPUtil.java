package util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *<p>Title	: ClientIPUtil</p>
 * @Description	: 获取客户端IP(真实IP地址)
 * @author	: admin
 * @date	: 2017年11月22日上午9:34:57
 */
public class ClientIPUtil {
	
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<String>();

    /**
     * 
     * @Description : 真实IP地址
     * @param request
     * @return
     */
    public static String getRealClientIP(HttpServletRequest request) {
        String clientIP = "";
        String remote_addr = request.getRemoteAddr();
        String x_forwarded_for = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(x_forwarded_for)) {
            clientIP = remote_addr;
        } else {
            clientIP = x_forwarded_for;
        }
        LOCAL.set(clientIP);
        return clientIP;
    }

}
