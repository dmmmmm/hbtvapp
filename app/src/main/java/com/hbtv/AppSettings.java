package com.hbtv;
import android.content.Context;

/**
 * APP常用偏好设置
 */
public class AppSettings {

	//横竖屏
	public static String selectVH = "selectVH";
	//服务器端口号
	public static String serverPort = "serverPort";
	//服务器IP
	public static String serverIP = "serverIP";
	//席位号
	public static String xwh = "xwh";


	/**
	 * 设置为横屏
	 * @param context
	 * @return
	 */
	public static void setHP(Context context,boolean vh) {
		PreferencesUtils.setPrefBool(context, selectVH,
				vh, PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 判断是否是横屏
	 * @param context
	 * @return
	 */
	public static boolean isHP(Context context) {
		if (!PreferencesUtils.getPrefBool(context, selectVH,
				true, PreferencesUtils.STORE_SETTINGS)) {
			return false;
		}
		return true;
	}

	/**
	 * 设置为返回
	 * @param context
	 * @return
	 */
	public static void setFH(Context context,boolean fh) {
		PreferencesUtils.setPrefBool(context, "fhflag",
				fh, PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 判断是否是返回
	 * @param context
	 * @return
	 */
	public static boolean isFH(Context context) {
		if (!PreferencesUtils.getPrefBool(context, "fhflag",
				false, PreferencesUtils.STORE_SETTINGS)) {
			return false;
		}
		return true;
	}



	/**
	 * 设置服务器IP
	 * @param context
	 * @return
	 */
	public static void setServerIP(Context context, String ip) {
		PreferencesUtils.setPrefString(context, serverIP, ip,
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 获取服务器IP
	 *
	 * @param context
	 * @return
	 */
	public static String getServerIP(Context context) {
		return PreferencesUtils.getPrefString(context, serverIP, "",
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 设置服务器port
	 * @param context
	 * @return
	 */
	public static void setServerPort(Context context, String port) {
		PreferencesUtils.setPrefString(context, serverPort, port,
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 获取服务器port
	 *
	 * @param context
	 * @return
	 */
	public static String getServerPort(Context context) {
		return PreferencesUtils.getPrefString(context, serverPort, "",
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 设置席位号
	 * @param context
	 * @return
	 */
	public static void setXWH(Context context, String hao) {
		PreferencesUtils.setPrefString(context, xwh, hao,
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * 获取席位号
	 * @param context
	 * @return
	 */
	public static String getXWH(Context context) {
		return PreferencesUtils.getPrefString(context, xwh, "",
				PreferencesUtils.STORE_SETTINGS);
	}

}
