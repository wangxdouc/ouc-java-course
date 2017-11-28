package music.client;

import ouc.cs.course.java.httpclient.FileDownloader;

/**
 * FileDownloader 测试类
 */
public class FileDownloaderTest {

	public static void main(String[] args) {
		FileDownloader.downloadMusicFile("http://service.uspacex.com/music.server/downloadMusic",
				"1e659b0eefb3e1bb796e93cfe0710a9c", "/Users/xiaodong/Downloads");

		FileDownloader.downloadMusicSheetPicture("http://service.uspacex.com/music.server/downloadPicture",
				"235edc3a68144beb8e8980e59941c470", "/Users/xiaodong/Downloads");
	}
}