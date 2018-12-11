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

@WebServlet("/admin/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsersServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao userDao = new UserDaoImpl();

		try {
			request.setAttribute("users", userDao.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("users.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
