package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;

import config.Const;
import config.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 *<p>Title	: RequestUtils</p>
 * @Description	: HttpServletRequest
 * @author	: admin
 * @date	: 2017年11月22日上午9:41:13
 */
public class RequestUtils {
	private final static Log logger = LogFactory.getLog(RequestUtils.class);
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	/**
	 * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的，
	 * 那么将通过HttpServletRequest#getParameter获取。
	 * 
	 * @param request
	 *            web请求
	 * @param name
	 *            参数名称
	 * @return
	 */
	public static String getQueryParam(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		if (request.getMethod().equalsIgnoreCase(Constants.POST)) {
			return request.getParameter(name);
		}
		String s = request.getQueryString();
		if (StringUtils.isBlank(s)) {
			return null;
		}
		try {
			s = URLDecoder.decode(s, Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error("encoding " + Constants.UTF8 + " not support?", e);
		}
		String[] values = parseQueryString(s).get(name);
		if (values != null && values.length > 0) {
			return values[values.length - 1];
		} else {
			return null;
		}
	}
	
	public static Map<String, Object> getQueryParams(HttpServletRequest request) {
		Map<String, String[]> map;
		if (request.getMethod().equalsIgnoreCase(Constants.POST)) {
			map = request.getParameterMap();
		} else {
			String s = request.getQueryString();
			if (StringUtils.isBlank(s)) {
				return new HashMap<>();
			}
			try {
				s = URLDecoder.decode(s, Constants.UTF8);
			} catch (UnsupportedEncodingException e) {
				logger.error("encoding " + Constants.UTF8 + " not support?", e);
			}
			map = parseQueryString(s);
		}
		
		Map<String, Object> params = new HashMap<>(map.size());
		int len;
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			len = entry.getValue().length;
			if (len == 1) {
				params.put(entry.getKey(), entry.getValue()[0]);
			} else if (len > 1) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		return params;
	}

	/**
	 * 
	 * Parses a query string passed from the client to the server and builds a
	 * <code>HashTable</code> object with key-value pairs. The query string
	 * should be in the form of a string packaged by the GET or POST method,
	 * that is, it should have key-value pairs in the form <i>key=value</i>,
	 * with each pair separated from the next by a &amp; character.
	 * 
	 * <p>
	 * A key can appear more than once in the query string with different
	 * values. However, the key appears only once in the hashtable, with its
	 * value being an array of strings containing the multiple values sent by
	 * the query string.
	 * 
	 * <p>
	 * The keys and values in the hashtable are stored in their decoded form, so
	 * any + characters are converted to spaces, and characters sent in
	 * hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
	 * 
	 * @param s
	 *            a string containing the query to be parsed
	 * 
	 * @return a <code>HashTable</code> object built from the parsed key-value
	 *         pairs
	 * 
	 * @exception IllegalArgumentException
	 *                if the query string is invalid
	 * 
	 */
	public static Map<String, String[]> parseQueryString(String s) {
		String valArray[] = null;
		if (s == null) {
			throw new IllegalArgumentException();
		}
		Map<String, String[]> ht = new HashMap<>();
		StringTokenizer st = new StringTokenizer(s, "&");
		while (st.hasMoreTokens()) {
			String pair = (String) st.nextToken();
			int pos = pair.indexOf('=');
			if (pos == -1) {
				continue;
			}
			String key = pair.substring(0, pos);
			String val = pair.substring(pos + 1, pair.length());
			if (ht.containsKey(key)) {
				String oldVals[] = (String[]) ht.get(key);
				valArray = new String[oldVals.length + 1];
				for (int i = 0; i < oldVals.length; i++) {
					valArray[i] = oldVals[i];
				}
				valArray[oldVals.length] = val;
			} else {
				valArray = new String[1];
				valArray[0] = val;
			}
			ht.put(key, valArray);
		}
		return ht;
	}

	public static Map<String, String> getRequestMap(HttpServletRequest request,
			String prefix) {
		return getRequestMap(request, prefix, false);
	}

	public static Map<String, String> getRequestMapWithPrefix(
			HttpServletRequest request, String prefix) {
		return getRequestMap(request, prefix, true);
	}

	private static Map<String, String> getRequestMap(
			HttpServletRequest request, String prefix, boolean nameWithPrefix) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> names = request.getParameterNames();
		String name, key, value;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			if (name.startsWith(prefix)) {
				key = nameWithPrefix ? name : name.substring(prefix.length());
				value = StringUtils.join(request.getParameterValues(name), ',');
				map.put(key, value);
			}
		}
		return map;
	}

	public static String getRequestPath(HttpServletRequest request){
		try {
			if(request == null){
				request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
									.getRequest();
			}
			return request.getScheme()+"://"+request.getServerName()
						+":"+request.getServerPort()+request.getContextPath();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		}
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 获取IP地址，无法获取到request对象时，使用此方法
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr() {
		try {
			return getIpAddr(null);
		} catch (Exception e) {
			return "127.0.0.1";
		}
	}

	/**
	 * 获得当的访问路径
	 * 
	 * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
	 * 
	 * @param request
	 * @return
	 */
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}

	

	/**
	 * 获取域名
	 * 
	 * @param request
	 * @return
	 */
	public static String getDomain(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String domainUrl = url
				.delete(url.length() - request.getRequestURI().length(),
						url.length()).append("/").toString();
		return domainUrl;
	}

	
	/**
	 * 获取操作系统
	 * @param args
	 */
	public static  String  getOs(HttpServletRequest request){
		String  browserDetails  =   request.getHeader("User-Agent");
//		String[] browserDetails1 = browserDetails.split(" ");
//		System.out.println(browserDetails);
        String  userAgent       =   browserDetails;
        String os = "";
        if (userAgent.toLowerCase().indexOf("nt 6.0") > 0)
        {  
            os = "Windows Vista/Server 2008";  
        } else if (userAgent.toLowerCase().indexOf("nt 5.2") > 0)
        {  
            os = "Windows Server 2003";  
        } else if (userAgent.toLowerCase().indexOf("nt 5.1") > 0)
        {  
            os = "Windows XP";  
        } else if (userAgent.toLowerCase().indexOf("nt 6.0") > 0)
        {  
            os = "Windows Vista";  
        } else if (userAgent.toLowerCase().indexOf("nt 6.1") > 0)
        {  
            os = "Windows 7";  
        } else if (userAgent.toLowerCase().indexOf("nt 6.2") > 0)
        {  
            os = "Windows Slate";  
        } else if (userAgent.toLowerCase().indexOf("nt 5") > 0)
        {  
            os = "Windows 2000";  
        } else if (userAgent.toLowerCase().indexOf("nt 4") > 0)
        {  
            os = "Windows NT4";  
        } else if (userAgent.toLowerCase().indexOf("98") > 0){  
            os = "Windows 98";  
        } else if (userAgent.toLowerCase().indexOf("95") > 0){  
            os = "Windows 95";  
        } 
        else if (userAgent.toLowerCase().indexOf("nt 10") > 0){  
            os = "Windows 10";  
        }else if (userAgent.toLowerCase().indexOf("sunos") > 0)
        {  
            os = "SunOS";  
        }  
         else if(userAgent.toLowerCase().indexOf("mac") >= 0)
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().indexOf("unix") >= 0)
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().indexOf("linux") >= 0)
        {
            os = "Linux";
        }
        else if(userAgent.toLowerCase().indexOf("android") >= 0)
        {
            os = "Android";
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        
        return os;
	}
	
	/**
	 * 获取浏览器
	 * @param args
	 */
	public static  String  getBrowser(HttpServletRequest request){
		String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String browser = "";
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE";
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        return browser;
	}
	    
    /**
     * <B>方法名称：</B>获取客户端Cookie信息<BR>
     * <B>概要说明：</B>根据指定的名称，获取客户端Cookie信息。<BR>
     * 
     * @param request 请求
     * @param name Cookie名
     * @return Cookie Cookie信息
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    /**
     * <B>方法名称：</B>获取客户端Cookie值<BR>
     * <B>概要说明：</B>根据指定的名称，获取客户端Cookie值。<BR>
     * 
     * @param request 请求
     * @param name Cookie名
     * @return String Cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookieByName(request, name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }
    
    /**
     * <B>方法名称：</B>获取Cookie验证值<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @return String Cookie验证值
     */
    public static String getValidateKey(HttpServletRequest request) {
        return getCookieValue(request, Const.COOKIE_VALIDATE_KEY);
    }
	    
    /**
     * <B>方法名称：</B>获取会话属性值<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @param name 属性名
     * @return String 属性值
     */
    public static String getSessionValue(HttpServletRequest request, String name) {
        Object attr = request.getSession().getAttribute(name);
        if (attr == null) {
            return null;
        }
        return (String) attr;
    }

    /**
     * <B>方法名称：</B>获取客户端信息<BR>
     * <B>概要说明：</B>根据请求获取客户端信息。<BR>
     * 
     * @param request 请求
     * @return String 客户端信息
     */
    public static String getClientInfo(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * <B>方法名称：</B>判断客户端是否为Firefox<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @return boolean 是否为Firefox
     */
    public static boolean isFirefox(HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT").toUpperCase();
        return (!StringUtils.isBlank(agent) && agent.indexOf("FIREFOX") > 0);
    }

    /**
     * <B>方法名称：</B>获取上传文件<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @return MultipartFile 上传文件
     */
    public static MultipartFile getUploadFile(HttpServletRequest request) {
        return getUploadFile(request, "file");
    }

    /**
     * <B>方法名称：</B>获取上传文件<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @param name 文件名
     * @return MultipartFile 上传文件
     */
    public static MultipartFile getUploadFile(HttpServletRequest request, String name) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        return multipartRequest.getFile(name);
    }

    /**
     * <B>方法名称：</B>保存上传文件<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 请求
     * @param path 保存路径
     * @throws IOException 预想外异常错误
     */
    public static void saveUploadFile(HttpServletRequest request, String path) throws IOException {
        MultipartFile file = getUploadFile(request);
        String sep = System.getProperty("file.separator");
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String fullPath = path + sep + file.getOriginalFilename();
        File uploadedFile = new File(fullPath);
        file.transferTo(uploadedFile);
    }
	
//	/**
//	 * 获取浏览器名字和版本号
//	 * @param args
//	 */
//	public static Map<String, String> getBrowserVersion(HttpServletRequest request,
//			String Version) {
//		String  browserDetails  =   request.getHeader("User-Agent");
//        String  userAgent       =   browserDetails;
//        String  user            =   userAgent.toLowerCase();
//		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//	    Browser browser = userAgent.getBrowser();
//	}
//	
	
	
	public static void main(String[] args) {
	}
}
