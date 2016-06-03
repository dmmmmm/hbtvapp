package com.hongbao.okhttp;

import org.json.JSONException;

/**
 * 数据回调
 * 
 * @author huajun
 * 
 */
public interface HttpDataHandlerListener {
	void setHandlerData(int connectionId, Object data) throws JSONException;

}
