package util;

import org.apache.commons.lang.StringEscapeUtils;

public class SqlFilter {
	public static String filter(String input) {
		if (input != null) {
			return input.replace(";", "；").replace("* ", "＊").replace("--", "－－").replace("'", "’").replace("+ ", "＋")
					.replace("&", "＆").replace("|", "｜").replace("update", "ｕｐｄａｔｅ").replace("drop", "ｄｒｏｐ")
					.replace("delete", "ｄｅｌｅｔｅ").replace("exec", "ｅｘｅｃ").replace("create", "ｃｒｅａｔｅ")
					.replace("execute", "ｅｘｅｃｕｔｅ").replace("where", "ｗｈｅｒｅ").replace("truncate", "ｔｒｕｎｃａｔｅ")
					.replace("insert", "ｉｎｓｅｒｔ").replace("master", "ｍａｓｔｅｒ").replace("declare", "declare")
					.replace("union", "ｕｎｉｏｎ");
		}
		
		return input;
	}
	
	public static String Escape(String input){
		if(input == null){
			return null;
		}
		
		return StringEscapeUtils.escapeSql(input);
	}
}
