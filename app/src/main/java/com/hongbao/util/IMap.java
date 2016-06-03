package com.hongbao.util;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取响应数据（JSON型字符串）中相应元素的帮助类
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
	public static String BALANCE = "balance";// 余额
	public static String INTEGRAL = "integral";// 积分
	public static String IS_ADD_KNOWOWN = "knowown";// 是否执行过添加熟货主操作
	public static String IS_ADD_ALRU = "alru";// 是否执行过添加常跑地操作
	public static String RED_PURSE_OPEN_TIME = "open_time";// 红包次数
	public static String CARD_NO = "cardNo";// 卡号
	public static String CARD_NAME = "cardName";// 卡的类型

	public static String LAST_LONGITUDE = "longitude";// 经度
	public static String LAST_LATITUDE = "latitude";// 纬度
	
	public static String TIME_STAMP = "time_stamp";
	
	/**
	 * 获取l(集合/对象)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getLFromResponse(String response, Class calss) {
		if (response == null || response.equals("")) {
			return null;
		}
		//将JSON型字符串转为java对象IParams
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "", IParams.class);
		//获得IParams对象中的l字段
		List<LinkedHashMap> l = iParams.getL();
		List list = null;
		if (l != null) {
			//将JSON型字符串转为List<calss>类型的对象 
			list = JSON.parseArray(JSON.toJSONString(l), calss);
			return list;
		} else {
			return null;
		}

	}

	/**
	 * 获取u(分页信息中的总页数totalPage)
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
	 * 获取g(通过返回状态)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int getGFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return iParams.getG();
	}
	
	
	/**
	 * 获取l(返回的M中的l字段)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getlFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return String.valueOf(iParams.getM().get("l"));
	}
	
	/**
	 * 获取a(提示信息)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getAFromResponse(String response) {
		IParams<LinkedHashMap> iParams = JSON.parseObject(response + "",IParams.class);
		return (String) iParams.getA();
	}
	
	
	
	
	

}
