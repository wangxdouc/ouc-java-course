package ouc.javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.javaweb.util.image.ImageUtil;

/**
 * 获取硬盘路径中的图片
 *
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 可以从请求字符串中获取要显示的图片名
		String imgId = request.getParameter("id");
		
		// 查询数据库获得完整的图片路径（此处临时拼凑一个）
		String imgPath = "/home/user/album/teacher/" + imgId + ".jpg"; 
		if (null != imgPath && !"".equals(imgPath.trim())) {
			ImageUtil.showImage(response, imgPath, true);
		}
	}
}
