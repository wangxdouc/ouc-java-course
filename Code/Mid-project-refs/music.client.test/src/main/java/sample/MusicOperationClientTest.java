package sample;

import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.model.MusicSheet;
import ouc.cs.course.java.musicclient.MusicOperationClient;

public class MusicOperationClientTest {
	public static void main(String[] args) {

		/*
		 * 创建MusicOperationClient对象
		 */
		MusicOperationClient moc = new MusicOperationClient();

		/*
		 * 音乐单及音乐上传测试，创建MusicSheet对象时，将其uuid设置为服务器已经存在的，会更新该音乐单
		 */
		/*
		List<String> filePaths = new ArrayList<String>();

		filePaths.add("/Users/xiaodong/Music/Rock_Chinese/黑豹 - 别去糟蹋.mp3");
		filePaths.add("/Users/xiaodong/Music/Rock_Chinese/黑豹 - 无地自容.mp3");

		MusicSheet ms = new MusicSheet();
		ms.setCreatorId("2011022");
		ms.setPicture("/Users/xiaodong/Music/Rock_Chinese/fig-heibao-band.jpg");
		ms.setCreator("王晓东");
		ms.setName("无地自容的我");

		moc.createMusicSheetAndUploadFiles(ms, filePaths);
		*/
		
		/*
		 * 获取所有音乐单测试
		 */
		for (MusicSheet musicSheet : moc.queryAllMusicSheets()) {
			System.out.println(musicSheet.getUuid());
		}

		/*
		 * 下载uuid为ea601e51fd4346aa85d9c239366e8a29的音乐单封面图片测试
		 */
		moc.downloadMusicSheetPicture("ea601e51fd4346aa85d9c239366e8a29", "/Users/xiaodong/Desktop");

		/*
		 * 下载md5为332d4b51d6b7b410bf829df2654c5c0e的音乐文件测试
		 */
		moc.downloadMusicFile("332d4b51d6b7b410bf829df2654c5c0e", "/Users/xiaodong/Desktop");
	}
}
