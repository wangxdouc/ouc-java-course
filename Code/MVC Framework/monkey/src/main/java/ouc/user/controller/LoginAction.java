package ouc.user.controller;

/**
 * LoginAction 用户登录业务控制器
 * 
 * 特点：<br>
 * 1.POJO，与Servlet API解耦<br>
 * 2.使用get和set方法获取和设置参数<br>
 * 3.约定使用execute()方法执行业务逻辑<br>
 * 
 * @author xiaodong
 *
 */
public class LoginAction {

	private String username;
	private String password;

	public LoginAction() {

	}

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

	public String execute() {
		System.out.println("当前类型为 " + this.getClass() + " 的业务控制器的Hash Code为 " + this.hashCode());
		System.out.println("当前登录用户名为 " + username + "\t密码为 " + password);
		
		return "success";
	}

}
