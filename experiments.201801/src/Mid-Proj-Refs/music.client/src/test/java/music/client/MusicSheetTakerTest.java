package music.client;

import ouc.cs.course.java.httpclient.MusicSheetTaker;
import ouc.cs.course.java.model.MusicSheet;

/**
 * MusicSheetTaker 测试类
 */
public class MusicSheetTakerTest {
	private static final String URL = "http://service.uspacex.com/music.server/queryMusicSheets?type=all";

	public static void main(String[] args) throws Exception {

		/**
		 * 查询获取所有音乐单的UUID和名称
		 * 
		 */
		for (MusicSheet ms : MusicSheetTaker.queryMusicSheets(URL)) {
			System.out.println("[UUID] " + ms.getUuid() + "\t[Music sheet name] " + ms.getName());
		}

	}
}