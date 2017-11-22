package config;

public class RedisConfig {
	/**==============================用户有关信息===================================*/
	/**
	 * user_index对象前缀,后面拼接用户id
	 * 类型:String
	 * 返回UserIndex对象
	 */
	public static final String KEY_USER_INDEX_PREFIX = "user_index_";
	
	/**
	 * user_info对象前缀,后面拼接用户id
	 * 类型:String
	 * 返回UserInfo对象
	 */
	public static final String KEY_USER_INFO_PREFIX = "user_info_";

	
	/**==============================用户登录注册相关===================================*/
	/**
	 * 用户注册时存储动态码的key,后面拼接手机号
	 * 类型:String
	 * 返回登录/注册时mobile对应的动态码
	 */
	public static final String KEY_USER_REGISTER_DYNAMIC = "register_dynamic_";
	
}
