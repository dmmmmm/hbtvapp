package com.hbtv;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 偏好设置帮助类
 */
public class PreferencesUtils {

	public static final String STORE_SETTINGS = "settings";

	/**
	 * set preference String
	 */
	public static void setPrefString(Context context, String key, String value,
									 String name) {
		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		mSettings.edit().putString(key, value).commit();
	}

	/**
	 * get preference String
	 */
	public static String getPrefString(Context context, String key,
									   String defaultValue, String name) {
		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		return mSettings.getString(key, defaultValue);
	}

	/**
	 * set preference int
	 */
	public static void setPrefInt(Context context, String key, int value,
								  String name) {

		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		mSettings.edit().putInt(key, value).commit();
	}

	/**
	 * get preference int
	 */
	public static int getPrefInt(Context context, String key, int defaultValue,
								 String name) {
		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		return mSettings.getInt(key, defaultValue);
	}

	/**
	 * set preference long
	 * @param context
	 * @param key
	 * @param value
	 * @param filename
	 */
	public static void setPrefLong(
			Context context, String key,
			long value,String filename) {
		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(
				filename,Context.MODE_WORLD_WRITEABLE);
		mSettings.edit().putLong(key, value).commit();
	}

	/**
	 * get preference long
	 * @param context
	 * @param key
	 * @param defaultValue
	 * @param filename
	 * @return
	 */
	public static long getPrefLong(Context context, String key,
								   long defaultValue, String filename) {
		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(filename,
				Context.MODE_WORLD_WRITEABLE);
		return mSettings.getLong(key, defaultValue);
	}

	/**
	 * set preference bool
	 */
	public static void setPrefBool(Context context, String key, boolean value,
								   String name) {

		SharedPreferences mSettings;
		mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		mSettings.edit().putBoolean(key, value).commit();
	}

	/**
	 * get preference bool
	 */
	public static boolean getPrefBool(Context context, String key,
									  boolean defaultValue, String name) {
		SharedPreferences mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		return mSettings.getBoolean(key, defaultValue);
	}

	/**
	 * 清除用户的信息
	 */
	public static void clearInfo(Context context, String name) {
		// mEditor.putString(ConstantsUtil.SESSION_ID, sessionId);
		// mEditor.commit();
		SharedPreferences mSettings = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		mSettings.edit().clear().commit();
	}

}
