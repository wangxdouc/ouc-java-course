package ouc.cs.course.java.httpclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * 功能：通过模拟POST方式提交数据
 */
public class MusicSheetAndFilesUploader {

	/**
	 * 功能：模拟表单数据上传
	 * 
	 * 说明：contentType为空则默认采用application/octet-stream，
	 * contentType非空则采用filename匹配默认的图片类型
	 */
	@SuppressWarnings("rawtypes")
	private static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType) {

		String res = "";
		HttpURLConnection conn = null;
		// boundary是request头和上传文件内容的分隔符
		String BOUNDARY = "---------------------------13708983877";

		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());

			// TEXT
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// File
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					/**
					 * 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
					 */
					contentType = new MimetypesFileTypeMap().getContentType(file);

					/**
					 * contentType非空采用filename匹配默认的图片类型
					 */
					if (!"".equals(contentType)) {
						if (filename.endsWith(".png")) {
							contentType = "image/png";
						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
								|| filename.endsWith(".jpe")) {
							contentType = "image/jpeg";
						} else if (filename.endsWith(".gif")) {
							contentType = "image/gif";
						} else if (filename.endsWith(".ico")) {
							contentType = "image/image/x-icon";
						}
					}
					if (contentType == null || "".equals(contentType)) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("Send POST Request Error: " + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	/**
	 * 功能：上传音乐单所包含的文件，包括音乐文件和音乐单封面图片
	 */
	public static void createMusicSheetAndUploadFiles(String url, MusicSheet musicSheet, List<String> musicFilePaths) {

		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("musicSheetUuid", musicSheet.getUuid());
		textMap.put("musicSheetName", musicSheet.getName());
		textMap.put("musicSheetCreatorId", musicSheet.getCreatorId());
		textMap.put("musicSheetCreator", musicSheet.getCreator());
		textMap.put("musicSheetDateCreated", musicSheet.getDateCreated());
		textMap.put("musicSheetPicture", musicSheet.getPicture());

		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("musicSheetPicture", musicSheet.getPicture());

		Map<String, String> musicFileMap = new HashMap<String, String>();

		FileInputStream fis = null;
		String fileMd5;

		for (String filePath : musicFilePaths) {
			try {
				fis = new FileInputStream(filePath);
				fileMd5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
				fileMap.put(fileMd5, filePath);
				musicFileMap.put(fileMd5, filePath);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(fis);
			}
		}

		musicSheet.setMusicItems(musicFileMap);
		String contentType = null;
		String ret = formUpload(url, textMap, fileMap, contentType);

		System.out.println(ret);
	}
}