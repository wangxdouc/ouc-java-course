package ouc.javaweb.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.javaweb.dao.UserDao;
import ouc.javaweb.dao.impl.UserDaoImpl;
import ouc.javaweb.model.User;
import ouc.javaweb.util.sec.SecUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!password.equals(password2)) {
			response.sendRedirect("user/register.html");
		}

		User user = new User();

		user.setName(username);
		user.setPassword(password);
		user.setPasswordMd5(SecUtil.md5(password));

		UserDao userDao = new UserDaoImpl();

		// 解决中文响应乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");


		try {
			userDao.insert(user);
			response.getWriter().append("用户" + username + " 注册成功!");

		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("注册失败！");
		}

	}

}
