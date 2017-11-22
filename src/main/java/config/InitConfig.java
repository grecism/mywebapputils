package config;

public class InitConfig {
	
	public String isDemo; 				// 是否生产环境
	
	/**
	 * 发邮件的配置文件
	 */
	public String mail_from_smtp;//邮件服务器地址
	public String mail_from_port;//邮件端口
	public String mail_from_email;//邮件发送者
	public String mail_from_password;//邮件密码
	public String mail_email;//邮件接收者
	/**
	 * 运营跳转域名配置
	 */
	public String operation_domain;//运营跳转域名
	
	public void init() {
		ConfigFile.ISDEMO=getIsDemo();
		//短信
		ConfigFile.MAIL_FROM_SMTP=getMail_from_smtp();
		ConfigFile.MAIL_FROM_PORT=getMail_from_port();
		ConfigFile.MAIL_FROM_EMAIL=getMail_from_email();
		ConfigFile.MAIL_FROM_PASSWORD=getMail_from_password();
		ConfigFile.MAIL_EMAIL=getMail_email();
		//运营跳转域名
		ConfigFile.OPERATION_DOMAIN=getOperation_domain();
	}
	
	public String getMail_from_smtp() {
		return mail_from_smtp;
	}

	public void setMail_from_smtp(String mail_from_smtp) {
		this.mail_from_smtp = mail_from_smtp;
	}

	public String getMail_from_port() {
		return mail_from_port;
	}

	public void setMail_from_port(String mail_from_port) {
		this.mail_from_port = mail_from_port;
	}

	public String getMail_from_email() {
		return mail_from_email;
	}

	public void setMail_from_email(String mail_from_email) {
		this.mail_from_email = mail_from_email;
	}

	public String getMail_from_password() {
		return mail_from_password;
	}

	public void setMail_from_password(String mail_from_password) {
		this.mail_from_password = mail_from_password;
	}

	public String getMail_email() {
		return mail_email;
	}

	public void setMail_email(String mail_email) {
		this.mail_email = mail_email;
	}

	public String getIsDemo() {
		return isDemo;
	}

	public void setIsDemo(String isDemo) {
		this.isDemo = isDemo;
	}


	public String getOperation_domain() {
		return operation_domain;
	}


	public void setOperation_domain(String operation_domain) {
		this.operation_domain = operation_domain;
	}


	
}
