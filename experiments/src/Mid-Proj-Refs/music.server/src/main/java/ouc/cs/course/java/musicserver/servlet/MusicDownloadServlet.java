package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.service.MusicService;

@WebServlet("/downloadMusic")
public class MusicDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MusicService musicService = new MusicService();

		ServletContext ctx = this.getServletContext();
		String path = ctx.getInitParameter("musicFilePath");

		String md5value = request.getParameter("md5");

		if (md5value == null) {
			System.out.println("Please check parameter: md5value.");
			return;
		}

		String filePath = null;

		try {
			filePath = musicService.getFilePathByMd5value(md5value);
			File f = new File(path + "/" + filePath);

			if (f.exists()) {
				fis = new FileInputStream(f);
				// 解决中文文件名下载后乱码
				String filename = URLEncoder.encode(f.getName(), "UTF-8");
				byte[] b = new byte[fis.available()];
				fis.read(b);
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");

				ServletOutputStream out = response.getOutputStream();
				out.write(b);
				out.flush();
				out.close();
			} else {
				Writer out = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "The file " + filePath + " does not exist! ");

				out.write(jsonObject.toString());
				out.flush();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
