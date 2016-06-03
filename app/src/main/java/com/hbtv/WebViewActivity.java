package com.hbtv;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hongbao.okhttp.HttpDataHandlerListener;
import com.hongbao.okhttp.HttpException;
import com.hongbao.okhttp.HttpUtil;
import com.hongbao.okhttp.ResultCallBack;
import com.hongbao.util.LU;
import com.umeng.update.UmengUpdateAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
public class WebViewActivity extends Activity implements
		HttpDataHandlerListener,ResultCallBack{
	private HttpDataHandlerListener mlistener;
	private WebView wv;
	PowerManager powerManager = null;
	WakeLock wakeLock = null;
	private boolean vh;
	private LoadingDialog mDialog;
	private String url;
	private String api;
	private String uuid;
	private int i = 0;
	//	private long errtime=30000;
	private long errtime=1000*15*1;
	//	private long normaltime=1000*60*5;
	private long normaltime=1000*60*30;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_webview);
		mDialog = LoadingDialog.createDialog(this);

//		if (!NetworkUtils.checkNetworkAvailable(WebViewActivity.this)) {
//			TU.show(WebViewActivity.this, "您的网络不稳定，请检查网络连接", Toast.LENGTH_LONG);
//			WebViewActivity.this.finish();
//			return;
//		}
		vh = getIntent().getBooleanExtra("vh", true);
		//		if(vh){
		//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//		}else{
		//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//		}
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);
		powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
		wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
		wv=(WebView)findViewById(R.id.webView);
		//如果网页中含有js
		WebSettings settings = wv.getSettings();
		//设置webView支持js
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		url = getIntent().getStringExtra("url");
		api = getIntent().getStringExtra("api");
		loadUrl();
		loadApi(uuid,10000);
	}


	private void loadUrl() {
		if (mDialog != null && !mDialog.isShowing()) {
			mDialog.show();
		}
		wv.loadUrl(url+"&guid="+uuid);
		wv.setWebViewClient(new WebViewClient(){
			//在webview本身加载新页面
			//不使用浏览器加载新页面
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				wv.loadUrl(url);
				return true;
			}
			@Override
			public void onPageFinished(WebView view, final String url) {
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				super.onPageFinished(view, url);
			}
		});
	}


	void loadApi(final String guid,long time){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				sendRequest(98899,api+"?guid="+guid,"",WebViewActivity.this);
//				sendRequest(98899,api,"{"+"guid:"+uuid+"}",WebViewActivity.this);
			}
		}, time);
	}

	@Override
	protected void onResume() {
		/**
		 * 设置为横屏
		 */
		//		if(vh){
		//			if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
		//				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//			}
		//		}else{
		//			if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
		//				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//			}
		//		}
		super.onResume();
		wakeLock.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();
		wakeLock.release();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			AppSettings.setFH(WebViewActivity.this, true);
//			startActivity(new Intent(WebViewActivity.this,MainActivity.class));
			WebViewActivity.this.finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void setHandlerData(int connectionId, Object data)
			throws JSONException {
		JSONObject obj = new JSONObject(data.toString());
//		TU.show(WebViewActivity.this, ""+obj.get("code"));
//		TU.show(WebViewActivity.this, "响应结果code："+obj.get("code"));
		if (obj.get("code").toString().equals("0")) {
			i = 0;
			loadUrl();
			loadApi(uuid,normaltime);
		}else if(obj.get("code").toString().equals("1")){
			i = 0;
			loadApi(uuid,normaltime);
		}
	}

	/**
	 * 系统发送POST请求方法
	 * @param connectionId 请求id
	 * @param params 请求参数
	 * @param listener 回调接口
	 */
	public void sendRequest(int connectionId,String url,
							String params, HttpDataHandlerListener listener) {
//		if (!NetworkUtils.checkNetworkAvailable(this)) {
//			TU.show(this, "您的网络不稳定，请检查网络连接", Toast.LENGTH_LONG);
//			AppSettings.setFH(WebViewActivity.this, true);
//			startActivity(new Intent(WebViewActivity.this,MainActivity.class));
//			WebViewActivity.this.finish();
//			return;
//		}
		this.mlistener = listener;
		LU.i("请求参数：",url+params);
		HttpUtil.getInstance().sendRequest(connectionId, url,params, WebViewActivity.this);
	}

	@Override
	public void onResponse(int connectionId, Object data) {
		try {
			mlistener.setHandlerData(connectionId, data);
			LU.i("响应参数：",data != null ? data.toString() : "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 服务器未响应
	 */
	@Override
	public void onError(int connectedId, com.squareup.okhttp.Request request,
						HttpException httpException) {
//		if (i++<=15) {
		i++;
		loadUrl();
//			TU.show(WebViewActivity.this, "服务器异常第"+i+"次请求");
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		loadApi(uuid,errtime);
//		}else{
//			TU.show(WebViewActivity.this, "服务器请求多次还是异常");
//			AppSettings.setFH(WebViewActivity.this, true);
//			startActivity(new Intent(WebViewActivity.this,MainActivity.class));
//			WebViewActivity.this.finish();
//		}
	}
}
