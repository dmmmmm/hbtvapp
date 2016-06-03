package com.hongbao.util;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ��ȡ��Ӧ���ݣ�JSON���ַ���������ӦԪ�صİ�����
 */
public class IMap {
	
	public static String STATE = "state";
	public static String USER_INFO = "user_info";
	public static String EQ_Tel = "eq_Tel";
	public static String DRIVERINFO = "driver_info";
	public static String UER_ID = "user_id";
	public static String UER_NAME = "user_name";
	public static String UER_CHECK_STATE = "user_check_state";
	public static String EQ_tokenTel = "eq_tokenTel";
	public static String EQ_tokenId = "eq_tokenId";
	public static String L = "l";
	public static String LIST = "list";
	public static String BALANCE = "balance";// ���
	public static String INTEGRAL = "integral";// ����
	public static String IS_ADD_KNOWOWN = "knowown";// �Ƿ�ִ�й�������������
	public static String IS_ADD_ALRU = "alru";// �Ƿ�ִ�й���ӳ��ܵز���
	public static String RED_PURSE_OPEN_TIME = "open_time";// �������
	public static String CARD_NO = "cardNo";// ����
	public static String CARD_NAME = "cardName";// ��������

	public static String LAST_LONGITUDE = "longitude";// ����
	public static String LAST_LATITUDE = "latitude";// γ��
	
	public static String TIME_STAMP = "time_stamp";
	
	/**
	 * ��ȡl(����/����)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getLFromResponse(String response, Class calss) {
		if (response == null || response.equals("")) {
			return null;
		}
		//��JSON���ַ���תΪjava����IParams
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "", IParams.class);
		//���IParams�����е�l�ֶ�
		List<LinkedHashMap> l = iParams.getL();
		List list = null;
		if (l != null) {
			//��JSON���ַ���תΪList<calss>���͵Ķ��� 
			list = JSON.parseArray(JSON.toJSONString(l), calss);
			return list;
		} else {
			return null;
		}

	}

	/**
	 * ��ȡu(��ҳ��Ϣ�е���ҳ��totalPage)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int getUFromResponse(String response) {
		if (response == null || response.equals("")) {
			return 0;
		}
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "", IParams.class);
		if (iParams != null) {
			Map<String, Object> map = iParams.getU();
			return Integer.parseInt(String.valueOf(map.get("totalPage")));
		} else {
			return 0;
		}
	}

	
	
	/**
	 * ��ȡg(ͨ������״̬)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int getGFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return iParams.getG();
	}
	
	
	/**
	 * ��ȡl(���ص�M�е�l�ֶ�)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getlFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return String.valueOf(iParams.getM().get("l"));
	}
	
	/**
	 * ��ȡa(��ʾ��Ϣ)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getAFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return (String) iParams.getA();
	}
	
	
	
	
	

}
