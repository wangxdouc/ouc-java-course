package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.service.MusicSheetService;

@WebServlet("/queryMusicSheets")
public class MusicSheetQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		boolean token = true;
		MusicSheetService musicSheetService = new MusicSheetService();
		List<MusicSheet> mslist = null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		String queryType = request.getParameter("type");

		if (queryType != null) {

			switch (queryType) {
			case "all":
				System.out.println("Show all music sheets");
				try {
					mslist = musicSheetService.getAll();
				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				break;

			case "top1":
				System.out.println("top1");
				try {
					mslist = musicSheetService.getLatest(1);
				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				break;

			case "top20":
				System.out.println("top20");
				try {
					mslist = musicSheetService.getLatest(20);
				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				break;

			case "random10":
				System.out.println("random10");
				break;

			default:
				System.out.println("default");
				break;
			}

			Writer out = response.getWriter();
			JSONObject jsonObject = new JSONObject();

			if (token) {
				jsonObject.put("musicSheetList", JSONArray.fromObject(mslist));
				jsonObject.put("message", "Get musicsheet list successfully.");
			} else {
				jsonObject.put("message", "Get musicsheet list failed.");
			}
			out.write(jsonObject.toString());
			out.flush();

		} else {
			Writer out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Please set query type correctly.");
			out.write(jsonObject.toString());
			out.flush();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
