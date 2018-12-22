package ouc.framework.filter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.framework.config.ControllerConfig;
import ouc.framework.config.FrameworkConfigParser;
import ouc.user.controller.LoginAction;

/**
 * 框架核心控制器，基于Servlet Filter实现
 * 
 * @author xiaodong
 *
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/*" }) // 所有的请求均被该过滤器先拦截
public class FrameworkFilter implements Filter {

	Map<String, ControllerConfig> controllerConfigs = null;

	public FrameworkFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {

		String monkeyConfigFilePath = fConfig.getServletContext().getRealPath("/") + "WEB-INF/monkey.xml";
		File file = new File(monkeyConfigFilePath);
		controllerConfigs = FrameworkConfigParser.getControllerConfigs(file);
	}

	/*
	 * 核心过滤方法，在此完成将用户特定请求交给特定业务控制器进行处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("当前过滤器对象的Hash Code为 " + this.hashCode());

		HttpServletRequest httpRequest = (HttpServletRequest) request; // 请求造型为HttpServletRequest类型
		HttpServletResponse httpResponse = (HttpServletResponse) response; // 响应造型为HttpServletResponse类型

		System.out.println(httpRequest.getRequestURI() + " 经过过滤器 " + this.getClass() + " 处理");

		String currentPath = httpRequest.getServletPath();
		System.out.println("当前请求资源路径为 " + currentPath);

		String currentMethod = httpRequest.getMethod();
		System.out.println("当前请求方法为 " + currentMethod);
		System.out.println("当前请求参数字符串为 " + httpRequest.getQueryString());

		/*
		 * 作为一个框架的目标是将以下代码写活，适应各请求的情况，该怎么做？现在的代码是死的，我们周五实验课再改活
		 */
		/* ********************************************** */
		// 用于处理资源路径为/login的用户请求
		// 创建LoginAction的对象，通过调用该对象的set方法将从HttpServletRequest对象中的数据进行传递
		/*
		if ("/login".equals(currentPath)) {
			LoginAction action = new LoginAction();
			// 创建用户控制器的一个实例 
			action.setUsername(httpRequest.getParameter("username"));
			action.setPassword(httpRequest.getParameter("password"));
		if (action.execute().equals("success")) {
			httpRequest.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
		};
			
		
		}
		*/
		

		/* ********************************************** */
		String controllerName = currentPath.substring(1);
		System.out.println("[Controller Name] " + controllerName);

		String classPath = controllerConfigs.get(controllerName).getControllerClass();
		System.out.println("[Controller Class] " + classPath);

		Class<?> clazz;

		try {
			clazz = Class.forName(classPath);
			Object action = clazz.newInstance();

			for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
				// System.out.println(clazz.getDeclaredFields()[i].getName());
				clazz.getMethod("set" + toUpperCaseFirstOne(clazz.getDeclaredFields()[i].getName()),
						clazz.getDeclaredFields()[i].getType())
						.invoke(action, httpRequest.getParameter(clazz.getDeclaredFields()[i].getName()));
			}

			String statusCode = (String) clazz.getMethod("execute").invoke(action);

			httpRequest.getRequestDispatcher(controllerConfigs.get(controllerName).getResults().get(statusCode))
					.forward(httpRequest, httpResponse);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// chain.doFilter(request, response);
	}

	public void destroy() {
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

}
