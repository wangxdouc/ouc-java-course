package ouc.cs.course.java.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpPostWithJSON {
	public static String httpPostWithJSON(String url) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		// JSON方式
		String[] sheetItems = {"Song 01", "Song 02", "Song 03"};
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("userId", "00000001");
		jsonParam.put("sheetId", "1111111111");
		jsonParam.put("sheetItems", sheetItems);
		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		System.out.println();

		// 表单方式
		// List<BasicNameValuePair> pairList = new
		// ArrayList<BasicNameValuePair>();
		// pairList.add(new BasicNameValuePair("name", "admin"));
		// pairList.add(new BasicNameValuePair("pass", "123456"));
		// httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = resp.getEntity();
			respContent = EntityUtils.toString(httpEntity, "UTF-8");
		}
		return respContent;
	}

	public static void main(String[] args) throws Exception {
		String result = httpPostWithJSON("http://localhost:8080/music.server/LoginUserServlet");
		System.out.println(result);
	}
}
