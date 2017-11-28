package ouc.cs.course.java.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import ouc.cs.course.java.model.MusicSheet;

/**
 * MusicSheetTaker
 * 
 * 查询所有音乐单，返回音乐单对象列表
 */
public class MusicSheetTaker {

	public MusicSheetTaker() {
	}

	/**
	 * 查询所有音乐单（HTTP端口默认为80）
	 * 
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static List<MusicSheet> queryMusicSheets(String url) throws HttpException, IOException {
		return queryMusicSheets(url, 80);
	}

	/**
	 * 查询所有音乐单
	 * 
	 * @param url
	 * @param port
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static List<MusicSheet> queryMusicSheets(String url, int port) throws HttpException, IOException {

		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(url, port);
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);

		// 打印服务器返回的状态
		System.out.println(method.getStatusLine());

		// 打印返回的信息
		InputStream bodystreams = method.getResponseBodyAsStream();
		JSONObject jsonBody = new JSONObject(convertStreamToString(bodystreams));
		JSONArray jsonMusicSheetList = (JSONArray) jsonBody.get("musicSheetList");

		JSONObject jms = null;
		List<MusicSheet> mss = new ArrayList<MusicSheet>();
		MusicSheet ms = null;
		Map<String, String> mum = null;

		for (Object musicSheetObj : jsonMusicSheetList) {
			jms = new JSONObject(musicSheetObj.toString());
			ms = new MusicSheet();
			ms.setUuid((String) jms.get("uuid"));
			ms.setName((String) jms.get("name"));
			ms.setCreator((String) jms.get("creator"));
			ms.setCreatorId((String) jms.get("creatorId"));
			ms.setDateCreated((String) jms.get("dateCreated"));
			ms.setPicture((String) jms.get("picture"));
			JSONObject mumObj = new JSONObject(jms.get("musicItems"));
			Iterator<String> keys = mumObj.keys();
			mum = new HashMap<String, String>();

			while (keys.hasNext()) {
				String key = keys.next();
				mum.put(key, mumObj.getString(key));
			}

			ms.setMusicItems(mum);
			mss.add(ms);
		}

		// 释放连接
		method.releaseConnection();

		return mss;
	}

	/**
	 * 把InputStream转换成String返回
	 *
	 * @throws UnsupportedEncodingException
	 */
	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/** 忽略以下代码 **********************************************************************************/
	public static String doPostMethod(String url, int port) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口
		client.getHostConfiguration().setHost(url, port);
		// 使用POST方法
		PostMethod method = new PostMethod(url);
		// 设置传递参数
		// NameValuePair agent = new NameValuePair("User-Agent", "Mozilla/4.0
		// (compatible; MSIE 8.0; Windows 2000)");
		NameValuePair username = new NameValuePair("userName", "test");
		NameValuePair password = new NameValuePair("userPwd", "test");
		method.setRequestBody(new NameValuePair[] { username, password });
		// 执行请求
		client.executeMethod(method);
		// 设置cookies
		// Cookie[] cookies = client.getState().getCookies();
		// client.getState().addCookies(cookies);

		// 打印服务器返回的状态
		System.out.println(method.getStatusLine());
		// 打印返回的信息
		// System.out.println(method.getResponseBodyAsString());
		// response body of large or unknown size. Using getResponseBodyAsStream
		// instead is recommended.
		InputStream bodystreams = method.getResponseBodyAsStream();
		String body = convertStreamToString(bodystreams);
		System.out.println(body);
		// 释放连接
		method.releaseConnection();

		return body;
	}

	/********************************************************************************************/

}