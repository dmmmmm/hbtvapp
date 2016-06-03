package com.hongbao.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.hongbao.okhttp.HttpRequestBuilder.HttpMethod;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author huajun
 * 
 */
public class HttpUtil {

	private static HttpUtil httpManager;
	private static OkHttpClient client;
	private MyHttpHandler handler;

	public static Call sendRequst(Request request, Callback callBack) {
		Call c = client.newCall(request);
		c.enqueue(callBack);
		return c;
	}

	private HttpUtil() {
		handler = new MyHttpHandler(Looper.getMainLooper());
		client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
	}

	public static HttpUtil getInstance() {
		if (httpManager == null) {
			synchronized (HttpUtil.class) {
				httpManager = new HttpUtil();
			}
		}
		return httpManager;
	}

	/**
	 * @param url
	 * @param params
	 * @param callback
	 */
	public <T> void sendRequest(int connectionId, String url, String params,
			ResultCallBack callback) {
		sendPostRequest(connectionId, HttpMethod.POST, url, params, callback);
	}

	/**
	 * @param callback
	 */
	public <T> void sendGetRequest(int connectionId, HttpRequest request,
			ResultCallBack callback) {
		getRequest(connectionId, request, callback);
	}

	/**
	 * @param request
	 * @param callback
	 */
	public <T> void getRequest(final int connectionId, HttpRequest request,
			ResultCallBack callback) {
		if (callback == null) {
			throw new NullPointerException("callback must not be null");
		}
		final ResultCallBack myCallback = callback;
		Call c = client.newCall(request.getRequest());
		c.enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				if (response.code() == 200) {
					String body = response.body().string();
					ObjectStruct ost = new ObjectStruct(
							MyHttpHandler.SEND_SUCCESS);
					ost.callBack = myCallback;
					ost.content = body;
					ost.connectionId = connectionId;
					handler.sendMessage(ost.getMessage());
				} else {
					ObjectStruct ost = new ObjectStruct(MyHttpHandler.SEND_FAIL);
					ost.ex = new HttpException(response.code());
					ost.callBack = myCallback;
					handler.sendMessage(ost.getMessage());
				}
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				ObjectStruct ost = new ObjectStruct(MyHttpHandler.SEND_FAIL);
				ost.ex = new HttpException(arg1);
				ost.obj = arg0;
				ost.callBack = myCallback;
				handler.sendMessage(ost.getMessage());
			}
		});
	}

	/**
	 * @param callback
	 */
	public <T> void sendPostRequest(final int connectionId, HttpMethod method,
			String url, String params, ResultCallBack callback) {
		HttpRequest request = new HttpRequestBuilder().url(url)
				.addHeader("cookie", "df").method(method).build(params);
		if (callback == null) {
			throw new NullPointerException("callback must not be null");
		}
		final ResultCallBack myCallback = callback;
		Call c = client.newCall(request.getRequest());
		c.enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				if (response.code() == 200) {
					String body = response.body().string();
					ObjectStruct ost = new ObjectStruct(
							MyHttpHandler.SEND_SUCCESS);
					ost.callBack = myCallback;
					ost.content = body;
					ost.connectionId = connectionId;
					handler.sendMessage(ost.getMessage());
				} else {
					ObjectStruct ost = new ObjectStruct(MyHttpHandler.SEND_FAIL);
					ost.ex = new HttpException(response.code());
					ost.callBack = myCallback;
					handler.sendMessage(ost.getMessage());
				}
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				ObjectStruct ost = new ObjectStruct(MyHttpHandler.SEND_FAIL);
				ost.ex = new HttpException(arg1);
				ost.obj = arg0;
				ost.callBack = myCallback;
				handler.sendMessage(ost.getMessage());
			}
		});
	}

	

	class MyHttpHandler extends Handler {

		public static final int SEND_SUCCESS = 1;
		public static final int SEND_FAIL = 2;
		public static final int ON_DOWNLOAD_PROGRASS = 3;
		public static final int ON_DOWNLOAD_SUCESS = 4;
		public static final int ON_DOWNLOAD_FAIL = 5;

		public MyHttpHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			ObjectStruct ost = (ObjectStruct) msg.obj;
			int connectedId = ost.connectionId;
			if (msg.what == SEND_SUCCESS) {
				try {
					ost.callBack.onResponse(connectedId, ost.content);
				} catch (Exception ex) {
					Request request = (Request) ost.obj;
					ost.callBack.onError(connectedId, request,
							new HttpException(ex));
				}
			} else if (msg.what == SEND_FAIL) {
				Request request = (Request) ost.obj;
				ost.callBack.onError(connectedId, request, ost.ex);
			} else if (msg.what == ON_DOWNLOAD_PROGRASS) {
				ost.downLoadCallBack.onDownLoading(ost.prograss);
			} else if (msg.what == ON_DOWNLOAD_SUCESS) {
				ost.downLoadCallBack.onDownLoadSuccess();
			} else if (msg.what == ON_DOWNLOAD_FAIL) {
				ost.downLoadCallBack.onDownLoadFail(ost.ex);
			}
		}
	}

	private class ObjectStruct {
		private ResultCallBack callBack;
		private DownLoadCallBack downLoadCallBack;
		private String content;
		private HttpException ex;
		private Object obj;
		private Message msg;
		private int prograss;
		private int state;
		private int connectionId;// ����ID

		public ObjectStruct(int STATE) {
			this.msg = new Message();
			this.state = STATE;
		}

		public Message getMessage() {
			this.msg.what = state;
			msg.obj = this;
			return msg;
		}
	}
}
