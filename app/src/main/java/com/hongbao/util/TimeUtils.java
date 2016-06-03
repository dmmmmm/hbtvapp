package com.hongbao.util;
import android.content.Context;
import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间操作工具类 2013/11/23
 * 
 * @author huajun
 * 
 */
public class TimeUtils {
	private static TimeUtils mInstance;

	public TimeUtils(Context mCtx) {
	}

	public static TimeUtils build(Context mCtx) {
		if (mInstance == null) {
			mInstance = new TimeUtils(mCtx);
		}
		return mInstance;
	}

	public void start(long millisInFuture, long countDownInterval,
			final OnTimeFinshListener onFinish) {
		mInstance.startTimer(millisInFuture, countDownInterval, onFinish);
	}

	/**
	 * 定时器
	 * 
	 * @param millisInFuture
	 *            截至时间
	 * @param countDownInterval
	 *            循环时间间隔
	 * @param onFinish
	 */
	private void startTimer(long millisInFuture, long countDownInterval,
			final OnTimeFinshListener onFinish) {
		new CountDownTimer(millisInFuture, countDownInterval) {

			@Override
			public void onTick(long millisUntilFinished) {
				if (onFinish == null) {
					return;
				}
				onFinish.onTick(millisUntilFinished);
			}

			@Override
			public void onFinish() {
				if (onFinish == null) {
					return;
				}
				onFinish.onFinish();
			}
		}.start();
	}

	/**
	 * 计时器监听(对外提供)
	 * 
	 * @author huajun
	 * 
	 */
	public interface OnTimeFinshListener {
		void onFinish();

		void onTick(long millisUntilFinished);
	}

	/**
	 * 
	 * @param @param style 显示类型
	 * @param @param time long类型时间
	 * @return String 返回类型
	 */
	public static String getSureTime(String style, long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		return sdf.format(time);
	}

	public static Long getTime(String user_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		Date d;
		long l = 0l;
		try {
			d = sdf.parse(user_time);
			l = d.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	// 将时间戳转为字符串
	public static String getStrTime(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

}
