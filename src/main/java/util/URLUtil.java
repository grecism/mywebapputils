package util;

import thirdparty.security.Encrypt;


/**
 * 
 *<p>Title	: URLUtil</p>
 * @Description	: 关于URL的相关工具类
 * @author	: admin
 * @date	: 2017年11月22日上午9:44:26
 */
public class URLUtil {
	/**
	 * 解析渠道导流的链接，返回[50, 10000)之间的值，即导流活动的id
	 * @param urlParam
	 * @return
	 */
	public static int parseChannelURL(String urlParam) {
		int value = -1;
		switch(urlParam.substring(0, 4)) 
		{
		case "ucSz": value = getValue(50, 400, urlParam);break;
		case "ucSi": value = getValue(400, 1000, urlParam);break;
		case "ucSa": value = getValue(1000, 1400, urlParam);break;
		case "ucSu": value = getValue(1400, 2000, urlParam);break;
		case "ucSf": value = getValue(2000, 2400, urlParam);break;
		case "ucSN": value = getValue(2400, 3000, urlParam);break;
		case "ucSt": value = getValue(3000, 3450, urlParam);break;
		case "uctz": value = getValue(3450, 4000, urlParam);break;
		case "uctv": value = getValue(4000, 4500, urlParam);break;
		case "ucta": value = getValue(4500, 5000, urlParam);break;
		case "uctc": value = getValue(5000, 5500, urlParam);break;
		case "uctf": value = getValue(5500, 6000, urlParam);break;
		case "uctS": value = getValue(6000, 6500, urlParam);break;
		case "uctt": value = getValue(6500, 7000, urlParam);break;
		case "ufzi": value = getValue(7000, 7510, urlParam);break;
		case "ufzv": value = getValue(7510, 8000, urlParam);break;
		case "ufzu": value = getValue(8000, 8580, urlParam);break;
		case "ufzc": value = getValue(8580, 9000, urlParam);break;
		case "ufzN": value = getValue(9000, 9600, urlParam);break;
		case "ufzS": value = getValue(9600, 10000, urlParam);break;
		}
		return value;
	}
	private static int getValue(int min, int max, String urlParam) {
		int value = -1;
		for(int id = min; id < max; id++) {
			if(createChannelURL(id).equals(urlParam)) {
				value = id;
				break;
			}
		}
		return value;
	}
	/**
	 * 创建一个活动连接，传入的id必须是[50,10000)之间的值, 50以下的渠道导流链接已经被占用了，如果三文钱接入超过1W家导流渠道
	 * 我宁愿重写一遍该代码，无偿的
	 * @param id
	 * @return
	 */
	public static String createChannelURL(int id) {
		String[] chars = new String[] { "j", "G", "g", "q", "F", "P", "U", "L", "x", "m", "H", "l", "k", "J", "Y", "y",
				"3", "T", "z", "I", "s", "X", "b", "O", "d", "o", "W", "0", "R", "D", "1", "K", "A", "9", "e", "B", "E",
				"4", "7", "h", "8", "n", "5", "M", "Q", "6", "2", "r", "z", "i", "v", "a", "u", "c", "f", "N", "S", "t",
				"V", "p", "C", "w" };
		
		String result = String.valueOf(String.format("%05d", id).hashCode());
		String subfix = "";
		for(char number : result.toCharArray()) {
			subfix += chars[(int)number];
		}
		return subfix;
	}
	
	/**
	 * 生成一组短链接，还需要完善，因为短链接和原链接之间并没有进行关联，可以在这组里面随机挑一个使用，以免短链接重复，还可以换其它的
	 */
	public static String[] shortURL(String url) {
		String prefix = "sAnWeNqIan";
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		String[] urls = new String[4]; 
		//生成一个32位长度的码
		String md5Url = Encrypt.MD5(prefix + url);
		
		//每8为一组，转为16进制，然后与0x3FFFFFFF进行按位与操作, 以下方法可以生产4组短链接
		for(int i = 0; i < 4; i++) {
			String group = md5Url.substring(i * 8,  i * 8 + 8);
			long result = Long.parseLong(group, 16) & 0x3FFFFFFF;
			
			//将得到的结果与0x0000003D进行按位与操作，得到一个值作为chars数组的下标值，取到该字符，一共取6个字符作为最终的URL
			String newURL = "";
			for(int j = 0; j < 6; j++) {
				long index = result & 0x0000003D;
				newURL += chars[(int)index];
				
				//每次循环，将result右移5位
				result = result >> 5;
			}
			urls[i] = newURL;
		}
		return urls;
	}
	
	/**
	 * 生成一个短链接，还需要完善，因为短链接和原链接之间并没有进行关联
	 * @param url
	 * @return
	 */
	public static String shortOneURL(String url) {
		String prefix = "sAnWeNqIan";
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		//生成一个32位长度的码
		String md5Url = Encrypt.MD5(prefix + url);
		
		//每4为一组，取每组的第一个字符组成一个8位的字符串，转为16进制，然后与0x3FFFFFFF进行按位与操作, 以下方法可以生产4组短链接
		String group = "";
		for(int i = 0; i < 8; i++) {
			group += md5Url.substring(i * 4,  i * 4 + 1);
		}
		long result = Long.parseLong(group, 16) & 0x3FFFFFFF;
		
		//将得到的结果与0x0000003D进行按位与操作，得到一个值作为chars数组的下标值，取到该字符，一共取6个字符作为最终的URL
		String newURL = "";
		for(int j = 0; j < 6; j++) {
			long index = result & 0x0000003D;
			newURL += chars[(int)index];
			
			//每次循环，将result右移5位
			result = result >> 5;
		}
		return newURL;
	}
}
