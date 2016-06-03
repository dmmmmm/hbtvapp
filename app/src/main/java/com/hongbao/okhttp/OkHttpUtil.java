package com.hongbao.okhttp;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ��ʱ����
 * 
 * @author dm
 * 
 */
public class OkHttpUtil {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");
	private static final OkHttpClient mOkHttpClient = new OkHttpClient();
	static {
		mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
	}

	/**
	 * �ò��Ὺ���첽�̡߳�
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Response execute(Request request) throws IOException {
		return mOkHttpClient.newCall(request).execute();
	}

	/**
	 * �����첽�̷߳�������
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(Request request, Callback responseCallback) {
		mOkHttpClient.newCall(request).enqueue(responseCallback);
	}

	/**
	 * �����첽�̷߳�������, �Ҳ����ⷵ�ؽ����ʵ�ֿ�callback��
	 * 
	 */
	public static void sendPostRequest(String url, String jsonParams) {
		RequestBody body = RequestBody.create(JSON, jsonParams);
		Request request = new Request.Builder().url(url).post(body).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				System.out.println("result>>" + response.body().toString());
				onSuccess(0, response);
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {

			}
		});
	}

	public static String getStringFromServer(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = execute(request);
		if (response.isSuccessful()) {
			String responseUrl = response.body().string();
			return responseUrl;
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}

	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * ����ʹ����HttpClinet��API��ֻ��Ϊ�˷���
	 * 
	 * @param params
	 * @return
	 */
	public static String formatParams(List<BasicNameValuePair> params) {
		return URLEncodedUtils.format(params, CHARSET_NAME);
	}

	/**
	 * ΪHttpGet �� url �������Ӷ��name value ������
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String attachHttpGetParams(String url,
			List<BasicNameValuePair> params) {
		return url + "?" + formatParams(params);
	}

	/**
	 * ΪHttpGet �� url ��������1��name value ������
	 * 
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public static String attachHttpGetParam(String url, String name,
			String value) {
		return url + "?" + name + "=" + value;
	}

	private static void onSuccess(int connectionId, Response response)
			throws IOException {
		if (response.code() == 200) {
			System.out.println("result>>" + response.body().toString());
			return;
		}
		onFailure(connectionId);
	}

	private static void onFailure(int connectionId) throws IOException {
		System.out.println("onFailure" + connectionId);
	}
}
