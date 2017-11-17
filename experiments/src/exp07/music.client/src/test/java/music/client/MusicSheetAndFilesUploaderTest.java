package music.client;

import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.httpclient.MusicSheet;
import ouc.cs.course.java.httpclient.MusicSheetAndFilesUploader;

public class MusicSheetAndFilesUploaderTest {
	
	public static void main(String[] args) {
		String url = "http://localhost:8080/music.server/upload";
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("/Users/xiaodong/Music/Beyond/无声的告别 - Beyond.mp3");
		filePaths.add("/Users/xiaodong/Music/Beyond/AMANI.mp3");
		filePaths.add("/Users/xiaodong/Music/Beyond/命运是你家 - Beyond.mp3");
		filePaths.add("/Users/xiaodong/Music/Beyond/长城.mp3");

		MusicSheet ms = new MusicSheet();
		ms.setUuid("cddc055bfa33439a889cb611c1cb6db2");
		ms.setCreatorId("2011022");
		ms.setPicture("/Users/xiaodong/Temp/test01.png");
		ms.setCreator("王晓东");
		ms.setName("永远的Beyond乐队");

		MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(url, ms, filePaths);
	}
}
