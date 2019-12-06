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

@WebServlet("/music")
public class MusicStreamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

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
			int len_l = (int) f.length();

			response.setHeader("Content-Type", "audio/mpeg");
			if (f.exists()) {

				fis = new FileInputStream(f);
				byte[] buf = new byte[2048];
				ServletOutputStream out = response.getOutputStream();

				len_l = fis.read(buf);

				while (len_l != -1) {
					out.write(buf, 0, len_l);
					len_l = fis.read(buf);
				}

				out.flush();
				out.close();
				fis.close();
			} else {
				Writer out = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "The file " + filePath + " does not exist! ");

				out.write(jsonObject.toString());
				out.flush();
				out.close();
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
