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
import ouc.cs.course.java.musicserver.service.MusicSheetService;

@WebServlet("/downloadPicture")
public class MusicSheetPictureDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		MusicSheetService musicSheetService = new MusicSheetService();

		String path = this.getServletContext().getInitParameter("musicFilePath");
		String musicSheetUuid = request.getParameter("uuid");

		if (musicSheetUuid != null) {
			String filePath = null;

			try {
				filePath = musicSheetService.getMusicSheetPictureUrl(musicSheetUuid);
				File f = new File(path + "/" + filePath);

				if (f.exists()) {
					fis = new FileInputStream(f);
					String filename = URLEncoder.encode(f.getName(), "UTF-8");
					byte[] b = new byte[fis.available()];
					fis.read(b);
					response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
					ServletOutputStream out = response.getOutputStream();
					out.write(b);
					out.flush();
					out.close();
				} else {
					Writer out = response.getWriter();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("message", "The music sheet picture file " + filePath + " does not exist! ");
					out.write(jsonObject.toString());
					out.flush();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.setContentType("application/json;charset=utf-8");
			Writer out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Please set uuid correctly. ");

			out.write(jsonObject.toString());
			out.flush();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}