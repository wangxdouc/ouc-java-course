package ouc.cs.course.java.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import ouc.cs.course.java.model.MusicSheet;

public class MusicSheetUploader {

	public MusicSheetUploader() {
	}

	/**
	 * 功能：对url和musicSheet进行完备性检查
	 */
	public static boolean dataCheck(String url, MusicSheet musicSheet) {
		return true;
	}

	/**
	 * 功能：音乐单上传
	 */
	public static String uploadMusicSheet(String url, MusicSheet musicSheet) throws Exception {

		if (!dataCheck(url, musicSheet)) {
			throw new Exception("Format Excpetion, please check url and musicSheet!");
		}

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		JSONObject jsonParam = new JSONObject();
		jsonParam.put("creatorId", musicSheet.getCreatorId());
		jsonParam.put("creator", musicSheet.getCreator());
		jsonParam.put("name", musicSheet.getName());
		jsonParam.put("dateCreated", musicSheet.getDateCreated());
		jsonParam.put("picture", musicSheet.getPicture());
		jsonParam.put("musicItems", musicSheet.getMusicItems());

		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = resp.getEntity();
			respContent = EntityUtils.toString(httpEntity, "UTF-8");
		}
		return respContent;
	}

}
