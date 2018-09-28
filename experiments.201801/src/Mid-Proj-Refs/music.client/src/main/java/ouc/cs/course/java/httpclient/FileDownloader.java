package ouc.cs.course.java.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class FileDownloader {
	private static final int SUCCESS = 200;

	public static void downloadMusicFile(String url, String fileMd5, String targetPath) {
		downloadMusicFile(url, fileMd5, targetPath, null);
	}

	public static void downloadMusicFile(String url, String fileMd5, String targetPath, String fileName) {
		HttpClient client = new HttpClient();
		GetMethod get = null;
		FileOutputStream output = null;
		String filename = null;

		try {
			get = new GetMethod(url + "?md5=" + fileMd5);
			int i = client.executeMethod(get);

			if (SUCCESS == i) {
				if (fileName == null) {
					filename = java.net.URLDecoder
							.decode(get.getResponseHeader("Content-Disposition").getValue().substring(21), "UTF-8");
					System.out.println("[The file name getting from HTTP HEADER] " + filename);
				} else {
					filename = fileName;
				}

				File storeFile = new File(targetPath + "/" + filename);
				output = new FileOutputStream(storeFile);
				output.write(get.getResponseBody());
			} else {
				System.out.println("DownLoad file failed with error code: " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			get.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
	}

	public static void downloadMusicSheetPicture(String url, String musicSheetUuid, String targetPath) {
		HttpClient client = new HttpClient();
		GetMethod get = null;
		FileOutputStream output = null;
		String filename = null;

		try {
			get = new GetMethod(url + "?uuid=" + musicSheetUuid);
			int i = client.executeMethod(get);

			if (SUCCESS == i) {
				filename = java.net.URLDecoder
						.decode(get.getResponseHeader("Content-Disposition").getValue().substring(21), "UTF-8");
				System.out.println("[The file name getting from HTTP HEADER] " + filename);

				File storeFile = new File(targetPath + "/" + filename);
				output = new FileOutputStream(storeFile);
				output.write(get.getResponseBody());
			} else {
				System.out.println("DownLoad file failed with error code: " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			get.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
	}
}
