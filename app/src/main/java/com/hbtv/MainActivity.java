package com.hbtv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
public class MainActivity extends Activity {
	PowerManager powerManager = null;
	WakeLock wakeLock = null;
	boolean flag = true;
	private EditText et_ip;
	private EditText et_port;
	private EditText et_xwh;
	private RadioGroup rg;
	private RadioButton rb1;
	private RadioButton rb2;
	private long exitTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
		wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
		et_ip = (EditText)findViewById(R.id.ip);
		String asip = AppSettings.getServerIP(this);
		et_ip.setText(asip);
		et_port = (EditText)findViewById(R.id.port);
		String asport = AppSettings.getServerPort(this);
		et_port.setText(asport);
		et_xwh = (EditText)findViewById(R.id.xwh);
		String asxwh = AppSettings.getXWH(this);
		et_xwh.setText(asxwh);
		rg = (RadioGroup)findViewById(R.id.rg);
		rb1 = (RadioButton)findViewById(R.id.rb1);
		rb1.setChecked(AppSettings.isHP(this));
		rb2 = (RadioButton)findViewById(R.id.rb2);
		rb2.setChecked(!AppSettings.isHP(this));

//		if (!AppSettings.isFH(this)) {
		if (!SU.isEmpty(asip)&&!SU.isEmpty(asport)&&!SU.isEmpty(asxwh)) {
//				AppSettings.setFH(this, false);
			String ip = et_ip.getText().toString();
			String port = et_port.getText().toString();
			String xwh = et_xwh.getText().toString();
			String o = AppSettings.isHP(this)==true?"h":"v";
			Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
			intent.putExtra("vh", AppSettings.isHP(this));
			intent.putExtra("url", "http://"+ip+":"+port+"/tvshow"+o+".html?roomid="+xwh);
			intent.putExtra("api", "http://"+ip+":"+port+"/api.ashx");
			startActivity(intent);
			MainActivity.this.finish();
			return;
		}
//		}




		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == rb1.getId()) {
					flag = true;
				}
				if (checkedId == rb2.getId()) {
					flag = false;
				}
			}
		});

		((Button)findViewById(R.id.btn_login)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String ip = et_ip.getText().toString();
				String port = et_port.getText().toString();
				String xwh = et_xwh.getText().toString();
				AppSettings.setServerIP(MainActivity.this, ip);
				AppSettings.setServerPort(MainActivity.this, port);
				AppSettings.setXWH(MainActivity.this, xwh);
				AppSettings.setHP(MainActivity.this,flag);
//				if (!NetworkUtils.checkNetworkAvailable(MainActivity.this)) {
//					TU.show(MainActivity.this, "您的网络不稳定，请检查网络连接", Toast.LENGTH_LONG);
//					MainActivity.this.finish();
//					return;
//				}
				if (SU.isEmpty(ip)) {
					TU.showLong(MainActivity.this, "服务器IP不能为空");
					return;
				}
				if (SU.isEmpty(port)) {
					TU.showLong(MainActivity.this, "端口号不能为空");
					return;
				}
				if (SU.isEmpty(xwh)) {
					TU.showLong(MainActivity.this, "席位号不能为空");
					return;
				}
				String o = flag==true?"h":"v";
				Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
				intent.putExtra("vh", flag);
				intent.putExtra("url", "http://"+ip+":"+port+"/tvshow"+o+".html?roomid="+xwh);
				intent.putExtra("api", "http://"+ip+":"+port+"/api.ashx");
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
	}

	@Override
	protected void onResume() {
		/**
		 * 设置为横屏
		 */
//		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		AppSettings.setFH(MainActivity.this, false);
		super.onDestroy();
	}

}
