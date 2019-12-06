package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.service.MusicSheetService;

@WebServlet("/upload")
public class MusicSheetAndFileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Properties properties = new Properties(System.getProperties());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		ServletContext ctx = this.getServletContext();
		String path = ctx.getInitParameter("musicFilePath");

		DiskFileItemFactory factory = new DiskFileItemFactory();

		System.out.println("File Storage on Server: " + path);

		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 试图解决Linux服务器写入MySQL数据库中文文件名乱码，未成功？
		upload.setHeaderEncoding("UTF-8");

		Map<String, String> musicSheetMetadata = new HashMap<String, String>();
		Map<String, String> musicSheetFileData = new HashMap<String, String>();

		try {
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				String name = item.getFieldName();

				if (item.isFormField()) {
					String value = item.getString("utf-8");
					System.out.println("[FORM FIELD] " + name + ": " + value);
					musicSheetMetadata.put(name, value);

				} else {
					String value = item.getName();
					int start = value.lastIndexOf(properties.getProperty("file.separator"));
					String filename = value.substring(start + 1);

					// 将音乐单的封面图片名称设置为UUID + 扩展名，并更新音乐单元数据中musicSheetPicture的值
					if (name.equals("musicSheetPicture")) {
						String suffix = filename.substring(filename.lastIndexOf(".") + 1);
						filename = musicSheetMetadata.get("musicSheetUuid") + "." + suffix;
						musicSheetMetadata.put("musicSheetPicture", filename);
					}

					OutputStream out = new FileOutputStream(new File(path, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];

					System.out.print("[FILE FIELD] " + name + ": " + value);
					System.out.println(" with size: " + item.getSize());

					if (!name.equals("musicSheetPicture")) {
						musicSheetFileData.put(name, value);
					}

					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}

					in.close();
					out.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("音乐单元数据：" + musicSheetMetadata);
		System.out.println("音乐单所包含的音乐：" + musicSheetFileData);

		/**
		 * 将音乐单数据写入数据库
		 */
		MusicSheetService musicSheetService = new MusicSheetService();

		MusicSheet ms = new MusicSheet();
		ms.setUuid(musicSheetMetadata.get("musicSheetUuid"));
		ms.setName(musicSheetMetadata.get("musicSheetName"));
		ms.setCreatorId(musicSheetMetadata.get("musicSheetCreatorId"));
		ms.setCreator(musicSheetMetadata.get("musicSheetCreator"));
		ms.setDateCreated(musicSheetMetadata.get("musicSheetDateCreated"));
		ms.setPicture(musicSheetMetadata.get("musicSheetPicture"));
		ms.setMusicItems(musicSheetFileData);

		boolean token = true;

		try {
			musicSheetService.createOrUpdate(ms);
		} catch (SQLException e) {
			e.printStackTrace();
			token = false;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		Writer out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		if (token) {
			jsonObject.put("musicSheet", JSONObject.fromObject(ms));

			jsonObject.put("message", "Upload musicsheet successfully.");
		} else {
			jsonObject.put("message", "Upload musicsheet failed.");
		}
		out.write(jsonObject.toString());
		out.flush();

		// request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
