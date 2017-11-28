package ouc.cs.course.java.musicclient;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import ouc.cs.course.java.httpclient.FileDownloader;
import ouc.cs.course.java.httpclient.MusicSheetAndFilesUploader;
import ouc.cs.course.java.httpclient.MusicSheetTaker;
import ouc.cs.course.java.model.MusicSheet;

/**
 * MusicOperationClient 与music.server进行互操作的类
 * 
 * @author xiaodong
 *
 */
public class MusicOperationClient {

	private final String URL_DownloadMusicFile = "http://service.uspacex.com/music.server/downloadMusic";
	private final String URL_DownloadMusicSheetPicture = "http://service.uspacex.com/music.server/downloadPicture";
	private final String URL_CreateMusicSheetAndUploadFiles = "http://service.uspacex.com/music.server/upload";
	private final String URL_QueryAllMusicSheets = "http://service.uspacex.com/music.server/queryMusicSheets?type=all";

	public MusicOperationClient() {
	}

	/**
	 * createMusicSheetAndUploadFiles 在服务器上创建音乐单并上传音乐文件
	 * 
	 * @param musicSheet
	 *            MusicSheet对象
	 * @param musicFilePaths
	 *            上传的音乐列表对象，列表中的项为音乐在本地磁盘的路径字符串
	 */
	public void createMusicSheetAndUploadFiles(MusicSheet musicSheet, List<String> musicFilePaths) {
		MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(URL_CreateMusicSheetAndUploadFiles, musicSheet,
				musicFilePaths);
	}

	/**
	 * queryAllMusicSheets 从服务器查询所有音乐单，返回MusicSheet类型对象列表
	 * 
	 * @return List<MusicSheet>
	 */
	public List<MusicSheet> queryAllMusicSheets() {
		try {
			return MusicSheetTaker.queryMusicSheets(URL_QueryAllMusicSheets);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * downloadMusicFile 下载音乐文件，需要传入预下载音乐文件的md5及本地保存路径；本地保存路径为本地目录，不需要包含文件名
	 * 
	 * @param fileMd5
	 *            音乐文件md5字符串
	 * @param targetPath
	 *            音乐文件本地存储路径
	 */
	public void downloadMusicFile(String fileMd5, String targetPath) {
		FileDownloader.downloadMusicFile(URL_DownloadMusicFile, fileMd5, targetPath);
	}

	/**
	 * downloadMusicSheetPicture
	 * 下载音乐单封面图片，需要传入预下载图片的音乐单uuid及本地保存路径；本地保存路径为本地目录，不需要包含文件名
	 * 
	 * @param musicSheetUuid
	 *            音乐单uuid
	 * @param targetPath
	 *            音乐单封面图片的本地保存路径
	 */
	public void downloadMusicSheetPicture(String musicSheetUuid, String targetPath) {
		FileDownloader.downloadMusicSheetPicture(URL_DownloadMusicSheetPicture, musicSheetUuid, targetPath);
	}
}
