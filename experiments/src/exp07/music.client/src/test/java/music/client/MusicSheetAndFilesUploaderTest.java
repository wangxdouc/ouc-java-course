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
		ms.setUuid("f4d15f962976458d93975ab99562870e");
		ms.setCreatorId("2011022 NB");
		ms.setPicture("/Users/xiaodong/Temp/test01.png");
		ms.setCreator("Wang Xiaodong NB");
		ms.setName("Forever Beyond Band");

		MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(url, ms, filePaths);
	}
}
