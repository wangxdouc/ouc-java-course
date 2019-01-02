package ouc.javaweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.javaweb.model.Image;

/**
 * Image Servlet
 * 
 */
@WebServlet("/images")
public class ImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImagesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Image img01 = new Image("Image 01", "Image 01 details.");
		Image img02 = new Image("Image 02", "Image 02 details.");
		Image img03 = new Image("Image 03", "Image 03 details.");
		
		List<Image> images = new ArrayList<Image>();
		
		images.add(img01);
		images.add(img02);
		images.add(img03);
		
		request.setAttribute("images", images);
		
		request.getRequestDispatcher("images.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
