package com.hongbao.okhttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * 用来封装Request对象
 * 
 * @author huajun
 * 
 */
public class HttpRequestBuilder {

	Request.Builder requestBuilder;
	private HashMap<String, String> params;
	private HttpMethod method;
	private String url;
	private boolean hasFile;
	private boolean isDownLoad;
	private List<FileDiscription> fileList;
	private String downLoadDir;
	private String downLoadFileName;

	public HttpRequestBuilder() {
		params = new HashMap<String, String>();
		requestBuilder = new Request.Builder();
		hasFile = false;
		fileList = new ArrayList<FileDiscription>();
	}

	/**
	 * 地址
	 * 
	 * @param url
	 *            网址
	 * @return
	 */
	public HttpRequestBuilder url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 添加请求头部
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpRequestBuilder addHeader(String key, String value) {
		requestBuilder.addHeader(key, value);
		return this;
	}

	public HttpRequestBuilder addFile(FileDiscription file) {
		fileList.add(file);
		hasFile = true;
		return this;
	}

	/**
	 * 添加键�?�对请求参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpRequestBuilder addParams(String key, String value) {
		params.put(key, value);
		return this;
	}

	/**
	 * 下载文件设置�? 请求方法�?要设置为get.也支持添加httpHeader.
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public HttpRequestBuilder downLoadPath(String dir, String fileName) {
		this.isDownLoad = true;
		this.downLoadDir = dir;
		this.downLoadFileName = fileName;
		return this;
	}

	/**
	 * 添加http方法
	 * 
	 * @param method
	 * @return
	 */
	public HttpRequestBuilder method(HttpMethod method) {
		this.method = method;
		return this;
	}

	/**
	 * 建立httpGet请求方式
	 * 
	 * @return
	 */
	public HttpRequest build() {
		HttpRequest request = new HttpRequest();
		if (this.url == null)
			throw new IllegalArgumentException("url is null");
		if (this.method == null)
			this.method = HttpMethod.GET;
		if (this.method.name().equals(HttpMethod.GET.name())) {
			BindGetRequet(request);
		}
		request.setRequest(requestBuilder.url(url).build());
		return request;
	}

	/**
	 * 建立httpPost请求方式
	 * 
	 * @param postParams
	 * @return
	 */
	public HttpRequest build(String postParams) {
		HttpRequest request = new HttpRequest();
		if (this.url == null) {
			throw new IllegalArgumentException("url is null");
		}
		bindPostRequestParams(requestBuilder, postParams);
		request.setRequest(requestBuilder.url(url).build());
		return request;
	}

	private void BindGetRequet(HttpRequest request) {
		// TODO Auto-generated method stub
		if (isDownLoad) {
			request.setFileDir(downLoadDir);
			request.setFileName(downLoadFileName);
		}
		this.url = bindGetRequestParam(url, params);
	}

	/**
	 * 处理http请求实体�?
	 */
	private void BindPostRequest() {
		// TODO Auto-generated method stub
		if (!hasFile) {
			bindPostRequestParams(requestBuilder, params);
		} else {
			bindPostFileParams();
		}
	}

	private void bindPostFileParams() {
		// TODO Auto-generated method stub
		MultipartBuilder multipartBuilder = new MultipartBuilder();
		multipartBuilder.type(MultipartBuilder.FORM);
		for (FileDiscription file : fileList) {
			bindFilePart(multipartBuilder, file);
		}
		if (params == null)
			return;
		Set<String> set = params.keySet();
		for (String key : set) {
			String value = params.get(key);
			if (value == null)
				value = "";
			multipartBuilder.addFormDataPart(key, value);
		}
		requestBuilder.post(multipartBuilder.build());
	}

	/**
	 * 绑定文件
	 * 
	 * @param multipartBuilder
	 * @param key
	 * @param file
	 */
	private static void bindFilePart(MultipartBuilder multipartBuilder,
			FileDiscription fileDiscription) {
		// TODO Auto-generated method stub
		multipartBuilder
				.addFormDataPart(
						fileDiscription.getKey(),
						fileDiscription.getFileName(),
						new CustomRequestBody(
								RequestBody.create(MediaType
										.parse(fileDiscription.getMediaType()),
										fileDiscription.getFile()),
								fileDiscription.getUploadPrograssListener()))
				.type(MultipartBuilder.FORM).build();
	}

	/**
	 * 绑定post参数 Map集合类型数据传输
	 * 
	 * @param builder
	 * @param params
	 */
	private void bindPostRequestParams(Request.Builder builder,
			HashMap<String, String> params) {
		FormEncodingBuilder formBodyBuilder = new FormEncodingBuilder();
		if (params == null || params.size() == 0)
			return;
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String parameValue = params.get(key);
			formBodyBuilder.add(key, parameValue);
		}
		builder.post(formBodyBuilder.build());
	}

	/**
	 * 绑定post参数 JSON字符串数据传�?
	 * 
	 * @param builder
	 * @param jsonParams
	 */
	private void bindPostRequestParams(Request.Builder builder,
			String jsonParams) {
		RequestBody body = RequestBody.create(OkHttpUtil.JSON, jsonParams);
		builder.post(body);
	}

	/**
	 * 绑定get请求参数
	 * 
	 * @param url
	 *            地址
	 * @param param
	 *            参数
	 * @return
	 */
	private String bindGetRequestParam(String url, Map<String, String> param) {
		if (param == null)
			return url;
		Set<String> keySet = param.keySet();
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		sb.append("?");
		for (String key : keySet) {
			sb.append(key);
			sb.append("=");
			sb.append(param.get(key));
			sb.append("&");
		}
		return sb.subSequence(0, sb.length() - 1).toString();
	}

	public enum HttpMethod {
		GET, POST, DELETE, PUT;
	}

}
