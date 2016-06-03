package com.hbtv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class BootUpReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
//		Intent i = new Intent(context,MainActivity.class);
//		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(i);
		final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
		String action = intent.getAction();
//        TU.show(context, "action " + action);
		if(TextUtils.equals(action, CONNECTIVITY_CHANGE_ACTION)){//网络变化的时候会发送通知
//        	TU.show(context, "网络变化了");
			Intent i = new Intent(context,MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
			return;
		}
	}
}
