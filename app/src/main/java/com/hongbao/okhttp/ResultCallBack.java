package com.hongbao.okhttp;

import com.squareup.okhttp.Request;


/**
 * okhttp���ݻص�
 * 
 * @author dm
 * 
 */
public interface ResultCallBack {

	public void onResponse(int connectedId, Object response);

	public void onError(int connectedId, Request request,
			HttpException httpException);
}
