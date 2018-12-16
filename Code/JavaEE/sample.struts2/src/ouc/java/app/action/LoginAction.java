package ouc.java.app.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() throws Exception {

		System.out.println(getUsername());
		System.out.println(getPassword());

		if (getUsername().equals("admin") && getPassword().equals("admin")) {
			ActionContext.getContext().getSession().put("user", getUsername());
			return SUCCESS;
		} else {
			addActionError("这是使用addActionError方法添加的信息。");
			return ERROR;
		}
	}

	public void validate() {
		if (username == null || username.length() < 5 || username.length() > 20) {
			addFieldError("username", "用户名的长度不符合要求！");
		}

	}
}