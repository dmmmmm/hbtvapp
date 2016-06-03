package com.hongbao.util;
import android.content.Context;

/**
 * APP����ƫ������
 */
public class AppSettings {
	
	//��һ������APP
	public static String FIRST_START_APP = "first_start_app";

	/**
	 * �����ѵ�һ������APP
	 * @param context
	 * @return
	 */
	public static void setFirstStart(Context context) {
		PreferencesUtils.setPrefBool(context, FIRST_START_APP,
				true, PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * �ж��Ƿ��ǵ�һ������APP
	 * @param context
	 * @return
	 */
	public static boolean isFirstStart(Context context) {
		if (!PreferencesUtils.getPrefBool(context, FIRST_START_APP,
				false, PreferencesUtils.STORE_SETTINGS)) {
			return false;
		}
		return true;
	}

	/**
	 * �����û�id
	 * 
	 * @param context
	 * @return
	 */
	public static void setUserId(Context context, long id) {
		PreferencesUtils.setPrefLong(context, IMap.UER_ID, id,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ��ȡ�û�id
	 * 
	 * @param context
	 * @return
	 */
	public static long getUserId(Context context) {
		return PreferencesUtils.getPrefLong(context, IMap.UER_ID, 0l,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * �����û���
	 * 
	 * @param context
	 * @return
	 */
	public static void setUserName(Context context, String userName) {
		PreferencesUtils.setPrefString(context, IMap.UER_NAME, userName,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ��ȡ�û���
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserName(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.UER_NAME, "",
				PreferencesUtils.STORE_USERINFO);
	}
	/**
	 * ���û���
	 * 
	 * @param context
	 * @return
	 */
	public static void setName(Context context, String Name) {
		PreferencesUtils.setPrefString(context, IMap.UER_NAME, Name,
				PreferencesUtils.STORE_USERINFO);
	}
	
	/**
	 * ��ȡ����
	 * 
	 * @param context
	 * @return
	 */
	public static String getName(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.UER_NAME, "",
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * �����Ƿ����
	 * 
	 * @param context
	 * @return
	 */
	public static void setCheckState(Context context, String status) {
		PreferencesUtils.setPrefString(context, IMap.UER_CHECK_STATE, status,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ��ȡ�Ƿ����
	 * 
	 * @param context
	 * @return
	 */
	public static String getCheckState(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.UER_CHECK_STATE,
				"0", PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ���õ绰
	 * 
	 * @param context
	 * @return
	 */
	public static void setTokenTel(Context context, String tel) {
		PreferencesUtils.setPrefString(context, IMap.EQ_Tel, tel,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ����TokenId
	 * 
	 * @param context
	 * @return
	 */
	public static void setTokenId(Context context, String tolkenId) {
		PreferencesUtils.setPrefString(context, IMap.EQ_tokenId, tolkenId,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ������ӹ������
	 * 
	 * @param context
	 * @return
	 */
	public static void setIsAddOwn(Context context, boolean isAdd) {
		PreferencesUtils.setPrefBool(context, IMap.IS_ADD_KNOWOWN, isAdd,
				PreferencesUtils.STORE_OPERATION);
	}

	/**
	 * ��ȡ�Ƿ���ӹ������
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getIsAddOwn(Context context) {
		return PreferencesUtils.getPrefBool(context, IMap.IS_ADD_KNOWOWN,
				false, PreferencesUtils.STORE_OPERATION);
	}

	/**
	 * ���ÿ�����
	 * 
	 * @param context
	 * @return
	 */
	public static void setCardName(Context context, String cardName) {
		PreferencesUtils.setPrefString(context, IMap.CARD_NAME, cardName,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ��ȡ������
	 * 
	 * @param context
	 * @return
	 */
	public static String getCardName(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.CARD_NAME, "0",
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ����ʱ���
	 * @param context
	 * @param value
	 */
	public static void setTimeStamp(Context context, long value) {
		PreferencesUtils.setPrefLong(
				context, IMap.TIME_STAMP, 
				value,PreferencesUtils.STORE_OPERATION);
	}

	/**
	 * ��ȡʱ���
	 * @param context
	 * @return 
	 */
	public static long getTimeStamp(Context context) {
		return PreferencesUtils.getPrefLong(context, IMap.TIME_STAMP, 0l,
				PreferencesUtils.STORE_OPERATION);
	}

	/**
	 * ���ÿ���
	 * 
	 * @param context
	 * @return
	 */
	public static void setCardNo(Context context, String cardNo) {
		PreferencesUtils.setPrefString(context, IMap.CARD_NO, cardNo,
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ��ȡ����
	 * 
	 * @param context
	 * @return
	 */
	public static String getCardNo(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.CARD_NO, "0",
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ������ӹ����ܵ�
	 * 
	 * @param context
	 * @return
	 */
	public static void setIsAddAlru(Context context, boolean isAdd) {
		PreferencesUtils.setPrefBool(context, IMap.IS_ADD_ALRU, isAdd,
				PreferencesUtils.STORE_OPERATION);
	}
	
	/**
	 * ��ȡ�Ƿ���ӹ����ܵ�
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getIsAddAlru(Context context) {
		return PreferencesUtils.getPrefBool(context, IMap.IS_ADD_ALRU, false,
				PreferencesUtils.STORE_OPERATION);
	}


	/**
	 * ��ȡγ��
	 * 
	 * @param context
	 * @return
	 */
	public static double getlongitude(Context context) {
		return Double.parseDouble(PreferencesUtils.getPrefString(context,
				IMap.LAST_LONGITUDE, "", PreferencesUtils.STORE_SETTINGS));
	}

	/**
	 * ����γ��
	 * 
	 * @param context
	 * @return
	 */
	public static void setlongitude(Context context, String longitude) {
		PreferencesUtils.setPrefString(context, IMap.LAST_LONGITUDE, longitude,
				PreferencesUtils.STORE_SETTINGS);
	}

	/**
	 * ��ȡγ��
	 * 
	 * @param context
	 * @return
	 */
	public static double getLatitude(Context context) {
		return Double.parseDouble(PreferencesUtils.getPrefString(context,
				IMap.LAST_LATITUDE, "", PreferencesUtils.STORE_SETTINGS));
	}

	/**
	 * ����γ��
	 * 
	 * @param context
	 * @return
	 */
	public static void setLatitude(Context context, String latitude) {
		PreferencesUtils.setPrefString(context, IMap.LAST_LATITUDE, latitude,
				PreferencesUtils.STORE_SETTINGS);
	}

	
	/**
	 * ��ȡ�û��绰
	 * 
	 * @param context
	 * @return
	 */
	public static String getTokenTel(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.EQ_Tel, "",
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * ȡ��TokenId
	 * 
	 * @param context
	 * @return
	 */
	public static String getTokenId(Context context) {
		return PreferencesUtils.getPrefString(context, IMap.EQ_tokenId, "",
				PreferencesUtils.STORE_USERINFO);
	}

	/**
	 * �ж��Ƿ��½��
	 * @param context
	 * @return
	 */
	public static boolean isLogin(Context context, String tolkenId) {
		if (SU.isEmpty(tolkenId)) {
			return false;
		}
		return true;
	}
	

	



}
